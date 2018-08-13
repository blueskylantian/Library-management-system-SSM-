/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/8/13 15:47:43                           */
/*==============================================================*/


drop table if exists account;

drop table if exists book;

drop table if exists booktypes;

drop table if exists borrow;

drop table if exists labels;

drop table if exists reader;

drop table if exists receipt;

drop table if exists setting;

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   account_id           int not null auto_increment,
   reader_id            int,
   account_name         varchar(16),
   account_password     varchar(16),
   remark               varchar(20),
   primary key (account_id)
);

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   book_id              int not null auto_increment,
   type_id              int,
   book_num             varchar(20),
   book_name            varchar(20),
   book_author          varchar(20),
   book_publish         varchar(20),
   book_introduction    varchar(256),
   book_price           double precision(10,4),
   book_amount          int(8),
   remark               varchar(256),
   primary key (book_id)
);

/*==============================================================*/
/* Table: booktypes                                             */
/*==============================================================*/
create table booktypes
(
   type_id              int not null auto_increment,
   type_num             varchar(20),
   type_name            varchar(20),
   remark               varchar(256),
   primary key (type_id)
);

/*==============================================================*/
/* Table: borrow                                                */
/*==============================================================*/
create table borrow
(
   borrow_id            int not null auto_increment,
   book_id              int,
   reader_id            int,
   borrow_date          varchar(20),
   borrow_return        varchar(20),
   borrow_num           int(8),
   borrow_type          smallint,
   remark               varchar(256),
   primary key (borrow_id)
);

/*==============================================================*/
/* Table: labels                                                */
/*==============================================================*/
create table labels
(
   label_id             int not null auto_increment,
   label_num            varchar(20),
   label_name           varchar(20),
   remark               varchar(256),
   primary key (label_id)
);

/*==============================================================*/
/* Table: reader                                                */
/*==============================================================*/
create table reader
(
   reader_id            int not null auto_increment,
   reader_name          varchar(20),
   reader_type          smallint,
   reader_lendnum       int(8),
   reader_credit        smallint,
   remark               varchar(256),
   primary key (reader_id)
);

/*==============================================================*/
/* Table: receipt                                               */
/*==============================================================*/
create table receipt
(
   receipt_id           int not null auto_increment,
   borrow_id            int,
   reader_id            int,
   receipt_money        double precision(10,4),
   receipt_date         varchar(20),
   receipt_type         smallint,
   primary key (receipt_id)
);

/*==============================================================*/
/* Table: setting                                               */
/*==============================================================*/
create table setting
(
   setting_id           int not null auto_increment,
   fine                 double precision(10,4),
   lend_days            int(8),
   lend_num             int(8),
   teacher_num          int(8),
   student_num          int(8),
   remark               varchar(256),
   primary key (setting_id)
);

alter table account add constraint FK_Reference_6 foreign key (reader_id)
      references reader (reader_id) on delete restrict on update restrict;

alter table book add constraint FK_Reference_2 foreign key (type_id)
      references booktypes (type_id) on delete restrict on update restrict;

alter table borrow add constraint FK_Reference_4 foreign key (book_id)
      references book (book_id) on delete restrict on update restrict;

alter table borrow add constraint FK_Reference_5 foreign key (reader_id)
      references reader (reader_id) on delete restrict on update restrict;

alter table receipt add constraint FK_Reference_7 foreign key (borrow_id)
      references borrow (borrow_id) on delete restrict on update restrict;

