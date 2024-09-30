package framework.constants;

public enum Locations {
    INSTANBUL_TURKEY("Istanbul, Turkey"),
    PARIS_FRANCE("Paris, France"),
    LONDON_UK("London, United Kingdom"),
    UNITED_STATES("United States"),
    BERLIN_GERMANY("Berlin, Germany");

    private final String location;

    private Locations(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
