package framework.constants;

public enum CompanySubMenus {
    CAREERS("Careers");

    private final String title;

    CompanySubMenus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
