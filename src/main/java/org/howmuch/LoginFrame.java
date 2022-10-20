package org.howmuch;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static org.howmuch.Main.*;

public class LoginFrame extends JFrame {
    JLabel username, password, background_lbl;
    JButton login_btn, guest_btn, newAccount_btn, exit_btn, resize_btn, minimize_btn;
    JTextField username_txt_fld;
    JPasswordField password_txt_fld;
    LoginFrame() {
        this.setTitle("How Much? ");
        this.setResizable(false);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createFonts();
        createButtons();
        createLabels();
        createTextFields();

        this.add(login_btn);
        this.add(username);
        this.add(password);
        this.add(guest_btn);
        this.add(newAccount_btn);
        this.add(exit_btn);
//        this.add(resize_btn);
        this.add(minimize_btn);
        this.add(username_txt_fld);
        this.add(password_txt_fld);
        this.add(background_lbl);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void createButtons() {
        login_btn = new JButton();
        login_btn.setText("Login");
        login_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        login_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        login_btn.setBounds(775, 532, 200, 40);
        login_btn.setFont(textFont.deriveFont(44f));
        login_btn.setFocusPainted(false);
        login_btn.setContentAreaFilled(false);
        login_btn.setOpaque(true);
        login_btn.setBorder(null);

        login_btn.setBackground(Colors.bgColor);
        login_btn.addChangeListener(evt -> {
            if (login_btn.getModel().isPressed()) {
                login_btn.setForeground(Colors.primaryColor);
            } else if (login_btn.getModel().isRollover()) {
                login_btn.setForeground(Colors.secondaryColor);
            } else {
                login_btn.setForeground(Colors.primaryColor);
            }
        });

        login_btn.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(1);
        });
        login_btn.setEnabled(false);

        guest_btn = new JButton();
        guest_btn.setText("Guest");
        guest_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        guest_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        guest_btn.setBounds(940, 532, 200, 40);
        guest_btn.setFont(textFont.deriveFont(44f));
        guest_btn.setFocusPainted(false);
        guest_btn.setContentAreaFilled(false);
        guest_btn.setOpaque(true);
        guest_btn.setBorder(null);
        guest_btn.setBackground(Colors.bgColor);
        guest_btn.addChangeListener(evt -> {
            if (guest_btn.getModel().isPressed()) {
                guest_btn.setForeground(Colors.primaryColor);
            } else if (guest_btn.getModel().isRollover()) {
                guest_btn.setForeground(Colors.secondaryColor);
            } else {
                guest_btn.setForeground(Colors.primaryColor);
            }
        });
        guest_btn.addActionListener(e -> {
            Main.isGuest = true;
            DataBaseManager.currentPassword = "guest";
            DataBaseManager.currentUsername = "guest";
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(1);
        });

        newAccount_btn = new JButton();
        newAccount_btn.setText("Create New Account");
        newAccount_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        newAccount_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        newAccount_btn.setBounds(615, 590, 800, 40);
        newAccount_btn.setFont(textFont.deriveFont(44f));
        newAccount_btn.setFocusPainted(false);
        newAccount_btn.setContentAreaFilled(false);
        newAccount_btn.setOpaque(true);
        newAccount_btn.setBorder(null);
        newAccount_btn.setBackground(Colors.bgColor);
        newAccount_btn.addChangeListener(evt -> {
            if (newAccount_btn.getModel().isPressed()) {
                newAccount_btn.setForeground(Colors.primaryColor);
            } else if (newAccount_btn.getModel().isRollover()) {
                newAccount_btn.setForeground(Colors.secondaryColor);
            } else {
                newAccount_btn.setForeground(Colors.primaryColor);
            }
        });

        newAccount_btn.addActionListener(e -> {
            Main.isGuest = false;
            if (!Objects.equals(DataBaseManager.currentUsername, "guest")){
                if(!Objects.equals(DataBaseManager.currentPassword, "guest")){
                    DataBaseManager.addUser();
                    grantAccess = true;
                }
            }
            this.setVisible(false);
            this.dispose();
            Main.changeFrame(1);
        });
        newAccount_btn.setEnabled(false);

        ImageIcon exit = new ImageIcon("src/main/resources/icons/circle_delete.png");
        Image exit_image = exit.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon minimize = new ImageIcon("src/main/resources/icons/circle_minus.png");
        Image minimize_image = minimize.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon resize = new ImageIcon("src/main/resources/icons/screen_expand_3.png");
        Image resize_image = resize.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        exit_btn = new JButton();
//        exit_btn.setText("-");
        exit_btn.setIcon(new ImageIcon(exit_image));
        exit_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        exit_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        exit_btn.setBounds(1230, 15, 25, 25);
        exit_btn.setFont(textFont.deriveFont(44f));
        exit_btn.setFocusPainted(false);
        exit_btn.setContentAreaFilled(false);
        exit_btn.setOpaque(true);
        exit_btn.setBorder(null);
        exit_btn.setBackground(Colors.bgColor);
        exit_btn.addChangeListener(evt -> {
            if (exit_btn.getModel().isPressed()) {
                exit_btn.setForeground(Colors.primaryColor);
                this.setVisible(false);
                this.dispose();
                Main.changeFrame(0);
            } else if (exit_btn.getModel().isRollover()) {
                exit_btn.setForeground(Colors.secondaryColor);
            } else {
                exit_btn.setForeground(Colors.primaryColor);
            }
        });

        resize_btn = new JButton();
//        resize_btn.setText("-");
        resize_btn.setIcon(new ImageIcon(resize_image));
        resize_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        resize_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        resize_btn.setBounds(1195, 15, 25, 25);
        resize_btn.setFont(textFont.deriveFont(44f));
        resize_btn.setFocusPainted(false);
        resize_btn.setContentAreaFilled(false);
        resize_btn.setOpaque(true);
        resize_btn.setBorder(null);
        resize_btn.setBackground(Colors.bgColor);
        resize_btn.addChangeListener(evt -> {
            if (exit_btn.getModel().isPressed()) {
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                exit_btn.setForeground(Colors.primaryColor);
            } else if (exit_btn.getModel().isRollover()) {
                exit_btn.setForeground(Colors.secondaryColor);
            } else {
                exit_btn.setForeground(Colors.primaryColor);
            }
        });

        minimize_btn = new JButton();
//        minimize_btn.setText("-");
        minimize_btn.setIcon(new ImageIcon(minimize_image));
        minimize_btn.setAlignmentY(Box.CENTER_ALIGNMENT);
        minimize_btn.setAlignmentX(Box.CENTER_ALIGNMENT);
        minimize_btn.setBounds(1195, 15, 25, 25);
        minimize_btn.setFont(textFont.deriveFont(44f));
        minimize_btn.setFocusPainted(false);
        minimize_btn.setContentAreaFilled(false);
        minimize_btn.setOpaque(true);
        minimize_btn.setBorder(null);
        minimize_btn.setBackground(Colors.bgColor);
        minimize_btn.addChangeListener(evt -> {
            if (minimize_btn.getModel().isPressed()) {
                this.setState(JFrame.ICONIFIED);
                minimize_btn.setForeground(Colors.primaryColor);
            } else if (minimize_btn.getModel().isRollover()) {
                minimize_btn.setForeground(Colors.secondaryColor);
            } else {
                minimize_btn.setForeground(Colors.primaryColor);
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
        username.setForeground(Colors.primaryColor);


        password = new JLabel();
        password.setText("Password");
        password.setFont(textFont.deriveFont(44f));
        password.setAlignmentX(Box.CENTER_ALIGNMENT);
        password.setBounds(822, 340, 800, 150);
        password.setForeground(Colors.primaryColor);
    }

    public void createTextFields()
    {
        username_txt_fld = new JTextField("");
        username_txt_fld.setFont(password_font);
        username_txt_fld.setBounds(822, 319, 300, 50);
        username_txt_fld.setBackground(Colors.bgColor);
        username_txt_fld.setOpaque(true);
        username_txt_fld.setBorder(null);
        username_txt_fld.setForeground(Colors.accentColor);

        password_txt_fld = new JPasswordField("");
        password_txt_fld.setFont(password_font);
        password_txt_fld.setBounds(822, 452, 300, 50);
        password_txt_fld.setBackground(Colors.bgColor);
        password_txt_fld.setOpaque(true);
        password_txt_fld.setBorder(null);
        password_txt_fld.setEchoChar('*');
        password_txt_fld.setForeground(Colors.accentColor);
        password_txt_fld.setAlignmentY(Box.CENTER_ALIGNMENT);
    }
}
