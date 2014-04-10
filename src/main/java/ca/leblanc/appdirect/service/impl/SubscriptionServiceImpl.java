package ca.leblanc.appdirect.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ca.leblanc.appdirect.domain.Subscription;
import ca.leblanc.appdirect.domain.exception.SubscriptionNotFoundException;
import ca.leblanc.appdirect.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final Map<String, Subscription> subscriptions = new HashMap<String, Subscription>();

    /**
     * Implementation of {@code UserDetailsService}. We only need this to satisfy the {@code RememberMeServices}
     * requirements.
     */
    public void saveSubscription(String id, Subscription subscription) {
    	
    	subscriptions.put(id, subscription);
    }    
    
    /**
     * Implementation of {@code UserDetailsService}. We only need this to satisfy the {@code RememberMeServices}
     * requirements.
     */
    public Subscription loadSubscriptionByUsername(String id) throws SubscriptionNotFoundException {
    	Subscription subscription = subscriptions.get(id);

        if (subscription == null) {
            throw new SubscriptionNotFoundException(id);
        }

        return subscription;
    }
}
