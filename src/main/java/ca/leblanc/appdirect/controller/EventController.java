package ca.leblanc.appdirect.controller;

import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.leblanc.appdirect.domain.Result;

@Controller()
public class EventController {
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	/**
     * <p>Buy event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/buy'.</p>
     */
    @RequestMapping(value="/event/subscriptionOrder", method=RequestMethod.GET)
	public @ResponseBody Result subscriptionOrder(@RequestParam("eventUrl") String eventUrl) throws Exception  {
		
    	// log everything!
    	logger.info("************** Entering method");
    	 
    	// TODO: verify encryption
    	
    	// callback
    	OAuthConsumer consumer = new DefaultOAuthConsumer("bijoux-8197", "RHDwlCp4EhN6Mtmm");
    	
    	logger.info("************** opening url:" + eventUrl);
    	URL url = new URL(eventUrl);
    	HttpURLConnection request = (HttpURLConnection) url.openConnection();
    	consumer.sign(request);
    	request.connect();    	

    	logger.info("About to save!");

    	
    	// TODO: save order
    	
    	// read xml
    	String type = request.getContentType();
    	
    	logger.info("Content type is " + type);
    	
    	// send answer:
    	/*
    	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    	<result>
    	    <success>true</success>
    	    <message>Account creation successful</message>
    	    <accountIdentifier>new-account-identifier</accountIdentifier>
    	</result>
*/
    	//or for errors:http://info.appdirect.com/developers/docs/event_references/api_error_codes/ 
    	
    	Result result = new Result(true, "Account creation successful", "fake124");
    	
    	/*
    	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<result>
    <success>false</success>
    <errorCode>ACCOUNT_NOT_FOUND</errorCode>
    <message>The account TEST123 could not be found.</message>
</result>
For more information about the supported error codes, see the error code documentation.
    	 */
    	
    	return result;
    }
    
    /**
     * <p>Buy event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/buy'.</p>
     */
    @RequestMapping(value="/event/subscriptionCancel", method=RequestMethod.GET)
	public @ResponseBody Result subscriptionCancel(@RequestParam("eventUrl") String eventUrl) throws Exception  {
		
    	// log everything!
    	logger.info("************** Entering method");
    	 
    	// TODO: verify encryption
    	
    	// callback
    	OAuthConsumer consumer = new DefaultOAuthConsumer("bijoux-8197", "RHDwlCp4EhN6Mtmm");
    	
    	logger.info("************** opening url:" + eventUrl);
    	URL url = new URL(eventUrl);
    	HttpURLConnection request = (HttpURLConnection) url.openConnection();
    	consumer.sign(request);
    	request.connect();    	

    	logger.info("About to save!");

    	
    	// TODO: cancel order
    	
    	// read xml
    	String type = request.getContentType();
    	
    	logger.info("Content type is " + type);
    	
    	Result result = new Result(true, "Account Cancellation successful", "fake124");
    
    	return result;
    }    
}