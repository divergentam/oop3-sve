package rs.ac.ni.oop3.tamara333.vezbe_7_4;

import java.util.Arrays;
import java.util.List;

public class EmployeesRepository {
    public static final EmployeesRepository INSTANCE = new EmployeesRepository();

    private EmployeesRepository(){}

    private final List<Employee> _employers = Arrays.asList(
            Employee.builder().id(1).name("Pera").title(Employee.Title.CEO).salary(1000).build(),
            Employee.builder().id(1).name("Mika").title(Employee.Title.CTO).salary(900).build(),
            Employee.builder().id(1).name("Mara").title(Employee.Title.CTO).salary(1050).build(),
            Employee.builder().id(1).name("Sonja").title(Employee.Title.DEVELOPER).salary(800).build(),
            Employee.builder().id(1).name("Rada").title(Employee.Title.DEVELOPER).salary(680).build(),
            Employee.builder().id(1).name("Djoka").title(Employee.Title.DEVELOPER).salary(700).build(),
            Employee.builder().id(1).name("Sale").title(Employee.Title.QA).salary(700).build(),
            Employee.builder().id(1).name("Dule").title(Employee.Title.QA).salary(450).build(),
            Employee.builder().id(1).name("Tasa").title(Employee.Title.DEVELOPER).salary(950).build()

    );

    public List<Employee> findAll(){
        return _employers;
    }
}
