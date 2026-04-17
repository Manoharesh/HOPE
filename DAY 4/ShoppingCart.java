
import java.util.*;
class Product{
    int id;
    double price;
    String name;
    int quantity;
    Product(int id, double price, String name, int quantity){
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
    double getTotalPrice(){
        return price*quantity;
    }
    void display(){
        System.out.println("Name: "+name);
        System.out.println("Price: "+price);
        System.out.println("Id: "+id);
        System.out.println("Quantity: "+quantity);
    }
}
public class ShoppingCart {
        public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int id1;
        double price1;
        String name1;
        int quan1;
        double grandtotal=0;
        Product[] cart=new Product[4];
        for(int i=1;i<3;i++){
            id1=sc.nextInt();
            sc.nextLine();
            name1=sc.nextLine();
            price1=sc.nextDouble();
            quan1=sc.nextInt();
            cart[i]=new Product(id1,price1,name1,quan1);
        }
        
        for(int i=1;i<3;i++){
            grandtotal+=cart[i].getTotalPrice();
            cart[i].display();
        }
        System.out.println(grandtotal);
        sc.close();
    }
}