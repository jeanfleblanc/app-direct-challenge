package ca.leblanc.appdirect.controller;

import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;

import net.oauth.OAuthException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.event.ErrorResult;
import ca.leblanc.appdirect.domain.event.Event;
import ca.leblanc.appdirect.domain.event.Notice;
import ca.leblanc.appdirect.domain.event.Result;
import ca.leblanc.appdirect.domain.event.SuccessResult;
import ca.leblanc.appdirect.domain.event.User;
import ca.leblanc.appdirect.domain.exception.SubscriptionNotFoundException;
import ca.leblanc.appdirect.domain.exception.UserAlreadyExistException;
import ca.leblanc.appdirect.domain.exception.UserNotExistException;
import ca.leblanc.appdirect.service.SubscriptionService;
import ca.leblanc.appdirect.util.OAuthSignature;

@RestController()
public class AppDirectEventController {

	private static final Logger logger = LoggerFactory.getLogger(AppDirectEventController.class);

	@Autowired
	private OAuthSignature oauthSignature;

	@Autowired
	private SubscriptionService subscriptionService;

	/**
	 * <p>
	 * Buy event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/buy'.
	 * </p>
	 */
	@RequestMapping(value = "/event/buy", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public Result buy(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Save order");

		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		String accountId = subscriptionService.generateAccountId();
		subscriptionService.saveSubscription(accountId, new Subscription(event.getCreator(), event.getPayload().getOrder()));

		// send success response
		return new SuccessResult(true, SuccessResult.ACCOUNT_CREATION_SUCCESSFUL, accountId);
	}

	/**
	 * <p>
	 * Cancel event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/change'.
	 * </p>
	 */
	@RequestMapping(value = "/event/change", method = RequestMethod.GET)
	public @ResponseBody Result change(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Change order");

		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		String accountId = event.getPayload().getAccount().getAccountIdentifier();
		subscriptionService.updateOrder(accountId, event.getPayload().getOrder());

		// send success response
		return new SuccessResult(true, SuccessResult.ACCOUNT_CHANGE_SUCCESSFUL);
	}

	/**
	 * <p>
	 * Cancel event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/cancel'.
	 * </p>
	 */
	@RequestMapping(value = "/event/cancel", method = RequestMethod.GET)
	public @ResponseBody Result cancel(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Cancel order");

		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		String accountId = event.getPayload().getAccount().getAccountIdentifier();
		subscriptionService.cancelSubscription(accountId);

		// send success response
		return new SuccessResult(true, SuccessResult.ACCOUNT_CANCELLATION_SUCCESSFUL);
	}

	/**
	 * <p>
	 * Status event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/status'.
	 * </p>
	 */
	@RequestMapping(value = "/event/status", method = RequestMethod.GET)
	public @ResponseBody Result status(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Add something");

		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		// don't manage upcoming invoice at this point
		if (!event.getPayload().getNotice().equals(Notice.UPCOMING_INVOICE)) {

			String accountId = event.getPayload().getAccount().getAccountIdentifier();
			subscriptionService.updateStatus(accountId, event.getPayload().getAccount().getStatus());
		}

		// send success response
		return new SuccessResult(true, SuccessResult.STATUS_NOTIFICATION_SUCCESSFUL);
	}

	/**
	 * <p>
	 * Add-on event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/addon'.
	 * </p>
	 */
	@RequestMapping(value = "/event/addon", method = RequestMethod.GET)
	public @ResponseBody Result addon(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Addon something");

		// TODO: addon order
		logger.info("Content message is " + conn.getResponseMessage());		
		
		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		String accountId = event.getPayload().getAccount().getAccountIdentifier();
		subscriptionService.updateOrder(accountId, event.getPayload().getOrder());

		// send success response
		return new SuccessResult(true, SuccessResult.ACCOUNT_ADDON_SUCCESSFUL);
	}

	/**
	 * <p>
	 * Assign user event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/assignUser'.
	 * </p>
	 */
	@RequestMapping(value = "/event/assignUser", method = RequestMethod.GET)
	public @ResponseBody Result assignUser(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Assign user");

		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		String accountId = event.getPayload().getAccount().getAccountIdentifier();
		User user = event.getPayload().getUser();
		subscriptionService.addUser(accountId, user);

		// send success response
		return new SuccessResult(true, SuccessResult.ASSIGN_USER_SUCCESSFUL);
	}

	/**
	 * <p>
	 * Unassign user event.
	 * </p>
	 * 
	 * <p>
	 * Expected HTTP GET and request '/event/unassignUser'.
	 * </p>
	 */
	@RequestMapping(value = "/event/unassignUser", method = RequestMethod.GET)
	public @ResponseBody Result unassign(HttpServletRequest request, @RequestParam("eventUrl") String eventUrl) throws Exception {

		// validate request
		oauthSignature.validate(request);

		logger.info("Opening url:" + eventUrl);

		// sign and send request
		HttpURLConnection conn = (HttpURLConnection) new URL(eventUrl).openConnection();
		oauthSignature.sign(conn);
		conn.connect();

		logger.info("Unassign user");

		// read response
		JAXBContext context = JAXBContext.newInstance(Event.class);
		Event event = (Event) context.createUnmarshaller().unmarshal(new StringReader(conn.getResponseMessage()));

		String accountId = event.getPayload().getAccount().getAccountIdentifier();
		User user = event.getPayload().getUser();
		subscriptionService.removeUser(accountId, user);

		// send success response
		return new SuccessResult(true, SuccessResult.UNASSIGN_USER_SUCCESSFUL);
	}

	@ExceptionHandler(OAuthException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public Result handleExceptions(OAuthException e) {

		// send error response
		return new ErrorResult(false, ErrorResult.UNAUTHORIZED, e.getMessage());
	}

	@ExceptionHandler(SubscriptionNotFoundException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Result handleExceptions(SubscriptionNotFoundException e) {

		// send error response
		return new ErrorResult(false, ErrorResult.ACCOUNT_NOT_FOUND, e.getId());
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Result handleExceptions(UserAlreadyExistException e) {

		// send error response
		return new ErrorResult(false, ErrorResult.USER_ALREADY_EXISTS, e.getId());
	}

	@ExceptionHandler(UserNotExistException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public Result handleExceptions(UserNotExistException e) {

		// send error response
		return new ErrorResult(false, ErrorResult.USER_NOT_FOUND, e.getId());
	}
}