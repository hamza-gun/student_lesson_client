package tr.gov.sgk.restmvc.webservice;

import tr.gov.sgk.restmvc.data.Student;

import java.util.List;
import java.util.Optional;

public interface StudentWebService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(int id);
    Student createStudent(Student student);
    Student updateStudent(int id, Student student);
    void deleteStudent(int id);
}
