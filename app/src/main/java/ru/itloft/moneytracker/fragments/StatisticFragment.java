package ru.itloft.moneytracker.fragments;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ru.itloft.moneytracker.R;


@EFragment(R.layout.statistic_fragment)
public class StatisticFragment extends android.app.Fragment {

    int num1, num2, num3, num4, num5;

    @ViewById(R.id.web)
    WebView webView;

    @AfterViews
    void ready() {

        num1 = 750;
        num2 = 300;
        num3 = 75;
        webView.addJavascriptInterface(new WebAppInterface(), "Android");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");

        webView.loadUrl("file:///android_asset/chart.html");

    }

    private class WebAppInterface {

        @JavascriptInterface
        public int getNum1() {
            return num1;
        }

        @JavascriptInterface
        public int getNum2() {
            return num2;
        }

        @JavascriptInterface
        public int getNum3() {
            return num3;
        }

        @JavascriptInterface
        public int getNum4() {
            return num4;
        }

        @JavascriptInterface
        public int getNum5() {
            return num5;
        }
    }

}
