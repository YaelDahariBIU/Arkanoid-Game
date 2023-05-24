// 325166510 Yael Dahari
package GameControl;
/**
 * The Counter allows us to increase, decrease and get the value.
 */
public class Counter {
    static final int INITIALIZE = 0;
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.count = INITIALIZE;
    }
    private void setCount(int num) {
        this.count = num;
    }

    /**
     * The method adds a given number to current count.
     *
     * @param number (int) - the number
     */
    public void increase(int number) {
        this.setCount(this.count + number);
    }

    /**
     * The method subtracts a given number from current count.
     *
     * @param number (int) - the number
     */
    public void decrease(int number) {
        this.setCount(this.count - number);
    }

    /**
     * The method returns the current count.
     *
     * @return (int) - the value
     */
    public int getValue() {
        return this.count;
    }
}
