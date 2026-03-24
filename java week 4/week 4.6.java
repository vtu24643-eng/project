//Employee.java
package com.yourpackage;
public class Employee {
    private int id;
    private String name;
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString() {
        return id + " - " + name;
    }
}

//EmployeeService.java
package com.yourpackage;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component   
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    public void addEmployee(int id, String name) {
        employees.add(new Employee(id, name));
    }
    public List<Employee> getEmployees() {
        return employees;
    }
}

//MainApp.java (BeanFactory + DI)
package com.yourpackage;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class EmployeeController {
    @Autowired   
    private EmployeeService employeeService;
    public void run() {
        employeeService.addEmployee(1, "Pavan");
        employeeService.addEmployee(2, "Reddy");
        System.out.println("Employee List:");
        for (Employee e : employeeService.getEmployees()) {
            System.out.println(e);
        }
    }
}
public class MainApp {
    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
        EmployeeController controller = factory.getBean(EmployeeController.class);
        controller.run();
    }
}
