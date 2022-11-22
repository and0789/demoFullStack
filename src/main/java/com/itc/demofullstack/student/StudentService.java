package com.itc.demofullstack.student;

import com.itc.demofullstack.student.exception.BadRequestException;
import com.itc.demofullstack.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void addStudent(Student student) {
        Boolean existsEmail = studentRepository.selectExistEmail(student.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + student.getEmail() + " taken"
            );
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "student with id " + studentId + " does not exist"
            );
        }

        studentRepository.deleteById(studentId);
    }
}
