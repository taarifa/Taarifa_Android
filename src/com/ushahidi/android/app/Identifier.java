package com.ushahidi.android.app;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

public class Identifier extends Activity {
	
    //Get the unique identifier of the phone
    
    public String identifier(){

    	TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

    	String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        tmPhone = "" + tm.getSimOperator();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueID = deviceUuid.toString();
    	
    	return uniqueID;
    }

}
