package entertainment.chatapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageToViewHolder extends RecyclerView.ViewHolder
        implements ViewHolderViewInterface {
    TextView userName;
    TextView userMessage;


    public MessageToViewHolder(View itemView) {
        super(itemView);
        //userName = (TextView)itemView
        userName = (TextView)itemView.findViewById(R.id.user_to_text_view);
        userMessage = (TextView)itemView.findViewById(R.id.user_to_message_text_view);
    }

    @Override
    public void setToUserName(String name) {
      userName.setText(name);

    }

    @Override
    public void setToMessage(String message) {
     userMessage.setText(message);
    }

    @Override
    public void setFromUserName(String name) {

    }

    @Override
    public void setFromMessage(String message) {

    }
}
