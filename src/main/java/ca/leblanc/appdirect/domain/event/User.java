package ca.leblanc.appdirect.domain.event;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "user")
public class User {
	
	private String email;
	private String firstName;
	private String lastName;
	private String openId;
	private String language;
	
	@XmlElementWrapper(name="attributes")
	@XmlElement(name="entry")
	private List<Entry> entries;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}	
	public List<Entry> getAttributes() {
		return entries;
	}
	public void setAttributes(List<Entry> attributes) {
		this.entries = attributes;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", openId=" + openId
				+ ", language=" + language + ", attributes=" + entries + "]";
	}
}
