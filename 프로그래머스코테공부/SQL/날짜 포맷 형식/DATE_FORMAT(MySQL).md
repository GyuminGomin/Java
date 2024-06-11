-- 18. SELECT > 흉부외과 또는 일반외과 의사 목록 출력하기

SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, '20%y-%m-%d')
FROM DOCTOR
WHERE MCDP_CD IN ('CS', 'GS')
ORDER BY HIRE_YMD DESC, DR_NAME ASC;

<table border="1">
    <tr>
        <th>FORMAT
        </th>
        <th>설명
        </th>
    </tr>
    <tr>
        <td>%M
        </td>
        <td>Month (January, February ...)
        </td>
    </tr>
    <tr>
        <td>%m
        </td>
        <td>Month (01, 02, 03 ...)
        </td>
    </tr>
    <tr>
        <td>%D
        </td>
        <td>Month 월 (1st, 2nd, 3rd ...)
        </td>
    </tr>
    <tr>
        <td>%Y
        </td>
        <td>Year 연도 (1999, 2000, 2020)
        </td>
    </tr>
    <tr>
        <td>%y
        </td>
        <td>Year 연도 (99, 00, 20)
        </td>
    </tr>
    <tr>
        <td>%X
        </td>
        <td>Year 연도 (1999, 2000, 2020) %V와 같이 쓰임 <br/>
        %V란 Week 주(시작: 일요일)
        </td>
    </tr>
    <tr>
        <td>%x
        </td>
        <td>Year 연도(1999, 2000, 2020) %v와 같이 쓰임 <br/>
        %v란 Week 주(시작: 월요일)
        </td>
    </tr>
    <tr>
        <td>%a
        </td>
        <td>Day of Week 요일 (Sun, Mon, Tue ...)
        </td>
    </tr>
    <tr>
        <td>%W
        </td>
        <td>Day of Week 요일 (Sunday, Monday ...)
        </td>
    </tr>
    <tr>
        <td>%d
        </td>
        <td>Day 일(00, 01, 02 ...)
        </td>
    </tr>
    <tr>
        <td>%e
        </td>
        <td>Day 일(0, 1, 2 ...)
        </td>
    </tr>
    <tr>
        <td>%c
        </td>
        <td>Month(1, 2, 3 ...)
        </td>
    </tr>
    <tr>
        <td>%b
        </td>
        <td>Month(Jen Feb ...)
        </td>
    </tr>
    <tr>
        <td>%j
        </td>
        <td>n번째 일(100, 365)
        </td>
    </tr>
    <tr>
        <td>%H
        </td>
        <td>Hour 시(00, 01, 24) 24시간 형태
        </td>
    </tr>
    <tr>
        <td>%h
        </td>
        <td>Hour 시(01, 02, 12) 12시간 형태
        </td>
    </tr>
    <tr>
        <td>%I
        </td>
        <td>Hour 시(01, 02, 12) 12시간 형태
        </td>
    </tr>
    <tr>
        <td>%i
        </td>
        <td>Minute 분(00, 01 59)
        </td>
    </tr>
    <tr>
        <td>%r
        </td>
        <td>hh:mm:ss AP
        </td>
    </tr>
    <tr>
        <td>%T
        </td>
        <td>hh:mm:ss
        </td>
    </tr>
    <tr>
        <td>%S, %s
        </td>
        <td>Second 초
        </td>
    </tr>
    <tr>
        <td>%p
        </td>
        <td>AP, PM
        </td>
    </tr>
    <tr>
        <td>%w
        </td>
        <td>Day Of Week (0, 1, 2) 0부터 일요일
        </td>
    </tr>
    <tr>
        <td>%U, %u, %V, %v
        </td>
        <td>%U, %V : Week 주(시작: 일요일) <br/>
        %u, %v : Week 주(시작: 월요일)
        </td>
    </tr>
</table>