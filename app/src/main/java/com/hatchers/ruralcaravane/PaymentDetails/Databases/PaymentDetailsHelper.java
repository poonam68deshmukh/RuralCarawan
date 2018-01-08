package com.hatchers.ruralcaravane.PaymentDetails.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hatchers.ruralcaravane.Databases.DatabaseHandler;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.KitchenTable;

import java.util.ArrayList;

/**
 * Created by Nikam on 31/12/2017.
 */
public class PaymentDetailsHelper {

    public static boolean insertPaymentDeatilsData(Context context, PaymentTable paymentTable)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(PaymentTable.PAYMENT_ID,paymentTable.getPayment_idValue());
            values.put(PaymentTable.PAYMENT_AMOUNT,paymentTable.getPayment_amountValue());
            values.put(PaymentTable.ADVANCE_AMOUNT,paymentTable.getAdvance_amountValue());
            values.put(PaymentTable.REMAINING_AMOUNT,paymentTable.getReamaining_amountValue());
            values.put(PaymentTable.UPLOAD_STATUS,paymentTable.getUpload_statusValue());
        //    values.put(PaymentTable.IMAGE_PATH,paymentTable.getImagePathValue());
            values.put(PaymentTable.CUSTOMER_ID,paymentTable.getCustomerIdValue());
         //   values.put(PaymentTable.CUSTOMER_NAME,paymentTable.getCustomerName());

            if (db.insert(PaymentTable.PAYMENT_TABLE, null, values) > 0)
            {
                //Toast.makeText(context,"Payment details inserted",Toast.LENGTH_LONG).show();
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

    public static boolean updatePaymentDeatilsData(Context context, PaymentTable paymentTable)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(PaymentTable.PAYMENT_ID,paymentTable.getPayment_idValue());
            values.put(PaymentTable.PAYMENT_AMOUNT,paymentTable.getPayment_amountValue());
            values.put(PaymentTable.ADVANCE_AMOUNT,paymentTable.getAdvance_amountValue());
            values.put(PaymentTable.REMAINING_AMOUNT,paymentTable.getReamaining_amountValue());
            values.put(PaymentTable.UPLOAD_STATUS,paymentTable.getUpload_statusValue());
            //values.put(PaymentTable.IMAGE_PATH,paymentTable.getImagePathValue());
            values.put(PaymentTable.CUSTOMER_ID,paymentTable.getCustomerIdValue());
           /// values.put(PaymentTable.CUSTOMER_NAME,paymentTable.getCustomerName());

            // upadating Row
            if(db.update(PaymentTable.PAYMENT_TABLE, values, PaymentTable.PAYMENT_ID+"="+paymentTable.getPayment_idValue(), null)>0)
            {
                Toast.makeText(context,"Payment deatails updated",Toast.LENGTH_LONG).show();
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

    public static ArrayList<PaymentTable> getPaymentDetailsList(Context context)
    {
        ArrayList<PaymentTable> paymentTableArrayList = new ArrayList<PaymentTable>();
        SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ PaymentTable.PAYMENT_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                PaymentTable paymentTable=new PaymentTable();

                paymentTable.setPayment_idValue(cursor.getString(cursor.getColumnIndex(PaymentTable.PAYMENT_ID)));
                paymentTable.setPayment_amountValue(cursor.getString(cursor.getColumnIndex(PaymentTable.PAYMENT_AMOUNT)));
                paymentTable.setAdvance_amountValue(cursor.getString(cursor.getColumnIndex(PaymentTable.ADVANCE_AMOUNT)));
                paymentTable.setReamaining_amountValue(cursor.getString(cursor.getColumnIndex(PaymentTable.REMAINING_AMOUNT)));
                paymentTable.setUpload_statusValue(cursor.getString(cursor.getColumnIndex(PaymentTable.UPLOAD_STATUS)));
               // paymentTable.setImagePathValue(cursor.getString(cursor.getColumnIndex(PaymentTable.IMAGE_PATH)));
                paymentTable.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(PaymentTable.CUSTOMER_ID)));
              //  paymentTable.setCustomerName(cursor.getString(cursor.getColumnIndex(PaymentTable.CUSTOMER_NAME)));

                paymentTableArrayList.add(paymentTable);
                cursor.moveToNext();
            }
            return paymentTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public static boolean deletePaymentDetailsById(Context context, String payment_id)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + PaymentTable.PAYMENT_TABLE +
                    " WHERE " + PaymentTable.PAYMENT_ID + " = '" + payment_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Payment Details Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deletePaymentDetailsData(Context context)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + PaymentTable.PAYMENT_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Payment Details Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
