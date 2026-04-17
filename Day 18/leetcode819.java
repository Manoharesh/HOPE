import java.util.*;
class leetcode819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph=paragraph.toLowerCase();
        paragraph=paragraph.replaceAll("[^a-z]"," ");
        String[] words=paragraph.split("\\s+");
        Set<String> set=new HashSet<>(Arrays.asList(banned));
        int max=0;
        String result="";
        Map<String,Integer> freq=new HashMap<>();
        for(String s:words){
            if(!set.contains(s) && s.length()>0){
                freq.put(s,freq.getOrDefault(s,0)+1);
                if(freq.get(s)>max){
                    max=freq.get(s);
                    result=s;
                }
            }
        }
        return result;
    }
}