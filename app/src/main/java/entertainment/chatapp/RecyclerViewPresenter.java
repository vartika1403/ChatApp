package entertainment.chatapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entertainment.chatapp.adapter.MessageAdapter;
import entertainment.chatapp.model.MessageChat;

public class RecyclerViewPresenter implements RecyclerViewPresenterInterface {
    private List<MessageChat> chatUsersList;
    private MessageAdapter messageAdapter;
    private ChatScreenInterface chatScreenInterface;


    public RecyclerViewPresenter(ChatScreenInterface chatScreenInterface, RecyclerView recyclerView) {
        this.chatScreenInterface = chatScreenInterface;
        chatUsersList = new ArrayList<MessageChat>();
        messageAdapter = new MessageAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) chatScreenInterface,
                OrientationHelper.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messageAdapter);

    }

    @Override
    public void sendMessageToAdapter(String message, boolean isSelf) {
     MessageChat chatUser = new MessageChat("me", message, true);
     chatUsersList.add(chatUser);
     messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolderAtPosition(ViewHolderViewInterface messageViewHolder, int position) {
        MessageChat messageChat = chatUsersList.get(position);
        if (messageChat.isSelf()) {
          messageViewHolder.setToUserName(messageChat.getName());
          messageViewHolder.setToMessage(messageChat.getText());
        } else {
            messageViewHolder.setFromUserName(messageChat.getName());
            messageViewHolder.setFromMessage(messageChat.getText());
        }

    }

    @Override
    public int getListItemCount() {
        return  chatUsersList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderForView(@NonNull ViewGroup parent, int viewType) {
      if (viewType == 1) {
          View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message_right,
                  parent, false);
          return  new MessageToViewHolder(view);
      } else {
          if (viewType == 0) {
              View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message_left,
                      parent, false);
              return new MessageFromViewHolder(view);
          }
      }
      return null;
    }

    @Override
    public int getItemViewTypeForItem(int position) {
       if (chatUsersList.get(position).isSelf()) {
           return 1;
       } else {
           return 0;
       }
    }
}
