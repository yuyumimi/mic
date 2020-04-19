import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
* 集合类不安全问题:原因是为了保证并发性，add操作没有加锁
*  java.util.ConcurrentModificationException
* ArrayList
* */
public class ContainerNotSafeDemo {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        for(int i=1;i<=20;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
}
}
 
