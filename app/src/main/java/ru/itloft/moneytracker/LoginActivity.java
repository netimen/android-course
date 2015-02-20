/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   20.02.15
 */
package ru.itloft.moneytracker;

import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.login)
public class LoginActivity extends ActionBarActivity {
    @ViewById
    Button enter;

    @ViewById
    EditText login, password;

    // TODO make return button switch between edits

    @Click
    void enter() {
        finish();
    }

    @TextChange({R.id.login, R.id.password})
    void credentialsChanged() {
        enter.setEnabled(!TextUtils.isEmpty(login.getText()) && !TextUtils.isEmpty(password.getText()));
    }

}
