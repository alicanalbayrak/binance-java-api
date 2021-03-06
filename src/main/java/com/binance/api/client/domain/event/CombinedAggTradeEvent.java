package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CombinedAggTradeEvent {

    private String stream;

    private AggTradeEvent data;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public AggTradeEvent getData() {
        return data;
    }

    public void setData(AggTradeEvent data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("stream", stream)
                .append("data", data)
                .toString();
    }
}
