package io.pivotal.accounts.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057275461420965784L;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
	private Integer id;

	@Column(name = "address", length = 250)
    private String address;

	@Column(name = "passwd", length = 250)
    private String passwd;

	@Column(name = "userid", length = 250, unique = true)
    @NotNull
    private String userid;
	
	@Column(name = "email", length = 250)
    private String email;

	@Column(name = "creditcard", length = 250)
    private String creditcard;

	@Column(name = "fullname", length = 250)
    private String fullname;
	
	@Column(name = "authtoken", length = 100)
    private String authtoken;
    
	@Column(name = "creationdate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "M-")
    private Date creationdate;
    
	@Column(name = "openbalance", precision = 14, scale = 2)
    private BigDecimal openbalance;
    
	@Column(name = "logoutcount")
	@NotNull
    private Integer logoutcount;
    
	@Column(name = "balance", precision = 14, scale = 2)
    private BigDecimal balance;
    
	@Column(name = "lastlogin")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "M-")
    private Date lastlogin;
    
	@Column(name = "logincount")
	@NotNull
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

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=").append(id).append(", address=")
				.append(address).append(", passwd=").append(passwd)
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
