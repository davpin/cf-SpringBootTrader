/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.pivotal.accounts.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Java configuration which bootstraps the web application context. Global error
 * handling is configured via
 * {@code configureHandlerExceptionResolvers(List<HandlerExceptionResolver>
 * exceptionResolvers)} enabling consistent REST exception handling across
 * Controllers.
 * 
 * 
 * @author David Ferreira Pinto
 */

@Configuration
@ComponentScan(basePackages = { "io.pivotal.accounts" })
public class WebConfig extends WebMvcConfigurationSupport {

	/**
	 * configure the message converters with the date formatter.
	 */
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		// Configure JSON support
		MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJacksonHttpMessageConverter.setSupportedMediaTypes(Arrays
				.asList(MediaType.APPLICATION_JSON));
		//mappingJacksonHttpMessageConverter.getObjectMapper().configure(
		//		Feature.WRITE_DATES_AS_TIMESTAMPS, true);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		// There is no need to set the timezone as Jackson uses GMT and not the
		// local time zone (which is exactly what you want)
		// Note: While SimpleDateFormat is not threadsafe, Jackson Marshaller's
		// StdSerializerProvider clones the configured formatter for each thread
		mappingJacksonHttpMessageConverter.getObjectMapper().setDateFormat(
				format);
		//mappingJacksonHttpMessageConverter.getObjectMapper().configure(
		//		Feature.INDENT_OUTPUT, true);
		// mappingJacksonHttpMessageConverter.getObjectMapper().getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		converters.add(mappingJacksonHttpMessageConverter);
	}
	/*
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureHandlerExceptionResolvers(
			List<HandlerExceptionResolver> exceptionResolvers) {
		ExtendedExceptionHandlerExceptionResolver customResolver = new ExtendedExceptionHandlerExceptionResolver();
		customResolver.setExceptionHandler(new GlobalExceptionHandler());
		customResolver.setMessageConverters(getMessageConverters());
		customResolver.afterPropertiesSet();
		exceptionResolvers.add(customResolver);
	}
*/
}
