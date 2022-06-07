# issue 3774
### Issue Link
  - https://github.com/firebase/firebase-android-sdk/issues/3774
### Summary
- When `callbacks.impressionDetected()` is called consecutively (e.g. twice/thrice), only the first campaignId is impressed.
### Prerequisite
- Add google-services.json
- Publish 2 campaigns from Firebase Messaging dashboard
- Add 2 events namely `main_screen_opened` and `another_main_screen_opened` on each campaign respectively.
### Steps to reproduce
1. Open the app
2. Click the `TRIGGER EVENT` button to retrieve the 2 campaigns.
      - The log `displayMessage: campaign {id} saved to hashmap` is displayed twice. Meaning 2 campaigns have been retrieved.
3. Click `MARK IMPRESSED` button. *This should've impressed both campaign id*
      - The log `markAsImpressionDetected: {id}` is displayed twice. 
4. Now, click the `TRIGGER EVENT` button again to retrieve the 2 campaigns.
      - The logs will show `I/FIAM.Headless: Already impressed campaign {id} ? : true`, however only one campaign has been marked impressed, the other campaign will show false.
      - *This shows that the `callbacks.impressionDetected()` was indeed called twice, however did not mark the 2nd campaign as impressed.*
