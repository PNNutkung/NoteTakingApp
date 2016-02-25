package th.in.pnnutkung.notetakingapp.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by nut on 2/25/16.
 */
public class Note implements Serializable{

    private String subject;
    private String body;
    private long createdTime;

    public Note(String subject,String body){
        this.subject = subject;
        this.body = body;
        this.createdTime = System.currentTimeMillis();
    }

    public String getBody() {
        return this.body;
    }

    public String getSubject() {
        return this.subject;
    }

    public long getCreatedTime() {
        return this.createdTime;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        return createdTime == note.createdTime;

    }

    @Override
    public int hashCode() {
        return (int) (createdTime ^ (createdTime >>> 32));
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Subject: "+this.subject+" Body: "+this.body;
    }

    public String getReadableCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm");
        Date date = new Date(this.createdTime);
        return sdf.format(date);
    }

    public static class AlphabetComparator implements Comparator<Note> {

        @Override
        public int compare(Note lhs, Note rhs) {
            return lhs.getSubject().compareTo(rhs.getSubject());
        }
    }

    public static class CreatedTimeComparator implements Comparator<Note> {

        @Override
        public int compare(Note lhs, Note rhs) {
            return (int)(lhs.getCreatedTime() - rhs.getCreatedTime());
        }
    }
}
