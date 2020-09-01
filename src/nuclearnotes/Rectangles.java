package nuclearnotes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Andreas
 */
public class Rectangles extends JPanel {
        
    int windowSizeX;
    int windowSizeY;
    int fretCount;
    int stringCount;
    
    public Rectangles(int x, int y, int fretC, int stringC) {
        windowSizeX = x;
        windowSizeY = y;
        fretCount = fretC;
        stringCount = stringC;
    }

    @Override
    public void paintComponent(Graphics g) {
        for(int i = 0; i < 26; i++){
            for (int j = 0; j < 7; j++){
                g.drawRect(i*(windowSizeX/fretCount),j*(windowSizeY/stringCount),(windowSizeX/fretCount),(windowSizeY/stringCount));
                g.setColor(new Color((int)(Math.random()*0x1000000)));
                g.fillRect(i*(windowSizeX/fretCount),j*(windowSizeY/stringCount),(windowSizeX/fretCount),(windowSizeY/stringCount));
            }
        }
    }
}
