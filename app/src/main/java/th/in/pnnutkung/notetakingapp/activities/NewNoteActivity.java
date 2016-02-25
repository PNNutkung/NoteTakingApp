package th.in.pnnutkung.notetakingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import th.in.pnnutkung.notetakingapp.R;
import th.in.pnnutkung.notetakingapp.models.Note;
import th.in.pnnutkung.notetakingapp.models.Storage;

public class NewNoteActivity extends AppCompatActivity {

    private TextView subject;
    private TextView body;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        initComponets();
    }

    private void initComponets(){
        subject = (TextView) findViewById(R.id.subject);
        body = (TextView) findViewById(R.id.body);
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewNote();
                finish();
            }
        });

    }

    private void saveNewNote(){
        Storage.getInstance().saveNote(NewNoteActivity.this,
                new Note(subject.getText().toString(),
                        body.getText().toString()
                )
        );
    }
}
