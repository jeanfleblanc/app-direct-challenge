package ca.leblanc.appdirect.controller;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import net.oauth.OAuthException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.leblanc.appdirect.domain.Result;
import ca.leblanc.appdirect.domain.ErrorResult;
import ca.leblanc.appdirect.domain.SuccessResult;
import ca.leblanc.appdirect.util.OAuthSignature;

@RestController()
public class AppDirectEventController {
	
	private static final Logger logger = LoggerFactory.getLogger(AppDirectEventController.class);
	
	@Autowired
	private OAuthSignature oauthSignature;

	/**
     * <p>Buy event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/buy'.</p>
     */
    @RequestMapping(value="/event/buy", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public Result buy(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {

    	Result result;
  
       	try {

       		// validate request
       		oauthSignature.validate(request);       		       	
	    	
	    	logger.info("Opening url:" + eventUrl);

	    	// sign and send request
	    	HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
	    	oauthSignature.sign(conn);
	    	conn.connect();    	
	
	    	logger.info("Save order");
	    	
	    	// read response
	    	String type = conn.getContentType();
	    	String content = conn.getResponseMessage();

	    	// TODO: save order	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
	    	// send success response	    	
	    	result = new SuccessResult(true, SuccessResult.ACCOUNT_CREATION_SUCCESSFUL, "fake124");
	    	
       	}
       	catch (OAuthException e) {

	    	// send error response	    	       		
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
       	}
       	
    	return result;
    }

    /**
     * <p>Cancel event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/change'.</p>
     */
    @RequestMapping(value="/event/change", method=RequestMethod.GET)
	public @ResponseBody Result change(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {
		
    	Result result;
  
       	try {

       		// validate request
       		oauthSignature.validate(request);       		       	
	    	
	    	logger.info("Opening url:" + eventUrl);

	    	// sign and send request
	    	HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
	    	oauthSignature.sign(conn);
	    	conn.connect();    	
	
	    	logger.info("Cancel order");
	    	
	    	// read response
	    	String type = conn.getContentType();
	    	String content = conn.getResponseMessage();

	    	// TODO: change order	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
	    	// send success response	    	
	    	result = new SuccessResult(true, SuccessResult.ACCOUNT_CHANGE_SUCCESSFUL, "fake124");
	    	
       	}
       	catch (OAuthException e) {

	    	// send error response	    	       		
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
       	}
       	
    	return result;
    }        
    
    /**
     * <p>Cancel event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/cancel'.</p>
     */
    @RequestMapping(value="/event/cancel", method=RequestMethod.GET)
	public @ResponseBody Result cancel(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {
    	
    	Result result;
  
       	try {

       		// validate request
       		oauthSignature.validate(request);       		       	
	    	
	    	logger.info("Opening url:" + eventUrl);

	    	// sign and send request
	    	HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
	    	oauthSignature.sign(conn);
	    	conn.connect();    	
	
	    	logger.info("Cancel order");
	    	
	    	// read response
	    	String type = conn.getContentType();
	    	String content = conn.getResponseMessage();

	    	// TODO: cancel order	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
	    	// send success response	    	
	    	result = new SuccessResult(true, SuccessResult.ACCOUNT_CANCELLATION_SUCCESSFUL, "fake124");
	    	
       	}
       	catch (OAuthException e) {

	    	// send error response	    	       		
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
       	}
       	
    	return result;
    }    
    
    /**
     * <p>Add-on event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/addon'.</p>
     */
    @RequestMapping(value="/event/addon", method=RequestMethod.GET)
	public @ResponseBody Result addon(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {
    	
    	Result result;
  
       	try {

       		// validate request
       		oauthSignature.validate(request);       		       	
	    	
	    	logger.info("Opening url:" + eventUrl);

	    	// sign and send request
	    	HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
	    	oauthSignature.sign(conn);
	    	conn.connect();    	
	
	    	logger.info("Add something");
	    	
	    	// read response
	    	String type = conn.getContentType();
	    	String content = conn.getResponseMessage();

	    	// TODO: cancel order	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
	    	// send success response	    	
	    	result = new SuccessResult(true, SuccessResult.ACCOUNT_ADDON_SUCCESSFUL, "fake124");
	    	
       	}
       	catch (OAuthException e) {

	    	// send error response	    	       		
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
       	}
       	
    	return result;
    }      
    
    /**
     * <p>Assign user event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/assignUser'.</p>
     */
    @RequestMapping(value="/event/assignUser", method=RequestMethod.GET)
	public @ResponseBody Result assignUser(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {
    	
    	Result result;
  
       	try {

       		// validate request
       		oauthSignature.validate(request);       		       	
	    	
	    	logger.info("Opening url:" + eventUrl);

	    	// sign and send request
	    	HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
	    	oauthSignature.sign(conn);
	    	conn.connect();    	
	
	    	logger.info("Assign user");
	    	
	    	// read response
	    	String type = conn.getContentType();
	    	String content = conn.getResponseMessage();

	    	// TODO: assign user	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
	    	// send success response	    	
	    	result = new SuccessResult(true, SuccessResult.ASSIGN_USER_SUCCESSFUL, "fake124");
	    	
       	}
       	catch (OAuthException e) {

	    	// send error response	    	       		
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
       	}
       	
    	return result;
    }    
    
    /**
     * <p>Unassign user event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/unassignUser'.</p>
     */
    @RequestMapping(value="/event/unassignUser", method=RequestMethod.GET)
	public @ResponseBody Result unassign(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {
    	
    	Result result;
  
       	try {

       		// validate request
       		oauthSignature.validate(request);       		       	
	    	
	    	logger.info("Opening url:" + eventUrl);

	    	// sign and send request
	    	HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
	    	oauthSignature.sign(conn);
	    	conn.connect();    	
	
	    	logger.info("Unassign user");
	    	
	    	// read response
	    	String type = conn.getContentType();
	    	String content = conn.getResponseMessage();

	    	// TODO: unassign user	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
	    	// send success response	    	
	    	result = new SuccessResult(true, SuccessResult.UNASSIGN_USER_SUCCESSFUL, "fake124");
	    	
       	}
       	catch (OAuthException e) {

	    	// send error response	    	       		
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
       	}
       	
    	return result;
    }        
}