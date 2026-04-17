class Game{
    String name;
    Game(String name){
        this.name=name;
    }
    void display(){
        System.out.println("Name:"+name);
    }
}
class Warrior extends Game{
    Warrior(String name){
        super(name);
    }
    void display(){
        System.out.println("Attack with sword");
    }
}
class Archer extends Game{
    Archer(String name){
        super(name);
    }
    void display(){
        System.out.println("Attack with arrow");
    }
}
class Mage extends Game{
    Mage(String name){
        super(name);
    }
    void display(){
        System.out.println("Attack with magic");
    }
}


public class mini_game {
    public static void main(String[] args) {
        Game[] g={new Warrior("ABC"),new Archer("XYZ"),new Mage("DCE")};
        for (Game a:g){
            a.display();

        }
    }
    
}
