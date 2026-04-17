import java.util.*;
class leeetcode242 { //method3
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch:s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0) +1);
        }
        for(char ch:t.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0) -1);
            if(map.get(ch)<0){
                return false;
            }
        }
        return true;
    }
}
class leetcode242 { //method2
    public boolean isAnagram(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        if(ch1.length != ch2.length)
            return false;
        HashMap<Character,Integer> map1 = new HashMap<>();
        HashMap<Character,Integer> map2 = new HashMap<>();
        for(int i = 0;i<ch1.length;i++){
            map1.put(ch1[i], map1.getOrDefault(ch1[i],0) +1);
            map2.put(ch2[i],map2.getOrDefault(ch2[i],0)+1);
        }
        for(int i=0;i<ch1.length;i++){
            if(!map1.get(ch1[i]).equals(map2.get(ch1[i]))){
                return false;
            }
        }
        return true;
    }
}
class leetcode242 { //method1
    public boolean isAnagram(String s, String t) {
        char[] str1=s.toCharArray();
        char[] str2=t.toCharArray();
        if(s.length()!=t.length())
            return false;
        Arrays.sort(str1);
        Arrays.sort(str2);
        for(int ind=0;ind<s.length();ind++){
            if(str1[ind]!=str2[ind])
                return false;
        }
        return true;
    }
}