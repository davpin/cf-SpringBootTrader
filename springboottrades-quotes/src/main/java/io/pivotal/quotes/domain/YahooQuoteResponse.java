package io.pivotal.quotes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class YahooQuoteResponse {

	@JsonProperty("query")
	private YahooResults results;
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YahooQuoteResponse [results=").append(results)
				.append("]");
		return builder.toString();
	}

	public YahooResults getResults() {
		return results;
	}

	public void setResults(YahooResults results) {
		this.results = results;
	}
	
}
