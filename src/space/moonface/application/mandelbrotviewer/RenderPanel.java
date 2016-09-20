package space.moonface.application.mandelbrotviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class RenderPanel extends JPanel {

    private static int panelWidth = 800;
    private static int panelHeight = (int) (0.8 * panelWidth);

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(panelWidth, panelHeight);
    }

    public RenderPanel() {
        ComponentListener resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                Dimension dimNew = e.getComponent().getSize();
                panelHeight = dimNew.height;
                panelWidth = dimNew.width;
                repaint();
            }
        };

        addComponentListener(resizeListener);
    }

    private void printPixel(int x, int y, Color color, Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, panelWidth, panelHeight);

        int depth;
        ComplexNumber z = new ComplexNumber(0, 0);
        ComplexNumber c = new ComplexNumber();
        for (int x = 0; x < panelWidth; x++) {
            for (int y = 0; y < panelHeight; y++) {
                c.setReal(2.5 / panelWidth * x - 2.0);
                c.setImag(2.0 / panelHeight * y - 1.0);

                depth = Mandelbrot.boundedSequence(z, c, 0);
                if (depth == 0) {
                    printPixel(x, y, Color.BLACK, g);
                } else {
                    printPixel(x, y, Color.getHSBColor((float) (1 - 1.0 / 50 * depth), 1, 1), g);
                }
            }

        }
    }

}