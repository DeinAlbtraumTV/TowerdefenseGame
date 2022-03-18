package towerDefense.dialogs.components;

import java.awt.*;

public class ButtonComponent extends GenericComponent {
    private String content;

    public ButtonComponent(int pos_x, int pos_y, int width, int height, String content) {
        super(pos_x, pos_y, width, height);

        this.content = content;
    }

    @Override
    public Image render(Image image) {

        if (super.isVisible()) {
            Graphics2D g = (Graphics2D) image.getGraphics();

            g.setBackground(super.getBackgroundColor());
            g.clearRect(0, 0, WIDTH, HEIGHT);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(content, (super.WIDTH / 2) - (4 * content.length() + (WIDTH / 10)), (super.HEIGHT / 2) + 6);
        }

        return super.render(image);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}