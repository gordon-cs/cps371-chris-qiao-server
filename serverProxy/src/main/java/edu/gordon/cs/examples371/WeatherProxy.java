/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.gordon.cs.examples371;
// Client side:
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// Server side:
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WeatherProxy extends HttpServlet {
  private static WeatherCache cache = new WeatherCache();
  String weatherNow = "";
  String lat, lng;

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    resp.setHeader("content-type", "application/json; charset=utf-8");
    PrintWriter out = resp.getWriter();
    String uri = req.getRequestURI();
    String delims = "[,]";
    String [] tokens = uri.split(delims);
    lat = tokens[0];
    lng = tokens[1];

    weatherNow = cache.getCache(lat,lng);
    if(weatherNow==null){
      this.getWeather(lat, lng);
      cache.addCache(lat,lng,weatherNow);
    }
      out.write(weatherNow);
    }

  private void getWeather( String lat, String lng) {
    String apiUrl = "https://api.forecast.io/forecast/";
    String apiKey = "14e723fbe931ee119ade496aabcf28ba";
  //  String lat = "42.589611";
  //  String lng = "-70.819806";
    String urlString = apiUrl + apiKey + lat + "," + lng;

    try {
      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      conn.connect();

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
                                   + conn.getResponseCode());
      }

      // The body of the response is available as an input stream.
      // Using BufferedReader is good practice for efficient I/O, because it
      // takes lots of little reads and does fewer larger actual I/O
      // operations.  It doesn't make much difference in this case,
      // since we only do one I/O.  But it's still good practice.
      BufferedReader br = new BufferedReader(new InputStreamReader(
                                               (conn.getInputStream())));
      // api.forecast.io returns a single (very long) line of JSON.
      weatherNow = br.readLine();

    } catch (MalformedURLException e) {
      e.printStackTrace();
      this.weatherNow = "{ 'error': 'MalformedURLException' }";
    } catch (IOException e) {
      e.printStackTrace();
      this.weatherNow = "{ 'error': 'IOException' }";
    }
  }
}
