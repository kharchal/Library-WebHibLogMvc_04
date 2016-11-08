package ua.com.hav.domain;


import org.hibernate.annotations.Generated;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Юля on 13.10.2016.
 */
//@Entity
@Table(name = "pets")
public class Pet {
//    private static long cnt;

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, max = 5)
    private String name;

    @Range(min = 1, max = 6)
    private int age;

    private Kind kind;

    public Pet() {

//        this.id = ++cnt;
    }

    public Pet(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public Pet(String name, int age, Kind kind) {
        this(name, age);
        this.kind = kind;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", kind=" + kind +
                '}';
    }
}
