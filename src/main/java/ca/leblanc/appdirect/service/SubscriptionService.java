package ca.leblanc.appdirect.service;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.event.Order;
import ca.leblanc.appdirect.domain.event.User;
import ca.leblanc.appdirect.domain.exception.SubscriptionNotFoundException;
import ca.leblanc.appdirect.domain.exception.UserAlreadyExistException;
import ca.leblanc.appdirect.domain.exception.UserNotExistException;

/**
 * Manage User Subscription.
 * 
 * @author jean_francois
 */
public interface SubscriptionService {

	/**
	 * Load subscription by account id.
	 * 
	 * @param accountId the account id
	 * @return the subscription
	 * 
	 * @throws SubscriptionNotFoundException
	 */
	Subscription loadSubscriptionByAccountId(String accountId) throws SubscriptionNotFoundException;

	/**
	 * Load subscription by open id.
	 * 
	 * @param accountId the open id
	 * @return the subscription
	 * 
	 * @throws SubscriptionNotFoundException
	 */
	Subscription loadSubscriptionByOpenId(String openId) throws UserNotExistException;
	
	/**
	 * Generate Account Id.
	 * 
	 * @return the account Id
	 */
	String generateAccountId();	
	
	/**
	 * Save subscription.
	 * 
	 * @param accountId the account id
	 * @param subscription the subscription
	 */
	void saveSubscription(String accountId, Subscription subscription);

	/**
	 * Update order.
	 * 
	 * @param accountId the account id
	 * @param order the order
	 */
	void updateOrder(String accountId, Order order) throws SubscriptionNotFoundException;
	
	/**
	 * Update status.
	 * 
	 * @param accountId the account id
	 * @param status the status
	 */
	void updateStatus(String accountId, String status) throws SubscriptionNotFoundException;

	/**
	 * Cancel subscription.
	 * 
	 * @param accountId the account id
	 * @param subscription the subscription
	 */
	void cancelSubscription(String accountId) throws SubscriptionNotFoundException;

	/**
	 * Add user to a subscription.
	 * 
	 * @param accountId the account id
	 * @param user the user to add
	 * @throws SubscriptionNotFoundException
	 * @throws UserAlreadyExistException
	 */
	void addUser(String accountId, User user) throws SubscriptionNotFoundException, UserAlreadyExistException;
	
	/**
	 * Remove user from subscription.
	 * 
	 * @param accountId the account id
	 * @param user the user to add
	 * @throws SubscriptionNotFoundException
	 * @throws UserNotExistException
	 */
	void removeUser(String accountId, User user) throws SubscriptionNotFoundException, UserNotExistException;
}
