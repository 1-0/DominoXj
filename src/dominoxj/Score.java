/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
