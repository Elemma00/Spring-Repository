package com.emma.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                HandlerMethod methodName = (HandlerMethod) handler;
                logger.info("LoadingTimeInterceptor: preHandle() entrando ... " + methodName.getMethod().getName());
                //Iniciamos el tiempo de inicio
                long start = System.currentTimeMillis();
                request.setAttribute("start", start);
                Random random = new Random();
                int delay = random.nextInt(2000);
                //simulamos latencia en la red con un delay aleatorio
                Thread.sleep(delay);
                // return true;
                return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
                long end = System.currentTimeMillis();
                long start = (long) request.getAttribute("start");
                long result = end - start;
                logger.info("Tiempo transcurrido: " + result + " ms");
                logger.info("LoadingTimeInterceptor: postHandle() saliendo...");
    }


}
