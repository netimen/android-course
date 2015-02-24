/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   24.02.15
 */
package ru.itloft.moneytracker.auth;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new Authenticator(this).getIBinder();
    }
}
