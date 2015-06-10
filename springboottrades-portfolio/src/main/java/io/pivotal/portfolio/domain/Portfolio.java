package io.pivotal.portfolio.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Represents Portfolio object.
 * 
 * Portfolios are a collection of holdings.
 * 
 * @author David Ferreira Pinto
 *
 */
public class Portfolio {

	private String accountId;
	private String name;
	private BigDecimal currentTotalValue = BigDecimal.ZERO;
	private BigDecimal purchaseValue = BigDecimal.ZERO;
	private BigDecimal sellValue = BigDecimal.ZERO;
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
	
	/**
	 * Iterates through each of the holdings aggregating the values.
	 */
	public void refreshTotalValue() {
		this.currentTotalValue = BigDecimal.ZERO;
		this.purchaseValue = BigDecimal.ZERO;
		this.sellValue = BigDecimal.ZERO;
		holdings.values().forEach(holding -> {
			this.currentTotalValue = this.currentTotalValue.add(holding.getCurrentValue().multiply(new BigDecimal(holding.getQuantity())));
			this.purchaseValue = this.purchaseValue.add(holding.getPurchaseValue());
			this.sellValue = this.sellValue.add(holding.getSellValue());
		});
	}
	
	public BigDecimal getSellValue() {
		return sellValue;
	}
	public void setSellValue(BigDecimal sellValue) {
		this.sellValue = sellValue;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Portfolio [accountId=").append(accountId)
				.append(", name=").append(name).append(", currentTotalValue=")
				.append(currentTotalValue).append(", purchaseValue=")
				.append(purchaseValue).append(", sellValue=").append(sellValue)
				.append(", holdings=").append(holdings).append("]");
		return builder.toString();
	}
	public BigDecimal getPurchaseValue() {
		return purchaseValue;
	}
	public void setPurchaseValue(BigDecimal purchaseValue) {
		this.purchaseValue = purchaseValue;
	}
	
}
