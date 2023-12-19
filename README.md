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