--
-- PostgreSQL database dump
--

-- Started on 2008-05-16 19:40:19

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1319 (class 1259 OID 223340)
-- Dependencies: 5
-- Name: game; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE game (
    gameid integer NOT NULL,
    categoryid integer,
    networkid integer,
    gamename character varying(255),
    imgsrc character varying(255),
    bannerimgsrc character varying(255),
    minplaytimeinmin integer,
    tournamentbannerimgsrc character varying(255),
    introduction character varying(255),
    multilayersupport character varying(255),
    headtoheadfaq character varying(255)
);


ALTER TABLE public.game OWNER TO postgres;

--
-- TOC entry 1679 (class 0 OID 223340)
-- Dependencies: 1319
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (1, NULL, 1000002, 'Halo 2', '/wg/images/gameart/halo2-xbox360.png', 'wg/images/banners/halo2banner.png', NULL, '/wg/css/images/banners/tournaments/banners-tournament-halo3.jpg', '/doc/introduction.html', '/doc/multilayersupport.html', '/doc/headtoheadfaq.html');
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (2, NULL, 1000004, 'Tiger Woods 08', '/wg/images/gameart/tw08-ps3-178.png', 'wg/images/banners/tigerwoods08banner.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-tigerwoods08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (4, NULL, 1000004, 'MotorStorm', '/wg/images/gameart/motorstorm-ps3-178.png', 'wg/images/banners/motorstormps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (3, NULL, 1000004, 'NCAA March Madness 08', '/wg/images/gameart/ncaamm08-ps3-178.png', 'wg/images/banners/NCAAMarchMadness08ps3.jp', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (6, NULL, 1000003, 'NCAA March Madness 08', '/wg/images/gameart/ncaamm08-ps2-178.png', 'wg/images/banners/NCAAMarchMadness08ps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (5, NULL, 1000004, 'Resistance Fall of Man', '/wg/images/gameart/resistancefom-ps3-178.png', 'wg/images/banners/resistancefallofmanbanner.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (7, NULL, 1000003, 'MLB 08 the Show', '/wg/images/gameart/mlb08theshow-ps2-178.png', 'wg/images/banners/MLB08theShowps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (8, NULL, 1000002, 'NCAA March Madness 08', '/wg/images/gameart/ncaamm08-360-178.png', 'wg/images/banners/NCAAMarchMadness08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (9, NULL, 1000002, 'Project Gotham Racing 4', '/wg/images/gameart/pgr4-360-178.png', 'wg/images/banners/projectgothamracing4XBOX360.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (11, NULL, 1000002, 'NBA Street', '/wg/images/gameart/nbastreet-360-178.png', 'wg/images/banners/nbastreetxbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (18, NULL, 1000003, 'NBA Live 08', '/wg/images/gameart/nbalive08-ps2-178.png', 'wg/images/banners/nbalive08ps2.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-nbalive08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (19, NULL, 1000003, 'NHL 08', '/wg/images/gameart/nhl08-ps2-178.png', 'wg/images/banners/nhl08ps2.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-nhl08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (21, NULL, 1000003, 'NCAA Football 08', '/wg/images/gameart/ncaa08-ps2-178.png', 'wg/images/banners/ncaafootball08ps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (27, NULL, 1000002, 'Tiger Woods 08', '/wg/images/gameart/tw08-360-178.png', 'wg/images/banners/tigerwoods08xbox360.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-tigerwoods08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (28, NULL, 1000004, 'NBA Street', '/wg/images/gameart/nbastreet-ps3-178.png', 'wg/images/banners/nbastreetps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (36, NULL, 1000002, 'NBA Live 08', '/wg/images/gameart/nbalive08-360-178.png', 'wg/images/banners/nbalive08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (37, NULL, 1000002, 'Fifa 08', '/wg/images/gameart/fifa08-360-178.png', 'wg/images/banners/fifa08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (38, NULL, 1000003, 'Fifa 08', '/wg/images/gameart/fifa08-ps2-178.png', 'wg/images/banners/fifa08ps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (39, NULL, 1000004, 'NHL 08', '/wg/images/gameart/nhl08-ps3-178', 'wg/images/banners/nhl08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (41, NULL, 1000004, 'NBA Live 08', '/wg/images/gameart/nbalive08-ps3-178.png', 'wg/images/banners/nbalive08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (42, NULL, 1000004, 'Fifa 08', '/wg/images/gameart/fifa08-ps3-178.png', 'wg/images/banners/fifa08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (44, NULL, 1000004, 'NCAA BBL 08', '/wg/images/gameart/ncaa08-ps3-178.png', 'wg/images/banners/ncaabbl08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (13, NULL, 1000002, 'Nascar 08', '/wg/images/gameart/nascar08-360-178.png', 'wg/images/banners/nascar08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (14, NULL, 1000002, 'NCAA FOOTBALL 08', '/wg/images/gameart/ncaa08-360-178.png', 'wg/images/banners/ncaafootball08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (16, NULL, 1000002, 'Madden 08', '/wg/images/gameart/madden08-360-178.png', 'wg/images/banners/madden08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (20, NULL, 1000003, 'Nascar 08', '/wg/images/gameart/nascar08-ps2-178.png', 'wg/images/banners/nascar08ps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (23, NULL, 1000003, 'Madden 08', '/wg/images/gameart/madden08-ps2-178.png', 'wg/images/banners/madden08xps2.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-madden08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (30, NULL, 1000004, 'NCAA Football 2008', '/wg/images/gameart/ncaa08-ps3-178.png', 'wg/images/banners/ncaafootball08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (31, NULL, 1000004, 'Madden 08', '/wg/images/gameart/madden08-ps3-178.png', 'wg/images/banners/madden08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (32, NULL, 1000002, 'Halo 3', '/wg/images/gameart/halo3-360.png', 'wg/images/banners/halo3xbox360.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-halo3.jpg', '/doc/introduction.html', '/doc/multilayersupport.html', '/doc/headtoheadfaq.html');
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (33, NULL, 1000002, 'NHL 08', '/wg/images/gameart/nhl08-360-178.png', 'wg/images/banners/nhl08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (45, NULL, 1000004, 'Nascar 08', '/wg/images/gameart/nascar08-ps3-178.png', 'wg/images/banners/nascar08ps3.jpg', NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 1676 (class 2606 OID 223461)
-- Dependencies: 1319 1319
-- Name: PK_GameMaster_Gameid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "PK_GameMaster_Gameid" PRIMARY KEY (gameid);


--
-- TOC entry 1677 (class 2606 OID 223560)
-- Dependencies: 1319 1320
-- Name: FK_GameCategoryMaster_GameMaster_CategoryId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "FK_GameCategoryMaster_GameMaster_CategoryId" FOREIGN KEY (categoryid) REFERENCES gamecategory(categoryid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1678 (class 2606 OID 223600)
-- Dependencies: 1319 1325
-- Name: FK_NetworkMaster_GameMaster_NetworkId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "FK_NetworkMaster_GameMaster_NetworkId" FOREIGN KEY (networkid) REFERENCES network(networkid) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2008-05-16 19:40:19

--
-- PostgreSQL database dump complete
--

