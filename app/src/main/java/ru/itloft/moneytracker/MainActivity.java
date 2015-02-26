package ru.itloft.moneytracker;


import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.List;

import ru.itloft.moneytracker.auth.SessionManager;
import ru.itloft.moneytracker.fragments.CardFragment_;
import ru.itloft.moneytracker.fragments.ListFragment_;
import ru.itloft.moneytracker.model.Category;
import ru.itloft.moneytracker.model.Transaction;
import ru.itloft.moneytracker.rest.Result;
import ru.itloft.moneytracker.rest.TransactionsResult;


@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    private ActionBarDrawerToggle drawerToggle;

    @App
    LoftApplication app;

    @ViewById(R.id.left_drawer)
    ListView leftDrawerList;

    @ViewById(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @StringArrayRes(R.array.screen_array)
    String[] leftSliderData;

    @Bean
    SessionManager sessionManager;

    @AfterViews
    void ready() {
        ArrayAdapter<String> navigationDrawerAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.drawer_list_item, leftSliderData);
        leftDrawerList.setAdapter(navigationDrawerAdapter);
        leftDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        selectItem(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionManager.login(this);
    }

    @Background
    void testMethodForPlayingWithRestAndDB() {

        Category c = new Category("some stuff");
        c.save();
        final List<Category> categories = Category.getAll();
        categories.toString();
        Transaction t = new Transaction(c, "1111");
        t.save();

        Transaction tt = new Transaction(c, "2111");
        tt.save();
        final List<Transaction> transactions = Transaction.getAll();
        transactions.toString();
        final List<Transaction> items = c.items();
        items.toString();
        final Result ccc = app.restClient.addCategory("ccc");
        TransactionsResult transactionsResult = app.restClient.getTransactions();
        transactionsResult.toString();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItem(int position) {
        switch (position) {
            case 0:
                chooseSection(R.string.transaction, CardFragment_.builder().build());
                break;
            case 1:
                chooseSection(R.string.category, ListFragment_.builder().build());
                break;
        }
        leftDrawerList.setItemChecked(position, true);
        setTitle(leftSliderData[position]);
        drawerLayout.closeDrawer(leftDrawerList);
    }

    private void chooseSection(int titleId, Fragment fragment) {
        setTitle(getString(titleId));
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);

        super.setTitle(title);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}




