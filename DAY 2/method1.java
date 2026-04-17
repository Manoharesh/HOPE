public class method1 {
    static int mul(int a,int b){
        return a*b;
    }
    static int square(int n){
        return mul(n,n);
    } 
    static void print(int n){
       System.out.println(square(n));
    }
    public static void main(String[] args) {
        int s=4;
        print(s);
        
    }
    
}
