package entertainment.chatapp;

public interface ChatScreenInterface {
    public void sendMessage(String message);
    public void runOnUiThread(String message, boolean isSelf);
}
