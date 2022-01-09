/*
 * The MIT License
 *
 * Copyright 2021 a10.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package dominoxj;

//import javax.swing.JOptionPane;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;

/**
 * class for creaing start menu for <b>DominoXj</b> game
 *
 * @author a10
 */
public class Menu extends Parent {
//    private static final Logger LOG = Logger.getLogger(Menu.class.getName());

    public static MainFrame mainFrame = Main.getMainFrame();
    public static Group group;

    Menu() {
        ImageView background;
        Button play, hightScore, about, settings, exit;
        Text logo, license;
        VBox vBoxMenu;
        RotateTransition rotateTransition, rotateTransition2;

        Config.getSound(Config.SOUND_START).play(1.0);
        background = new ImageView();
        background.setFocusTraversable(true);
        background.setImage(Config.getImage(Config.IMAGE_BACKGROUND));

        play = new Button("Play");
        play.setTooltip(new Tooltip("Start new game DominoXj"));
        play.setFont(Font.font(Font.getDefault().getFamily(), 24));
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainFrame.changeState(MainFrame.TABLE);
            }
        });

        settings = new Button("Settings");
        settings.setTooltip(new Tooltip("Setup options DominoXj game"));
        settings.setFont(Font.font(Font.getDefault().getFamily(), 24));
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Settings...");
                mainFrame.changeState(MainFrame.SETTINGS);
            }
        });

        hightScore = new Button("Score");
        hightScore.setTooltip(new Tooltip("Show DominoXj hightscore time"));
        hightScore.setFont(Font.font(Font.getDefault().getFamily(), 24));
        hightScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String s;
                Score h = Main.getHightScore();
                group.setVisible(false);
                MainFrame.SwingDialog dial = new MainFrame.SwingDialog();
                if (h.getBestTimeDelta() != 0) {
                    s = "Time: " + h.getString();
                } else {
                    s = "try to pass game";
                }
                int res = dial.getResult(
                        "Best DominoXj",
                        s,
                        MainFrame.SwingDialog.TYPE_OK
                );
                group.setVisible(true);
            }
        });

        about = new Button("About");
        about.setTooltip(new Tooltip("Show about DominoXj game"));
        about.setFont(Font.font(Font.getDefault().getFamily(), 24));
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group.setVisible(false);
                MainFrame.SwingDialog dial = new MainFrame.SwingDialog();
                int res = dial.getResult(
                        "About DominoXj",
                        "v." + Config.GAME_VERSION + " Pick pair of dominoeses",
                        MainFrame.SwingDialog.TYPE_OK
                );
                group.setVisible(true);
            }
        });

        exit = new Button("Exit");
        exit.setTooltip(new Tooltip("Exit DominoXj game"));
        exit.setFont(Font.font(Font.getDefault().getFamily(), 24));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainFrame.SwingDialog dial = new MainFrame.SwingDialog();
                int res = dial.getResult(
                        "Exit",
                        "Do You want to exit?",
                        MainFrame.SwingDialog.TYPE_YES_NO
                );
                if (res == 0) {
                    System.exit(0);
                }
                System.out.println(res);
            }
        });

        logo = new Text("DominoXj");
        logo.setFill(Color.AQUAMARINE);
        logo.setBoundsType(TextBoundsType.VISUAL);
        logo.setFont(Font.font(Font.getDefault().getFamily(), 50));
        rotateTransition = new RotateTransition();
        rotateTransition.setNode(logo);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(10);
        rotateTransition.setCycleCount(6);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

        license = new Text("MIT License");

        vBoxMenu = new VBox();
        vBoxMenu.setSpacing(10);
        vBoxMenu.setEffect(new Lighting());
        vBoxMenu.setAlignment(Pos.TOP_CENTER);
        vBoxMenu.getChildren().addAll(
                logo,
                play,
                settings,
                hightScore,
                about,
                exit,
                license
        );
        vBoxMenu.setLayoutX(
                (Config.SCREEN_WIDTH
                - (int) logo.getLayoutBounds().getWidth()) / 2
        );
        vBoxMenu.setLayoutY(Config.SCREEN_HEIGHT / 4);
        rotateTransition2 = new RotateTransition();
        rotateTransition2.setNode(vBoxMenu);
        rotateTransition2.setDuration(Duration.seconds(1));
        rotateTransition2.setFromAngle(0);
        rotateTransition2.setToAngle(360);
        rotateTransition2.setCycleCount(1);
        rotateTransition2.setAutoReverse(true);
        rotateTransition2.play();
        group = new Group();
        group.getChildren().addAll(background, vBoxMenu);
        group.autosize();
        getChildren().add(group);
    }

    public void start() {
    }

    public void stop() {
    }
}
