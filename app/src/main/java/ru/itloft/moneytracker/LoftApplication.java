/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   26.02.15
 */
package ru.itloft.moneytracker;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.RestTemplate;

import ru.itloft.moneytracker.rest.AuthenticatorInterceptor_;
import ru.itloft.moneytracker.rest.MessageConverter;
import ru.itloft.moneytracker.rest.RestClient;

@EApplication
public class LoftApplication extends com.activeandroid.app.Application {

    @RestService
    RestClient restClient;

    @AfterInject
    void ready() {
        final RestTemplate restTemplate = restClient.getRestTemplate();
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new MessageConverter()); // TODO examine this stupid bug in AA
        restTemplate.getInterceptors().add(AuthenticatorInterceptor_.getInstance_(this));
    }
}
