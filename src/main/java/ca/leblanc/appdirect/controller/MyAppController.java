package ca.leblanc.appdirect.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class MyAppController {

	private static final Logger logger = LoggerFactory.getLogger(AppDirectEventController.class);	
	
    private static final String LOGIN = "login";
    
	/**
     * <p>Welcome Page.</p>
     * 
     * <p>Expected HTTP GET and request '/login'.</p>
     */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	logger.info("*** User requested login");
    	
            return LOGIN;
    }
}
