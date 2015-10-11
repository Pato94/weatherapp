package com.contreras.cristian.weatherappcristiancontreras;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pato on 10/10/2015.
 */
public class Utils {

    public static String getStringTempFromFloat(float tempInFloat) {
        return String.format("%dº", Math.round(tempInFloat));
    }

    public static String getFormattedDateFromTimeInSeconds(long timeInSeconds) {
        return new SimpleDateFormat("EEEE, dd/MM/yyyy").format(new Date(timeInSeconds * 1000));
    }

    public static int getResourceFromCode(String imageCode) {
        String iconId = imageCode.length() > 2 ? imageCode.substring(0, 2) : "10";

        int resId;
        switch (iconId) {
            case "01":
                resId = R.drawable.sun_100;
                break;
            case "02":
                resId = R.drawable.party_cloudy_100;
                break;
            case "03":
                resId = R.drawable.clouds_100;
                break;
            case "04":
                resId = R.drawable.clouds_100;
                break;
            case "10":
                resId = R.drawable.rain_100;
                break;
            default:
                resId = R.drawable.rain_100;
                break;
        }
        return resId;
    }
}
