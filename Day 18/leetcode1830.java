class leetcode1830{
    public boolean checkIfPangram(String sentence) {
        int flag=0;
        char[] charr=sentence.toCharArray();
        for(char ch:charr){
            flag=flag|(1<<(ch-'a'));
        }
        return flag==(1<<26)-1;
        //
    }
}