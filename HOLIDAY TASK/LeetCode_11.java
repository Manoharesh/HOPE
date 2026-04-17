public class LeetCode_11 {
   class Solution {
    public int maxArea(int[] height) {
        int start=0;
        int end=height.length-1;
        int max=0;
        while(start<end){
            int width=end-start;
            if(height[start]<height[end]){
                int area=width*height[start];
                max=Math.max(max,area);
                start++;
            }
            else{
                int area=width*height[end];
                max=Math.max(max,area);
                end--;
            }
        
        }
        return max;
    }
} 
}
