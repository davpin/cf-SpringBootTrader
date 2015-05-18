package io.pivotal.common.springcloud;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.cloud.util.UriInfo;

public class HttpWebServiceConnectorCreator extends AbstractServiceConnectorCreator<UriInfo, HttpWebServiceInfo>{

	@Override
	public UriInfo create(HttpWebServiceInfo serviceInfo,
			ServiceConnectorConfig serviceConnectorConfig) {
		// TODO Auto-generated method stub
		UriInfo info = new UriInfo(serviceInfo.getScheme(), serviceInfo.getHost(), serviceInfo.getPort(), serviceInfo.getUserName(), serviceInfo.getPassword(), serviceInfo.getPath(), serviceInfo.getQuery(), null);
		return info;
	}

}
