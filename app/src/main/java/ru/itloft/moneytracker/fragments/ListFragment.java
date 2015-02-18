package ru.itloft.moneytracker.fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import ru.itloft.moneytracker.R;


@EFragment(R.layout.list_fragment)
public class ListFragment extends Fragment {

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item, values);
        listView.setAdapter(adapter);
    }

    private void alertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(getString(R.string.add_the_category));
        final EditText input = new EditText(getActivity());
        alert.setView(input);
        alert.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                Toast.makeText(getActivity(), value,
                        Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton(getString(R.string.CANCEL), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }

}
