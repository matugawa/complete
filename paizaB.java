import java.util.*;

public class paizaB{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();

        Map<String, Map<String, Integer>> mp=new LinkedHashMap<>();

        for(int i=0; i<a; i++){
            mp.put(sc.next(), new LinkedHashMap<>());
        }
        for(int i=0; i<b; i++){
            mp.get(sc.next()).put(sc.next(), sc.nextInt());
        }
 
        Set<Map.Entry<String, Map<String, Integer>>> sett=mp.entrySet();
        for(Map.Entry <String, Map<String, Integer>> set: sett){
            System.out.println(set.getKey());
            Set<Map.Entry<String, Integer>> se=mp.get(set.getKey()).entrySet();
            for(Map.Entry<String, Integer> s: se){
                System.out.println(s.getKey()+" "+s.getValue());
            }
            System.out.println("-----");

        }
    }
}