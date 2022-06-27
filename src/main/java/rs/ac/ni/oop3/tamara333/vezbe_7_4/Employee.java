package rs.ac.ni.oop3.tamara333.vezbe_7_4;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Employee {
    long id;
    String name;
    Title title;
    int salary;

    public enum Title{
        CEO,
        CTO,
        DEVELOPER,
        QA
    }
}
