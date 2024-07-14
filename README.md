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
