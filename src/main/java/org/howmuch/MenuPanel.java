package org.howmuch;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private Image background;
    MenuPanel()
    {

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        int width = this.getSize().width;
        int height = this.getSize().height;

        if (this.background != null) {
            //Add the size of the window in drawImage method()
            g.drawImage(this.background, 0, 0, width, height, null);
        }
    }

    public void setBackground(String imagePath) {
        this.background = new ImageIcon(imagePath).getImage();
        repaint();
    }

}
