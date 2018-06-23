package entertainment.chatapp;

import com.google.firebase.database.DatabaseReference;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import entertainment.chatapp.adapter.MessageAdapter;
import entertainment.chatapp.model.MessageChat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirebaseConnection {
    private RecyclerViewPresenter recyclerViewPresenter;

    public FirebaseConnection(RecyclerViewPresenter recyclerViewPresenter) {
        this.recyclerViewPresenter = recyclerViewPresenter;
    }

    public void sendMessageToServer(String message, boolean isSelf) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("www.personalityforge.com")
                .addPathSegment("api")
                .addPathSegment("chat")
                .addQueryParameter("apiKey", "6nt5d1nJHkqbkphe")
                .addQueryParameter("message", message)
                .addQueryParameter("chatBotID", "63906")
                .addQueryParameter("externalID", "chirag1")
                .build();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final String myResponse = response.body().string();

               recyclerViewPresenter.onReceivingMessageFromUser(myResponse, false);

            }
        });
    }

    public void sendReceiverMessageToServer(String message, boolean isSelf, DatabaseReference firebaseChatRef,
                                            List<MessageChat> chatUsersList, MessageAdapter messageAdapter) {
        try {
            JSONObject jsonObj = new JSONObject(message);
            JSONObject object = jsonObj.getJSONObject("message");
            String receivedMessage = object.getString("message");
            MessageChat messageChat = new MessageChat(receivedMessage, false);
            chatUsersList.add(messageChat);
            messageAdapter.notifyDataSetChanged();

            firebaseChatRef.push().setValue(messageChat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

