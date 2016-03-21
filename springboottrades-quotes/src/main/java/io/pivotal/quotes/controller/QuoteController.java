package io.pivotal.quotes.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import io.pivotal.quotes.domain.CompanyInfo;
import io.pivotal.quotes.domain.Quote;
import io.pivotal.quotes.exception.SymbolNotFoundException;
import io.pivotal.quotes.service.QuoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller providing the REST API for the Quote Service. Provides two calls (both HTTP GET methods):
 *
 * - /quotes?q={symbol} - Retrieves the current
 * quotes for a comma-separated symbol list.
 * - /company/{name} - Retrieves a list of company
 * information for companies that match the {name}.
 * 
 * @author David Ferreira Pinto
 */
@RestController
public class QuoteController {
	private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

	/**
	 * The service to delegate calls to.
	 */
	@Autowired
	private QuoteService service;

	/**
	 * Retrieves the current quotes for the given symbols.
	 *
	 * @param query
	 *            request parameter with q=symbol,symbol
	 * @return The Quote
	 * @throws SymbolNotFoundException
	 *             if the symbol is not valid.
	 */
	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public ResponseEntity<List<Quote>> getQuotes(@RequestParam(value="q", required=false) String query) throws SymbolNotFoundException {
		logger.debug("received Quote query for: %s", query);
		if (query == null) {
			//return empty list.
			return new ResponseEntity<>(new ArrayList<>(), getNoCacheHeaders(), HttpStatus.OK);
		}

		List<Quote> quotes = service.getQuotes( query );

		logger.info(String.format("Retrieved symbols: %s with quotes {}", query));
		return new ResponseEntity<>(quotes, getNoCacheHeaders(), HttpStatus.OK);
	}

	/**
	 * Searches for companies that have a name or symbol matching the parameter.
	 * 
	 * @param name
	 *            The name or symbol to search for.
	 * @return The list of companies that match the search parameter.
	 */
	@RequestMapping(value = "/company/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyInfo>> getCompanies(@PathVariable("name") final String name) {
		logger.debug("QuoteController.getCompanies: retrieving companies for: " + name);
		List<CompanyInfo> companies = service.getCompanyInfo(name);
		logger.info(String.format("Retrieved companies with search parameter: %s - list: {}", name), companies);
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	/**
	 * Generates HttpHeaders that have the no-cache set.
	 * 
	 * @return HttpHeaders.
	 */
	private HttpHeaders getNoCacheHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Cache-Control", "no-cache");
		return responseHeaders;
	}

	/**
	 * Handles the response to the client if there is any exception during the
	 * processing of HTTP requests.
	 * 
	 * @param e
	 *            The exception thrown during the processing of the request.
	 * @param response
	 *            The HttpResponse object.
	 * @throws IOException
	 */
	@ExceptionHandler({ Exception.class })
	public void handleException(Exception e, HttpServletResponse response) throws IOException {
		logger.warn("Handle Error: " + e.getMessage());
		logger.warn("Exception:", e);
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR: " + e.getMessage());
	}
}
