package com.example.demo.aspect;

import com.example.demo.aspect.annotation.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description 定义日志切面
 *
 * @Lazy 注解:容器一般都会在启动的时候实例化所有单实例 bean，
 * 如果我们想要 Spring 在启动的时候延迟加载 bean，需要用到这
 * 个注解，value为 true 和 false，默认为 true，即延迟加载，
 * Lazy(false)表示对象会在初始化的时候创建。
 *
 * @Auther 葛定财
 * @Date 2019/11/12 17:29
 * @Version 1.0
 */
@Aspect
@Component
@Lazy(false)
public class LoggerAspect {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     *
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* com.example.annotation.LoggerApply.*（..））
     *     com.example.annotation.LoggerApply这个类里的所有的方法
     * 4、execution（* com.example.annotation.*.*（..））
     *     com.example.annotation包下的所有的类的所有的方法
     * 5、execution（* com.example.annotation..*.*（..））
     *     com.example.annotation包及子包下所有的类的所有的方法
     * 6、execution(* com.example.annotation..*.*(String,?,Long))
     *     com.example.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * 7、execution(@annotation(com.example.annotation.SysLog))
     */

    /**
    * @Description 这里使用的是注解的形式，只要使用了@SysLog的注解都会调用这个方法，
     * 而这个方法是个切面方法，会被增强（@Before、@After...）。
    * @Param []
    * @Return void
    */
    @Pointcut("@annotation(com.example.demo.aspect.annotation.SysLog)")
    private void cutMethod() {

    }

    /**
    * @Description 当然，我们也可以通过切点表达式直接指定需要拦截的 package、
     * class 以及 method。
     *
     * 这里就是正常的切面方法，指定到方法和参数。第一个 * 通配权限修饰符和返回参数，
     * （..）表示通配入参。
    * @Param []
    * @Return void
    */
    @Pointcut("execution(* com.example.demo.aspect.service.SysLogService.save(..))")
    private void cutMethod2() {

    }

    /**
    * @Description 前置通知：在目标方法执行前调用
    * @Param []
    * @Return void
    */
    @Before("cutMethod()")
    public void begin() {
        System.out.println("==Daniel Ge logger==: begin");
    }

    @Before("cutMethod2()")
    public void before2() {
        System.out.println("==Daniel Ge logger==: before22222");
    }

    /**
    * @Description 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
    * @Param []
    * @Return void
    */
    @AfterReturning("cutMethod()")
    public void afterReturning() {
        System.out.println("==Daniel Ge logger==: after returning");
    }

    /**
    * @Description 后置/最终通知：无论目标方法在执行过程中是否出现异常都会在它之后调用
    * @Param []
    * @Return void
    */
    @After("cutMethod()")
    public void after() {
        System.out.println("==Daniel Ge logger==: finally returning");
    }

    /**
    * @Description 异常通知：目标方法抛出异常时执行
    * @Param []
    * @Return void
    */
    @AfterThrowing("cutMethod()")
    public void afterThrowing() {
        System.out.println("==Daniel Ge logger==: after throwing");
    }

    /**
    * @Description 环绕通知：灵活自由的在目标方法中切入代码
    * @Param [joinPoint]
    * @Return void
    */
    @Around("cutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        SysLog sysLog = getDeclaredAnnotation(joinPoint);
        Long beginTime = System.currentTimeMillis();
        System.out.println("==Daniel Ge logger==: 环绕之前 ");
        // 执行源方法
        Object result = joinPoint.proceed();
        System.out.println("==Daniel Ge logger==: 环绕之后 ");
        Long spaceTime = System.currentTimeMillis() - beginTime;
        try {
            saveLog(spaceTime);
        } catch (Exception e) {}
        return result;
    }

    /**
    * @Description 保存日志
    * @Param []
    * @Return void
    */
    public void saveLog(Long time) {
        System.out.println("保存日志： " + time);
    }

    /**
    * @Description 获取方法中声明的注解
    * @Param [joinPoint]
    * @Return com.example.demo.aspect.annotation.SysLog
    */
    public SysLog getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        SysLog annotation = objMethod.getDeclaredAnnotation(SysLog.class);
        return annotation;
    }
}
