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
    private String userName;
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

## (Service 레이어 실습)

1. service 폴더 생성
`src > main > java > com > edu > board 아래에 service 폴더 생성`

2. 서비스 레이어 생성 
	
``` java
- 1번째 방법 (Service interface를 만든 후, 구현)
// UserService.java
import java.util.List;

import com.edu.board.domain.User;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findById(String id);
    void deleteById(String id);
}

// UserServiceImple.java
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.board.domain.User;
import com.edu.board.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository; // UserServiceImpl을 생성해주면 바로 할당해주는 것

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user); // insert or update
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
        // User가 없는 경우도 있으므로 그렇게 하지 않도록 .orElse(null);을 추가 (그렇지 않으면 null을 출력)
        // .orElseThrow() -> 예외를 처리
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
    
}

```
``` java
- 2번째 방법 (Service Class를 바로 구현)
// PostService.java
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.board.domain.Post;
import com.edu.board.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

}

// CommentService.java
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.board.domain.Comment;
import com.edu.board.repository.CommentRepository;

@Service
public class CommentService {
    
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
		if (comment.getContent().length() < 10) throw new RuntimeException("댓글은 10글자 이상이어야 합니다.");
        return commentRepository.save(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
    
}
```
	
## (Controller 레이어 실습)
1. controller 폴더 생성
`src > main > java > com > edu > board 아래에 controller 폴더 생성`

2. 컨트롤러 레이어 생성

- UserController.java, PostController.java, CommentController.java

``` java
// UserController.java (Post나 Comment도 아래와 비슷하게 작업해줌 됨)
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.board.domain.User;
import com.edu.board.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    } // 요청 처리한 user가 body에 존재하므로 body에 있는 것을 가져와 저장해준다는 의미

    @GetMapping
    public List<User> readAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}") // http:localhost:8080/users/hong
    public User readOne(@PathVariable String id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        User u = userService.findById(id);
        if (u != null) {
            u.setUserName(user.getUserName());
            return userService.save(u);
        }
        throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteById(id);
    }
    
}
```

``` java
// PostController.java 참고

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.board.domain.Post;
import com.edu.board.service.PostService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.save(post);
    } // 요청 처리한 user가 body에 존재하므로 body에 있는 것을 가져와 저장해준다는 의미

    @GetMapping
    public List<Post> readAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}") // http:localhost:8080/users/hong
    public Post readOne(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        Post p = postService.findById(id);
        if (p != null) {
            p.setTitle(post.getTitle());
            return postService.save(p);
        }
        throw new EntityNotFoundException("게시물을 찾을 수 없습니다.");
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deleteById(id);
    }
    
}
```

3. 실행 후 API 테스트 확인 (insomnia 사용)

- 먼저 insomnia 설치
```
https://insomnia.rest/
(Free로 설치, github 아이디가 있다면 github으로 sign up하길 권장)
```

- 설치 후 
```
1. get localhost:8080/users


2. post localhost:8080/users

{
	"userId":"아무거나",
	"password":"아무거나",
	"userName":"아무거나"
}
```

## (스프링 데이터 REST 실습)
1. jpaDemo 폴더를 복사해서 jpaDemo_REST로 설정

2. pom.xml dependency 추가
```
(spring boot starter data rest)
https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-rest

버전은 상관없으니 아무거나 가져와서 버전 정보 삭제(알아서 버전에 맞게 설정)
```

3. controller의 bean 할당해주는 정보 주석
``` java
// UserController.java
// @RestController
// @RequestMapping("/users")

// PostController.java
// @RestController
// @RequestMapping("/post")

// CommentController.java
// @RestController
// @RequestMapping("/comment")
```

4. repostiory 레이어 수정
``` java
// UserRepository.java
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, String>{

}

// PostRepository.java
@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostRepository extends JpaRepository<Post, Long>{
    
}

// CommentRepository.java
@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
```

5. 똑같이 insomnia 켜서 GET POST PUT DELETE 실행 해보면 작동

## (스프링 게이트웨이 실습)
1. 새로운 포트를 가동하기 위한 Spring boot 새로 실행
```
Spring boot version : 3.1.7
Project language : Java
Group Id : com.example
Artifact Id : demo
Packaging type : Jar
Java version : 17
Choose Dependency : Gateway
```

2. 새로만든 Gateway 폴더아래에 application.properties 수정
``` java
// application.properties
server.port=9000

spring.cloud.gateway.routes[0].id=board-service
spring.cloud.gateway.routes[0].uri=http://localhost:8080
spring.cloud.gateway.routes[0].predicates[0]=Path=/**
```

3. jpaDemo board 서버와 jpaDemo_Gateway demo 서버 둘다 실행

4. insomnia로 localhost:9000으로 GET, POST, PUT, DELETE를 실행하면 작동 됨

## (스프링 시큐리티 실습)

- 참고 : 현재 실습을 진행하면서 SecurityConfig.java 파일을 설정했는데, 아마도 현재 업데이트 된 경우라서 실습이 진행이 안됨.
- 어짜피 현재 단계에서는 이해 하나도 안되므로 넘어가도 좋음

1. jpaDemo 폴더 아래 board를 복사해서 board_security로 설정

2. Ctrl+Shift+P -> Spring intializr Add Starter로 spring security 설정

3. config 폴더 생성
`src > main > java > com > edu > board 아래에 config 폴더 생성`

4. SimplePasswordEncoder.java, ServiceUserDetail.java, ServiceUserDetailService.java, SecurityConfig.java
- Spring Security에서 제공
``` java
// SimplePasswordEncoder.java
import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordEncoder implements PasswordEncoder{

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword);
    }
    
}
```

``` java
// ServiceUserDetail.java
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class ServiceUserDetail implements UserDetails {
    
    private Long seq;
    private String loginId;
    private String password;
    private Collection<GrantedAuthority> authorities; // 권한
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 실습에서 권한은 별도로 존재하지 않아 null 처리
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```
``` java
// ServiceUserDetailService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.board.domain.User;
import com.edu.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceUserDetailService implements UserDetailsService {
    
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findById(username);
        ServiceUserDetail serviceUserDetail = new ServiceUserDetail();
        serviceUserDetail.setLoginId(user.getUserId());
        serviceUserDetail.setPassword(user.getPassword());
        return serviceUserDetail;
    } 
}
```
``` java
// SecurityConfig.java
import org.apache.tomcat.util.net.DispatchType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().disable()
            .authorizeRequests(request -> request
                .dispatcherTypeMatchers(DispatchType.FORWARD).permitAll()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(login -> login
            .defaultSuccessUrl("/success", true)
            .permitAll()
        );

        return http.build();
    }
}
```

## (Junit 실습)

1. pom.xml 파일에 springboot test 의존성 확인
    - 기존 JPADEMO 폴더의 board로 가서 pom.xml 확인
    ``` xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
	</dependency>
    <!-- 위 의존성이 존재해야 함 -->
    ```

2. test 폴더를 오픈하고 controller 폴더 생성하고 UserControllerTest
``` java
// test/java/com/edu/board/controller/UserControllerTest.java
public class UserControllerTest {
    
    @Test
    void test() {
        int a = 3;
        int b = 3;
        int c = a+b;

        assertEquals(6, c); // 테스트가 이걸 통과해야 잘 만들어진 소스인 것 학인
    }
}

// 위의 코드를 실행하기 위해 vscode의 왼쪽 파일탭에서 Test를 클릭해서 확인
```
3. Spring Boot Test 진행해보기 위한 코드  
<a href="./board/src/test/java/com/edu/board/controller/">코드</a>
    - 이해가 안되는 부분이므로 그냥 넘어가는 것을 권장
    - 그리고 지금까지의 실습 부분에 없었던 dto를 폴더에 추가해야하므로 넘어가기

