class Bank{
    int balance=1000;
    synchronized void withdraw(int amount){
        if(amount<=balance){
            System.out.println("Amount withdraw successful");
            balance-=amount;
            System.out.println("remaining balance:"+balance);

        }
        else {
            System.out.println("insufficient balance");
        }
    }
}
class Bankthread extends Thread{
    Bank bank;
    Bankthread(Bank bank){
        this.bank=bank;
    }
    public void run(){
        bank.withdraw(800);
    }
}
public class Sync{
    public static void main(String[] args){
        Bank b=new Bank();
        Bankthread b1=new Bankthread(b);
        Bankthread b2=new Bankthread(b);
        b1.start();
        b2.start();

    } 
}