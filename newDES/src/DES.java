public class DES {
    private static int[] ip_table = {57, 49, 41, 33, 25, 17, 9, 1,  //数据进行的第一次ip置换
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7,
            56, 48, 40, 32, 24, 16, 8, 0,
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6};
    private static int[] ps_table = {56,48,40,32,24,16, 8, 0,     //秘钥第一次置换，64位变56位
            57,49,41,33,25,17, 9, 1,
            58,50,42,34,26,18,10, 2,
            59,51,43,35, 62,54,46,38,
            30,22,14, 6,61,53,45,37,
            29,21, 13, 5,60,52,44,36,
            28,20,12, 4,27,19,11, 3};
    private static int[] left_move_table = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};   //左移表
    private static int[] ys_table = {13,16,10,23, 0, 4, 2,27,14, 5,20,9,  //压缩置换表，56位压缩成48位
            22,18,11, 3,25, 7,15, 6,26,19,12, 1,
            40,51,30,36,46,54,29,39,50,44,32,47,
            43,48,38,55,33,52,45,41,49,35,28,31};
    private static int[][][] S_Box= {         //s盒
            //S1
            {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},
            //S2
            {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},
            //S3
            {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},
            //S4
            {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},
            //S5
            {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},
            //S6
            {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},
            //S7
            {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},
            //S8
            {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}
    };
    private static int[] e_table = {31, 0, 1, 2, 3, 4, 3, 4, 5, 6, 7, 8,  //32位数据进行e扩展成48位
            7, 8,9,10,11,12,11,12,13,14,15,16,
            15,16,17,18,19,20,19,20,21,22,23,24,
            23,24,25,26,27,28,27,28,29,30,31, 0};
    private static int[] p_table = {15, 6,19,20,28,11,27,16, 0,14,22,25, 4,17,30,9,   //s盒后进行p置换
            1, 7,23,13,31,26, 2, 8,18,12,29, 5,21,10, 3,24};
    private  static int[] _ip_table = { 39, 7,47,15,55,23,63,31,38, 6,46,14,54,22,62,30,  //IP末置换
            37, 5,45,13,53,21,61,29,36, 4,44,12,52,20,60,28,
            35, 3,43,11,51,19,59,27,34, 2,42,10,50,18,58,26,
            33, 1,41,9,49,17,57,25,32, 0,40, 8,48,16,56,24};
    public static void main(String args[]){
        System.out.print("***********DES************\n");
        String str = "abcdefgh";            //对abcdefgh加密
        String pwd = "12345678";           //秘钥是12345678
        char[] str_array = str.toCharArray();
        char[] pwd_array = pwd.toCharArray();
        int[] str_bit = changeToBinary(str_array);   //把输入的字符串数据转换成64位2进制数据
        int[] pwd_bit = changeToBinary(pwd_array);  //把秘钥转换成64位2进制
        int[] encryption = encrypt(str_bit,pwd_bit);
        System.out.print("加密后\n");
        char [] result = changeToChar(encryption);
        for(int i=0;i<8;i++){
            System.out.print(result[i]);
        }
        System.out.println("\n解密后");
        int[] decryption = decrypt(encryption,pwd_bit);
        char[] output = changeToChar(decryption);
        for(int i=0;i<8;i++){
            System.out.print(output[i]);
        }
    }
    public static int[] changeToBinary(char input[]){      //字符数组转换成二进制
        int k;
        int[] output = new int[64];
        for(int i=0;i<8;i++){
            k = input[i];
            for(int j=0;j<8;j++){
                output[8*(i+1)-1-j] = k%2;
                k = k/2;
            }
        }
        return output;
    }
    public static char[] changeToChar(int input[]){       //64位二进制转换成8个字符
        int[] temp = new int[8];
        char [] output = new char[8];
        for(int i=0;i<8;i++){
            System.arraycopy(input,i*8,temp,0,8);
            int num = 0;
            for(int j=0;j<8;j++){
                num = num + temp[j]*(int)Math.pow(2,7-j);
            }
            output[i] = (char)num;
        }
        return output;
    }
    public static int[] permute(int input[],int table[]){     //表置换函数
        int[] output = new int[table.length];
        for(int i=0;i<table.length;i++){
            output[i] = input[table[i]];
        }
        return output;
    }
    public static int[] xor(int data1[],int data2[]){         //异或函数
        int[] output = new int[data1.length];
        for(int i=0;i<data1.length;i++){
            if(data1[i]==data2[i]){
                output[i]=0;
            }else{
                output[i]=1;
            }
        }
        return output;
    }
    public static void move(int data[]){          //左移一位
        int k = data[0];
        for(int i=0;i<data.length-1;i++){
            data[i] = data[i+1];
        }
        data[data.length-1] = k;
    }
    public static void leftMove(int data[],int num){  //左移num位，num为1或者2
        if(num==1){
            move(data);
        }
        if(num==2){
            move(data);
            move(data);
        }
    }
    public static int[][] getKeys(int[] pwd){         //获取16个秘钥，存放在二维数组中
        int[] changed_pwd = permute(pwd,ps_table);      //经过表置换得到56位秘钥
        int[] left = new int[28];
        int[] right = new int[28];
        int temp[][] = new int[16][56];
        int output[][] = new int[16][48];
        System.arraycopy(changed_pwd,0,left,0,28);
        System.arraycopy(changed_pwd,28,right,0,28);
        for(int i=0;i<left_move_table.length;i++){
            leftMove(left,left_move_table[i]);
            leftMove(right,left_move_table[i]);
            System.arraycopy(left,0,temp[i],0,left.length);
            System.arraycopy(right,0,temp[i],left.length,right.length);
        }    //经过循环，得到16个56位的秘钥
        for(int i=0;i<16;i++){
            System.arraycopy(permute(temp[i],ys_table),0,output[i],0,ys_table.length);
        }                    //压缩置换，得到16个48位秘钥
        return output;
    }
    public static int[] s_change(int data[]){      //s盒置换，48位进，得到32位
        int[] temp1 = new int[4];
        int[] output = new int[32];
        for(int i=0;i<8;i++){
            int a = S_Box[i][data[i*6]*2+data[(i+1)*6-1]][data[i*6+1]*8+data[i*6+2]*4+data[i*6+3]*2+data[i*6+4]];
            for(int j=0;j<4;j++){
                temp1[3-j] = a%2;
                a = a/2;
            }
            System.arraycopy(temp1,0,output,i*4,4);
        }
        return output;
    }
    public static int[] f(int r_data[],int[] key){     //48位的秘钥
        int[] e_changed_data = permute(r_data,e_table);  //E扩展，32->48位
        int[] xor_changed_data = xor(e_changed_data,key);     //与秘钥异或
        int[] s_changed_data = s_change(xor_changed_data);//S盒置换
        return permute(s_changed_data,p_table);       //p置换得到最终的32位秘钥
    }
    public static int [] encrypt(int data[],int pwd[]){      //加密
        int[] changed_data = permute(data,ip_table);
        int [][] temp1 = new int [17][32];
        int [][] temp2 = new int [17][32];
        int [] output = new int [64];
        System.arraycopy(changed_data,0,temp1[0],0,32);
        System.arraycopy(changed_data,32,temp2[0],0,32);
        int[][] keys = getKeys(pwd);
        for(int i=0;i<16;i++){
            System.arraycopy(temp2[i],0,temp1[i+1],0,32);
            System.arraycopy(xor(temp1[i],f(temp2[i],keys[i])),0,temp2[i+1],0,32);
        }
        System.arraycopy(temp2[16],0,output,0,32);
        System.arraycopy(temp1[16],0,output,32,32);
        return permute(output,_ip_table);
    }
    public static int [] decrypt(int data[],int pwd[]){    //解密
        int[] changed_data = permute(data,ip_table);
        int [][] temp1 = new int [17][32];
        int [][] temp2 = new int [17][32];
        int [] output = new int [64];
        System.arraycopy(changed_data,0,temp1[0],0,32);
        System.arraycopy(changed_data,32,temp2[0],0,32);
        int[][] keys = getKeys(pwd);
        for(int i=0;i<16;i++){
            System.arraycopy(temp2[i],0,temp1[i+1],0,32);
            System.arraycopy(xor(temp1[i],f(temp2[i],keys[15-i])),0,temp2[i+1],0,32);
        }
        System.arraycopy(temp2[16],0,output,0,32);
        System.arraycopy(temp1[16],0,output,32,32);
        return permute(output,_ip_table);
    }
}
