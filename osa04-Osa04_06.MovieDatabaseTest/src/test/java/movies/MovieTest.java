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
        //    Submit the form
        find("form").first().submit();
        //    Tarkistetaan että sivulla on teksti "Uuno Epsanjassa"
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        //    Tarkistetaan ettei sivulla ole tekstiä "Uuno Turhapuro"
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        //    Mennään näyttelijäsivulle
        goTo("http://localhost:" + port + "/actors");
        //    Tarkistetaan ettei sivulla ole tekstiä "Uuno Turhapuro"
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        //    Etsitään kenttä, jonka id on "name" ja asetetaan kenttään teksti "Uuno Turhapuro"
        find("#name").fill().with("Uuno Turhapuro");
        //    Lähetetään lomake
        find("form").first().submit();
        //    Tarkistetaan että sivulla on teksti "Uuno Turhapuro"
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        //    Etsitään linkki, jossa on teksti "Uuno Turhapuro" ja klikataan sitä
        find("a", containingText("Uuno Turhapuro")).click();
        //    Etsitään nappi, jonka id on "add-to-movie" ja klikataan sitä.
        find("#add-to-movie").click();
        //    Mennään elokuvasivulle
        goTo("http://localhost:" + port + "/movies");
        //    Tarkistetaan että sivulla on teksti "Uuno Epsanjassa"
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        //    Tarkistetaan että sivulla on teksti "Uuno Turhapuro"
        assertTrue(pageSource().contains("Uuno Turhapuro"));
    }

}
