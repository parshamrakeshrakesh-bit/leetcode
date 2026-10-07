class Solution {
    public int compress(char[] chars) {
        int i=0;
        int write=0;
        while(i<chars.length){
            char ch=chars[i];
            int count=0;
            while(i<chars.length && chars[i]==ch){
                i++;
                count++;
            }
            chars[write]=ch;
            write++;
            if(count>1){
                String num=String.valueOf(count);
                for(char c:num.toCharArray()){
                    chars[write]=c;
                    write++;
                }
            }
        }
        return write;
    }
}