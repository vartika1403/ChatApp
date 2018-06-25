package entertainment.chatapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageToViewHolder extends RecyclerView.ViewHolder
        implements ViewHolderViewInterface {
    private TextView userMessage;


    public MessageToViewHolder(View itemView) {
        super(itemView);
        userMessage = (TextView)itemView.findViewById(R.id.user_to_message_text_view);
    }


    @Override
    public void setToMessage(String message) {
         userMessage.invalidate();
         userMessage.setVisibility(View.VISIBLE);
         userMessage.setText(message);
    }

    @Override
    public void setFromMessage(String message) {

    }
}
