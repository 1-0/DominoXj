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

/**
 *
 * @author a10
 */
public final class Score {

    private long timeDelta;
    private long timeStart;
    private String player;

    Score() {
//        this.prepareScore(0, 0, "");
    }

    Score(long timeDelta, long timeStart, String player) {
        prepareScore(timeDelta, timeStart, player);
    }

    public String getString() {
//        String res = Main.getHightScore().getBestTimeDelta() + "(ms) by " + Main.getHightScore().getBestPlayer();
        String res = "bestTimeDistance = "
                + this.getTimeDelta()
                + "(ms)bestTimeStart = "
                + (DateFormat.getDateTimeInstance().format(
                        new Date(this.getTimeStart())))
                + "bestPlayer = "
                + this.getPlayer();
        return res;
    }

    /**
     * @return the timeDelta
     */
    public long getTimeDelta() {
        return timeDelta;
    }

    /**
     * @return the timeStart
     */
    public long getTimeStart() {
        return timeStart;
    }

    /**
     * @return the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * @param timeDelta
     * @param timeStart
     * @param player to set
     */
    public void prepareScore(long timeDelta, long timeStart, String player) {
        this.timeDelta = timeDelta;
        this.timeStart = timeStart;
        this.player = player;
    }
}
