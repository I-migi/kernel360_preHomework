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

`@Data`는 `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, `@RequiredArgsConstructor`를 포함합니다.

`@Builder` 애노테이션은 Lombok에서 제공하는 편리한 기능으로, 객체를 생성할 때 빌더 패턴을 사용하도록 합니다. 빌더 패턴은 특히 복잡한 객체를 생성할 때 유용합니다. 일반적으로 다음과 같은 경우에 `@Builder`를 사용합니다:

- **다수의 생성자 매개변수**: 객체를 생성할 때 많은 매개변수가 필요한 경우, 생성자 호출이 복잡해질 수 있습니다. `@Builder`는 명확하고 가독성 좋은 방식으로 객체를 생성할 수 있게 합니다.
    
    ```java
    java코드 복사
    Reply reply = Reply.builder()
        .id(1L)
        .content("This is a reply")
        .author("User123")
        .timestamp(LocalDateTime.now())
        .build();
    
    ```
    
- **불변 객체**: 객체를 불변으로 만들고자 할 때, 생성자를 통한 값을 설정한 후 변경하지 않도록 할 수 있습니다.
    
    ```java
    java코드 복사
    @Builder
    public class Reply {
        private final Long id;
        private final String content;
        private final String author;
        private final LocalDateTime timestamp;
    }
    
    ```
    
- **가독성 향상**: 빌더 패턴은 코드의 가독성을 높여줍니다. 각 필드에 값을 설정하는 메서드가 체인 형태로 연결되기 때문에 어떤 값이 설정되는지 명확하게 알 수 있습니다.
- **선택적 매개변수**: 필수 매개변수와 선택적 매개변수를 함께 사용할 때 유용합니다. 모든 필드를 다루지 않아도 필요한 값만 설정할 수 있습니다.
    
    ```java
    java코드 복사
    Reply reply = Reply.builder()
        .content("This is a reply")
        .author("User123")
        .build();
    
    ```
    

 JPA 리포지토리 인터페이스에서 제공하는 메소드 이름 규칙을 사용하면, 복잡한 쿼리를 간단하게 메소드 이름으로 표현할 수 있습니다. JPA는 메소드 이름을 해석하여 자동으로 적절한 SQL 쿼리를 생성합니다.

`findAllByPostIdAndStatusOrderByIdDesc` 메소드는 메소드 이름 규칙을 잘 따르고 있으며, 이는 JPA 리포지토리에서 지원되는 기능입니다.

`@JsonNaming` 애노테이션은 Jackson 라이브러리에서 사용하는 애노테이션으로, JSON 직렬화 및 역직렬화 시 필드 이름을 변환하는 데 사용됩니다. `PropertyNamingStrategies.SnakeCaseStrategy`를 사용하면 JSON 필드 이름이 스네이크 케이스(예: `my_field_name`)로 변환됩니다. 이 애노테이션을 클래스에 적용하면 해당 클래스의 모든 필드에 이 전략이 적용됩니다.

`@Valid`를 사용하면 Spring이 `@RequestBody`로 매핑된 객체에 대해 자동으로 유효성 검사를 수행합니다.

페이지네이션(Pagination)은 대량의 데이터를 여러 페이지로 나누어 표시하거나 처리하는 기술입니다. 이 기술은 사용자 경험을 개선하고, 대규모 데이터셋을 효율적으로 관리할 수 있도록 도와줍니다.

### 페이지네이션의 구성 요소

일반적으로 페이지네이션은 다음과 같은 구성 요소를 포함합니다:

- **페이지 크기**: 한 페이지에 표시할 항목의 수를 결정합니다. 예를 들어, 페이지당 10개의 항목을 표시할 수 있습니다.
- **페이지 번호**: 요청된 페이지의 번호를 나타냅니다. 일반적으로 첫 번째 페이지는 0이 아니라 1부터 시작합니다.
- **정렬 기준**: 데이터를 특정 기준(예: 날짜, 이름 등)으로 정렬할 수 있습니다.
- **이전 페이지, 다음 페이지 링크**: 사용자가 페이지를 이동할 수 있는 링크 또는 버튼을 제공합니다.

`Pageable`은 Spring Framework에서 제공하는 인터페이스로, 데이터를 페이지네이션(pagination)하여 조회할 때 사용됩니다. 주로 데이터베이스 쿼리나 서비스 메소드에서 페이지네이션을 구현할 때 활용됩니다.

`ageable` 인터페이스는 다음과 같은 주요 메소드를 정의하고 있습니다:

- `getPageNumber()`: 요청된 페이지의 번호를 반환합니다. 페이지 번호는 0부터 시작합니다.
- `getPageSize()`: 한 페이지에 포함된 항목의 수를 반환합니다.
- `getSort()`: 데이터를 정렬하는 데 사용되는 `Sort` 객체를 반환합니다.
- `next()`, `previous()`: 다음 페이지나 이전 페이지를 요청할 때 사용됩니다.
- `first()`, `last()`: 첫 번째 페이지나 마지막 페이지를 요청할 때 사용됩니다.
