package tr.gov.sgk.restmvc.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.gov.sgk.restmvc.data.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentWebServiceImpl implements StudentWebService{
    private final RestTemplate restTemplate;
    private final String apiUrl;

    @Autowired
    public StudentWebServiceImpl(RestTemplate restTemplate, @Value("${rest.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }
    @Override
    public List<Student> getAllStudents() {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch students");
        }
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                apiUrl + "/" + id,
                Student.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return Optional.empty();
        } else {
            throw new RuntimeException("Failed to fetch student with id: " + id);
        }
    }

    @Override
    public Student createStudent(Student student) {
        ResponseEntity<Student> response = restTemplate.postForEntity(
                apiUrl,
                student,
                Student.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to create student");
        }
    }

    @Override
    public Student updateStudent(int id, Student student) {
        ResponseEntity<Student> response = restTemplate.exchange(
                apiUrl + "/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(student),
                Student.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to update student with id: " + id);
        }
    }

    @Override
    public void deleteStudent(int id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
