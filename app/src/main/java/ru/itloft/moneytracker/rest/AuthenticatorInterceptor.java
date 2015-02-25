/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   16.02.15
 */
package ru.itloft.moneytracker.rest;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class AuthenticatorInterceptor implements ClientHttpRequestInterceptor {

    /**
     * TODO temp solution
     */
    public static String authToken;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("authToken", authToken);
        return execution.execute(request, data);
    }
}
