package net.jhoobin.cordovabridge;

import android.content.Context;
import com.google.gson.Gson;
import net.jhoobin.amaroidsdk.TrackHelper;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class AmaroidBridge extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        Context context = this.cordova.getActivity();
        String jSon = data.getString(0);

        if (action.equals("trackView")) {
            trackViewSubject(context, jSon, callbackContext);
            return true;
        } else if (action.equals("setTags")) {
            setTags(jSon, callbackContext);
            return true;
        } else if (action.equals("trackEvent")) {
            trackEvent(context, jSon, callbackContext);
            return true;
        } else {
            return false;
        }
    }

    private void trackEvent(Context context, String jSon, CallbackContext callbackContext) {
        if (jSon != null && jSon.length() > 0) {
            try {

                Event event = new Gson().fromJson(jSon, Event.class);
                TrackHelper.trackEvent(context, event.eventName, event.eventValue, event.eventAction, event.eventTarget);

                callbackContext.success("success. " + jSon);
            } catch (Exception e) {
                callbackContext.error("Invalid argument.");
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void setTags(String jSon, CallbackContext callbackContext) {
        if (jSon != null && jSon.length() > 0) {
            try {

                AmaroidTag amaroidTag = new Gson().fromJson(jSon, AmaroidTag.class);
                TrackHelper.setTags(amaroidTag.tag1, amaroidTag.tag2, amaroidTag.tag3);

                callbackContext.success("success. " + jSon);
            } catch (Exception e) {
                callbackContext.error("Invalid argument.");
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void trackViewSubject(Context context, String jSon, CallbackContext callbackContext) {
        if (jSon != null && jSon.length() > 0) {
            try {

                pageView pageView = new Gson().fromJson(jSon, pageView.class);
                TrackHelper.trackViewSubject(context, pageView.pageName);

                callbackContext.success("success. " + jSon);
            } catch (Exception e) {
                callbackContext.error("Invalid argument.");
            }
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public class Event {
        public Long eventValue;
        public String eventName;
        public String eventTarget;
        public String eventAction;
    }

    public class AmaroidTag {
        public String tag1;
        public String tag2;
        public String tag3;
    }

    public class pageView {
        public String pageName;
    }
}