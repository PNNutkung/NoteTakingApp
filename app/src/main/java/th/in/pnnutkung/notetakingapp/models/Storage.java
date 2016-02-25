package th.in.pnnutkung.notetakingapp.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nut on 2/25/16.
 */
public class Storage {
    private static Storage instance;

    private List<Note> savedNotes;
    private Storage(){
        savedNotes = new ArrayList<Note>();
    }

    public static Storage getInstance(){
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }

    public void saveNote(Context context,Note note){
        savedNotes.add(note);
    }

    public List<Note> loadNote(){
        return savedNotes;
    }

    public void clearNotes(){
        savedNotes.clear();
    }

    public void deleteNote(Note note){
        savedNotes.remove(note);
    }
}
