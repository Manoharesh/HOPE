

class LC_1781 {
    public int beautySum(String s) {
        int n=s.length();
        int total_beauty=0;
        for(int i=0;i<n;i++){
            int[] freq=new int[26];
            for(int j=i;j<n;j++){
                freq[s.charAt(j)-'a']++;
                int minfreq=Integer.MAX_VALUE;
                int maxfreq=0;
                for(int f:freq){
                    if(f>0){
                        minfreq=Math.min(minfreq,f);
                        maxfreq=Math.max(maxfreq,f);
                    }
                }
                total_beauty+=(maxfreq-minfreq);
            }
        }
        return total_beauty;
    }
}