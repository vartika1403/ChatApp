package entertainment.chatapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.List;

import entertainment.chatapp.adapter.MessageAdapter;
import entertainment.chatapp.model.MessageChat;

public class RecyclerViewPresenter implements RecyclerViewPresenterInterface {
    private static final String LOG_TAG = RecyclerViewPresenter.class.getSimpleName();
    private List<MessageChat> chatUsersList;
    private MessageAdapter messageAdapter;
    private ChatScreenInterface chatScreenInterface;
    private DatabaseReference firebaseChatRef;
    public FirebaseConnection firebaseConnection;

    public RecyclerViewPresenter(ChatScreenInterface chatScreenInterface, RecyclerView recyclerView) {
        this.chatScreenInterface = chatScreenInterface;
        firebaseConnection = new FirebaseConnection(this);
        chatUsersList = new ArrayList<MessageChat>();
        messageAdapter = new MessageAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager((Context) chatScreenInterface,
                OrientationHelper.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messageAdapter);
    }

    @Override
    public void sendMessageToAdapter(String message, boolean isSelf) {
     MessageChat chatUser = new MessageChat(message, isSelf);
     chatUsersList.add(chatUser);
     messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolderAtPosition(ViewHolderViewInterface messageViewHolder, int position) {
        MessageChat messageChat = chatUsersList.get(position);
        if (messageChat.getIsSelf()) {
          messageViewHolder.setToMessage(messageChat.getMesage());
        } else {
            messageViewHolder.setFromMessage(messageChat.getMesage());
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
       if (chatUsersList.get(position).getIsSelf()) {
           return 1;
       } else {
           return 0;
       }
    }

    @Override
    public void sendMessageToServer(String message, boolean isSelf,
                                    DatabaseReference firebaseChatRef) {
        firebaseConnection.sendMessageToServer(firebaseChatRef, message, isSelf);
    }

    @Override
    public void onReceivingMessageFromUser(final String message, boolean isSelf) {
        chatScreenInterface.runOnUiThread(message, isSelf);
    }

    @Override
    public void onRunningOnUiThreadShowResponse(String message, boolean isSelf,
                                                DatabaseReference firebaseChatRef) {
        firebaseConnection.sendReceiverMessageToServer(message, isSelf,
                firebaseChatRef, chatUsersList, messageAdapter);
    }

    @Override
    public void displayOldMessages(DatabaseReference firebaseChatRef) {
       firebaseConnection.displayOldMessageOnUi(firebaseChatRef);
    }
}
