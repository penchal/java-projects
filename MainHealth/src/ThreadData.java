public class ThreadData {

  String name;
  long   id;

  public ThreadData(Thread currentThread) {
    this.id = currentThread.getId();
    this.name = currentThread.getName();
  }

  public void print() {
    System.out.println("Name: " + name);
    System.out.println("ID: " + id);
  }
}
