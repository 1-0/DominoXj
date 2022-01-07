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

import javafx.animation.RotateTransition;
import static javafx.animation.RotateTransitionBuilder.create;
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
public class Settings extends Parent {

    private static final MainFrame mainFrame = Main.getMainFrame();
    private final TextField setPlayer;
    private final CheckBox randomizeGame;

    Settings() {
        Button menu, saveButton;
        Text label, labelPlayer;
        VBox vBoxSettings;
        ImageView background;
        RotateTransition rotateTransition;
        Config.getSounds().get(Config.SOUND_START).play(1.0);

        background = new ImageView();
        background.setImage(Config.getImages().get(Config.SELECTED_DOMINOES5));

        label = new Text("DominoXj settings");
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

        saveButton = new Button("Save and return");
        saveButton.setTooltip(new Tooltip("Save and return to DominoXj start menu"));
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveConfig();
                mainFrame.changeState(MainFrame.MENU);
            }
        });
        labelPlayer = new Text("Default player name: " + Config.getPlayerName());
        labelPlayer.setFont(Font.font(Font.getDefault().getFamily(), 14));
        randomizeGame = new CheckBox();
        randomizeGame.setText("Randomize game");
        randomizeGame.setTooltip(new Tooltip("Check for random sorting"));
        if (Config.isRandomizeGame()) {
            randomizeGame.setSelected(true);
        } else {
            randomizeGame.setSelected(false);
        }
        

        setPlayer = new TextField(Config.getPlayerName());
//        setPlayer.setFont(Font.font(Font.getDefault().getFamily(), 14));
        setPlayer.setTooltip(new Tooltip("Default player name"));
        vBoxSettings = new VBox();
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.TOP_CENTER);
        vBoxSettings.setLayoutY(Config.DOMINOES_SIZE * 2);
        vBoxSettings.setLayoutX(
                (Config.SCREEN_WIDTH
                - (int) label.getLayoutBounds().getWidth()) / 2
        );
        vBoxSettings.getChildren().addAll(
                label,
                menu,
                labelPlayer,
                setPlayer,
                randomizeGame,
                saveButton
        );
        getChildren().addAll(background, vBoxSettings);
        background.setX(Config.SCREEN_WIDTH - Config.DOMINOES_SIZE * 5);
        background.setY(Config.SCREEN_HEIGHT - Config.DOMINOES_SIZE * 5);
        background.setScaleX(2);
        background.setScaleY(2);
        rotateTransition = create()
                .node(background)
                .duration(Duration.seconds(1))
                .fromAngle(0)
                .toAngle(725)
                .cycleCount(1)
                .autoReverse(true)
                .build();
        rotateTransition.play();
    }

    public void start() {
    }

    public void saveConfig() {
        Config.setPlayerName(setPlayer.getText());
        Config.setRandomizeGame(randomizeGame.isSelected());
    }

    public void stop() {
    }

}
