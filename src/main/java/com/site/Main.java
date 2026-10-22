
package com.site;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String password = loadDatabasePassword();

        Map<String, Object> settings = new HashMap<>();
        settings.put("jakarta.persistence.jdbc.password", password);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("studentPU", settings);

        try {
            Student student = new Student("Mohammed Ali", "Python");

            try (EntityManagerScope scope = new EntityManagerScope(factory)) {
                EntityManager em = scope.entityManager();
                em.getTransaction().begin();
                em.persist(student);
                em.getTransaction().commit();
                System.out.println("CREATE SUCCESS: " + student);
            }

            int id = student.getId();

            try (EntityManagerScope scope = new EntityManagerScope(factory)) {
                Student found = scope.entityManager().find(Student.class, id);
                System.out.println("READ SUCCESS: " + found);
            }

            try (EntityManagerScope scope = new EntityManagerScope(factory)) {
                EntityManager em = scope.entityManager();
                em.getTransaction().begin();

                Student found = em.find(Student.class, id);

                if (found == null) {
                    throw new IllegalStateException("Student not found: " + id);
                }

                found.setName("Alemayehu");
                em.getTransaction().commit();
                System.out.println("UPDATE SUCCESS: " + found);
            }

            try (EntityManagerScope scope = new EntityManagerScope(factory)) {
                EntityManager em = scope.entityManager();
                em.getTransaction().begin();

                Student found = em.find(Student.class, id);

                if (found == null) {
                    throw new IllegalStateException("Student not found: " + id);
                }

                em.remove(found);
                em.getTransaction().commit();
                System.out.println("DELETE SUCCESS: student ID " + id);
            }

            System.out.println("ALL FOUR CRUD OPERATIONS COMPLETED!");

        } finally {
            factory.close();
        }
    }

    private static String loadDatabasePassword() {
        try {
            java.util.Properties properties = new java.util.Properties();

            try (java.io.FileInputStream input =
                         new java.io.FileInputStream("db.properties")) {
                properties.load(input);
            }

            String password = properties.getProperty("db.password");

            if (password == null || password.isBlank()) {
                throw new IllegalStateException(
                        "Set db.password in db.properties");
            }

            return password;

        } catch (java.io.IOException e) {
            throw new IllegalStateException(
                    "Cannot read db.properties", e);
        }
    }

    private static class EntityManagerScope implements AutoCloseable {

        private final EntityManager em;

        EntityManagerScope(EntityManagerFactory factory) {
            em = factory.createEntityManager();
        }

        EntityManager entityManager() {
            return em;
        }

        @Override
        public void close() {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
