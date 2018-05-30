package com.huseyin.kubernetes.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 656711119945L;

    private String userMessage;

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
