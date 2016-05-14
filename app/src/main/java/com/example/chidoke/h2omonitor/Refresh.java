package com.example.chidoke.h2omonitor;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class Refresh extends AsyncTask<Void, Void, Void> {
 
    private Context mCon;
     
    public Refresh(Context con)
    {
        mCon = con;
    }
     
    @Override
    protected Void doInBackground(Void... done) {
        try {

            ((MainActivity) mCon).checkfragment();
            Thread.sleep(2000);

            return null;
             
        } catch (Exception e) {
            return null;
        }
    }
     
    @Override
    protected void onPostExecute(Void done) {

        Toast.makeText(mCon, "Operation finished!",
                Toast.LENGTH_LONG).show();
         

        ((MainActivity) mCon).resetUpdating();
    }
}