import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Entity> entities;

    public World() {
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void update() {
// Prevent wolves from overlapping.
for (Entity first : entities) {

    if (first instanceof Wolf) {

        Wolf firstWolf = (Wolf) first;

        for (Entity second : entities) {

            if (second instanceof Wolf && first != second) {

                Wolf secondWolf = (Wolf) second;

                firstWolf.separateFrom(secondWolf);
            }
        }
    }
}
        // Move rabbits
        for (Entity entity : entities) {
            if (entity instanceof Rabbit) {
                entity.update();
            }
        }

        // Move wolves towards the nearest rabbit
        for (Entity entity : entities) {
            if (entity instanceof Wolf) {

                Wolf wolf = (Wolf) entity;
                Rabbit nearestRabbit = findNearestRabbit(wolf);

                if (nearestRabbit != null) {
                    wolf.moveTowards(nearestRabbit);
                }
            }
        }

        handleInteractions();
        handleReproduction();
    }

    private Rabbit findNearestRabbit(Wolf wolf) {

        Rabbit nearest = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Entity entity : entities) {

            if (entity instanceof Rabbit) {

                Rabbit rabbit = (Rabbit) entity;
                double distance = distance(wolf, rabbit);

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearest = rabbit;
                }
            }
        }

        return nearest;
    }

    private void handleInteractions() {

        List<Entity> removed = new ArrayList<>();

        for (Entity predator : entities) {

            if (predator instanceof Wolf) {

                Wolf wolf = (Wolf) predator;

                for (Entity prey : entities) {

                    if (prey instanceof Rabbit && !removed.contains(prey)) {

                        double distance = distance(wolf, prey);

                        if (distance < 12) {
                            removed.add(prey);
                            wolf.gainEnergy(40);
                        }
                    }
                }
            }
        }

        for (Entity entity : removed) {
            removeEntity(entity);
        }
    }

    private void handleReproduction() {

        List<Entity> newborns = new ArrayList<>();

        int rabbitCount = 0;

        for (Entity entity : entities) {
            if (entity instanceof Rabbit) {
                rabbitCount++;
            }
        }

        if (rabbitCount < 8) {

            for (Entity entity : entities) {

                if (entity instanceof Rabbit) {

                    Rabbit rabbit = (Rabbit) entity;

                    if (Math.random() < 0.01) {
                        newborns.add(rabbit.reproduce());
                    }
                }
            }
        }

        for (Entity newborn : newborns) {
            addEntity(newborn);
        }
    }

    private double distance(Entity first, Entity second) {

        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}