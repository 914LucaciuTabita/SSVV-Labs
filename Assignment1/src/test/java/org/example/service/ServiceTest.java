package org.example.service;

import org.example.domain.Student;
import org.example.validation.StudentValidator;
import org.example.validation.ValidationException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class ServiceTest {
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
        // Arrange
        Student student = new Student("12345", "John Doe", -5, "john@example.com");
        StudentValidator validator = new StudentValidator();

        // Act & Assert
        assertThrows(ValidationException.class, () -> validator.validate(student));
    }

    @Test
    public void testAddStudentWithPositiveGroup() {
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
}
