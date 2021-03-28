package game;

import city.cs.engine.*;
/**heart class, used as a collectible that gives more health in a level*/
public class Heart extends StaticBody {

    private static Shape shape = new PolygonShape(
            -0.263f,0.319f, 0.202f,0.325f,
            0.13f,-0.284f, -0.002f,-0.44f, -0.126f,-0.278f);
    private static BodyImage image = new BodyImage("data/heart.gif", 1f);

    public Heart(World w) {
        super(w, shape);
        addImage(image);
    }
}
