package tr.gov.sgk.restmvc.data;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int number;
}
