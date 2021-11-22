package it.unibo.oop.lab.reactivegui03;

import it.unibo.oop.lab.reactivegui02.ConcurrentGUI;

public class AnotherConcurrentGUI extends ConcurrentGUI {

    /**
     * 
     */
    private static final long serialVersionUID = -8710276539980695794L;

    public AnotherConcurrentGUI() {
        super();
        final NewAgent agent1 = new NewAgent();
        new Thread(agent1).start();
    }
    
    private class NewAgent implements Runnable {
        public void run() {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyStop(agent);
        }
    }
}
