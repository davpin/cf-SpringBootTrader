package io.pivotal.web.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {
	@JsonProperty("Status")
	private String status;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Symbol")
	private String symbol;
	@JsonProperty("LastPrice")
	private Double lastPrice = 0d;
	@JsonProperty("Change")
	private Double change = 0d;
	@JsonProperty("ChangePercent")
	private Double changePercent = 0d;
	@JsonProperty("Timestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd HH:mm:ss zzzXXX yyyy")
	private Date timestamp;
	@JsonProperty("MSDate")
	private Double mSDate;
	@JsonProperty("MarketCap")
	private Double marketCap = 0d;
	@JsonProperty("Volume")
	private Integer volume = 0;
	@JsonProperty("ChangeYTD")
	private Double changeYTD = 0d;
	@JsonProperty("ChangePercentYTD")
	private Double changePercentYTD = 0d;
	@JsonProperty("High")
	private Double high = 0d;
	@JsonProperty("Low")
	private Double low = 0d;
	@JsonProperty("Open")
	private Double open = 0d;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
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
