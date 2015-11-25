# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Channels (
  id                        bigint auto_increment not null,
  cid                       varchar(255),
  cname                     varchar(255),
  subnum                    varchar(255),
  extra                     varchar(255),
  constraint pk_Channels primary key (id))
;

create table news3 (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  source                    varchar(255),
  cp                        varchar(255),
  time                      datetime,
  cid                       varchar(255),
  snap_detail               varchar(255),
  icon                      varchar(255),
  url                       varchar(255),
  extra                     varchar(255),
  constraint pk_news3 primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table Channels;

drop table news3;

SET FOREIGN_KEY_CHECKS=1;

