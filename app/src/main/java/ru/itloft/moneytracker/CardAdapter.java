package ru.itloft.moneytracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.itloft.moneytracker.rest.TransactionsResult;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<TransactionsResult> transactionsResults;

    public CardAdapter(List<TransactionsResult> transactionsResults) {
        this.transactionsResults = transactionsResults;
    }


    @Override
    public int getItemCount() {
        return transactionsResults.size();
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        TransactionsResult tr = transactionsResults.get(i);
        //cardViewHolder.name.setText(tr.name);
        //cardViewHolder.date.setText(tr.date);
        //cardViewHolder.sum.setText(tr.sum);
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




