package movies;

import static org.fluentlenium.core.filter.FilterConstructor.containingText;
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
public class MovieTest extends org.fluentlenium.adapter.junit.FluentTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void canAddMovieAndAssignActorsToIt() {

        // Go to /movies page
        goTo("http://localhost:" + port + "/movies");
        // Check movie doesn't exist
        assertFalse(pageSource().contains("Uuno Epsanjassa"));
        // Check actor doesn't exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        // Find field with id - "name" and add movie to it.
        find("#name").fill().with("Uuno Epsanjassa");
        // Find field with id - "lengthInMinutes" and add value "92" to it.
        find("#lengthInMinutes").fill().with("92");
        // Submit the form 
        find("form").first().submit();
        // Check, text "Uuno Espanjassa" exists
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        // Check, text "Uuno Turhapuro" does not exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        // Go to actors page
        goTo("http://localhost:" + port + "/actors");
        // Check, text "Uuno Turhapuro" does not exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        // Find field with id - "name" and insert "Uuno Turhapuro" into it.
        find("#name").fill().with("Uuno Turhapuro");
        // Submit the form
        find("form").first().submit();
        // Check, text "Uuno Turhapuro" exists
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        // Find link with text "Uuno Turhapuro" and click it
        find("a", containingText("Uuno Turhapuro")).click();
        // Find a button with id - "add-to-movie" and click it.
        find("#add-to-movie").click();
        // Go to movies page
        goTo("http://localhost:" + port + "/movies");
        // Check, text "Uuno Espanjassa" exists
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        // Check, text "Uuno Turhapuro" exists
        assertTrue(pageSource().contains("Uuno Turhapuro"));
    }

}
