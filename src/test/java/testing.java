import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

import static java.lang.Math.round;

public class testing extends JFrame {

    public static final int heighth = 500;
    public static final int widthh = 1000;
    testing() {
        this.setTitle("How Much? ");

        this.setPreferredSize(new Dimension(1000, 500));
        this.setResizable(true);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanelBackground sth = new JPanelBackground();
        sth.setBackground("src/main/resources/images/Main_Menu_bg.png");
        this.add(sth);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

//        this.addComponentListener(new ComponentAdapter() {
//
//            @Override
//            public void componentResized(ComponentEvent e) {
////                setSize(new Dimension((int) (1.7777 * getHeight()), getHeight()));
////                super.componentResized(e);
////                if((int) (getHeight() * 1.777) != getWidth())
////                {
////                    setSize(new Dimension((int) (1.7777 * getHeight()), getHeight()));
////                    super.componentResized(e);
////                }
//                if(getHeight() > heighth )
//                {
//                    System.out.println(getHeight());
//                    System.out.println(getWidth());
//                    if(getWidth() <= widthh)
//                    {
//                        System.out.println(getWidth());
//                        setSize(new Dimension(widthh, heighth));
//                    }
//                }
//            }
//
//        });
    }
    @Override
    public void setBounds(int x, int y, int width, int height) {
        if (width <= widthh) {
        super.setBounds(x, y, width, height);
        }
    }
}
