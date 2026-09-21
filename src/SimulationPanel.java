import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class SimulationPanel extends JPanel {

    private final World world;

    public SimulationPanel(World world) {
        this.world = world;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Entity entity : world.getEntities()) {

            int x = (int) entity.getX();
            int y = (int) entity.getY();

            if (entity instanceof Rabbit) {
                g.setColor(Color.GREEN);
                g.fillOval(x, y, 12, 12);
            }

            if (entity instanceof Wolf) {
                g.setColor(Color.RED);
                g.fillOval(x, y, 18, 18);
            }
        }

        g.setColor(Color.BLACK);

        int rabbits = 0;
        int wolves = 0;

        for (Entity entity : world.getEntities()) {

            if (entity instanceof Rabbit) {
                rabbits++;
            }

            if (entity instanceof Wolf) {
                wolves++;
            }
        }

        g.drawString("Rabbits: " + rabbits, 10, 20);
        g.drawString("Wolves: " + wolves, 10, 40);
    }
}