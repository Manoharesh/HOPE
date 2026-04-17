abstract class SmartDevice{
    abstract void turnon();
    abstract void turnoff();
    void deviceInfo(){
        System.out.println("It is a device");
    }
    abstract void setlevel();
    
}
class Light extends SmartDevice{
    @Override
    void turnon(){
        System.out.println("Light on");
    }
    @Override
    void turnoff(){
        System.out.println("Light off");
    }
    @Override
    void setlevel(){
        System.out.print("Light level");
    }
}
class Fan extends SmartDevice{
    @Override
    void turnon(){
        System.out.println("Fan on");

    }
    @Override
    void turnoff(){
        System.out.println("Fan off");
    }
    @Override
    void setlevel(){
        System.out.println("Fan level");
    }
}

public class abstract1 {
    public static void main(String[] args) {
        SmartDevice device=new Light();
        SmartDevice f1=new Fan();
        device.turnon();
        device.deviceInfo();
        f1.turnoff();
        f1.setlevel();
        
    }
}


