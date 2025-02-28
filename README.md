# 슬레이트 & 윈터
<img src="https://gtypeid.github.io/resource/path/folio/logo-0.png" width="400" alt="프로젝트 로고">

## 📝 프로젝트 개요
### Slate
프론트 뷰 동적 제작을 도와주는 구조를 설계하고 싶었습니다.
바닐라 본연의 HTML, CSS, JavaScript로 존재하는 것이 특징입니다.
HTML, CSS, JavaScript를 하나로 묶어 위젯이라 부르며 렌더링합니다.
위젯으로 관리되는 동적 구조는 재사용성을 증가시키며 객체로서 관리됩니다.
또한 다수의 컴포넌트를 통해 Slate 내에서 생산성을 확장시킬 수 있습니다.

### Winter
스프링의 생명주기를 참고한 Fake Spring 입니다.
java.sun.net.httpserver 모듈을 사용하여, 단순한 Http Server를 만들어 보았습니다.
진입 라우팅을 Controller라 부르며 공통 로직을 숨겨 사용자는 비즈니스 로직에 집중하도록 구조화하였습니다.
Low Level단의 DB Connection의 반복되는 코드 또한 공통 처리 하여,
쿼리 전환 값 객체 매핑등의 프로세스를 자동화해보았습니다.

## 🧩 프로젝트 의도 및 이점
### 슬레이트
- HTML, CSS, JS를 하나로 묶어
위젯이라 부르며 도큐먼트를
조작하여 위젯을 렌더링 합니다.
- 스프링을 습득하고 프로젝트를 시작하기 전에 프론트엔드 작업을 돕기 위한 도구가 필요하여 이를 제작하게 되었습니다.
- HTML은 구조를, CSS는 스타일을, JS는 동적 조작을 담당하도록 하여 각 영역이 서로 영향을 주지 않도록 했습니다.
- 도큐먼트 구조를 그대로 유지하기에 , 퍼블리싱만으로도 컴포넌트처럼 재사용 가능한 설계를 할 수 있도록 의도했습니다.
- 공통 기능을 별도로 구현하여 사용함으로써 생산성을 높일 수 있는 구조를 만들고자 했습니다.
- 바닐라 자바스크립트 사용 및 외부 라이브러리를 배제하여 의존성을 최소화하고자 했습니다.

### 윈터
- 스프링의 핸들러, 컨트롤러, 리졸버
흐름을 참고하여, Java의 HTTP 모듈로
제작한 서버입니다.
- Slate를 제작한 후, Google Sheet의 AppScript를 이용해 CRUD 기능을 구현하고 있었습니다. 이후 간단한 HTTP 서버를 찾고 있던 중
Java HTTP 모듈을 알게 되었습니다. 이후 스프링을 배우고 프로젝트를 시작하기 전에 스프링의 흐름을 이해하기 위해 직접
제작해보았습니다.
- 핸들러, 컨트롤러, 리졸버의 구조를 활용하여 진입점, 비즈니스 로직 확장 영역, 반환 처리 로직을 구분하여 개발했습니다. 공통
처리 영역을 컨트롤러 상단에 배치하여 스프링의 의도처럼 사용자가 추상화된 구조에서 비즈니스 로직을 쉽게 확장할 수
있도록 구조화 해보았습니다.

## 🛠️ 사용 기술
- 기술
  - JAVA
  - JAVASCRIPT
- 라이브러리
  - OJDBC
  - LOMBOK
  - JACKSON
  - MATTER.JS

## 🚀 슬레이트를 통해 제작한 뷰

### Board (게시판) 서비스
<img src="https://github.com/user-attachments/assets/6a209c0f-1c73-4782-848d-4a3789bb413b" width="500" alt="프로젝트 로고"><br><br>
- 초기에는 Google Sheet의 엑셀을 활용하여 게시판 CRUD 기능을
대신했습니다 . App Script의 doPost 메서드를 사용하여 셀에 값을
입력하거나 , URL을 통해 엑셀 시트의 데이터를 .CSV 형식으로 가져와
게시글을 생성했습니다. 이후에는 `Winter` 서버를 사용하여 오라클
데이터베이스와 연동하였습니다.
<br><br>

### BM (구매자, 판매자, 오더) 서비스
<img src="https://github.com/user-attachments/assets/1d2abf0d-b06c-42d9-86bc-414c68837229" width="500" alt="프로젝트 로고"><br><br>
- 구매자, 판매자, 오더 서비스를 각각 독립적으로 구현하였습니다. 구매자는
판매자 서버의 API를 통해 스토어와 스토어 아이템 정보를 획득하며, 판매자
서버는 스토어와 아이템을 등록 및 관리하고, 구매 내역을 확인합니다. 오더
서비스는 발생한 오더 내역에 대해 자세한 정보를 제공합니다.
`Winter` 서버를 사용하였습니다.
<br><br>

### Thomas Friends (토마스 프렌즈)
<img src="https://github.com/user-attachments/assets/79878957-86de-4c0a-9b94-85eb6861c9c7" width="500" alt="프로젝트 로고"><br><br>
- Common Module 프로젝트 중 제작했던 프론트 뷰 입니다.
학원 내 활용할 컴퓨터 자원이 많았기에, 클라우드 환경이라 간주 할 수 있는
프로젝트 고려하였고 , 중앙에서 다수의 컴퓨터 컨트롤 하기 위한 환경
구축시도를 해보았습니다.
초기에는 TCP Layer 4 계층 통신을 사용하려 했으나, 최종적으로 Layer 7 계층의
WebSocket을 활용하기로 결정했습니다. 프론트 뷰는 해당 컴퓨터의 서버를
원격으로 작동시킬 수 있는 웹 소켓 서비스를 제공합니다.
<br><br>

### Folio (포트폴리오 사이트)
<img src="https://github.com/user-attachments/assets/b3babdef-0bdd-4610-849c-7d70d4b4cacd" width="500" alt="프로젝트 로고"><br><br>
- 커리큘럼 과정 중 작업한 프로젝트들의 상세 정보를
게시한 곳입니다.
각 프로젝트 소개, 이미지, 설명, 커리큘럼 발표 PPT,
프로세스 플로우, 클래스 다이어그램 , 핵심 클래스 코드
등을 포함한 다양한 설명을 포함합니다.
<br><br>

## 🔍 슬레이트 살펴보기
### Slate 시연
<img src="https://gtypeid.github.io/resource/path/folio/board-0.gif" width="500" alt="프로젝트 로고"><br><br>
- 로그인 및 게시판 상세보기, 코멘트 작성
RestBinder를 통해 스크롤시 Winter API를 요청하여
게시글을 동적으로 생성하는 모습입니다.
<br><br>

<img src="https://gtypeid.github.io/resource/path/folio/board-2.png" width="500" alt="프로젝트 로고"><br><br>
- 회원가입 및 로그인을 통한
Winter 서버로부터 받은 응답 입니다.
<br><br>

<img src="https://gtypeid.github.io/resource/path/folio/board-3.png" width="500" alt="프로젝트 로고"><br><br>
- 게시글 및 파일을 업로드 합니다.
파일을 Blob화 하여 DB에 저장합니다
Winter 서버로부터 받은 응답 입니다.
<br><br>

<img src="https://gtypeid.github.io/resource/path/folio/board-6.png" width="500" alt="프로젝트 로고"><br><br>
- 게시글 위젯입니다. HTML, CSS, JS
이하 셋 리소스를 묶어 하나로 랜더링합니다.
<br><br>

### BM 시연
<img src="https://gtypeid.github.io/resource/path/folio/bm-0.gif" width="300" alt="프로젝트 로고"><br><br>
- 구매자 서비스 입니다. 인트로 및 로그인을 이후
스토어 카테고리를 Winter 서버에 요청 합니다.
스토어로 등록된 판매자, 메뉴를 구매하는 모습입니다.
<br><br>

<img src="https://gtypeid.github.io/resource/path/folio/bm-1.png" width="500" alt="프로젝트 로고"><br><br>
- 구매자, 판매자, 오더, 각각의 서비스로 존재하는 모습입니다.
<br><br>

<img src="https://gtypeid.github.io/resource/path/folio/bm-2.png" width="300" alt="프로젝트 로고"><br><br>
- 로그인 화면, 로그인 위젯 입니다.
<br><br>

<img src="https://gtypeid.github.io/resource/path/folio/bm-3.png" width="500" alt="프로젝트 로고"><br><br>
- 카테고리 선택 및 스토어 리스트를
판매자 서버로부터 응답받은 모습입니다.
<br><br>

## 🔍 윈터 살펴보기
### 윈터
<img src="https://github.com/user-attachments/assets/f30749db-4c1a-4164-bc3d-6dd330cd5c70" width="600" alt="프로젝트 로고"><br><br>
<img src="https://github.com/user-attachments/assets/7631c2f8-8f73-4f49-a599-b6f431e30f5a" width="600" alt="프로젝트 로고"><br><br>

## 🏗️ 슬레이트 구현 위젯

<img src="https://github.com/user-attachments/assets/5838ef0b-3646-47fd-8a32-f7a6f9ae1d60" width="200" alt="프로젝트 로고"><br>
- Board ( 게시판 ) : 7개 위젯
<br><br>

<img src="https://github.com/user-attachments/assets/7f5d84d2-c7b6-4f3f-aa14-5fa8bf1b70c3" width="200" alt="프로젝트 로고"><br>
- BM ( 구매자, 판매자, 오더 ) : 17개 위젯
<br><br>

<img src="https://github.com/user-attachments/assets/e449c2c7-84c4-47ad-864d-1677c1a40d7b" width="200" alt="프로젝트 로고"><br>
- Thomas ( 토마스 ) : 11개 위젯
<br><br>

<img src="https://github.com/user-attachments/assets/f5520270-9263-4829-9438-0f528d71a6fc" width="200" alt="프로젝트 로고"><br>
- Folio ( 포트폴리오 사이트 ) : 12개 위젯
<br><br>

## 📋 슬레이트 구현 기능 

### 핵심 기능
- 위젯 생성, 삭제, 도큐먼트 조작
- CSS 스타일 통합 : 다수의 선택자 스타일을 한 개의 스타일 시트에 통합시킵니다.
- 클래스 이름 조작 : 클래스 이름 중복을 피하기 위해 기존 클래스 이름을 도큐먼트 엔진 진입 시 변화시킵니다.
- 캐싱 : HTML, CSS, JS로드시 캐싱 처리 하여, 리소스 중복 및 반응성을 향상시킵니다.

### 공통기능
- 공통 기능 ( 컴포넌트 )
- DocEventHandler : 도큐먼트 내의 이벤트 부착 및 관리를 담당 합니다.
- WindowPanel : 위젯에 이동, 병합, 활성화 기능을 가진 윈도우 패널을 부착합니다.
- Highlighter : 콘텐츠 내의 H1, H2, H3 태그를 읽어 이를 기반으로 목차를 생성하거나 콘텐츠를 정렬합니다.
- ImgDragBox : 이미지 파일 사이즈를 확인 및 제한하며, 이를 BLOB(Binary Large Object) 형식으로 변환 서버와
송수신합니다.
- RestBinder : 엔드포인트 및 메소드 타입을 정의하여 서버의 API를 호출합니다. 또한 요청 흐름의 판단을
클라이언트에 맡길 수도 있습니다. 예를 들어, 클라이언트가 JSON 리스트를 받은 경우, 현재 사용될 아이템 수와
비교하여 서버에 다시 요청할지, 아니면 클라이언트의 저장소에 있는 JSON 데이터를 소모할지 판단할 수 있습니다.
인피니티 스크롤처럼 서버에서 JSON 리스트를 가져오는 경우, RestBinder를 사용하면 불필요한 서버 호출을 줄일 수
있습니다.
- EntityGenerator : JSON 리스트를 사용하여 Entity(예: Widget)들을 동적으로 생성합니다. 각 Entity는 JSON 데이터를
주입받아 생성되며, 이 주입된 데이터를 통해 프로퍼티를 손쉽게 조작할 수 있습니다. 이를 통해, RestBinder와
EntityGenerator의 조합을 사용하면 API 호출에 의한 동적 생성 작업을 간편하게 구현할 수 있습니다.

## 📋 윈터 구현 기능 
### 공통 기능
- AppConfig : Winter App의 설정을 정의합니다 . PORT번호 및 JDBC 데이터 소스의 접속 설정 등을 정의합니다.
또한 해당 App의 컨트롤러들을 불러옵니다.
- DataBase : 오라클 데이터 소스와 연결하여 애플리케이션 설정을 통해 DB 테이블이나 시퀀스 등을 생성하거나
삭제할 수 있습니다. 개발 및 테스트 속도를 높이는 데 도움을 줍니다. 또한, JDBC를 사용할 때 자주 반복되는 코드
(예: 연결 설정, 쿼리 실행, 결과 매핑 등)를 Binder라는 인터페이스와 묶어 공통화하여 사용합니다. 이를 통해
사용자는 SQL에 객체를 직접 삽입하거나 , 객체를 반환받는 등의 작업을 자동화할 수 있습니다.

### 컨트롤러
- URL 라우팅 등록
- 메소드 매핑: 컨트롤러 인터페이스에 등록된 GET, POST 요청에 따라 적합한 메소드를 호출합니다.
- pathVariable (경로 변수)
- HTTP 바디 데이터 파싱
- 응답 상태 코드
- 에러 페이지
- 정적 리소스 반환 : 클라이언트 요청에 따라 HTTP 콘텐츠 타입(HTML, CSS, JS 등)을 확인하고,
해당 파일을 읽어 반환합니다 . ( 컨트롤러 리턴 타입이 문자열 시 )
- 객체, 객체 리스트 JSON 직렬화 반환 ( 컨트롤러 리턴 타입이 객체 및 객체 리스트 시 )
- BLOB 대용량 데이터 반환 ( 컨트롤러 리턴 타입이 BLOB 타입 시 )
- 필터 : 요청 응답에 대하여 VO를 필터링 합니다.예를들어 유저 객체의 패스워드 필드를 제거하여 직렬화 합니다.

### 구현 API
- Board ( 게시판 ) APP
  - 유저 로그인, 회원가입
  - 다수, 특정 게시판 조회 & 게시판 등록, 삭제
  - 다수 코멘트 조회 & 코멘트 등록
  - 특정 유저 조회
  - 프로필 조회, 등록
  - 파일 조회, 등록
- BM ( 구매자 ) APP
  - 구매자 회원가입 & 로그인
  - 특정 구매자 조회
- BM ( 판매자 ) APP
  - 판매자 로그인, 회원가입
  - 다수 스토어 조회 & 스토어 추가
  - 다수 스토어 메뉴 조회 & 스토어 메뉴 추가
  - 메뉴, 로고 이미지 업로드
  - 파일 조회
- BM ( 오더 ) APP
  - 관리자 로그인
  - 다수, 특정 오더 조회
  - 오더 추가, 오더 승인

## 📊 슬레이트 프로젝트 회고
### 좋았던 점
- 과거에 HTML,CSS를 통해 웹을 제작해보는 웹에디터를 제작시도 해본 적이 있습니다. 그 아이디어를 차용 프론트
뷰를 제작해보는 프로젝트를 시작해보았고 , 단순히 에디터에서만 활용 가능한 구조가 아니라 도큐먼트 구조를
그대로 활용하는 방식으로 탈피하여, 보다 유용한 관리 방식을 적용할 수 있었습니다.
- 다수의 위젯을 생성, 그 위젯을 관리하는 컨테이너 구조를 적극 차용해보았으며 , 최소한의 기능을 가진 위젯들이
많아 수정사항에 쉽게 대응하였습니다.

### 아쉬운 점
- 최초 설계 당시 위젯 내의 또 다른 위젯 즉, 깊이 구조를 고려하지 않았기에 위젯을 생성하는 메소드
흐름이 두 개로 나뉘어진게 아쉽습니다 . ( 문서를 읽어 초기에 위젯을 생성하는 : spawnWidget,
런타임 중 동적으로 위젯을 생성하는 : runTimeSpawnWidget)
- 다수의 CSS를 통합할 때, 단순한 정규식을 사용하여 키 값 매칭 형태의 CSS 스타일만 적용할 수 있습니다. 복잡한
선택자나 여러 클래스 스타일을 사용을 고려하지 못한 점이 아쉽습니다.

## 📊 윈터 프로젝트 회고
### 좋았던 점
- 스프링 프로젝트를 시작하기 전에, 스프링의 핸들러, 컨트롤러, 리졸버 흐름을 따라한 서버를 Java로 구현하고,
직접 제작한 프론트엔드와 JSON 통신을 통해 서비스를 완성한 경험이 좋았습니다.
- HTML문서를 사용자에게 반환하려면 어떻게 해야 할까?, 직접 태그를 작성해 전달하나? ,
톰캣 JSP는 어떻지? 같은 방향 등을 고민해보고 , 파일을 읽어 HTTP 헤더 내용에 기반
정적 파일을 반환하는 작업들을 직접 구현해본 것이 흥미로웠습니다.
- 사용자가 매핑해야 할 VO, 혹은 반복되어 작성되는 코드를 한 곳에 공통화 처리에 대해 고민하였습니다.

### 아쉬운 점
- 핸들러, 컨트롤러, 리졸버의 로직 구현에만 시도해보자는 경직된 생각으로 컨트롤러 내부 클래스에
메소드로만 구분해 처리한 점 입니다.
- 마이바티스라는 것을 나중에 알게 되었습니다. 현재는 SQL이 자바 코드에 하드 코딩되어 있지만, SQL을 외부 XML
파일로 매핑하여 관리하는 구조로 제작 했다면 추가적인 빌드 작업에 유연해질 것이였다고 생각합니다.

### 문제 발생 및 해결
- Blob 객체를 동적할당 생성후 PreparedStatement에 바인딩 시 SQL.Blob가 아닌 Oracle.BLOB으로 강제로
캐스팅되어 에러가 나던 상황이 있었습니다. 그래서 Oracle.BLOB을 사용을 고려하였으나 ,
해당 클래스는 더 이상 사용되지 않음을 알게 되었습니다. 해결은 커넥션 연결후(conn) 커넥션 객체를 통해
createBlob메소드를 이용해 생성, 생성된 blob에 읽어온 바이트 설정 및, PreparedStatement에 파라미터 바인딩을 통해
ORACLE.BLOB의 강제 캐스팅 문제를 해결하였습니다.
- Blob 파일을 가져오는 과정에서 문제가 발생한 적이 있습니다. 에러를 분석한 결과 원인은 DB 연결, 파라미터 바인딩,
리소스 해제 등을 자동화하여 finally 블록에서 리소스를 close하는 구조에서 대용량 파일을 처리할 때 충분한 연결 시간이
보장되지 않았기 때문이라고 추론했습니다. 이를 해결하기 위해 sqlQueryNoneClose와 같이 리소스를 자동으로 해제하지
않는 흐름을 별도로 만들고, CompleteResponse 인터페이스를 추가하여 컨트롤러 상단에서 리소스 해제를 담당하도록
로직을 개선했습니다

## 📈 성능 최적화

## 🔍 기술적 도전과 해결 방법

## ⚙️ 설치 및 실행 방법

## 🧪 테스트

## 📝 API 문서

## 🌟 핵심 기여

## 📚 회고 및 개선점

## 📜 라이센스

## 🙏 감사의 말

## 📞 연락처

- 이메일: your.email@example.com
- 블로그: [블로그 이름](https://your-blog.com)
- 깃허브: [GitHub 프로필](https://github.com/yourusername)
