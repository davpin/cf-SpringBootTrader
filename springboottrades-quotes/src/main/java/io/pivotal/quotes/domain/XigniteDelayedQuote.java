package io.pivotal.quotes.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a point in time price and information for a company's stock based on http://globalquotes.xignite.com/v3/xGlobalQuotes.json/GetGlobalDelayedQuote
 * 
 * @author Sufyaan Kazi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XigniteDelayedQuote implements Comparable<XigniteDelayedQuote>{
	/*
	 * {"Outcome":"Success",
	 * "Message":"Delay times are 15 mins for NYSE.",
	 * "Identity":"Request",
	 * "Delay":0.1715989,
	 * "Date":"10/29/2015",
	 * "Time":"4:00:00 PM",
	 * "UTCOffset":-4.0,
	 * "Open":26.1,
	 * "Close":26.16,
	 * "High":26.29,
	 * "Low":25.965,
	 * "Last":26.16,
	 * "LastSize":321754.0,
	 * "Volume":8210341.0,
	 * "PreviousClose":26.16,
	 * "PreviousCloseDate":"10/29/2015",
	 * "ChangeFromPreviousClose":0.0,
	 * "PercentChangeFromPreviousClose":0.0,
	 * "Bid":26.1,
	 * "BidSize":700.0,
	 * "BidDate":"10/29/2015",
	 * "BidTime":"4:05:16 PM",
	 * "Ask":26.19,
	 * "AskSize":200.0,
	 * "AskDate":"10/29/2015",
	 * "AskTime":"4:05:16 PM",
	 * "High52Weeks":30.92,
	 * "Low52Weeks":22.66,
	 * "Currency":"USD",
	 * "TradingHalted":false,
	 * "Security":{"CIK":"0000790070","CUSIP":null,"Symbol":"EMC","ISIN":null,"Valoren":"926696","Name":"EMC Corp","Market":"NYSE","MarketIdentificationCode":"XNYS","MostLiquidExchange":true,"CategoryOrIndustry":"ComputerBasedSystems"}}
	 */
	@JsonProperty("Outcome")
	private String outcome=null;
	@JsonProperty("Message")
	private String message=null;
	@JsonProperty("Identity")
	private String identity=null;
	@JsonProperty("Delay")
	private Float delay=null;
	@JsonProperty("Date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy", locale="ENGLISH")
	private Date date=null;
	@JsonProperty("Time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss a", locale="ENGLISH")
	private Date time=null;
	@JsonProperty("UTCOffset")
	private Float UTCOffset=null;
	@JsonProperty("Open")
	private BigDecimal open=null;
	@JsonProperty("Close")
	private BigDecimal close=null;
	@JsonProperty("High")
	private BigDecimal high=null;
	@JsonProperty("Low")
	private BigDecimal low=null;
	@JsonProperty("Last")
	private BigDecimal last=null;
	@JsonProperty("LastSize")
	private Float lastSize=null;
	@JsonProperty("Volume")
	private Float volume=null;
	@JsonProperty("PreviousClose")
	private BigDecimal previousClose=null;
	@JsonProperty("PreviousCloseDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy", locale="ENGLISH")
	private Date previousCloseDate=null;
	@JsonProperty("ChangeFromPreviousClose")
	private BigDecimal changeFromPreviousClose=null;
	@JsonProperty("PercentChangeFromPreviousClose")
	private Float percentChangeFromPreviousClose=null;
	@JsonProperty("Bid")
	private BigDecimal bid=null;
	@JsonProperty("BidSize")
	private BigDecimal bidSize=null;
	@JsonProperty("BidDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy", locale="ENGLISH")
	private Date bidDate=null;
	@JsonProperty("BidTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss a", locale="ENGLISH")
	private Date bidTime=null;
	@JsonProperty("Ask")
	private BigDecimal ask=null;
	@JsonProperty("AskSize")
	private BigDecimal askSize=null;
	@JsonProperty("AskDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy", locale="ENGLISH")
	private Date askDate=null;
	@JsonProperty("AskTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss a", locale="ENGLISH")
	private Date askTime=null;
	@JsonProperty("High52Weeks")
	private BigDecimal high52Weeks=null;
	@JsonProperty("Low52Weeks")
	private BigDecimal low52Weeks=null;
	@JsonProperty("Currency")
	private String currency=null;
	@JsonProperty("TradingHalted")
	private Boolean tradingHalted=null;
	@JsonProperty("Security")
	private Security security=null;
	
	public XigniteDelayedQuote() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote2 [outcome=");
		builder.append(outcome);
		builder.append(", message=");
		builder.append(message);
		builder.append(", identity=");
		builder.append(identity);
		builder.append(", delay=");
		builder.append(delay);
		builder.append(", date=");
		builder.append(date);
		builder.append(", time=");
		builder.append(time);
		builder.append(", UTCOffset=");
		builder.append(UTCOffset);
		builder.append(", open=");
		builder.append(open);
		builder.append(", close=");
		builder.append(close);
		builder.append(", high=");
		builder.append(high);
		builder.append(", low=");
		builder.append(low);
		builder.append(", last=");
		builder.append(last);
		builder.append(", lastSize=");
		builder.append(lastSize);
		builder.append(", volume=");
		builder.append(volume);
		builder.append(", previousClose=");
		builder.append(previousClose);
		builder.append(", previousCloseDate=");
		builder.append(previousCloseDate);
		builder.append(", changeFromPreviousClose=");
		builder.append(changeFromPreviousClose);
		builder.append(", percentChangeFromPreviousClose=");
		builder.append(percentChangeFromPreviousClose);
		builder.append(", bid=");
		builder.append(bid);
		builder.append(", bidSize=");
		builder.append(bidSize);
		builder.append(", bidDate=");
		builder.append(bidDate);
		builder.append(", bidTime=");
		builder.append(bidTime);
		builder.append(", ask=");
		builder.append(ask);
		builder.append(", askSize=");
		builder.append(askSize);
		builder.append(", askDate=");
		builder.append(askDate);
		builder.append(", askTime=");
		builder.append(askTime);
		builder.append(", high52Weeks=");
		builder.append(high52Weeks);
		builder.append(", low52Weeks=");
		builder.append(low52Weeks);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", tradingHalted=");
		builder.append(tradingHalted);
		builder.append(", security=");
		builder.append(security);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((UTCOffset == null) ? 0 : UTCOffset.hashCode());
		result = prime * result + ((ask == null) ? 0 : ask.hashCode());
		result = prime * result + ((askDate == null) ? 0 : askDate.hashCode());
		result = prime * result + ((askSize == null) ? 0 : askSize.hashCode());
		result = prime * result + ((askTime == null) ? 0 : askTime.hashCode());
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((bidDate == null) ? 0 : bidDate.hashCode());
		result = prime * result + ((bidSize == null) ? 0 : bidSize.hashCode());
		result = prime * result + ((bidTime == null) ? 0 : bidTime.hashCode());
		result = prime
				* result
				+ ((changeFromPreviousClose == null) ? 0
						: changeFromPreviousClose.hashCode());
		result = prime * result + ((close == null) ? 0 : close.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((delay == null) ? 0 : delay.hashCode());
		result = prime * result + ((high == null) ? 0 : high.hashCode());
		result = prime * result
				+ ((high52Weeks == null) ? 0 : high52Weeks.hashCode());
		result = prime * result
				+ ((identity == null) ? 0 : identity.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result
				+ ((lastSize == null) ? 0 : lastSize.hashCode());
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result
				+ ((low52Weeks == null) ? 0 : low52Weeks.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((outcome == null) ? 0 : outcome.hashCode());
		result = prime
				* result
				+ ((percentChangeFromPreviousClose == null) ? 0
						: percentChangeFromPreviousClose.hashCode());
		result = prime * result
				+ ((previousClose == null) ? 0 : previousClose.hashCode());
		result = prime
				* result
				+ ((previousCloseDate == null) ? 0 : previousCloseDate
						.hashCode());
		result = prime * result
				+ ((security == null) ? 0 : security.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result
				+ ((tradingHalted == null) ? 0 : tradingHalted.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XigniteDelayedQuote other = (XigniteDelayedQuote) obj;
		if (UTCOffset == null) {
			if (other.UTCOffset != null)
				return false;
		} else if (!UTCOffset.equals(other.UTCOffset))
			return false;
		if (ask == null) {
			if (other.ask != null)
				return false;
		} else if (!ask.equals(other.ask))
			return false;
		if (askDate == null) {
			if (other.askDate != null)
				return false;
		} else if (!askDate.equals(other.askDate))
			return false;
		if (askSize == null) {
			if (other.askSize != null)
				return false;
		} else if (!askSize.equals(other.askSize))
			return false;
		if (askTime == null) {
			if (other.askTime != null)
				return false;
		} else if (!askTime.equals(other.askTime))
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (bidDate == null) {
			if (other.bidDate != null)
				return false;
		} else if (!bidDate.equals(other.bidDate))
			return false;
		if (bidSize == null) {
			if (other.bidSize != null)
				return false;
		} else if (!bidSize.equals(other.bidSize))
			return false;
		if (bidTime == null) {
			if (other.bidTime != null)
				return false;
		} else if (!bidTime.equals(other.bidTime))
			return false;
		if (changeFromPreviousClose == null) {
			if (other.changeFromPreviousClose != null)
				return false;
		} else if (!changeFromPreviousClose
				.equals(other.changeFromPreviousClose))
			return false;
		if (close == null) {
			if (other.close != null)
				return false;
		} else if (!close.equals(other.close))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (delay == null) {
			if (other.delay != null)
				return false;
		} else if (!delay.equals(other.delay))
			return false;
		if (high == null) {
			if (other.high != null)
				return false;
		} else if (!high.equals(other.high))
			return false;
		if (high52Weeks == null) {
			if (other.high52Weeks != null)
				return false;
		} else if (!high52Weeks.equals(other.high52Weeks))
			return false;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		if (lastSize == null) {
			if (other.lastSize != null)
				return false;
		} else if (!lastSize.equals(other.lastSize))
			return false;
		if (low == null) {
			if (other.low != null)
				return false;
		} else if (!low.equals(other.low))
			return false;
		if (low52Weeks == null) {
			if (other.low52Weeks != null)
				return false;
		} else if (!low52Weeks.equals(other.low52Weeks))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (open == null) {
			if (other.open != null)
				return false;
		} else if (!open.equals(other.open))
			return false;
		if (outcome == null) {
			if (other.outcome != null)
				return false;
		} else if (!outcome.equals(other.outcome))
			return false;
		if (percentChangeFromPreviousClose == null) {
			if (other.percentChangeFromPreviousClose != null)
				return false;
		} else if (!percentChangeFromPreviousClose
				.equals(other.percentChangeFromPreviousClose))
			return false;
		if (previousClose == null) {
			if (other.previousClose != null)
				return false;
		} else if (!previousClose.equals(other.previousClose))
			return false;
		if (previousCloseDate == null) {
			if (other.previousCloseDate != null)
				return false;
		} else if (!previousCloseDate.equals(other.previousCloseDate))
			return false;
		if (security == null) {
			if (other.security != null)
				return false;
		} else if (!security.equals(other.security))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (tradingHalted == null) {
			if (other.tradingHalted != null)
				return false;
		} else if (!tradingHalted.equals(other.tradingHalted))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the delay
	 */
	public Float getDelay() {
		return delay;
	}

	/**
	 * @param delay the delay to set
	 */
	public void setDelay(Float delay) {
		this.delay = delay;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the uTCOffset
	 */
	public Float getUTCOffset() {
		return UTCOffset;
	}

	/**
	 * @param uTCOffset the uTCOffset to set
	 */
	public void setUTCOffset(Float uTCOffset) {
		UTCOffset = uTCOffset;
	}

	/**
	 * @return the open
	 */
	public BigDecimal getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	/**
	 * @return the close
	 */
	public BigDecimal getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(BigDecimal close) {
		this.close = close;
	}

	/**
	 * @return the high
	 */
	public BigDecimal getHigh() {
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public BigDecimal getLow() {
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(BigDecimal low) {
		this.low = low;
	}

	/**
	 * @return the last
	 */
	public BigDecimal getLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(BigDecimal last) {
		this.last = last;
	}

	/**
	 * @return the lastSize
	 */
	public Float getLastSize() {
		return lastSize;
	}

	/**
	 * @param lastSize the lastSize to set
	 */
	public void setLastSize(Float lastSize) {
		this.lastSize = lastSize;
	}

	/**
	 * @return the volume
	 */
	public Float getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Float volume) {
		this.volume = volume;
	}

	/**
	 * @return the previousClose
	 */
	public BigDecimal getPreviousClose() {
		return previousClose;
	}

	/**
	 * @param previousClose the previousClose to set
	 */
	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}

	/**
	 * @return the previousCloseDate
	 */
	public Date getPreviousCloseDate() {
		return previousCloseDate;
	}

	/**
	 * @param previousCloseDate the previousCloseDate to set
	 */
	public void setPreviousCloseDate(Date previousCloseDate) {
		this.previousCloseDate = previousCloseDate;
	}

	/**
	 * @return the changeFromPreviousClose
	 */
	public BigDecimal getChangeFromPreviousClose() {
		return changeFromPreviousClose;
	}

	/**
	 * @param changeFromPreviousClose the changeFromPreviousClose to set
	 */
	public void setChangeFromPreviousClose(BigDecimal changeFromPreviousClose) {
		this.changeFromPreviousClose = changeFromPreviousClose;
	}

	/**
	 * @return the percentChangeFromPreviousClose
	 */
	public Float getPercentChangeFromPreviousClose() {
		return percentChangeFromPreviousClose;
	}

	/**
	 * @param percentChangeFromPreviousClose the percentChangeFromPreviousClose to set
	 */
	public void setPercentChangeFromPreviousClose(
			Float percentChangeFromPreviousClose) {
		this.percentChangeFromPreviousClose = percentChangeFromPreviousClose;
	}

	/**
	 * @return the bid
	 */
	public BigDecimal getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	/**
	 * @return the bidSize
	 */
	public BigDecimal getBidSize() {
		return bidSize;
	}

	/**
	 * @param bidSize the bidSize to set
	 */
	public void setBidSize(BigDecimal bidSize) {
		this.bidSize = bidSize;
	}

	/**
	 * @return the bidDate
	 */
	public Date getBidDate() {
		return bidDate;
	}

	/**
	 * @param bidDate the bidDate to set
	 */
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	/**
	 * @return the bidTime
	 */
	public Date getBidTime() {
		return bidTime;
	}

	/**
	 * @param bidTime the bidTime to set
	 */
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	/**
	 * @return the ask
	 */
	public BigDecimal getAsk() {
		return ask;
	}

	/**
	 * @param ask the ask to set
	 */
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	/**
	 * @return the askSize
	 */
	public BigDecimal getAskSize() {
		return askSize;
	}

	/**
	 * @param askSize the askSize to set
	 */
	public void setAskSize(BigDecimal askSize) {
		this.askSize = askSize;
	}

	/**
	 * @return the askDate
	 */
	public Date getAskDate() {
		return askDate;
	}

	/**
	 * @param askDate the askDate to set
	 */
	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}

	/**
	 * @return the askTime
	 */
	public Date getAskTime() {
		return askTime;
	}

	/**
	 * @param askTime the askTime to set
	 */
	public void setAskTime(Date askTime) {
		this.askTime = askTime;
	}

	/**
	 * @return the high52Weeks
	 */
	public BigDecimal getHigh52Weeks() {
		return high52Weeks;
	}

	/**
	 * @param high52Weeks the high52Weeks to set
	 */
	public void setHigh52Weeks(BigDecimal high52Weeks) {
		this.high52Weeks = high52Weeks;
	}

	/**
	 * @return the low52Weeks
	 */
	public BigDecimal getLow52Weeks() {
		return low52Weeks;
	}

	/**
	 * @param low52Weeks the low52Weeks to set
	 */
	public void setLow52Weeks(BigDecimal low52Weeks) {
		this.low52Weeks = low52Weeks;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the tradingHalted
	 */
	public Boolean getTradingHalted() {
		return tradingHalted;
	}

	/**
	 * @param tradingHalted the tradingHalted to set
	 */
	public void setTradingHalted(Boolean tradingHalted) {
		this.tradingHalted = tradingHalted;
	}

	/**
	 * @return the security
	 */
	public Security getSecurity() {
		return security;
	}

	/**
	 * @param security the security to set
	 */
	public void setSecurity(Security security) {
		this.security = security;
	}

	@Override
	public int compareTo(XigniteDelayedQuote o) {
		if(o == null || o.getSecurity() == null || this.getSecurity() == null)
		{
			return -1;
		}
		
		if(o.getSecurity().getSymbol().equalsIgnoreCase(this.getSecurity().getSymbol())){
			return this.getDate().compareTo(o.getDate()) + this.getTime().compareTo(o.getTime());
		}
		
		return this.getSecurity().getSymbol().compareTo(o.getSecurity().getSymbol());
	}
}
