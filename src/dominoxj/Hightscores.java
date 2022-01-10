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

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
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
}
