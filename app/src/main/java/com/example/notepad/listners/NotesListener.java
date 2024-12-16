package com.example.notepad.listners;

import com.example.notepad.entitles.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position );
}
