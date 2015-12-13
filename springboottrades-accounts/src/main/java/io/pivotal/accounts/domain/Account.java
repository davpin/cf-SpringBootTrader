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
/**
 * Represents the account.
 *
 * Entity object that represents a user account.
 *
 * @author David Ferreira Pinto
 *
 */
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
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "LL")
    private Date creationdate;

	@Column(name = "openbalance", precision = 14, scale = 2, nullable=false)
	@NotNull
    private BigDecimal openbalance = new BigDecimal(0);

	@Column(name = "logoutcount")
	@NotNull
    private Integer logoutcount;

	@Column(name = "balance", precision = 14, scale = 2, nullable=false)
	@NotNull
    private BigDecimal balance = new BigDecimal(0);

	@Column(name = "lastlogin")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "LL")
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
		if (openbalance != null)
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
		if (balance != null)
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
				.append(address).append(", passwd=").append("PASSWD OMMITED")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((authtoken == null) ? 0 : authtoken.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result
				+ ((creationdate == null) ? 0 : creationdate.hashCode());
		result = prime * result
				+ ((creditcard == null) ? 0 : creditcard.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastlogin == null) ? 0 : lastlogin.hashCode());
		result = prime * result
				+ ((logincount == null) ? 0 : logincount.hashCode());
		result = prime * result
				+ ((logoutcount == null) ? 0 : logoutcount.hashCode());
		result = prime * result
				+ ((openbalance == null) ? 0 : openbalance.hashCode());
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (authtoken == null) {
			if (other.authtoken != null)
				return false;
		} else if (!authtoken.equals(other.authtoken))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (creationdate == null) {
			if (other.creationdate != null)
				return false;
		} else if (!creationdate.equals(other.creationdate))
			return false;
		if (creditcard == null) {
			if (other.creditcard != null)
				return false;
		} else if (!creditcard.equals(other.creditcard))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastlogin == null) {
			if (other.lastlogin != null)
				return false;
		} else if (!lastlogin.equals(other.lastlogin))
			return false;
		if (logincount == null) {
			if (other.logincount != null)
				return false;
		} else if (!logincount.equals(other.logincount))
			return false;
		if (logoutcount == null) {
			if (other.logoutcount != null)
				return false;
		} else if (!logoutcount.equals(other.logoutcount))
			return false;
		if (openbalance == null) {
			if (other.openbalance != null)
				return false;
		} else if (!openbalance.equals(other.openbalance))
			return false;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

}
