package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Code {
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private long id;

    //@Generated(value = GenerationTime.INSERT)
    @Column(unique = true, updatable = false)
    @JsonIgnore
    private UUID uuid;

    @Column
    private String code;

    @Column
    //@CreationTimestamp
    @JsonIgnore
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "lastModified")
    //@UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDateTime date = dateCreated;

    @Column
    private long time;

    @Column
    private long views;

    //@Generated(value = GenerationTime.INSERT)
    @Column(updatable = false)
    @JsonIgnore
    private boolean timeRestricted;

    //@Generated(value = GenerationTime.INSERT)
    @Column(updatable = false)
    @JsonIgnore
    private boolean viewRestricted;

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public boolean isViewRestricted() {
        return viewRestricted;
    }
}
