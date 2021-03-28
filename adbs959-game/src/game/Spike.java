package game;

import city.cs.engine.*;
/**static body that uses a collision listener later on*/
public class Spike extends StaticBody {
    private static Shape shape = new PolygonShape(
            -1.05f,-0.746f, -0.663f,0.355f, -0.225f,0.606f, 0.225f,0.61f, 0.65f,0.359f, 1.054f,-0.75f);
    private static BodyImage image = new BodyImage("data/enemy/traps/Spike.png", 1.5f);

    public Spike(World w) {
        super(w, shape);
        addImage(image);
    }
}
