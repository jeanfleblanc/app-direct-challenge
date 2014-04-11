package ca.leblanc.appdirect.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.exception.UserNotExistException;
import ca.leblanc.appdirect.service.SubscriptionService;

/**
 * Application Main Page.
 * 
 * @author jean_francois
 */
@Controller()
public class MyAppController {

	private static final Logger logger = LoggerFactory.getLogger(AppDirectEventController.class);	
	
    private static final String LOGIN = "login";
    private static final String WELCOME = "welcome"; 
    private static final String MY_APP = "myApp";
    
    @Autowired
    private SubscriptionService subscriptionService;
    
	/**
     * <p>Welcome Page.</p>
     * 
     * <p>Expected HTTP GET and request '/login'.</p>
     */
    @RequestMapping(value="/welcome", method=RequestMethod.GET)
    public String welcome(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	logger.info("*** User requested welcome page");

    	String destination = WELCOME;
    	
    	if (request.getRemoteUser() != null) {
    		
    		try {
    			
    			logger.info("OpenId:" + request.getRemoteUser());
    			Subscription subscription = subscriptionService.loadSubscriptionByOpenId(request.getRemoteUser());	
    			request.setAttribute("subscription", subscription);
    			destination = MY_APP;
    		} catch (UserNotExistException e) {
    			
    			logger.error("User not found!", e.getMessage());
    		}
    	}
    	
    	return destination;
    }    
    
	/**
     * <p>Welcome Page.</p>
     * 
     * <p>Expected HTTP GET and request '/login'.</p>
     */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() throws IOException {

    	logger.info("*** User requested login");
    	
        return LOGIN;
    }
}
