package io.pivotal.portfolio.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Holding.
 * 
 * Holding being a set of orders related to a particular stock.
 * 
 * @author David Ferreira Pinto
 *
 */
public class Holding {

	private Integer id;
	private String symbol;
	private Integer quantity = 0;
	private BigDecimal purchaseValue = BigDecimal.ZERO;
	private BigDecimal sellValue = BigDecimal.ZERO;
	private Set<Order> orders = new HashSet<>();
	private BigDecimal currentValue = BigDecimal.ZERO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(BigDecimal purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public BigDecimal getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}

	public void addOrder(Order order) {
		// check order is not already in.
		if (orders.contains(order)) {
			// TODO: throw RuntimeException?? and do nothing;
		} else {
			orders.add(order);
			// update stats
			if (order.getOrderType().equals(OrderType.BUY)) {
				setQuantity(getQuantity() + order.getQuantity());
				setPurchaseValue(getPurchaseValue().add(order.getPrice().multiply(new BigDecimal(order.getQuantity()))));
			} else if (order.getOrderType().equals(OrderType.SELL)) {
				setQuantity(getQuantity() - order.getQuantity());
				setSellValue(getSellValue().add(order.getPrice().multiply(new BigDecimal(order.getQuantity()))));
			}
		}
	}

	public BigDecimal getSellValue() {
		return sellValue;
	}

	public void setSellValue(BigDecimal sellPrice) {
		this.sellValue = sellPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Holding [id=").append(id).append(", symbol=").append(symbol).append(", quantity=").append(quantity).append(", purchasePrice=").append(purchaseValue).append(", sellPrice=")
				.append(sellValue).append(", orders=").append(orders).append(", currentValue=").append(currentValue).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentValue == null) ? 0 : currentValue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((purchaseValue == null) ? 0 : purchaseValue.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((sellValue == null) ? 0 : sellValue.hashCode());
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
		Holding other = (Holding) obj;
		if (currentValue == null) {
			if (other.currentValue != null)
				return false;
		} else if (!currentValue.equals(other.currentValue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (purchaseValue == null) {
			if (other.purchaseValue != null)
				return false;
		} else if (!purchaseValue.equals(other.purchaseValue))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (sellValue == null) {
			if (other.sellValue != null)
				return false;
		} else if (!sellValue.equals(other.sellValue))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
}
