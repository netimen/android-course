/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   20.02.15
 */
package ru.itloft.moneytracker;

import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import ru.itloft.moneytracker.auth.SessionManager;
import ru.itloft.moneytracker.rest.RegisterResult;

@EActivity(R.layout.login)
public class LoginActivity extends AuthenticatorActivity {

    @ViewById
    Button enter;

    @ViewById
    EditText login, password;

    @Bean
    SessionManager sessionManager;

    @App
    LoftApplication app;

    @Click
    void enter() {
        enterAsync();
    }

    @TextChange({R.id.login, R.id.password})
    void credentialsChanged() {
        enter.setEnabled(!TextUtils.isEmpty(login.getText()) && !TextUtils.isEmpty(password.getText()));
    }

    @EditorAction(R.id.password)
    void passwordEntered(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE && enter.isEnabled()) enter();
    }

    ///

    @Background
    void enterAsync() { // TODO network error
        RegisterResult result = app.restClient.login(login.getText(), password.getText());
        handleLoginResult(result);
    }

    @UiThread
    void handleLoginResult(RegisterResult result) { // TODO activity death
        if (TextUtils.equals(result.status, "success")) {
            setAccountAuthenticatorResult(sessionManager.open(login.getText().toString(), result.authToken));
            finish();
        } else
            Toast.makeText(this, result.status, Toast.LENGTH_LONG).show();
    }

}
