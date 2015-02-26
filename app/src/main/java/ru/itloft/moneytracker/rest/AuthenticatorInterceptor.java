/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   16.02.15
 */
package ru.itloft.moneytracker.rest;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import ru.itloft.moneytracker.auth.SessionManager;

@EBean
public class AuthenticatorInterceptor implements ClientHttpRequestInterceptor {

    @Bean
    SessionManager sessionManager;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("authToken", sessionManager.getAuthToken());
        return execution.execute(request, data);
    }
}
