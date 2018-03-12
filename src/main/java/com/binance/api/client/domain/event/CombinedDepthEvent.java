package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CombinedDepthEvent {

    private String stream;

    private DepthEvent data;

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public DepthEvent getData() {
        return data;
    }

    public void setData(DepthEvent data) {
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
