package com.icechallenge.artistdemoservice.domain.error;

import java.util.Map;

public class UnknownException extends DomainException {
    public UnknownException(String reason, Map<String, String> details) {
        super(reason, details);
    }
}
