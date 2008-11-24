--
-- PostgreSQL database dump
--

-- Started on 2008-05-22 12:42:15

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

INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (21, NULL, 1000003, 'NCAA Football 08', '/wg/images/gameart/ncaa08-ps2-178.png', 'wg/images/banners/ncaafootball08ps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (44, NULL, 1000004, 'NCAA BBL 08', '/wg/images/gameart/ncaa08-ps3-178.png', 'wg/images/banners/ncaabbl08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (11, NULL, 1000002, 'NBA Street', '/wg/images/gameart/NBA Street 08 - 360.jpg', 'wg/images/banners/nbastreetxbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (16, NULL, 1000002, 'Madden 08', '/wg/images/gameart/Madden 08 - 360.jpg', 'wg/images/banners/madden08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (13, NULL, 1000002, 'Nascar 08', '/wg/images/gameart/NASCAR 08 - 360.jpg', 'wg/images/banners/nascar08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (14, NULL, 1000002, 'NCAA FOOTBALL 08', '/wg/images/gameart/NCAA FOOTBALL 08 - 360.jpg', 'wg/images/banners/ncaafootball08xbox360.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (7, NULL, 1000003, 'MLB 08 the Show', '/wg/images/gameart/MLB The Show-ps2.jpg', 'wg/images/banners/MLB08theShowps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (20, NULL, 1000003, 'Nascar 08', '/wg/images/gameart/nascar 08 -ps2.jpg', 'wg/images/banners/nascar08ps2.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (23, NULL, 1000003, 'Madden 08', '/wg/images/gameart/Madden 08 -ps2.jpg', 'wg/images/banners/madden08xps2.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-madden08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (4, NULL, 1000004, 'MotorStorm', '/wg/images/gameart/Motorstorm Cover.jpg', 'wg/images/banners/motorstormps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (5, NULL, 1000004, 'Resistance Fall of Man', '/wg/images/gameart/Resistance Fall of Man Cover.jpg', 'wg/images/banners/resistancefallofmanbanner.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (28, NULL, 1000004, 'NBA Street', '/wg/images/gameart/NBA Street cover.jpg', 'wg/images/banners/nbastreetps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (30, NULL, 1000004, 'NCAA Football 2008', '/wg/images/gameart/NCAA Football 08 Cover.jpg', 'wg/images/banners/ncaafootball08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (31, NULL, 1000004, 'Madden 08', '/wg/images/gameart/Madden 08 Cover.jpg', 'wg/images/banners/madden08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (42, NULL, 1000004, 'Fifa 08', '/wg/images/gameart/Fifa 08 cover.jpg', 'wg/images/banners/fifa08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (45, NULL, 1000004, 'Nascar 08', '/wg/images/gameart/Nascar 08 Cover.jpg', 'wg/images/banners/nascar08ps3.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (1, NULL, 1000002, 'Halo 2', '/wg/images/gameart/Halo 2 - 360.jpg', 'wg/images/banners/gamelobby-Halo2.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-halo3.jpg', '/doc/introduction.html', '/doc/multilayersupport.html', '/doc/headtoheadfaq.html');
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (2, NULL, 1000004, 'Tiger Woods 08', '/wg/images/gameart/Tiger Woods PGA Tour 08 cover.jpg', 'wg/images/banners/gamelobby-TigerWoods08.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-tigerwoods08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (3, NULL, 1000004, 'NCAA March Madness 08', '/wg/images/gameart/NCAA Basketball 08.jpg', 'wg/images/banners/gamelobby-Madden08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (6, NULL, 1000003, 'NCAA March Madness 08', '/wg/images/gameart/ncaa basketball 08-ps2.jpg', 'wg/images/banners/gamelobby-Madden08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (8, NULL, 1000002, 'NCAA March Madness 08', '/wg/images/gameart/Madden 08 - 360.jpg', 'wg/images/banners/gamelobby-Madden08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (37, NULL, 1000002, 'Fifa 08', '/wg/images/gameart/FIFA 08 - 360.jpg', 'wg/images/banners/gamelobby-FIFA08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (38, NULL, 1000003, 'Fifa 08', '/wg/images/gameart/fifa08-ps2-178.png', 'wg/images/banners/gamelobby-FIFA08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (32, NULL, 1000002, 'Halo 3', '/wg/images/gameart/halo3-360.png', 'wg/images/banners/gamelobby-Halo3.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-halo3.jpg', '/doc/introduction.html', '/doc/multilayersupport.html', '/doc/headtoheadfaq.html');
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (18, NULL, 1000003, 'NBA Live 08', '/wg/images/gameart/NBA Live 08 -ps2.jpg', 'wg/images/banners/gamelobby-NBALive08.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-nbalive08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (36, NULL, 1000002, 'NBA Live 08', '/wg/images/gameart/NBA Live - 360.jpg', 'wg/images/banners/gamelobby-NBALive08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (41, NULL, 1000004, 'NBA Live 08', '/wg/images/gameart/nba live 08 cover.jpg', 'wg/images/banners/gamelobby-NBALive08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (19, NULL, 1000003, 'NHL 08', '/wg/images/gameart/nhl08-ps2-178.png', 'wg/images/banners/gamelobby-NHL08.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-nhl08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (33, NULL, 1000002, 'NHL 08', '/wg/images/gameart/NHL 08 - 360.jpg', 'wg/images/banners/gamelobby-NHL08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (39, NULL, 1000004, 'NHL 08', '/wg/images/gameart/nhl 08 cover.jpg', 'wg/images/banners/gamelobby-NHL08.jpg', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (27, NULL, 1000002, 'Tiger Woods 08', '/wg/images/gameart/Tiger 08 - 360.jpg', 'wg/images/banners/gamelobby-TigerWoods08.jpg', NULL, '/wg/css/images/banners/tournaments/banners-tournament-tigerwoods08.jpg', NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (9, NULL, 1000002, 'Project Gotham Racing 4', '/wg/images/gameart/pgr4-360-178.png', '/wg/images/banners/projectgothamracing4XBOX360.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO game (gameid, categoryid, networkid, gamename, imgsrc, bannerimgsrc, minplaytimeinmin, tournamentbannerimgsrc, introduction, multilayersupport, headtoheadfaq) VALUES (46, NULL, 1000004, 'Fight Night Round 3 ', '/wg/images/gameart/fightnight-ps3-178.png
', 'wg/images/banners/
fightnightbanner.jpg', NULL, NULL, NULL, NULL, NULL);


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


-- Completed on 2008-05-22 12:42:15

--
-- PostgreSQL database dump complete
--

