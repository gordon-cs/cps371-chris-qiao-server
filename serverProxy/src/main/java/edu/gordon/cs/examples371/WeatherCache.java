package edu.gordon.cs.examples371;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WeatherCache{
    private String weatherData;
    private String time;
    private String [] key;
    private HashMap<String[],String> weatherCache;
    private Calendar cal;
    private SimpleDateFormat sdf;

    public WeatherCache(){
      weatherCache = new HashMap<String[],String>();
      weatherData = "";
      key = new String[3];
      cal = Calendar.getInstance();
      sdf = new SimpleDateFormat("HHddMMyyyy");
    }

    public String getCache(String lat, String lng){
        time = sdf.format(cal.getTime());
        key[0]=time;
        key[1]=lat;
        key[2]=lng;
        weatherData = weatherCache.get(key);
        return weatherData;
    }

    public void addCache(String lat, String lng, String data){
        time = sdf.format(cal.getTime());
        key[0]=time;
        key[1]=lat;
        key[2]=lng;
        weatherCache.put(key, data);
    }
    public void deleteCache(String time){
        ;
    }
}
