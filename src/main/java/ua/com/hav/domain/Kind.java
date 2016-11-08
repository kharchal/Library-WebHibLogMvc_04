package ua.com.hav.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


/**
 * Created by Юля on 17.10.2016.
 */
@Entity
@Table(name = "kinds")
public class Kind {

    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp = "^[a-z]+[s]{1}$")
    private String name;

    public Kind() {
    }

    public Kind(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kind{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
