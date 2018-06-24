package entertainment.chatapp;

public interface ChatScreenInterface {
    void sendMessage(String message);
    void runOnUiThread(String message, boolean isSelf);
}
