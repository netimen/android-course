package ru.itloft.moneytracker.fragments;


import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.itloft.moneytracker.CardAdapter;
import ru.itloft.moneytracker.R;
import ru.itloft.moneytracker.model.Transaction;


@EFragment(R.layout.card_fragment)
public class CardFragment extends Fragment {

    CardAdapter cardAdapter;

    @ViewById(R.id.cardList)
    RecyclerView recList;

    @ViewById(R.id.button)
    Button button;

    @Click(R.id.button)
    void alert() {
        Toast.makeText(getActivity(), getString(R.string.transaction), Toast.LENGTH_LONG).show();
    }

    @AfterViews
    void main() {
        List<Transaction> data = getTestData();
        recList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        cardAdapter = new CardAdapter(data);
        recList.setAdapter(cardAdapter);
    }

    private List<Transaction> getTestData() {
        List<Transaction> data = new ArrayList<Transaction>();
        data.add(new Transaction("Телефон", 150, new Date()));
        data.add(new Transaction("Еда", 75, new Date()));
        data.add(new Transaction("Книга Война и Мир", 300, new Date()));
        data.add(new Transaction("Телефон", 150, new Date()));
        data.add(new Transaction("Телефон", 150, new Date()));
        data.add(new Transaction("Телефон", 150, new Date()));
        data.add(new Transaction("Телефон", 150, new Date()));

        return data;
    }
}
