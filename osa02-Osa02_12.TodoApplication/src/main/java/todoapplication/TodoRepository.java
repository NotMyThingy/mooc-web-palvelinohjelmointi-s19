package todoapplication;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author notmythingy
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
