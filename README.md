# 🎶Java
---

<a href="#스프링_프레임워크_개요_및_특징">스프링 프레임 워크 개요 및 특징</a>
<a href="#스프링_빌드도구">스프링 빌드도구</a>


---
>Java

# JDK 설치
1. OpenJDK 검색
1. OpenJDK 11 버전 검색
1. 또는zuju jdk 11 검색
1. 사이트에 들어가, Java11(LTS)설치 (윈도우) .zip으로해도 되고, .msi로 해도 됨
1. 환경 변수 설정 한 후, cmd로 확인 하면 끝

# Maven 설치
1. maven 다운로드 검색
1. 다운로드 후 압축풀기 해서 환경변수 설정 bin폴더로
1. vscode Extension Pack for Java를 설치하면 끝
1. Extension에서 Community Server Connection 설치





---
# 스프링_프레임워크_개요_및_특징

## 스프링 프레임워크
`엔터프라이즈용 Java 애플리케이션 개발을 편하게 할 수 있게 해주는 오픈소스 경량급 애플리케이션 프레임워크 - 기업과 개인에 무관하게 웹 애플리케이션 개발을 위해 스프링 코드를 사용할 수 있도록 오픈소스로 제공되는 방대한 소스를 지닌 프레임워크`

- 엔터프라이즈 용 : 기업용 (db, 복잡한 로직, 규격화 된 통신 제공)

### 프레임 워크란?
`소프트웨어의 구체적인 부분에 해당하는 설계와 구현을 재사용이 가능하게끔 일련의 협업화된 형태로 클래스들을 제공하는 것`

- 개발 용이성 : 패턴 기반 개발과 비즈닛 로직에만 집중한 개발 가능 공통 기능은 프레임워크가 제공
- 시스템 복잡도의 감소 : 복잡한 기술은 프레임워크에 의해 숨겨지며, 미리 잘 정의된 기술 셋 적용
- 이식성 : 플랫폼 비의존적인 개발 가능, 플랫폼과의 연동은 프레임워크가 제공함
- 품질보증 : 검증된 개발기술과 패턴에 따른 개발, 고급 개발자와 초급 개발자의 차이를 줄여줌.
- 운영 용이성 : 변경이 용이, 비즈니스로직/아키텍처 파악이 용이
- 개발코드의 최소화 : 반복 개발 제거, 공통 컴포넌트와 서비스 활용
- 변경 용이성 : 잘 구조화 된 아키텍처 적용, 플랫폼에 비 의존성
- 설계와 코드의 재사용성 : 프레임워크 서비스 및 패턴의 재사용, 사전에 개발된 컴포넌트으 재사용

### EJB(Enterprise JavaBeans)
`Java 기반의 엔터프라이즈 애플리케이션 개발을 위한 서버측 컴포넌트 모델`

- JavaBeans : 자바로 작성된 소프트웨어 컴포넌트들, 자바 클래스들이 복합적으로 이루어진 구조

- 문제점
    - 복잡성, 무거움, 학습 곡선과 개발 복잡성

### 스프링 프레임워크의 구조
- Core
    - 프레임워크의 가장 기본적인 부분이며 `IoC` 및 종속성 주입 기능을 제공
    - BeanFactory를 기반으로 `Bean 클래스`들을 제어할 수 있는 기능 지원
- Context
    - Spring Core 바로 위에 있으며 Spring Core에서 지원하는 기능 외에 추가적인 기능들과 좀 더 쉬운 개발이 가능하도록 지원하고 EJB등을 위한 Adaptor들을 포함
- DAO
    - 데이터베이스 공급업체별 `JDBC` 코딩 및 구문 분석을 수행할 필요가 없는 JDBC 추상화 계층을 제공
- ORM
    - `JPA`, `JDO`, `Hibernate` 및 `iBatis`를 포함한 일반적인 개체 관계 매핑 API에 대한 통합 계층을 제공
- Web
    - 파일 업로드 기능, 서블릿 수신기를 사용한 IoC 컨테이너 초기화 및 웹 지향 애플리케이션 컨텍스트와 같은 기본적인 웹 지향 통합 기능을 제공
- MVC
    - MVC(ModelViewController) 구현을 제공
    - 도메인 모델 코드와 웹 양식을 완벽하게 구분할 수 있으며 Spring Framework의 다른 모드 기능을 사용할 수 있음
- JPA(Java Persistence API)
    - 현재 ORM(Object Relational Mapping)의 기술 표준으로, 인터페이스 모음
    - ORM에 대한 자바 API 규격이며 Hibernate, OpenJPA 등이 JPA를 구현한 구현체

### 스프링 프레임워크의 특징
- 경량화
- 제어 반전(IoC, Inversion of Control)
    - 객체의 생성, 관계 설정, 생명주기 관리 등을 개발자가 아닌 스프링 프레임워크가 담당해서 객체 간의 결합도를 낮추고, 유연한 구조를 구축함
- 의존성 주입 (DI, Dependency Injection)
    - 객체 간의 의존성을 개발자가 직접 관리하지 않고, 스프링 프레임워크가 의존성을 관리
- 관점 지향 프로그래밍 (AOP, AspctOriented Programming)
    - 여러 객체에 공통적으로 적용될 수 있는 기능(ex. 로깅, 트랜잭션, 보안 등)을 모듈화하고, 이를 통해 코드의 재사용성과 유지보수성을 높임
- 통합, 테스트, 보안

### 스프링의 주요 패턴 3가지
1. Dependency Injection(DI, 의존성 주입)

`객체 간의 의존성을 최소화하고 유연하게 관리하기 위한 패턴`
``` java
public class UserService {
    private UserRepository userRepository;

    // 생성자를 통한 의존성 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        //userRepository를 사용하여 사용자 생성 로직 수행
        userRepository.save(user);
    }
}

위의 예시에서 보면, userRepository라는 db를 spring이 알고 있으니 
UserService라는 객체에 의존성 주입을 통해 저장할 수 있는 기능을 제공
```

2. Aspect Oriented Programming (AOP, 관점 지향 프로그래밍)

`객체 지향 개념에서의 관심사 분리를 위해 사용되는 패턴`
``` java
public interface LoggingAspect {
    // 공통 로깅 동작을 정의하는 메서드
    void logBefore(JoinPoint joinPoint);
}

@Component
public class LoggingAspectImpl implements LoggingAspect {
    // 메서드 구현
    public void logBefore(JoinPoint joinPoint) {
        // 메서드 호출 전 로깅 동작을 수행
        System.out.println("Logging before method: " + joinPoint.getSignature().getName());
    }
}
메서드를 호출하기 전에 수행함으로써 관점 분리 가능
```

3. Model-View-Controller (MVC)

`컨트롤러, 뷰, 모델의 세 가지 요소를 사용해 웹 애플리케이션을 구성`

``` java
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ModelAndView getUsers() {
        List<User> users = userService.getUsers();
        ModelAndView modelAndView = new ModelAndView("user-list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users";
    }
}
```
---

# 스프링 빌드도구
`Maven과 Gradle을 주로 지원하지만, 다른 도구인 Ant도 사용됨`

- Spring Boot 프로젝트일 경우, Gradle Wrapper 또는 Maven Wrapper를 사용해 내장 빌드 도구를 사용할 수 있음
    - Wrapper를 사용하면 프로젝트에 빌드 도구를 별도로 설치하지 않고도 프로젝트를 빌드할 수 있음

## 빌드 도구
`소스 코드의 빌드 과정을 자동으로 처리해주는 프로그램, 외부 소스코드(외부 라이브러리) 자동 추가 및 관리`

- 필요한 이유
    - 수동으로 빌드 할 경우 무엇을 빌드할지, 어떤 순서를 가지고 빌드할지, 어떤 의존성이 있는지 모두 추적하기 쉽지 않음.
    - 빌드 툴을 사용하면 해당 과정을 일관되게 할 수 있음.






## 11-CQRS 기반 데이터 통합 실습은 실습을 진행할 수 없음
