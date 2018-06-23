package entertainment.chatapp.model;

public class MessageChat {
    private String id;
    private String text;
    private String name;
    private boolean isSelf;

    public MessageChat() {

    }
    public MessageChat(String name ,String text, boolean isSelf) {
        this.name = name;
        this.text = text;
        this.isSelf = isSelf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }
    public boolean isSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
}
