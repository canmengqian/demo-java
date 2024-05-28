package guava.timeout;

public class Man implements Human{
    @Override
    public void eat() {
        System.out.println ("eat.....");
    }

    @Override
    public void sleep() {
        try {
            Thread.sleep (3000);
        } catch (InterruptedException e) {
            throw new RuntimeException (e);
        }
    }
}
