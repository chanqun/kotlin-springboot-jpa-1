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


!!! 
강의를 보면 OrderItem...이라는 가변인자를 받는 부분이 나온다

kotlin 에서는 vararg로 해결할 수 있다


현재 서비스 계층은 단순히 엔티티에 필요한 요청을 위임하는 역할을 함
이처럼 엔티티가 비지니스 로직을 가지고 객체 지향의 특성을 적극 활용하는 것을
도메인 모델 패턴이라 한다.

<-> transaction script pattern


#### 변경 감지와 병합(merge) !!!!!! 

##### 준영속 엔티티?
영속성 컨텍스트가 더이상 관리하지 않는 엔티티

> 영속성 컨텍스트가 더는 관리하지 않는 엔티티
> 디비에 가서 엔티티를 가져오는 것은 준영속 상태에 엔티티다.
> 영속성 컨텍스트가 관리하지 않기 때문에 아무리 값을 바꿔도 update가 일어나지 않는다.

해결방법
1. 변경감지 (보통 더 나은 방법)
    - findItem 을 해서 가져와서 setting 하고 set한다.
    - transaction안에서 변경해야 더티 채킹 ㅅㅅ
2. merge
    - merge하면 db에서 가져와서 파라미터 데이터를 다 set해서 동작한다.
    바꿔치기 된 것을 반환해준다.
      

#### API 개발 고급
- 조회용 샘플 데이터 입력
- 지연 로딩과 조회 성능 최적화
- 컬렉션 조회 회적화
- 페이징과 한계 돌파
- OSIV와 성능 최적화 -> open session in view 