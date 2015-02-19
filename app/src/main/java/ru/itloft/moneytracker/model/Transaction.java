/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   10.02.15
 */
package ru.itloft.moneytracker.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

@Table(name = "transactions")
public class Transaction extends Model {
    @Column(name = "category")
    public Category category;
    @Column(name = "uuid")
    private int id;
    @Column(name = "categoryId")
    private int categoryId;
    @Column(name = "comment")
    public String comment;
    @Column(name = "timestamp")
    public Date trDate;
    @Column(name = "sum")
    public int sum;


    /**
     * required by ActiveAndroid
     */
    public Transaction() {

    }

    public Transaction(String comment, int sum, Date date) {
        this.trDate = date;
        this.sum = sum;
        this.comment = comment;
    }

    public Transaction(Category category, String comment) {
        trDate = new Date();
        this.comment = comment;
        this.category = category;
    }

    public static List<Transaction> getAll() {
        return new Select().from(Transaction.class).orderBy("timestamp DESC").execute();
//        return new Select().from(Transaction.class).execute();
    }
}
