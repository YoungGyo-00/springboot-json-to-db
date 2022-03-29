# jsontodb
Superb AI 라벨링 작업 파일을 DB에 저장하는 기능

## 개발 순서
1. `DB 연동 (MySQL)`


2. `Entity` (domain)
   * 배열을 데이터베이스에 2가지 저장법
     1. 해당 데이터를 별도의 테이블을 구성하고, 쿼리문의 조인을 통해 DTO를 구성
     2. 배열 형태의 데이터를 통째로 `String`으로 변환 후 DB에 저장. 꺼낼 때는, `String`을 파싱하여`List`


3. `Repository`
   * `DB Table`들 간의 연관 관계 확인
   * <Strong><u>객체 연관 관계랑의 차이 존재</u></Strong>
   * `@OneToMany` - `@ManyToOne`
     * `MappedBy` : (좌)
     * `@JoinColumn` : (우)


4. `Controller`
   * `JSON` -> `@RequestBody HashMap<Object, Object> params`
   * `Service`로 전송
   * `Talend API tester`로 예시 파일 전송
   

5. `Service`
   * `Gson` : `Java`에서 `JSON`을 파싱하고, 생성하기 위해 구글에서 개발한 오픈소스
     * `Gradle`
        ```
        dependencies {
          implementation `com.google.code.gson:gson:2.8.7`
        }
        ```
   
   * `JSONObject`
     * `JSON` 파일을 파싱하기 위해 JSONObject class의 `parse` Method
     * `File Reader` 객체를 전달
     * `get()` `Property` 명을 전달하여 값을 읽어옴.
   

   * `JSONArray`
     * 다건 JSON객체가 내부에 있을 떄, `JSONObject`와 반복 사용.

    
   * `JsonParser`
     * Json 내 Json 파싱