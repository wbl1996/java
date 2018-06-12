

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    public static void main(String args[]){
        //int p = 997;
        //int q = 1973;
        int p = 11;
        int q= 17;
        int n = p*q;
        int fi = (p-1)*(q-1);
        int e = getRand(fi);
        int d = getD(e,fi);
        System.out.print("请输入一个字符:");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char[] data = s.toCharArray();
        int input = (int)data[0];
        int result = encrypt(input,e,n);
        System.out.print("加密后为:"+result);
        int decryption = encrypt(result,d,n);
        System.out.print("\n解密后:"+(char)decryption);

    }
    public static int gcd(int a,int b){
        return (a==0)? b:gcd(b%a , a);
    }
    public static int getRand(int fi){
        int num = 0;
        for(;;){
            Random rand = new Random();
            num = rand.nextInt(fi);
            if(num>1&&gcd(num,fi)==1){
                break;
            }
        }
        return num;
    }
    public static int getD(int e,int fi){
        int t = 0;
        while(true){
            t++;
            if((fi*t+1)%e==0){
                break;
            }
        }
        int result = (fi*t+1)/e;
        return result;

    }
    public static int encrypt(int data,int num1,int num2){
        BigInteger b1 = BigInteger.valueOf(data);
        BigInteger b2 = BigInteger.valueOf(num2);
        BigInteger num = b1.pow(num1);
        BigInteger result = num.remainder(b2);
        int output = Integer.valueOf(result.toString());
        return output;
    }
}
