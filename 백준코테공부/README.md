- 백준 코테 공부
```
참고
- 패키지는 작성안해도 된다.
- 이클립스나 VsCode로 코드를 짠 후에 제출하자
- Scanner 보다는 BufferedReader를 사용하자 (속도가 더 빠름)
- 그리고 split() 보다는 StringTokenizer로 입력 (더 빠름)
- 이것보다도 직접 1byte씩 읽어오는 더 빠른 방식이 존재 (https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/)
- System.out.println() 보다 BufferedWriter로 출력하자 (더 빠름)
```


``` java

java.util.*;
java.lang.*;


public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
        }
    }
}
```

```
<img src="" style="width:100%; height:auto;" />

```

### 1000 번
<img src="./images/1000.png" style="width:100%; height:auto;" />

- 하면서 배운 건데, bw.write는 정수를 직접 출력할 수 없다고 함 (Text로만 반환시켜주기 때문에)

### 1001 번
