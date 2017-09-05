package com.prem.studyproject.exceptions.handler;


import com.sun.mail.util.MailConnectException;
import lombok.extern.slf4j.Slf4j;
import org.omg.SendingContext.RunTime;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MailConnectException.class)
    public void myError(MailConnectException exception) {
        log.error("MailConnectException", exception);
    }

    @ExceptionHandler(RuntimeException.class)
    public void myError(RuntimeException exception) {
        log.error("Another runtime exception", exception);
    }


}
