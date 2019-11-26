package airports;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("airports"))
                .andExpect(model().attributeExists("aircrafts"));
    }

    @Test
    public void nameExists() throws Exception {
        String name = "HA-LOL";
        mockMvc.perform(post("/aircrafts")
                .param("name", name))
                .andExpect(redirectedUrl("/aircrafts"));

        List<Aircraft> aircrafts = aircraftRepository.findAll();

        boolean isInDB = aircrafts
                .stream()
                .anyMatch(port -> port.getName()
                .equals(name));

        assertTrue(isInDB);
    }

    @Ignore
    @Test
    public void aircraftXP55Exists() throws Exception {
        String name = "XP-55";
        mockMvc.perform(post("/aircrafts")
                .param("name", name))
                .andExpect(redirectedUrl("/aircrafts"));

        MvcResult res = mockMvc.perform(get("/aircrafts")).andReturn();

        List<Aircraft> aircrafts = (List) res.getModelAndView().getModel().get("aircrafts");

        boolean isInDB = aircrafts
                .stream()
                .anyMatch(aircraft -> aircraft.getName()
                .equals(name));

        assertTrue(isInDB);

    }
}
