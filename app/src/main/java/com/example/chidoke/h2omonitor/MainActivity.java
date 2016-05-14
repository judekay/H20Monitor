package com.example.chidoke.h2omonitor;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import com.example.chidoke.h2omonitor.dummy.DummyContent;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
    NotificationsFragment.OnFragmentInteractionListener{

    private Menu mymenu;
    RequestQueue mRequestQueue;


    public static final String API_URL = " http://api.inspiredarts.com.ng/chikodi/web/api/v1/data/retrieve";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRequest();

        getSupportActionBar().setIcon(R.drawable.logo);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if(savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, HomeFragment.newInstance(),"Home Fragment")
                    .commit();


        Button homeBtn = (Button)findViewById(R.id.homeBtn);
        Button notiBtn = (Button)findViewById(R.id.notiBtn);



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, HomeFragment.newInstance(), "Home Fragment")
                            .commit();
            }
        });

        notiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, new NotificationsFragment()).commit();

                getSupportFragmentManager().beginTransaction().replace(R.id.container, NotificationsFragment.newInstance(), "Notification Fragment").commit();

            }
        });

    }

    public void getRequest(){
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();
                final StringRequest stringrequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parseJson(response);

            }


                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                HomeFragment hFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("Home Fragment");
                hFragment.setText("50" + "%");
                Toast.makeText(getApplicationContext(),"Error connecting to network", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        }

        );
        mRequestQueue.add(stringrequest);
    }

    private void parseJson(String response) {
        JsonParser jsonparser = new JsonParser(response);
        jsonparser.parseJSON();

        HomeFragment hFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("Home Fragment");
        hFragment.setText(JsonParser.distance_percentage + "%");
        int percent = Integer.parseInt(JsonParser.distance_percentage);
        if(percent >= 0 && percent <= 30)
        {
            hFragment.setImageurl(R.drawable.low);
        }
        else if(percent >= 31 && percent <= 50)
        {
            hFragment.setImageurl(R.drawable.lowmed);
        }
        else if(percent >= 51 && percent <= 80)
        {
            hFragment.setImageurl(R.drawable.highmed);
        }
        else if(percent >= 81 && percent <= 100)
        {
            hFragment.setImageurl(R.drawable.high);
        }
        else {
            Toast.makeText(getApplicationContext(), "Water tank level could not be fetched, try again", Toast.LENGTH_SHORT).show();
            hFragment.setImageurl(0);
            hFragment.setText("0" + "%");
        }

    }




    @Override
    public void onFragmentInteraction() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);


        mymenu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
//            case R.id.action_refresh:
//                // Do animation start
//                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                ImageView rotate = (ImageView)inflater.inflate(R.layout.animate_refresh, null);
//                Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
//                rotation.setRepeatCount(Animation.INFINITE);
//                rotate.startAnimation(rotation);
//                item.setActionView(rotate);
//                new Refresh(this).execute();
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void resetUpdating()
    {
        MenuItem m = mymenu.findItem(R.id.action_refresh);
        if(m.getActionView()!=null)
        {
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }

    public void checkfragment()
    {
        Fragment myFragment = getFragmentManager().findFragmentByTag("Home Fragment");
        Fragment myFragment2 = getFragmentManager().findFragmentByTag("Notification Fragment");
        if (myFragment != null && myFragment.isVisible()) {
            getRequest();
        }
        else if(myFragment2 != null && myFragment2.isVisible())
        {
//            getRequestnotify();
        }

    }


}
