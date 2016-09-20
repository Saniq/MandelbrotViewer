package space.moonface.application.mandelbrotviewer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showUserInterface();
            }
        });
    }

    private static void showUserInterface() {
        RenderBufferedPanel panel = new RenderBufferedPanel();
        JFrame f = new JFrame("Mandelbrot Viewer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(panel);
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        panel.addMouseListener(mouseListener);
    }

    public static void startCalculator() {
        Thread calculator = new Thread(new Mandelbrot());
        calculator.start();
    }

    private static MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {

                double xPosition = (Mandelbrot.xMax - Mandelbrot.xMin) / RenderBufferedPanel.panelWidth * e.getX() + Mandelbrot.xMin;
                double yPosition = (Mandelbrot.yMax - Mandelbrot.yMin) / RenderBufferedPanel.panelHeight * e.getY() + Mandelbrot.yMin;

                Mandelbrot.xMin = xPosition - (Mandelbrot.xMax - Mandelbrot.xMin) / 4;
                Mandelbrot.xMax = xPosition + (Mandelbrot.xMax - Mandelbrot.xMin) / 4;

                Mandelbrot.yMin = yPosition - (Mandelbrot.yMax - Mandelbrot.yMin) / 4;
                Mandelbrot.yMax = yPosition + (Mandelbrot.yMax - Mandelbrot.yMin) / 4;

                //debug
                RenderBufferedPanel.mouse = String.valueOf(Mandelbrot.xMax - Mandelbrot.xMin);

                startCalculator();
            }
            if (SwingUtilities.isRightMouseButton(e)) {
                Mandelbrot.resetBounds();

                //debug
                RenderBufferedPanel.mouse = String.valueOf(Mandelbrot.xMax - Mandelbrot.xMin);

                startCalculator();
            }
        }
    };

}