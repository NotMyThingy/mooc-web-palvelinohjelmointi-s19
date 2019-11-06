package persondatabase;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author notmythingy
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
