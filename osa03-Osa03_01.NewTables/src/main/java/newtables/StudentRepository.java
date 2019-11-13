package newtables;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author notmythingy
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
