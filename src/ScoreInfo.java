import java.io.Serializable;

/**
 * ScoreInfo.
 * @author Elor lichtziger
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;
    /**
     * ScoreInfo.
     *
     * constructor.
     *
     * @param name      the name of the player.
     * @param score     the score of the player.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * ScoreInfo.
     *
     * @return the player's name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * ScoreInfo.
     *
     * @return the player's score.
     */
    public int getScore() {
        return this.score;
    }
}