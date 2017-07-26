package com.examapplication.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class PushNotificationServiceFCM extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";
    private Context mContext;
    public static String notifikey="";
    public Bitmap bitmap;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
         mContext = getApplicationContext();

      /*  String data = remoteMessage.getData().get("advert_id");
        String image = remoteMessage.getData().get("image");
        String image11 = remoteMessage.getData().get("image");

        Log.i("image",""+image);
        getBitmapfromUrl(image);
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);

*/
        try
        {
            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
            Log.d("JSON_OBJECT", object.toString());
        }

        catch (Exception e){
            e.printStackTrace();

        }

       /* Intent intent = new Intent(this, AdvertDetailsActivity.class);
        intent.putExtra("message", remoteMessage.getData().get("advert_id"));
        // intent.putExtra("message1", remoteMessage.getNotification().getBody());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);*/

        // showNotification(remoteMessage.getData().get("message"));


    }


    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }


}

