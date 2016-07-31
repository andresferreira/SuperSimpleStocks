package com.jpm.supersimplestocks;

import com.jpm.supersimplestocks.Configurations.ConfigHelper;

/**
 * The enum Stock enum.
 */
public enum Stock {

    /**
     * The TEA.
     */
    TEA(ConfigHelper.STOCK_TYPE_COMMON, 0, -1, 100),
    /**
     * The POP.
     */
    POP(ConfigHelper.STOCK_TYPE_COMMON, 8, -1, 100),
    /**
     * The ALE.
     */
    ALE(ConfigHelper.STOCK_TYPE_COMMON, 23, -1, 60),
    /**
     * The GIN.
     */
    GIN(ConfigHelper.STOCK_SYMBOL_PREFERRED, 0, 0.02, 100),
    /**
     * The JOE.
     */
    JOE(ConfigHelper.STOCK_TYPE_COMMON, 13, -1, 250);

    /**
     * The Stock type.
     */
    String stockType;

    /**
     * The Last dividend.
     */
    double lastDividend;

    /**
     * The Fixed dividend.
     */
    double fixedDividend;

    /**
     * The Par value.
     */
    double parValue;

    /**
     * The Price.
     */
    double price;

    /**
     * Instantiates a new Stock enum.
     *
     * @param stockType the stock type
     * @param lastDividend the last dividend
     * @param fixedDividend the fixed dividend
     * @param parValue the par value
     */
    Stock(String stockType, double lastDividend, double fixedDividend, double parValue) {
        this.stockType = stockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    /**
     * Gets stock.
     *
     * @param stockSymbol the stock symbol
     * @return the stock
     */
    public static Stock getStock(String stockSymbol) {
        Stock stock = null;
        if (stockSymbol.equals(ConfigHelper.STOCK_SYMBOL_TEA)) {
            stock = Stock.TEA;
        } else if (stockSymbol.equals(ConfigHelper.STOCK_SYMBOL_POP)) {
            stock = Stock.POP;
        } else if (stockSymbol.equals(ConfigHelper.STOCK_SYMBOL_ALE)) {
            stock = Stock.ALE;
        } else if (stockSymbol.equals(ConfigHelper.STOCK_SYMBOL_GIN)) {
            stock = Stock.GIN;
        } else if (stockSymbol.equals(ConfigHelper.STOCK_SYMBOL_JOE)) {
            stock = Stock.JOE;
        }
        return stock;
    }

    /**
     * Gets stock type.
     *
     * @return the stock type
     */
    public String getStockType() {
        return this.stockType;
    }

    /**
     * Sets stock type.
     *
     * @param stockType the stock type
     */
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    /**
     * Gets last dividend.
     *
     * @return the last dividend
     */
    public double getLastDividend() {
        return this.lastDividend;
    }

    /**
     * Sets last dividend.
     *
     * @param lastDividend the last dividend
     */
    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    /**
     * Gets fixed dividend.
     *
     * @return the fixed dividend
     */
    public double getFixedDividend() {
        return this.fixedDividend;
    }

    /**
     * Sets fixed dividend.
     *
     * @param fixedDividend the fixed dividend
     */
    public void setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    /**
     * Gets par value.
     *
     * @return the par value
     */
    public double getParValue() {
        return this.parValue;
    }

    /**
     * Sets par value.
     *
     * @param parValue the par value
     */
    public void setParValue(double parValue) {
        this.parValue = parValue;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StockEnum{" +
                "stockType='" + stockType + '\'' +
                ", lastDividend=" + lastDividend +
                ", fixedDividend=" + fixedDividend +
                ", parValue=" + parValue +
                ", price=" + price +
                '}';
    }
}

