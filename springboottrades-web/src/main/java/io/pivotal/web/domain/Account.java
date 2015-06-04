package io.pivotal.web.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("address")
    private String address;

	@JsonProperty("passwd")
    private String passwd;

	@JsonProperty("userid")
    private String userid;

	@JsonProperty("email")
    private String email;

	@JsonProperty("creditcard")
    private String creditcard;

	@JsonProperty("fullname")
    private String fullname;

	@JsonProperty("authtoken")
    private String authtoken;

	@JsonProperty("creationdate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date creationdate;
	
	@JsonProperty("openbalance")
    private BigDecimal openbalance;
    
	@JsonProperty("logoutcount")
    private Integer logoutcount;

	@JsonProperty("balance")
    private BigDecimal balance;
    
	@JsonProperty("lastlogin")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastlogin;
	
	@JsonProperty("logincount")
	private Integer logincount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public BigDecimal getOpenbalance() {
		return openbalance;
	}

	public void setOpenbalance(BigDecimal openbalance) {
		this.openbalance = openbalance;
	}

	public Integer getLogoutcount() {
		return logoutcount;
	}

	public void setLogoutcount(Integer logoutcount) {
		this.logoutcount = logoutcount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Integer getLogincount() {
		return logincount;
	}

	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=").append(id).append(", address=")
				.append(address).append(", passwd=").append("OMMITTED")
				.append(", userid=").append(userid).append(", email=")
				.append(email).append(", creditcard=").append(creditcard)
				.append(", fullname=").append(fullname).append(", authtoken=")
				.append(authtoken).append(", creationdate=")
				.append(creationdate).append(", openbalance=")
				.append(openbalance).append(", logoutcount=")
				.append(logoutcount).append(", balance=").append(balance)
				.append(", lastlogin=").append(lastlogin)
				.append(", logincount=").append(logincount).append("]");
		return builder.toString();
	}
}
