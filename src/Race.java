import java.util.concurrent.CountDownLatch;

/**
 * @author Rishat Ibrahimov
 */
public class Race {

  public static void main(String[] args) throws InterruptedException {
    int nCars = 5;
    CountDownLatch countDownLatch = new CountDownLatch(nCars);
    CountDownLatch startCountDownLatch = new CountDownLatch(3);

    for (int i = 0; i < nCars; i++) {
      Car car = new Car(i + 1, countDownLatch, startCountDownLatch);
      car.start();
    }

    countDownLatch.await();

    System.out.println("Ready!");
    startCountDownLatch.countDown();
    Thread.sleep(1000);

    System.out.println("Steady!");
    startCountDownLatch.countDown();
    Thread.sleep(1000);

    System.out.println("Go!");
    startCountDownLatch.countDown();
  }

  private static class Car extends Thread {
    private final CountDownLatch startCountDownLatch;
    private int carNumber;
    private CountDownLatch countDownLatch;

    public Car(int carNumber, CountDownLatch countDownLatch, CountDownLatch startCountDownLatch) {
      this.carNumber = carNumber;
      this.countDownLatch = countDownLatch;
      this.startCountDownLatch = startCountDownLatch;
    }

    @Override
    public void run() {
      try {
        Thread.sleep((long) (Math.random() * 10_000));
        System.out.println("Car #" + carNumber + " is ready!");
        countDownLatch.countDown();

        startCountDownLatch.await();

        Thread.sleep((long) (Math.random() * 10_000));
        System.out.println("Car #" + carNumber + " finished!");

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
