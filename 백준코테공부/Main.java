import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        for (int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            List<Integer> nk = new ArrayList<>();
            while (st.hasMoreTokens()) {
                nk.add(Integer.parseInt(st.nextToken()));
            }

            Map<Integer, Integer> times = new HashMap<>();
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=nk.get(0); j++) {
                times.put(j,Integer.parseInt(st.nextToken()));
            } 

            // 시간을 구하는 것이므로, rule에 저장되는 값을 시간으로 변경
            int[][] rule = new int[nk.get(1)][2]; 
            for(int k=0; k<nk.get(1); k++) {
                st = new StringTokenizer(br.readLine(), " ");
                rule[k][0] = Integer.parseInt(st.nextToken());
                rule[k][1] = Integer.parseInt(st.nextToken());
            }

            int goal = Integer.parseInt(br.readLine());

            // 위상정렬은 하나씩 지워가며 설계
            // 못 풀겠다.. 오늘부터 코테 책보며 공부하고 풀기 시작해야겠다..
            
            
            
        }

        bw.flush();
        bw.close();
    }
}