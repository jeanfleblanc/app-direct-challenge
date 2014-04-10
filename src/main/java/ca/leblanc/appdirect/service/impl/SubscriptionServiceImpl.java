package ca.leblanc.appdirect.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.event.Order;
import ca.leblanc.appdirect.domain.event.User;
import ca.leblanc.appdirect.domain.exception.SubscriptionNotFoundException;
import ca.leblanc.appdirect.domain.exception.UserAlreadyExistException;
import ca.leblanc.appdirect.domain.exception.UserNotExistException;
import ca.leblanc.appdirect.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final Map<String, Subscription> subscriptions = new HashMap<String, Subscription>();

	@Override
	public String generateAccountId() {
		
		return UUID.randomUUID().toString();
	}
   
	@Override
    public void saveSubscription(String accountId, Subscription subscription) {
    	
    	subscriptions.put(accountId, subscription);
    }    
    
	@Override
	public void updateOrder(String accountId, Order order) throws SubscriptionNotFoundException {
	
		Subscription subscription = loadSubscriptionByAccountId(accountId);
		
		subscription.setOrder(order);
	}
	
	@Override
	public void updateStatus(String accountId, String status) throws SubscriptionNotFoundException {
		
		// Verify that the user has a subscription
		Subscription subscription = loadSubscriptionByAccountId(accountId);
		
		subscription.setStatus(status);
	}

	@Override
	public void cancelSubscription(String accountId) throws SubscriptionNotFoundException {
		
		// Verify that the user has a subscription
		loadSubscriptionByAccountId(accountId);
		
		subscriptions.remove(accountId);
	}

	@Override
	public void addUser(String accountId, User user) throws SubscriptionNotFoundException, UserAlreadyExistException {
		
		Subscription subscription = loadSubscriptionByAccountId(accountId);
		
		List<User> users = subscription.getUsers();
		
		boolean found = false;
		int i = 0;
		
		while (!found && i++ < users.size()) {
			
			found = users.get(i).getOpenId().equals(user.getOpenId());
		}
		
		if (!found) {
		
			users.add(user);
		} else {
			
			throw new UserAlreadyExistException(user.getOpenId());
		}
	}

	@Override
	public void removeUser(String accountId, User user)
			throws SubscriptionNotFoundException, UserNotExistException {
		
		Subscription subscription = loadSubscriptionByAccountId(accountId);
		
		List<User> users = subscription.getUsers();
		
		boolean found = false;
		int i = 0;
		
		while (!found && i++ < users.size()) {
			
			found = users.get(i).getOpenId().equals(user.getOpenId());
		}
		
		if (found) {
		
			users.remove(i);
		} else {
			
			throw new UserNotExistException(user.getOpenId());
		}
		
	}

	@Override
    public Subscription loadSubscriptionByAccountId(String accountId) throws SubscriptionNotFoundException {
    	Subscription subscription = subscriptions.get(accountId);

        if (subscription == null) {
            throw new SubscriptionNotFoundException(accountId);
        }

        return subscription;
    }
}
