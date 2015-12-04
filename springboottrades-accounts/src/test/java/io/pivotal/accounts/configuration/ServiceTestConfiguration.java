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
package io.pivotal.accounts.configuration;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import io.pivotal.accounts.domain.Account;
import io.pivotal.accounts.repository.AccountRepository;
import io.pivotal.accounts.service.AccountService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 *  ServiceTestConfiguration provides test objects and mock service layer for unit tests.
 *  
 *  @author David Ferreira Pinto
 */

@Configuration
@Profile("test")
public class ServiceTestConfiguration  {
	//Holding constants
	public static Integer HOLDING_ID = 100;
	public static Integer ACCOUNT_ID = 500;
	public static BigDecimal PURCHASE_PRICE =  BigDecimal.valueOf(50000);
	public static String SYMBOL = "VMW";
	public static BigDecimal QUANTITY =  BigDecimal.valueOf(200);
	
	//Account profile constants
	public static Integer PROFILE_ID 	=  400;
	public static String USER_ID 		= "johndoe";
	public static String EMAIL 		= "anon@springsource.com";
	public static String FULL_NAME 	= "John Doe";
	public static String CC_NUMBER 	= "999999999";
	public static String ADDRESS 		= "45 Test Dr.";
	public static Integer NOT_A_VALID_PROFILE = 900;
	public static String PASSWORD = "password";
	public static String BAD_USER_ID  = "NA";
	public static String BAD_PASSWORD  = "NA";
	public static Date ACCOUNT_DATE = new Date(1329759342904l);
	//Order constants
	public static Integer ORDER_ID 	=  555;
	public static BigDecimal ORDER_PRICE = BigDecimal.valueOf(100);
	public static BigDecimal ORDER_QUANTITY = BigDecimal.valueOf(200);
	public static String ORDER_TYPE_BUY	=  "buy";
	public static String ORDER_STATUS_CLOSED	=  "closed";
	
	//Quote constants
	public static Integer QUOTE_ID = 1;
	public static String COMPANY_NAME	=  "VMware";
	public static BigDecimal HIGH	=   BigDecimal.valueOf(50.02);
	public static BigDecimal OPEN	=  BigDecimal.valueOf(40.11);
	public static BigDecimal VOLUME	= BigDecimal.valueOf(3000);
	public static BigDecimal CURRENT_PRICE	=  BigDecimal.valueOf(48.44);
	public static Integer RANDOM_QUOTES_COUNT = 5;
	
	//Account constants
	public static BigDecimal ACCOUNT_OPEN_BALANCE	=   BigDecimal.valueOf(55.02);
	public static BigDecimal ACCOUNT_BALANCE	=   new BigDecimal(40.11);
	public static Integer LOGOUT_COUNT	=  new Integer(5);
	public static Integer LOGIN_COUNT	=  new Integer(4);
	public static String AUTH_TOKEN	=  "faef8649-280d-4ba4-bdf6-574e758a04a7";
	
	
	//Portfolio Summary
	public static Integer HOLDING_COUNT	=  1;
	public static BigDecimal BASIS =   BigDecimal.valueOf(150.25);
	public static BigDecimal MARKET_VALUE =  BigDecimal.valueOf(300.10);
	
	//Market Summary
	public static BigDecimal MARKET_INDEX =  	BigDecimal.valueOf(100.25);
	public static BigDecimal MARKET_OPENING =   BigDecimal.valueOf(35.25);
	public static BigDecimal MARKET_VOLUME =   	BigDecimal.valueOf(40.45);
	
	//Holding Summary
	public static BigDecimal HOLDING_SUMMARY_GAINS =   BigDecimal.valueOf(1000.54);
	public static BigDecimal GAIN1 =   BigDecimal.valueOf(600.54);
	public static BigDecimal GAIN2 =   BigDecimal.valueOf(400.00);
	public static String SYMBOL2 = "OTHER";
	public static String PAGE_LABEL = "page";
	public static String PAGE_SIZE = "pageSize";
	public static String TOTAL_RECORDS = "totalRecords";
	public static Long RESULT_COUNT  = new Long(1);
	public static String DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date(1329759342904l));
	
	
	@Bean 
	public AccountService accountService() {
		AccountService accountService = Mockito.mock(AccountService.class);
		//when(accountService.findAccountProfile(400)).thenReturn(accountProfile());
		//when(accountService.findAccountProfile(NOT_A_VALID_PROFILE)).thenReturn(null);
		//when(accountService.updateAccountProfile(any(Accountprofile.class), any(String.class))).thenReturn(accountProfile());
		//when(accountService.saveAccountProfile(any(Accountprofile.class))).thenReturn(accountProfile());
		when(accountService.findAccount(eq(ACCOUNT_ID))).thenReturn(account());
		//when(accountService.findAccountByProfile(any(Accountprofile.class))).thenReturn(account());
		when(accountService.login(eq(USER_ID), eq(PASSWORD))).thenReturn(loginResponse());
		when(accountService.login(eq(BAD_USER_ID), eq(BAD_PASSWORD))).thenReturn(null);

		doNothing().when(accountService).logout(any(String.class));
		return accountService;
	}
	
	@Bean
	public AccountRepository accountRepository() {
		AccountRepository repo = Mockito.mock(AccountRepository.class);
		return repo;
	}
	

	@Bean 
	public static Account account() {
		Account account = new Account();
		account.setId(PROFILE_ID);
		account.setBalance(ACCOUNT_BALANCE);
		account.setOpenbalance(ACCOUNT_OPEN_BALANCE);
		account.setLogincount(LOGIN_COUNT);
		account.setLogoutcount(LOGOUT_COUNT);
		account.setCreationdate(ACCOUNT_DATE);
		account.setLastlogin(ACCOUNT_DATE);
		account.setUserid(USER_ID);
		account.setPasswd(PASSWORD);
		account.setAddress(ADDRESS);
		account.setEmail(EMAIL);
		account.setFullname(FULL_NAME);
		account.setCreditcard(CC_NUMBER);
		account.setAuthtoken(AUTH_TOKEN);
		return account;
	}
	
	public static Map<String,Object> loginResponse() {
		Map<String,Object> loginResponse = new HashMap<String, Object>();
		
		loginResponse.put("authToken", AUTH_TOKEN);
		loginResponse.put("accountid", PROFILE_ID);
		return loginResponse;
	}
}
