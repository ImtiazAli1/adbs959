package game;

import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**created a mousehandler that allows the player to shoot. The 'shot' is based on the x position of the mouse click
the shot os created from the players body and travels horizontally until it collides with something*/
public class MouseHandler implements MouseListener{

    private GameLevel level;
    private Person person;
    private Game game;
    public MouseHandler(Game game, GameLevel level) {
        this.game = game;
        this.level = level;
    }

    private static final float ShootSpeed = 5;

    BodyImage image = new BodyImage("data/player/standing.png", 2.5f);
    BodyImage shootright = new BodyImage("data/player/shootright/shoot.gif", 2.5f);
    BodyImage shootleft = new BodyImage("data/player/shootleft/shoot.gif", 2.5f);
    BodyImage fireball = new BodyImage("data/player/shoot/fireball.gif", 1f);

    @Override
    public void mousePressed(MouseEvent e) {
        /**mouse coordinates and JFrame coordinates are different
        i had to calculate the differences between them to be able to shoot properly*/
        double xPos = game.getLevel().getPerson().getPosition().x*20.5;
        Shoot shoot = new Shoot(game.getLevel());
        shoot.setGravityScale(0);
        if(e.getX() >= 500){
            shoot(e, xPos, shoot);
        } else if(xPos <= 501){
            shoot(e, xPos, shoot);
        }
    }
    /**code that discerns which way the shot is created*/
    private void shoot(MouseEvent e, double xPos, Shoot shoot) {
        if(e.getY()<=500){
        if(e.getX()-500 > xPos){
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(shootright);
            shoot.setPosition(new Vec2(game.getLevel().getPerson().getPosition().x,game.getLevel().getPerson().getPosition().y));
            shoot.startWalking(ShootSpeed);
        }
        if(e.getX()-500 < xPos){
            game.getLevel().getPerson().removeAllImages();
            game.getLevel().getPerson().addImage(shootleft);
            shoot.setPosition(new Vec2(game.getLevel().getPerson().getPosition().x-.5f,game.getLevel().getPerson().getPosition().y));
            shoot.startWalking(-ShootSpeed);
            shoot.removeAllImages();
            shoot.addImage(fireball).flipHorizontal();
        }}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.getLevel().getPerson().removeAllImages();
        game.getLevel().getPerson().addImage(image);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
