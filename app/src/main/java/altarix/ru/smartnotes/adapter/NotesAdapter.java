package altarix.ru.smartnotes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import altarix.ru.smartnotes.R;
import altarix.ru.smartnotes.model.Notes;

/**
 * Created by home on 26.06.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private Context context;
    private List<Notes> mItems = new ArrayList<>();
    private TextView mTitleNotes;

    CallbackNotesAdapter mCallbackNotesAdapter;

    public void setOnShareClickedListener(CallbackNotesAdapter mCallbackNotesAdapter) {
        this.mCallbackNotesAdapter = mCallbackNotesAdapter;

    }

    public interface CallbackNotesAdapter{
        void callbackContent(ViewHolder holder);
    }

    public NotesAdapter(List<Notes> mItems, Context context) {
        this.mItems = mItems;
        this.context = context;

    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_items, parent, false);
        NotesAdapter.ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Notes mNotes = mItems.get(holder.getAdapterPosition());
        holder.bindNotesItem(mNotes);

        mTitleNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallbackNotesAdapter.callbackContent(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleNotes = (TextView) itemView.findViewById(R.id.title_notes_items);
        }

        private void bindNotesItem(Notes mNotes){
            mTitleNotes.setText(mNotes.getTitle());
        }

    }
}
