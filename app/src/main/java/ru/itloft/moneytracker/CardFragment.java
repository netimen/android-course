package ru.itloft.moneytracker;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CardFragment extends android.app.Fragment {
    public static final String ARG_MENU_INDEX = "index";

    public CardFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        int index = getArguments().getInt(ARG_MENU_INDEX);
        switch (index){
            case 0:

                getActivity().setTitle("Траты");
                RecyclerView recList = (RecyclerView) view.findViewById(R.id.cardList);
                recList.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recList.setLayoutManager(linearLayoutManager);

                CardAdapter cardAdapter = new CardAdapter(ProductList(100));
                recList.setAdapter(cardAdapter);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Clicked From Траты",Toast.LENGTH_SHORT).show();
                    }
                });




                break;

            case 1:

                getActivity().setTitle("Категории");
                recList = (RecyclerView) view.findViewById(R.id.cardList);
                recList.setHasFixedSize(true);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recList.setLayoutManager(linearLayoutManager);

                cardAdapter = new CardAdapter(CathegoryList(5));
                recList.setAdapter(cardAdapter);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Clicked From Катгории",Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case 2:

                getActivity().setTitle("Статистика");
                recList = (RecyclerView) view.findViewById(R.id.cardList);
                recList.setHasFixedSize(true);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recList.setLayoutManager(linearLayoutManager);

                cardAdapter = new CardAdapter(Statistic(15));
                recList.setAdapter(cardAdapter);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"Clicked From Статистика",Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            default:

                break;

        }

        return view;
    }


    private List<DataInfo> ProductList(int size) {

        List<DataInfo> result = new ArrayList<DataInfo>();
        for (int i=1; i <= size; i++) {
            DataInfo ci = new DataInfo();
            ci.name = DataInfo.NAME_PREFIX + i;
            ci.date = DataInfo.DATE_PREFIX + i + "/02/2015";
            ci.sum = DataInfo.SUM_PREFIX + (i*100);

            result.add(ci);

        }

        return result;
    }

    private List<DataInfo> CathegoryList(int size) {

        List<DataInfo> result = new ArrayList<DataInfo>();
        for (int i=1; i <= size; i++) {
            DataInfo ci = new DataInfo();
            ci.name = DataInfo.CATHEGORY_NAME_PREFIX + i;
            ci.date = DataInfo.CATHEGORY_DATE_PREFIX + i + "/02/2015";
            ci.sum = DataInfo.CATHEGORY_SUM_PREFIX + (i*100);

            result.add(ci);

        }

        return result;
    }

    private List<DataInfo> Statistic(int size) {

        List<DataInfo> result = new ArrayList<DataInfo>();
        for (int i=1; i <= size; i++) {
            DataInfo ci = new DataInfo();
            ci.name = DataInfo.STATISTIC_NAME_PREFIX + i;
            ci.date = DataInfo.STATISTIC_PREFIX + i + "/02/2015";
            ci.sum = DataInfo.STATISTIC_SUM_PREFIX + (i*100);

            result.add(ci);

        }

        return result;
    }

}
