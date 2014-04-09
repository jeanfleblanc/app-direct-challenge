package ca.leblanc.appdirect.controller;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.miniauth.exception.ValidationException;
import org.miniauth.oauth.credential.mapper.OAuthSingleConsumerCredentialMapper;
import org.miniauth.web.oauth.OAuthProviderAuthHandler;
import org.miniauth.web.oauth.OAuthSingleConsumerURLConnectionAuthHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.leblanc.appdirect.domain.EventErrorCode;
import ca.leblanc.appdirect.domain.Result;

@RestController()
public class EventController {
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	/**
     * <p>Buy event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/buy'.</p>
     */
    @RequestMapping(value="/event/subscriptionOrder", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public Result subscriptionOrder(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception  {
		
    	Result result;
    	
    	// log everything!
    	logger.info("************** Entering method");
 
       
    	
    	// Verify signature       	
       	OAuthSingleConsumerCredentialMapper mapper = new OAuthSingleConsumerCredentialMapper("bijoux-8197", "RHDwlCp4EhN6Mtmm");
       	OAuthProviderAuthHandler oauthProviderAuthHandler = new OAuthProviderAuthHandler(mapper);
       	boolean validSignature;
       	
       	try {
       		
       		validSignature = oauthProviderAuthHandler.verifyRequest(request);
       	} catch (ValidationException e) {
       			
       		validSignature = false;
       	}
       	
       	if (validSignature) {
       	
	       	// Connect to retrieve info
	       	URL url = new URL(eventUrl);
	       	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	       	OAuthSingleConsumerURLConnectionAuthHandler handler = new OAuthSingleConsumerURLConnectionAuthHandler("bijoux-8197", "RHDwlCp4EhN6Mtmm");
	       	handler.endorseRequest(conn);
	       	conn.connect();
	       	
	    	// callback
	    	
	    	logger.info("************** opening url:" + eventUrl);
	       	//OAuthConsumer consumer = new DefaultOAuthConsumer("bijoux-8197", "RHDwlCp4EhN6Mtmm");        
	    	//URL url = new URL(eventUrl);
	    	//HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    	//consumer.sign(request);
	    	//request.connect();    	
	
	    	logger.info("About to save!");
	
	    	
	    	// TODO: save order
	    	
	    	// read xml
	    	String type = conn.getContentType();
	    	String message = conn.getResponseMessage();
	    	
	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + message);
	    	
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
	    	
	    	result = new Result(true, "Account creation successful", "fake124");
	    	
       	}
       	else {
       		result = new Result(false, EventErrorCode.UNAUTHORIZED, "");
       	}
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
     * <p>Cancel event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/buy'.</p>
     */
    @RequestMapping(value="/event/subscriptionCancel", method=RequestMethod.GET)
	public @ResponseBody Result subscriptionCancel(@RequestParam("eventUrl") String eventUrl) throws Exception  {
		
    	// log everything!
    	logger.info("************** Entering method");
    	 
    	// TODO: verify encryption
    	
    	// callback
    	//OAuthConsumer consumer = new DefaultOAuthConsumer("bijoux-8197", "RHDwlCp4EhN6Mtmm");
    	
    	logger.info("************** opening url:" + eventUrl);
    	//URL url = new URL(eventUrl);
    	//HttpURLConnection request = (HttpURLConnection) url.openConnection();
    	//consumer.sign(request);
    	//request.connect();    	
    	
       	URL url = new URL(eventUrl);
       	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
       	OAuthSingleConsumerURLConnectionAuthHandler handler = new OAuthSingleConsumerURLConnectionAuthHandler("bijoux-8197", "RHDwlCp4EhN6Mtmm");
       	handler.endorseRequest(conn);
       	conn.connect();    	

    	logger.info("About to save!");

    	
    	// TODO: cancel order
    	
    	// read xml
    	String type = conn.getContentType();
    	
    	logger.info("Content type is " + type);
    	
    	Result result = new Result(true, "Account Cancellation successful", "fake124");
    
    	return result;
    }    
}