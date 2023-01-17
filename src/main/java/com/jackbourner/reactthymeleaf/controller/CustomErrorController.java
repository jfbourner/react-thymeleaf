package com.jackbourner.reactthymeleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        String errorDetails = "";

        int httpErrorCode = getErrorCode(httpRequest);
        Throwable throwable = (Throwable) httpRequest.getAttribute(ERROR_EXCEPTION);
        switch (httpErrorCode) {
            case 400 -> {
                errorMsg = "Http Error Code: 400. Bad Request";
            }
            case 401 -> {
                errorMsg = "Http Error Code: 401. Unauthorized";
            }
            case 403 -> {
                errorMsg = "Http Error Code: 403. forbidden";
            }
            case 404 -> {
                errorMsg = "Http Error Code: 404. Resource not found";
            }
            case 500 -> {
                errorMsg = "Http Error Code: 500. Internal Server Error";
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("errorDetails", errorDetails);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
       Object a =  httpRequest.getAttribute(ERROR_STATUS_CODE);
       if(a instanceof Integer castInt){
           return castInt;
       } else {
           log.error("Cant cast status_code: {} to int. Either not int or null", a);
           return 0;
       }
    }
}
