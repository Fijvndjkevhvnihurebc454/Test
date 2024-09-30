package framework.constants;

public enum Departments {
    BUSINESS_DEVELOPMENT("Business Development"),
    MOBILE_DEVELOPMENT("Mobile Development"),
    PRODUCT_MANAGEMENT("Product Management"),
    QUALITY_ASSURANCE("Quality Assurance"),
    SOFTWARE_DEVELOPMENT("Software Development");

    private final String department;

    private Departments(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }
}
