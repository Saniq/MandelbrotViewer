package space.moonface.application.mandelbrotviewer;

public class Mandelbrot implements Runnable {

    public static int[][] framebuffer = new int[1920][1080];

    public static double xMin = -2.0;
    public static double xMax = 0.5;
    public static double yMin = -1.0;
    public static double yMax = 1.0;

    public static void resetBounds() {
        xMin = -2.0;
        xMax = 0.5;
        yMin = -1.0;
        yMax = 1.0;
    }

    public static int boundedSequence(ComplexNumber z, ComplexNumber c, int depth) {
        ComplexNumber zNew;

        if (ComplexNumber.length(z) > 2) {
            return depth;
        } else if (depth > 1000) {
            return 0;
        }

        zNew = ComplexNumber.add(ComplexNumber.pow(z), c);
        return boundedSequence(zNew, c, depth + 1);
    }

    @Override
    public void run() {
        int depth;
        ComplexNumber z = new ComplexNumber(0, 0);
        ComplexNumber c = new ComplexNumber();

        for (int x = 0; x < 1920; x++) {
            for (int y = 0; y < 1080; y++) {
                framebuffer[x][y] = -1;
            }
        }

        for (int x = 0; x < RenderBufferedPanel.panelWidth; x++) {
            for (int y = 0; y < RenderBufferedPanel.panelHeight; y++) {
                c.setReal((xMax - xMin) / RenderBufferedPanel.panelWidth * x + xMin);
                c.setImag((yMax - yMin) / RenderBufferedPanel.panelHeight * y + yMin);

                depth = Mandelbrot.boundedSequence(z, c, 0);
                framebuffer[x][y] = depth;
            }

        }
    }
}