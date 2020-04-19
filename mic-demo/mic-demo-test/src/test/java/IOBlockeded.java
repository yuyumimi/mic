import java.io.IOException;
import java.io.InputStream;

/**
 * @author hetiantian
 * 模拟IO阻塞,不可响应中断
 */
public class IOBlockeded implements Runnable {
    private InputStream in;

    public IOBlockeded(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read():");
            in.read();
        }  catch (IOException e) {
            //如果当前被中断了的话
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
           } else {
                throw new RuntimeException();
            }
        }
    }
}