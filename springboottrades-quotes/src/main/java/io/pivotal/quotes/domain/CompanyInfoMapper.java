/**
 * 
 */
package io.pivotal.quotes.domain;

/**
 * Maps Company Infor from non Markit services
 * @author skazi
 */
public class CompanyInfoMapper {
	private CompanyInfoMapper() {
		super();
	}
	
	public static final CompanyInfoMapper INSTANCE = new CompanyInfoMapper();
	
	public CompanyInfo mapXigniteCompanyInfo(XigniteDelayedQuote quote){
		if(quote == null){
			return null;
		}
		
		if(quote.getSecurity() == null){
			throw new IllegalArgumentException("Cannot extract company info from quote with no securities.");
		}
		
		CompanyInfo info = new CompanyInfo();
		
		info.setExchange(quote.getSecurity().getMarket());
		info.setName(quote.getSecurity().getName());
		info.setSymbol(quote.getSecurity().getSymbol());
		
		return info;
	}

}
