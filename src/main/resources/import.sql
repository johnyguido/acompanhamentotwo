insert into TB_USUARIO (CRIADO_EM,  EMAIL,  	LOGIN,  	NOME,  	SENHA,  	SOBRENOME ) values (NOW(),  'johnyguido@hotmail.com.br', 'joalencar', 'Johny','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'Alencar')
    insert into TB_USUARIO (CRIADO_EM,  EMAIL,  	LOGIN,  	NOME,  	SENHA,  	SOBRENOME ) values (NOW(),  'michellegarcezc@gmail.com', 'migarcez', 'Michelle','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'Garcez')

insert into tb_energia ( leitura_Inicial, leitura_Final, total, data, user_id) values ( 0L, 5L, 5L, '2022-03-01 16:14:54.293364', 1L)
insert into tb_energia ( leitura_Inicial, leitura_Final, total, data, user_id) values ( 5L, 6L, 1L, '2022-03-02 16:14:54.293364', 1L)
insert into tb_energia ( leitura_Inicial, leitura_Final, total, data, user_id) values ( 6L, 7L, 1L, '2022-03-05 16:14:54.293364', 1L)
insert into tb_energia ( leitura_Inicial, leitura_Final, total, data, user_id) values ( 7L, 8L, 1L, '2022-03-04 16:14:54.293364', 1L)
insert into tb_energia ( leitura_Inicial, leitura_Final, total, data, user_id) values ( 8L, 9L, 1L, '2022-03-05 16:14:54.293364', 1L)
insert into tb_energia ( leitura_Inicial, leitura_Final, total, data, user_id) values ( 9L, 11L, 2L, '2022-03-06 16:14:54.293364', 1L)

INSERT INTO TB_PERFIL   (ID, PERMISSAO) VALUES (1L, 'ROLE_ADMIN')
INSERT INTO TB_PERFIL   (ID, PERMISSAO) VALUES (2L, 'ROLE_OPERATOR')

INSERT INTO TB_USUARIO_PERFIS  (USUARIO_ID, PERFIS_ID) VALUES (1L, 1L)
INSERT INTO TB_USUARIO_PERFIS  (USUARIO_ID, PERFIS_ID) VALUES (1L, 2L)
INSERT INTO TB_USUARIO_PERFIS  (USUARIO_ID, PERFIS_ID) VALUES (2L, 2L)