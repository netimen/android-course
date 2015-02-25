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
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.SystemService;

@EBean(scope = EBean.Scope.Singleton)
public class SessionManager {

    private static final String LOG_TAG = SessionManager.class.getSimpleName();

    public static final String AUTH_ACCOUNT_TYPE = "ru.itloft.moneytracker";
    public static final String AUTH_TOKEN_TYPE_FULL_ACCESS = AUTH_ACCOUNT_TYPE + ".tokenFullAccess";

    @SystemService
    AccountManager accountManager;

    @RootContext
    Context context;
//    @Pref
//    Session_ session;

    private String mToken;
    private boolean mIsOpen;

    @Background
    public void restore() {
        synchronized (this) { // Android Annotations don't allow to write like this: public synchronized void restore()
//            if (blockingRestore()) {
//                onSessionOpen();
//            } else {
//                onSessionClose();
//            }
        }
    }
    /**
     * @return if session is opened (if user is logged in)
     */
    public boolean blockingRestore() {
        if (mIsOpen) {
            return true;
        }

        try {
            android.accounts.Account[] availableAccounts = accountManager.getAccountsByType(AUTH_ACCOUNT_TYPE);
            Log.d(LOG_TAG, "restore(), Available accounts: " + availableAccounts.length);

            if (availableAccounts.length == 0)
                throw new Exception("No account");

            Account account = availableAccounts[0];

            Log.d(LOG_TAG, "restore(), Check account: " + account);
            AccountManagerFuture<Bundle> future = accountManager.getAuthToken(availableAccounts[0], AUTH_TOKEN_TYPE_FULL_ACCESS, null, false, null, null);
            Bundle bundle = future.getResult();
            String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);

            if (authToken == null)
                throw new Exception("No auth token");

            return openSessionWithBundle(bundle);
        } catch (Exception e) {
            return false;
        }
    }

    public Bundle open(String login, String token) {
        Account account = new Account(login, AUTH_ACCOUNT_TYPE);

        Bundle userData = new Bundle();
        final Bundle result = new Bundle();
        if (accountManager.addAccountExplicitly(account, null, userData)) {
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, token);
            accountManager.setAuthToken(account, AUTH_TOKEN_TYPE_FULL_ACCESS, token);

            openSessionWithBundle(result);
//            ContentResolver.setSyncAutomatically(account, Constants.CONTENT_AUTHORITY, true);

//            onSessionOpen();
        } else {
//            result.putString(AccountManager.KEY_ERROR_MESSAGE, context.getString(R.string.account_already_exists));
        }

        return result;
    }
    boolean openSessionWithBundle(Bundle bundle) {
        mToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
        mIsOpen = true;

//        String storedToken = session.authToken().get();
//        if (!TextUtils.equals(storedToken, mToken))
//            session.authToken().put(mToken);

        return true;
    }

    @Background
    public void login(Activity activity) {
        if (mIsOpen)
            return;

        try {
            AccountManagerFuture<Bundle> future = accountManager.addAccount(AUTH_ACCOUNT_TYPE, AUTH_TOKEN_TYPE_FULL_ACCESS, null, null, activity, null, null);
            future.getResult();
        } catch (Exception e) {
            Log.d(LOG_TAG, "login(), Failed: " + e);
        }
    }

    public void logout() {
        Account[] accounts = accountManager.getAccountsByType(AUTH_ACCOUNT_TYPE);
        for (Account account : accounts) {
            Log.d(LOG_TAG, "removeAccounts(), Will remove account: " + account);
            accountManager.removeAccount(account, null, null);
        }

//        onSessionClose();
    }
}
