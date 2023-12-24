# 게시판 만들기 (JPA 엔티티와 연관 관계 매핑 실습)

1. Spring initializr 로 생성
```
Spring boot version : 3.1.7
Project language : Java
Group Id : com.edu
Artifact Id : board
Packaging type : Jar
Java version : 17
Choose Dependency : Lombok, Spring Web, Spring data JPA, H2-Database
```

2. 추가 Dependency (springdoc-openapi-ui)

pom.xml 파일에
mavenrepository 오픈 소스 사이트에서 springdoc-openapi-ui
1.5버전중에 가장 최신을 복사해와 붙여넣기 해준다.
``` xml
<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.5.13</version>
		</dependency>
```

3. Domain 생성

`src > main > java > com > edu > board 아래에 domain 폴더 생성`

4. 엔티티 생성

```
1. domain 폴더 아래 User.java 파일 생성
2. Post.java 파일 생성
3. Comment.java 파일 생성
```

