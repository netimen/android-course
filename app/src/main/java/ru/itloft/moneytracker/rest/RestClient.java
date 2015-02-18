/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   09.02.15
 */
package ru.itloft.moneytracker.rest;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.web.client.RestTemplate;

@Rest(converters = {MessageConverter.class}, interceptors = {AuthenticatorInterceptor.class}, rootUrl = "http://62.109.17.114/")
public interface RestClient {
    @Get("/transcat")
    @Accept(MediaType.APPLICATION_JSON)
    TransactionsResult getTransactions();

    @Get("/categories")
    @Accept(MediaType.APPLICATION_JSON)
    CategoriesResult getCategories();

    @Post("/categories/add?title={title}")
    @Accept(MediaType.APPLICATION_JSON)
    Result addCategory(String title);

    @Get("/auth?login={login}&password={password}&register=1")
    @Accept(MediaType.APPLICATION_JSON)
    RegisterResult register(String login, String password);

    @Get("/auth?login={login}&password={password}")
    @Accept(MediaType.APPLICATION_JSON)
    RegisterResult login(String login, String password);

    RestTemplate getRestTemplate();

}
