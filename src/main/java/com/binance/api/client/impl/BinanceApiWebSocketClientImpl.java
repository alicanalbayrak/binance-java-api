package com.binance.api.client.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.AllMarketTickersEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.event.CombinedCandleStickEvent;
import com.binance.api.client.domain.event.CombinedAggTradeEvent;
import com.binance.api.client.domain.event.CombinedDepthEvent;

/**
 * Binance API WebSocket client implementation using OkHttp.
 */
public class BinanceApiWebSocketClientImpl implements BinanceApiWebSocketClient, Closeable {

  private OkHttpClient client;

  public BinanceApiWebSocketClientImpl() {
    Dispatcher d = new Dispatcher();
    d.setMaxRequestsPerHost(100);
    this.client = new OkHttpClient.Builder().dispatcher(d).build();
  }

  public void onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback) {
    final String channel = String.format("%s@depth", symbol);
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, DepthEvent.class));
  }

  @Override
  public void onDepthEvent(List<String> symbols, BinanceApiCallback<CombinedDepthEvent> callback) {
    symbols.stream()
            .map(symbol -> String.format("%s@depth", symbol))
            .reduce((s1, s2) -> s1 + "/" + s2)
            .ifPresent(channel -> createNewWebSocket(channel, BinanceApiConstants.WS_API_COMBINE_STREAM_URL,
                    new BinanceApiWebSocketListener<>(callback, CombinedDepthEvent.class)));
  }

  @Override
  public void onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback) {
    final String channel = String.format("%s@kline_%s", symbol, interval.getIntervalId());
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class));
  }

    @Override
    public void onCandlestickEvent(List<String> symbols, CandlestickInterval interval,BinanceApiCallback<CombinedCandleStickEvent> callback) {
        symbols.stream()
            .map(symbol -> String.format("%s@kline_%s", symbol, interval.getIntervalId()))
            .reduce((s1, s2) -> s1 + "/" + s2)
            .ifPresent(channel -> createNewWebSocket(channel, BinanceApiConstants.WS_API_COMBINE_STREAM_URL,
                    new BinanceApiWebSocketListener<>(callback, CombinedCandleStickEvent.class)));
    }

    public void onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback) {
    final String channel = String.format("%s@aggTrade", symbol);
    createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, AggTradeEvent.class));
  }

  @Override
  public void onAggTradeEvent(List<String> symbols, BinanceApiCallback<CombinedAggTradeEvent> callback) {
    symbols.stream()
            .map(symbol -> String.format("%s@aggTrade", symbol))
            .reduce((s1, s2) -> s1 + "/" + s2)
            .ifPresent(channel -> createNewWebSocket(channel, BinanceApiConstants.WS_API_COMBINE_STREAM_URL,
                    new BinanceApiWebSocketListener<>(callback, CombinedAggTradeEvent.class)));
  }

  public void onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback) {
    createNewWebSocket(listenKey, new BinanceApiWebSocketListener<>(callback, UserDataUpdateEvent.class));
  }

  public void onAllMarketTickersEvent(BinanceApiCallback<List<AllMarketTickersEvent>> callback) {
    final String channel = "!ticker@arr";
    createNewWebSocket(channel, new BinanceApiWebSocketListener<List<AllMarketTickersEvent>>(callback));
  }


  private void createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
    String streamingUrl = String.format("%s/%s", BinanceApiConstants.WS_API_BASE_URL, channel);
    Request request = new Request.Builder().url(streamingUrl).build();
    client.newWebSocket(request, listener);
  }

  private void createNewWebSocket(String channel, String streamUrl, BinanceApiWebSocketListener<?> listener) {
    String streamingUrl = String.format("%s/%s", streamUrl, channel);
    Request request = new Request.Builder().url(streamingUrl).build();
    client.newWebSocket(request, listener);
  }

  @Override
  public void close() throws IOException {
    client.dispatcher().executorService().shutdown();
  }

}
