package com.binance.api.client.domain.event;

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
        return new ToStringBuilder(this)
                .append("stream", stream)
                .append("data", data)
                .toString();
    }
}
