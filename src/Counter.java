/**
 * class for counting things.
 */
public class Counter {
    private int counter;

    /**
     * constructor - initialize counter object with 0 value.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * constructor - initialize counter object with received value.
     *
     * @param num value to initialize this counter.
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * add number to current count.
     *
     * @param number number to add tp this counter.
     */
    void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number number to subtract from this counter.
     */
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     *
     * @return current count.
     */
    int getValue() {
        return this.counter;
    }
}