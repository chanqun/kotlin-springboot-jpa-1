### springboot-jpa-kotlin

#### 프로젝트 환경설정


#### 주의점 애플리케이션 구현 준비
- Setter를 사용하지 말고 사용하는 method를 만들자
- 모든 연관관계는 LAZY로 진행 
    lazy 로딩 exception -> fetch join 으로 한다.
- list 초기화 언제 할까? 하이버네이트는 엔티티를 영속화 할 때 컬렉션을 감싸서 하이버네이트카
제공하는 내장 컬렉션으로 변경한다.

테이블 camel case -> underscore case로 바꿈

- cascade = CascadeType.ALL 종속되어 저장 삭제 가능


회원 도메인
상품 도메인
주문 도메인

#### 회원 도메인 개발 참고할점
class @Repository에 등록하고

@PersistenceContext
private EntityManager em;

- Autowired, setter, constructor injection을 사용할 수 있다.
 --> 필드는 바꾸기 힘듬
  -> setter은 런타임에서 바뀌면 큰일남
  -> consturctor 은 컴파일 시점에 잡을 수 있음
  

@AllArgsConstructor 모든 필드를 가지고 생성자를 만든다.
@RequiredArgsConstructor은 final만 가지고 생성한다.


#### 상품 도메인 개발

#### 주문 도메인 개발
상품 주문, 주문 내역 조회, 주문 취소
