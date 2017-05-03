/**
 * @author Rishat Ibrahimov
 */
public class PrinterIncreaser {
  private static int value = 0;

  public static void main(String[] args) {
    Thread printer = new Thread(new Printer());
    printer.setName("Printer");
    Thread increaser = new Thread(new Increaser());
    increaser.setName("Increaser");

    printer.start();
    increaser.start();
  }


  private static class Printer implements Runnable {

    @Override
    public void run() {

    }
  }


  private static class Increaser implements Runnable {

    @Override
    public void run() {

    }
  }

}
