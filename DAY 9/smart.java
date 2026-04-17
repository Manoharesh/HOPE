//override (method over writing)
//runtime polymorphism

abstract class SmartDevice{
    abstract void turnon();
    abstract void turnoff();
    
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
}

public class smart {
    public static void main(String[] args) {
        SmartDevice device=new Light();
        device.turnon();
        device.turnoff();
        
    }
}
