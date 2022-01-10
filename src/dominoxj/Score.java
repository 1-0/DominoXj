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

/**
 *
 * @author a10
 */
public final class Score {
    private long bestTimeDelta;
    private long bestTimeStart;
    private String bestPlayer;
    private final Main outer;

    Score(final Main outer) {
        this.outer = outer;
        checkHightScore(0, 0, "");
    }

    public void setHightScore(long timeDelta, long timeStart, String player) {
        checkHightScore(timeDelta, timeStart, player);
    }

    public void checkHightScore(long timeDelta, long timeStart, String player) {
        if ((Main.getHightScore() == null) || (bestTimeDelta == 0) || (bestTimeDelta > timeDelta)) {
            bestTimeDelta = timeDelta;
            bestTimeStart = timeStart;
            bestPlayer = player;
            Main.setHightScore(this);
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
        String res = Main.getHightScore().getBestTimeDelta() + "(ms) by " + Main.getHightScore().getBestPlayer();
        //            String res = "\nbestTimeDistance = "
        //                    + hightScore.getBestTimeDelta()
        //                    + "(ms)\nbestTimeStart = "
        //                    + (DateFormat.getDateTimeInstance().format(
        //                            new Date(hightScore.getBestTimeStart())))
        //                    + "\nbestPlayer = "
        //                    + hightScore.getBestPlayer();
        return res;
    }
    
}
