package com.wizz.demo.Aspect;

import com.wizz.demo.Annotaions.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Component
@Aspect//表明这个类是个切面
public class LogAspect {//日志的切面 AOP编程
    private Logger logger= LoggerFactory.getLogger(LogAspect.class);//注意使用的是slf4j的注解下的logger！！！！！

    @Pointcut("execution(* com.wizz.demo.service.Impl ..*(..))")//设置切点表达式，是个学问
    private void pointCut(){

    }
    //方法后置切面
    @After(value="pointCut()")
    public void after(JoinPoint joinPoint) throws ClassNotFoundException {
        //拿到切点的类名，方法名，方法参数
        String className=joinPoint.getTarget().getClass().getName();
        String methodName=joinPoint.getSignature().getName();
        Object [] args=joinPoint.getArgs();

        //通过反射机制获取方法类，遍历类中的所有方法
        Class<?> targetClass=Class.forName(className);
        Method[] methods=targetClass.getMethods();
        for(Method method:methods){//遍历类中的所有方法
            if(method.getName().equalsIgnoreCase(methodName)){
                Class<?>[] clazzs=method.getParameterTypes();//方法名一致，并且参数的个数一致的话，就找到切点了
                if(clazzs.length==args.length){
                    //获取切点上的注解
                    Log logAnnotation=method.getAnnotation(Log.class);
                    if(logAnnotation!=null){
                        String logStr=logAnnotation.logStr();
                        logger.error("获取日志："+logStr);//输出日志
                        break;
                    }

                }
            }





        }

    }


}
