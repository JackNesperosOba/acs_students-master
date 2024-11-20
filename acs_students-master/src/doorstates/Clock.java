package doorstates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

//Unique clock for every door that can be in unlocked_shortly state
// to trace the time defined to change the state to propped or locked
@SuppressWarnings("checkstyle:MissingJavadocType")
public class Clock extends Observable {
  private LocalDateTime date;
  private Timer timer;
  private int period; // seconds
  private static Clock instance = null;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  private Clock(int period) {
    this.period = period;
    timer = new Timer();
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static synchronized Clock getInstance(int period) {
    if (instance == null) {
      instance = new Clock(period);
    }
    return instance;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      public void run() { // instance of anonymous class
        date = LocalDateTime.now();
        System.out.println("run() executed at " + date);
        setChanged();
        notifyObservers(date);
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void stop() {
    timer.cancel();
  }

  public LocalDateTime getDate() {
    return date;
  }
}