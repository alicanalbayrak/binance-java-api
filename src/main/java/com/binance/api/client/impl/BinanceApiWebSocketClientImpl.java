package com.binance.api.client.impl;

import java.io.Closeable;
import java.util.List;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.AllMarketTickersEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.CombinedAggTradeEvent;
import com.binance.api.client.domain.event.CombinedCandleStickEvent;
import com.binance.api.client.domain.event.CombinedDepthEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

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

    @Override
    public Closeable onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback) {
        final String channel = String.format("%s@depth", symbol);
        return createNewWebSocket(channel, BinanceApiConstants.WS_API_BASE_URL, new BinanceApiWebSocketListener<>(callback, DepthEvent.class));
    }

    @Override
    public Closeable onDepthEvent(List<String> symbols, BinanceApiCallback<CombinedDepthEvent> callback) {
        String channel = symbols.stream()
                .map(symbol -> String.format("%s@depth", symbol))
                .reduce((s1, s2) -> s1 + "/" + s2)
                .orElseThrow(() -> new IllegalArgumentException("Check symbol list provided to listen DepthEvent"));

        return createNewWebSocket(channel, BinanceApiConstants.WS_API_COMBINE_STREAM_URL,
                new BinanceApiWebSocketListener<>(callback, CombinedDepthEvent.class));
    }

    @Override
    public Closeable onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback) {
        final String channel = String.format("%s@kline_%s", symbol, interval.getIntervalId());
        return createNewWebSocket(channel, BinanceApiConstants.WS_API_BASE_URL, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class));
    }

    @Override
    public Closeable onCandlestickEvent(List<String> symbols, CandlestickInterval interval, BinanceApiCallback<CombinedCandleStickEvent> callback) {
        String channel = symbols.stream()
                .map(symbol -> String.format("%s@kline_%s", symbol, interval.getIntervalId()))
                .reduce((s1, s2) -> s1 + "/" + s2)
                .orElseThrow(() -> new IllegalArgumentException("Check symbol list provided to listen CandlestickEvent"));

        return createNewWebSocket(channel, BinanceApiConstants.WS_API_COMBINE_STREAM_URL,
                new BinanceApiWebSocketListener<>(callback, CombinedCandleStickEvent.class));
    }

    @Override
    public Closeable onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback) {
        final String channel = String.format("%s@aggTrade", symbol);
        return createNewWebSocket(channel, BinanceApiConstants.WS_API_BASE_URL, new BinanceApiWebSocketListener<>(callback, AggTradeEvent.class));
    }

    @Override
    public Closeable onAggTradeEvent(List<String> symbols, BinanceApiCallback<CombinedAggTradeEvent> callback) {
        String channel = symbols.stream()
                .map(symbol -> String.format("%s@aggTrade", symbol))
                .reduce((s1, s2) -> s1 + "/" + s2)
                .orElseThrow(() -> new IllegalArgumentException("Check symbol list provided to listen CandlestickEvent"));

        return createNewWebSocket(channel, BinanceApiConstants.WS_API_COMBINE_STREAM_URL,
                new BinanceApiWebSocketListener<>(callback, CombinedAggTradeEvent.class));
    }

    @Override
    public Closeable onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback) {
        return createNewWebSocket(listenKey, BinanceApiConstants.WS_API_BASE_URL, new BinanceApiWebSocketListener<>(callback, UserDataUpdateEvent.class));
    }

    @Override
    public Closeable onAllMarketTickersEvent(BinanceApiCallback<List<AllMarketTickersEvent>> callback) {
        final String channel = "!ticker@arr";
        return createNewWebSocket(channel, BinanceApiConstants.WS_API_BASE_URL, new BinanceApiWebSocketListener<>(callback));
    }

    @Override
    public void close() {
        client.dispatcher().executorService().shutdown();
    }

    private Closeable createNewWebSocket(String channel, String streamUrl, BinanceApiWebSocketListener<?> listener) {
        String streamingUrl = String.format("%s/%s", streamUrl, channel);
        Request request = new Request.Builder().url(streamingUrl).build();
        final WebSocket webSocket = client.newWebSocket(request, listener);
        return () -> {
            final int code = 1000;
            listener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            listener.onClosed(webSocket, code, null);
        };
    }

}
