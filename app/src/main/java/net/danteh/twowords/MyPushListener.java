package net.danteh.twowords;

import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

public class MyPushListener extends PusheListenerService{
    @Override
    public void onMessageReceived(JSONObject customContent, JSONObject pushMessage){
        android.util.Log.i("Pushe","Custom json Message: "+ customContent.toString());
        // Your Code
    }
}
