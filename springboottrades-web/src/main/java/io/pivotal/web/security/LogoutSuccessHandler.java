package io.pivotal.web.security;

import io.pivotal.web.service.UserService;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(LogoutSuccessHandler.class);
	@Autowired
	private UserService service;

	public LogoutSuccessHandler() {
		super();
	}
	// Just for setting the default target URL
	public LogoutSuccessHandler(String defaultTargetURL) {
		this.setDefaultTargetUrl(defaultTargetURL);
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		service.logout(authentication.getPrincipal().toString());
		super.onLogoutSuccess(request, response, authentication);
	}
}
