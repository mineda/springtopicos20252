create table usr_usuario (
  usr_id bigint generated always as identity,
  usr_nome varchar(20) not null,
  usr_senha varchar(150) not null,
  primary key (usr_id),
  unique (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint generated always as identity,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint not null,
  aut_id bigint not null,
  primary key (usr_id, aut_id),
  foreign key (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_anotacao (
  ant_id bigint generated always as identity,
  ant_texto varchar(256) not null,
  ant_data_hora timestamp not null,
  ant_usr_id bigint not null,
  primary key (ant_id),
  foreign key (ant_usr_id) references usr_usuario(usr_id)
);

create table tra_trabalho (
  tra_id bigint generated always as identity,
  tra_titulo varchar(100) not null unique,
  tra_data_hora_entrega timestamp not null,
  tra_descricao varchar(200),
  tra_usr_id bigint not null,
  tra_nota int,
  foreign key (tra_usr_id) references usr_usuario (usr_id) on delete restrict on update cascade
);

insert into usr_usuario (usr_nome, usr_senha)
  values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
  values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
  values (1, 1);
insert into ant_anotacao(ant_texto, ant_data_hora, ant_usr_id)
  values('Meu novo projeto', '2023-08-01 19:10', 1);
insert into tra_trabalho (tra_titulo, tra_data_hora_entrega, tra_nota, tra_usr_id)
  values ('Teste 1', current_timestamp, 6, 1),
         ('Teste 2', current_timestamp, null, 1);

create user spring with password 'pass123';

-- A partir daqui incluí tabelas extras para estudo e avalizações
-- Não esqueçam de executar o grant ao final

alter table tra_trabalho add primary key (tra_id);

create table sec_secao (
  sec_id bigint generated always as identity,
  sec_titulo varchar(100) not null,
  sec_data_hora_criacao timestamp not null,
  sec_conteudo varchar(300),
  sec_tra_id bigint not null,
  primary key (sec_id),
  foreign key (sec_tra_id) references tra_trabalho (tra_id) on delete restrict on update cascade
);

insert into sec_secao (sec_titulo, sec_data_hora_criacao, sec_conteudo, sec_tra_id)
  values ('Capítulo 1', '2023-08-04 10:30', 'Meu nome é Post', 2),
         ('Capítulo 2', '2023-08-04 11:16', 'Trabalho com software', 2);

create table com_comentario (
  com_id bigint generated always as identity,
  com_conteudo varchar(100) not null,
  com_data_hora_criacao timestamp not null,
  com_urgencia int not null,
  com_tra_id bigint not null,
  primary key (com_id),
  foreign key (com_tra_id) references tra_trabalho (tra_id) on delete restrict on update cascade
);

insert into com_comentario (com_conteudo, com_data_hora_criacao, com_urgencia, com_tra_id)
  values ('Falta conteúdo', '2025-10-20 11:00', 3, 2),
         ('Novo', current_timestamp, 1, 1);

create table rev_revisao (
  rev_id bigint generated always as identity,
  rev_feedback varchar(100) not null,
  rev_data_hora_criacao timestamp not null,
  rev_data_hora_correcao timestamp,
  rev_sec_id bigint not null,
  primary key (rev_id),
  foreign key (rev_sec_id) references sec_secao (sec_id) on delete restrict on update cascade
);


insert into rev_revisao (rev_feedback, rev_data_hora_criacao, rev_data_hora_correcao, rev_sec_id)
  values ('Somente título', '2025-10-20 11:00', '2025-10-27 23:05', 1),
         ('E o resto?', current_timestamp, null, 2);

grant update, delete, insert, select on all tables in schema public to spring;
