package browser;

public enum Browser {
    CHROME("chrome"),
    YANDEX("yandex");

    private final String value;

    Browser(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}