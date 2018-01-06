package com.hatchers.ruralcaravane.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases.ConstructionTable;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTable;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTable;
import com.hatchers.ruralcaravane.PaymentDetails.Databases.PaymentTable;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
        Context context;
    // Database Name
    public static final String DATABASE_NAME = "rural_caravane";

    // Contacts table name

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(KitchenTable.CREATE_KITCHEN_TABLE);
        db.execSQL(CustomerTable.CREATE_CUSTOMER_TABLE);
        db.execSQL(ConstructionTable.CREATE_CONSTRUCTION_TEAM_TABLE);
        db.execSQL(PaymentTable.CREATE_PAYMENT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS "+ CustomerTable.CUSTOMER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ KitchenTable.KITCHEN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ ConstructionTable.CONSTRUCTION_TEAM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ PaymentTable.PAYMENT_TABLE);

        onCreate(db);
    }

}