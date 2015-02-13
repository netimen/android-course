package ru.itloft.moneytracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ContactViewHolder>{


    private List<DataInfo> dataInfoList;

    public CardAdapter(List<DataInfo> dataInfoList) {
        this.dataInfoList = dataInfoList;
    }


    @Override
    public int getItemCount() {
        return dataInfoList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        DataInfo ci = dataInfoList.get(i);
        contactViewHolder.cardName.setText(ci.name);
        contactViewHolder.cardDate.setText(ci.date);
        contactViewHolder.cardSum.setText(ci.sum);

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item_card, viewGroup, false);

        return new ContactViewHolder(itemView);
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView cardName;
        protected TextView cardDate;
        protected TextView cardSum;
        private Context context;


        public ContactViewHolder(View itemView) {
            super(itemView);

            cardName =  (TextView) itemView.findViewById(R.id.name);
            cardDate = (TextView)  itemView.findViewById(R.id.date);
            cardSum = (TextView)  itemView.findViewById(R.id.sum);

        }
    }
}




