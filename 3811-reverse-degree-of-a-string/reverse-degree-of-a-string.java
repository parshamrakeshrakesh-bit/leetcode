class Solution {
    public int reverseDegree(String s) {
        int sum=0;
        for(int i=0;i<s.length();i++){
            int sp=26-(s.charAt(i)-'a');
            int count=i+1;
            sum+=sp*count;
        }
        return sum;
    }
}