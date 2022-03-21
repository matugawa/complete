/*
解法に動的計画法を用いた部分和問題を利用して、
現実に問題となりそうなシチュエーションの 作成とその解をプログラムを用いてもとめてみます。
シチュエーション 開店前のお店があり、すでにお客さんがXグループ、ばらばらに待機しています。 
各グループそれぞれの人数はx1, x2, x3, , ,人です。 またお店のシート数はY個であるので、
一度に入店できる人数もY人です。 グループは別れて入店することはできません。
開店時、一度に入店できる可能人数を求めてみます。

*/

import java.util.*;

public class DPCode1{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int numGr=sc.nextInt(); //number of groups
        int numSeat=sc.nextInt(); //number of seats
        int[] numPerGr=new int[numGr];

        for(int i=0; i<numGr; i++){
            numPerGr[i]=sc.nextInt();
        }
        //DP table
        boolean[][] dp=new boolean[numGr][numSeat+1];
        for(int i=0; i<numGr; i++){
            for(int j=0; j<numSeat+1; j++){
                if(j==0){
                    dp[i][j]=true;
                }else{
                    if(j==numPerGr[i]){
                        dp[i][j]=true;
                    }
                    if(i>0 &&dp[i-1][j]==true){
                        dp[i][j]=true;
                    }
                    if(i>0 && j-numPerGr[i]>0 &&dp[i-1][j-numPerGr[i]]){
                        dp[i][j]=true;
                    }
                }
            }
        }
        //max value of range
        for(int i=numSeat; i>0; i--){
            if(dp[numGr-1][i]){
                System.out.println(i);
                break;

            }
        }
    }
}