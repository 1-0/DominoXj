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

import java.text.DateFormat;
import java.util.Date;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;

/**
 * class for creaing game table frame for <b>DominoXj</b> game
 *
 * @author a10
 */
public class Table extends Parent {

//    private static final Logger LOG = Logger.getLogger(Menu.class.getName());
    private static final MainFrame mainFrame = Main.getMainFrame();
    private static final java.util.Random RANDOM = new java.util.Random();

    public static int randomInt(int maximum) {
        return (int) (RANDOM.nextDouble() * (maximum + 1));
    }

    private int score = 0;
    private Dominoes selectedDominoes = null;
    private final Dominoes[] dominoeses = new Dominoes[28];
    private final Group group;
    private final Text statistic;
    private final ProgressBar progressBar;
    private final Text timeText;
    private long startTime = 0;

    Table() {
        ImageView background;
        Button menu, restart;
        VBox vBoxTable;
        RotateTransition rotateTransition;

        Config.getSound(Config.SOUND_PLAY).play(1.0);
        background = new ImageView();
        background.setFocusTraversable(true);
        background.setImage(Config.getImage(Config.IMAGE_TABLE));

        menu = new Button("Menu");
        menu.setTooltip(new Tooltip("Return to DominoXj start menu"));
        menu.setFont(Font.font(Font.getDefault().getFamily(), 16));
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainFrame.changeState(MainFrame.MENU);
            }
        });

        restart = new Button("Restart");
        restart.setTooltip(new Tooltip("Restart DominoXj game"));
        restart.setFont(Font.font(Font.getDefault().getFamily(), 16));
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainFrame.changeState(MainFrame.TABLE);
            }
        });

        statistic = new Text("Progress:");
        statistic.setFill(Color.AQUA);
        statistic.setBoundsType(TextBoundsType.VISUAL);
        statistic.setFont(Font.font(Font.getDefault().getFamily(), 24));
        rotateTransition = create()
                .node(statistic)
                .duration(Duration.seconds(1))
                .fromAngle(0)
                .toAngle(360)
                .cycleCount(2)
                .autoReverse(true)
                .build();

        rotateTransition.play();
        progressBar = new ProgressBar(0);

        vBoxTable = new VBox();
        vBoxTable.setSpacing(10);
        vBoxTable.setAlignment(Pos.TOP_CENTER);
        vBoxTable.setLayoutX(10);
        vBoxTable.setLayoutY(10);
        vBoxTable.getChildren().addAll(
                statistic,
                progressBar,
                menu,
                restart
        );

        group = new Group();
        group.getChildren().addAll(background, vBoxTable);
        group.autosize();
        getChildren().add(group);
        prepareGame();

        timeText = new Text("Now: ");
        timeText.setX(Config.DOMINOES_SIZE / 2);
        timeText.setY(Config.SCREEN_HEIGHT - Config.DOMINOES_SIZE / 2);
        setTimeText();
        getChildren().add(timeText);

    }

    private void setTimeText() {
        String nowTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
        timeText.setText(nowTime);
    }

    public void start() {
    }

    public void stop() {
    }

    public void setSelected() {
        selectedDominoes = null;
    }

    public void setSelected(Dominoes dominoes) {
        selectedDominoes = dominoes;
    }

    public int posToIndex(int x, int y) {
        int i;
        Dominoes b;
        for (i = 0; i < 28; ++i) {
            b = dominoeses[i];
            if ((b.getPositionX() == x) && ((b.getPositionY() == y))) {
                return i;
            }
        }
        return i;
    }

    public Dominoes getSelected() {
        return (selectedDominoes);
    }

    public void addScore(int x) {
        score += x;
        double progress;
        progress = (score / 168.0);
        setTimeText();
        progressBar.setProgress(progress);
        progressBar.setTooltip(new Tooltip("Progress = " + ((int)(progress * 100)) + " %"));
        Config.getSound(Config.SOUND_REMOVE).play(0.9);
        if (score == 168) {
            gameEnd();
        }
    }

    public Dominoes getDominoes(int x, int y) {
        int i = posToIndex(x, y);
        return (dominoeses[i]);
    }

    public Group getGroup() {
        return (group);
    }

    private void gameEnd() {
        long endTime = (new Date()).getTime();
        long deltaTime = endTime - startTime;
        group.setVisible(false);
        MainFrame.SwingDialog dial = new MainFrame.SwingDialog();
        int res = dial.getResult(
                "Win DominoXj!",
                "Time: " + deltaTime,
                MainFrame.SwingDialog.TYPE_OK
        );
        group.setVisible(true);
        Config.getSound(Config.SOUND_WIN).play(1.0);
        Main.getHightScore().checkHightScore(
                deltaTime,
                startTime,
                Config.getPlayerName()
        );
        getChildren().removeAll(group);
        mainFrame.changeState(MainFrame.TABLE);
    }

    private void prepareGame() {
        Dominoes dominoes;
        int state;
        int[] xIndexes = new int[28],
                yIndexes = new int[28];
        boolean[] taked = new boolean[28];
        int i = 0;
        for (int x = 0; x < 7; ++x) {
            for (int y = 0; y < x + 1; ++y) {
                state = Dominoes.STATE_REVERSED;
                dominoes = new Dominoes(
                        6 - x,
                        6 - y,
                        x,
                        y,
                        state,
                        this
                );
                xIndexes[i] = x;
                yIndexes[i] = y;
                dominoeses[i] = dominoes;
                taked[i] = true;
                ++i;
            }
        };

        RotateTransition rotateTransition;
        rotateTransition = create()
                .node(group)
                .duration(Duration.seconds(1))
                .fromAngle(0)
                .toAngle(360)
                .cycleCount(1)
                .autoReverse(true)
                .build();

        rotateTransition.play();

        Boolean t;
        for (int n = 0; n < 28; ++n) {
            t = true;
            while (t) {
                if (Config.isRandomizeGame()) {
                    i = RANDOM.nextInt(28); // random sorting
                } else {
                    i = n; // without sorting
                }
                if (taked[i]) {
                    dominoeses[n].setValue(xIndexes[i], yIndexes[i]);
                    if (dominoeses[n].getPositionY() == 0 || dominoeses[n].getPositionY() == 6) {
                        dominoeses[n].setState(Dominoes.STATE_OPENED);
                    }
                    taked[i] = false;
                    t = false;
                }
            }
        }
        startTime = (new Date()).getTime();
    }
}
