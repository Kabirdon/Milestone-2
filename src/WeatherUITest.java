import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class WeatherUITest {

    private WeatherUI weatherUI;

    @Before
    public void setUp() {
        weatherUI = new WeatherUI();
    }

    @Test
    public void testValidInput() {
        weatherUI.nameField.setText("John");
        weatherUI.cityField.setText("London");
        weatherUI.submitButton.doClick();
        String resultText = weatherUI.resultLabel.getText();
        assertTrue(resultText.contains("Hi John!"));
        assertTrue(resultText.contains("The temperature in London is"));
        assertTrue(resultText.contains("degrees Celsius."));
    }

    @Test
    public void testInvalidInput() {
        weatherUI.nameField.setText("Jane");
        weatherUI.cityField.setText("");
        weatherUI.submitButton.doClick();
        String resultText = weatherUI.resultLabel.getText();
        assertEquals("An error occurred.", resultText);
    }

}

	    
	   
	    
	


	

	
