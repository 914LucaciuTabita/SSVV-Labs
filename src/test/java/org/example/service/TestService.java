package org.example.service;

import org.example.TestClass;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.ValidationException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertThrows;

public class TestService {
    public static Service service;

    @BeforeAll
    public static void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        TestClass.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void testAddStudentWithValidData() {
        // Arrange
        Student student = new Student("12345", "John Doe", 5, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithEmptyID() {
        // Arrange
        Student student = new Student("", "John Doe", 5, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithEmptyName() {
        // Arrange
        Student student = new Student("12345", "", 5, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithNegativeGroup() {
        //tested
        // Arrange
        Student student = new Student("12345", "John Doe", -5, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithPositiveGroup() {
        //tested
        // Arrange
        Student student = new Student("12345", "John Doe", 1023, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithNullEmail() {
        // Arrange
        Student student = new Student("12345", "John Doe", 5, null);
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithEmptyEmail() {
        // Arrange
        Student student = new Student("12345", "John Doe", 5, "");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithMaxGroup() {
        // Arrange
        Student student = new Student("12345", "John Doe", Integer.MAX_VALUE, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithMaxGroupMinusOne() {
        // Arrange
        Student student = new Student("12345", "John Doe", Integer.MAX_VALUE - 1, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithMaxGroupPlusOne() {
        // Arrange
        Student student = new Student("12345", "John Doe", Integer.MAX_VALUE + 1, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithMinGroup() {
        // Arrange
        Student student = new Student("12345", "John Doe", 0, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithMinMinusOneGroup() {
        // Arrange
        Student student = new Student("12345", "John Doe", -1, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithMinPlusOneGroup() {
        // Arrange
        Student student = new Student("12345", "John Doe", 1, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithValidEmail() {
        // Arrange
        Student student = new Student("12345", "John Doe", 1, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertDoesNotThrow(() -> validator.validate(student));
    }

    // White box testing
    @Test
    public void test_addTema_Invalid_deadline_smallerThan1_ThrowsError() {

        String nrTema = "100";
        String descriere = "test";
        int deadline = 0;
        int primire = 11;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(false);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(true);

        }

    }

    @Test
    public void test_addTema_Invalid_deadline_greaterThan14_ThrowsError() {

        String nrTema = "100";
        String descriere = "test";
        int deadline = 15;
        int primire = 11;

        Tema tema = new Tema(nrTema, descriere, deadline, primire );

        try{
            service.addTema(tema);
            assert(false);

        }catch (ValidationException ve){
            System.out.println("Validation Exception: " + ve.getMessage());
            assert(true);

        }

    }
}
