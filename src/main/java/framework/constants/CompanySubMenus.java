package framework.constants;

public enum CompanySubMenus {
    INTEGRATIONS("Integrations"),
    CAREERS("Careers"),
    PARTNERSHIPS("Partnerships"),
    NEWSROOM("Newsroom");

    private final String title;

    private CompanySubMenus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
