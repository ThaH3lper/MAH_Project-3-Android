package se.mah.homebois.ethaplanner.net;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import se.mah.homebois.ethaplanner.models.Weather.WeatherModel;

/**
 * Created by Patrik on 2016-10-23.
 */

public class WeatherFetcher  extends AsyncTask<String,Integer,WeatherModel> {
    private ModelListener listener;

    public WeatherFetcher(ModelListener listener, String url, String encoding) {
        if(listener!=null) {
            this.listener = listener;
            execute(url, encoding);
        }
    }
    protected WeatherModel doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), params[1]));
            String txt;
            while((txt=reader.readLine())!=null)
                result.append(txt+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close reader
        }
        WeatherModel model = new Gson().fromJson(result.toString(), WeatherModel.class);
        return model;
    }
    protected void onPostExecute(WeatherModel result) {
        listener.getWeather(result);
    }
    public interface ModelListener {
        void getWeather(WeatherModel model);
    }
}
