/**
 * @author Rishat Ibrahimov
 */
public class ResultExtraction {

  public static void main(String[] args) throws InterruptedException {
    FiboCalculator fibo = new FiboCalculator(50);
    fibo.setDaemon(true);
    fibo.start();
    System.out.println(fibo.result);
  }

  private static class FiboCalculator extends Thread {
    private int n;
    public int result = 0;

    public FiboCalculator(int n) {
      this.n = n;
    }


    private int calcFibo(int n) {
      if (n < 2) {
        return n;
      }
      int r = calcFibo(n - 1) + calcFibo(n - 2);
      System.out.println(r);
      return r;
    }


    @Override
    public void run() {
      result = calcFibo(n);
    }
  }
}
