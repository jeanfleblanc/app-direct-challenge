package ca.leblanc.appdirect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class IntegrationController {

    private static final String WELCOME = "welcome";
    private static final String LOGIN = "login";
    
	/**
     * <p>Welcome Page.</p>
     * 
     * <p>Expected HTTP GET and request '/welcome'.</p>
     */
    @RequestMapping(value="/welcome", method=RequestMethod.GET)
    public String welcome() {
       
        return WELCOME;
    }
    
	/**
     * <p>Welcome Page.</p>
     * 
     * <p>Expected HTTP GET and request '/login'.</p>
     */
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
       
    	/*
    	 
    	          user = users.get_current_user()
        if user:
            subscriptions = CompanySubscription.all()
            logoutUrl = users.create_logout_url("/")
            
            appUser = User.all()\
                .filter('openid =', user.federated_identity()).get()
            if appUser == None:
                appUser = User.all()\
                    .filter('email =', user.email()).get()
            if appUser:
                sub = appUser.subscription
                appUsers = User.all().filter('subscription = ', sub)
                template_values = { 'appUser' : appUser, \
                                        'companyName' : sub.name, \
                                        'edition' : sub.edition, \
                                        'appUsers' : appUsers, \
                                        'logoutUrl' : logoutUrl }
                path = os.path.join(os.path.dirname(__file__), 'index.html')
                self.response.out.write(template.render(path, template_values))
            else:
                # Send the user to AppDirect to purchase the app
                self.redirect(appDirectLink)
        else:
            # Log this user in
            self.redirect(users.create_login_url("https://www.appdirect.com/openid/id"))
    	 
    	 */
    	
        return LOGIN;
    }    
}
