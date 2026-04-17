class Battery{
    public void powerSupply(){
        System.out.println("still have 50% power");
    }
}
class Remote{
    Battery mini; //has a relationship
    Remote(){
        mini=new Battery(); // composite relationship(tightly coupled as constructor is used)
    } 
    public void Changechannel(){
        mini.powerSupply();
        System.out.println("Channel changed");
    }
    //mini.powerSupply();  it cannot be used;
    public void changeVolume(){
        System.out.println("volume increased");
    }
}
class TV{
    Remote tvremote;       //aggregation relationship  as remote can exist without tv object
    TV(Remote tvremote){
        this.tvremote=tvremote;
    }
    public void increasevolume(){
        tvremote.changeVolume();
    }
}

public class relationship {
    public static void main(String[] args) {
        Remote r=new Remote();
        r.Changechannel();
        TV sony=new TV(r);
        sony.increasevolume();
    }
}
