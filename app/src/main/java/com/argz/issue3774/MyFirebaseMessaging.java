package com.argz.issue3774;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.display.internal.FirebaseInAppMessagingDisplayImpl;
import com.google.firebase.inappmessaging.model.InAppMessage;

import java.util.HashMap;

public class MyFirebaseMessaging extends FirebaseInAppMessagingDisplayImpl{
    private static final String TAG = "MyFirebaseMessaging";
    public final HashMap<String, HashMap> InAppMessages = new HashMap<>();

    @Override
    public void displayMessage(@NonNull InAppMessage inAppMessage, @NonNull FirebaseInAppMessagingDisplayCallbacks callbacks) {
        try {
            String campaignId = inAppMessage.getCampaignMetadata().getCampaignId();

            // Here we build a object that represents the received message
            HashMap<String, Object> inAppMessageObject = new HashMap<>();
            inAppMessageObject.put("message", inAppMessage);
            inAppMessageObject.put("callbacks", callbacks);

            // Then we put on HashMap this object to be called later
            this.InAppMessages.put(campaignId, inAppMessageObject);
            Log.d(TAG, "displayMessage: campaign " + campaignId + " saved to hashmap");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void markAsImpressionDetected(String campaignId) {
        try {
            // We recover from HashMap the message callbacks
            FirebaseInAppMessagingDisplayCallbacks callbacks = (FirebaseInAppMessagingDisplayCallbacks) this.InAppMessages.get(campaignId).get("callbacks");

            // And call
            callbacks.impressionDetected();
            Log.d(TAG, "markAsImpressionDetected: " + campaignId);
        } catch(Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}
