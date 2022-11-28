package com.example.authorization.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "students")
public class Student {
    //generate table with id as auto_increment and primary
    private @Id
    @GeneratedValue
    long id;
    private String name;
    private LocalDate DoB;

    //make age virtually generated column
    @Transient
    private int age;

    public Student() {
    }

    public Student(long id, String name, LocalDate doB) {
        this.id = id;
        this.name = name;
        this.DoB = doB;
    }

    public Student(String name, LocalDate doB) {
        this.name = name;
        this.DoB = doB;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", DoB=" + DoB +
                ", age=" + age +
                '}';
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

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate doB) {
        DoB = doB;
    }

    //generate age
    public int getAge() {
        return Period.between(this.DoB, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
