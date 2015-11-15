package io.pivotal.quotes.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YahooQuote {
	/*
	 * { "symbol": "IBM", "Ask": "140.25", "AverageDailyVolume": "4611000",
	 * "Bid": "139.34", "AskRealtime": null, "BidRealtime": null, "BookValue":
	 * "13.70", "Change_PercentChange": "+0.29 - +0.21%", "Change": "+0.29",
	 * "Commission": null, "Currency": "USD", "ChangeRealtime": null,
	 * "AfterHoursChangeRealtime": null, "DividendShare": "5.20",
	 * "LastTradeDate": "11/2/2015", "TradeDate": null, "EarningsShare":
	 * "14.37", "ErrorIndicationreturnedforsymbolchangedinvalid": null,
	 * "EPSEstimateCurrentYear": "14.93", "EPSEstimateNextYear": "15.10",
	 * "EPSEstimateNextQuarter": "2.94", "DaysLow": "139.57", "DaysHigh":
	 * "140.52", "YearLow": "137.33", "YearHigh": "176.30",
	 * "HoldingsGainPercent": null, "AnnualizedGain": null, "HoldingsGain":
	 * null, "HoldingsGainPercentRealtime": null, "HoldingsGainRealtime": null,
	 * "MoreInfo": null, "OrderBookRealtime": null, "MarketCapitalization":
	 * "136.17B", "MarketCapRealtime": null, "EBITDA": "21.41B",
	 * "ChangeFromYearLow": "3.04", "PercentChangeFromYearLow": "+2.21%",
	 * "LastTradeRealtimeWithTime": null, "ChangePercentRealtime": null,
	 * "ChangeFromYearHigh": "-35.93", "PercebtChangeFromYearHigh": "-20.38%",
	 * "LastTradeWithTime": "4:02pm - <b>140.37</b>", "LastTradePriceOnly":
	 * "140.37", "HighLimit": null, "LowLimit": null, "DaysRange":
	 * "139.57 - 140.52", "DaysRangeRealtime": null, "FiftydayMovingAverage":
	 * "145.65", "TwoHundreddayMovingAverage": "158.63",
	 * "ChangeFromTwoHundreddayMovingAverage": "-18.26",
	 * "PercentChangeFromTwoHundreddayMovingAverage": "-11.51%",
	 * "ChangeFromFiftydayMovingAverage": "-5.28",
	 * "PercentChangeFromFiftydayMovingAverage": "-3.63%", "Name":
	 * "International Business Machines", "Notes": null, "Open": "140.50",
	 * "PreviousClose": "140.08", "PricePaid": null, "ChangeinPercent":
	 * "+0.21%", "PriceSales": "1.62", "PriceBook": "10.22", "ExDividendDate":
	 * "8/6/2015", "PERatio": "9.77", "DividendPayDate": "12/10/2015",
	 * "PERatioRealtime": null, "PEGRatio": "1.29",
	 * "PriceEPSEstimateCurrentYear": "9.40", "PriceEPSEstimateNextYear":
	 * "9.30", "Symbol": "IBM", "SharesOwned": null, "ShortRatio": "6.62",
	 * "LastTradeTime": "4:02pm", "TickerTrend": null, "OneyrTargetPrice":
	 * "149.17", "Volume": "3822267", "HoldingsValue": null,
	 * "HoldingsValueRealtime": null, "YearRange": "137.33 - 176.30",
	 * "DaysValueChange": null, "DaysValueChangeRealtime": null,
	 * "StockExchange": "NYQ", "DividendYield": "3.71", "PercentChange":
	 * "+0.21%" }
	 */

	// "Symbol": "IBM",
	@JsonProperty("symbol")
	private String symbol;
	// "Ask": "140.25",
	@JsonProperty("Ask")
	private BigDecimal ask;
	// "AverageDailyVolume": "4611000"
	@JsonProperty("AverageDailyVolume")
	private Integer averageDailyVolume;
	// "Bid": "139.34",
	@JsonProperty("Bid")
	private BigDecimal bid;
	// "AskRealtime": null
	@JsonProperty("AskRealtime")
	private BigDecimal askRealtime;
	// "BidRealtime": null
	@JsonProperty("BidRealtime")
	private BigDecimal bidRealtime;
	// "BookValue": "13.70"
	@JsonProperty("BookValue")
	private BigDecimal bookValue;
	// "Change_PercentChange": "+0.29 - +0.21%"
	@JsonProperty("Change_PercentChange")
	private String change_PercentChange;
	// "Commission": null
	@JsonProperty("Commission")
	private BigDecimal commission;
	// "Currency": USD
	@JsonProperty("Currency")
	private String currency;
	// "ChangeRealtime": null
	@JsonProperty("ChangeRealTime")
	private BigDecimal changeRealTime;
	// "AfterHoursChangeRealtime": null
	@JsonProperty("AfterHoursChangeRealtime")
	private BigDecimal afterHoursChangeRealtime;
	// "DividendShare": "5.20",
	@JsonProperty("DividendShare")
	private BigDecimal dividendShare;
	// "TradeDate": null
	@JsonProperty("TradeDate")
	private String tradeDate;
	// "EarningsShare": "14.37",
	@JsonProperty("EarningsShare")
	private BigDecimal earningsShare;
	// "ErrorIndicationreturnedforsymbolchangedinvalid": null,
	@JsonProperty("ErrorIndicationreturnedforsymbolchangedinvalid")
	private String errorIndicationreturnedforsymbolchangedinvalid;
	// "EPSEstimateCurrentYear": "14.93"
	@JsonProperty("EPSEstimateCurrentYear")
	private BigDecimal ePSEstimateCurrentYear;
	// "EPSEstimateNextYear": "15.10",
	@JsonProperty("EPSEstimateNextYear")
	private BigDecimal ePSEstimateNextYear;
	// "EPSEstimateNextQuarter": "2.94"
	@JsonProperty("EPSEstimateNextQuarter")
	private BigDecimal ePSEstimateNextQuarter;
	// "DaysLow": "139.57"
	@JsonProperty("DaysLow")
	private BigDecimal daysLow;
	// "DaysHigh":"140.52"
	@JsonProperty("DaysHigh")
	private BigDecimal daysHigh;
	// "YearLow": "139.57"
	@JsonProperty("YearLow")
	private BigDecimal yearLow;
	// "YearHigh":"140.52"
	@JsonProperty("YearHigh")
	private BigDecimal yearHigh;
	// "HoldingsGainPercent": null,
	@JsonProperty("HoldingsGainPercent")
	private String holdingsGainPercent;
	// "AnnualizedGain": null,
	@JsonProperty("AnnualizedGain")
	private String annualizedGain;
	// "HoldingsGain": null,
	@JsonProperty("HoldingsGain")
	private String holdingsGain;
	// "HoldingsGainPercentRealtime": null,
	@JsonProperty("HoldingsGainPercentRealtime")
	private String holdingsGainPercentRealtime;
	// "HoldingsGainRealtime": null
	@JsonProperty("HoldingsGainRealtime")
	private String holdingsGainRealtime;
	// "MoreInfo": null,
	@JsonProperty("MoreInfo")
	private String moreInfo;
	// "OrderBookRealtime": null,
	@JsonProperty("OrderBookRealtime")
	private String orderBookRealtime;
	// "MarketCapitalization": "136.17B"
	// TODO: use JsonFormat to get into BigDecimal
	@JsonProperty("MarketCapitalization")
	private String marketCapitalization;
	// "MarketCapRealtime": null
	@JsonProperty("MarketCapRealtime")
	private String marketCapRealtime;
	// "EBITDA": "21.41B"
	// TODO: use JsonFormat to get into BigDecimal
	@JsonProperty("EBITDA")
	private String eBITDA;
	// "ChangeFromYearLow": "3.04"
	@JsonProperty("ChangeFromYearLow")
	private BigDecimal changeFromYearLow;
	// "PercentChangeFromYearLow": "+2.21%",
	@JsonProperty("PercentChangeFromYearLow")
	private String percentChangeFromYearLow;
	// "LastTradeRealtimeWithTime": null,
	@JsonProperty("LastTradeRealtimeWithTime")
	private String lastTradeRealtimeWithTime;
	// "ChangePercentRealtime": null,
	@JsonProperty("ChangePercentRealtime")
	private BigDecimal changePercentRealtime;
	// "ChangeFromYearHigh": "-35.93"
	@JsonProperty("ChangeFromYearHigh")
	private BigDecimal changeFromYearHigh;
	// "PercebtChangeFromYearHigh": "-20.38%",
	@JsonProperty("PercebtChangeFromYearHigh")
	private String percebtChangeFromYearHigh;
	// "LastTradeWithTime": "4:02pm - <b>140.37</b>",
	@JsonProperty("LastTradeWithTime")
	private String lastTradeWithTime;
	// "LastTradePriceOnly": "140.37"
	@JsonProperty("LastTradePriceOnly")
	private BigDecimal lastTradePriceOnly;
	// "HighLimit": null,
	@JsonProperty("HighLimit")
	private String highLimit;
	// "LowLimit": null,
	@JsonProperty("LowLimit")
	private String lowLimit;
	// "DaysRange": "139.57 - 140.52"
	@JsonProperty("DaysRange")
	private String daysRange;
	// "DaysRangeRealtime": null,
	@JsonProperty("DaysRangeRealtime")
	private String daysRangeRealtime;
	// "FiftydayMovingAverage": "145.65",
	@JsonProperty("FiftydayMovingAverage")
	private BigDecimal fiftydayMovingAverage;
	// "TwoHundreddayMovingAverage": "158.63",
	@JsonProperty("TwoHundreddayMovingAverage")
	private BigDecimal twoHundreddayMovingAverage;
	// "ChangeFromTwoHundreddayMovingAverage": "-18.26",
	@JsonProperty("ChangeFromTwoHundreddayMovingAverage")
	private BigDecimal changeFromTwoHundreddayMovingAverage;
	// "PercentChangeFromTwoHundreddayMovingAverage": "-11.51%",
	@JsonProperty("PercentChangeFromTwoHundreddayMovingAverage")
	private String percentChangeFromTwoHundreddayMovingAverage;
	// "ChangeFromFiftydayMovingAverage": "-5.28",
	@JsonProperty("ChangeFromFiftydayMovingAverage")
	private BigDecimal changeFromFiftydayMovingAverage;
	// "PercentChangeFromFiftydayMovingAverage": "-3.63%"
	@JsonProperty("PercentChangeFromFiftydayMovingAverage")
	private String percentChangeFromFiftydayMovingAverage;
	// "Name": "International Business Machines"
	@JsonProperty("Name")
	private String name;
	// "Notes": null,
	@JsonProperty("Notes")
	private String notes;
	// "Open": "140.50",
	@JsonProperty("Open")
	private BigDecimal open;
	// "PreviousClose": "140.08",
	@JsonProperty("PreviousClose")
	private BigDecimal previousClose;
	// "PricePaid": null,
	@JsonProperty("PricePaid")
	private BigDecimal pricePaid;
	// "ChangeinPercent": "+0.21%",
	@JsonProperty("ChangeinPercent")
	private String changeinPercent;
	// "PriceSales": "1.62",
	@JsonProperty("PriceSales")
	private BigDecimal priceSales;
	// "PriceBook": "10.22",
	@JsonProperty("PriceBook")
	private BigDecimal priceBook;
	// "ExDividendDate":"8/6/2015",
	@JsonProperty("ExDividendDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "ENGLISH")
	private Date exDividendDate;
	// "PERatio": "9.77",
	@JsonProperty("PERatio")
	private BigDecimal pERatio;
	// "DividendPayDate": "12/10/2015",
	@JsonProperty("DividendPayDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "ENGLISH")
	private Date dividendPayDate;
	// "PERatioRealtime": null,
	@JsonProperty("PERatioRealtime")
	private String pERatioRealtime;
	// "PEGRatio": "1.29",
	@JsonProperty("PEGRatio")
	private Float pEGRatio;
	// "PriceEPSEstimateCurrentYear": "9.40",
	@JsonProperty("PriceEPSEstimateCurrentYear")
	private BigDecimal priceEPSEstimateCurrentYear;
	// "PriceEPSEstimateNextYear": * "9.30",
	@JsonProperty("PriceEPSEstimateNextYear")
	private BigDecimal priceEPSEstimateNextYear;
	// "SharesOwned": null,
	@JsonProperty("SharesOwned")
	private Integer sharesOwned;
	// "ShortRatio": "6.62",
	@JsonProperty("ShortRatio")
	private Float shortRatio;
	// "LastTradeTime":"4:02pm",
	@JsonProperty("LastTradeTime")
	private String lastTradeTime;
	// * "LastTradeDate": "11/2/2015",
	@JsonProperty("LastTradeDate")
	public String lastTradeDate;
	//"TickerTrend": null, 
	@JsonProperty("TickerTrend")
	private String tickerTrend;
	//"OneyrTargetPrice": "149.17",
	@JsonProperty("OneyrTargetPrice")
	private BigDecimal oneyrTargetPrice;
	//"Volume": "3822267",
	@JsonProperty("Volume")
	private Integer volume;
	//"HoldingsValue": null, 
	@JsonProperty("HoldingsValue")
	private Integer holdingsValue;
	//"HoldingsValueRealtime": null, 
	@JsonProperty("HoldingsValueRealtime")
	private  Integer holdingsValueRealtime;
	//"YearRange": "137.33 - 176.30",
	@JsonProperty("YearRange")
	private String yearRange;
	//"DaysValueChange": null, 
	@JsonProperty("DaysValueChange")
	private Integer daysValueChange;
	//"DaysValueChangeRealtime": null,
	@JsonProperty("DaysValueChangeRealtime")
	private Integer daysValueChangeRealtime;
	//"StockExchange": "NYQ", 
	@JsonProperty("StockExchange")
	private String stockExchange;
	//"DividendYield": "3.71",
	@JsonProperty("DividendYield")
	private Float dividendYield;
	//"PercentChange":"+0.21%"
	@JsonProperty("PercentChange")
	private String percentChange;
	// "Change": "+0.29",
	@JsonProperty("Change")
	public BigDecimal change;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getAsk() {
		return ask;
	}
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}
	public Integer getAverageDailyVolume() {
		return averageDailyVolume;
	}
	public void setAverageDailyVolume(Integer averageDailyVolume) {
		this.averageDailyVolume = averageDailyVolume;
	}
	public BigDecimal getBid() {
		return bid;
	}
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	public BigDecimal getAskRealtime() {
		return askRealtime;
	}
	public void setAskRealtime(BigDecimal askRealtime) {
		this.askRealtime = askRealtime;
	}
	public BigDecimal getBidRealtime() {
		return bidRealtime;
	}
	public void setBidRealtime(BigDecimal bidRealtime) {
		this.bidRealtime = bidRealtime;
	}
	public BigDecimal getBookValue() {
		return bookValue;
	}
	public void setBookValue(BigDecimal bookValue) {
		this.bookValue = bookValue;
	}
	public String getChange_PercentChange() {
		return change_PercentChange;
	}
	public void setChange_PercentChange(String change_PercentChange) {
		this.change_PercentChange = change_PercentChange;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getChangeRealTime() {
		return changeRealTime;
	}
	public void setChangeRealTime(BigDecimal changeRealTime) {
		this.changeRealTime = changeRealTime;
	}
	public BigDecimal getAfterHoursChangeRealtime() {
		return afterHoursChangeRealtime;
	}
	public void setAfterHoursChangeRealtime(BigDecimal afterHoursChangeRealtime) {
		this.afterHoursChangeRealtime = afterHoursChangeRealtime;
	}
	public BigDecimal getDividendShare() {
		return dividendShare;
	}
	public void setDividendShare(BigDecimal dividendShare) {
		this.dividendShare = dividendShare;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getEarningsShare() {
		return earningsShare;
	}
	public void setEarningsShare(BigDecimal earningsShare) {
		this.earningsShare = earningsShare;
	}
	public String getErrorIndicationreturnedforsymbolchangedinvalid() {
		return errorIndicationreturnedforsymbolchangedinvalid;
	}
	public void setErrorIndicationreturnedforsymbolchangedinvalid(
			String errorIndicationreturnedforsymbolchangedinvalid) {
		this.errorIndicationreturnedforsymbolchangedinvalid = errorIndicationreturnedforsymbolchangedinvalid;
	}
	public BigDecimal getePSEstimateCurrentYear() {
		return ePSEstimateCurrentYear;
	}
	public void setePSEstimateCurrentYear(BigDecimal ePSEstimateCurrentYear) {
		this.ePSEstimateCurrentYear = ePSEstimateCurrentYear;
	}
	public BigDecimal getePSEstimateNextYear() {
		return ePSEstimateNextYear;
	}
	public void setePSEstimateNextYear(BigDecimal ePSEstimateNextYear) {
		this.ePSEstimateNextYear = ePSEstimateNextYear;
	}
	public BigDecimal getePSEstimateNextQuarter() {
		return ePSEstimateNextQuarter;
	}
	public void setePSEstimateNextQuarter(BigDecimal ePSEstimateNextQuarter) {
		this.ePSEstimateNextQuarter = ePSEstimateNextQuarter;
	}
	public BigDecimal getDaysLow() {
		return daysLow;
	}
	public void setDaysLow(BigDecimal daysLow) {
		this.daysLow = daysLow;
	}
	public BigDecimal getDaysHigh() {
		return daysHigh;
	}
	public void setDaysHigh(BigDecimal daysHigh) {
		this.daysHigh = daysHigh;
	}
	public BigDecimal getYearLow() {
		return yearLow;
	}
	public void setYearLow(BigDecimal yearLow) {
		this.yearLow = yearLow;
	}
	public BigDecimal getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(BigDecimal yearHigh) {
		this.yearHigh = yearHigh;
	}
	public String getHoldingsGainPercent() {
		return holdingsGainPercent;
	}
	public void setHoldingsGainPercent(String holdingsGainPercent) {
		this.holdingsGainPercent = holdingsGainPercent;
	}
	public String getAnnualizedGain() {
		return annualizedGain;
	}
	public void setAnnualizedGain(String annualizedGain) {
		this.annualizedGain = annualizedGain;
	}
	public String getHoldingsGain() {
		return holdingsGain;
	}
	public void setHoldingsGain(String holdingsGain) {
		this.holdingsGain = holdingsGain;
	}
	public String getHoldingsGainPercentRealtime() {
		return holdingsGainPercentRealtime;
	}
	public void setHoldingsGainPercentRealtime(String holdingsGainPercentRealtime) {
		this.holdingsGainPercentRealtime = holdingsGainPercentRealtime;
	}
	public String getHoldingsGainRealtime() {
		return holdingsGainRealtime;
	}
	public void setHoldingsGainRealtime(String holdingsGainRealtime) {
		this.holdingsGainRealtime = holdingsGainRealtime;
	}
	public String getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	public String getOrderBookRealtime() {
		return orderBookRealtime;
	}
	public void setOrderBookRealtime(String orderBookRealtime) {
		this.orderBookRealtime = orderBookRealtime;
	}
	public String getMarketCapitalization() {
		return marketCapitalization;
	}
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}
	public String getMarketCapRealtime() {
		return marketCapRealtime;
	}
	public void setMarketCapRealtime(String marketCapRealtime) {
		this.marketCapRealtime = marketCapRealtime;
	}
	public String geteBITDA() {
		return eBITDA;
	}
	public void seteBITDA(String eBITDA) {
		this.eBITDA = eBITDA;
	}
	public BigDecimal getChangeFromYearLow() {
		return changeFromYearLow;
	}
	public void setChangeFromYearLow(BigDecimal changeFromYearLow) {
		this.changeFromYearLow = changeFromYearLow;
	}
	public String getPercentChangeFromYearLow() {
		return percentChangeFromYearLow;
	}
	public void setPercentChangeFromYearLow(String percentChangeFromYearLow) {
		this.percentChangeFromYearLow = percentChangeFromYearLow;
	}
	public String getLastTradeRealtimeWithTime() {
		return lastTradeRealtimeWithTime;
	}
	public void setLastTradeRealtimeWithTime(String lastTradeRealtimeWithTime) {
		this.lastTradeRealtimeWithTime = lastTradeRealtimeWithTime;
	}
	public BigDecimal getChangePercentRealtime() {
		return changePercentRealtime;
	}
	public void setChangePercentRealtime(BigDecimal changePercentRealtime) {
		this.changePercentRealtime = changePercentRealtime;
	}
	public BigDecimal getChangeFromYearHigh() {
		return changeFromYearHigh;
	}
	public void setChangeFromYearHigh(BigDecimal changeFromYearHigh) {
		this.changeFromYearHigh = changeFromYearHigh;
	}
	public String getPercebtChangeFromYearHigh() {
		return percebtChangeFromYearHigh;
	}
	public void setPercebtChangeFromYearHigh(String percebtChangeFromYearHigh) {
		this.percebtChangeFromYearHigh = percebtChangeFromYearHigh;
	}
	public String getLastTradeWithTime() {
		return lastTradeWithTime;
	}
	public void setLastTradeWithTime(String lastTradeWithTime) {
		this.lastTradeWithTime = lastTradeWithTime;
	}
	public BigDecimal getLastTradePriceOnly() {
		return lastTradePriceOnly;
	}
	public void setLastTradePriceOnly(BigDecimal lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}
	public String getHighLimit() {
		return highLimit;
	}
	public void setHighLimit(String highLimit) {
		this.highLimit = highLimit;
	}
	public String getLowLimit() {
		return lowLimit;
	}
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}
	public String getDaysRange() {
		return daysRange;
	}
	public void setDaysRange(String daysRange) {
		this.daysRange = daysRange;
	}
	public String getDaysRangeRealtime() {
		return daysRangeRealtime;
	}
	public void setDaysRangeRealtime(String daysRangeRealtime) {
		this.daysRangeRealtime = daysRangeRealtime;
	}
	public BigDecimal getFiftydayMovingAverage() {
		return fiftydayMovingAverage;
	}
	public void setFiftydayMovingAverage(BigDecimal fiftydayMovingAverage) {
		this.fiftydayMovingAverage = fiftydayMovingAverage;
	}
	public BigDecimal getTwoHundreddayMovingAverage() {
		return twoHundreddayMovingAverage;
	}
	public void setTwoHundreddayMovingAverage(BigDecimal twoHundreddayMovingAverage) {
		this.twoHundreddayMovingAverage = twoHundreddayMovingAverage;
	}
	public BigDecimal getChangeFromTwoHundreddayMovingAverage() {
		return changeFromTwoHundreddayMovingAverage;
	}
	public void setChangeFromTwoHundreddayMovingAverage(
			BigDecimal changeFromTwoHundreddayMovingAverage) {
		this.changeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
	}
	public String getPercentChangeFromTwoHundreddayMovingAverage() {
		return percentChangeFromTwoHundreddayMovingAverage;
	}
	public void setPercentChangeFromTwoHundreddayMovingAverage(
			String percentChangeFromTwoHundreddayMovingAverage) {
		this.percentChangeFromTwoHundreddayMovingAverage = percentChangeFromTwoHundreddayMovingAverage;
	}
	public BigDecimal getChangeFromFiftydayMovingAverage() {
		return changeFromFiftydayMovingAverage;
	}
	public void setChangeFromFiftydayMovingAverage(
			BigDecimal changeFromFiftydayMovingAverage) {
		this.changeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
	}
	public String getPercentChangeFromFiftydayMovingAverage() {
		return percentChangeFromFiftydayMovingAverage;
	}
	public void setPercentChangeFromFiftydayMovingAverage(
			String percentChangeFromFiftydayMovingAverage) {
		this.percentChangeFromFiftydayMovingAverage = percentChangeFromFiftydayMovingAverage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}
	public BigDecimal getPricePaid() {
		return pricePaid;
	}
	public void setPricePaid(BigDecimal pricePaid) {
		this.pricePaid = pricePaid;
	}
	public String getChangeinPercent() {
		return changeinPercent;
	}
	public void setChangeinPercent(String changeinPercent) {
		this.changeinPercent = changeinPercent;
	}
	public BigDecimal getPriceSales() {
		return priceSales;
	}
	public void setPriceSales(BigDecimal priceSales) {
		this.priceSales = priceSales;
	}
	public BigDecimal getPriceBook() {
		return priceBook;
	}
	public void setPriceBook(BigDecimal priceBook) {
		this.priceBook = priceBook;
	}
	public Date getExDividendDate() {
		return exDividendDate;
	}
	public void setExDividendDate(Date exDividendDate) {
		this.exDividendDate = exDividendDate;
	}
	public BigDecimal getpERatio() {
		return pERatio;
	}
	public void setpERatio(BigDecimal pERatio) {
		this.pERatio = pERatio;
	}
	public Date getDividendPayDate() {
		return dividendPayDate;
	}
	public void setDividendPayDate(Date dividendPayDate) {
		this.dividendPayDate = dividendPayDate;
	}
	public String getpERatioRealtime() {
		return pERatioRealtime;
	}
	public void setpERatioRealtime(String pERatioRealtime) {
		this.pERatioRealtime = pERatioRealtime;
	}
	public Float getpEGRatio() {
		return pEGRatio;
	}
	public void setpEGRatio(Float pEGRatio) {
		this.pEGRatio = pEGRatio;
	}
	public BigDecimal getPriceEPSEstimateCurrentYear() {
		return priceEPSEstimateCurrentYear;
	}
	public void setPriceEPSEstimateCurrentYear(
			BigDecimal priceEPSEstimateCurrentYear) {
		this.priceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
	}
	public BigDecimal getPriceEPSEstimateNextYear() {
		return priceEPSEstimateNextYear;
	}
	public void setPriceEPSEstimateNextYear(BigDecimal priceEPSEstimateNextYear) {
		this.priceEPSEstimateNextYear = priceEPSEstimateNextYear;
	}
	public Integer getSharesOwned() {
		return sharesOwned;
	}
	public void setSharesOwned(Integer sharesOwned) {
		this.sharesOwned = sharesOwned;
	}
	public Float getShortRatio() {
		return shortRatio;
	}
	public void setShortRatio(Float shortRatio) {
		this.shortRatio = shortRatio;
	}
	public String getLastTradeTime() {
		return lastTradeTime;
	}
	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}
	public String getLastTradeDate() {
		return lastTradeDate;
	}
	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	public String getTickerTrend() {
		return tickerTrend;
	}
	public void setTickerTrend(String tickerTrend) {
		this.tickerTrend = tickerTrend;
	}
	public BigDecimal getOneyrTargetPrice() {
		return oneyrTargetPrice;
	}
	public void setOneyrTargetPrice(BigDecimal oneyrTargetPrice) {
		this.oneyrTargetPrice = oneyrTargetPrice;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public Integer getHoldingsValue() {
		return holdingsValue;
	}
	public void setHoldingsValue(Integer holdingsValue) {
		this.holdingsValue = holdingsValue;
	}
	public Integer getHoldingsValueRealtime() {
		return holdingsValueRealtime;
	}
	public void setHoldingsValueRealtime(Integer holdingsValueRealtime) {
		this.holdingsValueRealtime = holdingsValueRealtime;
	}
	public String getYearRange() {
		return yearRange;
	}
	public void setYearRange(String yearRange) {
		this.yearRange = yearRange;
	}
	public Integer getDaysValueChange() {
		return daysValueChange;
	}
	public void setDaysValueChange(Integer daysValueChange) {
		this.daysValueChange = daysValueChange;
	}
	public Integer getDaysValueChangeRealtime() {
		return daysValueChangeRealtime;
	}
	public void setDaysValueChangeRealtime(Integer daysValueChangeRealtime) {
		this.daysValueChangeRealtime = daysValueChangeRealtime;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public Float getDividendYield() {
		return dividendYield;
	}
	public void setDividendYield(Float dividendYield) {
		this.dividendYield = dividendYield;
	}
	public String getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	public BigDecimal getChange() {
		return change;
	}
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YahooQuote [symbol=").append(symbol).append(", ask=")
				.append(ask).append(", averageDailyVolume=")
				.append(averageDailyVolume).append(", bid=").append(bid)
				.append(", askRealtime=").append(askRealtime)
				.append(", bidRealtime=").append(bidRealtime)
				.append(", bookValue=").append(bookValue)
				.append(", change_PercentChange=").append(change_PercentChange)
				.append(", commission=").append(commission)
				.append(", currency=").append(currency)
				.append(", changeRealTime=").append(changeRealTime)
				.append(", afterHoursChangeRealtime=")
				.append(afterHoursChangeRealtime).append(", dividendShare=")
				.append(dividendShare).append(", tradeDate=").append(tradeDate)
				.append(", earningsShare=").append(earningsShare)
				.append(", errorIndicationreturnedforsymbolchangedinvalid=")
				.append(errorIndicationreturnedforsymbolchangedinvalid)
				.append(", ePSEstimateCurrentYear=")
				.append(ePSEstimateCurrentYear)
				.append(", ePSEstimateNextYear=").append(ePSEstimateNextYear)
				.append(", ePSEstimateNextQuarter=")
				.append(ePSEstimateNextQuarter).append(", daysLow=")
				.append(daysLow).append(", daysHigh=").append(daysHigh)
				.append(", yearLow=").append(yearLow).append(", yearHigh=")
				.append(yearHigh).append(", holdingsGainPercent=")
				.append(holdingsGainPercent).append(", annualizedGain=")
				.append(annualizedGain).append(", holdingsGain=")
				.append(holdingsGain).append(", holdingsGainPercentRealtime=")
				.append(holdingsGainPercentRealtime)
				.append(", holdingsGainRealtime=").append(holdingsGainRealtime)
				.append(", moreInfo=").append(moreInfo)
				.append(", orderBookRealtime=").append(orderBookRealtime)
				.append(", marketCapitalization=").append(marketCapitalization)
				.append(", marketCapRealtime=").append(marketCapRealtime)
				.append(", eBITDA=").append(eBITDA)
				.append(", changeFromYearLow=").append(changeFromYearLow)
				.append(", percentChangeFromYearLow=")
				.append(percentChangeFromYearLow)
				.append(", lastTradeRealtimeWithTime=")
				.append(lastTradeRealtimeWithTime)
				.append(", changePercentRealtime=")
				.append(changePercentRealtime).append(", changeFromYearHigh=")
				.append(changeFromYearHigh)
				.append(", percebtChangeFromYearHigh=")
				.append(percebtChangeFromYearHigh)
				.append(", lastTradeWithTime=").append(lastTradeWithTime)
				.append(", lastTradePriceOnly=").append(lastTradePriceOnly)
				.append(", highLimit=").append(highLimit).append(", lowLimit=")
				.append(lowLimit).append(", daysRange=").append(daysRange)
				.append(", daysRangeRealtime=").append(daysRangeRealtime)
				.append(", fiftydayMovingAverage=")
				.append(fiftydayMovingAverage)
				.append(", twoHundreddayMovingAverage=")
				.append(twoHundreddayMovingAverage)
				.append(", changeFromTwoHundreddayMovingAverage=")
				.append(changeFromTwoHundreddayMovingAverage)
				.append(", percentChangeFromTwoHundreddayMovingAverage=")
				.append(percentChangeFromTwoHundreddayMovingAverage)
				.append(", changeFromFiftydayMovingAverage=")
				.append(changeFromFiftydayMovingAverage)
				.append(", percentChangeFromFiftydayMovingAverage=")
				.append(percentChangeFromFiftydayMovingAverage)
				.append(", name=").append(name).append(", notes=")
				.append(notes).append(", open=").append(open)
				.append(", previousClose=").append(previousClose)
				.append(", pricePaid=").append(pricePaid)
				.append(", changeinPercent=").append(changeinPercent)
				.append(", priceSales=").append(priceSales)
				.append(", priceBook=").append(priceBook)
				.append(", exDividendDate=").append(exDividendDate)
				.append(", pERatio=").append(pERatio)
				.append(", dividendPayDate=").append(dividendPayDate)
				.append(", pERatioRealtime=").append(pERatioRealtime)
				.append(", pEGRatio=").append(pEGRatio)
				.append(", priceEPSEstimateCurrentYear=")
				.append(priceEPSEstimateCurrentYear)
				.append(", priceEPSEstimateNextYear=")
				.append(priceEPSEstimateNextYear).append(", sharesOwned=")
				.append(sharesOwned).append(", shortRatio=").append(shortRatio)
				.append(", lastTradeTime=").append(lastTradeTime)
				.append(", lastTradeDate=").append(lastTradeDate)
				.append(", tickerTrend=").append(tickerTrend)
				.append(", oneyrTargetPrice=").append(oneyrTargetPrice)
				.append(", volume=").append(volume).append(", holdingsValue=")
				.append(holdingsValue).append(", holdingsValueRealtime=")
				.append(holdingsValueRealtime).append(", yearRange=")
				.append(yearRange).append(", daysValueChange=")
				.append(daysValueChange).append(", daysValueChangeRealtime=")
				.append(daysValueChangeRealtime).append(", stockExchange=")
				.append(stockExchange).append(", dividendYield=")
				.append(dividendYield).append(", percentChange=")
				.append(percentChange).append(", change=").append(change)
				.append("]");
		return builder.toString();
	}

}