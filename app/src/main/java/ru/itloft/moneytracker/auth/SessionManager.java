/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   17.02.15
 */
package ru.itloft.moneytracker.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SupposeBackground;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.UiThread;

import java.io.IOException;

@EBean(scope = EBean.Scope.Singleton)
public class SessionManager {

    private static final String LOG_TAG = SessionManager.class.getSimpleName();

    public static final String AUTH_ACCOUNT_TYPE = "ru.itloft.moneytracker";
    public static final String AUTH_TOKEN_TYPE_FULL_ACCESS = AUTH_ACCOUNT_TYPE + ".tokenFullAccess";

    @SystemService
    AccountManager accountManager;

    @RootContext
    Context context;

    private String authToken;
    private boolean isOpen;

    public Bundle open(String login, String token) {
        Account account = new Account(login, AUTH_ACCOUNT_TYPE);

        final Bundle result = new Bundle();
        if (accountManager.addAccountExplicitly(account, null, null)) {
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, token);
            accountManager.setAuthToken(account, AUTH_TOKEN_TYPE_FULL_ACCESS, token);

//            ContentResolver.setSyncAutomatically(account, Constants.CONTENT_AUTHORITY, true);

            onSessionOpen(result);
        }

        return result;
    }

    /**
     * must be called from bg thread, because AccountManagerFuture can't be used in main thread
     */
    @Background
    public void login(Activity activity) {
        if (isOpen)
            return;

        if (restore())
            return;

        try {
            AccountManagerFuture<Bundle> future = accountManager.addAccount(AUTH_ACCOUNT_TYPE, AUTH_TOKEN_TYPE_FULL_ACCESS, null, null, activity, null, null);
            future.getResult();
        } catch (OperationCanceledException | AuthenticatorException | IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "login(), Failed: " + e);
        }
    }

    public void logout() {
        for (Account account : accountManager.getAccountsByType(AUTH_ACCOUNT_TYPE))
            accountManager.removeAccount(account, null, null);

        onSessionClose();
    }

    /**
     * must be called from bg thread, because AccountManagerFuture can't be used in main thread
     */
    @SupposeBackground
    boolean restore() {
        try {
            android.accounts.Account[] availableAccounts = accountManager.getAccountsByType(AUTH_ACCOUNT_TYPE);
            Log.d(LOG_TAG, "restore(), Available accounts: " + availableAccounts.length);

            if (availableAccounts.length == 0)
                return false;

            AccountManagerFuture<Bundle> future = accountManager.getAuthToken(availableAccounts[0], AUTH_TOKEN_TYPE_FULL_ACCESS, null, false, null, null);
            onSessionOpen(future.getResult());
            return true;
        } catch (OperationCanceledException | IOException | AuthenticatorException e) {
            e.printStackTrace();
            return false;
        }
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    void onSessionOpen(Bundle bundle) {
        authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
        isOpen = true;

//        String storedToken = session.authToken().get();
//        if (!TextUtils.equals(storedToken, mToken))
//            session.authToken().put(mToken);

//        if (!mSynced)
//            sync(true);
//
//        LocalBroadcastManager.getInstance(context)
//                .sendBroadcast(new Intent(SESSION_OPENED_BROADCAST));
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    void onSessionClose() {
        isOpen = false;
        authToken = null;
//        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(SESSION_CLOSED_BROADCAST));
    }

}
