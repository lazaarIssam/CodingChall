package com.issam.remoteunity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //---------------------------------
    RecyclerView recyclerViewallProduct;
    RecyclerView.Adapter adapterAllProduct;
    RecyclerView.LayoutManager layoutManagerAllProduct;
    ArrayList<Repos> ListData = new ArrayList<Repos>();
    String url_srv ="https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc";
    //---------------------------------
    LinearLayout activDataFromServ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_trending:
                    activDataFromServ.setVisibility(View.VISIBLE);
                    getDataFromServer();
                    return true;
                case R.id.navigation_setting:
                    activDataFromServ.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //--------------------------------------
        recyclerViewallProduct = (RecyclerView) findViewById(R.id.recyclerviewDataFromServ);
        layoutManagerAllProduct = new LinearLayoutManager(this);
        recyclerViewallProduct.setLayoutManager(layoutManagerAllProduct);
        recyclerViewallProduct.setHasFixedSize(true);
        //--------------------------------------
        activDataFromServ = findViewById(R.id.activDataFromServ);
        activDataFromServ.setVisibility(View.VISIBLE);
        //--------------------------------------
        getDataFromServer();
    }
    private void getDataFromServer() {
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url_srv,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ListData.clear();
                    Log.d(String.valueOf(MainActivity.this),"response: "+response.toString());
                    JSONArray obj = response.getJSONArray("items");
                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject jo = obj.getJSONObject(i);
                        Repos rp = new Repos();
                        rp.setReposName(jo.getString("name"));
                        rp.setReposDescription(jo.getString("description"));
                        rp.setStarsNbr(jo.getString("score"));
                        JSONObject objOwner= jo.getJSONObject("owner");
                        rp.setUserName(objOwner.getString("login"));
                        rp.setAvatarURL(objOwner.getString("avatar_url"));
                        ListData.add(rp);
                    }
                    adapterAllProduct = new recyclerAdapterDataFromSrv(ListData);
                    recyclerViewallProduct.setAdapter(adapterAllProduct);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },null);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
