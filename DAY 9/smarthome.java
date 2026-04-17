//smart home automation system
// if motion is detected-light on
//if temp>30 c-turn on ac
//at 7 pm -light on
//support multiple types of devices
//easy addition of new devices & rules in future
abstract class SmartDevices{
    abstract void turnon();
    abstract void turnoff();
}
class Light extends SmartDevices{

    @Override
    void turnon(){
        System.out.println("Light on");
    }
    @Override
    void turnoff(){
        System.out.println("Light off");
    }
}
class AC extends SmartDevices{
    @Override
    void turnon(){
        System.out.println("AC on");
    }
    @Override
    void turnoff(){
        System.out.println("AC off");
    }
}
interface Trigger{
    boolean isTriggered();

}
class MotionTriggered implements Trigger{
    public boolean isTriggered(){
        System.out.println("Motion triggered");
        return true;
    }  
}
class TempTriggered implements Trigger{
    public boolean isTriggered(){
        System.out.println("Temperature triggered");
        return true;
    }

}
class TimeTriggered implements Trigger{
    public boolean isTriggered(){
        System.out.println("Time triggered");
        return true;
    }
}
interface Action{
    void execute();
}
class TurnOnAction implements Action{
    SmartDevices device;
    TurnOnAction(SmartDevices device){
        this.device=device;
    }
    @Override
    public void execute(){
        device.turnon();
    }
}
class TurnOffAction implements Action{
    SmartDevices device;
    TurnOffAction(SmartDevices device){
        this.device=device;
    }
    @Override
    public void execute(){
        device.turnoff();
    }
}
class Rule{ // to link trigger & action
    Trigger trigger;
    Action action;
    Rule(Trigger trigger ,Action action){
        this.trigger=trigger;
        this.action=action;
    }
    
    void result(){
        if (trigger.isTriggered()){
            action.execute();
        }
        else{
            System.out.println("Trigger condition not meet");
        }

    }


}

public class smarthome {
    public static void main(String[] args) {
        SmartDevices d1=new Light();
        SmartDevices d2=new AC();
        Trigger t1=new MotionTriggered();
        Trigger t2=new TempTriggered();
        Trigger t3=new TimeTriggered();
        Action a1=new TurnOnAction(d1);
        Action a2=new TurnOffAction(d2);
        Rule r1=new Rule(t1,a1);
        Rule r2=new Rule(t2,a2);
        Rule r3=new Rule(t3,a1);
        r1.result();
        r2.result();
        r3.result();



    }
}
