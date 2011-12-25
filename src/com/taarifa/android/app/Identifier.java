package com.taarifa.android.app;

import java.util.UUID;

import android.content.ContentResolver;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Provides a unique identifier for the device the application is running on
 * @author nicoweinert
 *
 */
public class Identifier {
	private static final String CLASS_TAG = Identifier.class.getCanonicalName();
	
	public static String getDeviceIdentifier(Context context, ContentResolver contentResolver) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(contentResolver, android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        Log.d(CLASS_TAG, "Device Id is calculated as: " + deviceId);
        return deviceId;
	}

}
