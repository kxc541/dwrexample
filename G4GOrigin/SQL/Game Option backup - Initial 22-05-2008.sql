--
-- PostgreSQL database dump
--

-- Started on 2008-05-22 12:39:53

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1321 (class 1259 OID 223347)
-- Dependencies: 5
-- Name: gameoptions; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gameoptions (
    optionid integer NOT NULL,
    valueid integer NOT NULL,
    gameid integer NOT NULL,
    "option" character varying(255),
    value character varying(255),
    optionsequenceid integer,
    valuesequenceid integer
);


ALTER TABLE public.gameoptions OWNER TO postgres;

--
-- TOC entry 1678 (class 0 OID 223347)
-- Dependencies: 1321
-- Data for Name: gameoptions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 1, 'Type', 'Slayer', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 1, 'Type', 'King of the Hill', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 3, 1, 'Type', 'Oddball', 1, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 4, 1, 'Type', 'Juggernaut', 1, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 5, 1, 'Type', 'CTF', 1, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 6, 1, 'Type', 'Assault', 1, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 7, 1, 'Type', 'Territories', 1, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 1, 'Map', 'Ascension', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 1, 'Map', 'Beaver Creek', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 1, 'Map', 'Burial Mounds', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 1, 'Map', 'Coagulation', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 1, 'Map', 'Foundation', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 6, 1, 'Map', 'Headlong', 2, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 7, 1, 'Map', 'Ivory Tower', 2, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 8, 1, 'Map', 'Lockout', 2, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 9, 1, 'Map', 'Midship', 2, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 10, 1, 'Map', 'Waterworks', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 11, 1, 'Map', 'Zanzibar', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 12, 1, 'Map', 'Backwash', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 13, 1, 'Map', 'Elongation Gemini', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 14, 1, 'Map', 'Relic', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 15, 1, 'Map', 'Terminal', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 16, 1, 'Map', 'Containment', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 17, 1, 'Map', 'Warlock', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 18, 1, 'Map', 'Sanctuary', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 1, 'TimeLimit', 'Turf 3-25 Kills  ', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 1, 'TimeLimit', 'Time Limit', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 16, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 16, 'Difficulty', 'All Pro', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 16, 'Qtr Length', '5 Minute Quarters ', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 21, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 21, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 23, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 23, 'Mode', 'Hardcore Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 23, 'Difficulty', 'All Pro', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 23, 'Difficulty', 'All Madden', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 23, 'Qtr Length', '4 Minute Quarters', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (4, 1, 23, 'Fair Play', 'On', 4, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (4, 2, 23, 'Fair Play', 'Off', 4, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 31, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 32, 'Type', 'VIP', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 32, 'Type', 'Territories', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 3, 32, 'Type', 'Land Grab', 1, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 4, 32, 'Type', 'CTF', 1, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 5, 32, 'Type', 'Oddball', 1, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 6, 32, 'Type', 'Assault', 1, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 7, 32, 'Type', 'King of the Hill', 1, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 8, 32, 'Type', 'Slayer', 1, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 32, 'Map', 'Epitaph', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 32, 'Map', 'High ground', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 32, 'Map', 'Construct', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 32, 'Map', 'Isolation', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 32, 'Map', 'Last Resort', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 6, 32, 'Map', 'Narrows', 2, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 7, 32, 'Map', 'Sandtrap', 2, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 8, 32, 'Map', 'Snowbound', 2, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 9, 32, 'Map', 'The Pit', 2, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 10, 32, 'Map', 'Valhalla', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 11, 32, 'Map', 'Guardian', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 12, 32, 'Map', 'Foundry', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 13, 32, 'Map', 'Rat’s Nest', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 14, 32, 'Map', 'Moonlight Sonata', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 15, 32, 'Map', 'OK Corral', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 32, 'TimeLimit', 'Purple Reignal 3-25 Kills ', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 32, 'TimeLimit', 'TimeLimit', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 33, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 33, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 39, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 39, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 39, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 39, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 39, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 39, 'Difficulty', 'SuperStar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 39, 'Qtr Length', '5 Minute Periods', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 39, 'Qtr Length', '10 Minute Periods', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 39, 'Qtr Length', '20 Minute Periods', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 41, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 41, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 41, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 41, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 41, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 41, 'Difficulty', 'SuperStar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 41, 'Difficulty', 'Starter', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 36, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 36, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 36, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 36, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 36, 'Difficulty', 'SuperStar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 36, 'Difficulty', 'Starter', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 30, 'Mode', 'Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 30, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 30, 'Qtr Length', '5 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 14, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 14, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 33, 'Qtr Length', '5 Minute Periods', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 33, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 33, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 33, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 33, 'Difficulty', 'Superstar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 33, 'Qtr Length', '10 Minute Periods', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 33, 'Qtr Length', '20 Minute Periods', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 36, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 19, 1, 'Map', 'Colossus', 2, 19);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 19, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 19, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 19, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 19, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 19, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 19, 'Difficulty', 'SuperStar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 19, 'Qtr Length', '5 Minute Periods', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 19, 'Qtr Length', '10 Minute Periods', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 19, 'Qtr Length', '20 Minute Periods', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 31, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 31, 'Qtr Length', '5 Minutes ', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 21, 'Qtr Length', '5 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 14, 'Qtr Length', '5 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 41, 'Qtr Length', '3 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 41, 'Qtr Length', '5 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 41, 'Qtr Length', '12 Minutes', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 36, 'Qtr Length', '3 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 36, 'Qtr Length', '5 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 36, 'Qtr Length', '12 Minutes', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 41, 'Qtr Length', '4 Minute Periods', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 41, 'Qtr Length', '6 Minute Periods', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 41, 'Qtr Length', '7 Minute Periods', 3, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 41, 'Qtr Length', '8 Minute Periods', 3, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 41, 'Qtr Length', '9 Minute Periods', 3, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 41, 'Qtr Length', '10 Minute Periods', 3, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 10, 41, 'Qtr Length', '11 Minute Periods', 3, 10);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 10, 36, 'Qtr Length', '11 Minute Periods', 3, 10);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 18, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 18, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 18, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 18, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 18, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 18, 'Difficulty', 'SuperStar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 18, 'Difficulty', 'Starter', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 18, 'Qtr Length', '3 Minute Periods', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 18, 'Qtr Length', '5 Minute Periods', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 18, 'Qtr Length', '12 Minute Periods', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 18, 'Qtr Length', '4 Minute Periods', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 18, 'Qtr Length', '6 Minute Periods', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 18, 'Qtr Length', '7 Minute Periods', 3, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 18, 'Qtr Length', '8 Minute Periods', 3, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 18, 'Qtr Length', '9 Minute Periods', 3, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 18, 'Qtr Length', '10 Minute Periods', 3, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 10, 18, 'Qtr Length', '11 Minute Periods', 3, 10);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 37, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 37, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 37, 'Difficulty', 'Professional', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 37, 'Difficulty', 'Semi-Pro', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 37, 'Difficulty', 'World Class', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 37, 'Difficulty', 'Legend', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 37, 'Qtr Length', '3 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 37, 'Qtr Length', '5 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 37, 'Qtr Length', '12 Minutes', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 37, 'Qtr Length', '4 Minutes', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 37, 'Qtr Length', '6 Minutes', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 37, 'Qtr Length', '7 Minutes', 3, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 37, 'Qtr Length', '8 Minutes', 3, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 37, 'Qtr Length', '9 Minutes', 3, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 37, 'Qtr Length', '10 Minutes', 3, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 10, 37, 'Qtr Length', '11 Minutes', 3, 10);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 11, 37, 'Qtr Length', '13 Minutes', 3, 11);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 12, 37, 'Qtr Length', '14 Minutes', 3, 12);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 13, 37, 'Qtr Length', '15 Minutes', 3, 13);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 14, 37, 'Qtr Length', '16 Minutes', 3, 14);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 38, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 38, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 38, 'Difficulty', 'Professional', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 38, 'Difficulty', 'Semi-Pro', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 38, 'Difficulty', 'World Class', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 38, 'Difficulty', 'Legend', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 38, 'Qtr Length', '3 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 38, 'Qtr Length', '5 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 38, 'Qtr Length', '12 Minutes', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 38, 'Qtr Length', '4 Minutes', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 38, 'Qtr Length', '6 Minutes', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 38, 'Qtr Length', '7 Minutes', 3, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 38, 'Qtr Length', '8 Minutes', 3, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 38, 'Qtr Length', '9 Minutes', 3, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 38, 'Qtr Length', '10 Minutes', 3, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 10, 38, 'Qtr Length', '11 Minutes', 3, 10);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 11, 38, 'Qtr Length', '13 Minutes', 3, 11);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 12, 38, 'Qtr Length', '14 Minutes', 3, 12);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 13, 38, 'Qtr Length', '15 Minutes', 3, 13);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 14, 38, 'Qtr Length', '16 Minutes', 3, 14);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 42, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 42, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 42, 'Difficulty', 'Professional', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 42, 'Difficulty', 'Semi-Pro', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 42, 'Difficulty', 'World Class', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 42, 'Difficulty', 'Legend', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 42, 'Qtr Length', '3 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 42, 'Qtr Length', '5 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 42, 'Qtr Length', '12 Minutes', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 42, 'Qtr Length', '4 Minutes', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 42, 'Qtr Length', '6 Minutes', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 42, 'Qtr Length', '7 Minutes', 3, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 42, 'Qtr Length', '8 Minutes', 3, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 42, 'Qtr Length', '9 Minutes', 3, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 42, 'Qtr Length', '10 Minutes', 3, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 10, 42, 'Qtr Length', '11 Minutes', 3, 10);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 11, 42, 'Qtr Length', '13 Minutes', 3, 11);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 12, 42, 'Qtr Length', '14 Minutes', 3, 12);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 13, 42, 'Qtr Length', '15 Minutes', 3, 13);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 14, 42, 'Qtr Length', '16 Minutes', 3, 14);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 36, 'Qtr Length', '4 Minutes', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 36, 'Qtr Length', '6 Minutes', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 36, 'Qtr Length', '7 Minutes', 3, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 36, 'Qtr Length', '8 Minutes', 3, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 36, 'Qtr Length', '9 Minutes', 3, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 36, 'Qtr Length', '10 Minutes', 3, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 44, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 44, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 44, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 44, 'Difficulty', 'Jr. Varsity', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 44, 'Difficulty', 'Varsity', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 44, 'Difficulty', 'All Conference', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 44, 'Qtr Length', '5 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 44, 'Qtr Length', '10 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 44, 'Qtr Length', '20 Minutes', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 11, 'Mode', 'Un-Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 11, 'Difficulty', 'Pick Up', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 11, 'Difficulty', 'Trick Battle', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 11, 'Difficulty', 'Dunks Only', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 11, 'Difficulty', 'Game Breaker', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 11, 'Qtr Length', '11', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 11, 'Qtr Length', '21', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 11, 'Qtr Length', '3', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 11, 'Qtr Length', '15', 3, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 11, 'Qtr Length', '5', 3, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 11, 'Difficulty', 'Back to the Basics ', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 6, 11, 'Difficulty', 'Quick Pick Up', 2, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 46, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 46, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 46, 'Difficulty', 'Featherweight', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 46, 'Difficulty', 'Lightweight', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 46, 'Difficulty', 'Welterweight', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 46, 'Difficulty', 'Middleweight', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 46, 'Difficulty', 'Heavyweight ', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 46, 'Qtr Length', '3 Minutes', 3, 1);


--
-- TOC entry 1676 (class 2606 OID 223515)
-- Dependencies: 1321 1321 1321 1321
-- Name: gameoptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gameoptions
    ADD CONSTRAINT gameoptions_pkey PRIMARY KEY (gameid, optionid, valueid);


--
-- TOC entry 1677 (class 2606 OID 223565)
-- Dependencies: 1319 1321
-- Name: FK_GameMaster_GameOptionMaster_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gameoptions
    ADD CONSTRAINT "FK_GameMaster_GameOptionMaster_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2008-05-22 12:39:54

--
-- PostgreSQL database dump complete
--

