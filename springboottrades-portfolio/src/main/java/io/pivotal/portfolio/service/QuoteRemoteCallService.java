package io.pivotal.portfolio.service;

import java.util.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.pivotal.portfolio.domain.Quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Retrieves quotes from the quote service. Uses hystrix to manage failure.
 * 
 * @author David Ferreira Pinto
 *
 */
@Service
@RefreshScope
public class QuoteRemoteCallService {

	private static final Logger logger = LoggerFactory.getLogger(QuoteRemoteCallService.class);

	@Value("${pivotal.quotesService.name}")
	private String quotesService;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	/**
	 * Retrieve multiple quotes.
	 * 
	 * @param symbols comma separated list of symbols.
	 * @return
	 */
    @HystrixCommand(fallbackMethod = "getQuoteFallback",
            commandProperties = {@HystrixProperty(name="execution.timeout.enabled", value="false")})
	public List<Quote> getQuotes(String symbols) {
		logger.debug("retrieving multiple quotes: " + symbols);
		Quote[] quotesArr = restTemplate.getForObject("http://" + quotesService + "/quotes?q={symbols}", Quote[].class, symbols);
		List<Quote> quotes = Arrays.asList(quotesArr);
		logger.debug("Received quotes: {}",quotes);
		return quotes;
	}

	/**
	 * Retrieve multiple quotes.
	 * 
	 * @param symbols
	 * @return
	 */
	public List<Quote> getQuotes(Collection<String> symbols) {
		logger.debug("Fetching multiple quotes array: {} ",symbols);
		StringBuilder builder = new StringBuilder();
		for (Iterator<String> i = symbols.iterator(); i.hasNext();) {
			builder.append(i.next());
			if (i.hasNext()) {
				builder.append(",");
			}
		}
		return getQuotes(builder.toString());
	}

    @SuppressWarnings("unused")
    private List<Quote> getQuoteFallback(String symbols) {
        List<Quote> result = new ArrayList<>();
        String[] splitSymbols = symbols.split(",");

        for (String symbol : splitSymbols) {
            Quote quote = new Quote();
            quote.setSymbol(symbol);
            quote.setStatus("FAILED");
            result.add( quote );
        }
        return result;
    }
}
