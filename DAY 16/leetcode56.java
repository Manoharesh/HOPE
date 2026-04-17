import java.util.*;
class leetcode56 {
    public int[][] merge(int[][] intervals) {
        Comparator<int[]> byfirst =new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                return Integer.compare(a[0],b[0]);
            }
        };
        Arrays.sort(intervals,byfirst);
        List<int[]> list=new ArrayList<>();
        for(int i=0;i<intervals.length;i++){
            int[] curr=intervals[i];
                if(list.isEmpty()||list.get(list.size()-1)[1]<curr[0]){
                    list.add(curr);
                }else{
                    int[] merge=list.get(list.size()-1);
                    merge[1]=Math.max(merge[1],curr[1]);
                }
            
        }
        return list.toArray(new int[list.size()][]);
    }
}