package game;

import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game{

    /** declaring variables */
    private GameLevel level;
    private MyView view;
    private Controller controller;
    private MouseHandler mouseHandler;
    private SoundClip level1track;
    private SoundClip level2track;
    private SoundClip level3track;
    private SoundClip level4track;

    public Game(){
        level = new Level1(this);
        /** populate method is called, contains bodies that are worth saving */
        level.Populate(this);
        /** setting the view, and the mousehandler and controller to the view */
        view = new MyView(this,level, level.person, 1000, 520);
        /** adds a controller and mousehandler to the panel**/
        controller = new Controller(this);
        view.addKeyListener(controller);
        mouseHandler = new MouseHandler(this, level);
        view.addMouseListener(mouseHandler);
        view.addMouseListener(new GiveFocus(view));
        /** added a tracker that prevents the user from 'falling' off the map */
        level.addStepListener(new Tracker(this, level.getPerson()));
        final JFrame frame = new JFrame("Demo");
        /** setting the gui */
        ControlPanel controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(),BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        frame.setResizable(false);
        //JFrame debugView = new DebugViewer(level, 1000, 500);
        //view.setGridResolution(1);

        /**creating the background tracks
        surrounded in try and catch in case there are any file errors */
        try{
            level1track = new SoundClip("data/soundeffects/track1.wav");
            level2track = new SoundClip("data/soundeffects/track2.wav");
            level3track = new SoundClip("data/soundeffects/track3.wav");
            level4track = new SoundClip("data/soundeffects/track4.wav");
            level1track.loop();
        }   catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
        level.start();
    }
    private void Set() {
        /**used to set the game level, --repeated code-- */
        view.setWorld(level);
        JFrame debugView = new DebugViewer(level, 1000, 500);
        level.addStepListener(new Tracker(this, level.getPerson()));
        level.start();
    }
    /**code that transitions the game to the next level */
    public void goToNextLevel(){
        if (level instanceof Level1){
            level.stop();
            level = new Level2(this);
            level.Populate(this);
            Set();
            level1track.stop();
            level2track.loop();
        }
        else if (level instanceof Level2){
            level.stop();
            level = new Level3(this);
            level.Populate(this);
            Set();
            level2track.stop();
            level3track.loop();
        }
        else if (level instanceof Level3){
            level.stop();
            level = new Level4(this);
            level.Populate(this);
            Set();
            level3track.stop();
            level4track.loop();
        }
        else if (level instanceof Level4){
            System.out.println("Congratulations, game completed!");
        }
    }
    /**setting the view for the next level */
    public void setLevel(GameLevel level){
        this.level.stop();
        this.level = level;
        view.setWorld(this.level);
        this.level.start();
    }
    /**returns the level to change the background */
    public GameLevel getLevel() {
        return level;
    }
    public static void main(String[] args){
        new Game();
    }
    /**used for gui functions
    pauses and resumes the game, character is unable to move */
    public void pause(){
        if (level instanceof Level1 || level instanceof Level2 || level instanceof Level3 || level instanceof Level4) {
            level.stop();
            level1track.stop();
            level2track.stop();
            level3track.stop();
            level4track.stop();
        }
    }
    public void play(){
        level.start();
        if(level instanceof Level1){
            level1track.loop();
        }else if(level instanceof Level2){
            level2track.loop();
        }else if(level instanceof Level3){
            level3track.loop();
        }else if(level instanceof Level4){
            level4track.loop();
        }
    }
    /**restarts from the first level */
    public void restart(){
        if(level instanceof Level1 || level instanceof Level2 || level instanceof Level3 || level instanceof Level4){
            level.stop();
            level = new Level1(this);
            level.Populate(this);
            Set();
            level1track.loop();
            level2track.stop();
            level3track.stop();
            level4track.stop();
        }
    }
    /**quits the game entirely */
    public void quit(){
        System.exit(0);
    }
    /**mutes and unmutes the background tracks */
    public void mute(){
        if(level instanceof Level1){
            level1track.stop();
        }else if(level instanceof Level2){
            level2track.stop();
        }else if(level instanceof Level3){
            level3track.stop();
        }else if(level instanceof Level4) {
            level4track.stop();
        }
    }
    public void unmute(){
        if(level instanceof Level1){
            level1track.loop();
        }else if(level instanceof Level2){
            level2track.loop();
        }else if(level instanceof Level3){
            level3track.loop();
        }else if(level instanceof Level4){
            level4track.loop();
        }
    }
    /**step between levels */
    public void JumpNext(){
        if (level instanceof Level1){
            level.stop();
            level = new Level2(this);
            level.Populate(this);
            Set();
            level1track.stop();
            level2track.loop();
        }else if (level instanceof Level2){
            level.stop();
            level = new Level3(this);
            level.Populate(this);
            Set();
            level2track.stop();
            level3track.loop();
        }
        else if (level instanceof Level3){
            level.stop();
            level = new Level4(this);
            level.Populate(this);
            Set();
            level3track.stop();
            level4track.loop();
        }
    }
    public void JumpPrevious(){
        if (level instanceof Level2){
            level.stop();
            level = new Level1(this);
            level.Populate(this);
            Set();
            level2track.stop();
            level1track.loop();
        }
        else if (level instanceof Level3){
            level.stop();
            level = new Level2(this);
            level.Populate(this);
            Set();
            level3track.stop();
            level2track.loop();
        }else if (level instanceof Level4){
            level.stop();
            level = new Level3(this);
            level.Populate(this);
            Set();
            level4track.stop();
            level3track.loop();
        }
    }
    /**saves and loads the game, player can choose the save name and can choose which file they wish to load.
    The current game state is saved, any bodies destroyed will remain destroyed, player can start from mid level */
    public void Save(){
        try {
            /**Instead of a single save file, the player is given the choice to save multiple files
            this uses the systems file directory*/
            FileDialog dialog = new FileDialog((Frame)null, "Save as");
            dialog.setMode(FileDialog.SAVE);
            dialog.setVisible(true);
            String file = dialog.getFile();
            System.out.println(file + " saved");
            GameSaverLoader.save(getLevel(),"data/"+file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public void Load(){
        level1track.stop();
        level2track.stop();
        level3track.stop();
        level4track.stop();
        try {
            /**the player can also load any save file they wish to use, provided it is in the 'data' folder */
            FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();
            System.out.println(file + " opened");
            GameLevel level = GameSaverLoader.load(this,"data/"+file);
            this.setLevel(level);
            Set();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
