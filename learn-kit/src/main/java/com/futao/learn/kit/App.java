package com.futao.learn.kit;

/**
 * Java中的进制与进制之间的转换
 *
 * @author futao
 * Created on 2020/3/11.
 */
public class App {
    public static void main(String[] args) {
        //二进制
        int bin = 0b1100010;
        //八进制
        int oct = 0142;
        //十六进制
        int hex = 0X62;
        //十进制
        int dec = 98;

        //java中默认以十进制输出
        System.out.println(bin + "," + oct + "," + hex + "," + dec);

        //进制之间的转换
        //1.转换成二进制
        System.out.println(Integer.toBinaryString(bin));
        System.out.println(Integer.toBinaryString(oct));
        System.out.println(Integer.toBinaryString(hex));
        System.out.println(Integer.toBinaryString(dec));


        //2.转换成八进制
        System.out.println(Integer.toOctalString(bin));
        System.out.println(Integer.toOctalString(oct));
        System.out.println(Integer.toOctalString(hex));
        System.out.println(Integer.toOctalString(dec));

        //3.转换成十六进制
        System.out.println(Integer.toHexString(bin));
        System.out.println(Integer.toHexString(oct));
        System.out.println(Integer.toHexString(hex));
        System.out.println(Integer.toHexString(dec));

        //随意的进制转换
        System.out.println(Integer.toString(2, 2));
        System.out.println(Integer.valueOf("10000001", 2));

    }
}
