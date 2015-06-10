package io.pivotal.portfolio.config;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.pivotal.portfolio.domain.Holding;
import io.pivotal.portfolio.domain.Order;
import io.pivotal.portfolio.domain.OrderType;
import io.pivotal.portfolio.domain.Portfolio;
import io.pivotal.portfolio.domain.Quote;

public class ServiceTestConfiguration {
	
	public static final String ACCOUNT_ID = "user";
	public static final String SYMBOL = "EMC";
	public static final Integer QUANTITY = 1000;
	public static final BigDecimal PRICE = new BigDecimal(10.00);
	public static final BigDecimal FEE = new BigDecimal(1.00);
	public static final Date COMPLETION_DATE = new Date(1329759342904l);
	
	public static final String QUOTE_NAME = "EMC Corp";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzzXXX yyyy");
	public static final String QUOTE_DATE_STRING = "Wed May 6 00:00:00 UTC-04:00 2015";
	public static final double QUOTE_LAST_PRICE = 26.135;
	public static final double QUOTE_CHANGE = 0.00500000000000256d;
	public static final double QUOTE_CHANGE_PERCENT = 0.0191350937619692;
	public static final double QUOTE_MSDATE = 42130d;
	
	public static final String COMPANY_EXCHANGE = "NASDAQ";
	
	public static List<Order> orders() {
		List<Order> orders = new ArrayList<>();
		orders.add(order());
		return orders;
	}
	
	public static Order order() {
		Order order1 = new Order();
		order1.setAccountId(ACCOUNT_ID);
		order1.setCompletionDate(COMPLETION_DATE);
		order1.setOrderFee(FEE);
		order1.setOrderType(OrderType.BUY);
		order1.setPrice(PRICE);
		order1.setQuantity(QUANTITY);
		order1.setSymbol(SYMBOL);
		return order1;
	}
	
	public static Order order2() {
		Order order1 = new Order();
		order1.setOrderId(1);
		order1.setAccountId(ACCOUNT_ID);
		order1.setCompletionDate(COMPLETION_DATE);
		order1.setOrderFee(FEE);
		order1.setOrderType(OrderType.BUY);
		order1.setPrice(PRICE);
		order1.setQuantity(QUANTITY);
		order1.setSymbol(SYMBOL);
		return order1;
	}
	public static Order sellOrder() {
		Order order1 = new Order();
		order1.setOrderId(1);
		order1.setAccountId(ACCOUNT_ID);
		order1.setCompletionDate(COMPLETION_DATE);
		order1.setOrderFee(FEE);
		order1.setOrderType(OrderType.SELL);
		order1.setPrice(PRICE);
		order1.setQuantity(QUANTITY);
		order1.setSymbol(SYMBOL);
		return order1;
	}
	
	public static Quote quote() {
		Quote quote = new Quote();
		quote.setName("EMC Corp");
		quote.setSymbol(SYMBOL);
		quote.setLastPrice(QUOTE_LAST_PRICE);
		quote.setChange(QUOTE_CHANGE);
		quote.setChangePercent(QUOTE_CHANGE_PERCENT);
		try {
			quote.setTimestamp(dateFormat.parse(QUOTE_DATE_STRING));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quote.setmSDate(QUOTE_MSDATE);
		quote.setMarketCap(50755764235.00);
		quote.setVolume(15159291);
		quote.setChangeYTD(29.74);
		quote.setChangePercentYTD(-12.1217215870881);
		quote.setHigh(0.0);
		quote.setLow(0.0);
		quote.setOpen(26.52);
		return quote;
	}
	
	public static Portfolio portfolio() {
		Holding holding = new Holding();
		holding.setId(1);
		holding.setQuantity(QUANTITY);
		holding.setPurchaseValue(PRICE);
		holding.setCurrentValue(new BigDecimal(QUOTE_LAST_PRICE));
		holding.addOrder(order2());
		holding.setSymbol(SYMBOL);
		Portfolio folio = new Portfolio();
		folio.setAccountId(ACCOUNT_ID);
		folio.addHolding(holding);
		folio.refreshTotalValue();
		return folio;
	}

}
