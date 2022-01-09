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

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * class for creaing custom options for <b>DominoXj</b> game
 *
 * @author a10
 */
public class Config extends Parent {

    public static final String GAME_VERSION = "0.3.7";

    public static final String IMAGE_DIR = "images/desktop/";

    public static final int WINDOW_BORDER = 3; // on desktop platform
    public static final int TITLE_BAR_HEIGHT = 19; // on desktop platform
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 720;

    public static final int DOMINOES_SIZE = 42;
    public static final int DOMINOES_BORDER = 2;
    public static final int PEDDING_HEIGHT = (SCREEN_HEIGHT - DOMINOES_SIZE * 14) / 2;
    public static final int PEDDING_WIDTH = (SCREEN_WIDTH - (DOMINOES_SIZE + DOMINOES_BORDER) * 7) / 2;

    public static final int IMAGE_BACKGROUND = 0;
    public static final int IMAGE_TABLE = 1;
    public static final int IMAGE_LOGO = 2;
    public static final int IMAGE_GAMEOVER = 3;

    public static final int IMAGE_DOMINOES0 = 4;
    public static final int IMAGE_DOMINOES1 = 5;
    public static final int IMAGE_DOMINOES2 = 6;
    public static final int IMAGE_DOMINOES3 = 7;
    public static final int IMAGE_DOMINOES4 = 8;
    public static final int IMAGE_DOMINOES5 = 9;
    public static final int IMAGE_DOMINOES6 = 10;
    public static final int IMAGE_DOMINOES_REVERSE = 11;

    public static final int SELECTED_DOMINOES0 = 12;
    public static final int SELECTED_DOMINOES1 = 13;
    public static final int SELECTED_DOMINOES2 = 14;
    public static final int SELECTED_DOMINOES3 = 15;
    public static final int SELECTED_DOMINOES4 = 16;
    public static final int SELECTED_DOMINOES5 = 17;
    public static final int SELECTED_DOMINOES6 = 18;

    public static final int SOUND_PLAY = 0;
    public static final int SOUND_REMOVE = 1;
    public static final int SOUND_SELECT = 2;
    public static final int SOUND_START = 3;
    public static final int SOUND_WIN = 4;

    private static boolean randomizeGame = true;
    private static String playerName = "X";
    static Score[] scores = new Score[10];

    private static final String[] SOUND_NAMES = new String[]{
        "sounds/play.wav",
        "sounds/remove.wav",
        "sounds/select.wav",
        "sounds/start.wav",
        "sounds/win.wav",};

    private static final String[] IMAGES_NAMES = new String[]{
        "background.png",
        "table.png",
        "logo.png",
        "gameover.png",
        "dominoes/dominoes0.png",
        "dominoes/dominoes1.png",
        "dominoes/dominoes2.png",
        "dominoes/dominoes3.png",
        "dominoes/dominoes4.png",
        "dominoes/dominoes5.png",
        "dominoes/dominoes6.png",
        "dominoes/reverse.png",
        "selecteddominoes/dominoes0.png",
        "selecteddominoes/dominoes1.png",
        "selecteddominoes/dominoes2.png",
        "selecteddominoes/dominoes3.png",
        "selecteddominoes/dominoes4.png",
        "selecteddominoes/dominoes5.png",
        "selecteddominoes/dominoes6.png",};

    private static final ObservableList<Image> images
            = javafx.collections.FXCollections.<Image>observableArrayList();

    private static final ObservableList<AudioClip> sounds
            = javafx.collections.FXCollections.<AudioClip>observableArrayList();

    public static Image getImage(int imgId) {
        return images.get(imgId);
    }

    public static AudioClip getSound(int sndId) {
        return sounds.get(sndId);
    }

    public static void initialize() {
        for (String imageName : IMAGES_NAMES) {
            Image image = new Image(
                    Config.class.getResourceAsStream(IMAGE_DIR + imageName)
            );
            if (image.isError()) {
                System.out.println("Resource " + imageName + " not found");
            }
            images.add(image);
        }
        for (String soundName : SOUND_NAMES) {
            AudioClip sound = new AudioClip(
                    Config.class.getResource(soundName).toString());
            sounds.add(sound);
        }
    }

    /**
     * @return the playerName
     */
    public static String getPlayerName() {
        return playerName;
    }

    /**
     * @param aPlayerName the playerName to set
     */
    public static void setPlayerName(String aPlayerName) {
        playerName = aPlayerName;
    }

    /**
     * @return the randomizeGame
     */
    public static boolean isRandomizeGame() {
        return randomizeGame;
    }

    /**
     * @param aRandomizeGame the randomizeGame to set
     */
    public static void setRandomizeGame(boolean aRandomizeGame) {
        randomizeGame = aRandomizeGame;
    }

    Config() {
    }
}
