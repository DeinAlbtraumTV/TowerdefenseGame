package towerDefense.dialogs.components;

import towerDefense.dialogs.events.mouse.*;
import towerDefense.util.Checks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ComponentContainer extends JComponent {

    private final ArrayList<GenericComponent> components;

    private Image image;
    private Graphics2D g2d;

    public final int WIDTH;
    public final int HEIGHT;

    public ComponentContainer(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        this.components = new ArrayList<>();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX();
                int y = e.getY();
                for ( GenericComponent component : components ) {
                    if (component.getPos_x() < x && x < component.getPos_x() + component.WIDTH) {
                        if (component.getPos_y() < y && y < component.getPos_y() + component.HEIGHT) {
                            if (component.isVisible()) {
                                component.onEvent(new ClickEvent(x, y, e.getButton(), e, component));
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                for ( GenericComponent component : components ) {
                    if (component.getPos_x() < x && x < component.getPos_x() + component.WIDTH) {
                        if (component.getPos_y() < y && y < component.getPos_y() + component.HEIGHT) {
                            if (component.isVisible()) {
                                component.onEvent(new PressedEvent(x, y, e.getButton(), e, component));
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                for ( GenericComponent component : components ) {
                    if (component.isVisible()) {
                        component.onEvent(new ReleasedEvent(e, component));
                    }
                }
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int x = e.getX();
                int y = e.getY();
                for ( GenericComponent component : components ) {
                    if (component.getPos_x() < x && x < component.getPos_x() + component.WIDTH) {
                        if (component.getPos_y() < y && y < component.getPos_y() + component.HEIGHT) {
                            if (component.isVisible()) {
                                component.onEvent(new WheelRotatedEvent(x, y, e, component));
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                for ( GenericComponent component : components ) {
                    if (component.isVisible()) {
                        component.onEvent(new DraggedEvent(x, y, e, component));
                    }
                }
            }
        });
    }

    public ComponentContainer(int width, int height, ArrayList<GenericComponent> components) {
        this.WIDTH = width;
        this.HEIGHT = height;

        this.components = components;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
    }

    public void addComponents(GenericComponent... components) {
        Checks.noneNull(components, "components");

        Collections.addAll(this.components, components);
    }

    public void removeComponents(GenericComponent... components) {
        Checks.noneNull(components, "components");

        this.components.removeAll(Arrays.asList(components));
    }

    public ArrayList<GenericComponent> getAddedComponents() {
        return components;
    }

    public void clearPaint() {
        g2d.clearRect(0, 0, getWidth(), getHeight());
        repaint();
        g2d.setColor(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null || image.getWidth(this) < getSize().width || image.getHeight(this) < getSize().height) {
            resetImage();
        }

        updateImage();
        g.drawImage(image, 0, 0, this);
    }

    private void updateImage() {
        clearPaint();

        for ( GenericComponent component : components ) {
            g2d.drawImage(component.render(createImage(component.WIDTH, component.HEIGHT)), component.getPos_x(), component.getPos_y(), this);
        }
    }

    private void resetImage() {
        Image saveImage = image;
        Graphics2D saveG2d = g2d;

        image = createImage(getWidth(), getHeight());
        g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        if (saveG2d != null) {
            g2d.setColor(saveG2d.getColor());
            g2d.drawImage(saveImage, 0, 0, this);
            saveG2d.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (image != null) {
            size.width = image.getWidth(this);
            size.height = image.getHeight(this);
        }
        return size;
    }


}
