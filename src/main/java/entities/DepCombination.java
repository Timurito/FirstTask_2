package entities;

public class DepCombination {
    private Department origDep1;
    private Department origDep2;
    private Department combDep1;
    private Department combDep2;

    public DepCombination(Department origDep1, Department origDep2, Department combDep1, Department combDep2) {
        this.origDep1 = origDep1;
        this.origDep2 = origDep2;
        this.combDep1 = combDep1;
        this.combDep2 = combDep2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Combination of ").append(origDep1.getName()).append(" with ").append(origDep2.getName()).append("\n")
                .append("Original dep1:\n").append(origDep1).append("\n")
                .append("Original dep2:\n").append(origDep2).append("\n")
                .append("Combinational dep1:\n").append(combDep1).append("\n")
                .append("Combinational dep2:\n").append(combDep2).append("\n")
                .append("Original dep1 avg salary: ").append(origDep1.getAvgSalary()).append("\n")
                .append("Combinational dep1 avg salary: ").append(combDep1.getAvgSalary()).append("\n")
                .append("Original dep2 avg salary: ").append(origDep2.getAvgSalary()).append("\n")
                .append("Combinational dep2 avg salary: ").append(combDep2.getAvgSalary()).append("\n\n");
        return sb.toString();
    }
}