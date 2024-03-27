import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class RoundedBorderButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RoundedBorderButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color bgColor = getModel().isPressed() ? getBackground().darker() : getBackground();
        g2d.setColor(bgColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        super.paintComponent(g2d);
        g2d.dispose();
    }

    private static class RoundedBorder extends LineBorder {
        private static final long serialVersionUID = 1L;

        public RoundedBorder() {
            super(new Color(29, 33, 36), 1, true);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape borderShape = new RoundRectangle2D.Float(x, y, width - 1, height - 1, 20, 20);

            g2d.setColor(getLineColor());
            g2d.draw(borderShape);

            g2d.dispose();
        }
    }
}
