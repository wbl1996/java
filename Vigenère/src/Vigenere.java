import java.util.Scanner;
public class Vigenere {
    private static char[] table = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入明文:");
        String data = sc.nextLine();
        System.out.print("请输入秘钥:");
        String password = sc.nextLine();
        char[] data_array = data.toCharArray();
        char[] pwd_array = password.toCharArray();
        char[] upper_data = toUpper(data_array);
        char[] upper_pwd = toUpper(pwd_array);
        char [] encryption = encrypt(upper_data,upper_pwd);
        System.out.print("加密后:");
        for(int i=0;i<encryption.length;i++){
            System.out.print(encryption[i]);
        }
        System.out.print("\n解密后:");
        int[] change = isLowerCase(data_array);
        char [] decryption = toLower(decrypt(encryption,upper_pwd),change);
        for(int i=0;i<decryption.length;i++){
            System.out.print(decryption[i]);
        }
    }
    public static char[][] getTable(char table[]){
        char[][] output = new char[26][26];
        for(int i=0;i<table.length;i++) {
            for (int j = 0; j < table.length; j++) {
                if ((int) table[j] + i > 90) {
                    output[i][j] = (char) ((int) table[j] - 26 + i);
                } else {
                    output[i][j] = (char) ((int) table[j] + i);
                }
            }
        }
        return output;
    }
    public static int[] isLowerCase(char data[]){
        int[] temp = new int[data.length];
        for(int i=0;i<data.length;i++){
            if((int)data[i]>90){
                temp[i] = 1;
            }else{
                temp[i] = 0;
            }
        }
        return temp;
    }
    public static char[] toUpper(char data[]){
        char[] output = new char[data.length];
        for(int i=0;i<data.length;i++){
            if((int)data[i]<=90){
                output[i] = data[i];
            }else{
                output[i] = (char)((int)data[i]-32);
            }
        }
        return output;
    }
    public static char[] toLower(char data[],int change[]){
        char[] output = new char[data.length];
        for(int i=0;i<data.length;i++){
            if(change[i]==1){
                output[i] = (char)((int)data[i]+32);
            }else{
                output[i] = data[i];
            }
        }
        return output;
    }
    public static int getIndex(char data[],char a){
        int temp = 0;
        for(int i=0;i<data.length;i++){
            if(data[i] == a ){
                temp = i;
                break;
            }
        }
        return temp;
    }
    public static char[] encrypt(char data[],char pwd[]){
        int a = data.length;
        int b = pwd.length;
        int x = a/b;
        int y = a%b;
        char [] new_pwd = new char[data.length];
        char [] encryption = new char[data.length];
        char[][] new_table = getTable(table);
        for(int i=0;i<x;i++){
            System.arraycopy(pwd,0,new_pwd,i*pwd.length,pwd.length);
        }
        for(int i=0;i<data.length-x*pwd.length;i++){
            new_pwd[x*pwd.length+i] = pwd[i];
        }
        for(int i=0;i<data.length;i++){
            encryption[i] = new_table[(int)data[i]-65][(int)new_pwd[i]-65];
        }
        return encryption;
    }
    public static char[] decrypt(char data[],char pwd[]){
        int a = data.length;
        int b = pwd.length;
        int x = a/b;
        int y = a%b;
        char [] new_pwd = new char[data.length];
        char [] decryption = new char[data.length];
        char[][] new_table = getTable(table);
        char[] temp = new char[table.length];
        for(int i=0;i<x;i++){
            System.arraycopy(pwd,0,new_pwd,i*pwd.length,pwd.length);
        }
        for(int i=0;i<data.length-x*pwd.length;i++){
            new_pwd[x*pwd.length+i] = pwd[i];
        }
        for(int i=0;i<decryption.length;i++){
            System.arraycopy(new_table[(int)new_pwd[i]-65],0,temp,0,temp.length);
            decryption[i] = table[getIndex(temp,data[i])];
        }
        return decryption;
    }
}
