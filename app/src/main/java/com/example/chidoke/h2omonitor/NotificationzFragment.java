package com.example.chidoke.h2omonitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.chidoke.h2omonitor.dummy.DummyContent.DummyItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NotificationzFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private LinearLayoutManager layoutManager;



    MyNotificationRecyclerViewAdapter adapter;

    private static List<Data> demoData;
    public static final String API_URL = " http://api.inspiredarts.com.ng/chikodi/web/api/v1/data/retrieve";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotificationzFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotificationzFragment newInstance() {
        NotificationzFragment fragment = new NotificationzFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notification_list, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);


        demoData = new ArrayList<>();
        Data dd = new Data();


        demoData.add(new Data());
        demoData.add(new Data());
        demoData.add(dd);
        adapter = new MyNotificationRecyclerViewAdapter(demoData, R.layout.fragment_notification_list, getActivity());
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

//    private void prepareMovieData() {
//        Data movie = new Data("Mad Max: Fury Road", "Action & Adventure", 0);
//        demoData.add(movie);
//
//        movie = new Data("Inside Out", "Animation, Kids & Family", 0);
//        demoData.add(movie);
//
//        movie = new Data("Star Wars: Episode VII - The Force Awakens", "Action", 0);
//        demoData.add(movie);
//        adapter.notifyDataSetChanged();
//
//
//    }
        @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);






    }



//    public void getRequest(){
//        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
//
//// Set up the network to use HttpURLConnection as the HTTP client.
//        Network network = new BasicNetwork(new HurlStack());
//
//// Instantiate the RequestQueue with the cache and network.
//        mRequestQueue = new RequestQueue(cache, network);
//
//// Start the queue
//        mRequestQueue.start();
//        final StringRequest stringrequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
////                parseJson(response);
//
//            }
//
//
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                HomeFragment hFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("Home Fragment");
////                hFragment.setText("50" + "%");
////                Toast.makeText(getApplicationContext(), "Error connecting to network", Toast.LENGTH_SHORT).show();
////                error.printStackTrace();
//
//            }
//        }
//
//        );
//        mRequestQueue.add(stringrequest);
//    }
//
//    private void parseJson(JSONObject response) {
//        JSONObject jsonObject=null;
//        try {
//
//
//            JSONArray distance = response.getJSONArray("distance");
//
//
//            for(int i=0; i< distance.length(); i++ ){
//                JSONObject details = distance.optJSONObject(i);
////                distance_percentage = details.optString(KEY_DIST_PERCENT);
////                JSONObject detail = distance.optJSONObject(i);
//                String dataval = details.optString("distance");
////
//                data_val = new String[distance.length()];
//                data_val[i] = dataval;
//
//            }
//
//            Data data = new Data(data_val[1],"this is cool", 0);
//            dataList.add(data);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
