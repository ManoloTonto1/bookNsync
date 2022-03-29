package app;
public interface Subscriber {
   public String type = "";

    public void update(String context, String content);
}
