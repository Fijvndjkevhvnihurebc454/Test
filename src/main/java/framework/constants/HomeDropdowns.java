package framework.constants;

public enum HomeDropdowns {
    WHY_INSIDER("Why Insider"),
    PLATFORM("Platform"),
    SOLUTIONS("Solutions"),
    RESOURCES("Resources"),
    COMPANY("Company"),
    TAKE_TOUR("Take A Tour");

    private final String title;

    private HomeDropdowns(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
