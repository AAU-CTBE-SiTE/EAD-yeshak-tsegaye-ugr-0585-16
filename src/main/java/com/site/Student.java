package com.site;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Student {

    private int id;
    private String name;
    private int age;

    private Address address;
    private Subject[] subjects;

    public void getDetails() {
        System.out.println("Student Name: " + this.getName());

        int index = 1;
        for (Subject subject : subjects) {
            System.out.println("Subject " + index + ": "
                    + subject.getSubjectName());
            index++;
        }
    }
}
