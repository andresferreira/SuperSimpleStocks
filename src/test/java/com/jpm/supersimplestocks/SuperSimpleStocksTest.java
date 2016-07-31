package com.jpm.supersimplestocks;

import com.jpm.supersimplestocks.Configurations.ConfigHelper;
import com.jpm.supersimplestocks.MainMethods.SuperSimpleStocksComputations;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The type Super simple stocks test.
 */
public class SuperSimpleStocksTest extends TestCase {

    /**
     * Test calculate dividend yield.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalculateDividendYield() throws Exception {

        SuperSimpleStocksComputations stock = new SuperSimpleStocksComputations();
        double dividendYeeldResult;

        // Positive tests
        dividendYeeldResult = stock.calculateDividendYield(ConfigHelper.STOCK_SYMBOL_TEA, 1);
        assertEquals(0.0, dividendYeeldResult);

        dividendYeeldResult = stock.calculateDividendYield(ConfigHelper.STOCK_SYMBOL_POP, 1);
        assertEquals(8.0, dividendYeeldResult);

        dividendYeeldResult = stock.calculateDividendYield(ConfigHelper.STOCK_SYMBOL_ALE, 1);
        assertEquals(23.0, dividendYeeldResult);

        dividendYeeldResult = stock.calculateDividendYield(ConfigHelper.STOCK_SYMBOL_GIN, 1);
        assertEquals(2.0, dividendYeeldResult);

        dividendYeeldResult = stock.calculateDividendYield(ConfigHelper.STOCK_SYMBOL_JOE, 1);
        assertEquals(13.0, dividendYeeldResult);
    }

    /**
     * Test calculate pE ratio.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalculatePERatio() throws Exception {
        SuperSimpleStocksComputations stock = new SuperSimpleStocksComputations();
        double peRatioResult;

        peRatioResult = stock.calculatePERatio(ConfigHelper.STOCK_SYMBOL_POP, 1);
        assertEquals(0.125, peRatioResult);
    }

    /**
     * Test trade calculate volume weighted stock price in time.
     *
     * @throws Exception the exception
     */
    @Test
    public void testTradeCalculateVolumeWeightedStockPriceInTime() throws Exception {

        SuperSimpleStocksComputations stock = new SuperSimpleStocksComputations();
        List<Trade> tradeList = new ArrayList<Trade>();
        Trade trade = new Trade();

        // < 15 minutes
        trade.setTimeStamp(Calendar.getInstance().getTimeInMillis());
        tradeList.add(trade);
        assertEquals(1, stock.getTradeListInTime(tradeList, 15).size());

        // aprox. 15 minutes
        tradeList.clear();
        trade.setTimeStamp(Calendar.getInstance().getTimeInMillis()-899999); //15 minutes = 900000 ms (timestamp)
        tradeList.add(trade);
        assertEquals(1,stock.getTradeListInTime(tradeList, 15).size());

        // > 15 minutes
        tradeList.clear();
        trade.setTimeStamp(Calendar.getInstance().getTimeInMillis()-901000);
        tradeList.add(trade);
        assertEquals(0,stock.getTradeListInTime(tradeList, 15).size());
    }

    /**
     * Test record trade.
     *
     * @throws Exception the exception
     */
    @Test
    public void testRecordTrade() throws Exception {

        SuperSimpleStocksComputations stock = new SuperSimpleStocksComputations();

        Trade trade = new Trade();
        trade.setTimeStamp(System.currentTimeMillis());
        trade.setSharesQuantity(1);
        trade.setTradeIndicator(ConfigHelper.BUY_INDICATOR);
        trade.setPrice(10);

        String expectedString = "Trade{" +
                "timeStamp=" + trade.getTimeStamp() +
                ", sharesQuantity=" + trade.getSharesQuantity() +
                ", tradeIndicator='" + trade.getTradeIndicator() + '\'' +
                ", price=" + trade.getPrice() +
                '}';

        stock.recordTrade(trade);
        assertEquals(expectedString,trade.toString());
    }

    /**
     * Test stock price.
     *
     * @throws Exception the exception
     */
    @Test
    public void testStockPrice() throws Exception {

        SuperSimpleStocksComputations stock = new SuperSimpleStocksComputations();

        List<Trade> tradeList = new ArrayList<Trade>();
        Trade trade1 = new Trade();
        Trade trade2 = new Trade();
        trade1.setPrice(10);
        trade1.setSharesQuantity(1);
        trade1.setTimeStamp(System.currentTimeMillis());
        tradeList.add(trade1);
        trade2.setPrice(2);
        trade2.setSharesQuantity(4);
        trade2.setTimeStamp(System.currentTimeMillis());
        tradeList.add(trade2);

        double expectedResult = (10.0*1 + 4.0*2)/(1+4);
        double actualResult = stock.calculateVolumeWeightedStockPrice(tradeList, 15);

        assertEquals(expectedResult,actualResult);
    }

    /**
     * Test GBCE all share index.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGBCEAllShareIndex() throws Exception {

        SuperSimpleStocksComputations stock = new SuperSimpleStocksComputations();
        List<Stock> stockList = new ArrayList<Stock>();
        Stock stock1 = Stock.POP;
        stock1.setPrice(10.0);
        stockList.add(stock1);
        Stock stock2 = Stock.JOE;
        stock2.setPrice(10.0);
        stockList.add(stock2);

        double allShareIndex = stock.calculateGBCEAllShareIndex(stockList);
        assertEquals(10.0,allShareIndex);
    }
}