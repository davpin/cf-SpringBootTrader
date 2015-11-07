package io.pivotal.quotes.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YahooResults {

	@JsonProperty("results")
	private YahooQuoteList quoteList;

	@JsonProperty("count")
	private Integer count;
	
	@JsonProperty("created") 
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", locale="ENGLISH") //"2015-11-03T11:57:31Z"
	private Date created;
	
	@JsonProperty("lang")
	private String lang;
	
	public YahooQuoteList getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(YahooQuoteList quoteList) {
		this.quoteList = quoteList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("YahooResults [quoteList=").append(quoteList)
				.append(", count=").append(count).append(", created=")
				.append(created).append(", lang=").append(lang).append("]");
		return builder.toString();
	}
}