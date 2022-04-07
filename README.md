# springboot-json-to-db
(Prototype)Superb AI 라벨링 작업 파일을 DB에 저장, 필요 컬럼만 JSON 형식으로 리턴하는 기능

## 개발 기술
* `프레임워크` : `Spring Boot`
* `데이터 저장 계층` : `JPA`, `MySQL`
* `build` : `Gradle`

## 기능
* Superb AI Image 라벨링 작업 파일에서 필요한 Value DB에 저장
* 실제 데이터가 맞게 들어갔는지 확인하는 Script
* 시험적으로 3000장 DB에 저장
  * 각 테이블의 DB Size 예상
  * 저장 후 JSON 파일로 재조회해보기
* [진행 플로우 그리기](https://github.com/YoungGyo-00/springboot-json-to-db/tree/master/artifacts/docs_managements)

## 사전 지식
* Class -> ex) Pen
* Object -> ex) Blue Pen , Red Pen 
* Instance -> ex) Blue Pen 1, Blue Pen 2 ...

## 개발 순서
### 1. `DB 연동 (MySQL)`
### - `ERD` 1차(수정)
![ERD 최조 설계](artifacts/docs_img/ERD_1차.png)


### - `ERD` 2차

![ERD 수정](artifacts/docs_img/ERD_3차_2022.04.07.png)
  * list를 DB에 저장할 경우 -> ex) `point`
    1. String으로 변환 후 저장 : 문자열이 길어지면 저장 못할 수 있음
    2. Table 하나 생성 후 Join : Join 쿼리 날리는 비용 계산
  * 최대한 테이블 하나에 몰아 넣고, 파티셔닝은 나중에 필요할 때
  * `Id`
    * 서로 계산하기 위해 선언하는 PK면, `INT`
    * 구분을 위한 PK면, `VARCHAR`로 선언하는 것이 좋음
  

### 2. `Entity` (domain)
   * 배열을 데이터베이스에 2가지 저장법
     1. 해당 데이터를 별도의 테이블을 구성하고, 쿼리문의 조인을 통해 DTO를 구성
     2. 배열 형태의 데이터를 통째로 `String`으로 변환 후 DB에 저장. 꺼낼 때는, `String`을 파싱하여`List`
   * `@Column(unique = true)`


### 3. `Repository`
   * `DB Table`들 간의 연관 관계 확인
   * <Strong><u>객체 연관 관계랑의 차이 존재</u></Strong>
   * `@OneToMany` - `@ManyToOne`
     * `MappedBy` : (좌)
     * `@JoinColumn` : (우)


### 4. `Controller`
   * `JSON` -> `@RequestBody HashMap<Object, Object> params`
   * `Service`로 전송
   * `Talend API tester`로 예시 파일 전송
   * `Folder Path` : `application.yml` 에 설정
   * `DTO` & `Domain`간의 변환 작업 시행?(`Service` 단에서 처리??)


### 5. `Service`
   * `Gson` : `Java`에서 `JSON`을 파싱하고, 생성하기 위해 구글에서 개발한 오픈소스
     * `Gradle`
        ```
        dependencies {
          implementation `com.google.code.gson:gson:2.8.7`
        }
        ```
   * `google-json-simple`
     * `JSONObject`
       * `JSON` 파일을 파싱하기 위해 JSONObject class의 `parse` Method
       * `File Reader` 객체를 전달
       * `get()` `Property` 명을 전달하여 값을 읽어옴.
     * `JSONArray`
       * 다건 JSON객체가 내부에 있을 떄, `JSONObject`와 반복 사용.
     * `JsonParser`
       * Json 내 Json 파싱

   * `Files.walk(path)`
     * path 에는 String이 아닌 `Path` 인터페이스 객체를 파라미터로 받음
     * `Path` 객체는 `Paths.get(String)`으로 얻을 수 있음
     * 

   * `Double`형 `JSON Value` `Int` 형으로 파싱해서 저장
     1. `String` : 캐스팅 변환이 아닌 `String.valueof`로 형변환
     2. `Double` : 실수형으로 변환
     3. `Math.round()` : 소수점 반올림
     4. `Int` : 캐스팅 정수형 변환


### 6. 예외 처리
* `SQLIntegrityConstraintViolationException` : DB에러에 대해 Exception 처리 불가능 -> `DataIntegrityViolationException`
* `Transactional Rollback` : `@Transactional`이 걸려 있는 메서드 내부에서의 오류
  * `UnexpectedRollbackException` : DB에 값이 저장되지 않는 현상


### 7. `Swagger UI 연동`


### 8. DB 저장 용량 예상
* `Row` 당 `Size` 구하기 (20 폴더 기준 - 3076 files)
  * `project`
    * time : 1.3s   =  4분 예상
    * size : 0.094 MB  =  16.5 MB
  * `meta` 
    * time : 1m 3s   =  3시간 예상
    * size : 0.766 MB   = 134 MB
  * `object`
    * time : 4m 45s   =  14시간 예상
    * size : 10.578 MB  = 1.8 GB ?_?


* `analyze table ` : 인덱스를 재생성하여 성능을 최적화


### 9. `DTO`
* Data Transfer Object
* Controller -> Service -> Repository 간의 통신
* Service 계층에서 사용되는 잠재적인 설계 요소


* `Jackson Property` 
  * `@JsonIgnore` : `property`는 return 되지 않는다
  * `@JsonInclude` 
    * `ALWAYS` : 속성 값에 의존하지 말고 항상
    * `NOT_EMPTY` : null 또는 값이 빈 경우가 아니면 포함
    * `NOT_NULL` : null 아니면 포함
    * `NOT_DEFAULT` : `bean`의 기본 생성자로 정의된 필드값과 다르게 변경된 필드만
  * `@JsonProperty` : class의 property name을 바꿀 때


* `GSON`
  * Class를 직접 만들지 않고, 직접 JSON을 만들어 전송


* `@ResponseBody`
  * View Page가 아닌 반환값 그대로 클라이언트한테 return 하고 싶을 때


* `MapStruct`
  * 엔티티와 DTO 간에 변환할 때 자동 매핑 라이브러리
  * `ModelMapper`보다 매핑 속도가 빠름
  
### 10. `Unit Test`
* `JUint` : 테스트 도구
* `@SpringBootTest` : 실제 DB와 connection 진행되는 Live Test 방법
* `Service`에서 JSON Value가 DB에 정확하게 들어갔는지 확인
* `Mockmvc`
* <u>실행 오류</u>
  * [java.lang.IllegalStateException: Failed to retrieve PlatformTransactionManager for @Transactional test](https://velog.io/@hanblueblue/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B83-4.-%EB%B2%84%EA%B7%B8-%EC%88%98%EC%A0%95)
  * [@WebMvcTest 단위 테스트 시 Bean 주입 에러](https://velog.io/@gillog/SpringBoot-WebMvcTest-%EB%8B%A8%EC%9C%84-%ED%85%8C%EC%8A%A4%ED%8A%B8%EC%8B%9C-Bean-%EC%A3%BC%EC%9E%85-%EC%97%90%EB%9F%AC)

## API 명세서
[Notion - 크라우드 소싱 웹 개발 API도 포함](https://shade-sled-bf2.notion.site/API-b11de231685246b49f97d96a4e6887da)

## Agile Scrum
[Notion - Agile Scrum (1) - 2022.03.04](https://shade-sled-bf2.notion.site/Agile-Scrum-1-2022-03-04-ad266c5dedfd433da88c26556c9ea876)
[Notion - Agile Scrum (2) - 2022.04.01](https://shade-sled-bf2.notion.site/Agile-Scrum-2-2022-04-01-a1fcfb323b9a4ce8acb89a00d7566899)
[Notion - Agile Scrum (3) - 2022.04.06](https://shade-sled-bf2.notion.site/Agile-Scrum-2-2022-04-06-8e472c03f070477fbe957e4d00634c51)

## 높고 높은 벽..
* Spring JPA Auto DDL generation을 실행하면, 알파벳 순서로 정렬..
  * DB Column 순서 또한 중요한 점을 고려하여, 문제점으로 분류
  * JPA를 사용하면 하이버네이트가 자동으로 테이블 생성
  * 이는 실제 배포 단계에서 위험
  * <Strong>개념과 해결 방법 서치..</Strong>
    * `DDL generation`
      1. `@Entity` 클래스 찾기
      2. `spring.jpa.generate-ddl=true` 옵션 적용시 자동 생성
    * `SQL script`
      * Spring 기본값으로 classpath 루트에 `schema.sql`파일이 있다면, 서버 시작시 스크립트를 실행
      * `schema.sql` -> DDL spcript 명시
      * `data.sql` -> Data를 위한 DML 명시
      * JPA 기반의 애플리케이션을 만든다면, `schema.sql` or `hibernate.ddl-auto` 둘 중 하나만
      * `schema.sql` -> db 폴더 생성 후 저장
    * profile 파일 분리
      * 개발 단계에서 dev profile 활성화, 배포할 때는 real profile 활성화
      * 이는 `application.yml`에서 관리


* List 합치기.. Stream으로 List 합치기.. list 안에 list 있을 때.. => Controlle에서 함수 재사용하기 위함..
  1. addAll() -> list 합치기
  2. concat() -> stream 합치기
  3. flatMap() -> list 안에 list 합치기
  
## 3000개 파일 저장 시에 발생한 예외 상황
* Object property value 가 `empty` or `null` 인 경우가 존재함
    * `empty` -> -1 로 일단 대체
    * `null`  -> -2 로 일단 대체
  
* Meta 30XX A30049099001TNV030O001LV.jpg 값이 안 들어가 있어서 오류
* `javax.persistence.NonUniqueResultException: query did not return a unique result: 2`
  * JPA Repository 쿼리 값이 1개가 아닌 복수 개가 넘어오는 경우
  * limit 설정으로 첫 번째 값만 불러오기로 일단은 해결

## 참고 자료
* [PK int형 vs varchar형 1 - stackoverflow](https://stackoverflow.com/questions/2103322/varchar-as-foreign-key-primary-key-in-database-good-or-bad%20)
* [PK int형 vs varchar형 2 - stackoverflow](https://stackoverflow.com/questions/3162202/sql-primary-key-integer-vs-varchar)
* [Folder Files 가져오기](https://codechacha.com/ko/java-list-files/)
* [Array in MySQL Databases - stackoverflow](https://stackoverflow.com/questions/10686333/save-array-in-mysql-database/32664559)
* [MySQL TEXT vs BLOB vs CLOB 성능 관점, 사용성 관점](https://stackoverflow.com/questions/7071662/mysql-text-vs-blob-vs-clob)
* [DB Row Size 구하는 쿼리](https://wizardee.tistory.com/146)
* [Analyze-Table doc](https://dev.mysql.com/doc/refman/5.6/en/analyze-table.html)
* [DTO의 사용 범위에 대하여](https://xlffm3.github.io/spring%20&%20spring%20boot/DTOLayer/)
* [DTO는 어느 레이어까지 사용하는 것이 맞을까?](https://www.slipp.net/questions/93)
* [Entity To DTO Conversion for a Spring REST API](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)
* [스프링 부트 통합테스트 방법과 팁](https://galid1.tistory.com/735)
* [MapStruct 사용 방법](https://kth990303.tistory.com/131)
* [MapStruct DTO 안에 DTO](https://ryumodrn.tistory.com/26)
* [MapStruct 1.4.2 guide PDF](https://mapstruct.org/documentation/stable/reference/pdf/mapstruct-reference-guide.pdf)
* [높고 높은 벽 JPA Auto DDL 설정 시 참고했던 자료 1](https://pravusid.kr/java/2018/10/10/spring-database-initialization.html)
* [높고 높은 벽 JPA Auto DDL 설정 시 참고했던 자료 2](https://leveloper.tistory.com/38)
* [높고 높은 벽 JPA 데이터베이스 초기화 doc](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization)
* [특정 경로의 하위 파일, 폴더 탐색(File.walk)](https://codechacha.com/ko/java-traverse-directory/)
* [Stream으로 list 합치기](https://jekal82.tistory.com/60)