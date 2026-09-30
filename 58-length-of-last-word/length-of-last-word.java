class Solution {
    public int lengthOfLastWord(String s) {
        String[] words=s.trim().split(" ");
        String str=words[words.length-1];
        int length=str.length();
        return length;
    }
}