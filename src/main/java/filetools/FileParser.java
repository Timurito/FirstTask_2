package filetools;


import entities.Department;
import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private static final String FILE_PATTERN = "((\\s*\".*\\u041E\\u0442\\u0434\\u0435\\u043B .*\"\\R)([^\"].* (?!-)\\d+\\s*)+)+"; // влияют ли как-то ?: на производительность?
    private static final String DEPARTMENT_PATTERN = "(\".*\\u041E\\u0442\\u0434\\u0435\\u043B .*\"\\R)([^\"].* \\d+\\s*)+";

    public static boolean validateFile(String fileStr) {
        return Pattern.compile(FILE_PATTERN).matcher(fileStr).matches();
    }

    public static List<Department> parseFile(String fileStr) {
        List<Department> resList = new ArrayList<>();
        Pattern filePattern = Pattern.compile(DEPARTMENT_PATTERN);
        Matcher matcher = filePattern.matcher(fileStr);
        while (matcher.find()) {
            resList.add(parseDepartment(matcher.group()));
        }
        return resList;
    }

    private static Department parseDepartment(String fileStr) {
        fileStr = fileStr.trim();
        Scanner scan = new Scanner(fileStr);
        Department newDept = new Department(scan.nextLine().replaceAll("\"", "").trim());
        while (scan.hasNextLine()) {
            String newEmployeeStr = scan.nextLine().replaceAll("\\s+", " ").trim();
            String fullName = newEmployeeStr.substring(0, newEmployeeStr.lastIndexOf(' '));
            double salary = Double.parseDouble(newEmployeeStr.substring(fullName.length() + 1, newEmployeeStr.length()));
            newDept.addEmployee(new Employee(fullName, salary));
        }
        return newDept;
    }
}
