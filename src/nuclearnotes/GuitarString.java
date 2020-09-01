package nuclearnotes;

import java.util.ArrayList;
import java.util.List;

public class GuitarString {
    private final Notes startNote; // e
    private int fretCount = 25; // fret count including the open fret (0)
    
    private List<Notes> noteList = new ArrayList<>();

    public GuitarString(Notes startNote) {
        this.startNote = startNote;
        
        setNotes();
    }
    
    public GuitarString(Notes startNote, int fretCount) {
        this.startNote = startNote;
        this.fretCount = fretCount;
        
        setNotes();
    }
    
    /*
     * Fill the note list with each corresponding note, starting with the open fret
     */
    private void setNotes() {
        for (int i = 0; i < fretCount; i++) {
            noteList.add(startNote.findNote(i));
        }
    }
    
    public Notes getStartNote() {
        return startNote;
    }
    
    public int getFretCount() {
        return fretCount;
    }
    
    public void printNotes() {
        for (Notes note : noteList) {
            System.out.print(note.toString() + " ");
        }
    }
    
}
