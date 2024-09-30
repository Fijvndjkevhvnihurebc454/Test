package framework.constants;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String browser;

    private Browser(String browser) {
        this.browser = browser;
    }

    public String getBrowserType() {
       return this.browser;
    }
}
