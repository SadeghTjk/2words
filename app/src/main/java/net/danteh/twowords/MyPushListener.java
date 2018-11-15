package net.danteh.twowords;

import org.json.JSONException;
import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

public class MyPushListener extends PusheListenerService{
    static final String[] words = {"test","test","test","test"};

    @Override
    public void onMessageReceived(JSONObject customContent, JSONObject pushMessage){
        android.util.Log.i("Pushe","Custom json Message: "+ customContent.toString());
        try{
            String s1 = customContent.getString("title");
            String s2 = customContent.getString("content");
            String noti1 = pushMessage.getString("noti");
            words[0] = noti1;
            android.util.Log.i("Pushe","Json Message\n title: " + s1 + "\n content: " + s2);
        } catch (JSONException e) {
            android.util.Log.e("TAG","Exception in parsing json" ,e);
        }
    }
}
