class SampleThread extends Thread{
    @Override
    public void run(){
        System.out.println("Thread running using thread class");
    }
}
class Samplethread2 implements Runnable{
    @Override
    public void run(){
        try{
            System.out.println("Thread2 is running using Runnable interface");
            Thread.sleep(4000);
            System.out.println("After 4 seconds");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
public class thread{
    public static void main(String[] args){
        SampleThread t=new SampleThread();
        t.start();
        //Thread t2=new Thread(new Samplethread2());
        Samplethread2 t3=new Samplethread2();
        Thread t4=new Thread(t3);
        //t2.start();
        t4.start();
        
    }
}