package cn.com.myproject.ClosureTest;

/**
 * 匿名内部类，多线程
 */
public class ClosureTest /*implements Runnable*/ {
    public static void main(String[] args) {
        final String str = "java";
        /*Thread thread=new Thread(new ClosureTest());
        thread.start();*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true){
                    System.out.println(str+" "+i++);
                }
            }
        }).start();
        //执行到此处，main方法结束
    }

/*    @Override
    public void run() {
        int i =0;
        while (true) {
            System.out.println(i++);
        }
    }*/
}
