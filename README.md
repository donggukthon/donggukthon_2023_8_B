<div align="center">
<img src="https://capsule-render.vercel.app/api?type=rounded&color=E0F8F7&height=200&section=header&text=Santa&fontSize=90&fontColor=BDBDBD&fontAlign=70&rotate=13&fontAlignY=3"/><br>
#백엔드 Repository입니다#

</br>

<h3>[프로젝트 설명]</h3>
<a>우리의 서비스는 겨울철 이벤트성으로 사용할 수 있는 서비스이자 사회문제 해결에 기여할 수 있는 서비스입니다.</a></br>
<a>기부를 하면 보상으로 3D 트리에 소품을 장식할 수 있고, 메시지 카드를 받을 수 있습니다.</a></br>
<a>지인에게 따뜻한 연말인사를 전할 수 있으며, 기부챌린지가 이어집니다. 서비스 수익금은 겨울철 필요한 불우이웃에게 전달 됩니다.</a>

<h3>[개발 기간]</h3>
<a>2023.12.18~2023.12.20</a></br>
<h3>[개발 인원]</h3>

#기획</br>
<a>[이설]</a>

#디자인</br>
<a>[이민지]</a>

#프론트엔드</br>
<a>[황재형]</a>
<a>[김동민]</a>

#백엔드</br>
<a>[유수민]<a>
<a href="https://github.com/dmsqor">[홍정표]<a>

<h3>[개발 환경]</h3>

#언어</br>
<img src="https://img.shields.io/badge/Java(JDK 17)-F80000?style=flat&logo=Oracle&logoColor=white"/>
<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white"/>
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/>

#서버</br>
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat&logo=Amazon EC2&logoColor=white"/>

#OS</br>
<img src="https://img.shields.io/badge/Windows 11-0078D4?style=flat&logo=Windows 11&logoColor=white"/>

#데이터 저장 및 관리</br>
<img src="https://img.shields.io/badge/MariaDB-003545?style=flat&logo=MariaDB&logoColor=white"/>

#IDE</br>
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat&logo=IntelliJ IDEA&logoColor=white"/>
<img src="https://img.shields.io/badge/HeldiSQL-569A31?style=flat&logo=&logoColor=white"/>
<img src="https://img.shields.io/badge/Git-F05032?style=flat&logo=Git&logoColor=white"/>

#개발 도구</br>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/>
<img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white"/>

#API</br>

#프로젝트 형상관리</br>
<img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white"/>

#웹 디자인 설계</br>
<img src="https://img.shields.io/badge/Figma-F24E1E?style=flat&logo=Figma&logoColor=white"/>

#협업툴</br>
<img src="https://img.shields.io/badge/Notion(프로젝트 관리)-000000?style=flat&logo=Notion&logoColor=white"/>
<img src="https://img.shields.io/badge/KakaoTalk(일정 조율)-FFCD00?style=flat&logo=KakaoTalk&logoColor=white"/></br>











# Getting Started

1. File -> open -> build.gradle 더블클릭 -> open as project

2. application.yml 파일 만들기
<details>
  <summary>application.yaml</summary>

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/{본인커스 }?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true
    username: {본인 커스텀}
    password: {본인 커스텀}

  jackson:
    serialization:
      FAIL_ON_EMPTY_BEANS: true
  jpa:
    hibernate:
      ddl-auto: create
    show_sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
    defer-datasource-initialization: true
  sql:
    init:
      mode: embedded
```

</details>


3. data.sql 만들기

<details>
  <summary>클릭하여 세부 내용 보기</summary>

```SQL
-- The table order was sorted considering the relationship to prevent error from occurring if all are run at once.

-- member Table Create SQL
-- Table Creation SQL - member
CREATE TABLE member
(
    `id`            INT            NOT NULL    AUTO_INCREMENT,
    `email`         VARCHAR(50)    NOT NULL    COMMENT '구글이메일. UNIQUE',
    `password`      VARCHAR(50)    NOT NULL    COMMENT '비밀번호',
    `name`          VARCHAR(50)    NULL        COMMENT '이름',
    `nickname`      VARCHAR(50)    NULL        COMMENT '닉네임',
    `gender`        VARCHAR(50)    NULL        COMMENT '성별',
    `phone_number`  VARCHAR(50)    NULL        COMMENT '전화번호',
    `created_at`    TIMESTAMP      NOT NULL    COMMENT '생성일',
    PRIMARY KEY (id)
);

-- Table Comment SQL - member
ALTER TABLE member COMMENT '유저';


-- submission Table Create SQL
-- Table Creation SQL - submission
CREATE TABLE submission
(
    `id`            INT              NOT NULL    AUTO_INCREMENT,
    `member_id`     INT              NOT NULL    COMMENT '후원한 사람',
    `amount`        INT              NOT NULL    COMMENT '후원 금액',
    `card_message`  VARCHAR(2048)    NULL        COMMENT '카드 문구. metadata',
    `is_actived`    BIT              NULL        DEFAULT 0 COMMENT '활성여부',
    `created_at`    TIMESTAMP        NOT NULL    COMMENT '생성일',
    PRIMARY KEY (id)
);

-- Foreign Key SQL - submission(member_id) -> member(id)
ALTER TABLE submission
    ADD CONSTRAINT FK_submission_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Delete Foreign Key SQL - submission(member_id)
-- ALTER TABLE submission
-- DROP FOREIGN KEY FK_submission_member_id_member_id;


-- decoration Table Create SQL
-- Table Creation SQL - decoration
CREATE TABLE decoration
(
    `id`           INT            NOT NULL    AUTO_INCREMENT,
    `description`  VARCHAR(50)    NULL        COMMENT '장식품 종류',
    `file`         BLOB           NULL        COMMENT '3D파일',
    `scale`        INT            NULL        COMMENT '크기',
    `location`     VARCHAR(50)    NULL        COMMENT '위치',
    `created_at`   TIMESTAMP      NOT NULL    COMMENT '생성일',
    PRIMARY KEY (id)
);


-- member_decoration_relation Table Create SQL
-- Table Creation SQL - member_decoration_relation
CREATE TABLE member_decoration_relation
(
    `id`             INT          NOT NULL    AUTO_INCREMENT,
    `member_id`      INT          NULL,
    `decoration_id`  INT          NULL,
    `created_at`     TIMESTAMP    NOT NULL,
    PRIMARY KEY (id)
);

-- Foreign Key SQL - member_decoration_relation(decoration_id) -> decoration(id)
ALTER TABLE member_decoration_relation
    ADD CONSTRAINT FK_member_decoration_relation_decoration_id_decoration_id FOREIGN KEY (decoration_id)
        REFERENCES decoration (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Delete Foreign Key SQL - member_decoration_relation(decoration_id)
-- ALTER TABLE member_decoration_relation
-- DROP FOREIGN KEY FK_member_decoration_relation_decoration_id_decoration_id;

-- Foreign Key SQL - member_decoration_relation(member_id) -> member(id)
ALTER TABLE member_decoration_relation
    ADD CONSTRAINT FK_member_decoration_relation_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Delete Foreign Key SQL - member_decoration_relation(member_id)
-- ALTER TABLE member_decoration_relation
-- DROP FOREIGN KEY FK_member_decoration_relation_member_id_member_id;


-- designated_person Table Create SQL
-- Table Creation SQL - designated_person
CREATE TABLE designated_person
(
    `id`             INT              NOT NULL    AUTO_INCREMENT,
    `submission_id`  INT              NULL        COMMENT '제출',
    `member_id`      INT              NULL        COMMENT '후원한 사람',
    `send_message`   VARCHAR(2048)    NULL        COMMENT '전송 문구',
    `send_email`     VARCHAR(50)      NULL        COMMENT '전송 이메일',
    `created_at`     TIMESTAMP        NOT NULL    COMMENT '생성일',
    PRIMARY KEY (id)
);

-- Foreign Key SQL - designated_person(member_id) -> member(id)
ALTER TABLE designated_person
    ADD CONSTRAINT FK_designated_person_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Delete Foreign Key SQL - designated_person(member_id)
-- ALTER TABLE designated_person
-- DROP FOREIGN KEY FK_designated_person_member_id_member_id;

-- Foreign Key SQL - designated_person(submission_id) -> submission(id)
ALTER TABLE designated_person
    ADD CONSTRAINT FK_designated_person_submission_id_submission_id FOREIGN KEY (submission_id)
        REFERENCES submission (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Delete Foreign Key SQL - designated_person(submission_id)
-- ALTER TABLE designated_person
-- DROP FOREIGN KEY FK_designated_person_submission_id_submission_id;
```

</details>


4. Run SantaApplication
