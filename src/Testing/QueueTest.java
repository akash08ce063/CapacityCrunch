/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

public class QueueTest {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        eventQueue.push(new EventQueue());
 eventQueue.push(new EventQueue());

        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                System.out.println("Run");
            }
        });
    }

    private static class MyEventQueue extends EventQueue {
        public void postEvent(AWTEvent theEvent) {
            System.out.println("Event Posted");
            super.postEvent(theEvent);
        }
    }
}