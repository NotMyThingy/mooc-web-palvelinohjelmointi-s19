package newtables;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author notmythingy
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AbstractPersistable<Long> {

    private String first_name;
    private String last_name;
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
}
