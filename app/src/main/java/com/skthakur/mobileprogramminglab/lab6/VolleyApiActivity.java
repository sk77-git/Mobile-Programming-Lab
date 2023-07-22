package com.skthakur.mobileprogramminglab.lab6;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.skthakur.mobileprogramminglab.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class VolleyApiActivity extends AppCompatActivity {
    private static final String TAG = "VolleyApiActivity";
    private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com/posts";
    ProgressDialog progressDialog;
    TextView tvResponseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_api);
        tvResponseText = findViewById(R.id.tv_response_text);
        progressDialog = new ProgressDialog(this);
        getData();
    }

    private void getData() {
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                JSON_PLACEHOLDER_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        handleResponse(response);
                        Toast.makeText(VolleyApiActivity.this, "Success fetching data", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VolleyApiActivity.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error retrieving data: " + error.getMessage());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void handleResponse(JSONArray jsonArray) {
        StringBuilder result = new StringBuilder();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int postId = jsonObject.getInt("id");
                String title = jsonObject.getString("title");
                String body = jsonObject.getString("body");
                result.append(String.valueOf(i + 1)).append("\t").append(title).append("\n\n\n");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
        }
        tvResponseText.setText(result.toString());
        progressDialog.dismiss();
    }
}