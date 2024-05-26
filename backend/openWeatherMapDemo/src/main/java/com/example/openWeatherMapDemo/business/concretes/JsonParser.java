package com.example.openWeatherMapDemo.business.concretes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.openWeatherMapDemo.entities.concretes.City;



public class JsonParser {
	
    public static String getJSONFromURL(String strUrl) {
        String jsonText = "";

        try {
            URL url = new URL(strUrl);
            InputStream is = url.openStream();

            BufferedReader bufferedReader = 
                            new BufferedReader(new InputStreamReader(is));
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText += line + "\n";
            }

            is.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return jsonText;
    }
    
    public static City parse(City city,String json){
        
        String strJson = json;
        
        //System.out.println(strJson);
        
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(strJson);
            JSONObject mainJsonObject = (JSONObject) object;
            
            
            /****************************COORD***************/
            
            JSONObject jsonObjectCoord = (JSONObject) mainJsonObject.get("coord");
            
            
            double lon = (double) jsonObjectCoord.get("lon");
            city.setLon(lon);
            
            double lat = (double) jsonObjectCoord.get("lat");
            city.setLat(lat);
            
            
            /**********************WEATHER****************/
            JSONArray jsonArrayWeather = (JSONArray) mainJsonObject.get("weather");
            System.out.println("Weather : ");
            
            JSONObject jsonObjectWeather = (JSONObject) jsonArrayWeather.get(0);
            
            String description = (String) jsonObjectWeather.get("description");
            city.setWeatherDescription(description);
            
            /****************MAIN*******************/
            JSONObject jsonObjectMain = (JSONObject) mainJsonObject.get("main");
            
            double temp = (double) jsonObjectMain.get("temp");
            city.setTemp(temp);
            
            double tempMin = (double) jsonObjectMain.get("temp_min");
            city.setMinTemp(tempMin);
            
            double tempMax = (double) jsonObjectMain.get("temp_max");
            city.setMaxTemp(tempMax);
            
            /**************SYS*************/
            JSONObject jsonObjectSys = (JSONObject) mainJsonObject.get("sys");
            
            String country = (String) jsonObjectSys.get("country");
            city.setCountry(country);

		}
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return city;
    }
}