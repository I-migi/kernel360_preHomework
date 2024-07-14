# kernel360_preHomework
kernel360 사전과제

### REST API 개론

자원의 상태 전달.

1. 클라이언트와 서버가 서로 독립적으로 분리
2. 요청에 대해서 클라이언트의 상태가 서버에 저장 X
3. 클라이언트는 서버의 응답을 캐시 할 수 있어야 한다 > 서버의 부하를 낮춘다
4. 서버와 클라이언트 사이에, 방화벽, 게이트웨이, Proxy 등 다계층 형태를 구성할 수 있어야 하며, 확장 할 수 있어야 한다
5. 인터페이스 일관성
6. Code On Demand

인터페이스 일관성: REST를 잘 사용했는지 판단 할 수 있다

자원식별; 웹 기반의 REST에서는 리소스 접근을 URL로 사용

URI 설계

URI: 인터넷에서 특정 자원을 나타내는 주소값. 해당 값은 유일

URL: 인터넷상에서의 자원, 특정 파일이 어디에 위치하는지 식별 하는 주소

URL은 URI의 하위 개념

Spring Boot는 단순히 실행되며, 프로덕션 제품 수준의 스프링 기반 어플리케이션을 쉽게 만들 수 있다.

Spring Boot 어플리케이션에는 Spring 구성이 거의 필요하지 않다

@Controller 은 html을 리턴할 때 사용하는데 보통 백엔드 개발자는 api를 만들 때 객체를 반환하기 때문에 @RestController를 많이 사용하고 Controller를 사용하고 객체를 리턴할 때에는 @ResponseBody를 사용해줘야 한다.

잭슨 라이브러리의 동작방식

objectMapper는 변수가 아니라 메소드에 직렬처리를 해준다

제너럴 타입  Api<T>

클래스나 메소드가 다양한 데이터 타입을 처리할 수 있도록 하는 기능

`@Slf4j@RestControllerAdvice@Order(value = Integer.*MAX_VALUE*)public class GlobalExceptionHandler {    @ExceptionHandler(value = {Exception.class})    public ResponseEntity<Api> exception(Exception e){        *log*.error("",e);        var response = Api.*builder*()                .resultCode(String.*valueOf*(HttpStatus.*INTERNAL_SERVER_ERROR*.value()))                .resultMessage(HttpStatus.*INTERNAL_SERVER_ERROR*.getReasonPhrase())                .build();        return ResponseEntity                .*status*(HttpStatus.*INTERNAL_SERVER_ERROR*)                .body(response);    }}`

ExceptionHandler > 패키지에서 오류가 나면 모두 Handler를 통해서 해결된다.

@RestControllerAdvice > 전역적으로 모든 컨트롤러에서 발생하는 예외를 처리해주는 클래스를 나타낸다

@Order(vallue = Integer.MAX_VALUE) > 처리 순서를 결정하며 숫자가 높을수록 우선순위가 높다.

`@ExceptionHandler(value = {Exception.class})` class를 지정해줄 수도 있다

---

@RequestParam:  url에 ?category=book 이런식으로 파라미터를 전송하는 방식

@RequestBody: JSON데이터를 받아서 사용하는 방식

---

validation

- `@Controller`:
    - 주로 HTML 페이지를 반환하는 전통적인 Spring MVC 컨트롤러.
    - 뷰 리졸버를 통해 동적인 웹 페이지를 생성.
    - 뷰 이름을 반환하고 `Model` 객체를 사용하여 데이터를 전달.
- `@RestController`:
    - 주로 RESTful 웹 서비스를 제공하기 위한 컨트롤러.
    - JSON, XML 등 데이터를 직접 HTTP 응답 본문에 반환.
    - `@ResponseBody`가 자동으로 적용됨.
@OneToMany( // JPA 어노테이션으로, 일대다 관계를 나타냅니다. 하나의 BoardEntity가 여러 PostEntity를 가질 수 있습니다.
mappedBy = "boardEntity" // 관계의 주인이 아님을 나타내며, 외래 키는 PostEntity에 있습니다.

)

- **타입 추론 가능해야 함**:
`var`를 사용할 때 컴파일러가 타입을 명확히 추론할 수 있어야 합니다. 초기화 없이 변수를 선언할 수 없습니다.
    
    ```java
    java코드 복사
    var number; // 컴파일 오류: 타입을 추론할 수 없음
    number = 10;
    
    ```
    
- **로컬 변수에만 사용 가능**:
클래스 필드, 메소드 매개변수, 리턴 타입으로 사용할 수 없습니다.
    
    ```java
    java코드 복사
    public class Example {
        private var field; // 컴파일 오류: 클래스 필드에는 사용 불가
    
        public void method(var param) { // 컴파일 오류: 메소드 매개변수에는 사용 불가
        }
    
        public var method() { // 컴파일 오류: 리턴 타입으로 사용 불가
            return "Hello";
        }
    }
    
    ```
    
- **명확한 타입 추론**:
`var`를 사용할 때는 타입이 명확히 추론되는 경우에만 사용해야 합니다.
    
    ```java
    java코드 복사
    var list = new ArrayList<>(); // 컴파일러는 ArrayList<Object>로 추론
    list.add("Hello");
    list.add(10); // 문제가 될 수 있음
    
    ```
    

`var`는 코드의 가독성을 높이고, 반복되는 타입 선언을 줄여줍니다. 하지만, 코드의 명확성을 유지하기 위해서는 타입 추론이 명확한 경우에만 사용하는 것이 좋습니다. 복잡한 타입을 추론할 때는 오히려 가독성이 떨어질 수 있으므로 주의해야 합니다.

`@JoinColumn`은 JPA(Java Persistence API)에서 사용되는 어노테이션으로, 엔티티 간의 관계를 정의할 때 사용됩니다. 주로 외래 키(Foreign Key)를 지정하기 위해 사용됩니다. `@JoinColumn` 어노테이션은 데이터베이스 테이블에서 두 테이블 간의 조인을 정의하는 데 필요한 정보를 제공합니다.
`@JoinColumn`은 보통 `@ManyToOne`, `@OneToOne`, `@OneToMany`, `@ManyToMany`와 같은 관계 어노테이션과 함께 사용됩니다.

외래 키(Foreign Key)는 데이터베이스에서 두 테이블 간의 관계를 정의하는 키입니다. 외래 키는 한 테이블의 컬럼이 다른 테이블의 기본 키(Primary Key)를 참조하도록 설정됩니다. 이를 통해 두 테이블 간의 연결을 만들고 데이터 무결성을 유지합니다.

### 예제 설명

두 개의 테이블, `Board`와 `Post`가 있다고 가정해봅시다. 각 테이블은 다음과 같은 구조를 가집니다.

### Board 테이블

| id | board_name |
| --- | --- |
| 1 | General |
| 2 | News |
| 3 | Sports |

### Post 테이블

| id | title | board_id |
| --- | --- | --- |
| 1 | Hello World | 1 |
| 2 | Java News | 2 |
| 3 | Sports News | 3 |

위의 `Post` 테이블에서 `board_id` 컬럼이 `Board` 테이블의 `id` 컬럼을 참조하는 외래 키입니다. 이를 통해 `Post` 테이블의 각 행이 어떤 `Board`에 속하는지를 나타낼 수 있습니다.

