package ca.leblanc.appdirect.domain.event;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "order")
public class Order {

	private String editionCode;
	
	@XmlElement(name="item")
	private List<CodeItem> items;
	
	public String getEditionCode() {
		return editionCode;
	}
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}
	public List<CodeItem> getCodeItems() {
		return items;
	}
	public void setCodeItems(List<CodeItem> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "Order [editionCode=" + editionCode + ", items=" + items + "]";
	}
}
