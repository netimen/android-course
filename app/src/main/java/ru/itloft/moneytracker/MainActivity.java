package ru.itloft.moneytracker;


import android.app.Fragment;
import android.app.FragmentManager;
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
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import ru.itloft.moneytracker.fragments.MainFragment;
import ru.itloft.moneytracker.fragments.MainFragment_;
import ru.itloft.moneytracker.model.Category;
import ru.itloft.moneytracker.model.Transaction;
import ru.itloft.moneytracker.rest.MessageConverter;
import ru.itloft.moneytracker.rest.RegisterResult;
import ru.itloft.moneytracker.rest.RestClient;


@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {


    @RestService
    RestClient restClient;
    @ViewById(R.id.left_drawer)
    ListView leftDrawerList;
    @ViewById(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @StringArrayRes(R.array.screen_array)
    String[] leftSliderData;


    private ActionBarDrawerToggle drawerToggle;
    private ArrayAdapter<String> navigationDrawerAdapter;


    @AfterViews
    void ready() {

        testMethodForPlayingWithRestAndDB();

        navigationDrawerAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.drawer_list_item, leftSliderData);
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


    @Background
    void testMethodForPlayingWithRestAndDB() {
        final RestTemplate restTemplate = restClient.getRestTemplate();
        restTemplate.getMessageConverters().clear();
        restTemplate.getMessageConverters().add(new MessageConverter());

        RegisterResult result = restClient.login("aaaa", "aaaa");
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
//        TransactionsResult transactionsResult = restClient.getTransactions();
//        transactionsResult.toString();
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


        Fragment fragment = new MainFragment_();
        Bundle args = new Bundle();
        args.putInt(MainFragment.ARG_MENU_INDEX, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


        leftDrawerList.setItemChecked(position, true);
        setTitle(leftSliderData[position]);
        drawerLayout.closeDrawer(leftDrawerList);
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




