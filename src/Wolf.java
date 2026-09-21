public class Wolf extends Entity {

    private int energy;

    public Wolf(double x, double y) {
        super(x, y);
        this.energy = 150;
    }

    @Override
    public void update() {
        energy--;

        if (energy < 0) {
            energy = 0;
        }
    }

    public void moveTowards(Entity target) {
        double dx = target.getX() - x;
        double dy = target.getY() - y;

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 20) {
            x += (dx / distance) * 2;
            y += (dy / distance) * 2;
        }

        keepInsideWorld();
    }

    public void separateFrom(Wolf other) {
        double dx = x - other.getX();
        double dy = y - other.getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0 && distance < 30) {
            x += (dx / distance) * 2;
            y += (dy / distance) * 2;
        }

        keepInsideWorld();
    }

    private void keepInsideWorld() {
        if (x < 10) {
            x = 10;
        }

        if (x > 760) {
            x = 760;
        }

        if (y < 50) {
            y = 50;
        }

        if (y > 530) {
            y = 530;
        }
    }

    public int getEnergy() {
        return energy;
    }

    public void gainEnergy(int amount) {
        energy += amount;
    }
}