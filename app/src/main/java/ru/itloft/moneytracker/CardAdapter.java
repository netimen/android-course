package ru.itloft.moneytracker;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.itloft.moneytracker.model.Transaction;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Transaction> transactions;

    public CardAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        Transaction tr = transactions.get(i);
        String currentDateTimeString = (String) DateFormat.format("dd-MM-yyyy", tr.trDate);

        cardViewHolder.name.setText(tr.comment);
        cardViewHolder.date.setText(currentDateTimeString);
        cardViewHolder.sum.setText(Integer.toString(tr.sum));
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




