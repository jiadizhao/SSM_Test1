package com.zhaofan.Aop;

import com.zhaofan.controller.SysLogController;
import com.zhaofan.domain.SysLog;
import com.zhaofan.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 该类作用:使用Aop将每次用户的操作记录下来,并存放到SysLog中,
 * 该类的操作围绕SysLog进行
 * 分为前置通知和后置通知
 *
 * JoinPoint 封装了SpringAop中切面的方法信息
 *
 * getSignatrue() 获取署名信息对象,可获得目标方法名,所属类的Class信息
 * getTarget()  获得被代理的对象
 *
 */


@Component
@Aspect
public class AopLog {
    private Class clazz;
    private Method method;
    private Date visitTime;//开始时间
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    //获得访问时间
    @Before("execution(* com.zhaofan.controller.*.*(..))")
    public void beforeLog(JoinPoint jp) throws NoSuchMethodException {//joinPoint 中切面的方法信息
        //获取访问时间
        visitTime= new Date();
        clazz=jp.getTarget().getClass();//获得具体访问的类
        String methodName=jp.getSignature().getName();//获得方法名
        //获得方法,
        if(clazz!=null&&methodName!=null){
            Object[] args = jp.getArgs();//获得参数
            if(args.length==0){//如果该方法没有参数,则可以直接获得
                method = clazz.getMethod(methodName);
            }else{//如果有参数,那么需要先将参数转化为Class对象
                Class[] classMethod=new Class[args.length];
                for(int i=0;i<args.length;i++){
                    classMethod[i]=args[i].getClass();
                }
                method=clazz.getMethod(methodName,classMethod);
            }
        }
    }

    //获得方法名称,ip,url,运行时长
    @After("execution(* com.zhaofan.controller.*.*(..))")
    public void afterLog() throws Exception {
        SysLog sysLog=new SysLog();
        long executionTime = new Date().getTime() - visitTime.getTime();//获取运行时间
        String url;
        //获取url,需要通过类和方法上的
        if(clazz!=null&&method!=null&&clazz!=AopLog.class&&clazz!= SysLogController.class){
            //获取类上的url
            RequestMapping classAoontation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAoontation!=null){
                RequestMapping methodAnnotation=method.getAnnotation(RequestMapping.class);
                url=classAoontation.value()[0]+"/"+methodAnnotation.value()[0];
                String ip=request.getRemoteAddr();
                //获得操作用户
                SecurityContext context = SecurityContextHolder.getContext();
                User user = (User) context.getAuthentication().getPrincipal();
                //第二种获得用户的方式
                Object u = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                System.out.println("第二种获得用户的方式"+u);
                sysLog.setVisitTime(visitTime);
                sysLog.setExecutionTime(executionTime);
                sysLog.setIp(ip);
                sysLog.setMethod(method.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(user.getUsername());
                sysLogService.save(sysLog);
            }

        }
    }

}
