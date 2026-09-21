public class Rabbit extends Entity {

    private int energy;

    public Rabbit(double x, double y) {
        super(x, y);
        this.energy = 100;
    }

    @Override
    public void update() {
        x += Math.random() * 12 - 6;
        y += Math.random() * 12 - 6;

        // Keep the rabbit inside the simulation area.
        if (x < 10) {
            x = 10;
        }

        if (x > 770) {
            x = 770;
        }

        if (y < 50) {
            y = 50;
        }

        if (y > 550) {
            y = 550;
        }

        energy--;

        if (energy <= 0) {
            energy = 100;
        }
    }

    public int getEnergy() {
        return energy;
    }

    public Rabbit reproduce() {
        return new Rabbit(
                x + Math.random() * 20 - 10,
                y + Math.random() * 20 - 10
        );
    }
}