package entertainment.chatapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;

public interface RecyclerViewPresenterInterface {
    void sendMessageToAdapter(String message, boolean isSelf);
    void onBindViewHolderAtPosition(ViewHolderViewInterface messageViewHolder, int position);
    int getListItemCount();
    RecyclerView.ViewHolder onCreateViewHolderForView(@NonNull ViewGroup parent, int viewType);
    int getItemViewTypeForItem(int position);
    void sendMessageToServer(String message, boolean isSelf, DatabaseReference databaseReference);
    void onReceivingMessageFromUser(String message, boolean isSelf);
    void onRunningOnUiThreadShowResponse(String message, boolean isSelf,
                                         DatabaseReference firebaseRef);
    void displayOldMessages(DatabaseReference databaseReference);
}
