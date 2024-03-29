package com.example.myapplication.ChatApp.Notifications;

import android.media.session.MediaSession;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseIdService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken=FirebaseInstanceId.getInstance().getToken();
        if(firebaseUser!=null)
        {
            updateToken(refreshToken);
        }
    }

    private  void updateToken(String refreshToken)
    {
     FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Tokens");
        Token_model token=new Token_model(refreshToken);
        databaseReference.child(firebaseUser.getUid()).setValue(token);


    }
}
