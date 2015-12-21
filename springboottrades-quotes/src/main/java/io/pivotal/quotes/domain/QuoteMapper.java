/**
 * 
 */
package io.pivotal.quotes.domain;

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
