package ca.leblanc.appdirect.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;

public class OAuthSignature {
	
	private String consumerKey = "bijoux-8197";
	private String consumerSecret = "RHDwlCp4EhN6Mtmm";	

	/**
	 * Validate incoming request.
	 * 
	 * @param request the request
	 * @throws OAuthException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void validate(HttpServletRequest request) throws OAuthException, IOException, URISyntaxException {
		
		OAuthMessage oauthMessage=OAuthServlet.getMessage(request,null);

		//Construct an accessor and a consumer
		OAuthConsumer consumer=new OAuthConsumer(null, consumerKey, consumerSecret, null);
		OAuthAccessor accessor=new OAuthAccessor(consumer);

		//	Now validate. Weirdly, validator has a void return type. It throws exceptions
		//if there are problems.
		SimpleOAuthValidator validator=new SimpleOAuthValidator();

		validator.validateMessage(oauthMessage,accessor);
	}
	
	/**
	 * Sign an outgoing request.
	 * 
	 * @param conn the request
	 * @throws OAuthMessageSignerException
	 * @throws OAuthExpectationFailedException
	 * @throws OAuthCommunicationException
	 */
	public void sign(HttpURLConnection conn) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		
		// Construct a consumer
		oauth.signpost.OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);        
		
		// Sign the connection
		consumer.sign(conn);
	}
}
