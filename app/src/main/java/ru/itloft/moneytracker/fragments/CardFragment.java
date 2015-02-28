package ru.itloft.moneytracker.fragments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ru.itloft.moneytracker.CardAdapter;
import ru.itloft.moneytracker.R;
import ru.itloft.moneytracker.model.Transaction;

import android.app.Dialog;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@EFragment(R.layout.card_fragment)
public class CardFragment extends Fragment {

    CardAdapter cardAdapter;
    List<Transaction> data = new ArrayList<Transaction>();

    @ViewById(R.id.cardList)
    RecyclerView recList;

    @ViewById(R.id.button)
    Button button;

    @Click(R.id.button)
    void alert() {
        alertDialog();
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

        data.add(new Transaction("Телефон", 150, new Date()));
        data.add(new Transaction("Еда", 75, new Date()));
        data.add(new Transaction("Книга Война и Мир", 300, new Date()));
        data.add(new Transaction("Обои", 320, new Date()));
        data.add(new Transaction("Фитнес", 500, new Date()));
        data.add(new Transaction("Джинсы", 2000, new Date()));
        data.add(new Transaction("Проезд", 200, new Date()));

        return data;
    }

    private void alertDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_window_trans);
        TextView textView = (TextView) dialog.findViewById(R.id.title);
        final EditText editText = (EditText) dialog.findViewById(R.id.edittextName);
        final EditText editTextSum = (EditText) dialog.findViewById(R.id.edittextSum);
        Button okButton = (Button) dialog.findViewById(R.id.okButton);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);

        textView.setText(getString(R.string.add_trans));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable text = editText.getText();
                Editable textSum = editTextSum.getText();
                if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(textSum)) {
                    data.add(new Transaction(text.toString(), Integer.parseInt(textSum.toString()), new Date()));
                    cardAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }
}
