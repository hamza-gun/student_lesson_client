package tr.gov.sgk.restmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.gov.sgk.restmvc.data.Student;
import tr.gov.sgk.restmvc.webservice.StudentWebService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentWebService studentWebService;

    public StudentServiceImpl(StudentWebService studentWebService) {
        this.studentWebService = studentWebService;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentWebService.getAllStudents();
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return studentWebService.getStudentById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentWebService.createStudent(student);
    }

    @Override
    public Student updateStudent(int id, Student student) {
        return studentWebService.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(int id) {
        studentWebService.deleteStudent(id);
    }
}
