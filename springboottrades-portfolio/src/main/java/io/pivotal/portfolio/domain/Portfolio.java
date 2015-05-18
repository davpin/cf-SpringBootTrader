package io.pivotal.portfolio.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

	private String accountId;
	private String name;
	private Map<String,Holding> holdings = new HashMap<>();
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String,Holding> getHoldings() {
		return holdings;
	}
	public void setHoldings(Map<String,Holding> holdings) {
		this.holdings = holdings;
	}
	
	public void addHolding(Holding holding) {
		holdings.put(holding.getSymbol(),holding);
	}
	
	public Holding getHolding(String symbol) {
		return holdings.get(symbol);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Portfolio [accountId=").append(accountId)
				.append(", name=").append(name).append(", holdings=")
				.append(holdings).append("]");
		return builder.toString();
	}
	
}
