package com.example.chidoke.h2omonitor;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Judekayode on 4/19/2016.
 */
public class JsonParser {
    public static String distance_percentage;
    public static String[] remark;
    public static String[] data_val;


    public static final String JSON_OBJECT = "response";
    public static final String JSON_OBJ2 = "data";
    public static final String JSON_ARRAY = "distance";
    public static final String KEY_DIST_PERCENT = "distance";




    private String json;

    public JsonParser(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            JSONObject waterdistance = jsonObject.getJSONObject(JSON_OBJECT);

            JSONObject data = waterdistance.getJSONObject(JSON_OBJ2);

            JSONArray distance = data.getJSONArray(JSON_ARRAY);
            remark = new String[data.length()];
            data_val = new String[data.length()];


            for(int i=0; i< distance.length(); i++ ){
                JSONObject details = distance.optJSONObject(0);
                distance_percentage = details.optString(KEY_DIST_PERCENT);
//                JSONObject detail = distance.optJSONObject(i);
//                String dataval = detail.optString(KEY_DIST_PERCENT);
//
//                data_val[i] = dataval;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void parseJSON2(){

        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            JSONObject waterdistance = jsonObject.getJSONObject(JSON_OBJECT);

            JSONObject data = waterdistance.getJSONObject(JSON_OBJ2);

            JSONArray distance = data.getJSONArray(JSON_ARRAY);
            remark = new String[data.length()];
            data_val = new String[data.length()];


            for(int i=0; i< distance.length(); i++ ){

                JSONObject detail = distance.optJSONObject(i);
                String dataval = detail.optString(KEY_DIST_PERCENT);

                data_val[i] = dataval;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}