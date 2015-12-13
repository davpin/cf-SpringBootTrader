/**
 * 
 */
package io.pivotal.quotes.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Singleton Class to map between quote objects as returned by different public quotes URLS
 * 
 * @author Sufyaan Kazi
 */
public class QuoteMapper {
	public static QuoteMapper INSTANCE = new QuoteMapper();

	private QuoteMapper(){
		super();
	}
	
	/**
	 * Maps between a Xignite Delayed Quote to Markit Quote
	 * 
	 * @param xigQuote
	 * @return new quote
	 * @author skazi
	 */
	public Quote mapFromXigniteQuote(XigniteDelayedQuote xigQuote){
		if(xigQuote == null){
			return null;
		}
		
		if(xigQuote.getSecurity() == null){
			throw new IllegalArgumentException("Cannot translate XigniteDelayedquote with no securities data!");
		}
		
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
		
		Quote mappedQuote = new Quote();
		mappedQuote.setChange(xigQuote.getChangeFromPreviousClose());
		mappedQuote.setChangePercent(xigQuote.getPercentChangeFromPreviousClose());
		mappedQuote.setChangePercentYTD(null);
		mappedQuote.setChangeYTD(null);
		mappedQuote.setHigh(xigQuote.getHigh());
		mappedQuote.setLastPrice(xigQuote.getLast());
		mappedQuote.setLow(xigQuote.getLow());
		mappedQuote.setMarketCap(null);
		mappedQuote.setmSDate(null);
		mappedQuote.setName(xigQuote.getSecurity().getName());
		mappedQuote.setOpen(xigQuote.getOpen());
		mappedQuote.setStatus(xigQuote.getOutcome());
		mappedQuote.setSymbol(xigQuote.getSecurity().getSymbol());
		Calendar cal = Calendar.getInstance();
		cal.setTime(xigQuote.getDate());
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(xigQuote.getTime());
		cal.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
		mappedQuote.setTimestamp(cal.getTime());
		mappedQuote.setVolume(xigQuote.getVolume().intValue());
		
		return mappedQuote;
	}
	/**
	 * Maps between a Yahoo Quote to Markit Quote
	 * 
	 * @param YahooQuote
	 * @return new quote
	 * @author David Ferreira Pinto
	 */
	public Quote mapFromYahooQuote(YahooQuote yQuote, Date created){
		if(yQuote == null){
			return null;
		}
		
		
		Quote mappedQuote = new Quote();
		mappedQuote.setChange(yQuote.getChange());
		if (yQuote.getPercentChange() != null) {
			mappedQuote.setChangePercent(Float.parseFloat(yQuote.getPercentChange().replace("%", "")));
		}
		mappedQuote.setChangePercentYTD(null);
		mappedQuote.setChangeYTD(null);
		mappedQuote.setHigh(yQuote.getDaysHigh());
		mappedQuote.setLastPrice(yQuote.getLastTradePriceOnly());
		mappedQuote.setLow(yQuote.getDaysLow());
		mappedQuote.setMarketCap(null);
		mappedQuote.setmSDate(null);
		mappedQuote.setName(yQuote.getName());
		mappedQuote.setOpen(yQuote.getOpen());
		mappedQuote.setStatus("SUCESS");
		mappedQuote.setSymbol(yQuote.getSymbol());
		mappedQuote.setTimestamp(created);
		mappedQuote.setVolume(yQuote.getVolume());
		
		return mappedQuote;
	}
}
