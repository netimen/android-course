package ru.itloft.moneytracker.fragments;

import java.util.ArrayList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import ru.itloft.moneytracker.R;

import android.app.Dialog;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@EFragment(R.layout.list_fragment)
public class ListFragment extends Fragment {
    private ArrayList<String> ar = new ArrayList<String>();

    @ViewById(R.id.card_listView)
    ListView listView;

    @StringArrayRes(R.array.category)
    String values[];

    @Click(R.id.button)
    void alert() {
        alertDialog();
    }

    @AfterViews()
    void ready() {
//
//        String s1 = "Еда";
//        String s2 = "Развлечения";
//        String s3 = "Другое";
//        ar.add(s1);
//        ar.add(s2);
//        ar.add(s3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, values);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
    }

    private void alertDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_window);
        TextView textView = (TextView) dialog.findViewById(R.id.title);
        final EditText editText = (EditText) dialog.findViewById(R.id.edittext);
        Button okButton = (Button) dialog.findViewById(R.id.okButton);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);

        textView.setText(getString(R.string.add_category));
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable text = editText.getText();
                if (!TextUtils.isEmpty(text)) {

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
