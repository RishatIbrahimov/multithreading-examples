import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * @author Rishat Ibrahimov
 */
public class Parking {
  private static Semaphore semaphore = new Semaphore(4);

  public static void main(String[] args) {
    int nCars = 11;
    IntStream.range(0, nCars).mapToObj(Car::new).forEach(Car::start);
  }

  public static class Car extends Thread {
    private int carNum;

    public Car(int carNum) {
      this.carNum = carNum;
    }

    @Override
    public void run() {
      try {
        Thread.sleep((long) (10_000 * Math.random()));
        System.out.println("Car #" + carNum + " is ready");

        semaphore.acquire();
        System.out.println("Car #" + carNum + " parked");
        Thread.sleep((long) (10_000 * Math.random()));

        System.out.println("Car #" + carNum + " leaves parking");
        semaphore.release();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
