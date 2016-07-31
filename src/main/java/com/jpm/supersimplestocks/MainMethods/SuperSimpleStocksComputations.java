package com.jpm.supersimplestocks.MainMethods;

import com.jpm.supersimplestocks.Stock;
import com.jpm.supersimplestocks.SuperSimpleStocksInterface;
import com.jpm.supersimplestocks.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.jpm.supersimplestocks.Configurations.ConfigHelper.STOCK_TYPE_COMMON;

/**
 * The type Super simple stocks computations.
 */
public class SuperSimpleStocksComputations implements SuperSimpleStocksInterface {

    /**
     * The Trade list.
     */
    List<Trade> tradeList = new ArrayList<Trade>();

    private Logger logger = Logger.getLogger(SuperSimpleStocksComputations.class.getName());

    /**
     * @param stockSymbol the stock symbol
     * @param marketPrice the market price
     * @return
     */
    public double calculateDividendYield(String stockSymbol, double marketPrice) throws Exception {

        double dividendYeld = 0.0;
        Stock stock = Stock.getStock(stockSymbol);

        try {
            if(stock == null)
                throw new Exception("Stock is null");

            if (marketPrice > 0.0) {            // Preferred
                if (stock.getStockType().equals(STOCK_TYPE_COMMON)) {
                    dividendYeld = stock.getLastDividend() / marketPrice;
                } else {
                    dividendYeld = stock.getFixedDividend() * stock.getParValue() / marketPrice;
                }
            } else  {
                throw new Exception("Error - Dividend Yeld - Market Price should be more then zero");
            }
        } catch (Exception e) {
            logger.info("Exception caught calculating Dividend Yeld");
            throw new Exception("Error - Dividend Yeld");
        }
        return dividendYeld;
    }

    /**
     * @param stockSymbol the stock symbol
     * @param marketPrice the market price
     * @return
     */
    public double calculatePERatio(String stockSymbol, double marketPrice) throws Exception {

        double peRatio = 0.0;

        try {
            if(stockSymbol == null)
                throw new Exception("Stock Symbol is null");

            if (marketPrice > 0.0) {
                peRatio = marketPrice / calculateDividendYield(stockSymbol, marketPrice);
            }
        } catch (Exception e) {
            logger.info("Exception caught calculating PE Ratio");
            throw new Exception("Error - PE Ratio");
        }

        return peRatio;
    }

    /**
     * @param trade the trade
     * @return
     */
    public boolean recordTrade(Trade trade) throws Exception {

        boolean tradeInfo;

        try {
            if(trade == null)
                throw new Exception("Trade is null");

            if(trade.getSharesQuantity() > 0 && trade.getPrice() > 0) {
                tradeInfo = tradeList.add(trade);
            } else {
                    throw new Exception("Trade has invalid parameters");
            }
        } catch (Exception e) {
            logger.info("Exception caught getting record Trade");
            throw new Exception("Error - record Trade");
        }
        return tradeInfo;
    }

    /**
     * @param tradeList the trade list
     * @param timeRange the time range
     * @return
     */
    public double calculateVolumeWeightedStockPrice(List<Trade> tradeList, int timeRange) throws Exception {

        double volumeWeightedStockPrice = 0.0;

        try {
            if(tradeList == null)
                throw new Exception("Trade List is null");

            volumeWeightedStockPrice = volumeWeightedStockPriceInTime(tradeList, 15);
        } catch (Exception e) {
            logger.info("Exception caught calculating Volume Weighted Stock Price");
            throw new Exception("Error - Volume Weighted Stock Price");
        }
        return volumeWeightedStockPrice;
    }

    /**
     * @param stockList
     * @return
     */
    public double calculateGBCEAllShareIndex(List<Stock> stockList) throws Exception {

        double gbceAllShareIndex = 0.0;

        try {
            ArrayList<Double> stockPrices = new ArrayList<Double>();
            for (Stock stock : stockList) {
                double stockPrice = stock.getPrice();
                if (stockPrice > 0) {
                    stockPrices.add(stockPrice);
                }
            }

            if (stockPrices.size() >= 1) {
                double[] stockPricesArray = new double[stockPrices.size()];

                for (int i = 0; i <= (stockPrices.size() - 1); i++) {
                    stockPricesArray[i] = stockPrices.get(i).doubleValue();
                }
                // Calculates the GBCE All Share Index
                gbceAllShareIndex = geoMean(stockPricesArray);
            }
        } catch (Exception e) {
            logger.info("Exception caught calculating Share Index");
            throw new Exception("Error - Share Index");
        }
        return gbceAllShareIndex;
    }

    /**
     * Gets trade list in time.
     *
     * @param tradeList the trade list
     * @param timeRangeinMuntes the time rangein muntes
     * @return the trade list in time
     */
    public List<Trade> getTradeListInTime(List<Trade> tradeList, int timeRangeinMuntes) {

        List tradeListTimeRange = new ArrayList();

        long now = System.currentTimeMillis();
        long timeRangeComputtion = now - (TimeUnit.MINUTES.toMillis(timeRangeinMuntes));

        for (Trade trade : tradeList) {
            if (trade.getTimeStamp() >= timeRangeComputtion) {
                tradeListTimeRange.add(trade);
            }
        }
        return tradeListTimeRange;
    }

    /**
     * @param arr
     * @return
     */
    private static double geoMean(double[] arr) {

        if (arr.length == 0) {
            return 0.0;
        }

        double gm = 1.0;
        for (int i = 0; i < arr.length; i++) {
            gm *= arr[i];
        }
        gm = Math.pow(gm, 1.0 / (double) arr.length);

        return gm;
    }

    /**
     * Volume weighted stock price in time.
     *
     * @param tradeList the trade list
     * @param timeRange the time range
     * @return double double
     */
    private double volumeWeightedStockPriceInTime(List<Trade> tradeList, int timeRange) {
        double stockPrice = 0.0;

        // Calculate the summation
        double shareQuantityTotal = 0.0;
        double tradePriceTotal = 0.0;

        List<Trade> tradeList15mint = getTradeListInTime(tradeList, 15);

        for (Trade trade : tradeList15mint) {
            // Price x Quantity
            tradePriceTotal += (trade.getPrice() * trade.getSharesQuantity());
            // Quantity Summation
            shareQuantityTotal += trade.getSharesQuantity();
        }

        // Volume Weighted Stock Price formula
        if (shareQuantityTotal > 0.0) {
            stockPrice = tradePriceTotal / shareQuantityTotal;
        }

        return stockPrice;
    }
}
