import java.util.*;
public class KDistinctSubstring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();  
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            HashMap<Character, Integer> map = new HashMap<>();

            for (int j = i; j < str.length(); j++) {
                char ch = str.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.size() == k) {
                    count++;
                } else if (map.size() > k) {
                    break;
                }
            }
        }

        System.out.println(count);
    }
}