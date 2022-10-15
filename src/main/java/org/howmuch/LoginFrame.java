package org.howmuch;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {
    Font buttonFont, textFont, password_font;
    JLabel username, password, background_lbl;
    JButton login_btn, guest_btn, newAccount_btn, exit_btn;
    JTextField username_txt_fld;
    JPasswordField password_txt_fld;
    LoginFrame() {
        this.setTitle("How Much? ");
        this.setResizable(false);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createFont();
        createButtons();
        createLabels();
        createTextFields();

        this.add(login_btn);
        this.add(username);
        this.add(password);
        this.add(guest_btn);
        this.add(newAccount_btn);
        this.add(exit_btn);
        this.add(username_txt_fld);
        this.add(password_txt_fld);
        this.add(background_lbl);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void createFont() {
        try {
            buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/MomcakeBold-WyonA.otf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(buttonFont);


            textFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/MomcakeBold-WyonA.otf")).deriveFont(50f);
            //register the font
            ge.registerFont(buttonFont);

            password_font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Fonts/CaeciliaLTPro45Light.TTF")).deriveFont(35f);
            //register the font
            ge.registerFont(buttonFont);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createButtons() {
        login_btn = new JButton();
        login_btn.setText("Login");
        login_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        login_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        login_btn.setBounds(775, 532, 200, 40);
        login_btn.setFont(buttonFont.deriveFont(44f));
        login_btn.setFocusPainted(false);
        login_btn.setContentAreaFilled(false);
        login_btn.setOpaque(true);
        login_btn.setBorder(null);
        login_btn.setBackground(Colors.light_bgColor);
        login_btn.addChangeListener(evt -> {
            if (login_btn.getModel().isPressed()) {
                login_btn.setForeground(Colors.light_primaryColor);
            } else if (login_btn.getModel().isRollover()) {
                login_btn.setForeground(Colors.light_secondaryColor);
            } else {
                login_btn.setForeground(Colors.light_primaryColor);
            }
        });
        guest_btn = new JButton();
        guest_btn.setText("Guest");
        guest_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        guest_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        guest_btn.setBounds(940, 532, 200, 40);
        guest_btn.setFont(buttonFont.deriveFont(44f));
        guest_btn.setFocusPainted(false);
        guest_btn.setContentAreaFilled(false);
        guest_btn.setOpaque(true);
        guest_btn.setBorder(null);
        guest_btn.setBackground(Colors.light_bgColor);
        guest_btn.addChangeListener(evt -> {
            if (guest_btn.getModel().isPressed()) {
                guest_btn.setForeground(Colors.light_primaryColor);
            } else if (guest_btn.getModel().isRollover()) {
                guest_btn.setForeground(Colors.light_secondaryColor);
            } else {
                guest_btn.setForeground(Colors.light_primaryColor);
            }
        });

        newAccount_btn = new JButton();
        newAccount_btn.setText("Create New Account");
        newAccount_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        newAccount_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        newAccount_btn.setBounds(615, 590, 800, 40);
        newAccount_btn.setFont(buttonFont.deriveFont(44f));
        newAccount_btn.setFocusPainted(false);
        newAccount_btn.setContentAreaFilled(false);
        newAccount_btn.setOpaque(true);
        newAccount_btn.setBorder(null);
        newAccount_btn.setBackground(Colors.light_bgColor);
        newAccount_btn.addChangeListener(evt -> {
            if (newAccount_btn.getModel().isPressed()) {
                newAccount_btn.setForeground(Colors.light_primaryColor);
            } else if (newAccount_btn.getModel().isRollover()) {
                newAccount_btn.setForeground(Colors.light_secondaryColor);
            } else {
                newAccount_btn.setForeground(Colors.light_primaryColor);
            }
        });

        exit_btn = new JButton();
        exit_btn.setText("-");
        exit_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        exit_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        exit_btn.setBounds(1200, 20, 50, 40);
        exit_btn.setFont(buttonFont.deriveFont(44f));
        exit_btn.setFocusPainted(false);
        exit_btn.setContentAreaFilled(false);
        exit_btn.setOpaque(true);
        exit_btn.setBorder(null);
        exit_btn.setBackground(Colors.light_bgColor);
        exit_btn.addChangeListener(evt -> {
            if (exit_btn.getModel().isPressed()) {
                exit_btn.setForeground(Colors.light_primaryColor);
                System.out.println("Thanks for Playing!");
                System.exit(0);
            } else if (exit_btn.getModel().isRollover()) {
                exit_btn.setForeground(Colors.light_secondaryColor);
            } else {
                exit_btn.setForeground(Colors.light_primaryColor);
            }
        });

    }

    public void createLabels() {
        ImageIcon icon = new ImageIcon("src/main/resources/images/Login_bg.png");
        Image bg_image = icon.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);

        background_lbl = new JLabel();
        background_lbl.setIcon(new ImageIcon(bg_image));

        username = new JLabel();
        username.setText("Enter Username");
        username.setFont(textFont.deriveFont(44f));
        username.setAlignmentX(Box.CENTER_ALIGNMENT);
        username.setBounds(822, 244, 800, 80);
        username.setForeground(Colors.light_primaryColor);


        password = new JLabel();
        password.setText("Password");
        password.setFont(textFont.deriveFont(44f));
        password.setAlignmentX(Box.CENTER_ALIGNMENT);
        password.setBounds(822, 340, 800, 150);
        password.setForeground(Colors.light_primaryColor);
    }

    public void createTextFields()
    {
        username_txt_fld = new JTextField("");
        username_txt_fld.setFont(password_font);
        username_txt_fld.setBounds(822, 319, 300, 50);
        username_txt_fld.setBackground(Colors.light_bgColor);
        username_txt_fld.setOpaque(true);
        username_txt_fld.setBorder(null);
        username_txt_fld.setForeground(Colors.light_accentColor);

        password_txt_fld = new JPasswordField("");
        password_txt_fld.setFont(password_font);
        password_txt_fld.setBounds(822, 452, 300, 50);
        password_txt_fld.setBackground(Colors.light_bgColor);
        password_txt_fld.setOpaque(true);
        password_txt_fld.setBorder(null);
        password_txt_fld.setEchoChar('*');
        password_txt_fld.setForeground(Colors.light_accentColor);
    }
}
