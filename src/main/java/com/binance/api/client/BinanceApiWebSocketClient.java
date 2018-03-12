package com.binance.api.client;

import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.AllMarketTickersEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.event.CombinedCandleStickEvent;
import com.binance.api.client.domain.event.CombinedAggTradeEvent;
import com.binance.api.client.domain.event.CombinedDepthEvent;

import java.io.Closeable;
import java.util.List;

/**
 * Binance API data streaming façade, supporting streaming of events through web sockets.
 */
public interface BinanceApiWebSocketClient extends Closeable {

    /**
     * Open a new web socket to receive {@link DepthEvent depthEvents} on a callback.
     *
     * @param symbol   the market symbol to subscribe to
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback);

    /**
     * Open a new web socket to receive {@link DepthEvent depthEvents} on a callback.
     *
     * @param symbols   list of the market symbols to subscribe to
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onDepthEvent(List<String> symbols, BinanceApiCallback<CombinedDepthEvent> callback);

    /**
     * Open a new web socket to receive {@link CandlestickEvent candlestickEvents} on a callback.
     *
     * @param symbol   the market symbol to subscribe to
     * @param interval the interval of the candles tick events required
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback);

    /**
     * Open a new web socket to receive {@link CandlestickEvent candlestickEvents} on a callback.
     *
     * @param symbols List of the market symbols to subscribe to
     * @param interval the interval of the candles tick events required
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onCandlestickEvent(List<String> symbols, CandlestickInterval interval, BinanceApiCallback<CombinedCandleStickEvent> callback);

    /**
     * Open a new web socket to receive {@link AggTradeEvent aggTradeEvents} on a callback.
     *
     * @param symbol   the market symbol to subscribe to
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback);

    /**
     * Open a new web socket to receive {@link AggTradeEvent aggTradeEvents} on a callback.
     *
     * @param symbols List of the market symbol to subscribe to
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onAggTradeEvent(List<String> symbols, BinanceApiCallback<CombinedAggTradeEvent> callback);

    /**
     * Open a new web socket to receive {@link UserDataUpdateEvent userDataUpdateEvents} on a callback.
     *
     * @param listenKey the listen key to subscribe to.
     * @param callback  the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback);

    /**
     * Open a new web socket to receive {@link AllMarketTickersEvent allMarketTickersEvents} on a callback.
     *
     * @param callback the callback to call on new events
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    Closeable onAllMarketTickersEvent(BinanceApiCallback<List<AllMarketTickersEvent>> callback);


    void close();
}
