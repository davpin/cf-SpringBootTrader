package io.pivotal.common.springcloud;

import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

public class HttpWebServiceInfoCreator extends
		CloudFoundryServiceInfoCreator<HttpWebServiceInfo> {
	//TODO: do we need this constructor?
	public HttpWebServiceInfoCreator() {
		super(new Tags(), "http","https");
	}
	
	public HttpWebServiceInfoCreator(Tags tags) {
		super(tags, "http","https");
	}
	
	public HttpWebServiceInfo createServiceInfo(Map<String,Object> serviceData) {
		@SuppressWarnings("unchecked")
		Map<String,Object> credentials = (Map<String, Object>) serviceData.get("credentials");

		String id = (String) serviceData.get("name");

		String uri = getStringFromCredentials(credentials, "uri", "url");
		
		return new HttpWebServiceInfo(id, uri);
	}
}
