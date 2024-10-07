package framework.constants;

public enum Locations {
    ISTANBUL_TURKEY("Istanbul, Turkey");

    private final String location;

    Locations(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
