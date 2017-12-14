package entities;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> staff;

    public Department() {
        name = "Default name";
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, List<Employee> staff) {
        this.name = name;
        this.staff = staff;
    }

    public List<Employee> addEmployee(Employee newEmployee) {
        if (staff == null) {
            staff = new ArrayList<>();
        }
        staff.add(newEmployee);
        return staff;
    }

    public List<Employee> addAll(List<Employee> newEmployees) {
        if (staff == null) {
            staff = new ArrayList<>();
        }
        staff.addAll(newEmployees);
        return staff;
    }

    public List<Employee> getStaffByIndexes(int[] includeIndexes) {
        List<Employee> newList = new ArrayList<>();
        for (int staffIndex = 0; staffIndex < staff.size(); staffIndex++) {
            for (int includeIndex : includeIndexes) {
                if (staffIndex == includeIndex) {
                    newList.add(staff.get(staffIndex));
                    break;
                }
            }
        }
        return newList;
    }

    public List<Employee> getStaffByIndexesExceptOf(int[] excludeIndexes) {
        List<Employee> newList = new ArrayList<>();
        outer:
        for (int staffIndex = 0; staffIndex < staff.size(); staffIndex++) {
            for (int excludeIndex : excludeIndexes) {
                if (staffIndex == excludeIndex) {
                    continue outer;
                }
            }
            newList.add(staff.get(staffIndex));
        }
        return newList;
    }

    public int getStaffNumber() {
        return staff.size();
    }

    public double getAvgSalary() {
        double res = 0.0;
        for (Employee e : staff) {
            res += e.getSalary();
        }
        return res / staff.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", staff=" + staff +
                '}';
    }
}