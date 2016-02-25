package th.in.pnnutkung.notetakingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import th.in.pnnutkung.notetakingapp.R;
import th.in.pnnutkung.notetakingapp.models.Note;
import th.in.pnnutkung.notetakingapp.models.Storage;
import th.in.pnnutkung.notetakingapp.views.NoteAdapter;

public class MainActivity extends AppCompatActivity {

    //private TextView dummyNotes;
    private GridView notesGridView;
    private List<Note> notes;
    private NoteAdapter noteAdapter;
    private Button createButton;
    private Button clearButton;
    private Button sortAZ;
    private Button sortTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshNotes();
    }

    private void initComponents(){
        //dummyNotes = (TextView) findViewById(R.id.dummy_notes);
        notes = new ArrayList<Note>();
        noteAdapter = new NoteAdapter(this, R.layout.note_cell, notes);
        notesGridView = (GridView) findViewById(R.id.notes_grid_view);
        notesGridView.setAdapter(noteAdapter);
        notesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("note", notes.get(position));
                startActivity(intent);
            }
        });
        createButton = (Button) findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                                                startActivity(intent);
                                            }
                                        }
        );
        clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().clearNotes();
                refreshNotes();
            }
        });
        sortAZ = (Button) findViewById(R.id.sort_az);
        sortAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(notes, new Note.AlphabetComparator());
                noteAdapter.notifyDataSetChanged();
            }
        });
        sortTime = (Button) findViewById(R.id.sort_time);
        sortTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(notes, new Note.CreatedTimeComparator());
                noteAdapter.notifyDataSetChanged();
            }
        });
    }

    private void refreshNotes(){
        notes.clear();
        for (Note note :
                Storage.getInstance().loadNote()) {
            notes.add(note);
        }
        noteAdapter.notifyDataSetChanged();
    }
}