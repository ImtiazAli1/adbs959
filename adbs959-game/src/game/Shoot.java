package game;

import city.cs.engine.*;
/**shoot class for the player*/
public class Shoot extends Walker {

    private static Shape shape = new PolygonShape(
            0.598f,-0.194f, 0.334f,0.044f, 0.041f,-0.184f, 0.29f,-0.472f);

    private static BodyImage image = new BodyImage("data/player/shoot/fireball.gif", 1f);

    public Shoot(World world){
        super(world, shape);
        addImage(image);
    }
}
