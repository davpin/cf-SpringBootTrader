package io.pivotal.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents company information.
 * 
 * @author dpinto
 *
 */
public class CompanyInfo {
/*
 * {
	"Symbol":"NFLX",
	"Name":"Netflix Inc",
	"Exchange":"NASDAQ"
    }
 */
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
}
