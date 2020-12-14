package com.example.api_crud_mock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> stringArray ;
    UserAdapter adapter;
    ListView list;
    Button btnClick;
    TextView tvDisplay;    String url = "https://5fd0672a1f23740016631776.mockapi.io/vothituoanh_17106431_API/v1/users";
     //String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users/21";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnClick = (Button) findViewById(R.id.btnClick);
        list     = findViewById(R.id.list);

//        GetArrayJson(url);
//        for (int i=0; i< stringArray.size();i++){
//            Log.d("a",stringArray.get(i).toString());
//        }



        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // GetData(url);
                //GetJson(url);
               GetArrayJson(url);

                //PostApi(url);
                //PutApi(url);
               // DeleteApi(url);
                adapter = new UserAdapter(MainActivity.this,stringArray);
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);





            }
        });
    }

    private void GetData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                tvDisplay.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void GetJson(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            tvDisplay.setText(response.getString("name").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get JsonObject...", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    private void GetArrayJson(String url){

        stringArray = new ArrayList<User>();
        stringArray.add(new User(111,"a","a","a"));
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){

                        User u = new User();
                        try {


                            JSONObject object = (JSONObject) response.get(i);

                                u.setId(object.getInt("id"));
                                u.setCreatedAt(object.getString("createdAt"));
                                u.setName(object.getString("name"));
                                u.setAvatar(object.getString("avatar"));
                                //   Toast.makeText(MainActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
                            adapter.list.add(u);
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                            //  tvDisplay.append(u.toString());


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
//                params.put("id", "51");
                params.put("createdAt", "2020-12-10T09:48:30.985Z");
                params.put("name", "Oanh");
                params.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/thierrykoblentz/128.jpg");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void PutApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                url + '/' + 28, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                //                params.put("id", "51");
                params.put("createdAt", "2020-12-10T09:48:30.985Z");
                params.put("name", "Oanh");
                params.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/thierrykoblentz/128.jpg");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + 24, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
