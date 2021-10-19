package sortAlgorithms.VisualiserWindow;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class VisualiserWindow extends JPanel {
    JFrame window;
    public final int width = 1920;
    public final int height = 1080;
    private int barCount;
    private ArrayToSort array;
    private final int TIMESTEP;

    // Constructor initialisiert Fenster des Visualisierers
    public VisualiserWindow(ArrayToSort array, int TIMESTEP) {     
        this.array = array;
        this.TIMESTEP = TIMESTEP;
        this.barCount = this.array.size();
        this.setBackground(Color.BLACK);

        JFrame window = new JFrame("Sortieralgorithmen");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setContentPane(this);

        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                long startTime = System.currentTimeMillis();
                array.sort();
                System.out.println("Das Sortieren dauerte " + (System.currentTimeMillis() - startTime) + " Millisekunden");
                return null;
            }
        };
        swingWorker.execute();
    }

    public void update() {
        repaint();
        try {
            Thread.sleep(this.TIMESTEP);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g.create();
        try
		{
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			canvas.addRenderingHints(renderingHints);
			canvas.setColor(Color.WHITE);
			drawRects(canvas);
		} finally {
        	canvas.dispose();
        }
    }

    private void drawRects(Graphics2D canvas) {
        int barWidth = (getWidth() / this.barCount) + 1;
        int bufferedImageWidth = barWidth * this.barCount;

        BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bufferedGraphics = null;
        try
			{
				bufferedGraphics = bufferedImage.createGraphics();
				
				for (int i = 0; i < this.barCount; i++) {
					double currentValue = this.array.values[i];
					double percentOfMax = currentValue / this.array.size();
					double heightPercentOfPanel = percentOfMax * 0.8;
					int height = (int) (heightPercentOfPanel * (double) getHeight());
					int xBegin = i + (barWidth - 1) * i;
					int yBegin = getHeight() - height;
					bufferedGraphics.fillRect(xBegin, yBegin, barWidth, height);
				}
			}
			finally
			{
				if(bufferedGraphics != null)
				{
					bufferedGraphics.dispose();
				}
			}
			
			canvas.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}