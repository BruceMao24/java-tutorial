package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 操作日志切面：自动记录 Controller 每个接口的调用情况
 * Controller 一行代码都不用改，切面会自动包在方法外面
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * @Around：包裹目标方法，方法前后都能插手
     * 切点表达式：execution(* org.example.controller..*.*(..))
     *   第一个 *          —— 任意返回类型
     *   controller..      —— controller 包及其子包
     *   *.*(..)           —— 任意类的任意方法、任意参数
     */
    @Around("execution(* org.example.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 类名.方法名，如 BookController.page
        String name = pjp.getSignature().getDeclaringType().getSimpleName()
                + "." + pjp.getSignature().getName();

        long start = System.currentTimeMillis();
        try {
            // proceed() = 真正执行目标方法，返回值原样透传
            Object result = pjp.proceed();
            log.info("[AOP] {} success, cost {}ms", name, System.currentTimeMillis() - start);
            return result;
        } catch (Throwable e) {
            // 目标方法抛异常也会经过这里：记录后原样抛出，交给全局异常处理
            log.warn("[AOP] {} failed: {}, cost {}ms", name, e.getMessage(), System.currentTimeMillis() - start);
            throw e;
        }
    }
}
