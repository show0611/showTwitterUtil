package com.github.yossy0610.stu.window;

import com.github.yossy0610.replayLiker.core.STUCore;
import javax.swing.*;

/**
 * Created by yossy.0610 on 2016/05/30.
 */
public class MainWindow extends JFrame {
    private JPanel panel;
    private JTextArea textArea1;
    private JButton startButton;
    private JButton stopButton;

    public MainWindow() {
        this.setContentPane(panel);
        this.setTitle("Reply Liker /* CustomEdition */");
        this.setSize(600, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }
}
