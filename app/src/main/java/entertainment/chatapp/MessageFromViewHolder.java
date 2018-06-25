package entertainment.chatapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MessageFromViewHolder extends RecyclerView.ViewHolder
        implements ViewHolderViewInterface{
    private static final String LOG_TAG = MessageFromViewHolder.class.getSimpleName();
    private TextView userMessage;

    public MessageFromViewHolder(View itemView) {
        super(itemView);
        userMessage = (TextView)itemView.findViewById(R.id.user_from_message_text_view);
    }

    @Override
    public void setToMessage(String message) {

    }

    @Override
    public void setFromMessage(String messageFrom) {
        Log.i(LOG_TAG, "name, " + messageFrom);
        userMessage.invalidate();
        userMessage.setVisibility(View.VISIBLE);
        userMessage.setText(messageFrom);
    }
}
