import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestJVM extends Thread implements Serializable {
    boolean a = true;
    int i=0;
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        HttpURLConnection connection = (HttpURLConnection)new URL(" http://219.232.200.2/csibiz/csirp/handlerAuthResult").openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
        BufferedReader in = new BufferedReader( new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String currentLine;
        while ((currentLine = in.readLine()) != null) response.append(currentLine);
        in.close();
        response.toString();

    }
    void test(){
        i++;
        test();
    }

    private static ExecutorService es = Executors.newCachedThreadPool();

    private void test10M() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {

            lock.lock();
            System.out.println("@2" + lock);
            try {
                Thread.sleep(1000L);
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("wait over!");
        });
        thread.start();
        lock.lock();
        System.out.println(lock);
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public void run() {
        System.out.println("test serializable");
    }
}
