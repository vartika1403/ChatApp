package entertainment.chatapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import entertainment.chatsocketapp.RecyclerViewPresenter;
import entertainment.chatsocketapp.ViewHolderViewInterface;

public class MessageAdapter extends RecyclerView.Adapter {
    private static final String LOG_TAG = MessageAdapter.class.getSimpleName();
    private RecyclerViewPresenter recyclerViewPresenter;

    public MessageAdapter(RecyclerViewPresenter recyclerViewPresenter) {
        this.recyclerViewPresenter = recyclerViewPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return recyclerViewPresenter.onCreateViewHolderForView(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     recyclerViewPresenter.onBindViewHolderAtPosition((ViewHolderViewInterface) holder, position);
    }

    @Override
    public int getItemCount() {
        return recyclerViewPresenter.getListItemCount();
    }

    @Override
    public int getItemViewType(int position) {
       return recyclerViewPresenter.getItemViewTypeForItem(position);
    }

}
