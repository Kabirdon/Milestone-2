

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class WatherApi {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            
            // Greet the user and ask for their name
            System.out.println("Hi there! What's your name?");
            String name = scanner.nextLine();
            System.out.println("how are you??");
            String hey=scanner.nextLine();
            // Respond to the user's input
            System.out.println("Nice to meet you, " + name + "!");
   
           
            System.out.println("What city would you like to get the weather for?");
         // Get the user's input for the city
            String city = scanner.nextLine();
         
            
            
          
            
            // Set the API endpoint URL and your API key
            String endpointUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=30fc7ee828b5d4f90ce78df1ac686aca&units=metric";
            
            // Create a new HTTP connection to the endpoint URL
            URL url = new URL(endpointUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Read the response from the API endpoint
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Parse the JSON response into a Java object
            JSONObject jsonObject = new JSONObject(response.toString());
            
            // Extract the temperature and weather description from the API response
            double temperature = jsonObject.getJSONObject("main").getDouble("temp");
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            String weatherDescription = weatherArray.getJSONObject(0).getString("description");
            
            // Determine clothing suggestion based on the temperature
            String clothingSuggestion;
            if (temperature < 10) {
                clothingSuggestion = "It's cold, wear a jacket and scarf.";
            } else if (temperature < 20) {
                clothingSuggestion = "It's cool, wear a light jacket or sweater.";
            } else {
                clothingSuggestion = "It's warm, wear a t-shirt and shorts.";
            }
            
            // Print the temperature, weather description, and clothing suggestion to the console
            System.out.println("The temperature in " + city + " is " + temperature + " degrees Celsius.");
            System.out.println("The weather outside is " + weatherDescription + ".");
            System.out.println(clothingSuggestion);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
