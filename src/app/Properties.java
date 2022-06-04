package app;

// apply polymorphism
public abstract class Properties {
    private String text;
    private Boolean bool;

    public String getText() {
        return text;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public void setText(String text) {
        this.text = text;
    }

}