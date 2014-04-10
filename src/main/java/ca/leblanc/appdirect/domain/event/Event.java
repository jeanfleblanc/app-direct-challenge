package ca.leblanc.appdirect.domain.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "event")
public class Event {
	
	private String type;
	private Marketplace marketplace;
	private Creator creator;
	private Payload payload;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Marketplace getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}
	public Creator getCreator() {
		return creator;
	}
	public void setCreator(Creator creator) {
		this.creator = creator;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return "Event [type=" + type + ", marketplace=" + marketplace
				+ ", creator=" + creator + ", payload=" + payload + "]";
	}
}
