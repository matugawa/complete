/*
DPCode1に情報を追加し、ナップサック問題に変えその解を求めます。
シチュエーション
開店前のお店があり、すでにお客さんがXグループ、ばらばらに待機しています。
各グループそれぞれの人数はx1, x2, x3, , ,人です。
またお店のシート数はY個であるので、一度に入店できる人数もY人です。
グループは別れて入店することはできません。 
また、お店はそれぞれのグループの持っている予算を把握しており、売上が最大となるように グループを選択します。
このとき売上が最大となるときの金額と、入店するグループの組合せを示すDPテーブルを作成する。

*/

import java.util.*;

public class Knapsack{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] size=new int[n];
        int[] value=new int[n];
        int volume=sc.nextInt();
        for(int i=0; i<n;i++){
            size[i]=sc.nextInt();
        }
        for(int i=0; i<n; i++){
            value[i]=sc.nextInt();
        }
        

        //dp table
        int[][] dp=new int[n][volume+1];
        //selected item
        int[][] dpp=new int[n][volume+1];
        //format no choice =-1
        for(int i=0; i<n; i++){
            for(int j=0; j<=volume; j++){
                dpp[i][j]=-1;
            }
        }
        for(int i=0; i<volume; i++){
            if(size[0]<=i){
                dp[0][i]=value[0];
                dpp[0][i]=0;
            }
        }
        for(int i=1; i<n; i++){
            for(int j=0; j<=volume; j++){
                if(size[i]>j){
                    dp[i][j]=dp[i-1][j];
                    dpp[i][j]=dpp[i-1][j];
                }else if(j-size[i]>=0){
                    int tmp=dp[i-1][j];
                    int tmpp=dp[i-1][j-size[i]] +value[i];
                    if(tmp>tmpp){
                        dp[i][j]=tmp;
                        dpp[i][j]=dpp[i-1][j];
                    }else{
                        dp[i][j]=tmpp;
                        dpp[i][j]=i;
                    }
                }
            }
        }
        //show dp table
        for(int j=0; j<=volume; j++){
            System.out.printf("%4d", j);
        }
        System.out.printf("%n%n");

        for(int i=0; i<n; i++){
            for(int j=0; j<=volume; j++){
                System.out.printf("%4d", dp[i][j]);
            }
            System.out.println();
            for(int j=0; j<=volume; j++){
                System.out.printf("%4d", dpp[i][j]);
            }
            System.out.println();
        }
    }
}