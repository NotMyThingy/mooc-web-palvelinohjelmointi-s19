package airports;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    public void createTest() {
        airportService.create("HEL", "Helsinki-Vantaa");
        System.out.println(airportService.list());
        Airport airport = airportRepository.findAll().get(0);
        assertEquals(airport.getIdentifier(), airportService.list().get(0).getIdentifier());
        assertEquals(airport.getName(), airportService.list().get(0).getName());
    }

    @Test
    public void listTest() {
        airportService.create("HEL", "Helsinki-Vantaa");
        airportService.create("STO", "Tukholma");
        airportService.create("OSL", "Oslo");

        assertEquals(3, airportService.list().size());
    }

    @Test
    public void airportAlreadyExists() {
        airportService.create("HEL", "Helsinki-Vantaa");
        airportService.create("STO", "Tukholma");
        airportService.create("HEL", "Helsinki-Vantaa");

        assertEquals(2, airportService.list().size());
    }
}
