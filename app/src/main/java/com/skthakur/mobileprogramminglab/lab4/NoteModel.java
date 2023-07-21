package com.skthakur.mobileprogramminglab.lab4;

public class NoteModel {
    private String note;
    private int noteId;

    public NoteModel() {
    }

    public NoteModel(int noteId, String note) {
        this.noteId = noteId;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}

