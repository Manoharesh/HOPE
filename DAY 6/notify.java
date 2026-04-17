class Notification{
    String name;
    String msg;
    Notification(String name,String msg){
        this.name=name;
        this.msg=msg;

    }
    void send(){
        System.out.println("Sending notification from parent class ");
    }
}
class EmailNotification extends Notification{
    EmailNotification(String name,String msg){
        super(name,msg);
    }
    void send(){
        System.out.println("Sending email to "+name);
        System.out.println("Message:"+msg);
    }
    void changeReceiverEmail(){

    }
}
class SMSNotification extends Notification{
    SMSNotification(String name,String msg){
        super(name,msg);

    }
    void send(){
        System.out.println("Sending SMS notification to "+name);
        System.out.println("Message "+msg);
    }
}

public class notify {
    public static void main(String[] args) {
        Notification n;
        n=new EmailNotification("Test","Order confirmed");
        n.send();
        n=new SMSNotification("Test2","Order cancelled");
        n.send();

    }
    
}
