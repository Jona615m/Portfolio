package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.World;

public class AsteroidSplitter implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World w) {
        if (!(e instanceof Asteroid asteroid) || !asteroid.canSplit()) {
            return;
        }

        Asteroid.Size nextSize = asteroid.getSize().nextSmaller();
        double[] angle = {-25, 0, 25}; //Gives us 3 smaller asteroids when hit

        for(double angle1 : angle) {
            Asteroid smaller = new Asteroid();
            smaller.setSize(nextSize);

            double smallerAngle = asteroid.getRotation() + angle1;
            double smallerSpawn = asteroid.getRadius();

            smaller.setRotation(smallerAngle);
            smaller.setX(asteroid.getX() + Math.cos(Math.toRadians(smallerAngle)) * smallerSpawn);
            smaller.setY(asteroid.getY() + Math.sin(Math.toRadians(smallerAngle)) * smallerSpawn);

            w.addEntity(smaller);

        }
    }

}

