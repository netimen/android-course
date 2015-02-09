/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   09.02.15
 */
package ru.itloft.moneytracker;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(converters = {GsonHttpMessageConverter.class}, rootUrl = "http://62.109.17.114/")
public interface RestClient {
    @Get("/transcat")
    @Accept(MediaType.APPLICATION_JSON)
    Object getTransactions();

    @Get("/auth?login={login}&password={password}&register=1")
    @Accept(MediaType.APPLICATION_JSON)
    RegisterResult register(String login, String password);
}
