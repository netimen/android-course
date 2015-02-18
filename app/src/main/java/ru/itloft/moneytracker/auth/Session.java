/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   17.02.15
 */
package ru.itloft.moneytracker.auth;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SuppressWarnings("UnusedDeclaration")
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Session {

    String authToken();

    String user();

//    @DefaultLong(0)
//    long documentsSyncedTo();
//
//    @DefaultLong(0)
//    long shelvesSyncedTo();
//
//    @DefaultLong(0)
//    long markersSyncedTo();
//
//    @DefaultBoolean(false)
//    public boolean firstDocumentSyncFinished();

}
