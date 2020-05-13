package edu.uark.registerapp.commands.employees;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.SortedEmployeeRepository;

@Service
public class SortedEmployeesQuery implements ResultCommandInterface<List<Employee>> {
    @Override
    public List<Employee> execute() {
		final LinkedList<Employee> employees = new LinkedList<Employee>();

		for (final EmployeeEntity employeeEntity : sortedEmployeeRepository.findAll(Sort.by(Sort.Direction.DESC, "quantitySold"))) {
			employees.addLast(new Employee(employeeEntity));
		}
		
		return employees;
	}
    
	public List<Employee> execute(String config) {
		final LinkedList<Employee> employees = new LinkedList<Employee>();
        Boolean direction;
        String columnName;
        switch(config){
            case "0":
                direction = true;
                columnName = "quantitySold";
                break;
            case "1":
                direction = false;
                columnName = "quantitySold";
                break;
            case "2":
                direction = true;
                columnName = "employeeSales";
                break;
            case "3":
                direction = false;
                columnName = "employeeSales";
                break;
            default:
                direction = true;
                columnName = "quantitySold";
                break;
        }
        if(direction == true){
            for (final EmployeeEntity employeeEntity : sortedEmployeeRepository.findAll(Sort.by(Sort.Direction.DESC, columnName))) {
                employees.addLast(new Employee(employeeEntity));
            }
        }
        else{
            for (final EmployeeEntity employeeEntity : sortedEmployeeRepository.findAll(Sort.by(Sort.Direction.ASC, columnName))) {
                employees.addLast(new Employee(employeeEntity));
            }
        }
		
		return employees;
    }

	@Autowired
	SortedEmployeeRepository sortedEmployeeRepository;
}
