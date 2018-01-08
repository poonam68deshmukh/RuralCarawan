package com.hatchers.ruralcaravane.ConstructionTeamDetails.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hatchers.ruralcaravane.CustomerRegistration.Databases.CustomerTable;
import com.hatchers.ruralcaravane.Databases.DatabaseHandler;

import java.util.ArrayList;


public class ConstructionTableHelper {

    public static boolean insertConstructionTeamData(Context context, ConstructionTable constructionTable)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

                values.put(ConstructionTable.TECHNICIAN_ID, constructionTable.getTechnicianIdValue());
                values.put(ConstructionTable.TECHNICIAN_MOBILENO, constructionTable.getTechnicianMobileNoValue());
                values.put(ConstructionTable.TECHNICIAN_AGE, constructionTable.getTechnicianAgeValue());
                values.put(ConstructionTable.TECHNICIAN_NAME,constructionTable.getTechnicianNameValue());
                values.put(ConstructionTable.TECHNICIAN_ADDRESS,constructionTable.getTechnicianAddressValue());
                values.put(ConstructionTable.TECHNICIAN_GENDER,constructionTable.getTechnicianGenderValue());
                values.put(ConstructionTable.UPLOAD_STATUS,constructionTable.getUploadStatusValue());
                values.put(ConstructionTable.HALF_COMPLETION_IMAGE,constructionTable.getHalfCompletionImageValue());
                values.put(ConstructionTable.COMPLETION_IMAGE,constructionTable.getCompletionImageValue());
                values.put(ConstructionTable.CUSTOMER_ID,constructionTable.getCustomerIdValue());
                values.put(ConstructionTable.KITCHEN_ID,constructionTable.getKitchenIdValue());
                values.put(ConstructionTable.TECHNICIAN_UNIQUE_ID,constructionTable.getTechnicianUniqueIdValue());
                values.put(ConstructionTable.DATETIME,constructionTable.getDateTimeValue());



            if (db.insert(ConstructionTable.CONSTRUCTION_TEAM_TABLE, null, values) > 0)
            {
               // Toast.makeText(context,"Construction team member data inserted",Toast.LENGTH_LONG).show();
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

    public static boolean updateConstructionTeamData(Context context, ConstructionTable constructionTable)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(ConstructionTable.TECHNICIAN_ID, constructionTable.getTechnicianIdValue());
            values.put(ConstructionTable.TECHNICIAN_MOBILENO, constructionTable.getTechnicianMobileNoValue());
            values.put(ConstructionTable.TECHNICIAN_AGE, constructionTable.getTechnicianAgeValue());
            values.put(ConstructionTable.TECHNICIAN_NAME,constructionTable.getTechnicianNameValue());
            values.put(ConstructionTable.TECHNICIAN_ADDRESS,constructionTable.getTechnicianAddressValue());
            values.put(ConstructionTable.TECHNICIAN_GENDER,constructionTable.getTechnicianGenderValue());
            values.put(ConstructionTable.UPLOAD_STATUS,constructionTable.getUploadStatusValue());
            values.put(ConstructionTable.HALF_COMPLETION_IMAGE,constructionTable.getHalfCompletionImageValue());
            values.put(ConstructionTable.COMPLETION_IMAGE,constructionTable.getCompletionImageValue());
            values.put(ConstructionTable.CUSTOMER_ID,constructionTable.getCustomerIdValue());
            values.put(ConstructionTable.KITCHEN_ID,constructionTable.getKitchenIdValue());
            values.put(ConstructionTable.TECHNICIAN_UNIQUE_ID,constructionTable.getTechnicianUniqueIdValue());
            values.put(ConstructionTable.DATETIME,constructionTable.getDateTimeValue());

            // upadating Row
            if(db.update(ConstructionTable.CONSTRUCTION_TEAM_TABLE, values, ConstructionTable.TECHNICIAN_ID+"="+constructionTable.getTechnicianIdValue(), null)>0)
            {
                Toast.makeText(context,"Construction team member data updated",Toast.LENGTH_LONG).show();
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

    public static ConstructionTable getConstructionTeamData(Context context)
    {
        SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ ConstructionTable.CONSTRUCTION_TEAM_TABLE+" WHERE "+ ConstructionTable.TECHNICIAN_ID,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                ConstructionTable constructionTable = new ConstructionTable();

                constructionTable.setTechnicianIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_ID)));
                constructionTable.setTechnicianNameValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_NAME)));
                constructionTable.setTechnicianMobileNoValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_MOBILENO)));
                constructionTable.setTechnicianAgeValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_AGE)));
                constructionTable.setTechnicianAddressValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_ADDRESS)));
                constructionTable.setTechnicianUniqueIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_UNIQUE_ID)));
                constructionTable.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.CUSTOMER_ID)));
                constructionTable.setKitchenIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.KITCHEN_ID)));
                constructionTable.setDateTimeValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.DATETIME)));
                constructionTable.setHalfCompletionImageValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.HALF_COMPLETION_IMAGE)));
                constructionTable.setCompletionImageValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.COMPLETION_IMAGE)));
                constructionTable.setUploadStatusValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.UPLOAD_STATUS)));
                constructionTable.setTechnicianGenderValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_GENDER)));


                cursor.moveToNext();
                return  constructionTable;
            }
            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public static ArrayList<ConstructionTable> getConstructionTeamList(Context context)
    {
        ArrayList<ConstructionTable> constructionTableArrayList = new ArrayList<ConstructionTable>();
        SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ ConstructionTable.CONSTRUCTION_TEAM_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                ConstructionTable constructionTable = new ConstructionTable();

                constructionTable.setTechnicianIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_ID)));
                constructionTable.setTechnicianNameValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_NAME)));
                constructionTable.setTechnicianMobileNoValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_MOBILENO)));
                constructionTable.setTechnicianAgeValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_AGE)));
                constructionTable.setTechnicianAddressValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_ADDRESS)));
                constructionTable.setTechnicianUniqueIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_UNIQUE_ID)));
                constructionTable.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.CUSTOMER_ID)));
                constructionTable.setKitchenIdValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.KITCHEN_ID)));
                constructionTable.setDateTimeValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.DATETIME)));
                constructionTable.setHalfCompletionImageValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.HALF_COMPLETION_IMAGE)));
                constructionTable.setCompletionImageValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.COMPLETION_IMAGE)));
                constructionTable.setUploadStatusValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.UPLOAD_STATUS)));
                constructionTable.setTechnicianGenderValue(cursor.getString(cursor.getColumnIndex(ConstructionTable.TECHNICIAN_GENDER)));


                constructionTableArrayList.add(constructionTable);
                cursor.moveToNext();
            }
            return constructionTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public static boolean deleteConstructionTeamById(Context context, String construction_id)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + ConstructionTable.CONSTRUCTION_TEAM_TABLE +
                    " WHERE " + ConstructionTable.TECHNICIAN_ID + " = '" + construction_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "COnstruction Member Data Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteConstructionTeam(Context context)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + ConstructionTable.CONSTRUCTION_TEAM_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Construction team data deleted successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
