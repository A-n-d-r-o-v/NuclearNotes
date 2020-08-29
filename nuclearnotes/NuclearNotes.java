package nuclearnotes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Andreas och Isak i Skogen
 */
public class NuclearNotes {

    private JFrame frame = new JFrame();
    
    public NuclearNotes() {
        JLabel fretCountLabel = new JLabel("Fret count:");
        JTextField fretCountField = new JTextField(2);
        JButton generateGuitarButton = new JButton("Go!");
        
        JPanel panel = new JPanel();
        panel.add(fretCountLabel);
        panel.add(fretCountField);
        panel.add(generateGuitarButton);
        
        Rectangles guitarPanel = new Rectangles(1080, 720/2, 24, 6);
        guitarPanel.setBackground(Color.red);

        frame.setLayout(new GridLayout(0, 1));
        frame.add(panel);
        frame.add(guitarPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(new Dimension(1080, 720));
        frame.setTitle("Stor och fin, grävmaskin!");
        frame.setVisible(true);
    
    }
    
    public static void main(String[] args) {
        Note note = new Note("D2#");
        
        System.out.println(Note.toNote(note.toSemitone()));
        
        note.addSemitone(2);
        System.out.println(note.getValue());
        
        new NuclearNotes();
    }
    
}
