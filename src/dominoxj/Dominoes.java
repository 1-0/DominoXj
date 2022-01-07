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

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

/**
 * class for holding dominoeses for <b>DominoXj</b> game
 *
 * @author a10
 */
public final class Dominoes extends Parent {

    public static final int STATE_REVERSED = 0;
    public static final int STATE_OPENED = 1;
    public static final int STATE_SELECTED = 2;
    public static final int STATE_REMOVED = 3;

    private static final java.util.Random RANDOM = new java.util.Random();

    private int state;
    private final Table table;
    private ImageView imageView1 = null, imageView2 = null;
    private int posX, posY, value1, value2, value;

    Dominoes(int X, int Y, int v1, int v2, int s, Table t) {
        table = t;
        setValue(v1, v2);
        setState(s);
        setPositions(X, Y);
        table.getGroup().getChildren().addAll(imageView1, imageView2);
    }

    public void setState(int s) {
        Image image1 = null, image2 = null;
        state = s;
        switch (state) {
            case STATE_REMOVED:
                setRemoved();
                return;
            case STATE_OPENED:
                Config.getSounds().get(Config.SOUND_SELECT).play(0.1);
                image1 = Config.getImages().get(
                        Config.IMAGE_DOMINOES0 + value1);
                image2 = Config.getImages().get(
                        Config.IMAGE_DOMINOES0 + value2);

                if (imageView1 == null) {
                    imageView1 = new ImageView();
                    imageView2 = new ImageView();
                }
                setEvents();
                imageView1.setCursor(Cursor.HAND);
                imageView2.setCursor(Cursor.HAND);
                imageView1.setCursor(Cursor.HAND);
                imageView2.setCursor(Cursor.HAND);
                break;
            case STATE_SELECTED:
                Config.getSounds().get(Config.SOUND_SELECT).play(0.1);
                image1 = Config.getImages().get(
                        Config.SELECTED_DOMINOES0 + value1);
                image2 = Config.getImages().get(
                        Config.SELECTED_DOMINOES0 + value2);
                break;
            case STATE_REVERSED:
                image1 = Config.getImages().get(Config.IMAGE_DOMINOES_REVERSE);
                image2 = Config.getImages().get(Config.IMAGE_DOMINOES_REVERSE);
                if (imageView1 == null) {
                    imageView1 = new ImageView();
                    imageView2 = new ImageView();
                }
                break;
        }
        imageView1.setImage(image1);
        imageView2.setImage(image2);
    }

    public int getPositionX() {
        return (posX);
    }

    public int getPositionY() {
        return (posY);
    }

    private void setRemoved() {
        int x = posX, y = posY;
        table.addScore(value);
        imageView1.setCursor(Cursor.TEXT);
        imageView2.setCursor(Cursor.TEXT);
        if (y != 6) {
            checkDown(x, y);
        }
        if (y != 0) {
            checkUp(x, y);
        }
        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView1 = new ImageView();
        imageView2 = new ImageView();
    }

    private void checkDown(int x, int y) {
        Dominoes b;
        b = table.getDominoes(x, y + 1);
        if ((b.getState() == STATE_REVERSED)
                && ((x == 0)
                || (table.getDominoes(x - 1, y).getState() == STATE_REMOVED))) {
            b.setState(STATE_OPENED);
        }
        b = table.getDominoes(x + 1, y + 1);
        if (b.getState() == STATE_REVERSED) {
            if ((x == y) || (table.getDominoes(x + 1, y).getState() == STATE_REMOVED)) {
                b.setState(STATE_OPENED);
            }
        }
    }

    private void checkUp(int x, int y) {
        if ((x > 0)
                && (table.getDominoes(x - 1, y).getState() == STATE_REMOVED)
                && (table.getDominoes(x - 1, y - 1).getState() == STATE_REVERSED)) {
            table.getDominoes(x - 1, y - 1).setState(STATE_OPENED);
        }
        if ((x < y)
                && ((table.getDominoes(x + 1, y).getState() == STATE_REMOVED)
                && (table.getDominoes(x, y - 1).getState() == STATE_REVERSED))) {
            table.getDominoes(x, y - 1).setState(STATE_OPENED);
        }
    }

    public void setPositions(int X, int Y) {
        posX = X;
        posY = Y;
        int xx = (Config.PEDDING_WIDTH
                + posX * (Config.DOMINOES_SIZE + Config.DOMINOES_BORDER)
                + (6 - posY) * Config.DOMINOES_SIZE / 2);
        int yy = (Config.PEDDING_HEIGHT + posY * (Config.DOMINOES_SIZE * 2));
        imageView1.setX(xx);
        imageView1.setY(yy);
        imageView2.setX(xx);
        imageView2.setY(yy + Config.DOMINOES_SIZE);
    }

    public void setValue(int v1, int v2) {
        if (RANDOM.nextBoolean()) {
            value1 = v1;
            value2 = v2;
        } else {
            value1 = v2;
            value2 = v1;
        }
        value = value1 + value2;
    }

    public void setEvents() {
        if (state == STATE_OPENED) {
            imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    handleClick(event);
                }
            });
            imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    handleClick(event);
                }
            });
            imageView1.setOnTouchReleased(new EventHandler<TouchEvent>() {
                @Override
                public void handle(TouchEvent event) {
                    handleTouch(event);
                }
            });
            imageView2.setOnTouchReleased(new EventHandler<TouchEvent>() {
                @Override
                public void handle(TouchEvent event) {
                    handleTouch(event);
                }
            });
        }
    }

    public void handleClick(MouseEvent event) {
        dominoesPush();
    }

    public void handleTouch(TouchEvent event) {
        dominoesPush();
    }

    private void dominoesPush() {
//        System.out.printf("++posX=%d, posY=%d++, state=%d++\n", posX, posY, state);
        if (state == STATE_SELECTED) {
            table.setSelected();
            setState(STATE_OPENED);
            return;
        }
        if (state == STATE_OPENED) {
            if (table.getSelected() == null) {
                setState(STATE_SELECTED);
                table.setSelected(this);
            } else {
                if ((value + table.getSelected().getValue()) == 12) {
                    table.getSelected().setState(STATE_REMOVED);
                    setState(STATE_REMOVED);
                    table.setSelected();
                }
            }
        }
    }

    public int getState() {
        return state;
    }

    public int getValue() {
        return value;
    }
}
