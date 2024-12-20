package com.example.notepad.adapters;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.entitles.Note;
import com.example.notepad.listners.NotesListener;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.ArrayList;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{
    private List<Note> notes;
    private NotesListener notesListener;
    private Timer timer;
    private List<Note>noteSource;


    public NotesAdapter(List<Note> notes, NotesListener notesListener) {
        this.notes = notes;
        this.notesListener = notesListener;
        noteSource = notes;

    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_note,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setNote(notes.get(position));
        holder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesListener.onNoteClicked(notes.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle,textSubtitle,textDateTime;
        LinearLayout layoutNote;
        RoundedImageView imageNote;

        @SuppressLint("CutPasteId")
        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textSubtitle);
            layoutNote = itemView.findViewById(R.id.layoutNote);
            imageNote = itemView.findViewById(R.id.ImageNote);
        }
        void setNote(Note note){
            textTitle.setText(note.getTitle());
            if(note.getSubtitle().trim().isEmpty()){
                textSubtitle.setVisibility(View.GONE);
            }else{
                textSubtitle.setText(note.getSubtitle());
            }
            textDateTime.setText(note.getDateTime());
            GradientDrawable gradientDrawable =(GradientDrawable)  layoutNote.getBackground();
            if(note.getColor() != null ){
                gradientDrawable.setColor(Color.parseColor(note.getColor()));
            }else {
                gradientDrawable.setColor(Color.parseColor("#333333"));
            }

            if(note.getImagePath() != null){
                imageNote.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));
                imageNote.setVisibility(View.VISIBLE);
            }else {
                imageNote.setVisibility(View.GONE);
            }
        }
    }

    public void searchNotes(final String searchKeywords)
    {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(searchKeywords.trim().isEmpty())
                {
                    notes = noteSource;
                }else {
                    ArrayList<Note> temp = new ArrayList<>();
                    for(Note note : noteSource)
                    {
                        if(note.getTitle().toLowerCase().contains(searchKeywords.toLowerCase())
                         || note.getSubtitle().toLowerCase().contains(searchKeywords.toLowerCase())
                        || note.getNoteText().toLowerCase().contains(searchKeywords.toLowerCase()))
                        {
                            temp.add(note);
                        }
                    }
                    notes = temp;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        },500);
    }

    public void CancelTimer(){
        if(timer != null)
        {
            timer.cancel();
        }
    }


}
