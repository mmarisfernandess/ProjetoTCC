package ProjConcArmadoB;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel implements Scrollable {


    Component comp;
    private int W;
    private int H;

    public DrawingPanel(Component comp) {
        this.H = 800;
        this.W = 298;

        this.comp = comp;
        add(this.comp);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }



    @Override
    public Dimension getPreferredScrollableViewportSize() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        return new Dimension(298, d.height - 198);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return W / 10;
        } else {
            return 10;
        }
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.HORIZONTAL) {
            return W / 10;
        } else {
            return 10;
        }
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}

