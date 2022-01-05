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

//import java.util.logging.Logger;
import java.text.DateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import javafx.stage.Window;

/**
 * class for entering in <b>DominoXj</b> game
 *
 * @author a10
 */
public class Main extends Application {

//    private static Window mainWindow;
    private static MainFrame mainFrame;
    private static Score hightScore = null;
//    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

//    public static Window getMainWindow() {
//        return mainWindow;
//    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * @return the hightScore
     */
    public static Score getHightScore() {
        return hightScore;
    }

    @Override
    public void start(Stage stage) {
        hightScore = new Score();
//        mainWindow = stage.getOwner();
        Config.initialize();
        Group root = new Group();
        mainFrame = new MainFrame(root, this);
        stage.setTitle("DominoXj");
        stage.setResizable(false);
        stage.setWidth(Config.SCREEN_WIDTH + 2 * Config.WINDOW_BORDER);
        stage.setHeight(Config.SCREEN_HEIGHT + 2 * Config.WINDOW_BORDER + Config.TITLE_BAR_HEIGHT);
        Scene scene = new Scene(root);
        scene.setFill(Color.CHOCOLATE);
        stage.setScene(scene);
        mainFrame.changeState(MainFrame.MENU);
        stage.show();
    }

    public final class Score {

        private long bestTimeDelta;
        private long bestTimeStart;
        private String bestPlayer;

        Score() {
            checkHightScore(0, 0, "");
        }

        public void setHightScore(
                long timeDelta,
                long timeStart,
                String player
        ) {
            checkHightScore(timeDelta, timeStart, player);
        }

        public void checkHightScore(
                long timeDelta,
                long timeStart,
                String player
        ) {
            if ((getHightScore() == null)
                    || (bestTimeDelta == 0)
                    || (bestTimeDelta > timeDelta)) {
                bestTimeDelta = timeDelta;
                bestTimeStart = timeStart;
                bestPlayer = player;
                hightScore = this;
            }
        }

        /**
         * @return the bestTimeDelta
         */
        public long getBestTimeDelta() {
            return bestTimeDelta;
        }

        /**
         * @return the bestTimeStart
         */
        public long getBestTimeStart() {
            return bestTimeStart;
        }

        /**
         * @return the bestPlayer
         */
        public String getBestPlayer() {
            return bestPlayer;
        }

        public String getString() {
            String res = "\nbestTimeDistance = "
                    + hightScore.getBestTimeDelta()
                    + "(ms)\nbestTimeStart = "
                    + (DateFormat.getDateTimeInstance().format(
                            new Date(hightScore.getBestTimeStart())))
                    + "\nbestPlayer = "
                    + hightScore.getBestPlayer();
            return res;
        }
    }
}
