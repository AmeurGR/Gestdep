package com.example.Gestdep.professor;

import jakarta.persistence.*;

@Entity
@Table
public class Professor {


    @Id
    @SequenceGenerator(
            name ="professor_sequence",
            sequenceName = "professor_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "professor_sequence"
    )
    private long id;
    private String name;
    private int age;
    private String email;

    public Professor() {
    }

    public Professor(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Professor(long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
