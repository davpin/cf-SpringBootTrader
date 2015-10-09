package io.pivotal.portfolio.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a point in time price and information for a company's stock.
 * 
 * Used to communicate with the Quote service.
 * 
 * @author David Ferreira Pinto.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
/*
 * {
    "Name":"Apple Inc",
    "Symbol":"AAPL",
    "LastPrice":524.49,
    "Change":15.6,
    "ChangePercent":3.06549549018453,
    "Timestamp":"Wed Oct 23 13:39:19 UTC-06:00 2013",
    "MSDate": 41570.568969907,
    "MarketCap":476497591530,
    "Volume":397562,
    "ChangeYTD":532.1729,
    "ChangePercentYTD":-1.44368493773359,
    "High":52499,
    "Low":519.175,
    "Open":519.175
}
 */
	@JsonProperty("Status")
	private String status;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Symbol")
	private String symbol;
	@JsonProperty("LastPrice")
	private Double lastPrice;
	@JsonProperty("Change")
	private Double change;
	@JsonProperty("ChangePercent")
	private Double changePercent;
	@JsonProperty("Timestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd HH:mm:ss zzzXXX yyyy")
	private Date timestamp;
	@JsonProperty("MSDate")
	private Double mSDate;
	@JsonProperty("MarketCap")
	private Double marketCap;
	@JsonProperty("Volume")
	private Integer volume;
	@JsonProperty("ChangeYTD")
	private Double changeYTD;
	@JsonProperty("ChangePercentYTD")
	private Double changePercentYTD;
	@JsonProperty("High")
	private Double high;
	@JsonProperty("Low")
	private Double low;
	@JsonProperty("Open")
	private Double open;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getLastPrice() {
		return lastPrice;
	}
	
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public Double getChangePercent() {
		return changePercent;
	}
	
	public void setChangePercent(Double changePercent) {
		this.changePercent = changePercent;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Double getmSDate() {
		return mSDate;
	}
	
	public void setmSDate(Double mSDate) {
		this.mSDate = mSDate;
	}
	public Double getMarketCap() {
		return marketCap;
	}
	
	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}
	public Integer getVolume() {
		return volume;
	}
	
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Double getChangeYTD() {
		return changeYTD;
	}
	
	public void setChangeYTD(Double changeYTD) {
		this.changeYTD = changeYTD;
	}
	public Double getChangePercentYTD() {
		return changePercentYTD;
	}
	
	public void setChangePercentYTD(Double changePercentYTD) {
		this.changePercentYTD = changePercentYTD;
	}
	public Double getHigh() {
		return high;
	}
	
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getOpen() {
		return open;
	}
	
	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getChange() {
		return change;
	}
	
	public void setChange(Double change) {
		this.change = change;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote [status=").append(status).append(", name=")
				.append(name).append(", symbol=").append(symbol)
				.append(", lastPrice=").append(lastPrice).append(", change=")
				.append(change).append(", changePercent=")
				.append(changePercent).append(", timestamp=").append(timestamp)
				.append(", mSDate=").append(mSDate).append(", marketCap=")
				.append(marketCap).append(", volume=").append(volume)
				.append(", changeYTD=").append(changeYTD)
				.append(", changePercentYTD=").append(changePercentYTD)
				.append(", high=").append(high).append(", low=").append(low)
				.append(", open=").append(open).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result
				+ ((changePercent == null) ? 0 : changePercent.hashCode());
		result = prime
				* result
				+ ((changePercentYTD == null) ? 0 : changePercentYTD.hashCode());
		result = prime * result
				+ ((changeYTD == null) ? 0 : changeYTD.hashCode());
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result
				+ ((lastPrice == null) ? 0 : lastPrice.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result + ((mSDate == null) ? 0 : mSDate.hashCode());
		result = prime * result
				+ ((marketCap == null) ? 0 : marketCap.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		Quote other = (Quote) obj;
		if (change == null) {
			if (other.change != null)
				return false;
		} else if (!change.equals(other.change))
			return false;
		if (changePercent == null) {
			if (other.changePercent != null)
				return false;
		} else if (!changePercent.equals(other.changePercent))
			return false;
		if (changePercentYTD == null) {
			if (other.changePercentYTD != null)
				return false;
		} else if (!changePercentYTD.equals(other.changePercentYTD))
			return false;
		if (changeYTD == null) {
			if (other.changeYTD != null)
				return false;
		} else if (!changeYTD.equals(other.changeYTD))
			return false;
		if (high == null) {
			if (other.high != null)
				return false;
		} else if (!high.equals(other.high))
			return false;
		if (lastPrice == null) {
			if (other.lastPrice != null)
				return false;
		} else if (!lastPrice.equals(other.lastPrice))
			return false;
		if (low == null) {
			if (other.low != null)
				return false;
		} else if (!low.equals(other.low))
			return false;
		if (mSDate == null) {
			if (other.mSDate != null)
				return false;
		} else if (!mSDate.equals(other.mSDate))
			return false;
		if (marketCap == null) {
			if (other.marketCap != null)
				return false;
		} else if (!marketCap.equals(other.marketCap))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (open == null) {
			if (other.open != null)
				return false;
		} else if (!open.equals(other.open))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
	
}
