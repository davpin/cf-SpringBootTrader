package io.pivotal.quotes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents company information.
 *  
 * {
 *	"Symbol":"NFLX",
 *	"Name":"Netflix Inc",
 *	"Exchange":"NASDAQ"
 * }
 * 
 * @author David Ferreira Pinto
 *
 */
public class CompanyInfo implements Comparable<CompanyInfo> {

	@JsonProperty("Symbol")
	private String symbol;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Exchange")
	private String exchange;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyInfo [symbol=").append(symbol).append(", name=")
				.append(name).append(", exchange=").append(exchange)
				.append("]");
		return builder.toString();
	}
	@Override
	public int compareTo(CompanyInfo o) {
		if(o == null){
			return -1;
		}
		return this.getSymbol().compareTo(o.getSymbol());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		CompanyInfo other = (CompanyInfo) obj;
		if (exchange == null) {
			if (other.exchange != null)
				return false;
		} else if (!exchange.equals(other.exchange))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
}
