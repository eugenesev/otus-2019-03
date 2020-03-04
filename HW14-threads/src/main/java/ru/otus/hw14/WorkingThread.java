package ru.otus.hw14;

public class WorkingThread extends Thread {

    private int currentCount = 1;
    private String name = "";
    private boolean busy;
    private WorkingThread other;
    private int increment = 1;


    public WorkingThread(String name) {
        this.name = name;
    }

    synchronized void waitOthers() throws InterruptedException {
        busy = true;
        while (busy) {
            wait();
        }
    }

    synchronized private void release() {
        busy = false;
        notifyAll();
    }

    @Override
    public void run() {
        System.out.println("started " + name);
        try {
            while (true) {
                waitOthers();
                System.out.println(name + " " + currentCount);
                currentCount += increment;
                other.release();
                if (currentCount > 9) {
                    increment = -1;
                }
                if (increment < 0) {
                    if (currentCount < 1) {
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Application started");
        WorkingThread thread1 = new WorkingThread("Thread 1");
        WorkingThread thread2 = new WorkingThread("Thread 2");
        thread1.other = thread2;
        thread2.other = thread1;
        thread1.start();
        thread2.start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.release();
    }

}
