import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WatherApiTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Test
	public void testMain() {
        String input = "John\nHello\nNew York\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        WatherApi.main(new String[] {});
        
        // Assert that the output contains the expected strings
        assertTrue(systemOut().contains("Nice to meet you, John!"));
        assertTrue(systemOut().contains("What city would you like to get the weather for?"));
        assertTrue(systemOut().contains("The temperature in New York is"));
        assertTrue(systemOut().contains("The weather outside is"));
    }

    // Helper method to get the system output as a string
    private String systemOut() {
        return outContent.toString();
    }
	    


	
	

}
