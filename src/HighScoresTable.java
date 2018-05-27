import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * HighScoresTable.
 * @author Elor lichtziger
 */
class HighScoresTable implements Serializable {
    private List<ScoreInfo> scoreInfos;
    private int scoresAmount = 5;
    /**
     * HighScoresTable.
     *
     * constructor.
     *
     * @param size  the size of the table.
     */
    public HighScoresTable(int size) {
        this.scoresAmount = size;
        this.scoreInfos = new ArrayList<ScoreInfo>(this.scoresAmount);
    }
    /**
     * HighScoresTable.
     *
     * Add a high-score..
     *
     * @param score  ScoreInfo object.
     */
    public void add(ScoreInfo score) {
        //check if the scoreInfos is empty.
        if (this.scoreInfos.isEmpty()) {
            this.scoreInfos.add(score);
        } else {
            int tableSize = this.scoreInfos.size();
            for (int i = 0; i < tableSize; i++) {
                //check if this new record needs to enter to the table.
                if (this.scoreInfos.get(i).getScore() < score.getScore()) {
                    this.scoreInfos.add(i, score);
                    break;
                } else if (this.scoreInfos.size() < this.scoresAmount && (i == (this.scoreInfos.size() - 1))) {
                    this.scoreInfos.add(score);
                }
            }
            //if added more than 5 records, remove the sixth
            if (this.scoreInfos.size() >= 6) {
                this.scoreInfos.remove(5);
            }
        }
    }
    /**
     * size.
     *
     * @return the table size.
     */
    public int size() {
        return this.scoresAmount;
    }
    /**
     * getHighScores.
     *
     *
     * @return the current high scores. The list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreInfos;
    }
    /**
     * getRank.
     *
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score     the score.
     * @return the rank of the current score.
     */
    public int getRank(int score) {
        //check if the scoreInfos is empty.
        if (this.scoreInfos.isEmpty()) {
            return 0;
        } else {
            //loop that count how much scores in the table.
            for (int i = 0; i < this.scoreInfos.size(); i++) {
                if (this.scoreInfos.get(i).getScore() <= score) {
                    return i + 1;
                }
            }
        }
        return this.scoreInfos.size() + 1;
    }
    /**
     * getHighScores.
     *
     * Clears the table
     */
    public void clear() {
        this.scoreInfos.clear();
    }
    /**
     * load.
     *
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename      the file.
     *
     * @throws IOException if the load have been failed.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream input = null;
        try {
            //try to open the file.
            FileInputStream file = new FileInputStream(filename.getName());
            input = new ObjectInputStream(file);
            HighScoresTable loadTable = (HighScoresTable) input.readObject();
            //get the information from the file.
            this.scoreInfos = loadTable.scoreInfos;
            this.scoresAmount = loadTable.scoresAmount;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename.getName());
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }
    /**
     * save.
     *
     * Save table data to the specified file.
     *
     * @param filename      the file.
     *
     * @throws IOException  if the save have been failed.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream output = null;
        try {
            //try to get an output stream and save this information.
            FileOutputStream file = new FileOutputStream(filename.getName());
            output = new ObjectOutputStream(file);
            output.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }
    /**
     * HighScoresTable.
     *
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename  the file.
     *
     * @return the table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        try {
            if (!filename.exists()) {
                table.save(filename);
            }
            table.load(filename);
        } catch (IOException e) {
            System.err.println("Failed accessing file");
            return table;
        }
        return table;
    }
}