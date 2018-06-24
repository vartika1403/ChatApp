package entertainment.chatapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MessageFromViewHolder extends RecyclerView.ViewHolder
        implements ViewHolderViewInterface{
    private static final String LOG_TAG = MessageFromViewHolder.class.getSimpleName();
    private TextView userName;
    private TextView userMessage;

    public MessageFromViewHolder(View itemView) {
        super(itemView);
        userName = (TextView)itemView.findViewById(R.id.user_from_text_view);
        userMessage = (TextView)itemView.findViewById(R.id.user_from_message_text_view);
    }

    @Override
    public void setToUserName(String name) {

    }

    @Override
    public void setToMessage(String message) {

    }

    @Override
    public void setFromUserName(String name) {
        Log.i(LOG_TAG, "name, " + name);
        userName.setText(name);
    }

    @Override
    public void setFromMessage(String messageFrom) {
        Log.i(LOG_TAG, "name, " + messageFrom);
       userMessage.setText(messageFrom);
    }
}
