package com.argz.issue3774;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;

public class MainActivity extends AppCompatActivity {

    Button triggerEvents;
    Button markImpressed;
    MyFirebaseMessaging myFirebaseMessaging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triggerEvents = findViewById(R.id.btn_trigger_event);
        markImpressed = findViewById(R.id.btn_mark_impressed);

        myFirebaseMessaging = new MyFirebaseMessaging();

        triggerEvents.setOnClickListener(view -> {
            FirebaseInAppMessaging.getInstance().triggerEvent("main_screen_opened");
            FirebaseInAppMessaging.getInstance().triggerEvent("another_main_screen_opened");
        });
        markImpressed.setOnClickListener(view -> {
            myFirebaseMessaging.markAsImpressionDetected("replace_with_campaign_id_of_event1");
            myFirebaseMessaging.markAsImpressionDetected("replace_with_campaign_id_of_event2");
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseInAppMessaging.getInstance().setMessageDisplayComponent(myFirebaseMessaging);
    }
}