package io.pivotal.quotes.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class YahooQuoteList {
	
	@JsonProperty(value="quote")
	private List<YahooQuote> quote;

	public List<YahooQuote> getQuote() {
		return quote;
	}

	public void setQuote(List<YahooQuote> quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YahooQuoteList [quote=").append(quote).append("]");
		return builder.toString();
	}
	
}