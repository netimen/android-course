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

import java.util.List;

@Table(name = "categories")
public class Category extends Model {
//    @Column(name = "uuid")
//    private int uuid;

    @Column(name = "title")
    private String title;

    private Transaction[] transactions;

    /**
     * required by ActiveAndroid
     */
    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }

    public List<Transaction> items() {
        return getMany(Transaction.class, "Category");
    }
    public static List<Category> getAll() {
        return new Select().from(Category.class).orderBy("title ASC").execute();
    }
}
