package com.jpm.supersimplestocks;

/**
 * The type Trade.
 */
public class Trade {

    private long timeStamp;

    private int sharesQuantity = 0;

    private String tradeIndicator;

    private double price = 0.0;

    /**
     * Instantiates a new Trade.
     */
    public Trade() {
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public long getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets shares quantity.
     *
     * @return the shares quantity
     */
    public int getSharesQuantity() {
        return this.sharesQuantity;
    }

    /**
     * Sets shares quantity.
     *
     * @param sharesQuantity the shares quantity
     */
    public void setSharesQuantity(int sharesQuantity) {
        this.sharesQuantity = sharesQuantity;
    }

    /**
     * Gets trade indicator.
     *
     * @return the trade indicator
     */
    public String getTradeIndicator() {
        return this.tradeIndicator;
    }

    /**
     * Sets trade indicator.
     *
     * @param tradeIndicator the trade indicator
     */
    public void setTradeIndicator(String tradeIndicator) {
        this.tradeIndicator = tradeIndicator;
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
        return "Trade{" +
                "timeStamp=" + timeStamp +
                ", sharesQuantity=" + sharesQuantity +
                ", tradeIndicator='" + tradeIndicator + '\'' +
                ", price=" + price +
                '}';
    }
}
