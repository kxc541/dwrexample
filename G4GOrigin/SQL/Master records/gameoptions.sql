--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:40:36

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
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 19, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 19, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 19, 'Speed', 'Slow', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 19, 'Speed', 'Normal', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 19, 'Speed', 'Fast', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 19, 'Qtr Length', '5,10,15,20 Minute Periods', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 19, 'Qtr Length', '5 Minute Periods', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (4, 1, 19, 'Rules', 'NHL Rules', 4, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (4, 2, 19, 'Rules', 'NHL or International Rules', 4, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 21, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 21, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 21, 'Qtr Length', '5 Minute Quarters', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 23, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 23, 'Mode', 'Hardcore Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 23, 'Difficulty', 'All Pro', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 23, 'Difficulty', 'All Madden', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 23, 'Qtr Length', '4 Minute Quarters', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (4, 1, 23, 'Fair Play', 'On', 4, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (4, 2, 23, 'Fair Play', 'Off', 4, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 11, 'Location', 'Gym', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 11, 'Location', 'Park', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 11, 'Location', 'Venice Beach', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 11, 'Location', 'Franklin Park', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 5, 11, 'Location', 'Dirt Bowl', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 6, 11, 'Location', 'Cloverdale Courts', 2, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 7, 11, 'Location', 'ASH Park', 2, 7);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 8, 11, 'Location', 'Jordan Gym', 2, 8);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 9, 11, 'Location', 'Rupert Bell Rec Center ', 2, 9);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 11, 'GamePickup', 'Pick Up (21)', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 11, 'GamePickup', 'Trick Battle (5)', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 11, 'GamePickup', 'Dunks Only (15)', 1, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 11, 'GamePickup', 'Gamebreaker (3)', 1, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 11, 'GamePickup', 'Back2Basics (21)', 1, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 6, 11, 'GamePickup', 'Quick Pick Up (11)', 1, 6);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 11, 'Mode', 'Un-Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 31, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 31, 'Difficulty', 'All Pro', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 31, 'Qtr Length', '5 Minute Quarters', 3, 1);
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
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 33, 'Qtr Length', 'No Time Limit', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 37, 'Difficulty', 'World Class', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 37, 'Difficulty', 'Legend', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 38, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 38, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 38, 'Qtr Length', '4 minute Halves', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 38, 'Qtr Length', '2 Minute Halves', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 38, 'Qtr Length', '4 Minute Halves', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 38, 'Qtr Length', '6 Minute Halves', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 38, 'Qtr Length', '8 Minute Halves', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 6, 38, 'Qtr Length', '10 Minute Halves', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 38, 'Difficulty', 'Amateur', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 38, 'Difficulty', 'Professional', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 38, 'Difficulty', 'Semipro', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 4, 38, 'Difficulty', 'World class', 3, 4);
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
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 41, 'Qtr Length', '3 Minute Quarters', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 41, 'Qtr Length', '5 Minute Quarters', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 41, 'Qtr Length', '12 Minute Quarters', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 42, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 42, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 42, 'Difficulty', 'Professional', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 42, 'Difficulty', 'Semi Pro', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 42, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 42, 'Difficulty', 'World Class', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 42, 'Difficulty', 'Legend', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 42, 'Qtr Length', '5 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 42, 'Qtr Length', '2-20 Minutes', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 44, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 44, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 44, 'Difficulty', 'Jr. Varsity', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 44, 'Difficulty', 'Varsity', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 44, 'Difficulty', 'All Conference', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 44, 'Difficulty', 'All American', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 44, 'Qtr Length', '10 Minute Halves', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 44, 'Qtr Length', '2-20 Minutes ', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 36, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 36, 'Difficulty', 'All Star', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 36, 'Difficulty', 'Rookie', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 36, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 4, 36, 'Difficulty', 'SuperStar', 2, 4);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 5, 36, 'Difficulty', 'Starter', 2, 5);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 36, 'Qtr Length', '3 Minute Quarters', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 36, 'Qtr Length', '5 Minute Quarters', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 3, 36, 'Qtr Length', '12 Minute Quarters', 3, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 37, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 37, 'Mode', 'Un-Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 37, 'Difficulty', 'Professional', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 2, 37, 'Difficulty', 'Semi Pro', 2, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 3, 37, 'Difficulty', 'Pro', 2, 3);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 2, 30, 'Mode', 'Ranked', 1, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 30, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 30, 'Qtr Length', '5 Minutes', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 37, 'Qtr Length', '5 Minute Halves', 3, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 2, 37, 'Qtr Length', '2-20 Minute Halves', 3, 2);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (1, 1, 14, 'Mode', 'Ranked', 1, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (2, 1, 14, 'Difficulty', 'All American', 2, 1);
INSERT INTO gameoptions (optionid, valueid, gameid, "option", value, optionsequenceid, valuesequenceid) VALUES (3, 1, 14, 'Qtr Length', '5 Minute Quarters', 3, 1);


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


-- Completed on 2008-05-16 19:40:37

--
-- PostgreSQL database dump complete
--

