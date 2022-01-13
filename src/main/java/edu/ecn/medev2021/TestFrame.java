package edu.ecn.medev2021;

import javax.swing.*;

/**
 * Shows the {@link PgmGui}
 */
public class TestFrame
{
    public static void main(String[] args) {
        PgmGui gui = new PgmGui();
        JFrame frame = new JFrame();
        frame.add(gui.getRootPane());
        frame.setName("Pgm gui");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
