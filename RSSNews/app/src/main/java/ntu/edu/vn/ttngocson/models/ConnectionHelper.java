package ntu.edu.vn.ttngocson.models;

import android.util.Log;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionHelper
{
    public static HttpsURLConnection getConnection(String link)
    {
        URL url = null;
        try
        {
            url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/xml; charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible)");
            connection.setRequestProperty("Accept", "*/*");
            return connection;

        }
        catch (Exception e)
        {
            Log.d("Error",e.getMessage().toString());
        }
        return null;
    }
}
