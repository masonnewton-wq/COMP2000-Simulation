public class SimulationConfig {

    private final int rabbitCount;
    private final int wolfCount;

    public SimulationConfig(int rabbitCount, int wolfCount) {

        if (rabbitCount < 1) {
            throw new IllegalArgumentException(
                    "Rabbit population must be at least 1."
            );
        }

        if (wolfCount < 1) {
            throw new IllegalArgumentException(
                    "Wolf population must be at least 1."
            );
        }

        this.rabbitCount = rabbitCount;
        this.wolfCount = wolfCount;
    }

    public int getRabbitCount() {
        return rabbitCount;
    }

    public int getWolfCount() {
        return wolfCount;
    }
}