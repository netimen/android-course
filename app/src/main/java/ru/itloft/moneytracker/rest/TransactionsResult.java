/**
 * Copyright (c) 2015 Bookmate.
 * All Rights Reserved.
 *
 * Author: Dmitry Gordeev <netimen@dreamindustries.co>
 * Date:   10.02.15
 */
package ru.itloft.moneytracker.rest;

import ru.itloft.moneytracker.model.Category;

public class TransactionsResult {
    Category[] categories;

    public static final String NAME_PREFIX = "Товар__";
    public static final String DATE_PREFIX = "Дата__ ";
    public static final String SUM_PREFIX = "Сумма__";
    public String name;
    public String date;
    public String sum;
}
