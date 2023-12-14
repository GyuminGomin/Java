# 실행

- 실습을 진행하기 위해서는 vscode의 최상단 폴더가 DEMO로 설정되어 있어야 함


1. ctrl+shift+p > spring initializr: Add starter
```
Project : Maven
Language : Java
Spring Boot : 3.16(가장 아래)
Project Metadata
    Group : com.example
    Artifact : demo
    Name : demo
    Package name : com.example.demo
Packaging : Jar
Java : 17 (가장 아래) (LTS 추천)
Dependencies : Spring Web, Lombok, thymeleaf
```

2. Controller 설정
```
1. java/com/example/demo 아래에 DemoController.java 파일
2. resource/templates 아래에 demo.html 파일
```
