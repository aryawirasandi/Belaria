package com.example.aryawirasandi.belariaapp.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.aryawirasandi.belariaapp.Common.Common;
import com.example.aryawirasandi.belariaapp.Model.Request;
import com.example.aryawirasandi.belariaapp.OrderStatus;
import com.example.aryawirasandi.belariaapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ChoiceFormat;

public class ListenOrder extends Service implements ChildEventListener {
// Firebase init

    FirebaseDatabase db;
    DatabaseReference request;
    public ListenOrder() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseDatabase.getInstance();
        request = db.getReference("Requests");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        // Trigger the source code here
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Request request = dataSnapshot.getValue(Request.class);
        showNotification(dataSnapshot.getKey(), request);
    }

    private void showNotification(String key, Request request) {
        Intent intent = new Intent(getBaseContext(), OrderStatus.class);
        intent.putExtra("userPhone", request.getPhone()); // need put user phone
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("Belaria")
                .setContentInfo("Your order was updated")
                .setContentText( "Order #"+key+"was update status to "+ Common.convertCodeToStatus(request.getStatus()))
                .setContentIntent(contentIntent)
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
