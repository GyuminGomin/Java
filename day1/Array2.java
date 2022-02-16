package day1;

import java.util.Scanner;

public class Array2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // 표준 입력 장치
        int[] score = new int[5];
        for(int i=0; i<score.length; i++){
            System.out.println(i+"번째 점수 입력 >>> ");
            score[i] = in.nextInt(); // int 형태로 입력 받게끔 하는 것
        }
        for(int i = 0; i<score.length;i++){
            System.out.println(i+"번째 값은 : "+ score[i]);
        }
    }
}
