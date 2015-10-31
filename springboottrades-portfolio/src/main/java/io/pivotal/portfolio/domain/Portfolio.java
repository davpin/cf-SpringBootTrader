package io.pivotal.portfolio.domain;

import java.math.BigDecimal;
import java.util.HashMap;
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
	private Map<String, Holding> holdings = new HashMap<>();

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

	public Map<String, Holding> getHoldings() {
		return holdings;
	}

	public void setHoldings(Map<String, Holding> holdings) {
		this.holdings = holdings;
	}

	public void addHolding(Holding holding) {
		holdings.put(holding.getSymbol(), holding);
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
		builder.append("Portfolio [accountId=").append(accountId).append(", name=").append(name).append(", currentTotalValue=").append(currentTotalValue).append(", purchaseValue=")
				.append(purchaseValue).append(", sellValue=").append(sellValue).append(", holdings=").append(holdings).append("]");
		return builder.toString();
	}

	public BigDecimal getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(BigDecimal purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((currentTotalValue == null) ? 0 : currentTotalValue.hashCode());
		result = prime * result + ((holdings == null) ? 0 : holdings.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((purchaseValue == null) ? 0 : purchaseValue.hashCode());
		result = prime * result + ((sellValue == null) ? 0 : sellValue.hashCode());
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
		Portfolio other = (Portfolio) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (currentTotalValue == null) {
			if (other.currentTotalValue != null)
				return false;
		} else if (!currentTotalValue.equals(other.currentTotalValue))
			return false;
		if (holdings == null) {
			if (other.holdings != null)
				return false;
		} else if (!holdings.equals(other.holdings))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (purchaseValue == null) {
			if (other.purchaseValue != null)
				return false;
		} else if (!purchaseValue.equals(other.purchaseValue))
			return false;
		if (sellValue == null) {
			if (other.sellValue != null)
				return false;
		} else if (!sellValue.equals(other.sellValue))
			return false;
		return true;
	}

}
