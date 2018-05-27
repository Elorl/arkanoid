/**
 * Counter.
 * @author Elor lichtziger
 */
public class Counter {
    private int count;
    /**
     * Counter.
     *
     * constructor.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * increase.
     *
     * add number to current count.
     *
     * @param number    a number to add to the counter.
     */
    public void increase(int number) {
        this.count = this.count + number;
    }
    /**
     * decrease.
     *
     * add number to current count.
     *
     * @param number    a number to deduce from the counter.
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }
    /**
     * getValue.
     *
     * @return the current count number.
     */
    public int getValue() {
        return this.count;
    }
}