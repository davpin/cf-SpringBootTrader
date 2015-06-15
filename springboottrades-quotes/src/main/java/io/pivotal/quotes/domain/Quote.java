package io.pivotal.quotes.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a point in time price and information for a company's stock.
 * 
 * @author David Ferreira Pinto
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd HH:mm:ss zzzXXX yyyy", locale="ENGLISH")
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
	
}
