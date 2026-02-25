package org.acme.DomainInteraction;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.Domain.Student;
import org.acme.Domain.StudentDTO;

@ApplicationScoped
public class StudentMapper {
    public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        return student;
    }
}
