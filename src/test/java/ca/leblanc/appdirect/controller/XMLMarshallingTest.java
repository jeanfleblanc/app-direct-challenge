package ca.leblanc.appdirect.controller;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;

import junit.framework.Assert;

import org.junit.Test;

import ca.leblanc.appdirect.domain.event.Event;

public class XMLMarshallingTest {

	@Test
	public void testBuy() throws Exception {
		
		String requestResult = "<event><type>SUBSCRIPTION_ORDER</type><marketplace><partner>ACME</partner><baseUrl>https://www.acme-marketplace.com</baseUrl></marketplace><creator><email>andysen@gmail.com</email><firstName>Andy</firstName><lastName>Sen</lastName><openId>https://www.acme-marketplace.com/openid/id/a11a7918-bb43-4429-a256-f6d729c71033</openId><language>en</language></creator><payload><company><uuid>d15bb36e-5fb5-11e0-8c3c-00262d2cda03</uuid><email>admin@example.com</email><name>Example Company</name><phoneNumber>1-415-555-1212</phoneNumber><website>www.appdirect.com</website></company><order><editionCode>BASIC</editionCode><item> <quantity>10</quantity><unit>USER</unit></item> <item><quantity>15</quantity><unit>MEGABYTE</unit></item></order></payload></event>";
		
		JAXBContext context = JAXBContext.newInstance(Event.class);
		
		Event event = (Event)context.createUnmarshaller().unmarshal(new StringReader(requestResult));
		
		Assert.assertNotNull(event);
	}
	
	@Test
	public void testChange() throws Exception {
		
		String requestResult = "<event><type>SUBSCRIPTION_CHANGE</type><marketplace><partner>ACME</partner><baseUrl>https://www.acme-marketplace.com</baseUrl></marketplace><creator><email>andysen@gmail.com</email><firstName>Andy</firstName><lastName>Sen</lastName><openId>https://www.acme-marketplace.com/openid/id/a11a7918-bb43-4429-a256-f6d729c71033</openId></creator><payload><account><accountIdentifier>MY_ACCOUNT</accountIdentifier></account><order><editionCode>BASIC</editionCode><item><quantity>10</quantity><unit>USER</unit></item><item><quantity>15</quantity><unit>MEGABYTE</unit></item></order></payload></event>";

		JAXBContext context = JAXBContext.newInstance(Event.class);
		
		Event event = (Event)context.createUnmarshaller().unmarshal(new StringReader(requestResult));
				
		Assert.assertNotNull(event);	
	}
	
	@Test
	public void testAssignUser() throws Exception {
		
		String requestResult = "<event><type>USER_ASSIGNMENT</type><marketplace><partner>ACME</partner><baseUrl>https://www.acme-marketplace.com</baseUrl></marketplace><creator><email>andy.sen@appdirect.com</email><firstName>Andy</firstName><lastName>Sen</lastName><openId>https://www.acme-marketplace.com/openid/id/078e23c3-060f-47b4-b1a3-65c3cb2a283d</openId><language>en</language></creator><payload><account><accountIdentifier>MY_ACCOUNT_IDENTIFIER</accountIdentifier><status>FREE_TRIAL</status></account><user><email>christophe.levesque@appdirect.com</email><firstName>Christophe</firstName><lastName>Levesque</lastName><openId>https://www.acme-marketplace.com/openid/id/4a76c6c4-96e1-42a0-93e0-36af5fa374e8</openId><language>fr</language><attributes><entry><key>favoriteColor</key><value>green</value></entry><entry><key>hourlyRate</key><value>40</value></entry></attributes></user></payload></event>";

		JAXBContext context = JAXBContext.newInstance(Event.class);
		
		Event event = (Event)context.createUnmarshaller().unmarshal(new StringReader(requestResult));
				
		Assert.assertNotNull(event);	
	}	
}
