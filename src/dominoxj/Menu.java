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
import static javafx.animation.RotateTransitionBuilder.create;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
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
    private Timeline timeline;

    Menu() {
        ImageView background;
        Button play, hightScore, about, settings, exit;
        Text logo, license;
        VBox vBoxMenu;
        RotateTransition rotateTransition, rotateTransition2;

        Config.getSounds().get(Config.SOUND_START).play(1.0);
        background = new ImageView();
        background.setFocusTraversable(true);
        background.setImage(Config.getImages().get(Config.IMAGE_BACKGROUND));

        play = new Button("Play");
        play.setTooltip(new Tooltip("Start new game DominoXj"));
        play.setFont(Font.font(Font.getDefault().getFamily(), 24));
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("Play...");
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
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Best DominoXj");
//                Main.Score h = Main.getHightScore();
//                if (h.getBestTimeDelta() != 0) {
//                    alert.setHeaderText("HigthScore time: " + h.getString());
//                    alert.setContentText("try to bit");
//                } else {
//                    alert.setHeaderText("no hightscore time");
//                    alert.setContentText("try to pass game");
//                }
//                alert.showAndWait();
            }
        });

        about = new Button("About");
        about.setTooltip(new Tooltip("Show about DominoXj game"));
        about.setFont(Font.font(Font.getDefault().getFamily(), 24));
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("About DominoXj");
//                alert.setHeaderText("DominoXj game");
//                alert.setContentText("v." + Config.GAME_VERSION + "\n"
//                        + "Pick pair of domino dominoeses sum 12 to remove\n"
//                        + "MIT License\n"
//                //                                 + "(c) a10 - Alexander Desyatnichenko"
//                );
//                alert.showAndWait();
            }
        });

        exit = new Button("Exit");
        exit.setTooltip(new Tooltip("Exit DominoXj game"));
        exit.setFont(Font.font(Font.getDefault().getFamily(), 24));
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                jOptionPane.
//                jOptionPane.setResizable(false);
//                jOptionPane.setAlwaysOnTop(true);
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Exit?");
//                alert.setHeaderText("Confirm Exit");
//                alert.setContentText("Exit application?");
//                alert.showAndWait();
//                if (alert.getResult() == ButtonType.OK) {
//                    System.exit(0);
//                }
                
                MainFrame.SwingDialog swingDialog;
                swingDialog = new MainFrame.SwingDialog();
                swingDialog.setLabel("Exit?");
                swingDialog.setText("Do You want to exit?");
                int res = swingDialog.getResult();
                if (res==0) {
                    System.exit(0);
                }
//                System.out.println(swingDialog.getResult());
            }
        });

        logo = new Text("DominoXj");
        logo.setFill(Color.AQUAMARINE);
        logo.setBoundsType(TextBoundsType.VISUAL);
        logo.setFont(Font.font(Font.getDefault().getFamily(), 50));
        rotateTransition = create()
                .node(logo)
                .duration(Duration.seconds(1))
                .fromAngle(0)
                .toAngle(10)
                .cycleCount(6)
                .autoReverse(true)
                .build();
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
        rotateTransition2 = create()
                .node(vBoxMenu)
                .duration(Duration.seconds(1))
                .fromAngle(0)
                .toAngle(360)
                .cycleCount(1)
                .autoReverse(true)
                .build();
        rotateTransition2.play();

        Group group = new Group();
        group.getChildren().addAll(background, vBoxMenu);
        group.autosize();
        getChildren().add(group);
    }

    public void start() {
    }

    public void stop() {
    }
}
