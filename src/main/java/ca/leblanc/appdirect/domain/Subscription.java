package ca.leblanc.appdirect.domain;

import java.util.ArrayList;
import java.util.List;

import ca.leblanc.appdirect.domain.event.Creator;
import ca.leblanc.appdirect.domain.event.Order;
import ca.leblanc.appdirect.domain.event.User;

public class Subscription {

	private Creator creator;
	private Order order;
	private String status;
	private List<User> users = new ArrayList<User>();
	
	public Subscription(Creator creator, Order order) {
		
		this.creator = creator;
		this.order = order;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Subscription [creator=" + creator + ", order=" + order
				+ ", status=" + status + ", users=" + users + "]";
	}
}
