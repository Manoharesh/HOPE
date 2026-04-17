//It's method overloading (compile time polymorphism)
// implicit conversion can  takes place (small to large)

class Calculator{
    int add(int n1,int n2){
        return n1+n2;
    }
    int add(int a,int b,int c){
        return a+b+c;
    }
    double add(double a,double b){
        return a+b;
    }
}


public class poly_overload {
    public static void main(String[] args) {
        Calculator c=new Calculator();
        System.out.println(c.add(30,40.9867));
    }
}
