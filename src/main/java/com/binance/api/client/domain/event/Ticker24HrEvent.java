package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker24HrEvent {

    @JsonProperty("e")
    private String eventType;

    @JsonProperty("E")
    private long eventTime;

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("p")
    private String priceChange;

    @JsonProperty("P")
    private String priceChangePercent;

    @JsonProperty("w")
    private String weightedAveragePrice;

    @JsonProperty("x")
    private String previousDayClosePrice;

    @JsonProperty("c")
    private String currentDayClosePrice;

    @JsonProperty("Q")
    private String quantityOfCloseTrade;

    @JsonProperty("b")
    private String bestBidPrice;

    @JsonProperty("B")
    private String bestBidQuantity;

    @JsonProperty("a")
    private String bestAskPrice;

    @JsonProperty("A")
    private String bestAskQuantity;

    @JsonProperty("o")
    private String openPrice;

    @JsonProperty("h")
    private String highPrice;

    @JsonProperty("l")
    private String lowPrice;

    @JsonProperty("v")
    private String totalTradedBaseAssetVolume;

    @JsonProperty("q")
    private String totalTradedBaseQuoteAssetVolume;

    @JsonProperty("O")
    private long statsOpenTime;

    @JsonProperty("C")
    private long statsCloseTime;

    @JsonProperty("F")
    private long firstTradeId;

    @JsonProperty("L")
    private long lastTradeId;

    @JsonProperty("n")
    private long totalNumberOfTrades;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(String priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getWeightedAveragePrice() {
        return weightedAveragePrice;
    }

    public void setWeightedAveragePrice(String weightedAveragePrice) {
        this.weightedAveragePrice = weightedAveragePrice;
    }

    public String getPreviousDayClosePrice() {
        return previousDayClosePrice;
    }

    public void setPreviousDayClosePrice(String previousDayClosePrice) {
        this.previousDayClosePrice = previousDayClosePrice;
    }

    public String getCurrentDayClosePrice() {
        return currentDayClosePrice;
    }

    public void setCurrentDayClosePrice(String currentDayClosePrice) {
        this.currentDayClosePrice = currentDayClosePrice;
    }

    public String getQuantityOfCloseTrade() {
        return quantityOfCloseTrade;
    }

    public void setQuantityOfCloseTrade(String quantityOfCloseTrade) {
        this.quantityOfCloseTrade = quantityOfCloseTrade;
    }

    public String getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(String bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public String getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(String bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getTotalTradedBaseAssetVolume() {
        return totalTradedBaseAssetVolume;
    }

    public void setTotalTradedBaseAssetVolume(String totalTradedBaseAssetVolume) {
        this.totalTradedBaseAssetVolume = totalTradedBaseAssetVolume;
    }

    public String getTotalTradedBaseQuoteAssetVolume() {
        return totalTradedBaseQuoteAssetVolume;
    }

    public void setTotalTradedBaseQuoteAssetVolume(String totalTradedBaseQuoteAssetVolume) {
        this.totalTradedBaseQuoteAssetVolume = totalTradedBaseQuoteAssetVolume;
    }

    public long getStatsOpenTime() {
        return statsOpenTime;
    }

    public void setStatsOpenTime(long statsOpenTime) {
        this.statsOpenTime = statsOpenTime;
    }

    public long getStatsCloseTime() {
        return statsCloseTime;
    }

    public void setStatsCloseTime(long statsCloseTime) {
        this.statsCloseTime = statsCloseTime;
    }

    public long getFirstTradeId() {
        return firstTradeId;
    }

    public void setFirstTradeId(long firstTradeId) {
        this.firstTradeId = firstTradeId;
    }

    public long getLastTradeId() {
        return lastTradeId;
    }

    public void setLastTradeId(long lastTradeId) {
        this.lastTradeId = lastTradeId;
    }

    public long getTotalNumberOfTrades() {
        return totalNumberOfTrades;
    }

    public void setTotalNumberOfTrades(long totalNumberOfTrades) {
        this.totalNumberOfTrades = totalNumberOfTrades;
    }

    public String getBestBidQuantity() {
        return bestBidQuantity;
    }

    public void setBestBidQuantity(String bestBidQuantity) {
        this.bestBidQuantity = bestBidQuantity;
    }

    public String getBestAskQuantity() {
        return bestAskQuantity;
    }

    public void setBestAskQuantity(String bestAskQuantity) {
        this.bestAskQuantity = bestAskQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("eventType", eventType)
            .append("eventTime", eventTime)
            .append("symbol", symbol)
            .append("priceChange", priceChange)
            .append("priceChangePercent", priceChangePercent)
            .append("weightedAveragePrice", weightedAveragePrice)
            .append("previousDayClosePrice", previousDayClosePrice)
            .append("currentDayClosePrice", currentDayClosePrice)
            .append("quantityOfCloseTrade", quantityOfCloseTrade)
            .append("bestBidPrice", bestBidPrice)
            .append("bestBidQuantity", bestBidQuantity)
            .append("bestAskPrice", bestAskPrice)
            .append("bestAskQuantity", bestAskQuantity)
            .append("openPrice", openPrice)
            .append("highPrice", highPrice)
            .append("lowPrice", lowPrice)
            .append("totalTradedBaseAssetVolume", totalTradedBaseAssetVolume)
            .append("totalTradedBaseQuoteAssetVolume", totalTradedBaseQuoteAssetVolume)
            .append("statsOpenTime", statsOpenTime)
            .append("statsCloseTime", statsCloseTime)
            .append("firstTradeId", firstTradeId)
            .append("lastTradeId", lastTradeId)
            .append("totalNumberOfTrades", totalNumberOfTrades)
            .toString();
    }
}
