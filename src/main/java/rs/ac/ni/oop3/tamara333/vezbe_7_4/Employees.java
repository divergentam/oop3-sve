package rs.ac.ni.oop3.tamara333.vezbe_7_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employees {
    private static EmployeesRepository _repository = EmployeesRepository.INSTANCE;

    // 4. ZADATAK - a
    public static double averageSalary(){
        return _repository.findAll().stream()
                .mapToInt(e -> e.getSalary())
                .average()
                .getAsDouble();


       /* final List<Employee> employees = _repository.findAll();

        int count = employees.size();
        if(count == 0){
            return 0;
        }

        int totalSalary = 0;
        for(Employee e : employees){
            totalSalary += e.getSalary();
        }
        return ((double)totalSalary) / count;*/
    }

    public static Map<Employee.Title, List<Employee>> groupByTitle(){
        final Map<Employee.Title, List<Employee>> map = new HashMap();

        for(final Employee employee : _repository.findAll()){
            final Employee.Title title = employee.getTitle();

            List<Employee> list = map.get(title);

            if(list == null){
                list = new ArrayList<>();
            }

            list.add(employee);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println("Average of salaries is: " + averageSalary());

        final Map<Employee.Title, List<Employee>> employersByTitle = groupByTitle();

        employersByTitle.forEach((key, value) -> {
                  System.out.println(key);
                  value.forEach(System.out::println);
                });

    }

}
