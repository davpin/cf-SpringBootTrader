package io.pivotal.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import io.pivotal.web.domain.CompanyInfo;
import io.pivotal.web.domain.Order;
import io.pivotal.web.domain.Quote;
import io.pivotal.web.domain.Search;
import io.pivotal.web.service.MarketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TradeController {

	private static final Logger logger = LoggerFactory
			.getLogger(TradeController.class);
	
	@Autowired
	private MarketService marketService;
	
	@RequestMapping(value = "/trade", method = RequestMethod.GET)
	public String showTrade(Model model) {
		logger.debug("/trade.GET");
		//model.addAttribute("marketSummary", marketService.getMarketSummary());
		
		model.addAttribute("search", new Search());
		//check if user is logged in!
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    logger.debug("User logged in: " + currentUserName);
		    model.addAttribute("order", new Order());
		    //TODO: add account summary?
		    try {
		    	model.addAttribute("portfolio",marketService.getPortfolio(currentUserName));
		    } catch (HttpServerErrorException e) {
		    	model.addAttribute("portfolioRetrievalError",e.getMessage());
		    }
		}
		
		return "trade";
	}
	@RequestMapping(value = "/trade", method = RequestMethod.POST)
	public String showTrade(Model model, @ModelAttribute("search") Search search) {
		logger.debug("/trade.POST - symbol: " + search.getName());
		
		//model.addAttribute("marketSummary", marketService.getMarketSummary());
		model.addAttribute("search", search);
		
		if (search.getName() == null || search.getName().equals("") ) {
			model.addAttribute("quotes", new ArrayList<Quote>());
		} else {
			List<Quote> newQuotes = getQuotes(search.getName());
			model.addAttribute("quotes", newQuotes);
		}
		//check if user is logged in!
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    logger.debug("User logged in: " + currentUserName);
		    model.addAttribute("order", new Order());
		    //TODO: add portfolio and account summary.
		    try {
		    	model.addAttribute("portfolio",marketService.getPortfolio(currentUserName));
		    } catch (HttpServerErrorException e) {
		    	model.addAttribute("portfolioRetrievalError",e.getMessage());
		    }
		}
		
		return "trade";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String buy(Model model, @ModelAttribute("order") Order order) {
		model.addAttribute("search", new Search());
		
		// buy the order after setting attributes not set by the UI.
		//check if user is logged in!
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (!(authentication instanceof AnonymousAuthenticationToken)) {
				    String currentUserName = authentication.getName();
				    logger.debug("/order ORDER: " + order);
				    order.setAccountId(currentUserName);
				    order.setCompletionDate(new Date());

				    Order result = marketService.sendOrder(order);
				    model.addAttribute("savedOrder", result);
				    model.addAttribute("order", new Order());
				    try {
				    	model.addAttribute("portfolio",marketService.getPortfolio(currentUserName));
				    } catch (HttpServerErrorException e) {
				    	model.addAttribute("portfolioRetrievalError",e.getMessage());
				    }
				} else {
					//should never get here!!!
				}
		return "trade";
	}
	
	
	private List<Quote> getQuotes(String companyName) {
		logger.debug("Fetching quotes for companies that have: " + companyName + " in name or symbol");
		List<CompanyInfo> companies = marketService.getCompanies(companyName);
		
		/*
		 * Sleuth currently doesn't work with parallelStreams
		 */
		//get district companyinfos and get their respective quotes in parallel.
		//List<Quote> result = companies.stream().collect(Collectors.toCollection(
		//	      () -> new TreeSet<CompanyInfo>((p1, p2) -> p1.getSymbol().compareTo(p2.getSymbol())) 
		//		)).parallelStream().map(n -> getQuote(n.getSymbol())).collect(Collectors.toList());
		List<Quote> result = companies.stream().collect(Collectors.toCollection(
			      () -> new TreeSet<CompanyInfo>((p1, p2) -> p1.getSymbol().compareTo(p2.getSymbol())) 
				)).stream().map(n -> getQuote(n.getSymbol())).collect(Collectors.toList());
		
		List<Quote> quotes = result.parallelStream().filter(n -> n.getStatus().startsWith("SUCCESS")).collect(Collectors.toList());
		return quotes;
	}
	
	private Quote getQuote(String symbol) {
		return marketService.getQuote(symbol);
	}
	
	@ExceptionHandler({ Exception.class })
	public ModelAndView error(HttpServletRequest req, Exception exception) {
		logger.debug("Handling error: " + exception);
		logger.warn("Exception:", exception);
		ModelAndView model = new ModelAndView();
		model.addObject("errorCode", exception.getMessage());
		model.addObject("errorMessage", exception);
		model.setViewName("error");
		return model;
	}
}
