package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {
    public void render(World world, GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600); // Clear the screen
        for (Entity entity : world.getEntities()) {
            double[] coords = entity.getPolygonCoordinates();
            double x = entity.getX();
            double y = entity.getY();
            if (coords != null && coords.length >= 6) {
                double[] xPoints = new double[coords.length / 2];
                double[] yPoints = new double[coords.length / 2];
                for (int i = 0; i < coords.length; i += 2) {
                    xPoints[i / 2] = x + coords[i];
                    yPoints[i / 2] = y + coords[i + 1];
                }
                gc.setStroke(Color.WHITE);
                gc.strokePolygon(xPoints, yPoints, xPoints.length);
            }
        }
    }
}

