package io.pivotal.common.springcloud;

import org.springframework.cloud.localconfig.LocalConfigServiceInfoCreator;


public class HttpWebServiceLocalCreator extends LocalConfigServiceInfoCreator<HttpWebServiceInfo> {

	public HttpWebServiceLocalCreator() {
		super();
	}
	@Override
	public HttpWebServiceInfo createServiceInfo(String id, String uri) {
		HttpWebServiceInfo info = new HttpWebServiceInfo(id, uri);
		return info;
	}

}
