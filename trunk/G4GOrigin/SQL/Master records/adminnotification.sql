--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:37:04

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1314 (class 1259 OID 223322)
-- Dependencies: 1675 1676 1677 5
-- Name: adminnotification; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE adminnotification (
    adminnotificationid integer NOT NULL,
    notificationtypeid integer,
    email boolean DEFAULT false,
    popup boolean DEFAULT false,
    message boolean DEFAULT false
);


ALTER TABLE public.adminnotification OWNER TO postgres;

--
-- TOC entry 1681 (class 0 OID 223322)
-- Dependencies: 1314
-- Data for Name: adminnotification; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (1, 1, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (2, 2, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (3, 3, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (4, 4, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (5, 5, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (7, 7, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (9, 9, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (10, 10, false, true, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (12, 12, false, true, false);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (6, 6, false, false, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (8, 8, false, false, true);
INSERT INTO adminnotification (adminnotificationid, notificationtypeid, email, popup, message) VALUES (11, 11, false, false, true);


--
-- TOC entry 1679 (class 2606 OID 223451)
-- Dependencies: 1314 1314
-- Name: PK_AdminNotification_AdminNotificationID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adminnotification
    ADD CONSTRAINT "PK_AdminNotification_AdminNotificationID" PRIMARY KEY (adminnotificationid);


--
-- TOC entry 1680 (class 2606 OID 223610)
-- Dependencies: 1314 1327
-- Name: FK_NotificationType_AdminNotification_NotificationId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adminnotification
    ADD CONSTRAINT "FK_NotificationType_AdminNotification_NotificationId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2008-05-16 19:37:05

--
-- PostgreSQL database dump complete
--

