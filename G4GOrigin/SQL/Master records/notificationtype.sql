--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:37:56

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1327 (class 1259 OID 223376)
-- Dependencies: 5
-- Name: notificationtype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificationtype (
    notificationtypeid integer NOT NULL,
    name character varying(16)
);


ALTER TABLE public.notificationtype OWNER TO postgres;

--
-- TOC entry 1679 (class 0 OID 223376)
-- Dependencies: 1327
-- Data for Name: notificationtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO notificationtype (notificationtypeid, name) VALUES (1, 'Message');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (2, 'Clan');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (3, 'Challenge');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (4, 'ChallengeResult');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (5, 'Friend Request');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (6, 'WorldGaming');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (7, 'MatchConfirm');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (8, 'TournamentJoin');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (9, 'CashConfirm');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (10, 'MatchResults');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (11, 'TournamentResult');
INSERT INTO notificationtype (notificationtypeid, name) VALUES (12, 'UpcomingMatch');


--
-- TOC entry 1676 (class 2606 OID 223469)
-- Dependencies: 1327 1327
-- Name: PK_NotificationType_NotificationTypeID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationtype
    ADD CONSTRAINT "PK_NotificationType_NotificationTypeID" PRIMARY KEY (notificationtypeid);


--
-- TOC entry 1678 (class 2606 OID 223493)
-- Dependencies: 1327 1327
-- Name: UK_NotificationType_Name; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationtype
    ADD CONSTRAINT "UK_NotificationType_Name" UNIQUE (name);


-- Completed on 2008-05-16 19:37:56

--
-- PostgreSQL database dump complete
--

