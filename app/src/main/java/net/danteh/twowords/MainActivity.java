package net.danteh.twowords;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.ronash.pushe.Pushe;

public class MainActivity extends AppCompatActivity {
    TextView word1, word2;
    SharedPreferences save = getSharedPreferences("2words", MODE_PRIVATE);
    List<Words> wordsList = new ArrayList<>();
    boolean tempBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pushe.initialize(this, true);
        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);

        MyPushListener push = new MyPushListener();

        getJson();
        getWords();
    }
    
    public boolean getJson() {
        String url = "http://danteh.net/DanTehdes/words.json";
        StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                save.edit().putString("json", response);
                tempBoolean = true;
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Anything you want
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        return tempBoolean;
    }

    public void getWords() {
        try {

            if (!save.getString("json", "").equals("")) {
                //if sp is not null

                JSONObject jsonObject = new JSONObject(save.getString("json", ""));

                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jo = jsonArray.getJSONObject(i);
                    String english = jo.getString("english");
                    String id = jo.getString("id");
                    String persian = jo.getString("persian");

                    Words mywords = new Words(english, id, persian);
                    wordsList.add(mywords);
                }
            }
            else {
                Toast.makeText(this, "Connect to internet.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
