/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   16.02.15
 */
package ru.itloft.moneytracker.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

public class MessageConverter extends GsonHttpMessageConverter {
    public MessageConverter() {
        setGson(new GsonBuilder()
//                .registerTypeAdapter(Date.class, new GsonDate.Serializer())
//                .registerTypeAdapter(Date.class, new GsonDate.Deserializer())
//                .registerTypeAdapterFactory(new GsonBaseResourceFactory())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .disableHtmlEscaping()
                .create());
    }

}
