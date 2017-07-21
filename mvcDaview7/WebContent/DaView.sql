--멤버 테이블생성

  CREATE TABLE "MEMBER" 
   (	"M_ID" VARCHAR2(10 BYTE), 
	"M_PW" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"M_EMAIL" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"M_NICK" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"M_REG_DATE" DATE DEFAULT sysdate, 
	 PRIMARY KEY ("M_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ; 
commit;



--게시판 테이블 생성
CREATE TABLE "BOARD" 
   (	"B_NUM" NUMBER, 
	"B_SUBJECT" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"B_CONTENT" VARCHAR2(500 BYTE) NOT NULL ENABLE, 
	"B_RC" NUMBER DEFAULT 0, 
	"B_PW" VARCHAR2(12 BYTE) NOT NULL ENABLE, 
	"B_REG_DATE" DATE NOT NULL ENABLE, 
	"B_PART" VARCHAR2(10 BYTE), 
	"M_ID" VARCHAR2(10 BYTE), 
	"B_STAR" VARCHAR2(10 BYTE), 
	 PRIMARY KEY ("B_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 FOREIGN KEY ("M_ID")
	  REFERENCES "MEMBER" ("M_ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;



--댓글 테이블 생성
  CREATE TABLE "COMMENT_BOARD" 
   (	"C_NUM" NUMBER, 
	"B_NUM" NUMBER NOT NULL ENABLE, 
	"M_ID" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"C_DATE" DATE DEFAULT sysdate, 
	"C_REF" NUMBER DEFAULT 0, 
	"C_RE_STEP" NUMBER DEFAULT 0, 
	"C_RE_LEVEL" NUMBER DEFAULT 0, 
	"C_CONTENT" VARCHAR2(500 BYTE) NOT NULL ENABLE, 
	 PRIMARY KEY ("C_NUM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 FOREIGN KEY ("B_NUM")
	  REFERENCES "BOARD" ("B_NUM") ENABLE, 
	 FOREIGN KEY ("M_ID")
	  REFERENCES "MEMBER" ("M_ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

commit;

insert into member values
('ab','1234','ab@naver.com','메르시',sysdate);
insert into member values
('cd','1234','cd','호날도',sysdate);
insert into member values
('ef','1234','ef','bsdf3',sysdate);
insert into member values
('gh','1234','gf','카카',sysdate);
insert into member values
('ik','1234','wer','차',sysdate);
insert into member values
('lm','1234','asg','고양이',sysdate);
insert into member values
('no','1234','asf','개',sysdate);
insert into member values
('pq','1234','wef','바나나',sysdate);
insert into member values
('st','1234','sag','초코',sysdate);
insert into member values
('uv','1234','gasd','asdf',sysdate);

insert into board values
(1,'숙박153','당첨',0,'1234',sysdate,'숙박','lm',3);
insert into board values
(2,'숙박235','당첨',0,'1234',sysdate,'숙박','lm',4);
insert into board values
(3,'게임26234','당첨',0,'1234',sysdate,'게임','pq',4);
insert into board values
(4,'책235','당첨',0,'1234',sysdate,'책','gh',2);
insert into board values
(5,'책23324','당첨',0,'1234',sysdate,'책','lm',5);
insert into board values
(6,'toto','당첨',0,'1234',sysdate,'숙박','lm',3);
insert into board values
(7,'gkdldy','바이',0,'1234',sysdate,'가전','no',2);
insert into board values
(8,'하이요','12341',0,'1234',sysdate,'게임','gh',5);
insert into board values
(9,'가전이요','쿠쿠다스',0,'1234',sysdate,'가전','pq',4);
insert into board values
(10,'게임이요','헬싱',0,'1234',sysdate,'식품','ik',2);
insert into board values
(11,'숙박이요','허ㅗ호호',0,'1234',sysdate,'식품','ef',5);
insert into board values
(12,'요기요','쿠폰',0,'1234',sysdate,'책','ef',1);
insert into board values
(13,'sbasf3','153ㅎㄹㄹ',0,'1234',sysdate,'책','ab',3);
insert into board values
(14,'게임1','ㅋㅋㅋㅋㅋ',0,'1234',sysdate,'게임','cd',2);
insert into board values
(15,'게임6','ㄱㄱ',0,'1234',sysdate,'게임','uv',3);
insert into board values
(16,'ㅅㅅㅅㅅ','ㅅㅅㅅㅅ',0,'1234',sysdate,'숙박','st',5);
insert into board values
(17,'게임3','ㅅㅅㅅㅅ',0,'1234',sysdate,'게임','uv',5);
insert into board values
(18,'게임4','ㅅㅅㅅㅅ',0,'1234',sysdate,'게임','st',5);
insert into board values
(19,'숙박3','ㅅㅅㅅㅅ',0,'1234',sysdate,'숙박','cd',5);
insert into board values
(20,'숙박2','ㅅㅅㅅㅅ',0,'1234',sysdate,'숙박','ef',5);
insert into board values
(21,'책3','ㅅㅅㅅㅅ',0,'1234',sysdate,'책3','ik',5);
insert into board values
(22,'요기요','쿠폰',0,'1234',sysdate,'책','ef',1);
insert into board values
(23,'식품3','쿠폰',0,'1234',sysdate,'식품','st',4);
insert into board values
(24,'식품2','쿠폰',0,'1234',sysdate,'식품','uv',3);
insert into board values
(25,'식품1','쿠폰',0,'1234',sysdate,'식품','cd',2);
insert into board values
(26,'게임123','쿠폰',0,'1234',sysdate,'게임','ab',5);
insert into board values
(27,'숙박89','쿠폰',0,'1234',sysdate,'숙박','ef',3);
insert into board values
(28,'숙박7','쿠폰',0,'1234',sysdate,'숙박','gh',3);
insert into board values
(29,'게임10','쿠폰',0,'1234',sysdate,'게임','gh',2);
insert into board values
(30,'게임8','쿠폰',0,'1234',sysdate,'게임','ik',1);
insert into board values
(31,'게임99','쿠폰',0,'1234',sysdate,'게임','ef',2);
insert into board values
(32,'가전353','쿠폰',0,'1234',sysdate,'가전','ef',5);
insert into board values
(33,'가전23','쿠폰',0,'1234',sysdate,'가전','ef',4);
insert into board values
(34,'가전787','쿠폰',0,'1234',sysdate,'가전','ef',1);
insert into board values
(35,'가전12','쿠폰',0,'1234',sysdate,'가전','ef',2);
insert into board values
(36,'가전65','쿠폰',0,'1234',sysdate,'가전','ef',3);
insert into board values
(37,'가전999','쿠폰',0,'1234',sysdate,'가전','ef',2);
insert into board values
(38,'가전111','쿠폰',0,'1234',sysdate,'가전','ef',4);
insert into board values
(39,'가전333','쿠폰',0,'1234',sysdate,'가전','ef',4);
insert into board values
(40,'가전534','쿠폰',0,'1234',sysdate,'가전','ef',5);

commit;