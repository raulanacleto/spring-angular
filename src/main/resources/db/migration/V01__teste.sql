CREATE SEQUENCE public.categoria_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE public.categoria_codigo_seq
    OWNER TO postgres;

CREATE TABLE categoria
(
    codigo integer NOT NULL primary key DEFAULT nextval('categoria_codigo_seq'::regclass),
    nome   VARCHAR(50) NOT NULL
);

insert into categoria (nome)
values ('Lazer');
insert into categoria (nome)
values ('Alimentação');
insert into categoria (nome)
values ('Supermercado');
insert into categoria (nome)
values ('Farmácia');
insert into categoria (nome)
values ('Outros');