package space.moonface.application.mandelbrotviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RenderBufferedPanel extends JPanel {

    public static int panelWidth = 800;
    public static int panelHeight = (int) (0.8 * panelWidth);

    //debug
    public static String mouse = "debug";

    public RenderBufferedPanel() {
        ComponentListener resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                Dimension dimNew = e.getComponent().getSize();
                panelHeight = dimNew.height;
                panelWidth = dimNew.width;

                Main.startCalculator();
            }
        };
        addComponentListener(resizeListener);

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        };
        Timer timer = new Timer(200, taskPerformer);
        timer.start();
    }

    private void printPixel(int x, int y, Color color, Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(panelWidth, panelHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, panelWidth, panelHeight);

        for (int x = 0; x < panelWidth; x++) {
            for (int y = 0; y < panelHeight; y++) {
                if (Mandelbrot.framebuffer[x][y] == 0) {
                    printPixel(x, y, Color.BLACK, g);
                } else if (Mandelbrot.framebuffer[x][y] == -1) {
                    printPixel(x, y, Color.WHITE, g);
                } else {
                    printPixel(x, y, Color.getHSBColor((float) (1 - 1.0 / 50 * Mandelbrot.framebuffer[x][y]), 1, 1), g);
                }
            }

        }

        //debug
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(mouse, 5, 15);
    }

}