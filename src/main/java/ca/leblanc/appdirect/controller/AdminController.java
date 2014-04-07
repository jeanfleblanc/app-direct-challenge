package ca.leblanc.appdirect.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class AdminController {

    private static final String LIST = "list";
    
    @Autowired
    private UserDetailsService service;
    
	/**
     * <p>List Page.</p>
     * 
     * <p>Expected HTTP GET and request '/list'.</p>
     */
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    		UserDetails user = service.loadUserByUsername(request.getRemoteUser());
    	
    		request.setAttribute("user", user);
    	
            return LIST;
    }
    	
}
