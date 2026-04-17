interface Remote{
    void change();
}
class TV implements Remote{
    @Override
    public void change(){
        System.out.println("Change channel");

    }
}
public class interface1 {
    public static void main(String[] args) {
        Remote r =new TV();
        r.change();
    }
}


// method should always be public //