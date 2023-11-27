package ait.employee.dao;

import ait.employee.model.Employee;
import ait.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompanyListImpl implements Company {
    private List<Employee> employees;
    private int capacity;

    public CompanyListImpl(int capacity) {
        this.capacity = capacity;
        employees = new ArrayList<>();
    }


    //0(n)
    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Employee Failed"); // бросили RuntimeException
        }

        if (capacity == employees.size() || employees.contains(employee)) {
            return false;
        }
        return employees.add(employee);
    }
     // 0(n)
    @Override
    public Employee removeEmployee(int id) {
        Employee employee = findEmployee(id);
        employees.remove(employee);
        return employee;
    }
     // 0(n)
    @Override
    public Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    // 0(n)
    @Override
    public double totalSalary() {
        double res = 0;
        for (Employee employee : employees) {
            res += employee.calcSalary();
        }
        return res;
    }
    //0(1)
    @Override
    public int quantity() {
        return employees.size();
    }
    //  0(n)
    @Override
    public double totalSales() {
        double res = 0;
        for (Employee employee : employees) {
            if (employee instanceof SalesManager) {
                SalesManager salesManager = (SalesManager) employee;
                res += salesManager.getSalesValue();
            }
        }
        return res;
    }
    // 0(n)
    @Override
    public void printEmployees() {
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
      employees.forEach(e -> System.out.println(e));
    }
     // 0(n)
    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        return findEmployeesByPredicate(e -> e.getHours() >= hours);
    }
    // 0(n)
    @Override
    public Employee[] findEmployeesSalaryRange(int minSalary, int maxSalary) {
        return findEmployeesByPredicate(e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary);
    }

    // 0(n)
    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        List<Employee> res = new ArrayList<>();
        for (Employee employee : employees) {
            if (predicate.test(employee)) {
                res.add(employee);
            }
        }
        return res.toArray(new Employee[0]);    // res.toArray(new Employee[res.size()]);
    }

}
