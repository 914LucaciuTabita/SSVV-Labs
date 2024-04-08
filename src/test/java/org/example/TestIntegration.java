package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.ValidationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegration {
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml = new File("fisiere/studentiTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        File xml2 = new File("fisiere/temeTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml2))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        File xml3 = new File("fisiere/noteTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml3))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo("fisiere/studentiTest.xml");
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo("fisiere/temeTest.xml");
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo("fisiere/noteTest.xml");
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @AfterAll
    public static void teardown() {

        new File("fisiere/studentiTest.xml").delete();
        new File("fisiere/temeTest.xml").delete();
        new File("fisiere/noteTest.xml").delete();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("333", "Ana", 931, "ana@gmail.com");
        assertNull(service.addStudent(student));
    }

    @Test
    public void testAddTema() {
        Tema tema = new Tema("333", "a", 1, 1);
        assertNull(service.addTema(tema));
    }

    @Test
    public void testAddGrade() {

        Nota nota = new Nota("333", "333", "333", 10, LocalDate.now());
        assertEquals(service.addNota(nota, "bine"), 10.0);

        service.deleteNota("333");
        service.deleteStudent("333");
        service.deleteTema("333");
    }

    @Test
    public void testAddStudentTemaGrade() {

        Student student = new Student("222", "Ana", 934, "ana@gmail.com");
        Tema tema = new Tema("222", "a", 1, 1);
        Nota nota = new Nota("222", "222", "222", 10, LocalDate.now());

        assertNull(service.addStudent(student));
        assertNull(service.addTema(tema));
        assertEquals(service.addNota(nota, "bine"), 10.0);

        service.deleteNota("333");
        service.deleteStudent("333");
        service.deleteTema("333");
    }

    public static class TemaBuilder {
        private String nrTema = "111";
        private String descriere = "testDescriere";
        private int deadline = 6;
        private int primire = 5;

        public TemaBuilder setNrTema(String nrTema) {
            this.nrTema = nrTema;
            return this;
        }

        public TemaBuilder setDescriere(String descriere) {
            this.descriere = descriere;
            return this;
        }

        public TemaBuilder setDeadline(int deadline) {
            this.deadline = deadline;
            return this;
        }

        public TemaBuilder setPrimire(int primire) {
            this.primire = primire;
            return this;
        }

        public Tema createTema() {
            return new Tema(nrTema, descriere, deadline, primire);
        }
    }

    public static class StudentBuilder {
        private String idStudent = "111";
        private String nume = "Andrei";
        private int grupa = 937;
        private String email = "test@test.com";

        public StudentBuilder setIdStudent(String idStudent) {
            this.idStudent = idStudent;
            return this;
        }

        public StudentBuilder setNume(String nume) {
            this.nume = nume;
            return this;
        }

        public StudentBuilder setGrupa(int grupa) {
            this.grupa = grupa;
            return this;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Student createStudent() {
            return new Student(idStudent, nume, grupa, email);
        }
    }

    public static class NotaBuilder {
        private String id = "111";
        private String idStudent = "111";
        private String idTema = "111";
        private double nota = 10;
        private LocalDate data = LocalDate.of(2021, 4, 1);

        public NotaBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public NotaBuilder setIdStudent(String idStudent) {
            this.idStudent = idStudent;
            return this;
        }

        public NotaBuilder setIdTema(String idTema) {
            this.idTema = idTema;
            return this;
        }

        public NotaBuilder setNota(double nota) {
            this.nota = nota;
            return this;
        }

        public NotaBuilder setData(LocalDate data) {
            this.data = data;
            return this;
        }

        public Nota createNota() {
            return new Nota(id, idStudent, idTema, nota, data);
        }
    }

}
