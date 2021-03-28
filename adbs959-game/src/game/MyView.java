package game;

import city.cs.engine.UserView;
import javax.swing.*;
import java.awt.*;

public class MyView extends UserView {

    private Person person;
    private GameLevel level;
    private Game game;

    public MyView(Game game, GameLevel level,Person person, int width, int height) {
        super(level, width, height);
        this.person = person;
        this.level = level;
        this.game = game;
    }

    /**gets the background image from the levels*/
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(game.getLevel().getBackground(),0,0,this);
    }
    /**displays the information, i.e health, points*/
    @Override
    protected void paintForeground(Graphics2D g){
        g.drawString("Lives:"+game.getLevel().getPerson().getLifeCount(),50, 515);
        g.drawString("Coins:"+ game.getLevel().getPerson().getCoinCount(),150, 515);
        g.drawString("Kills:"+ game.getLevel().getPerson().getKillCount(),250, 515);
    }
}
