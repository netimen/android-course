package ru.itloft.moneytracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {


    private List<DataInfo> dataInfoList;

    public CardAdapter(List<DataInfo> dataInfoList) {
        this.dataInfoList = dataInfoList;
    }


    @Override
    public int getItemCount() {
        return dataInfoList.size();
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        DataInfo ci = dataInfoList.get(i);
        cardViewHolder.name.setText(ci.name);
        cardViewHolder.date.setText(ci.date);
        cardViewHolder.sum.setText(ci.sum);

    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item_card, viewGroup, false);

        return new CardViewHolder(itemView);
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView date;
        protected TextView sum;


        public CardViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            sum = (TextView) itemView.findViewById(R.id.sum);

        }
    }
}




