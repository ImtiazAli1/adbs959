package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**all gui elements are implemented here through Game.java*/
public class ControlPanel {
    private Game game;
    private JPanel mainPanel;
    private JButton pauseButton;
    private JButton restartButton;
    private JButton quitButton;
    private JButton resumeButton;
    private JButton muteButton;
    private JButton unmuteButton;
    private JButton jumpNextButton;
    private JButton jumpPreviousButton;
    private JButton saveButton;
    private JButton loadButton;


    public ControlPanel(Game game){
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pause();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.quit();
            }
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.play();
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.mute();
            }
        });
        unmuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.unmute();
            }
        });
        jumpNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.JumpNext();
            }
        });
        jumpPreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.JumpPrevious();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.Save();
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.Load();
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
