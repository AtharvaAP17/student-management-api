package com.example.studentapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentRepository repository;
    private StudentService service;

    @BeforeEach
    public void setUp() {
        repository = mock(StudentRepository.class);
        service = new StudentService(repository);
    }

    @Test
    public void testFindAllStudents() {
        List<Student> mockStudents = Arrays.asList(
            new Student("Alice", "alice@example.com", "Math", 3.9),
            new Student("Bob", "bob@example.com", "CS", 3.7)
        );
        when(repository.findAll()).thenReturn(mockStudents);

        List<Student> result = service.findAll();
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    public void testFindById_StudentExists() {
        Student student = new Student("Alice", "alice@example.com", "Math", 3.9);
        when(repository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student("Charlie", "charlie@example.com", "Physics", 3.6);
        when(repository.save(student)).thenReturn(student);

        Student saved = service.save(student);
        assertNotNull(saved);
        assertEquals("Charlie", saved.getName());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
