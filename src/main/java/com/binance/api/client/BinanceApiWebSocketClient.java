package com.binance.api.client;

import java.util.List;
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
 * Binance API data streaming façade, supporting streaming of events through web sockets.
 */
public interface BinanceApiWebSocketClient {

  void onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback);

  void onDepthEvent(List<String> symbols, BinanceApiCallback<CombinedDepthEvent> callback);

  void onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback);

  void onCandlestickEvent(List<String> symbols, CandlestickInterval interval, BinanceApiCallback<CombinedCandleStickEvent> callback);

  void onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback);

  void onAggTradeEvent(List<String> symbols, BinanceApiCallback<CombinedAggTradeEvent> callback);

  void onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback);

  void onAllMarketTickersEvent(BinanceApiCallback<List<AllMarketTickersEvent>> callback);
}
