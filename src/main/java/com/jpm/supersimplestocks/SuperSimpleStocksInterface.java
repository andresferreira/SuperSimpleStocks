package com.jpm.supersimplestocks;

import java.util.List;

/**
 * The interface Super simple stock interface.
 */
public interface SuperSimpleStocksInterface {

    /**
     * Calculate Dividend Yield.
     *
     * @param stockSymbol the stock symbol
     * @param marketPrice the market price
     * @return the double
     */
    public double calculateDividendYield(String stockSymbol, double marketPrice) throws Exception;

    /**
     * Calculate PE Ratio.
     *
     * @param stockSymbol the stock symbol
     * @param marketPrice the market price
     * @return the double
     */
    public double calculatePERatio(String stockSymbol, double marketPrice) throws Exception;

    /**
     * Record Trade.
     *
     * @param trade the trade
     * @return the boolean
     */
    public boolean recordTrade(Trade trade) throws Exception;

    /**
     * Volume weighted stock price.
     *
     * @param tradeList the trade list
     * @param timeRange the time range
     * @return the double
     */
    public double calculateVolumeWeightedStockPrice(List<Trade> tradeList, int timeRange) throws Exception;

    /**
     * Calculate GBCE all share index.
     *
     * @return the double
     */
    public double calculateGBCEAllShareIndex(List<Stock> stockList) throws Exception;
}
