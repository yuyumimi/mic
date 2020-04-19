import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tickets {
    private int num = 500;
    Lock lock=new ReentrantLock();

    public void sale() throws InterruptedException {
        lock.lock();
        try {
            if (num > 0) {
                TimeUnit.MICROSECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "卖出" + (num--) + "票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}

class Test {


    public static void main(String[] args) throws InterruptedException {
        final Tickets ticket = new Tickets();
        System.out.println(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        ticket.sale();
                    }
                }
            }, "runnable").start();
        }


        System.out.println("还剩票" + ticket.getNum());
    }

    static void startThread(Runnable runnable, String name) {
        for (int i = 0; i < 1; i++) {
            new Thread(runnable, name + "\t" + i).start();
        }
    }
}
