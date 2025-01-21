package com.something.demo.factory.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.annotation.processing.Generated;
import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "requestType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DatabaseRequest.class, name = "database"),
        @JsonSubTypes.Type(value = RedisRequest.class, name = "redis")
})

public class BaseRequest implements Serializable {
    private String requestId;
    private String requestType;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
