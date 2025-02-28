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
- 스프링을 습득하고 프로젝트를 시작하기 전에 프론트엔드 작업을 돕기 위한 도구가 필요하여 이를 제작하게 되었습니다.
- HTML은 구조를, CSS는 스타일을, JS는 동적 조작을 담당하도록 하여 각 영역이 서로 영향을 주지 않도록 했습니다.
- 도큐먼트 구조를 그대로 유지하기에 , 퍼블리싱만으로도 컴포넌트처럼 재사용 가능한 설계를 할 수 있도록 의도했습니다.
- 공통 기능을 별도로 구현하여 사용함으로써 생산성을 높일 수 있는 구조를 만들고자 했습니다.
- 바닐라 자바스크립트 사용 및 외부 라이브러리를 배제하여 의존성을 최소화하고자 했습니다.

## 🛠️ 사용 기술
- 기술
  - JAVA
  - JAVA SCRIPT
- 라이브러리
  - OJDBC
  - LOMBOK
  - JACKSON
  - MATTER.JS

## 🚀 슬레이트를 통해 제작한 뷰

### Board (게시판) 서비스
초기에는 Google Sheet의 엑셀을 활용하여 게시판 CRUD 기능을
대신했습니다 . App Script의 doPost 메서드를 사용하여 셀에 값을
입력하거나 , URL을 통해 엑셀 시트의 데이터를 .CSV 형식으로 가져와
게시글을 생성했습니다. 이후에는 `Winter` 서버를 사용하여 오라클
데이터베이스와 연동하였습니다.

### BM (구매자, 판매자, 오더) 서비스
구매자, 판매자, 오더 서비스를 각각 독립적으로 구현하였습니다. 구매자는
판매자 서버의 API를 통해 스토어와 스토어 아이템 정보를 획득하며, 판매자
서버는 스토어와 아이템을 등록 및 관리하고, 구매 내역을 확인합니다. 오더
서비스는 발생한 오더 내역에 대해 자세한 정보를 제공합니다.
`Winter` 서버를 사용하였습니다.

### Thomas Friends (토마스 프렌즈)
Common Module 프로젝트 중 제작했던 프론트 뷰 입니다.
학원 내 활용할 컴퓨터 자원이 많았기에, 클라우드 환경이라 간주 할 수 있는
프로젝트 고려하였고 , 중앙에서 다수의 컴퓨터 컨트롤 하기 위한 환경
구축시도를 해보았습니다.
초기에는 TCP Layer 4 계층 통신을 사용하려 했으나, 최종적으로 Layer 7 계층의
WebSocket을 활용하기로 결정했습니다. 프론트 뷰는 해당 컴퓨터의 서버를
원격으로 작동시킬 수 있는 웹 소켓 서비스를 제공합니다.

### Folio (포트폴리오 사이트)
커리큘럼 과정 중 작업한 프로젝트들의 상세 정보를
게시한 곳입니다.
각 프로젝트 소개, 이미지, 설명, 커리큘럼 발표 PPT,
프로세스 플로우, 클래스 다이어그램 , 핵심 클래스 코드
등을 포함한 다양한 설명을 포함합니다.

## 🔍 살펴보기

## 📋 시스템 아키텍처

![시스템 아키텍처 다이어그램](https://via.placeholder.com/800x500)

프로젝트의 전체 시스템 구조와 데이터 흐름을 설명합니다. 이 섹션에서는:
- 시스템 컴포넌트 간의 상호작용
- 데이터 흐름 설명
- 주요 아키텍처 패턴 및 설계 결정 사항

## 🧩 ERD (Entity Relationship Diagram)

![ERD 다이어그램](https://via.placeholder.com/800x500)

데이터베이스 구조와 엔티티 간의 관계를 설명합니다.

## 📈 성능 최적화

프로젝트에서 구현한 성능 최적화 전략과 그 결과:
- 로딩 시간 XX% 개선
- 메모리 사용량 XX% 감소
- 특정 문제 해결 방법과 성과

## 🔍 기술적 도전과 해결 방법

프로젝트 진행 중 직면한 주요 기술적 문제와 해결 방법:

### 도전 1: [문제 제목]
- **문제 상황**: 구체적인 문제 설명
- **시도한 접근법**: 다양한 시도와 실패 경험
- **최종 해결책**: 채택한 해결책과 그 이유
- **학습 포인트**: 이 과정에서 배운 점

### 도전 2: [문제 제목]
- **문제 상황**: 구체적인 문제 설명
- **시도한 접근법**: 다양한 시도와 실패 경험
- **최종 해결책**: 채택한 해결책과 그 이유
- **학습 포인트**: 이 과정에서 배운 점

## 🏗️ 프로젝트 구조

```
project-root/
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   ├── hooks/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── store/
│   │   ├── utils/
│   │   ├── App.tsx
│   │   └── index.tsx
│   ├── package.json
│   └── tsconfig.json
├── backend/
│   ├── config/
│   ├── controllers/
│   ├── middlewares/
│   ├── models/
│   ├── routes/
│   ├── services/
│   ├── utils/
│   ├── app.js
│   └── package.json
├── .github/
│   └── workflows/
├── docker/
├── docs/
└── README.md
```

## ⚙️ 설치 및 실행 방법

### 사전 요구사항
- Node.js 16.x 이상
- MongoDB
- Docker (선택사항)

### 로컬 개발 환경 설정

```bash
# 저장소 클론
git clone https://github.com/username/project-name.git
cd project-name

# 백엔드 설정
cd backend
npm install
cp .env.example .env
# .env 파일 설정 필요

# 백엔드 실행
npm run dev

# 프론트엔드 설정 (새 터미널)
cd frontend
npm install

# 프론트엔드 실행
npm start
```

### Docker로 실행

```bash
docker-compose up -d
```

## 🧪 테스트

테스트 전략과 실행 방법:

```bash
# 백엔드 테스트
cd backend
npm run test

# 프론트엔드 테스트
cd frontend
npm run test
```

## 📝 API 문서

API 엔드포인트 목록과 사용 방법:

### 사용자 API
- `GET /api/users` - 모든 사용자 조회
- `GET /api/users/:id` - 특정 사용자 조회
- `POST /api/users` - 새 사용자 생성
- `PUT /api/users/:id` - 사용자 정보 업데이트
- `DELETE /api/users/:id` - 사용자 삭제

전체 API 문서는 [여기(Swagger 링크)]()에서 확인 가능합니다.

## 🌟 핵심 기여

프로젝트에서 본인이 특별히 기여한 부분이나 구현에 대한 설명:
- 특정 알고리즘 구현
- 성능 최적화 전략
- 아키텍처 설계 결정

## 📊 프로젝트 결과

프로젝트 완료 후 달성한 결과:
- 사용자 지표 (등록 사용자, MAU 등)
- 비즈니스 성과
- 기술적 성과

## 📚 회고 및 개선점

프로젝트를 통해 배운 점과 향후 개선하고 싶은 부분:
- 기술적 측면에서의 학습
- 프로젝트 관리 측면에서의 학습
- 추후 구현하고 싶은 기능
- 리팩토링하고 싶은 코드

## 📜 라이센스

이 프로젝트는 [라이센스 이름] 라이센스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 🙏 감사의 말

프로젝트 진행에 도움을 준 사람들이나 사용한 오픈 소스 프로젝트에 대한 감사 인사.

---

## 📞 연락처

- 이메일: your.email@example.com
- 블로그: [블로그 이름](https://your-blog.com)
- 깃허브: [GitHub 프로필](https://github.com/yourusername)
