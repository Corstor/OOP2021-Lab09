package it.unibo.oop.lab.reactivegui03;

import it.unibo.oop.lab.reactivegui02.ConcurrentGUI;

/**
 * Class to represent a concurrent GUI.
 */
public class AnotherConcurrentGUI extends ConcurrentGUI {

    /**
     * 
     */
    private static final long serialVersionUID = -8710276539980695794L;
    private static final int MS_TO_SLEEP = 10_000;

    /**
     * Build a concurrent GUI.
     */
    public AnotherConcurrentGUI() {
        super();
        final NewAgent agent1 = new NewAgent();
        new Thread(agent1).start();
    }

    private class NewAgent implements Runnable {
        public void run() {
                try {
                    Thread.sleep(MS_TO_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyStop(getAgent());
        }
    }
}
