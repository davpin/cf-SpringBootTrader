package io.pivotal.quotes.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Security{
		@JsonProperty("CIK")
		private String CIK=null;
		
		@JsonProperty("CUSIP")
		private String CUSIP=null;
		
		@JsonProperty("Symbol")
		private String Symbol=null;
		
		@JsonProperty("ISIN")
		private String ISIN=null;
		
		@JsonProperty("Valoren")
		private String valoren=null;
		
		@JsonProperty("Name")
		private String name=null;
		
		@JsonProperty("Market")
		private String market=null;
		
		@JsonProperty("MarketIdentificationCode")
		private String marketIdentificationCode=null;
		
		@JsonProperty("MostLiquidExchange")
		private String mostLiquidExchange=null;
		
		@JsonProperty("CategoryOrIndustry")
		private String categoryOrIndustry=null;

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Security [CIK=");
			builder.append(CIK);
			builder.append(", CUSIP=");
			builder.append(CUSIP);
			builder.append(", Symbol=");
			builder.append(Symbol);
			builder.append(", ISIN=");
			builder.append(ISIN);
			builder.append(", valoren=");
			builder.append(valoren);
			builder.append(", name=");
			builder.append(name);
			builder.append(", market=");
			builder.append(market);
			builder.append(", marketIdentificationCode=");
			builder.append(marketIdentificationCode);
			builder.append(", mostLiquidExchange=");
			builder.append(mostLiquidExchange);
			builder.append(", categoryOrIndustry=");
			builder.append(categoryOrIndustry);
			builder.append("]");
			return builder.toString();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((CIK == null) ? 0 : CIK.hashCode());
			result = prime * result + ((CUSIP == null) ? 0 : CUSIP.hashCode());
			result = prime * result + ((ISIN == null) ? 0 : ISIN.hashCode());
			result = prime * result
					+ ((Symbol == null) ? 0 : Symbol.hashCode());
			result = prime
					* result
					+ ((categoryOrIndustry == null) ? 0 : categoryOrIndustry
							.hashCode());
			result = prime * result
					+ ((market == null) ? 0 : market.hashCode());
			result = prime
					* result
					+ ((marketIdentificationCode == null) ? 0
							: marketIdentificationCode.hashCode());
			result = prime
					* result
					+ ((mostLiquidExchange == null) ? 0 : mostLiquidExchange
							.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((valoren == null) ? 0 : valoren.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Security other = (Security) obj;
			if (CIK == null) {
				if (other.CIK != null)
					return false;
			} else if (!CIK.equals(other.CIK))
				return false;
			if (CUSIP == null) {
				if (other.CUSIP != null)
					return false;
			} else if (!CUSIP.equals(other.CUSIP))
				return false;
			if (ISIN == null) {
				if (other.ISIN != null)
					return false;
			} else if (!ISIN.equals(other.ISIN))
				return false;
			if (Symbol == null) {
				if (other.Symbol != null)
					return false;
			} else if (!Symbol.equals(other.Symbol))
				return false;
			if (categoryOrIndustry == null) {
				if (other.categoryOrIndustry != null)
					return false;
			} else if (!categoryOrIndustry.equals(other.categoryOrIndustry))
				return false;
			if (market == null) {
				if (other.market != null)
					return false;
			} else if (!market.equals(other.market))
				return false;
			if (marketIdentificationCode == null) {
				if (other.marketIdentificationCode != null)
					return false;
			} else if (!marketIdentificationCode
					.equals(other.marketIdentificationCode))
				return false;
			if (mostLiquidExchange == null) {
				if (other.mostLiquidExchange != null)
					return false;
			} else if (!mostLiquidExchange.equals(other.mostLiquidExchange))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (valoren == null) {
				if (other.valoren != null)
					return false;
			} else if (!valoren.equals(other.valoren))
				return false;
			return true;
		}

		/**
		 * @return the cIK
		 */
		public String getCIK() {
			return CIK;
		}

		/**
		 * @param cIK the cIK to set
		 */
		public void setCIK(String cIK) {
			CIK = cIK;
		}

		/**
		 * @return the cUSIP
		 */
		public String getCUSIP() {
			return CUSIP;
		}

		/**
		 * @param cUSIP the cUSIP to set
		 */
		public void setCUSIP(String cUSIP) {
			CUSIP = cUSIP;
		}

		/**
		 * @return the symbol
		 */
		public String getSymbol() {
			return Symbol;
		}

		/**
		 * @param symbol the symbol to set
		 */
		public void setSymbol(String symbol) {
			Symbol = symbol;
		}

		/**
		 * @return the iSIN
		 */
		public String getISIN() {
			return ISIN;
		}

		/**
		 * @param iSIN the iSIN to set
		 */
		public void setISIN(String iSIN) {
			ISIN = iSIN;
		}

		/**
		 * @return the valoren
		 */
		public String getValoren() {
			return valoren;
		}

		/**
		 * @param valoren the valoren to set
		 */
		public void setValoren(String valoren) {
			this.valoren = valoren;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the market
		 */
		public String getMarket() {
			return market;
		}

		/**
		 * @param market the market to set
		 */
		public void setMarket(String market) {
			this.market = market;
		}

		/**
		 * @return the marketIdentificationCode
		 */
		public String getMarketIdentificationCode() {
			return marketIdentificationCode;
		}

		/**
		 * @param marketIdentificationCode the marketIdentificationCode to set
		 */
		public void setMarketIdentificationCode(String marketIdentificationCode) {
			this.marketIdentificationCode = marketIdentificationCode;
		}

		/**
		 * @return the mostLiquidExchange
		 */
		public String getMostLiquidExchange() {
			return mostLiquidExchange;
		}

		/**
		 * @param mostLiquidExchange the mostLiquidExchange to set
		 */
		public void setMostLiquidExchange(String mostLiquidExchange) {
			this.mostLiquidExchange = mostLiquidExchange;
		}

		/**
		 * @return the categoryOrIndustry
		 */
		public String getCategoryOrIndustry() {
			return categoryOrIndustry;
		}

		/**
		 * @param categoryOrIndustry the categoryOrIndustry to set
		 */
		public void setCategoryOrIndustry(String categoryOrIndustry) {
			this.categoryOrIndustry = categoryOrIndustry;
		}
	}