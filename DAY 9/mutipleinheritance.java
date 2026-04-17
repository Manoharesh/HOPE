interface Remote{
    void change();
}
interface WifiControl{
    void connect();
}
class TV implements Remote,WifiControl{
    @Override
    public void change(){
        System.out.println("Change channel");

    }
    @Override
    public void connect(){
        System.out.println("wifi connected");
    }
}

public class mutipleinheritance {
    public static void main(String[] args) {
        Remote r=new TV();
        r.change();
        WifiControl w=new TV();
        w.connect();
    }
}
