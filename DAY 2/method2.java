public class method2 {
    static void change(int x){
        x=20;
        System.out.println("Inside method:"+x);
    }
    public static void main(String[] args) {
        int a=50;
        change(a);
        System.out.println("Outside method:"+a);
    }
}