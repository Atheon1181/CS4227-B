package inventory.controls;

public class RandomNumberGenerator {
    private int min;
    private int max;
    private double dmin;
    private double dmax;

    public RandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
        if (min >= max)
            min = max - 1;
        else if (max <= min)
            max = min + 1;

    }

    public RandomNumberGenerator(double dmin, double dmax) {
        this.dmin = dmin;
        this.dmax = dmax;
        if (min >= max)
            min = max - 1;
        else if (max <= min)
            max = min + 1;
    }

    public Object getRandomNumber(char type) {
        switch (type) {
            case 'i':
                return (int) (Math.random() * (max - min) + 1);
            default:
                return (Math.random() * (max - min) + 1);
        }

    }


    public RandomNumberGenerator() {

    }
}
