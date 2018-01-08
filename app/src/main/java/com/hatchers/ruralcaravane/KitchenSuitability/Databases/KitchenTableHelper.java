package com.hatchers.ruralcaravane.KitchenSuitability.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hatchers.ruralcaravane.Databases.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by Nikam on 28/12/2017.
 */
public class KitchenTableHelper {

    public static boolean insertKitchenData(Context context, KitchenTable kitchenTable)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

                values.put(KitchenTable.KITCHEN_ID,kitchenTable.getKitchen_idValue());
                values.put(KitchenTable.HOUSE_TYPE,kitchenTable.getHouse_typeValue());
                values.put(KitchenTable.ROOF_TYPE,kitchenTable.getRoof_typeValue());
                values.put(KitchenTable.KITCHEN_HEIGHT,kitchenTable.getKitchen_heightValue());
                values.put(KitchenTable.UPLOAD_STATUS,kitchenTable.getUpload_statusValue());
                values.put(KitchenTable.CUSTOMER_ID,kitchenTable.getCustomer_idValue());
                values.put(KitchenTable.CUSTOMER_NAME,kitchenTable.getCustomer_nameValue());
                values.put(KitchenTable.PLACE_IMAGE,kitchenTable.getPlaceImageValue());
                values.put(KitchenTable.KITCHEN_UNIQUE_ID,kitchenTable.getKitchenUniqueIdValue());
                values.put(KitchenTable.LATITUDE,kitchenTable.getLatitudeValue());
                values.put(KitchenTable.LONGITUDE,kitchenTable.getLongitudeValue());
                values.put(KitchenTable.UPLOAD_DATE,kitchenTable.getUploadDateValue());
                values.put(KitchenTable.GEO_ADDRESS,kitchenTable.getGeoAddressValue());
                values.put(KitchenTable.COST_OF_CHULLHA,kitchenTable.getCostOfChullhaValue());

            if (db.insert(KitchenTable.KITCHEN_TABLE, null, values) > 0)
            {
               // Toast.makeText(context,"KItchen data inserted",Toast.LENGTH_LONG).show();
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


    public static boolean updateKitchenData(Context context, KitchenTable kitchen_table)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(KitchenTable.KITCHEN_ID,kitchen_table.getKitchen_idValue());
            values.put(KitchenTable.KITCHEN_HEIGHT,kitchen_table.getKitchen_heightValue());
            values.put(KitchenTable.HOUSE_TYPE,kitchen_table.getHouse_typeValue());
            values.put(KitchenTable.ROOF_TYPE,kitchen_table.getRoof_typeValue());
            values.put(KitchenTable.UPLOAD_STATUS,kitchen_table.getUpload_statusValue());
            values.put(KitchenTable.CUSTOMER_ID,kitchen_table.getCustomer_idValue());
            values.put(KitchenTable.CUSTOMER_NAME,kitchen_table.getCustomer_nameValue());
            values.put(KitchenTable.PLACE_IMAGE,kitchen_table.getPlaceImageValue());
            values.put(KitchenTable.KITCHEN_UNIQUE_ID,kitchen_table.getKitchenUniqueIdValue());
            values.put(KitchenTable.LATITUDE,kitchen_table.getLatitudeValue());
            values.put(KitchenTable.LONGITUDE,kitchen_table.getLongitudeValue());
            values.put(KitchenTable.UPLOAD_DATE,kitchen_table.getUploadDateValue());
            values.put(KitchenTable.GEO_ADDRESS,kitchen_table.getGeoAddressValue());
            values.put(KitchenTable.COST_OF_CHULLHA,kitchen_table.getCostOfChullhaValue());



            // upadating Row
            if(db.update(KitchenTable.KITCHEN_TABLE, values, KitchenTable.KITCHEN_ID+"="+kitchen_table.getKitchen_idValue(), null)>0)
            {
                Toast.makeText(context,"Kitchen data updated",Toast.LENGTH_LONG).show();
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

    public static ArrayList<KitchenTable> getKitchenDataList(Context context)
    {
        ArrayList<KitchenTable> customerTableArrayList = new ArrayList<KitchenTable>();
        SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ KitchenTable.KITCHEN_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                KitchenTable kitchen_table=new KitchenTable();

                kitchen_table.setKitchen_idValue(cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_ID)));
                kitchen_table.setHouse_typeValue(cursor.getString(cursor.getColumnIndex(KitchenTable.HOUSE_TYPE)));
                kitchen_table.setRoof_typeValue(cursor.getString(cursor.getColumnIndex(KitchenTable.ROOF_TYPE)));
                kitchen_table.setKitchen_heightValue(cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_HEIGHT)));
                kitchen_table.setUpload_statusValue(cursor.getString(cursor.getColumnIndex(KitchenTable.UPLOAD_STATUS)));
                kitchen_table.setCustomer_idValue(cursor.getString(cursor.getColumnIndex(KitchenTable.CUSTOMER_ID)));
                kitchen_table.setCustomer_nameValue(cursor.getString(cursor.getColumnIndex(KitchenTable.CUSTOMER_NAME)));
                kitchen_table.setPlaceImageValue(cursor.getString(cursor.getColumnIndex(KitchenTable.PLACE_IMAGE)));
                kitchen_table.setKitchenUniqueIdValue(cursor.getString(cursor.getColumnIndex(KitchenTable.KITCHEN_UNIQUE_ID)));
                kitchen_table.setLatitudeValue(cursor.getString(cursor.getColumnIndex(KitchenTable.LATITUDE)));
                kitchen_table.setLongitudeValue(cursor.getString(cursor.getColumnIndex(KitchenTable.LONGITUDE)));
                kitchen_table.setUploadDateValue(cursor.getString(cursor.getColumnIndex(KitchenTable.UPLOAD_DATE)));
                kitchen_table.setGeoAddressValue(cursor.getString(cursor.getColumnIndex(KitchenTable.GEO_ADDRESS)));
                kitchen_table.setCostOfChullhaValue(cursor.getString(cursor.getColumnIndex(KitchenTable.COST_OF_CHULLHA)));

                customerTableArrayList.add(kitchen_table);
                cursor.moveToNext();
            }
            return customerTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteKitchenById(Context context, String kitchen_id)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + KitchenTable.KITCHEN_TABLE +
                    " WHERE " + KitchenTable.KITCHEN_ID + " = '" + kitchen_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Kitchen Data Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteKitchenData(Context context)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + KitchenTable.KITCHEN_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Kitchen data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
