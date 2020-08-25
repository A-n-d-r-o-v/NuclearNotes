package nuclearnotes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Note {
    private String value;
    
    private String noteLetter;
    private int octave;
    private boolean isSharp;
    
    public Note(String value) {
        this.value = value;
        if (!noteIsLegal(value)) {
            throw new IllegalArgumentException("Illegal note value!");
        }
        assignNoteProperties();
    }
    
    /*
     * Check whether or not a note is legal. A note is considered legal if it matches
     * the regex pattern for a correct note, e.g. "E4#" or "B1"
     */
    private boolean noteIsLegal(String note) {
        Pattern r = Pattern.compile("[A-G]\\d*#?"); // A-G, some amount of digits, and may contain a sharp.
        Matcher matcher = r.matcher(note);
        return matcher.find();     
    }
    
    /*
     * Set the follow properties of this note: the letter, the octave, and if it is sharp.
     */
    private void assignNoteProperties() {
        Pattern r = Pattern.compile("[A-G]");
        Matcher matcher = r.matcher(value);
        matcher.find();
        noteLetter = matcher.group(0);
        
        r = Pattern.compile("\\d*");
        matcher = r.matcher(value);
        matcher.find();
        matcher.find(); // i dOnT kNoW wHy ThIs wOrKs, bUt oOOhh WeLL!11!
        octave = Integer.parseInt(matcher.group(0));
        
        r = Pattern.compile("#");
        matcher = r.matcher(value);
        isSharp = matcher.find();
    }

    public String getNoteLetter() {
        return noteLetter;
    }

    public int getOctave() {
        return octave;
    }

    public boolean isSharp() {
        return isSharp;
    }
    
    
}


public enum Notes {
    A, 
    AS, 
    B, 
    C, 
    CS, 
    D, 
    DS, 
    E, 
    F, 
    FS, 
    G, 
    GS;
    
    /*
     * Return the note you get by ascending/descending an amount of semitones
     */
    public Notes findNote(int semitones) {
        int noteValue = (Notes.valueOf(this.toString()).ordinal() + semitones) % 12;
        return Notes.values()[noteValue];
    }
}