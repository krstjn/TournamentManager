package project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Catches errors, and renders a more user friendly error page
 */
@Controller
public class CustomErrorController implements ErrorController {
    private Logger logger = LogManager.getLogger(ErrorController.class);

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            logger.error("Error - statusCode: " + statusCode);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                m.addObject("errorMsg", statusCode + " - Page not found");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                m.addObject("errorMsg", statusCode + " - Server error");
            }
            else {
                m.addObject("errorMsg","Status code - " + statusCode);
            }
        }
        return m;
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
