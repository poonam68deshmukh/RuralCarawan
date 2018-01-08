package com.hatchers.ruralcaravane.user_login.apihelper;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hatchers.ruralcaravane.customer_registration.CustomerRegistrationActivity;
import com.hatchers.ruralcaravane.pref_manager.PrefManager;
import com.hatchers.ruralcaravane.app.MyApplication;
import com.hatchers.ruralcaravane.constants.WebServiceUrls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Login_ApiHelper
{
    public static boolean userLoginApi(final Activity activity, final SweetAlertDialog sweetAlertDialog)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlUserLogin,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("Success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Login successfully")) {
                            JSONArray result = responce.getJSONArray("result");
                            JSONObject jsonObject = result.getJSONObject(0);

                            PrefManager prefManager=new PrefManager(activity);
                            prefManager.setUserName(jsonObject.getString("user_name"));
                            prefManager.setUserId(jsonObject.getString("id"));
                            prefManager.setVillageName(jsonObject.getString("village_name"));
                            prefManager.setVillageId(jsonObject.getString("vilage_id"));
                            prefManager.setTqId(jsonObject.getString("tq_id"));
                            prefManager.setMobile(jsonObject.getString("mobile"));
                            prefManager.setBirthday(jsonObject.getString("dob"));
                            prefManager.setPassword(jsonObject.getString("password"));

                            prefManager.createLogin(jsonObject.getString("mobile"));
                            sweetAlertDialog.dismiss();
                            // new PrefManager(activity).setRegistration_skipped(false);
                            Intent intent = new Intent(activity, CustomerRegistrationActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                        else
                        {
                            sweetAlertDialog.dismiss();
                            Toast.makeText(activity,"Invalid Login...", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        sweetAlertDialog.dismiss();
                        Toast.makeText(activity,"Invalid Login...", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    sweetAlertDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                sweetAlertDialog.dismiss();
                Toast.makeText(activity,error.toString(),Toast.LENGTH_SHORT).show();

               }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                params.put("mobile", new PrefManager(activity).getUserName());
                params.put("password", new PrefManager(activity).getPassword());
                params.put("format","json");
                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);
        return true;

    }

}
