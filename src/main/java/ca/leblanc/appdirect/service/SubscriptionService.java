package ca.leblanc.appdirect.service;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.exception.SubscriptionNotFoundException;

public interface SubscriptionService {

	public Subscription loadSubscriptionByUsername(String id) throws SubscriptionNotFoundException;
	
	public void saveSubscription(String id, Subscription subscription);
}
