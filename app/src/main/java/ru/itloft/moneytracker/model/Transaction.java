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
//    @Column(name = "uuid")
//    private int uuid;
    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "trDate")
    private Date trDate;

    /**
     * required by ActiveAndroid
     */
    public Transaction() {

    }

    public Transaction(String comment) {
        trDate = new Date();
        this.comment = comment;
    }

    public static List<Transaction> getAll() {
//        return new Select().from(Transaction.class).orderBy("comment DESC").execute();
        return new Select().from(Transaction.class).execute();
    }
}
