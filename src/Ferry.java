import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author Rishat Ibrahimov
 */
public class Ferry {

  private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new FerryBoat());

  public static void main(String[] args) {
    int nCars = 23;
    IntStream.range(0, nCars).mapToObj(Car::new).forEach(Car::start);
  }

  public static class FerryBoat implements Runnable {

    @Override
    public void run() {
      try {

        System.out.println("Let's go!");

        Thread.sleep((long) (Math.random() * 1_000));

        System.out.println("Cars was transfered!");

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static class Car extends Thread {
    private int carNum;

    public Car(int carNum) {
      this.carNum = carNum;
    }

    @Override
    public void run() {
      try {
        Thread.sleep((long) (Math.random() * 20_000));
        System.out.println("Car #" + carNum + " is ready!");
        cyclicBarrier.await();
        System.out.println("Car #" + carNum + ": WOW!");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
