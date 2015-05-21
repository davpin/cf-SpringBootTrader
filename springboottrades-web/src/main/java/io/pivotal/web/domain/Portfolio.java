package io.pivotal.web.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

	private String accountId;
	private String name;
	private BigDecimal currentTotalValue;
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

	public BigDecimal getCurrentTotalValue() {
		return currentTotalValue;
	}
	public void setCurrentTotalValue(BigDecimal currentTotalValue) {
		this.currentTotalValue = currentTotalValue;
	}
	
	public void refreshTotalValue() {
		this.currentTotalValue = BigDecimal.ZERO;
		holdings.values().forEach(holding -> {
			this.currentTotalValue = this.currentTotalValue.add(holding.getCurrentValue().multiply(new BigDecimal(holding.getQuantity())));
		});
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Portfolio [accountId=").append(accountId)
				.append(", name=").append(name).append(", currentTotalValue=")
				.append(currentTotalValue).append(", holdings=")
				.append(holdings).append("]");
		return builder.toString();
	}
	
}
