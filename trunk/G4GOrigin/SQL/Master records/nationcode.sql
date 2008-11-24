--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:36:19

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1324 (class 1259 OID 223364)
-- Dependencies: 5
-- Name: nationalcode; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nationalcode (
    natcodeid integer NOT NULL,
    islegal character varying(255),
    nationcode character varying(255) NOT NULL,
    nationname character varying(255) NOT NULL
);


ALTER TABLE public.nationalcode OWNER TO postgres;

--
-- TOC entry 1682 (class 0 OID 223364)
-- Dependencies: 1324
-- Data for Name: nationalcode; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (13, 'true', 'AU', 'Australia');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (14, 'true', 'AT', 'Austria');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (21, 'true', 'BE', 'Belgium');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (38, 'true', 'CA', 'Canada');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (80, 'true', 'DE', 'Germany');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (57, 'true', 'DK', 'Denmark');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (72, 'true', 'FI', 'Finland');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (73, 'true', 'FR', 'France');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (91, 'true', 'HK', 'Hong Kong');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (97, 'true', 'IE', 'Ireland');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (99, 'true', 'IT', 'Italy');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (100, 'true', 'JP', 'Japan');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (114, 'true', 'LU', 'Luxembourg');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (135, 'true', 'NL', 'Netherlands');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (144, 'true', 'NO', 'Norway');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (137, 'true', 'NZ', 'New Zealand');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (153, 'true', 'PT', 'Portugal');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (202, 'true', 'US', 'United States');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (182, 'true', 'SE', 'Sweden');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (176, 'true', 'ES', 'Spain');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (201, 'true', 'GB', 'United Kingdom');
INSERT INTO nationalcode (natcodeid, islegal, nationcode, nationname) VALUES (183, 'true', 'CH', 'Switzerland');


--
-- TOC entry 1677 (class 2606 OID 223521)
-- Dependencies: 1324 1324
-- Name: nationalcode_nationcode_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_nationcode_key UNIQUE (nationcode);


--
-- TOC entry 1679 (class 2606 OID 223523)
-- Dependencies: 1324 1324 1324
-- Name: nationalcode_nationname_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_nationname_key UNIQUE (nationname, nationcode);


--
-- TOC entry 1681 (class 2606 OID 223525)
-- Dependencies: 1324 1324
-- Name: nationalcode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_pkey PRIMARY KEY (natcodeid);


--
-- TOC entry 1675 (class 1259 OID 223549)
-- Dependencies: 1324
-- Name: index_nationalcode_nationcode_key; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX index_nationalcode_nationcode_key ON nationalcode USING btree (nationcode);


-- Completed on 2008-05-16 19:36:19

--
-- PostgreSQL database dump complete
--

