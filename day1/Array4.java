package day1;

public class Array4 {
    public static void main(String[] args) {
        int[][] score = new int[5][3];
        score[0][0] = 99;
        score[2][2] = 87;
        /* for(int i =0; i<score.length; i++){
            for(int j=0; j<score[i].length; j++){
                System.out.print(score[i][j]+ "\t");
            }
            System.out.println();
        } */
        for(int[] i : score){ // for (1차원 배열 : 2차원배열)
            for(int j : i){ // for (int형 배열을 받을 아무 값 하나를 선언해서 받아주면 끝)
                System.out.print(j+ "\t");
            }
            System.out.println();
        }
    }
}
