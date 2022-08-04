--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2022-08-04 10:42:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE empleados;
--
-- TOC entry 3322 (class 1262 OID 24685)
-- Name: empleados; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE empleados WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';


ALTER DATABASE empleados OWNER TO postgres;

\connect empleados

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3323 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 24744)
-- Name: empleados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleados (
    identificacion bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    fecha_nacimiento date NOT NULL,
    cargo character varying(255) NOT NULL
);


ALTER TABLE public.empleados OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24686)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    "user" character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 24689)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 3324 (class 0 OID 0)
-- Dependencies: 210
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- TOC entry 3168 (class 2604 OID 24771)
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- TOC entry 3316 (class 0 OID 24744)
-- Dependencies: 211
-- Data for Name: empleados; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.empleados VALUES (12534, 'Daniel Ortiz Hernandez Maya', '2021-01-04', 'ASESOR COMERCIAL');
INSERT INTO public.empleados VALUES (2738800192, 'Rosalba Maria Conrado Durcan', '1987-07-01', 'Profesora de bachillerato');


--
-- TOC entry 3314 (class 0 OID 24686)
-- Dependencies: 209
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuarios VALUES (1, 'admin', 'admin');
INSERT INTO public.usuarios VALUES (5, 'user1', '123456');


--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 210
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 8, true);


--
-- TOC entry 3174 (class 2606 OID 24761)
-- Name: empleados empleados_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pk PRIMARY KEY (identificacion);


--
-- TOC entry 3170 (class 2606 OID 24695)
-- Name: usuarios usuarios.id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT "usuarios.id_pk" PRIMARY KEY (id);


--
-- TOC entry 3172 (class 2606 OID 24741)
-- Name: usuarios usuarios_un; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_un UNIQUE ("user");


-- Completed on 2022-08-04 10:42:10

--
-- PostgreSQL database dump complete
--

