package com.skthakur.mobileprogramminglab.lab4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skthakur.mobileprogramminglab.R;

import java.util.ArrayList;
import java.util.List;

public class CrudSqliteActivity extends AppCompatActivity implements NoteDeleteClickListener, NoteClickListener {
    private static final String TAG = "CrudSqliteActivity";
    DatabaseOperations dbOperations;
    EditText etAddNote;
    Button btnAddNote;
    RecyclerView rcvNotes;
    NotesAdapter notesAdapter;
    List<NoteModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_sqlite);
        dbOperations = new DatabaseOperations(this);
        etAddNote = findViewById(R.id.et_add_note);
        btnAddNote = findViewById(R.id.btn_add_note);
        rcvNotes = findViewById(R.id.rcv_notes);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etAddNote.getText().toString().trim();
                if (text.isEmpty()) {
                    etAddNote.setError("Please write something");
                    return;
                }
                dbOperations.insertData(text);
                etAddNote.getText().clear();
                setNotesRecycler();
            }
        });
        getNotesData();
        setNotesRecycler();
    }

    private void getNotesData() {
        List<NoteModel> dataList = dbOperations.getAllData();
        notes.clear();
        notes.addAll(dataList);
        for (NoteModel i : notes) {
            System.out.println(i.getNote());
        }
    }

    private void setNotesRecycler() {
        getNotesData();
        rcvNotes.setLayoutManager(new LinearLayoutManager(this));
        if (notesAdapter == null) {
            notesAdapter = new NotesAdapter(this, notes, this::onNoteDeleteClicked, this::onNoteClicked);
            rcvNotes.setAdapter(notesAdapter);
        } else {
            notesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNoteDeleteClicked(NoteModel noteModel) {
        dbOperations.deleteData(noteModel.getNoteId());
        setNotesRecycler();
        Log.d(TAG, "onNoteDeleteClicked: " + noteModel.getNoteId());
        Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoteClicked(NoteModel noteModel) {
        Log.d(TAG, "onNoteClicked: ");
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        showDialog(noteModel);
    }

    private void showDialog(NoteModel noteModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_note, null);
        builder.setView(dialogView);
        final EditText editText = dialogView.findViewById(R.id.etEditNote);
        editText.setText(noteModel.getNote());
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            String enteredText = editText.getText().toString();
            dbOperations.updateData(enteredText, noteModel.getNoteId());
            setNotesRecycler();
            editText.getText().clear();
            dialogInterface.dismiss(); // Dismiss the dialog
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}