package mainlogic;


import entities.DepCombination;
import entities.Department;
import filetools.FileHandler;
import filetools.FileParser;
import utils.MyCustomIOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TaskProcess {
    static void processFile(String inputFile, String outputFile, boolean append) {
        try {
            String fileStr = FileHandler.readFile(inputFile).toString();
            if (!FileParser.validateFile(fileStr)) {
                System.out.println("Sorry, input file format is invalid");
            } else {
                List<DepCombination> depCombs = processDepartments(FileParser.parseFile(fileStr));
                if (depCombs.size() != 0) {
                    String sb = depCombs.toString() + "Total number of combinations: " + depCombs.size() + "\n\n\n\n";
                    FileHandler.writeFile(outputFile, sb, append);
                }
            }
        } catch (MyCustomIOException e) {
            System.out.println("Sorry, unexpected IO error.");
            // should I log here or in FileHandler?
        }
    }

    private static List<DepCombination> processDepartments(List<Department> departments) {
        List<DepCombination> resList = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            for (int j = i + 1; j < departments.size(); j++) {
                resList.addAll(compareDepartments(departments.get(i), departments.get(j)));
            }
        }
        return resList;
    }

    private static List<DepCombination> compareDepartments(Department department1, Department department2) {
        List<DepCombination> resList = new ArrayList<>();
        // loop by number of elements for combinations (i-combinations from department1)
        for (int i = 0; i < department1.getStaffNumber(); i++) {
            List<int[]> dep1Combinations = getAllCombinations(department1.getStaffNumber(), i + 1);
            // loop by combinations of department1
            for (int[] q : dep1Combinations) {
                // loop by number of elements for combinations (i-combinations from department2)
                for (int j = 0; j < department2.getStaffNumber(); j++) {
                    List<int[]> dep2Combinations = getAllCombinations(department2.getStaffNumber(), j + 1);
                    // loop by combinations of department2
                    for (int[] k : dep2Combinations) {
                        Department dep1Combination = generateNewDepCombination(department1, department2, q, k);
                        Department dep2Combination = generateNewDepCombination(department2, department1, k, q);
                        double dep1OrigAvgSalary = department1.getAvgSalary();
                        double dep1CombAvgSalary = dep1Combination.getAvgSalary();
                        double dep2OrigAvgSalary = department2.getAvgSalary();
                        double dep2CombAvgSalary = dep2Combination.getAvgSalary();
                        if (dep1CombAvgSalary > dep1OrigAvgSalary && dep2CombAvgSalary > dep2OrigAvgSalary) {
                            resList.add(new DepCombination(department1, department2, dep1Combination, dep2Combination));
                        }
                    }
                }
            }
        }
        return resList;
    }

    private static List<int[]> getAllCombinations(int n, int k) {
        List<int[]> combinations = new ArrayList<>();
        int[] inputArr = new int[n];
        for (int i = 0; i < n; i++) {
            inputArr[i] = i;
        }
        getCombinations(combinations, inputArr, inputArr.length, k, 0, new int[k], 0);
        return combinations;
    }

    // this method is unconscionably stolen from the Internet
    private static void getCombinations(List<int[]> resultList, int arr[], int n, int k, int index, int data[], int i) {
        // Current combination is ready to be saved
        if (index == k) {
            resultList.add(Arrays.copyOf(data, data.length));
            return;
        }
        // When no more elements are there to put in data[]
        if (i >= n) {
            return;
        }
        // current is included, put next at next location
        data[index] = arr[i];
        getCombinations(resultList, arr, n, k, index + 1, data, i + 1);
        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        getCombinations(resultList, arr, n, k, index, data, i + 1);
    }

    private static Department generateNewDepCombination(Department dep1, Department dep2, int[] dep1ToExclude, int[] dep2ToInclude) {
        Department newDepComb = new Department(dep1.getName() + " combination with " + dep2.getName());
        newDepComb.addAll(dep1.getStaffByIndexesExceptOf(dep1ToExclude));
        newDepComb.addAll(dep2.getStaffByIndexes(dep2ToInclude));
        return newDepComb;
    }
}
