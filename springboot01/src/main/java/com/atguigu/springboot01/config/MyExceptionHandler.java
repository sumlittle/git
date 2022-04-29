package com.atguigu.springboot01.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
//@Slf4j
//public class MyExceptionHandler {
//
//    @ExceptionHandler(value =Exception.class)
//	public String exceptionHandler(Exception e){
//		log.info("error",e);
//       	return e.getMessage();
//    }
//}