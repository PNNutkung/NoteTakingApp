package th.in.pnnutkung.notetakingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import th.in.pnnutkung.notetakingapp.R;
import th.in.pnnutkung.notetakingapp.models.Note;
import th.in.pnnutkung.notetakingapp.models.Storage;

public class NoteActivity extends AppCompatActivity {

    private Note note;
    private TextView subject;
    private TextView body;
    private TextView date;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = (Note)getIntent().getSerializableExtra("note");
        setContentView(R.layout.activity_note);
        initComponents();
    }

    private void initComponents(){
        subject = (TextView) findViewById(R.id.subject);
        body = (TextView) findViewById(R.id.body);
        date = (TextView) findViewById(R.id.created_time);
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().deleteNote(note);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        subject.setText(note.getSubject());
        body.setText(note.getBody() );
        date.setText(note.getReadableCreatedTime().toString());
    }
}
