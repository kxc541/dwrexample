--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:36:41

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1325 (class 1259 OID 223369)
-- Dependencies: 5
-- Name: network; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE network (
    networkid integer NOT NULL,
    networkname character varying(10)
);


ALTER TABLE public.network OWNER TO postgres;

--
-- TOC entry 1679 (class 0 OID 223369)
-- Dependencies: 1325
-- Data for Name: network; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO network (networkid, networkname) VALUES (1000003, 'ps2');
INSERT INTO network (networkid, networkname) VALUES (1000004, 'ps3');
INSERT INTO network (networkid, networkname) VALUES (1000002, 'xbox360');


--
-- TOC entry 1676 (class 2606 OID 223465)
-- Dependencies: 1325 1325
-- Name: PK_NetworkMaster_Networkid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY network
    ADD CONSTRAINT "PK_NetworkMaster_Networkid" PRIMARY KEY (networkid);


--
-- TOC entry 1678 (class 2606 OID 223491)
-- Dependencies: 1325 1325
-- Name: UK_Network_NetworkName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY network
    ADD CONSTRAINT "UK_Network_NetworkName" UNIQUE (networkname);


-- Completed on 2008-05-16 19:36:42

--
-- PostgreSQL database dump complete
--

