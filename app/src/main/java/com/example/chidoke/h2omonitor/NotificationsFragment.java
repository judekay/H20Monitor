package com.example.chidoke.h2omonitor;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Judekayode on 4/25/2016.
 */
public class NotificationsFragment extends Fragment {

    public static final String TAG = "MyRecyclerList";
    private List<Data> listItemsList = new ArrayList<Data>();

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private OnFragmentInteractionListener mListener;
    private RequestQueue queue;

    private int counter = 0;
    private String count;
    private String jsonSubreddit;
    private String after_id;
    private static final String response = "response";
    private static final String data = "data";
    private static final String distance = "distance";
    private static final String distancestring = "distancestring";
    private static final String distanceurl = "http://api.inspiredarts.com.ng/chikodi/web/api/v1/data/retrieve";

    private ProgressDialog progressDialog;


    public NotificationsFragment() {
        // Required empty public constructor
    }


    public static NotificationsFragment newInstance() {
       NotificationsFragment fragment = new NotificationsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = layoutInflater.inflate(R.layout.fragment_notification_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), R.drawable.divider));

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        updateList();

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    public void updateList() {

        counter = 0;

        String disturl = distanceurl;

        adapter = new MyRecyclerAdapter(getActivity(), listItemsList);
        mRecyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(getActivity());

        adapter.clearAdapter();

        showPD();
//        if(fetchFromVolleyCache()) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, disturl, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    Log.d(TAG, response.toString());

                    hidePD();

                    try {
                        JSONObject data1 = response.getJSONObject("response");
                        JSONObject dataz = data1.getJSONObject(data);

                        JSONArray children = dataz.getJSONArray(distance);
                        Log.d("NEW", children.toString());


                        for (int i = 0; i < children.length(); i++) {
                            JSONObject distances = children.getJSONObject(i);
                            String distancez = distances.optString(distance);

                            Data item = new Data();

                            item.setDistance(distances.getString(distance) + "% remaining ");
                            int val = Integer.parseInt(distances.getString(distance));
                            String remark = "";
                            Integer image = 0;
                            if (val <= 40) {
                                remark = "Water is critically low";
                                image = R.drawable.critical;

                            } else if (val > 40 && val <= 70) {
                                remark = "Water level is normal";
                                image = R.drawable.normal;
                            } else if (val > 70 && val <= 95) {
                                remark = "Water in tank is almost full";
                                image = R.drawable.normal;
                            } else if (val > 95 && val <= 100) {
                                remark = "Water in tank is full";
                                image = R.drawable.full;
                            }
                            item.setRemark(remark);
                            item.setData_image(image);

                            listItemsList.add(item);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error" + error.getMessage());
                    hidePD();
                    Data item = new Data();

                    item.setDistance("0% remaining ");
                    item.setRemark("Error connecting to the internet");
                    item.setData_image(0);
                    listItemsList.add(item);
                    adapter.notifyDataSetChanged();
                }
            });

            queue.add(jsonObjectRequest);
        }
//        else {
//            fetchFromVolleyCache();
//        }
//        }
    private boolean fetchFromVolleyCache(){

        try{

            Cache cache = queue.getCache();
            Cache.Entry entry = cache.get(distanceurl);
            if(entry != null) {
                JSONObject cachedResponse = new JSONObject(new String(entry.data, "UTF-8"));
                Log.d("CACHE", cachedResponse.getJSONObject("response").toString());

                return true;
            }
        }catch (UnsupportedEncodingException | JSONException e){
            e.printStackTrace();
            Log.d("CACHE", "Error fetching ");
        }

        return false;
    }

    private void showPD() {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    private void hidePD() {
        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePD();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
