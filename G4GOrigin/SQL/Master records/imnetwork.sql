--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:37:26

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1322 (class 1259 OID 223352)
-- Dependencies: 5
-- Name: imnetwork; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE imnetwork (
    imnetworkid integer NOT NULL,
    imnetworkname character varying(10)
);


ALTER TABLE public.imnetwork OWNER TO postgres;

--
-- TOC entry 1680 (class 0 OID 223352)
-- Dependencies: 1322
-- Data for Name: imnetwork; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (1, 'Aim');
INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (2, 'Yahoo');
INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (3, 'MSN');
INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (4, 'Skype');


--
-- TOC entry 1676 (class 2606 OID 223517)
-- Dependencies: 1322 1322
-- Name: imnetwork_imnetworkname_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imnetwork
    ADD CONSTRAINT imnetwork_imnetworkname_key UNIQUE (imnetworkname);


--
-- TOC entry 1678 (class 2606 OID 223519)
-- Dependencies: 1322 1322
-- Name: imnetwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imnetwork
    ADD CONSTRAINT imnetwork_pkey PRIMARY KEY (imnetworkid);


--
-- TOC entry 1679 (class 1259 OID 223548)
-- Dependencies: 1322
-- Name: index_imnetwork_imnetworkname_key; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX index_imnetwork_imnetworkname_key ON imnetwork USING btree (imnetworkname);


-- Completed on 2008-05-16 19:37:26

--
-- PostgreSQL database dump complete
--

