
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherUI extends JFrame implements ActionListener {

    // Declare components
    private JLabel nameLabel, cityLabel;
	JLabel resultLabel;
    JTextField nameField;
	JTextField cityField;
    JButton submitButton;

    public WeatherUI() {
        super("Weather App");

        // Create components
        nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        cityLabel = new JLabel("Enter the city:");
        cityField = new JTextField(20);
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");

        // Set layout and add components
        setLayout(new GridLayout(3, 2));
        add(nameLabel);
        add(nameField);
        add(cityLabel);
        add(cityField);
        add(submitButton);
        add(resultLabel);

        // Add action listener to submit button
        submitButton.addActionListener(this);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                // Get user input
                String name = nameField.getText();
                String city = cityField.getText();

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

                // Display the results to the user
                resultLabel.setText("<html>Hi " + name + "!<br>The temperature in " + city + " is " + temperature + " degrees Celsius.<br>The weather outside is " + weatherDescription + ".<br>" + clothingSuggestion + "</html>");
            } catch (Exception ex) {
                ex.printStackTrace();
                resultLabel.setText("An error occurred.");
            }
        }
    }

    public static void main(String[] args) {
        new WeatherUI();
    }
}
