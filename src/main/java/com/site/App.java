package com.site;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class App {
    public static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void createStudent(Student student) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Student getStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        Student student = entityManager.find(Student.class, id);
        entityManager.close();
        return student;
    }

    public static void updateStudentName(String newName, int id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        student.setName(newName);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void updateStudentNameAndTech(String newName, String newTech, int id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        student.setName(newName);
        student.setTech(newTech);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteStudentById(int id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream("db.properties")) {
            properties.load(file);
        }
        Map<String, Object> settings = new HashMap<>();
        settings.put("jakarta.persistence.jdbc.password", properties.getProperty("db.password"));
        emf = Persistence.createEntityManagerFactory("studentPU", settings);

        try {
            Student belachew = new Student();
            belachew.setName("Belachew");
            belachew.setTech("Java");
            createStudent(belachew);

            int id = belachew.getId();
            System.out.println("CREATE: " + getStudentById(id));

            updateStudentName("Belachew Kebede", id);
            System.out.println("UPDATE NAME: " + getStudentById(id));

            updateStudentNameAndTech("Belachew Kebede", "Python", id);
            System.out.println("UPDATE NAME AND TECH: " + getStudentById(id));

            deleteStudentById(id);
            System.out.println("DELETE: " + id);
        } finally {
            emf.close();
        }
    }
}
