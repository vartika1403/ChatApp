package entertainment.chatapp.model;

public class MessageChat {
    private String id;
    private String mesage;
    private String name;
    private boolean isSelf;

    public MessageChat(String message, boolean b) {
        this.mesage = message;

    }
    public MessageChat(String name ,String text, boolean isSelf) {
        this.name = name;
        this.mesage = text;
        this.isSelf = isSelf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isSelf() {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
}
