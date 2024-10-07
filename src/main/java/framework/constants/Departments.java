package framework.constants;

public enum Departments {
    QUALITY_ASSURANCE("Quality Assurance");

    private final String department;

    Departments(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }
}
