package com.hatchers.ruralcaravane.customer_registration.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hatchers.ruralcaravane.database.DatabaseHandler;

import java.util.ArrayList;


public class CustomerTableHelper {

    public static boolean insertCustomerData(Context context, CustomerTable customer_table)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

                values.put(CustomerTable.CUSTOMER_ID, customer_table.getCustomerIdValue());
                values.put(CustomerTable.CUSTOMER_MOBILENO, customer_table.getCustomerMobilenoValue());
                values.put(CustomerTable.CUSTOMER_AGE, customer_table.getCustomerAgeValue());
                values.put(CustomerTable.CUSTOMER_NAME,customer_table.getCustomerNameValue());
                values.put(CustomerTable.VILLAGE_NAME,customer_table.getVillageNameValue());
                values.put(CustomerTable.CUSTOMER_ADDRESS,customer_table.getCustomerAddressValue());
                values.put(CustomerTable.CUSTOMER_GENDER,customer_table.getCustomerGenderValue());
                values.put(CustomerTable.UPLOAD_STATUS,customer_table.getUpload_statusValue());
                values.put(CustomerTable.UNIQUE_ID,customer_table.getUniqueIdValue());
                values.put(CustomerTable.IMAGE_PATH,customer_table.getImagePathValue());
                values.put(CustomerTable.AADHAR_ID,customer_table.getAadharIdValue());
                values.put(CustomerTable.VILLAGE_ID,customer_table.getVillageIdValue());
                values.put(CustomerTable.ADDED_DATE,customer_table.getAddedDateValue());
                values.put(CustomerTable.CITY_ID,customer_table.getCityId());

            if (db.insert(CustomerTable.CUSTOMER_TABLE, null, values) > 0)
            {

                //Toast.makeText(context,"Customer data inserted",Toast.LENGTH_LONG).show();
                db.close();
                return true;
            }
            else
            {

                db.close();
                return false;

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean updateCustomerData(Context context, CustomerTable customer_table)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(CustomerTable.CUSTOMER_ID, Integer.parseInt(customer_table.getCustomerIdValue()));
            values.put(CustomerTable.CUSTOMER_MOBILENO, Integer.parseInt(customer_table.getCustomerMobilenoValue()));
            values.put(CustomerTable.CUSTOMER_AGE, Integer.parseInt(customer_table.getCustomerAgeValue()));
            values.put(CustomerTable.CUSTOMER_NAME,customer_table.getCustomerNameValue());
            values.put(CustomerTable.CUSTOMER_ADDRESS,customer_table.getCustomerAddressValue());
            values.put(CustomerTable.VILLAGE_NAME,customer_table.getVillageNameValue());
            values.put(CustomerTable.CUSTOMER_ADDRESS,customer_table.getCustomerAddressValue());
            values.put(CustomerTable.CUSTOMER_GENDER,customer_table.getCustomerGenderValue());
            values.put(CustomerTable.UPLOAD_STATUS,customer_table.getUpload_statusValue());
            values.put(CustomerTable.UNIQUE_ID,customer_table.getUniqueIdValue());
            values.put(CustomerTable.IMAGE_PATH,customer_table.getImagePathValue());
            values.put(CustomerTable.AADHAR_ID,customer_table.getAadharIdValue());
            values.put(CustomerTable.VILLAGE_ID,customer_table.getVillageIdValue());
            values.put(CustomerTable.ADDED_DATE,customer_table.getAddedDateValue());
            values.put(CustomerTable.CITY_ID,customer_table.getCityId());

            // upadating Row
            if(db.update(CustomerTable.CUSTOMER_TABLE, values, CustomerTable.CUSTOMER_ID+"="+customer_table.getCustomerIdValue(), null)>0)
            {
                Toast.makeText(context,"Customer data updated",Toast.LENGTH_LONG).show();
                db.close();
                return true;
            }
            else
            {
                db.close();
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public static CustomerTable getCustomerData(Context context)
    {
        SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CustomerTable.CUSTOMER_TABLE+" WHERE "+ CustomerTable.CUSTOMER_ID,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
               CustomerTable customer = new CustomerTable();

                customer.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_ID)));
                customer.setCustomerNameValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_NAME)));
                customer.setCustomerAddressValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_ADDRESS)));
                customer.setCustomerMobilenoValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_MOBILENO)));
                customer.setCustomerAgeValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_AGE)));
                customer.setCustomerGenderValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_GENDER)));
                customer.setVillageNameValue(cursor.getString(cursor.getColumnIndex(CustomerTable.VILLAGE_NAME)));
                customer.setUpload_statusValue(cursor.getString(cursor.getColumnIndex(CustomerTable.UPLOAD_STATUS)));
                customer.setUniqueIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.UNIQUE_ID)));
                customer.setImagePathValue(cursor.getString(cursor.getColumnIndex(CustomerTable.IMAGE_PATH)));
                customer.setAadharIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.AADHAR_ID)));
                customer.setVillageIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.VILLAGE_ID)));
                customer.setAddedDateValue(cursor.getString(cursor.getColumnIndex(CustomerTable.ADDED_DATE)));
                customer.setCityId(cursor.getString(cursor.getColumnIndex(CustomerTable.CITY_ID)));

                cursor.moveToNext();
                return  customer;
            }
            return null;
        }
        catch (Exception e)

        {
            return null;
        }
    }


    public static ArrayList<CustomerTable> getCustomerdataList(Context context)
    {
        ArrayList<CustomerTable> customerTableArrayList = new ArrayList<CustomerTable>();
        SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ CustomerTable.CUSTOMER_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                CustomerTable customer = new CustomerTable();

                customer.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_ID)));
                customer.setCustomerNameValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_NAME)));
                customer.setCustomerAddressValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_ADDRESS)));
                customer.setCustomerMobilenoValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_MOBILENO)));
                customer.setCustomerAgeValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_AGE)));
                customer.setCustomerGenderValue(cursor.getString(cursor.getColumnIndex(CustomerTable.CUSTOMER_GENDER)));
                customer.setVillageNameValue(cursor.getString(cursor.getColumnIndex(CustomerTable.VILLAGE_NAME)));
                customer.setUpload_statusValue(cursor.getString(cursor.getColumnIndex(CustomerTable.UPLOAD_STATUS)));
                customer.setUniqueIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.UNIQUE_ID)));
                customer.setImagePathValue(cursor.getString(cursor.getColumnIndex(CustomerTable.IMAGE_PATH)));
                customer.setAadharIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.AADHAR_ID)));
                customer.setVillageIdValue(cursor.getString(cursor.getColumnIndex(CustomerTable.VILLAGE_ID)));
                customer.setAddedDateValue(cursor.getString(cursor.getColumnIndex(CustomerTable.ADDED_DATE)));
                customer.setCityId(cursor.getString(cursor.getColumnIndex(CustomerTable.CITY_ID)));

                customerTableArrayList.add(customer);
                cursor.moveToNext();
            }
            return customerTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteCustomerDataById(Context context, String customer_id)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + CustomerTable.CUSTOMER_TABLE +
                    " WHERE " + CustomerTable.CUSTOMER_ID + " = '" + customer_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Customer Data Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteCustomerData(Context context)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + CustomerTable.CUSTOMER_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Answer data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
