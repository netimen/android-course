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
import java.util.List;

import ru.itloft.moneytracker.CardAdapter;
import ru.itloft.moneytracker.R;
import ru.itloft.moneytracker.rest.TransactionsResult;


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
        recList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);
        cardAdapter = new CardAdapter(TransactionList(100));
        recList.setAdapter(cardAdapter);
    }

    private List<TransactionsResult> TransactionList(int size) {

        List<TransactionsResult> result = new ArrayList<TransactionsResult>();
        for (int i = 1; i <= size; i++) {
            TransactionsResult ci = new TransactionsResult();
            ci.name = TransactionsResult.NAME_PREFIX + i;
            ci.date = TransactionsResult.DATE_PREFIX + i + "/02/2015";
            ci.sum = TransactionsResult.SUM_PREFIX + (i * 100);

            result.add(ci);

        }

        return result;
    }
}
