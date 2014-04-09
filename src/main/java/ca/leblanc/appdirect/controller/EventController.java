package ca.leblanc.appdirect.controller;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.miniauth.common.IncomingRequest;
import org.miniauth.credential.AccessCredential;
import org.miniauth.exception.InvalidInputException;
import org.miniauth.exception.ValidationException;
import org.miniauth.oauth.common.OAuthIncomingRequestBuilder;
import org.miniauth.oauth.common.OAuthParamMap;
import org.miniauth.oauth.core.OAuthConstants;
import org.miniauth.oauth.credential.OAuthAccessCredential;
import org.miniauth.oauth.credential.mapper.OAuthLocalTokenCredentialMapper;
import org.miniauth.oauth.credential.mapper.OAuthSingleConsumerCredentialMapper;
import org.miniauth.oauth.service.OAuthRequestVerifier;
import org.miniauth.oauth.service.OAuthVerifierService;
import org.miniauth.oauth.signature.OAuthSignatureVerifier;
import org.miniauth.oauth.util.OAuthSignatureUtil;
import org.miniauth.service.RequestVerifier;
import org.miniauth.signature.SignatureVerifier;
import org.miniauth.web.oauth.OAuthProviderAuthHandler;
import org.miniauth.web.oauth.OAuthSingleConsumerURLConnectionAuthHandler;
import org.miniauth.web.oauth.util.OAuthServletRequestUtil;
import org.miniauth.web.util.ServletRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.leblanc.appdirect.domain.Result;
import ca.leblanc.appdirect.domain.ErrorResult;
import ca.leblanc.appdirect.domain.ErrorResult;
import ca.leblanc.appdirect.domain.SuccessResult;

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

       	boolean validSignature;
       	String message = "";
       	
       	try {
       		
       		/**
       		Host: www.appdirect.com
       		Content-Type: application/xml
       		Authorization: OAuth realm="",
       		oauth_nonce="72250409",
       		oauth_timestamp="1294966759",
       		oauth_consumer_key="Dummy",
       		oauth_signature_method="HMAC-SHA1",
       		oauth_version="1.0",
       		oauth_signature="IBlWhOm3PuDwaSdxE/Qu4RKPtVE="
       		*/
       		
       		AccessCredential accessCredential = new OAuthAccessCredential("bijoux-8197", "RHDwlCp4EhN6Mtmm");
       		
       		logger.info( "Auth header is " + request.getHeader("Authorization"));
       		       		
            String httpMethod = request.getMethod();
            String requestUrl = null;
            URI baseURI = null;
            try {
                requestUrl = request.getRequestURL().toString();
                baseURI = new URI(requestUrl);
            } catch (Exception e) {
                // ??? This cannot happen.
                throw new InvalidInputException("Invalid requestUrl = " + requestUrl, e);
            }
       		       		
            Map<String,String> authHeaderWrong = OAuthServletRequestUtil.getAuthParams(request);
            Map<String,String> authHeader = new HashMap<String, String>();
            
            for (String key : authHeaderWrong.keySet()) {
            	
            	if (key != null) {
            	authHeader.put(key.trim(), authHeaderWrong.get(key).trim());
            	logger.info( "Auth header ket is: '" + key.trim() + "' and value is '" + authHeaderWrong.get(key).trim() + "'");
            	}
            		
            }
            
            Map<String,String[]> queryParams = ServletRequestUtil.getQueryParams(request);
            Map<String,String[]> formParams = ServletRequestUtil.getFormParams(request);
            
            SignatureVerifier signatureVerifier = new OAuthSignatureVerifier();
            
            logger.info( "Before validate param");

            logger.info("*** contains key '" + OAuthConstants.PARAM_OAUTH_SIGNATURE_METHOD + "' value: " + authHeader.get(OAuthConstants.PARAM_OAUTH_SIGNATURE_METHOD));
            logger.info("*** contains: " + authHeader.containsKey(OAuthConstants.PARAM_OAUTH_SIGNATURE_METHOD));
            
            OAuthParamMap oauthParamMap = OAuthSignatureUtil.validateOAuthParams(authHeader, true);
            
            logger.info( "After validate param");	
            
            validSignature = signatureVerifier.verify(authHeader, httpMethod, baseURI, authHeader, formParams, queryParams);
            
       		//IncomingRequest incomingRequest = new OAuthIncomingRequestBuilder().setHttpMethod(httpMethod).setBaseURI(baseURI).setAuthHeader(authHeader).setFormParams(formParams).setQueryParams(queryParams).build();     		
       		//validSignature = OAuthRequestVerifier.getInstance().verify(accessCredential, incomingRequest);       		
       		
       		//OAuthLocalTokenCredentialMapper mapper = new OAuthLocalTokenCredentialMapper("bijoux-8197", "RHDwlCp4EhN6Mtmm");
           	//OAuthProviderAuthHandler oauthProviderAuthHandler = new OAuthProviderAuthHandler(mapper);       		
       		//validSignature = oauthProviderAuthHandler.verifyRequest(request);
       	} catch (ValidationException e) {
       		logger.error("Cannot validate signature", e);
       		message = e.getMessage();
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
	    	String content = conn.getResponseMessage();
	    	
	    	
	    	logger.info("Content type is " + type);
	    	logger.info("Content message is " + content);
	    	
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
	    	
	    	result = new SuccessResult(true, SuccessResult.ACCOUNT_CREATION_SUCCESSFUL, "fake124");
	    	
       	}
       	else {
       		result = new ErrorResult(false, ErrorResult.UNAUTHORIZED, message);
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
	public @ResponseBody ErrorResult subscriptionCancel(@RequestParam("eventUrl") String eventUrl) throws Exception  {
		
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
    	
    	ErrorResult result = new ErrorResult(true, "Account Cancellation successful", "fake124");
    
    	return result;
    }    
}