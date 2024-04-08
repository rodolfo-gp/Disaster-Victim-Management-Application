--
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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

DROP DATABASE ensf380project;
--
-- Name: ensf380project; Type: DATABASE; Schema: -; Owner: oop
--

CREATE DATABASE ensf380project WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Canada.1252';


ALTER DATABASE ensf380project OWNER TO oop;

\connect ensf380project

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: inquirer; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.inquirer (
    id integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50),
    phonenumber character varying(20) NOT NULL
);


ALTER TABLE public.inquirer OWNER TO oop;

--
-- Name: inquirer_id_seq; Type: SEQUENCE; Schema: public; Owner: oop
--

CREATE SEQUENCE public.inquirer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inquirer_id_seq OWNER TO oop;

--
-- Name: inquirer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oop
--

ALTER SEQUENCE public.inquirer_id_seq OWNED BY public.inquirer.id;


--
-- Name: inquiry_log; Type: TABLE; Schema: public; Owner: oop
--

CREATE TABLE public.inquiry_log (
    id integer NOT NULL,
    inquirer integer NOT NULL,
    calldate date NOT NULL,
    details character varying(500) NOT NULL
);


ALTER TABLE public.inquiry_log OWNER TO oop;

--
-- Name: inquiry_log_id_seq; Type: SEQUENCE; Schema: public; Owner: oop
--

CREATE SEQUENCE public.inquiry_log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inquiry_log_id_seq OWNER TO oop;

--
-- Name: inquiry_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: oop
--

ALTER SEQUENCE public.inquiry_log_id_seq OWNED BY public.inquiry_log.id;


--
-- Name: inquirer id; Type: DEFAULT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.inquirer ALTER COLUMN id SET DEFAULT nextval('public.inquirer_id_seq'::regclass);


--
-- Name: inquiry_log id; Type: DEFAULT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.inquiry_log ALTER COLUMN id SET DEFAULT nextval('public.inquiry_log_id_seq'::regclass);


--
-- Data for Name: inquirer; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.inquirer (id, firstname, lastname, phonenumber) FROM stdin;
\.
COPY public.inquirer (id, firstname, lastname, phonenumber) FROM '$$PATH$$/4844.dat';

--
-- Data for Name: inquiry_log; Type: TABLE DATA; Schema: public; Owner: oop
--

COPY public.inquiry_log (id, inquirer, calldate, details) FROM stdin;
\.
COPY public.inquiry_log (id, inquirer, calldate, details) FROM '$$PATH$$/4846.dat';

--
-- Name: inquirer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oop
--

SELECT pg_catalog.setval('public.inquirer_id_seq', 1, false);


--
-- Name: inquiry_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: oop
--

SELECT pg_catalog.setval('public.inquiry_log_id_seq', 1, false);


--
-- Name: inquirer inquirer_pkey; Type: CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.inquirer
    ADD CONSTRAINT inquirer_pkey PRIMARY KEY (id);


--
-- Name: inquiry_log inquiry_log_pkey; Type: CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.inquiry_log
    ADD CONSTRAINT inquiry_log_pkey PRIMARY KEY (id);


--
-- Name: inquiry_log inquiry_log_inquirer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: oop
--

ALTER TABLE ONLY public.inquiry_log
    ADD CONSTRAINT inquiry_log_inquirer_fkey FOREIGN KEY (inquirer) REFERENCES public.inquirer(id) ON UPDATE CASCADE;


--
-- PostgreSQL database dump complete
--

