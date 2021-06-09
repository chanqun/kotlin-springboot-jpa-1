### springboot-jpa-kotlin

#### 프로젝트 환경설정


#### 주의점
- Setter를 사용하지 말고 사용하는 method를 만들자
- 모든 연관관계는 LAZY로 진행 
    lazy 로딩 exception -> fetch join 으로 한다.
- list 초기화 언제 할까? 하이버네이트는 엔티티를 영속화 할 때 컬렉션을 감싸서 하이버네이트카
제공하는 내장 컬렉션으로 변경한다.

테이블 camel case -> underscore case로 바꿈

- cascade = CascadeType.ALL 종속되어 저장 삭제 가능