class User{
    String name;
    String phone;
    String location;
    User(String name,String phone,String location){
        this.name=name;
        this.phone=phone;
        this.location=location;
    }
    void displayInfo(){
        System.out.println("Name:"+name);
        System.out.println("Phone:"+phone);
        System.out.println("Location:"+location);
    }
}
class RideUser extends User{
    String vehicle;
    RideUser(String name,String phone,String location,String vehicle){
        super(name, phone, location);
        this.vehicle=vehicle;
    }
    void RideDisplay(){
        displayInfo();
        System.out.println("Vehicle:"+vehicle);
         
    }
}
class FoodUser extends User{
    String Fooditem;
    FoodUser(String name,String phone,String location,String Fooditem){
        super(name, phone, location);
        this.Fooditem=Fooditem;

    }
    void FoodDisplay(){
        displayInfo();
        System.out.println("Food:"+Fooditem);

    }
}
class ParcelUser extends User{
    double Parcelweight;
    ParcelUser(String name,String phone,String location,double Parcelweight){
        super(name, phone, location);
        this.Parcelweight=Parcelweight;
    }
    void ParcelDisplay(){
        displayInfo();
        System.out.println("Parcelweight:"+Parcelweight);
    }
}
public class task1 {
    public static void main(String[] args) {
        RideUser a=new RideUser("ABC","123","XXX","Bike");
        a.RideDisplay();
        FoodUser b=new FoodUser("DEF", "456", "YYY", "Tea");
        b.FoodDisplay();
        ParcelUser c=new ParcelUser("GHI", "789", "ZZZ", 56.987);
        c.ParcelDisplay();
        
    }
}
