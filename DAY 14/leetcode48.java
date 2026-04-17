class leetcode48 {
    public void rotate(int[][] matrix) {
        for(int i = 0;i<matrix.length;i++){
            for(int j=i;j<matrix[0].length;j++){
                int tempval = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tempval;
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length/2;j++){
                int val = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-j-1];
                matrix[i][matrix[0].length-j-1] = val;
            }
        }
    }
}