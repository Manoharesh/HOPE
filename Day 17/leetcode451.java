import java.util.*;
class leetcode451 {
    public String frequencySort(String s) {
        HashMap<Character,Integer>map=new HashMap<>();
        int n=s.length();
        for(int i=0;i<n;i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int[]sort=new int[map.size()];
        int idx=0;
        for(int i:map.values()){
           sort[idx++]=i;
        }
        Arrays.sort(sort);
        StringBuilder sb=new StringBuilder();
        for(int i=sort.length-1;i>=0;i--){
            int freq=sort[i];
            for(Map.Entry<Character,Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(sort[i])){
                    int j=freq;
                    while(j>0){
                        sb.append(entry.getKey());
                        j--;
                    }
                    map.put(entry.getKey(), -1);
                }
            }
        }
        String result=sb.toString();
        return result;
        
    }
}