package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.CandlestickInterval;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Market data stream endpoints examples.
 *
 * It illustrates how to create a stream to obtain updates on market data such as executed trades.
 */
public class MarketDataStreamExample {

  public static void main(String[] args) throws InterruptedException, IOException {
    BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();

    // Listen for aggregated trade events for ETH/BTC
    client.onAggTradeEvent("ethbtc", response -> System.out.println(response));

    // Listen for changes in the order book in ETH/BTC
    client.onDepthEvent("ethbtc", response -> System.out.println(response));

    // Obtain 1m candlesticks in real-time for ETH/BTC
    client.onCandlestickEvent("ethbtc", CandlestickInterval.ONE_MINUTE, response -> System.out.println(response));

    // Listen 1M interval candlestick data of given multiple symbols on singe socket
    client.onCandlestickEvent(new ArrayList<>(Arrays.asList("ltcbtc", "etcbtc")), CandlestickInterval.ONE_MINUTE, System.out::println);

    // Listen aggregated events of given multiple symbols on singe socket
    client.onAggTradeEvent(new ArrayList<>(Arrays.asList("ltcbtc", "etcbtc")), System.out::println);

    // Listen depth events of given multiple symbols on singe socket
    client.onDepthEvent(new ArrayList<>(Arrays.asList("ltcbtc", "etcbtc")), System.out::println);

  }
}
