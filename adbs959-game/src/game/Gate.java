package game;

import city.cs.engine.*;
import org.jbox2d.dynamics.Body;
/** gate class that will allow the player to transition to the next level with a collision*/
public class Gate extends StaticBody {
    private static Shape shape = new PolygonShape(
            -0.22f,0.52f, 0.36f,0.51f, 0.28f,-0.94f, -0.15f,-0.97f);
    private static BodyImage image = new BodyImage("data/closedportal.gif", 3.5f);

    public Gate(World world){
        super(world, shape);
        addImage(image);
    }
}
