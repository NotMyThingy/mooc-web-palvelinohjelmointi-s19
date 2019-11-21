package filemanager;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileObject extends AbstractPersistable<Long> {

    private String name;
    private String contentType;
    private Long contentLength;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
