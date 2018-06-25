package entertainment.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements  ChatScreenInterface{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerViewPresenter recyclerViewPresenter;
    private DatabaseReference firebaseChatRef;
    private FirebaseDatabase database;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.input_message_edit_text)
    EditText messageEditText;
    @BindView(R.id.send_button)
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseApp.initializeApp(this);
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            // ...
        }
        String chatId = "Cyber Ty".concat("63906");
        String userConversationUri = Conf.firebaseConverstionUri(chatId);
        if (userConversationUri.isEmpty()) {
            Log.i(LOG_TAG, "Empty userConversationUri");
            return;
        } else {
            Log.i(LOG_TAG, "firebase userConversationUri, " + userConversationUri);
        }
        firebaseChatRef = FirebaseDatabase.getInstance().getReferenceFromUrl(userConversationUri);

        recyclerViewPresenter = new RecyclerViewPresenter(this, recyclerView);
      /*  if (!Conf.isShown) {
            Log.i(LOG_TAG, "isStarted, " + Conf.isShown);
            recyclerViewPresenter.displayOldMessages(firebaseChatRef);
            Conf.isShown = true;
        }*/
    }

    @OnClick(R.id.send_button)
    public void send() {
        sendMessage(messageEditText.getText().toString());
    }

    @Override
    public void sendMessage(String message) {
        recyclerViewPresenter.sendMessageToAdapter(message, true);
        recyclerViewPresenter.sendMessageToServer(message, true, firebaseChatRef);
    }

    @Override
    public void runOnUiThread(final String message, final boolean isSelf) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
             recyclerViewPresenter.onRunningOnUiThreadShowResponse(message, isSelf , firebaseChatRef);
            }
        });
    }
}
