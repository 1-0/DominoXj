/*
 * The MIT License
 *
 * Copyright 2022 a10.
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

import java.text.DateFormat;
import java.util.Date;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * class for creaing game table frame for <b>DominoXj</b> game
 *
 * @author a10
 */
public class Hightscores extends Parent {

    private static final MainFrame mainFrame = Main.getMainFrame();

    public static void checkHightScores(Score aHightScore) {
        Score s0, s1 = aHightScore;
        for (int i = 0; i < Config.HIGHTSCORES_LENGTH; i++) {
            s0 = Config.getScore(i);
            if (s0 != null) {
                if (s0.getTimeDelta() < s1.getTimeDelta()) {
                    Config.setScore(s1, i);
                    s1 = s0;
                }
            } else {
                Config.setScore(s1, i);
                return;
            }
        }
    }

    Hightscores() {
        Button menu;
        Text label;
        VBox vBoxHightscores;
        ImageView background;
        RotateTransition rotateTransition;
        Config.getSound(Config.SOUND_START).play(0.5);

        background = new ImageView();
        background.setImage(Config.getImage(Config.SELECTED_DOMINOES5));

        label = new Text("DominoXj Hightscores");
        label.setFill(Color.AQUA);
        label.setFont(Font.font(Font.getDefault().getFamily(), 24));

        menu = new Button("Menu");
        menu.setTooltip(new Tooltip("Return to DominoXj start menu"));
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainFrame.changeState(MainFrame.MENU);
            }
        });
        vBoxHightscores = new VBox();
        vBoxHightscores.setSpacing(10);
        vBoxHightscores.setAlignment(Pos.TOP_CENTER);
        vBoxHightscores.setLayoutY(Config.DOMINOES_SIZE * 2);
        vBoxHightscores.setLayoutX(
                (Config.SCREEN_WIDTH
                - (int) label.getLayoutBounds().getWidth()) / 2
        );
        vBoxHightscores.getChildren().addAll(
                label,
                menu
        );
        addHightscores(vBoxHightscores);
        getChildren().addAll(background, vBoxHightscores);
        background.setX(Config.DOMINOES_SIZE * 3);
        background.setY(Config.SCREEN_HEIGHT - Config.DOMINOES_SIZE * 5);
        background.setScaleX(2);
        background.setScaleY(2);
        rotateTransition = new RotateTransition();
        rotateTransition.setNode(background);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(-725);
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }

    public void start() {
    }

    private void addHightscores(VBox box) {
        int i = Config.HIGHTSCORES_LENGTH - 1;
        if (Config.getScore(0) == null) {
            Text l = new Text("No Hightscores - try to pass game");
            box.getChildren().add(l);
            return;
        }
        Text t0, t1, t2;
        HBox hbox = new HBox();
        box.getChildren().add(hbox);
        VBox v0 = new VBox(), v1 = new VBox(), v2 = new VBox();
        hbox.getChildren().addAll(v2, v0, v1);
        t0 = new Text(" Time Delta (ms) ");
        t1 = new Text(" Time Start ");
        t2 = new Text("Player ");
        v0.getChildren().add(t0);
        v1.getChildren().add(t1);
        v2.getChildren().add(t2);
        while (i != -1) {
            if (Config.getScore(i) != null) {
                t0 = new Text(" " + Config.getScore(i).getTimeDelta() + " ");
                t1 = new Text(" " 
                        + DateFormat.getDateTimeInstance().format(
                                new Date(Config.getScore(i).getTimeStart()))
                        + " ");
                t2 = new Text(Config.getScore(i).getPlayer());
                v0.getChildren().add(t0);
                v1.getChildren().add(t1);
                v2.getChildren().add(t2);
            }
            i--;
        }
    }
}
