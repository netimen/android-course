/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   24.02.15
 */
package ru.itloft.moneytracker.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ru.itloft.moneytracker.LoginActivity_;

public class Authenticator extends AbstractAccountAuthenticator {
    private final Context context;

    public Authenticator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {

        final Bundle bundle = new Bundle();
        Account[] accounts = AccountManager.get(context).getAccountsByType(SessionManager.AUTH_ACCOUNT_TYPE);
        if (accounts.length > 0) {
            accounts.toString();
//            bundle.putInt(AccountManager.KEY_ERROR_CODE, SyncStateContract.Constants.ERROR_CODE_ADD_ACCOUNT_FAILED);
//            bundle.putString(AccountManager.KEY_ERROR_MESSAGE, Constants.ERROR_MESSAGE_ADD_ACCOUNT_FAILED);
        } else {
            Intent intent;
            intent = LoginActivity_.intent(context).get();

            intent.putExtra(SessionManager.AUTH_ACCOUNT_TYPE, accountType);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            bundle.putAll(options);
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        }
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}