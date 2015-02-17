package ru.itloft.moneytracker.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ru.itloft.moneytracker.R;


@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {
    public static final String ARG_MENU_INDEX = "index";


    @ViewById(R.id.cardList)
    RecyclerView recList;

    @AfterViews
    void main() {
        int index = getArguments().getInt(ARG_MENU_INDEX);
        switch (index) {
            case 0:
                getActivity().setTitle("Траты");
                Fragment fragment = new CardFragment_();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frag_content_frame, fragment).commit();
                break;

            case 1:
                getActivity().setTitle("Категории");
                Fragment listFragment = new ListFragment_();
                FragmentManager listFragmentManager = getFragmentManager();
                listFragmentManager.beginTransaction().replace(R.id.frag_content_frame, listFragment).commit();
                break;

            case 2:

                getActivity().setTitle("Статистика");

                break;

            default:

                break;

        }
    }
}