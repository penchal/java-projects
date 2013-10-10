public class Foo {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Thread currentThread = Thread.currentThread();

    ThreadData dataOfMain = new ThreadData(currentThread);
    dataOfMain.print();
  }

}
