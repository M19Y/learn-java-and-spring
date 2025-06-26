package com.m19y.learn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {


  @Pointcut("target(com.m19y.learn.service.HelloService)")
  public void helloServiceMethod(){
  }

  @Before(value = "helloServiceMethod()") // value-nya reference pada method nama method pointcut-nya
  public void beforeHelloServiceMethod(JoinPoint joinPoint){
    // JoinPoint ini merefleksikan dari targert class nya
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    log.info("Before call -> {}.{}()", className, methodName);
  }

  /* kita bisa memebuat 2 atau lebih log before
  @Before(value = "helloServiceMethod()")
  public void beforeHelloServiceMethod2(){
    log.info("Before HelloService Method again");
  }*/

  @Around(value = "helloServiceMethod()")
  public Object aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable{
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();

    try{
      log.info("Around Before -> {}.{}()", className, methodName);
      return joinPoint.proceed(joinPoint.getArgs());
    }catch (Throwable throwable){
      log.info("Around Error -> {}.{}()", className, methodName);
      throw throwable;
    }finally {
      log.info("Around Finally -> {}.{}()", className, methodName);

    }
  }

  @Pointcut("execution(* com.m19y.learn.service.HelloService.*(java.lang.String))")
  public void pointcutHelloServiceStringParam(){
  }

  // get parameter using JoinPoint
  @Before(value = "pointcutHelloServiceStringParam()")
  public void beforePointcutHelloServiceStringParamJP(JoinPoint joinPoint){
    String param = (String) joinPoint.getArgs()[0];
    String methodName = joinPoint.getSignature().getName();
    log.info("Execute method {}() with get parameter JP: {}", methodName, param);
  }

  // get parameter using args()
  @Before(value = "pointcutHelloServiceStringParam() && args(name)")
  public void beforePointcutHelloServiceStringParamARGS(String name){
    log.info("Execute method with get parameter args : {}", name);
  }


  // Multiple pointcut
  @Pointcut("execution(* com.m19y.learn.service.*.*(..))")
  public void pointcutForServicePackage(){}
  @Pointcut("bean(*Service)")
  public void pointcutForBeanEndWithService(){}
  @Pointcut("execution(public * *(..))")
  public void pointcutForAllMethodsWithAccessModifierPublic(){}

  // combination
  @Pointcut("pointcutForServicePackage() && pointcutForBeanEndWithService() && pointcutForAllMethodsWithAccessModifierPublic()")
  public void publicMethodForService(){}

  @Before(value = "publicMethodForService()")
  public void logAllPublicServiceMethods(){
    log.info("Log all public method at service");
  }

}
