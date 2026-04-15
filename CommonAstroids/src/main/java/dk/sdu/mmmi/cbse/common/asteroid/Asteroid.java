package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.data.Entity;

public class Asteroid extends Entity {

    public enum Size {
        LARGE(20f),
        MEDIUM(12f),
        SMALL(7f);

        private final float radius;

        Size(float radius) {
            this.radius = radius;
        }

        public float radius() {
            return radius;
        }

        public Size nextSmaller() {
            return switch (this) {
                case LARGE -> MEDIUM;
                case MEDIUM -> SMALL;
                case SMALL -> null;
            };
        }
    }

    private Size size = Size.LARGE;

    public Asteroid() {
        setSize(Size.LARGE);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
        int r = Math.round(size.radius());
        setRadius(size.radius());
        setPolygonCoordinates(r, -r, -r, -r, -r, r, r, r);
    }

    public boolean canSplit() {
        return size.nextSmaller() != null;
    }
}
