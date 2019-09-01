CREATE SEQUENCE public.pessoa_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 11
    CACHE 1;
ALTER TABLE public.pessoa_codigo_seq
    OWNER TO postgres;