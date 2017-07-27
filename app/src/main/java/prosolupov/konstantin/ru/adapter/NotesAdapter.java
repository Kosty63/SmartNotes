package prosolupov.konstantin.ru.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prosolupov.konstantin.ru.R;
import prosolupov.konstantin.ru.model.Notes;

/**
 * Created by home on 26.06.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private Context context;
    private static List<Notes> mItems = new ArrayList<>();
    private TextView mTitleNotes;
    private Button mButtonAddItem;

    CallbackNotesAdapter mCallbackNotesAdapter;

    public void setOnShareClickedListener(CallbackNotesAdapter mCallbackNotesAdapter) {
        this.mCallbackNotesAdapter = mCallbackNotesAdapter;
    }

    public interface CallbackNotesAdapter{
        void callbackContent(ViewHolder holder);
    }

    public NotesAdapter(){}
    
    public NotesAdapter(List<Notes> mItems, Context context) {
        this.mItems = mItems;
        this.context = context;
    }

    public static void updateItemsAdapter(int position, Notes notes){
        mItems.set(position, notes);
    }

    public static void addItemsAdapter(Notes notes){
        mItems.add(notes);
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
/*
        mButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notes notes = new Notes();
                notes.setTitle("shdgfjkshdf");
                notes.setBody("fgsdj");
                addItemsAdapter(notes);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleNotes = (TextView) itemView.findViewById(R.id.title_notes_items);
            mButtonAddItem = (Button) itemView.findViewById(R.id.add_item_test);
        }

        private void bindNotesItem(Notes mNotes){
            mTitleNotes.setText(mNotes.getTitle());
        }

    }


}
