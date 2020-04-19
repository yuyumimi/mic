public class P1 {

    private long b = 0;

    public void set1() {
        b = 0;
    }

    public void set2() {
        b = -1;

    }

    public void check() {
        if (b != 0 && b != -1) {
            System.out.println("error" + b);
        }
    }

    public static void main(String[] args) {
        final P1 p = new P1();
        final Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.set1();
                }
            }
        };
        t1.start();
        final Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.set2();
                }
            }
        };
        t2.start();
        final Thread t3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    p.check();
                }
            }
        };


        t3.start();
    }
}
