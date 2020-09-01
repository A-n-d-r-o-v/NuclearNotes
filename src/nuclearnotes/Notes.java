package nuclearnotes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Note {
    private String value;
    
    private char noteLetter;
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
     * Shift the note by some amount of semitones. E.g F2 + 3 semitones = G2#
     */
    public void addSemitone(int semitone) {
        int oldSemitone = toSemitone();
        int newSemitone = oldSemitone + semitone;
        value = toNote(newSemitone);
        assignNoteProperties();
    }
    
    /*
     * Convert a semitone value to a note string.
     */
    public static String toNote(int semitone) { 
        boolean sharp = false;
        int noteIndex = semitone % 12; // semitone between 0-12, e.g B=2, C#=4
        int newOctave = (semitone - noteIndex) / 12;
        
        String lettersStr = "A#BC#D#EF#G#";
        char note = lettersStr.charAt(noteIndex);
        if (note == '#') {
            note = lettersStr.charAt(noteIndex - 1); // the note before the sharp
            sharp = true;
        }
        
        String noteStr = Character.toString(note) + Integer.toString(newOctave);
        if (sharp) noteStr += "#";
        return noteStr;
    }
    
    /*
     * Convert the note to its semitone value. An octave is 12 semitones.
     */
    public int toSemitone() {
        return octave*12 + letterToSemitone(noteLetter, isSharp);
    }
    
    /*
     * Return letter to semitone, assuming the note isn't sharp.
     */
    public int letterToSemitone(char letter) {
        return letterToSemitone(letter, false);
    }
    
    /*
     * Return the semitone of the note, e.g 0 for A, 1 for A#, 2 for B...
     */
    public int letterToSemitone(char letter, boolean isSharp) {
        int result = 0;
        String lettersStr = "A#BC#D#EF#G#"; // Dashes represent sharps.
        char[] letters = lettersStr.toCharArray();
        
        for (int i = 0; i < letters.length; i++) {     
            if (Character.toUpperCase(letter) == letters[i]) {
                result = i;
                break;
            }
            if (i == letters.length-1) throw new IllegalArgumentException("Invalid letter!");
        }
        int isSharpInt = isSharp ? 1 : 0; // 0 if false, 1 if true.
        return result + isSharpInt;
    }
    
    /*
     *
     */
    //public Note findNote() {
    
    //}
    
    /*
     * Check whether or not a note is legal. A note is considered legal if it matches
     * the regex pattern for a correct note, e.g. "E4#" or "B1".
     */
    private boolean noteIsLegal(String note) {
        Pattern r = Pattern.compile("[A-G]\\d\\d*#?"); // A-G, one or more digits, and may contain a sharp.
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
        noteLetter = matcher.group(0).charAt(0);
        
        r = Pattern.compile("\\d\\d*");
        matcher = r.matcher(value);
        matcher.find();
        octave = Integer.parseInt(matcher.group(0));
        
        r = Pattern.compile("#");
        matcher = r.matcher(value);
        isSharp = matcher.find();
    }
    
    /* 
     * Return true if this is the same note as 'other'.
     */
    private boolean isSameNote(Note other) {
        boolean sameNote = noteLetter == other.getNoteLetter();
        boolean bothSharp = isSharp == other.isSharp();
        return sameNote && bothSharp;
    }
    
    /* 
     * Return true if this is the same note and octave as 'other'.
     */
    private boolean isSameNoteAndOctave(Note other) {
        boolean sameOctave = octave == other.getOctave();
        return isSameNote(other) && sameOctave;
    }
    
    public char getNoteLetter() {
        return noteLetter;
    }

    public int getOctave() {
        return octave;
    }

    public boolean isSharp() {
        return isSharp;
    }
    
    public String getValue() {
        return value;
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