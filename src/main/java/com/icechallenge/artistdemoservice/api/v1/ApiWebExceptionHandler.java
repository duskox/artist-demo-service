package com.icechallenge.artistdemoservice.api.v1;

import com.icechallenge.artistdemoservice.api.v1.dtos.ErrorDto;
import com.icechallenge.artistdemoservice.domain.error.ArtistNotFoundException;
import com.icechallenge.artistdemoservice.domain.error.DomainException;
import com.icechallenge.artistdemoservice.domain.error.NoArtistsFoundException;
import com.icechallenge.artistdemoservice.domain.error.TrackNotFoundException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ApiWebExceptionHandler extends DefaultErrorWebExceptionHandler {
    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resources          the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     * @since 2.4.0
     */
    public ApiWebExceptionHandler(ErrorAttributes errorAttributes,
                                  WebProperties.Resources resources,
                                  ErrorProperties errorProperties,
                                  ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        var error = getError(request);

        if (error instanceof ArtistNotFoundException
                || error instanceof NoArtistsFoundException
                || error instanceof TrackNotFoundException) {
            return handleAsNotFound((DomainException) error);
        }

        return super.renderErrorResponse(request);
    }

    private Mono<ServerResponse> handleAsBadRequest(DomainException ex) {
        return ServerResponse
                .status(HttpStatus.BAD_REQUEST)
                .body(BodyInserters
                        .fromValue(ErrorDto
                                .builder()
                                .failureReason(ex.getReason())
                                .details(ex.getDetails())
                                .build()));
    }

    private Mono<ServerResponse> handleAsNotFound(DomainException exception) {
        return ServerResponse
                .status(HttpStatus.NOT_FOUND)
                .body(BodyInserters
                        .fromValue(ErrorDto
                                .builder()
                                .failureReason(exception.getReason())
                                .details(exception.getDetails())
                                .build()));
    }

    private Mono<ServerResponse> handleAsConflictException(DomainException exception) {
        return ServerResponse
                .status(HttpStatus.CONFLICT)
                .body(BodyInserters
                        .fromValue(ErrorDto
                                .builder()
                                .failureReason(exception.getReason())
                                .details(exception.getDetails())
                                .build()));
    }
}
