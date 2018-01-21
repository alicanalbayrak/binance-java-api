package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.event.Ticker24HrEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.Closeable;
import java.io.IOException;

/**
 * Binance API WebSocket client implementation using OkHttp.
 */
public class BinanceApiWebSocketClientImpl implements BinanceApiWebSocketClient, Closeable {

  private OkHttpClient client;

  public BinanceApiWebSocketClientImpl() {
    this.client = new OkHttpClient();
  }

  public void onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback) {
    final String channel = String.format("%s@depth", symbol);
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, DepthEvent.class));
  }

  @Override
  public void onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback) {
    final String channel = String.format("%s@kline_%s", symbol, interval.getIntervalId());
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class));
  }

    @Override
    public void onCandlestickEvent(List<String> symbols, CandlestickInterval interval,
        BinanceApiCallback<CandlestickEvent> callback) {
        symbols.stream()
            .map(symbol -> String.format("%s@kline_%s", symbol, interval.getIntervalId()))
            .reduce((s1, s2) -> s1 + "/" + s2)
            .ifPresent(channel -> createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class)));
    }

    public void onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback) {
    final String channel = String.format("%s@aggTrade", symbol);
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, AggTradeEvent.class));
  }

  public void onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback) {
    createNewWebSocket(listenKey, new BinanceApiWebSocketListener<>(callback, UserDataUpdateEvent.class));
  }

  @Override
  public void onTicker24HrEvent(String symbol, BinanceApiCallback<Ticker24HrEvent> callback) {
    final String channel = String.format("%s@ticker", symbol);
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, Ticker24HrEvent.class));
  }

  private void createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
    String streamingUrl = String.format("%s/%s", BinanceApiConstants.WS_API_BASE_URL, channel);
    Request request = new Request.Builder().url(streamingUrl).build();
    client.newWebSocket(request, listener);
  }

  @Override
  public void close() throws IOException {
    client.dispatcher().executorService().shutdown();
  }
}
