package ca.leblanc.appdirect.service;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.event.Order;
import ca.leblanc.appdirect.domain.event.User;
import ca.leblanc.appdirect.domain.exception.SubscriptionNotFoundException;
import ca.leblanc.appdirect.domain.exception.UserAlreadyExistException;
import ca.leblanc.appdirect.domain.exception.UserNotExistException;

public interface SubscriptionService {

	Subscription loadSubscriptionByAccountId(String accountId) throws SubscriptionNotFoundException;

	Subscription loadSubscriptionByOpenId(String openId) throws UserNotExistException;
	
	String generateAccountId();	
	
	void saveSubscription(String accountId, Subscription subscription);

	void updateOrder(String accountId, Order order) throws SubscriptionNotFoundException;
	
	void updateStatus(String accountId, String status) throws SubscriptionNotFoundException;

	void cancelSubscription(String accountId) throws SubscriptionNotFoundException;

	void addUser(String accountId, User user) throws SubscriptionNotFoundException, UserAlreadyExistException;
	
	void removeUser(String accountId, User user) throws SubscriptionNotFoundException, UserNotExistException;
}
