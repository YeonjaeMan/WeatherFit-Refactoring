# ![logo](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/11ed9899-7b2c-434c-b5d9-e954ca9f09e6)
> 기온과 계절 정보를 활용하여 사용자들에게 다양한 패션을 제공하는 소셜 네트워크 서비스(SNS)

</br>

## 1. 제작 기간 & 참여 인원
- 2024년 3월 18일 ~ 4월 2일

> 정연재 : PM / Back, Front   
> 프로젝트 관리, 기상청 API 날씨 위젯, 웹소켓 실시간 채팅   

> 김동현 : Back / DB   
> 프로젝트 구조화, DB설계, 게시글 상세보기, 댓글, 프로필   

> 박종현 : Front   
> 게시글 작성, 해시태그, 주소 API   

> 엄희수 : Front   
> 프론트 개발, UI & UX 디자인, 시연페이퍼, 시연영상   

> 이동주 : 크롤링, DB / Back   
> 데이터 크롤링, 로그인, 회원가입, 문서 및 PPT 제작   

</br>

## 2. 사용 기술
#### `Back-end`
  - <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"/> 1.8
  - JSP/Servlet
  - Maven
  - <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/> 
  - <img src="https://img.shields.io/badge/Apache Tomcat 9.0-D22128?style=for-the-badge&logo=Apache Tomcat&logoColor=white"/> 9.0
  - Websocket
#### `Front-end`
  - <img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=html5&logoColor=white"> / <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> / <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  - <img src="https://img.shields.io/badge/BootStrap-7952B3?style=for-the-badge&logo=BootStrap&logoColor=white"/>
  - Jquery
#### `IDE`
  - <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=Eclipse&logoColor=white"/> 
  - <img src="https://img.shields.io/badge/VSCode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white"/>
  - <img src="https://img.shields.io/badge/Jupyter-F37626?style=for-the-badge&logo=Jupyter&logoColor=white"/>
#### `Etc`
  - <img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=Python&logoColor=white"/> (Crawling)
  - <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/> / <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/>
  - <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white"/>
  - Notion
  - AQuery

</br>

## 3. ERD 설계
![](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/86634da8-afa4-4bc0-b26f-e5b435ad16bb)

## 4. 핵심 기능
1. 게시글에 기온을 추가하여 사용자들끼리 패션 코디를 공유하기 위한 기능
2. 의류 정보, 의류 후기 등을 교류하기 위한 채팅 기능

<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 전체 흐름
![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/0bf7f35c-2122-4ed1-b41b-c9f45c07024e)

### 4.2. 사용자 요청
![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/b8851469-20c3-4a45-b4bd-ab19e6e7fafa)

- **URL 정규식 체크** :pushpin: [코드 확인](https://github.com/JungHyung2/gitio.io/blob/95b4c4f06a2a5a74a00f81a3c3fcc003c994725f/index.html#L15C8-L15C26)
  - Vue.js로 렌더링된 화면단에서, 사용자가 등록을 시도한 URL의 모양새를 정규식으로 확인합니다.
  - URL의 모양새가 아닌 경우, 에러 메세지를 띄웁니다.

- **Axios 비동기 요청** :pushpin: [코드 확인]()
  - URL의 모양새인 경우, 컨텐츠를 등록하는 POST 요청을 비동기로 날립니다.

### 4.3. Controller

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_controller.png)

- **요청 처리** :pushpin: [코드 확인](https://github.com/2023-SMHRD-KDT-IOT-4/Repo/blob/94e1b3a93c48cc3fdb51d4468de151930705faa6/Middle_project12/src/main/webapp/WEB-INF/views/BoardContent.jsp#L20)
  - Controller에서는 요청을 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.

- **결과 응답** :pushpin: [코드 확인]()
  - Service 계층에서 넘어온 로직 처리 결과(메세지)를 화면단에 응답해줍니다.

### 4.4. Service

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service1.png)

- **Http 프로토콜 추가 및 trim()** :pushpin: [코드 확인]()
  - 사용자가 URL 입력 시 Http 프로토콜을 생략하거나 공백을 넣은 경우,  
  올바른 URL이 될 수 있도록 Http 프로토콜을 추가해주고, 공백을 제거해줍니다.

- **URL 접속 확인** :pushpin: [코드 확인]()
  - 화면단에서 모양새만 확인한 URL이 실제 리소스로 연결되는지 HttpUrlConnection으로 테스트합니다.
  - 이 때, 빠른 응답을 위해 Request Method를 GET이 아닌 HEAD를 사용했습니다.
  - (HEAD 메소드는 GET 메소드의 응답 결과의 Body는 가져오지 않고, Header만 확인하기 때문에 GET 메소드에 비해 응답속도가 빠릅니다.)

  ![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service2.png)

- **Jsoup 이미지, 제목 파싱** :pushpin: [코드 확인]()
  - URL 접속 확인결과 유효하면 Jsoup을 사용해서 입력된 URL의 이미지와 제목을 파싱합니다.
  - 이미지는 Open Graphic Tag를 우선적으로 파싱하고, 없을 경우 첫 번째 이미지와 제목을 파싱합니다.
  - 컨텐츠에 이미지가 없을 경우, 미리 설정해둔 기본 이미지를 사용하고, 제목이 없을 경우 생략합니다.


### 4.5. Repository

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_repo.png)

- **컨텐츠 저장** :pushpin: [코드 확인]()
  - URL 유효성 체크와 이미지, 제목 파싱이 끝난 컨텐츠는 DB에 저장합니다.
  - 저장된 컨텐츠는 다시 Repository - Service - Controller를 거쳐 화면단에 송출됩니다.

</div>
</details>

</br>

## 5. 핵심 트러블 슈팅

<details>
<summary><b>5.1. 기획의 불안정 문제</b></summary>
<div markdown="1">
  
- 처음에는 날씨 API를 활용해 날씨의 변화에 맞춰 사용자마다 코디를 실시간으로 추천해주는 서비스를 기획했기에 tb_fashion, tb_weather 테이블 두개를 설계했습니다.

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/2b76f9aa-cb10-4ff3-89dc-49aac339a149)
  
- 하지만, 저희의 기술적인 한계로 인해 tb_fashion, tb_weather 테이블 대신 tb_crawling 테이블을 만들어 크롤링한 데이터를 계절별로만 나누어 추천했습니다.
  
![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/7f4b18fb-f2e5-4724-ba62-6f950b5b07b8)

</div>
</details>

<details>
<summary><b>5.2. 구현 과정 중 생긴 의견 통일의 문제</b></summary>
<div markdown="1">
  
- 구현 단계로 들어서기 전 피그마로 전체적인 UI/UX를 구성하였습니다.

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/b01f87b2-fe21-4119-980a-f3b569ecb2b6)

- 하지만, 구현 단계로 들어서서 피그마로 구성한 UI/UX에서 세밀한 부분은 사용자의 편의성에 맞추어 바꿔야 할 필요가 있었기 때문에 짧은 기간 동안 빠른 의견 전달을 위해 그림판, 노트 등의 시각화하는 방법으로 회의를 진행하였습니다.

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/9d44c9df-2a3a-48a5-a200-0bdb45e133e2)

</div>
</details>

<details>
<summary><b>5.3. Git 문제</b></summary>
<div markdown="1">
  
- 처음 배워서 사용해보는 Git이었기에 프로젝트 기간 초반에 Git을 사용해보면서 공부하는 것에 시간을 많이 사용했습니다.   

- 심지어 팀 한명의 파일이 전부 날아가는 사태도 일어났습니다.   

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/10db11c4-3682-4e7d-bd58-ef58961ffd9a)

- GitHub Desktop을 이용해 Git을 UI로 보여주는 소프트웨어를 사용했고, 매일 팀원과 시간을 맞추어 Git push & pull을 하여 충돌을 방지했습니다.   

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/1153595b-86ca-4719-8135-ceeeb4539edc)

</div>
</details>

<details>
<summary><b>5.4. WebSocket 채팅방 문제</b></summary>
<div markdown="1">
  
- 1:1 실시간 채팅을 위해 WebSocket을 구현하는 도중 채팅방을 통해 사용자 2명을 이어주는 것에 대해 어려움이 있었습니다.

- @ServerEndpoint 어노테이션을 사용해 사용자가 속해있는 roomIdx를 이어줌으로써 채팅기능을 해결하였습니다.

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/a65509cb-aecd-46db-b48d-2248b7668b8e)

</div>
</details>

<details>
<summary><b>5.5. Modal의 충돌</b></summary>
<div markdown="1">
  
- 프로젝트에 한 페이지 안에서 여러 모달을 사용해야만 했습니다.

- HTML5의 모달을 구현하여 모달마다 showModal(), removeModal() 함수를 호출해주어야 했지만, showModal(), removeModal() 함수를 호출할 때 다른 js 코드들이 작동하지 않는 문제가 발생했습니다.

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/0ddf8321-002c-4e93-b165-363ca1b853b9)

- 부트스트랩의 data-bs-toggle, data-bs-target 속성을 사용해 모달의 충돌이 일어나는 것을 방지할 수 있었고, 유지보수하기에도 용이해 부트스트랩을 공부하여 해결하였습니다.

![image](https://github.com/2024-SMHRD-KDT-BigData-23/WeatherFit/assets/76952340/4ba78611-865e-41cc-bd2d-871fad0c61bc)

</div>
</details>
