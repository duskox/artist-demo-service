package com.icechallenge.artistdemoservice.domain.error;

import java.util.Map;

public abstract class DomainException extends RuntimeException {

    private final String reason;
    private final Map<String, String> details;

    public DomainException(String reason, Map<String, String> details) {
        this.reason = reason;
        this.details = details;
    }

    public String getReason() {
        return reason;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
