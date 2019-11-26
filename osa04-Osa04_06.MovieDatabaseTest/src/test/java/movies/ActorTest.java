package movies;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorTest extends org.fluentlenium.adapter.junit.FluentTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void canAddActorOnlyOnce() {

        // Go to page /actors
        goTo("http://localhost:" + port + "/actors");

        // Check 'Johnny Weissmuller' doesn't exist
        assertFalse(pageSource().contains("Johnny Weissmuller"));

        // Search and fill text field with id - #name
        find("#name").fill().with("Johnny Weissmuller");

        // Send the form
        find("form").first().submit();

        // Ensure name now exists
        assertTrue(pageSource().contains("Johnny Weissmuller"));

    }

}
