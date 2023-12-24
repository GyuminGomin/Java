# 게시판 만들기 

## (JPA 엔티티와 연관 관계 매핑 실습)

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

    - domain 폴더 아래 User.java, Post.java, Comment.java 파일 생성
    
``` java
// User.java
package com.edu.board.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
@Data // Getter, Setter 둘다
@Entity
@Table(name = "app_user")
public class User {
    
    @Id
    @Schema(description = "User's Id required for creating a user") // Swagger
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String uesrName;
}
```

``` java
// Post.java
package com.edu.board.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // columnDefinition : 컬럼의 타입 설정
    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments; // Post에서 Comment를 보기 위한 내비게이션

}

```

``` java
// Comment.java
package com.edu.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore // Json으로 Parsing할 때, 해당 테이블의 데이터는 무시하라(예로 들자면, Comment를 참조할 때, Post를 볼 필요가 없다.)
    private Post post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
}
```

## (JPA Repository 실습)

1. 서버 설정
``` yml
# src/main/java/resources/application.properties
server.port=8080

spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
# 바로 접속 가능(mysql workbench와 비슷)
spring.h2.console.path=/h2-console
# 바로 접속하기 위한 접속 패스 설정
```

2. repository 폴더 생성
`src > main > java > com > edu > board 아래에 repository 폴더 생성`

3. JpaRepo 생성

	- UserRepository.java, PostRepository.java, CommentRepository.java

``` java
// UserRepository.java
package com.edu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

}
```
``` java
// PostRepository.java
package com.edu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}
```  
``` java
// CommentRepository.java 

package com.edu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
```

4. 실행 및 DB 접속 테스트
	1. BoardApplication.java를 실행
	2. 웹사이트에서 localhost:8080/h2-console 접속
	3. JDBC URL : jdbc:h2:mem:testdb, User Name : sa 설정 후, Connect
	4. SQL Statement에서 SQL문 작성 (select * from app_user) : 정상 작동하면 완료

## 