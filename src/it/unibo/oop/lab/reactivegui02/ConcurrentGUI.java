package it.unibo.oop.lab.reactivegui02;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConcurrentGUI extends JFrame {

    private static final long serialVersionUID = -8630968055862320453L;
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    final JLabel label = new JLabel();
    final JButton up = new JButton("Up");
    final JButton down = new JButton("Down");
    final JButton stop = new JButton("Stop");

    public ConcurrentGUI() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final JPanel canvas = new JPanel();
        canvas.add(this.label);
        canvas.add(this.up);
        canvas.add(this.down);
        canvas.add(this.stop);

        this.add(canvas);
        this.setVisible(true);
    }

}
