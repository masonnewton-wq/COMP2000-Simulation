import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {

    public static void main(String[] args) {

World world = new World();

try {

    SimulationConfig config = new SimulationConfig(15, 4);

    for (int i = 0; i < config.getRabbitCount(); i++) {
        world.addEntity(new Rabbit(
                20 + Math.random() * 700,
                50 + Math.random() * 500
        ));
    }

    for (int i = 0; i < config.getWolfCount(); i++) {
        world.addEntity(new Wolf(
                20 + Math.random() * 700,
                50 + Math.random() * 500
        ));
    }

} catch (IllegalArgumentException e) {
    System.out.println("Invalid simulation configuration: " + e.getMessage());
    return;
}

        SimulationPanel panel = new SimulationPanel(world);

        JFrame frame = new JFrame("Predator-Prey Simulation");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(panel);
        frame.setVisible(true);

        Timer timer = new Timer(100, e -> {
            world.update();
            panel.repaint();
        });

        timer.start();
    }
}