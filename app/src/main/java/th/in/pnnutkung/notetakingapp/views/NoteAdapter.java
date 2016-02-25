package th.in.pnnutkung.notetakingapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import th.in.pnnutkung.notetakingapp.R;
import th.in.pnnutkung.notetakingapp.models.Note;

/**
 * Created by nut on 2/25/16.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.note_cell, null);
        }

        TextView subject = (TextView) v.findViewById(R.id.subject);
        TextView body = (TextView) v.findViewById(R.id.body);
        TextView date = (TextView) v.findViewById(R.id.created_time);

        Note note = getItem(position);
        subject.setText(note.getSubject());
        body.setText(note.getBody());
        date.setText(note.getReadableCreatedTime());
        return v;
    }
}
