package ca.leblanc.appdirect.controller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class EventController {
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	private static final String SUBSCRIPTION_ORDER = "subscriptionOrder";

	/**
     * <p>Buy event.</p>
     * 
     * <p>Expected HTTP GET and request '/event/buy'.</p>
     */
    @RequestMapping(value="/event/subscriptionOrder", method=RequestMethod.GET)
	public String subscriptionOrder(String eventUrl) throws Exception  {
		
    	// log everything!
    	logger.info("************** Entering method");
    	 
    	// verify encryption
    	
    	// callback
    	OAuthConsumer consumer = new DefaultOAuthConsumer("bijoux-8197", "RHDwlCp4EhN6Mtmm");
    	URL url = new URL(eventUrl);
    	HttpURLConnection request = (HttpURLConnection) url.openConnection();
    	consumer.sign(request);
    	request.connect();    	

    	logger.info("About to save!");

    	
    	//save order
    	
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
    	
    	/*
    	 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<result>
    <success>false</success>
    <errorCode>ACCOUNT_NOT_FOUND</errorCode>
    <message>The account TEST123 could not be found.</message>
</result>
For more information about the supported error codes, see the error code documentation.
    	 */
    	
    	return SUBSCRIPTION_ORDER;
    }
}