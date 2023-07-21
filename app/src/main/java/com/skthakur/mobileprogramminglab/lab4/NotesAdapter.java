package com.skthakur.mobileprogramminglab.lab4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skthakur.mobileprogramminglab.R;

import java.util.List;

interface NoteDeleteClickListener {
    void onNoteDeleteClicked(NoteModel noteModel);
}

interface NoteClickListener {
    void onNoteClicked(NoteModel noteModel);
}

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private static final String TAG = "NotesAdapter";
    private Context context;
    private List<NoteModel> noteModelList;
    private NoteDeleteClickListener noteDeleteClickListener;
    private NoteClickListener noteClickListener;

    public NotesAdapter(Context context, List<NoteModel> noteModelList, NoteDeleteClickListener noteDeleteClickListener, NoteClickListener noteClickListener) {
        this.context = context;
        this.noteModelList = noteModelList;
        this.noteDeleteClickListener = noteDeleteClickListener;
        this.noteClickListener = noteClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: notesSize:" + noteModelList.size());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        NoteModel noteModel = noteModelList.get(position);
        holder.tvNotes.setText(noteModel.getNote());
        Log.d(TAG, "onBindViewHolder: " + noteModel.getNote());
        holder.cardNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteClickListener.onNoteClicked(noteModel);
            }
        });
        holder.btnDelete.setOnClickListener(view -> {
            if (position != RecyclerView.NO_POSITION) {
                noteDeleteClickListener.onNoteDeleteClicked(noteModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView tvNotes;
        ImageButton btnDelete;
        CardView cardNote;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            int position = getAdapterPosition();
            Log.d(TAG, "NotesViewHolder: position:" + position);
            tvNotes = itemView.findViewById(R.id.tv_note);
            cardNote = itemView.findViewById(R.id.card_note);
            btnDelete = itemView.findViewById(R.id.btn_delete);


        }
    }
}
