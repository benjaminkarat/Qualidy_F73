package sortAlgorithms.VisualiserWindow;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingWorker;
import javax.swing.event.*;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class VisualiserWindow extends JPanel {
    JFrame window;
    JLabel liveCounter;
    public final int width = 800;
    public final int height = 600;
    public final boolean showLabels = false;
    public ArrayToSort array;
    /** Anzahl an Wartezeit(ms) nach einem update (Wird durch Slider gesteuert) */
    private int TIMESTEP = 5; 

    /**
     * Constructor initialisiert Fenster des Visualisierers
     * @param array ToSort-Instanz die visualisiert werden soll
     */
    public VisualiserWindow(ArrayToSort array) {     
        this.array = array;
        this.setBackground(Color.WHITE); 

        // Fenster
        JFrame window = new JFrame("Sortieralgorithmen");
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setContentPane(this);

        // Slider
        JSlider speedSlider = new JSlider(2, 10, this.TIMESTEP);
        speedSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider targetSlider = (JSlider) e.getSource();
                VisualiserWindow.this.TIMESTEP = 10 - targetSlider.getValue() + 1;
            }
            
        });
        //this.add(speedSlider); // AUSKOMMENTIEREN UM SLIDER FREIZUSCHALTEN

        // Operation Textanzeige
        this.liveCounter = new JLabel("");
        this.liveCounter.setForeground(new Color(0,113, 188));
        //this.add(liveCounter); // AUSKOMMENTIEREN UM OPERATIONENZÄHLER FREIZUSCHALTEN

        // Asynchroner SwingWorker, führt Sortieren im Hintergrund aus
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                long startTime = System.currentTimeMillis(); // Zeitmessung
                array.sort(); // Das Sortieren unseres Arrays, startet hier automatisch
                System.out.println("Das Sortieren dauerte " + (System.currentTimeMillis() - startTime) + " Millisekunden");
                return null;
            }
        };
        swingWorker.execute(); // Führt den zuvor definierten Code asynchron aus
    }

    /** Zeichnet Visualisierung neu und wartet TIMESTEP Millisekunden */
    public void update() {
        repaint(); // Swing-Methode um Fensterinhalte neu zu zeichnen (Führt paintComponent aus)
        try {
            Thread.sleep(this.TIMESTEP);
            liveCounter.setText("Anzahl Zugriffe: " + Integer.toString(array.counter.getCount())); // Aktualisiere Anzeige
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Bei Thread-Interrupt-Signal wird dieser Thread gestoppt.
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g.create(); // Canvas (Zeichenfläche) auf dem unsere Balken gezeichnet werden
        try {
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			canvas.addRenderingHints(renderingHints);
			canvas.setColor(Color.WHITE);
			drawRects(canvas);
		} finally {
        	canvas.dispose();
        }
    }

    /**
     * Zeichnet Rechtecke in der jeweils richtigen Farbe
     * @param canvas auf dem die Rechtecke gezeichnet werden
     */
    private void drawRects(Graphics2D canvas) {
        int barWidth = (getWidth() / this.array.size()) + 1; // Breite der Rechtecke abhängig der Bildschirmbreite
        int bufferedImageWidth = barWidth * this.array.size();

        BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, getHeight(), BufferedImage.TYPE_INT_ARGB); // Objekt welches verschiedene Zeichenoperationen als Bild darstellt
        Graphics2D bufferedGraphics = null;
        try
			{
				bufferedGraphics = bufferedImage.createGraphics();

                // Die For-Schleife iteriert durch jeden Wert im Array und zeichnet den passenden Balken
				for (int i = 0; i < this.array.size(); i++) {
					double currentValue = this.array.values[i];
					double percentOfMax = currentValue / this.array.size();
					double heightPercentOfPanel = percentOfMax * 0.8; // Skalieren der y-Koordinate
					int height = (int) (heightPercentOfPanel * (double) getHeight());
					int xBegin = i + (barWidth - 1) * i;
					int yBegin = getHeight() - height;
                    bufferedGraphics.setColor(new Color(0,128,187));

					bufferedGraphics.fillRect(xBegin, yBegin, barWidth, height); // Zeichnen der Rechtecke auf dem bufferedGraphics Objekt
                    if (showLabels) bufferedGraphics.drawString(Integer.toString((int)currentValue), xBegin + 4, yBegin - 4);
				}
			} finally {
				if(bufferedGraphics != null) {
					bufferedGraphics.dispose();
				}
			}
			canvas.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null); // bufferedImage auf dem Canvas zeichnen
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
