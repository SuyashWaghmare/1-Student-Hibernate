package com.ssw;

import com.ssw.config.HibernateUtil;
import com.ssw.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;


public class Main {
    static Transaction transaction = null;
    static Scanner sc = new Scanner(System.in);


    public static void createData() {
        Student student = new Student("Suyash", "Waghmare", "suyash@gmail.com");
        Student student1 = new Student("Hailey", "Biber", "hailey@gmail.com");
        Student student2 = new Student("Cristiano", "Ronaldo", "ronaldo@gmail.com");


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            session.persist(student1);
            session.persist(student2);

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static void retriveAlldata() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery("from Student", Student.class).list();
            // students.forEach(s -> System.out.println(s.getFirstName()));
            for (Student student : students) {
                System.out.println("Student ID : " + student.getId());
                System.out.println("Student First Name : " + student.getFirstName());
                System.out.println("Student Last Name : " + student.getLastName());
                System.out.println("Student Email  : " + student.getEmail());
                System.out.println();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void retriveSingledata() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            System.out.println("Enter Student ID to get single data");
            Student student = session.get(Student.class, sc.nextInt());
            System.out.println("Student ID : " + student.getId());
            System.out.println("Student First Name : " + student.getFirstName());
            System.out.println("Student Last Name : " + student.getLastName());
            System.out.println("Student Email  : " + student.getEmail());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public static void deleteSingledata() {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            System.out.println("Enter Student ID to delete single data");
            int id = sc.nextInt();
            Query query = session.createQuery("delete from Student where id = " + id);
            int i = query.executeUpdate();
            System.out.println("Student ID deleted: " + id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateSingedata() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.println("Enter Student ID to get Update data");


            Student student = session.get(Student.class, sc.nextInt());

            System.out.println("enter first name");
            student.setFirstName(sc.next());

            System.out.println("enter last name");
            student.setLastName(sc.next());

            System.out.println("enter email");
            student.setEmail(sc.next());


            System.out.println("Student ID : " + student.getId());
            System.out.println("Student First Name : " + student.getFirstName());
            System.out.println("Student Last Name : " + student.getLastName());
            System.out.println("Student Email  : " + student.getEmail());

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello Application Started!");

        // createData();
        // retriveAlldata();
        //retriveSingledata();
        //deleteSingledata();
        //updateSingedata();


    }
}
















