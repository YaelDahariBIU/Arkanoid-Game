package GameControl;

public class Counter {
    private int count;
    public Counter() {
        this.count = 0;
    }
    private void setCount(int num) {
        if (num < 0) {
            this.count = 0;
        }
        this.count = num;
    }
    // add number to current count.
    void increase(int number) {
        this.setCount(this.count + number);
    }
    // subtract number from current count.
    void decrease(int number) {
        this.setCount(this.count - number);
    }
    // get current count.
    int getValue() {
        return this.count;
    }
}
