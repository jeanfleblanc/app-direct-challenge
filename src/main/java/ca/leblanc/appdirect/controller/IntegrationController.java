package ca.leblanc.appdirect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntegrationController {

    private static final String WELCOME = "welcome";

	/**
     * <p>Welcome Page.</p>
     * 
     * <p>Expected HTTP GET and request '/welcome'.</p>
     */
    @RequestMapping(value="/welcome", method=RequestMethod.GET)
    public String delete() {
       
        return WELCOME;
    }
}
