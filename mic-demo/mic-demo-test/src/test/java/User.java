import java.util.ArrayList;
import java.util.List;

public class User {




    public static void main(String[] args) throws InterruptedException {

        List list=new ArrayList(10);
        list.add("a");
        for (Object o : list) {
            System.out.println(o);
        }
    }
}