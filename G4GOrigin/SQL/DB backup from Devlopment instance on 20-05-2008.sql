--
-- PostgreSQL database dump
--

-- Started on 2008-05-20 20:29:31

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1883 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


--
-- TOC entry 308 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1313 (class 1259 OID 223317)
-- Dependencies: 5
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "admin" (
    adminemail text,
    maxpicsize integer,
    maxpics integer,
    adminid integer NOT NULL
);


ALTER TABLE public."admin" OWNER TO postgres;

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
-- TOC entry 1315 (class 1259 OID 223327)
-- Dependencies: 5
-- Name: avatars; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avatars (
    avatarid integer NOT NULL,
    image bytea NOT NULL
);


ALTER TABLE public.avatars OWNER TO postgres;

--
-- TOC entry 1316 (class 1259 OID 223332)
-- Dependencies: 5
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE employee (
    employeeid integer NOT NULL,
    roleid integer,
    loginname character varying(16),
    passwordhash character varying(255),
    passwordhashsalt character varying(64)
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 1317 (class 1259 OID 223334)
-- Dependencies: 1678 1679 5
-- Name: feedbackreputation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE feedbackreputation (
    playerid integer NOT NULL,
    matchid integer NOT NULL,
    reputation integer DEFAULT 0,
    israted boolean DEFAULT false,
    feedbackcomment character varying(255),
    feedbackreputationid integer NOT NULL,
    feedbackdate time with time zone
);


ALTER TABLE public.feedbackreputation OWNER TO postgres;

--
-- TOC entry 1318 (class 1259 OID 223338)
-- Dependencies: 5
-- Name: friends; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE friends (
    friendsid integer NOT NULL,
    playerid1 integer,
    playerid2 integer,
    player1accepted timestamp without time zone,
    player2accepted timestamp without time zone
);


ALTER TABLE public.friends OWNER TO postgres;

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
-- TOC entry 1320 (class 1259 OID 223345)
-- Dependencies: 5
-- Name: gamecategory; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gamecategory (
    categoryid integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.gamecategory OWNER TO postgres;

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
-- TOC entry 1323 (class 1259 OID 223354)
-- Dependencies: 1680 1681 1682 1683 1684 5
-- Name: message; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE message (
    messageid integer NOT NULL,
    siteid character varying(255),
    fromplayerid integer,
    toplayerid integer,
    subject character varying(255),
    body text,
    isarchivedbysender boolean DEFAULT false,
    isread boolean DEFAULT false,
    isdeletedbysender boolean DEFAULT false,
    notificationtypeid integer,
    isarchievedbyreciever boolean DEFAULT false,
    isdeletedbyreciever boolean DEFAULT false,
    createddate timestamp without time zone,
    matchid integer,
    randomid character(50)
);


ALTER TABLE public.message OWNER TO postgres;

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
-- TOC entry 1326 (class 1259 OID 223371)
-- Dependencies: 5
-- Name: notificationqueue; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificationqueue (
    notificationid integer NOT NULL,
    recipientplayerid integer,
    sourceplayerid integer,
    notificationtypeid integer,
    body text,
    creationdate timestamp with time zone,
    expiredate timestamp with time zone,
    viewdate timestamp with time zone
);


ALTER TABLE public.notificationqueue OWNER TO postgres;

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
-- TOC entry 1328 (class 1259 OID 223378)
-- Dependencies: 5
-- Name: pictures; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pictures (
    id integer NOT NULL,
    userid integer NOT NULL,
    image bytea NOT NULL
);


ALTER TABLE public.pictures OWNER TO postgres;

--
-- TOC entry 1329 (class 1259 OID 223383)
-- Dependencies: 1685 1686 1687 1688 1689 1690 1691 1692 1693 1694 1695 5
-- Name: player; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE player (
    playerid integer NOT NULL,
    skinid character varying(255),
    screenname character varying(255) NOT NULL,
    loginname character varying(255),
    emailaddress character varying(255),
    avatarid integer,
    message text,
    story text,
    availability text,
    averageamount integer,
    prefaliases boolean DEFAULT false,
    prefsysmail boolean DEFAULT false,
    prefdisplayrecord boolean DEFAULT false,
    prefdisplaycontacts boolean DEFAULT false,
    preftimezone character varying(255),
    recordwins integer DEFAULT 0,
    recordlosses integer DEFAULT 0,
    creationdate timestamp without time zone,
    lastupdatedate timestamp without time zone,
    lastlogintime timestamp without time zone,
    skype character varying(255),
    aim character varying(255),
    yahoo character varying(255),
    msn character varying(255),
    phone character varying(255),
    isonline boolean DEFAULT false,
    reputation integer DEFAULT 0,
    prefcontactinfo boolean DEFAULT false,
    pictureid integer,
    CONSTRAINT "CK_Player_RecordLosses" CHECK ((recordlosses >= 0)),
    CONSTRAINT "CK_Player_RecordWins" CHECK ((recordwins >= 0))
);


ALTER TABLE public.player OWNER TO postgres;

--
-- TOC entry 1330 (class 1259 OID 223399)
-- Dependencies: 5
-- Name: playeradmincomments; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE playeradmincomments (
    "comment" text,
    player integer NOT NULL,
    employeeid integer NOT NULL,
    creationdate timestamp without time zone,
    id integer NOT NULL
);


ALTER TABLE public.playeradmincomments OWNER TO postgres;

--
-- TOC entry 1331 (class 1259 OID 223404)
-- Dependencies: 5
-- Name: playercomments; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE playercomments (
    targetplayerid integer,
    playerid integer,
    commentseq integer NOT NULL,
    creationdate timestamp without time zone,
    "comment" character varying(255)
);


ALTER TABLE public.playercomments OWNER TO postgres;

--
-- TOC entry 1332 (class 1259 OID 223406)
-- Dependencies: 5
-- Name: playergame; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE playergame (
    playergameid integer NOT NULL,
    gameid integer,
    playerid integer,
    creationdate timestamp without time zone,
    lastupdatedate timestamp without time zone
);


ALTER TABLE public.playergame OWNER TO postgres;

--
-- TOC entry 1333 (class 1259 OID 223408)
-- Dependencies: 1696 5
-- Name: playerim; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE playerim (
    imid integer NOT NULL,
    playerid integer,
    playerimtag character varying(255),
    creationdate timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    lastupdatedate timestamp without time zone,
    imnetworkid integer NOT NULL
);


ALTER TABLE public.playerim OWNER TO postgres;

--
-- TOC entry 1334 (class 1259 OID 223411)
-- Dependencies: 5
-- Name: playernetwork; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE playernetwork (
    networkid integer,
    playerid integer,
    playernetworktag character varying(255),
    creationdate timestamp without time zone,
    lastupdatedate timestamp without time zone,
    playernetworkid integer NOT NULL
);


ALTER TABLE public.playernetwork OWNER TO postgres;

--
-- TOC entry 1335 (class 1259 OID 223413)
-- Dependencies: 5
-- Name: sitecontent; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sitecontent (
    siteid integer NOT NULL,
    contentid integer,
    content text
);


ALTER TABLE public.sitecontent OWNER TO postgres;

--
-- TOC entry 1336 (class 1259 OID 223418)
-- Dependencies: 5
-- Name: sitecontentsection; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sitecontentsection (
    contentid integer NOT NULL,
    name character varying(16)
);


ALTER TABLE public.sitecontentsection OWNER TO postgres;

--
-- TOC entry 1337 (class 1259 OID 223420)
-- Dependencies: 5
-- Name: skin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE skin (
    skinid character varying(255) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.skin OWNER TO postgres;

--
-- TOC entry 1338 (class 1259 OID 223422)
-- Dependencies: 5
-- Name: subnationalcode; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE subnationalcode (
    subnatcodeid integer NOT NULL,
    islegal character varying(255),
    natcodeid character varying(255),
    nationcode character varying(255),
    localabbrev character varying(255),
    nationname character varying(255)
);


ALTER TABLE public.subnationalcode OWNER TO postgres;

--
-- TOC entry 1339 (class 1259 OID 223427)
-- Dependencies: 1697 5
-- Name: timezones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE timezones (
    id integer NOT NULL,
    timezone_location character varying(30) NOT NULL,
    gmt character varying(11) NOT NULL,
    "offset" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.timezones OWNER TO postgres;

--
-- TOC entry 1340 (class 1259 OID 223430)
-- Dependencies: 5
-- Name: twoplayermatch; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE twoplayermatch (
    matchid integer NOT NULL,
    tournamentid integer,
    "level" integer,
    playeroneid integer,
    playeroneaccepteddate timestamp without time zone,
    playertwoid integer,
    playertwoaccepteddate timestamp without time zone,
    winnerid integer,
    nextmatchid integer,
    payoutamount double precision,
    scheduledstartdate timestamp without time zone,
    completeddate timestamp without time zone,
    gamecompleted smallint,
    matchstatus character varying(255),
    resultbody text,
    enddate timestamp without time zone
);


ALTER TABLE public.twoplayermatch OWNER TO postgres;

--
-- TOC entry 1341 (class 1259 OID 223435)
-- Dependencies: 5
-- Name: twoplayermatchcomments; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE twoplayermatchcomments (
    playerid integer,
    commentseq integer,
    creationdate timestamp without time zone,
    "comment" character varying(255),
    matchid character varying,
    tournamentcommentsid integer NOT NULL
);


ALTER TABLE public.twoplayermatchcomments OWNER TO postgres;

--
-- TOC entry 1342 (class 1259 OID 223440)
-- Dependencies: 1698 5
-- Name: twoplayertournament; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE twoplayertournament (
    tournamentid integer NOT NULL,
    skinid character varying(255),
    betid character varying(255),
    levels integer,
    entryfee numeric(22,2),
    gameid integer,
    housefeeperplayer numeric(22,2),
    creationdate timestamp with time zone,
    completiondate timestamp with time zone,
    cancellationdate timestamp with time zone,
    createdby integer,
    jackpot double precision DEFAULT 0.00
);


ALTER TABLE public.twoplayertournament OWNER TO postgres;

--
-- TOC entry 1343 (class 1259 OID 223446)
-- Dependencies: 5
-- Name: twoplayertournamentadmincomments; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE twoplayertournamentadmincomments (
    tournamentid integer,
    employeeid integer,
    commentseq integer NOT NULL,
    creationdate timestamp without time zone,
    "comment" character varying(255)
);


ALTER TABLE public.twoplayertournamentadmincomments OWNER TO postgres;

--
-- TOC entry 1344 (class 1259 OID 223448)
-- Dependencies: 5
-- Name: twoplayertournamentgameoptions; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE twoplayertournamentgameoptions (
    tournamentid integer,
    optionid integer,
    valueid integer,
    twoplayertournamentgameoptionsid integer NOT NULL,
    comments character varying(255)
);


ALTER TABLE public.twoplayertournamentgameoptions OWNER TO postgres;

--
-- TOC entry 1848 (class 0 OID 223317)
-- Dependencies: 1313
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1849 (class 0 OID 223322)
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
-- TOC entry 1850 (class 0 OID 223327)
-- Dependencies: 1315
-- Data for Name: avatars; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO avatars (avatarid, image) VALUES (2, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000d\\000d\\000\\000\\377\\354\\000\\021Ducky\\000\\001\\000\\004\\000\\000\\000<\\000\\000\\377\\356\\000\\016Adobe\\000d\\300\\000\\000\\000\\001\\377\\333\\000\\204\\000\\006\\004\\004\\004\\005\\004\\006\\005\\005\\006\\011\\006\\005\\006\\011\\013\\010\\006\\006\\010\\013\\014\\012\\012\\013\\012\\012\\014\\020\\014\\014\\014\\014\\014\\014\\020\\014\\016\\017\\020\\017\\016\\014\\023\\023\\024\\024\\023\\023\\034\\033\\033\\033\\034\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\001\\007\\007\\007\\015\\014\\015\\030\\020\\020\\030\\032\\025\\021\\025\\032\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\037\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001\\021\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\231\\000\\000\\001\\005\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\006\\003\\004\\005\\007\\010\\001\\002\\000\\001\\000\\003\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\005\\004\\020\\000\\001\\003\\002\\004\\003\\005\\004\\004\\010\\014\\007\\000\\000\\000\\000\\001\\002\\003\\004\\021\\005\\0001\\022\\006!A\\023Qa"2\\007q3\\0244\\221BR\\025\\201b#S5\\0267\\010\\360\\241\\261\\301\\321r\\222\\242DU6\\027\\262\\302C$TE\\030\\021\\001\\001\\000\\002\\002\\002\\002\\002\\003\\000\\000\\000\\000\\000\\000\\000\\000\\001\\021\\002!1\\022\\003A\\004Q\\005q\\2412\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\253\\267\\015\\316,\\213\\333\\356\\265''\\252\\310U\\030\\256e=\\370\\313-,i\\017\\335\\212(\\375]\\270\\316BHm\\367\\303aG\\265\\011\\251\\003\\373X\\275QWV(\\237`\\010\\353\\336\\340\\263X\\341\\231wYm\\304c \\247\\015\\012\\217bFj>\\314\\001\\001l\\365Oj\\\\n\\010\\204\\313\\216\\266\\245\\360\\016<\\331i5\\345\\347\\241\\343\\333Jad\\360/\\303''p\\007\\330\\003\\235\\334\\260\\006;\\365\\252\\320\\255\\233\\352\\024\\246\\240\\270DI\\350\\370\\230\\315"\\276\\004\\274|l\\236\\355I\\2563\\274U\\300?\\353\\005\\313\\363.ve\\374^\\334\\031\\030''s\\332N\\333\\346+\\304\\323\\221\\365\\200\\343\\300\\237\\010$\\002J{\\206$\\333G\\322\\215\\265d\\333\\273\\032\\335\\012\\317+\\343\\242<\\237\\211T\\337\\3178\\3455*\\2254\\310&\\235\\330\\3263O\\335\\367\\015\\222\\314\\330r\\3471\\270\\241\\\\R\\225\\237\\022\\200\\373(\\025Q\\374\\003\\006B\\251\\336>\\264]R\\242\\335\\241\\015Z\\255\\253\\360\\242\\3470u$<2?\\013\\031''>\\302\\341\\030WeM@\\262f?p\\224\\251\\313R\\245\\255\\304\\020\\253\\215\\325en\\001\\317C)\\001\\010\\035\\303\\351\\304\\345R!\\242\\3579\\254[b8\\363\\311y\\177\\020\\264 ,\\000\\206\\224\\311\\372\\252\\363\\024\\232\\344y\\340\\233\\014,\\315\\257\\353\\233\\261\\264\\306\\275DS\\214\\214\\234d\\240\\255"\\225\\311JMx\\034?"\\272\\254\\353F\\374\\332\\227x.\\315\\201=\\267\\243\\262\\235o)5%)\\355)\\246\\257\\342\\305e8LB\\235\\016ttI\\206\\362\\037\\216\\340\\252\\034A\\2508d\\024\\365swJ\\332\\333&]\\306\\031\\002k\\212Dx\\352?UN\\235%c\\275#\\210\\302\\247\\031gp\\300\\023\\031\\267>\\207:\\327\\031%ky\\347\\026\\245\\2705\\021\\346\\325^|E1\\012\\301\\037\\324h\\237\\347\\007*s\\371\\236\\337\\352\\341\\340#\\367\\234\\231\\000\\260\\350\\247JS(tR\\225)<Ei\\317\\277<gcE\\361\\373\\260\\357\\026\\016\\313\\272[\\247<\\032\\215ew\\254\\037uZP\\333\\017\\017-U\\300\\000\\244\\023\\370q\\256\\265\\226\\323\\225G\\353/\\250\\023n\\036\\241\\313\\270\\333''\\227`\\244%\\213d\\266\\352\\222\\206\\364\\321A\\263\\232j~\\260\\317\\021j\\265\\212\\364\\\\\\272\\262\\312\\245\\027\\245\\255U\\012(qI_\\036\\327+\\250\\3758XZ]\\211\\360\\322\\246\\327o\\272\\334-\\357 %*b\\340\\367\\304G\\256J\\361\\246\\201\\011\\366\\342\\2600k>\\016\\340e\\324F\\223\\245\\250\\344\\031\\010\\234\\025\\256:\\220\\351\\367\\215\\254y\\207\\263\\011.9"\\312\\301\\323\\031\\203<p\\002e\\301\\325\\247R\\271\\251\\010h\\242\\203\\262\\270\\025\\342ql\\3347k=\\3253,\\2569\\002Rh\\2446\\207T\\264\\032q#\\304N\\264\\037\\262\\252\\340\\211\\272\\257\\317O\\177xM\\247o\\267>n\\320$By\\365\\365\\235Lf\\365\\260\\025@\\223\\241)\\245\\012\\210''\\027\\224XK\\326\\337U\\366n\\352\\330\\315D\\262\\313S\\222\\325-\\245\\230\\3566\\246\\326\\020\\221\\250\\253\\3042\\366amF\\263\\225F&%2\\242\\274\\263\\356\\202j\\017<(u!\\367\\323?e~\\373\\255\\365\\177\\243\\014\\032n\\247\\343\\310\\330[~x\\024\\013m\\346V\\2408\\2052\\252\\024c;\\333@\\312.s\\342[\\243@e\\325\\265\\022A\\244\\246\\320\\345[ui\\242\\222\\027N\\006\\201Y`\\275\\024N\\274\\324;\\245\\241m8\\200\\321d\\002\\333\\224\\314\\201B+\\212\\301\\004-\\360\\036|)!*J\\002\\207QI\\036*V\\206\\230\\313\\333\\277\\214{~\\237\\256o\\266*R\\365m\\262Gu\\277\\273\\326\\265\\351\\037\\224i\\\\x\\216J\\354\\2560\\372\\376\\335\\366\\267.\\227\\354~\\277\\247]g\\217\\177\\3119Qa\\276\\343\\177\\006\\313\\254E(H\\\\g\\227\\250\\005\\363\\245\\011\\240\\307\\266\\307\\002\\324\\335\\212\\337\\265\\343\\303\\234\\213\\274GeMZ\\024\\230e\\204\\352H\\341\\301@\\366\\203\\313\\034\\357\\261w\\233\\314\\177\\227s\\350\\353\\246\\332Y\\237\\355\\017{\\2639\\035P\\202\\001@\\350\\244\\272\\252\\236\\016(qI''\\036\\237F\\326\\312\\361~\\302k6\\222~\\004\\277t\\246\\016\\335T\\302\\257\\020@\\360r*U{q\\275s\\360\\032\\226\\224\\265:\\334\\322\\226\\027\\326i\\267T\\006TY\\007\\3710\\225"Z\\340\\337Jci\\003\\206hOq\\313\\026\\232\\365\\324_\\330Nt\\313\\370q\\302,\\234\\333\\355\\252\\273lh\\361x\\226\\343Ky\\316\\232s:\\325Uq\\343O\\243\\023b\\340i\\306Z]\\245\\370\\300\\224\\313\\217 =\\035\\272W\\301]*\\361\\014\\370$r\\300\\013}\\365\\015\\2249\\036\\341-z\\302*\\314x\\211\\326\\332TG\\225u\\245=\\265\\301(\\260\\367i\\334\\242\\246\\317qC\\316!\\265\\307\\037\\026\\206\\2248\\276\\201\\301MW\\227\\022\\223\\211\\333Mv\\355\\246\\236\\315\\264\\347[\\204tk\\253\\016\\276\\344\\231)GM%\\004FO\\2048\\267\\016\\224\\240\\257\\211H\\034\\3152\\303\\323I:\\036\\317n\\333wr;\\261m{\\245\\366\\323\\025\\346\\243A\\206\\211\\354\\334\\235@\\021\\\\Pav\\304\\205\\026\\013\\205\\324\\235N\\003\\346\\323\\215<Xy@d+\\303\\366\\367XYB\\343\\272\\342\\022\\247\\030\\250*iK@X\\322\\256 \\325\\012\\007,F\\332K9\\345\\246\\233\\335ne\\301\\033\\345\\330\\255l%\\365jm\\256\\013Rx\\225W\\211U0k$\\342\\015\\255\\274\\336\\323+\\236\\231\\266''\\032\\264M\\370\\330iZC\\214\\272\\235\\022\\033\\000\\017\\036\\232\\253\\301RFxh\\260\\3142\\335\\317u\\262\\026\\232"\\033m\\241A\\002\\243Dt\\015Ye\\345\\301;RU\\330OK\\271\\311\\177\\312\\303j\\252I<\\002x\\012\\327\\0155\\343\\342\\342}\\237\\372\\275\\037)\\313\\363\\230\\015\\335\\263qb\\322\\266\\227\\325\\244YTR\\251\\307On\\240;\\260\\2454\\016\\345\\202\\374\\033\\253\\215EX\\0163\\254\\245\\300h4\\257\\306\\010\\354\\340\\274*#\\316\\316\\264&T\\204G,\\365V\\352J\\265\\016T\\347\\3758pTM\\371\\270\\260.\\217\\306\\210\\360\\223\\030+\\302\\272yO1\\337N\\334*\\014K\\311F\\231\\032z\\201\\267\\022\\2654rPI\\257\\034\\031\\002\\377\\000\\367\\227v\\256L\\271\\021[i\\224J\\225*Z\\220\\256!\\037\\030\\327IhE2\\001\\004\\214W\\222<\\002\\306Z\\313\\011S`6\\226\\326\\012P\\011U(\\200\\221B\\252\\237(\\031\\342V}j\\213"\\345y\\217\\030%K\\012\\004\\250''\\211\\242F\\003\\310\\212\\343`~\\324\\342o\\020\\177 \\250zP\\353n \\245\\013*\\255\\005E5\\003\\317\\017\\011\\224\\265\\221\\213\\234[\\314\\244\\250t\\345I\\214\\227d\\000\\000\\000H:\\317\\260\\024\\253\\004\\354e#v\\2727\\002\\003q\\031\\323\\245B\\2578<\\312''!\\202\\210\\213\\370\\327?2\\257&\\254\\371\\366\\340\\311\\037\\317\\330\\333\\257d\\306\\267\\334\\367U\\234\\374\\004\\204\\005\\241)>\\004\\253\\352\\241\\365&\\272\\025\\355\\302\\274*YC\\027K\\253\\227[\\333\\263:id\\311B\\201i5RG\\204$i\\374\\011\\301*\\260c\\033p\\271\\002*\\020\\363%\\304\\004\\251-\\264\\205\\251\\225+Vz\\234G\\213O\\342\\363\\301\\222\\246\\367g.\\022\\004y/Cf\\023.\\202Yi\\204hI\\011\\315G\\267<\\360\\021{4(\\262\\031\\232\\227I\\012\\020\\235[e54Z\\001"\\240v\\341\\220\\272\\333\\351\\024f\\366C\\373\\202l\\367z\\352\\201l\\270\\302\\216\\322R\\002\\333\\236\\377\\000E\\346\\310$\\022Q\\303I\\355\\305x3\\273TV\\376\\331\\254\\355k\\355\\342\\314\\303\\216>\\325\\272[-%\\367\\222\\022\\341K\\321\\232x%T\\346\\236\\245\\016\\025\\213\\326\\345\\017i\\270E\\264\\334\\321*T7&\\244$t\\322\\333\\252eIP5\\256\\244\\342U\\201l\\335\\300\\365\\372\\326"\\304Y~\\330\\035J\\317\\\\\\377\\000\\334E\\004\\215Ip\\216\\013\\343\\222\\253\\206F3no\\015\\311ts_\\230\\206t\\247* iH\\036\\314,\\250i\\261}"\\334\\333\\355\\245\\334b\\270\\334X,{\\2312\\001Sn\\270>\\250\\245k\\355\\305H\\213^\\277\\333=\\365\\376Z\\347\\315|\\006J\\367\\337o/u\\337\\203\\011\\313]\\314\\205\\016lg"\\314e\\022#:\\012\\\\e\\304\\205%@\\366\\203\\215\\022\\314\\277\\274\\037\\246{\\017k\\307\\207:\\303\\021p\\256\\223\\234P\\021\\333Y1\\364$x\\216\\203R\\024k\\300\\327\\031\\355\\252\\365\\332\\250V\\255\\354\\274\\205-\\352\\227\\221\\341_w\\260bcD\\355\\370\\265#`\\332\\2464\\241\\327\\265\\272\\344I(\\346R\\355\\012\\011\\376\\311\\303 \\335\\252\\361&\\333-\\251\\254$\\007\\232\\342\\020\\342u6\\240sJ\\223\\303RH\\314`\\310?N\\371\\272\\245\\264\\264\\236\\230m\\010\\351\\264\\330J\\250\\332\\001\\250B\\006\\252\\004\\244\\361\\003\\226\\014\\224\\220\\306U\\362t\\365\\025N\\224\\343\\334u\\225\\270u\\022\\272\\001Rs&\\203\\236\\026O\\204\\275\\234Fz\\325s\\237!\\011Tt\\240GaJ\\317\\250F\\240G\\321\\200\\316\\343B\\227\\016\\345j\\223\\011\\323\\015s\\032\\010.4+\\253\\201\\324\\024\\203Pr\\030t\\240\\257\\321\\033\\016\\335\\277\\372\\206\\324M\\306\\322\\344\\265''\\250\\353-\\205\\351mN\\246\\253\\322\\340\\002\\252\\007!B0k9N\\315\\235\\016\\034HQ[\\213\\021\\2241\\031\\224\\2044\\313`%)H\\344\\000\\306\\254\\313`\\017\\260\\006t\\375\\343\\030J\\367\\305\\221\\266\\335uo>\\307\\026~\\242B\\012\\210RG\\362\\342oj\\325\\237\\336\\2131\\247gMy\\262\\334\\015z\\227(\\012\\267G8\\241!]\\244\\036\\030\\210\\324\\306Z\\334\\227\\010\\010\\210q\\250\\217\\360p-UK\\216#5Pp\\341\\\\\\035\\221W\\265*\\320Z1\\324\\231M\\200\\332\\024\\201T,e\\250\\236Da\\330\\014\\014g\\033C\\016!!*\\003\\300\\2609\\247:\\327\\006\\001u5\\361\\211-\\263\\032\\222\\234\\314$Q\\011#5\\216\\317f\\013\\001v\\244\\364`\\033E\\300\\257\\356\\246]2\\244. It\\253IH\\342A\\024\\343\\205\\200\\237ajn\\347\\035\\365:\\236\\203P\\334v3\\241uI\\032@HM>\\271\\310\\201\\200C\\375\\201xv\\024\\333e\\3323^8/\\266\\026\\342s:\\000%\\024\\355 `\\325;7\\004\\031\\214\\315\\204\\304\\266N\\246\\237B\\\\A\\356P\\2565f[\\000w\\000g\\337[\\247BW\\250\\260\\220\\343\\375#\\002\\012\\234QO\\236\\252\\325\\300~\\014E\\274\\257X\\315\\355\\307\\274n\\011\\226\\373Lgi\\005\\347\\220\\334x\\345GBT\\243J\\236\\322\\234D\\215i\\360[\\320\\332T\\010\\355"t$<\\242\\206\\337\\005\\013K\\200\\351Z\\232px\\320\\025\\247\\216-\\0318e\\326ZS\\202Lyv\\366\\335\\000\\244{\\366hy\\251J\\374\\241\\003\\270a\\007\\025\\011\\205\\331\\344\\275\\031A\\346P\\377\\000M\\267Bh\\010Z\\207\\332\\242\\205i\\314`\\265R\\221\\226-\\261\\213\\215=%A\\3608\\306\\214\\235K*\\354R\\262\\037N\\027 \\321\\365\\270\\354u\\305b\\334!G\\226\\022\\322\\334$\\270\\372\\274`\\200\\024\\256\\3220\\007\\317-\\353K*\\262\\334\\233\\324JJ\\2428O\\227W!\\335\\205\\220\\367\\264\\035\\\\iOGs\\201Z\\017L\\250\\321%`xN\\034Mk\\257@\\267\\263W\\315\\240\\335\\252K\\211\\373\\316\\323\\371\\025 \\232)l\\375G)\\317\\260\\323\\032kY\\330\\264p\\310\\233\\357\\264\\303\\016>\\352\\202Zi%kQ\\310%"\\244\\340\\014s\\275\\356n_7|\\275\\321/\\205\\275\\307\\324\\322\\024\\341=>\\206\\222\\200\\220G\\365k\\214\\232N\\025\\253R\\356\\037\\013\\002\\335lJ\\225qjIu\\2457N\\245@\\255G\\263<\\037\\012NI\\334\\014\\312\\271+\\357\\270DI*\\011\\2252\\020\\342\\342\\200\\342\\2657\\300)]\\264#\\017\\222\\260\\367\\357\\233D\\230\\257\\333\\341\\314wS\\210!\\206\\345\\266\\020B\\206ZU\\\\\\310\\3000f\\305\\236[\\314]\\334}F\\335\\035\\200\\323\\316\\302A\\005.$!Z\\024O?\\027f\\025\\246\\361laZ\\030\\231+\\341m\\211\\322\\011y\\\\uj\\036\\032 \\222s\\347\\200<\\267\\177\\267\\304*~4%\\334\\344\\240\\216\\234\\247\\301J\\022\\006z\\0222\\343\\203#\\002\\215\\315\\351\\344\\013\\247\\245c\\177\\242\\362]\\273[h\\233\\235\\271m\\204\\004%k\\360!$)F\\242\\265\\257\\326\\356\\246\\0138N~\\001\\214-\\002+k@\\005\\326\\251\\245c\\351\\007\\024w\\261\\225\\236L\\373d\\204^-\\257\\252;\\215\\200\\364g[$p\\034J\\025N]\\240\\341t\\233\\026w\\377\\000I\\336\\177\\361#|\\256\\256~\\367\\355\\347\\227\\342\\342\\362\\234\\013o?\\260\\213\\237\\351?\\223\\377\\000\\027\\363>a\\375\\336\\334/\\203g\\253\\257\\354\\216\\323\\357>m\\234\\262\\315X\\212\\270\\023\\332\\037\\353h\\031\\371]\\367~\\177v|\\275\\370P\\353\\277\\373w|\\377\\0002\\257.y\\342\\250JnO''\\370_\\253\\347\\366\\177\\305\\200\\211\\332\\377\\000D^\\275\\367\\311\\247\\337\\347\\370;\\2730\\224\\030?1\\023?v\\217\\234\\366\\017''\\342\\366a\\024\\037?\\3724\\374\\276i\\3679a\\021\\254\\377\\000\\331\\344\\277\\177\\363\\021\\375\\307\\313y\\234\\371\\217\\371;\\353\\212\\275\\017\\220\\355\\263\\345\\271\\377\\0006\\034=\\206\\326_\\364\\224\\274\\374\\313\\362\\347\\227.\\354\\025 \\017\\247\\370\\1776\\021?\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (3, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000d\\000d\\000\\000\\377\\354\\000\\021Ducky\\000\\001\\000\\004\\000\\000\\000\\036\\000\\000\\377\\356\\000\\016Adobe\\000d\\300\\000\\000\\000\\001\\377\\333\\000\\204\\000\\020\\013\\013\\013\\014\\013\\020\\014\\014\\020\\027\\017\\015\\017\\027\\033\\024\\020\\020\\024\\033\\037\\027\\027\\027\\027\\027\\037\\036\\027\\032\\032\\032\\032\\027\\036\\036#%''%#\\036//33//@@@@@@@@@@@@@@@\\001\\021\\017\\017\\021\\023\\021\\025\\022\\022\\025\\024\\021\\024\\021\\024\\032\\024\\026\\026\\024\\032&\\032\\032\\034\\032\\032&0#\\036\\036\\036\\036#0+.''''''.+550055@@?@@@@@@@@@@@@\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\222\\000\\000\\003\\000\\003\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\004\\005\\006\\000\\003\\007\\002\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\000\\003\\020\\000\\002\\001\\002\\005\\001\\005\\004\\006\\007\\006\\007\\001\\000\\000\\000\\001\\002\\003\\021\\004\\000!1\\022\\005\\023AQ"2\\006aq3\\024\\201BRb#S\\221\\241rc4\\0255\\261\\301\\341\\361\\202C\\360\\302s$Tt%\\026\\021\\000\\002\\001\\004\\002\\002\\002\\003\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\021!AQ\\0021\\022q2a"\\221\\261\\341\\003\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000~y\\333\\016\\264\\267ov\\315\\024p\\365\\014\\013U\\330\\206\\245| \\370\\231\\266\\234`\\216\\303\\235\\266\\022\\331\\\\n\\237\\304Lnv\\346i]\\350+P\\265\\354\\367W\\034\\303\\222\\236H\\256\\230A%ae+\\021\\037\\224\\344\\235\\271\\373\\360\\307\\200\\344Z\\335\\3406\\304\\245\\300uF\\000\\320\\264`\\227)\\227c\\035q\\035nT\\215n \\3448\\236I\\244\\343$\\026\\375 z\\250\\016\\305\\360\\375\\326$\\262\\235|Z\\341\\362z\\311ox\\367Hbh\\257B\\355y\\005:*\\304j\\255\\273vt\\313,\\216\\000\\365\\034\\221\\362\\226\\261r\\334\\177Me\\267\\221\\242\\274j\\232\\032P\\005\\241\\036*\\322\\225\\247\\323\\204p\\335\\364\\330\\250VH\\244!\\025\\266\\376\\035Kv\\000\\243,\\206xT\\253\\231\\303\\260\\343\\371\\354\\222M\\306\\314\\255"\\210\\037\\361\\220\\320,\\233\\252\\221\\311\\337\\270\\253P\\340)=O\\310\\301t\\362Zm\\020\\314\\341\\344Q\\342y]\\311\\311W\\261\\201cOn\\025]\\336:\\207\\014\\30023\\306i\\367v\\032\\376\\274;\\343xy\\354\\342\\022G\\003\\265\\374\\306\\262\\355q\\034\\221$\\2655\\214m\\252\\370N\\265\\015\\335\\226\\023\\016b\\266\\270\\266\\264\\013r\\316\\360*\\226\\026\\016\\216\\221\\231\\030\\015\\275ir\\016\\240e\\323\\025^\\372\\343.:\\215\\021\\3449YV\\335$\\244q<\\033\\220\\242\\234\\250br\\301\\366\\236\\314\\215+\\226X\\313k{^\\036\\331\\2579k\\206\\231Y\\272\\226\\335c/X\\312\\016\\233\\272\\257\\333N\\314K\\362\\234\\355\\335\\372\\262\\316\\333\\374f7 P+\\003\\341\\242\\232\\323LK\\227\\341\\217\\354\\373{}\\304\\332\\314 Dk\\220\\265\\374Bv\\307\\324\\031\\215\\252we\\2156\\334\\345\\312\\312\\211\\023\\233d\\222l\\242\\267%\\002\\367\\225\\241\\256x[{n\\361\\333A,\\204~>\\347\\214\\0173\\000v\\227>\\315\\331\\017\\247\\003\\333\\312\\026]\\341\\250B\\220\\247\\273wn\\024\\250\\023S\\247\\372w\\325q\\336\\354\\262\\277u[\\302JG(\\311''d\\310\\323\\263\\177x\\031wwb\\223\\034f\\013Y\\036\\326K\\311$6\\366\\201\\204V\\365\\314\\311&\\264\\024 \\373I\\030\\251\\377\\000\\365\\374\\257\\346G\\3747GA\\361\\377\\000\\3625\\323\\331\\206B\\011\\217P[-\\273\\313l\\271|\\264\\362F\\024\\216\\315\\306\\233[\\272\\230Q\\033M\\016\\331\\323r\\215\\324I\\005@\\334\\264$\\003\\336*1i\\353K\\001\\027-tv\\376\\025\\314i1\\001\\366\\215\\342\\253QZ\\215T\\327\\0118wNG\\212\\272\\340\\032\\2131\\177\\234\\260c\\225fE\\331$U\\373\\351\\247\\264`\\230O\\340\\321-|\\204qW\\355\\374\\252km\\364\\217~\\366\\005Ku\\015MY\\266\\324\\344\\264\\300\\357y\\000g\\350H\\315\\326VR\\214Y\\252\\331\\024u\\015\\230\\301\\276\\221\\342\\256\\257\\346\\272\\267\\205\\007M\\002\\211\\214\\207h\\214\\265AR5>\\\\y\\266\\364\\217*\\374\\224\\326/\\013[\\312\\265x\\346pZ6\\003\\303\\274H\\005\\016\\265\\301)7%Ci\\002\\361\\226\\227\\\\\\277!\\266\\000cD=W\\220j\\201YK04\\363W,?\\266\\216\\007Y\\026cs\\320I\\030K-\\273\\020\\302Z\\326\\254d\\363\\373q\\250zbx$\\221\\255\\256]-\\000\\331$\\210N\\346\\317s\\320\\351\\256=\\362~\\241\\341\\241\\2656\\226A\\222x@Z\\000Yd\\312\\224=\\376\\374F\\337\\351\\332\\026\\225\\311kN\\262\\366\\240\\273\\324\\274\\341\\344e\\026\\220J\\306\\322\\314\\207\\201\\236\\273\\213\\323-\\305\\205M=\\275\\375\\272\\343G\\015n9^MU\\217F9\\027\\251t\\372,q\\306\\003I!''!\\334\\011\\3558H\\322;\\273\\350\\013\\020B\\346jt\\246+V\\322\\337\\206\\343\\022\\316Y\\224^\\362\\205M\\344\\200\\345\\015\\232\\032\\262\\216\\340\\315E\\373\\331\\342\\366kX\\313!&\\374#\\307-\\351\\316c\\225\\205\\371\\313t_\\226X\\325m\\354\\226\\275D\\201\\005\\021#ZS\\302\\272\\347Rk\\226''\\370\\2738o\\256zsK\\362\\366\\310\\245\\347\\237ip\\212\\277\\263\\337\\246x\\352\\336\\231\\275\\212\\347\\213P<\\022B\\317\\325B|\\241\\231\\235O\\354\\2259\\037\\243\\263\\021|\\205\\344v\\034\\024\\342\\315%\\264^ZigP\\361\\355C\\013\\023\\261Wh\\323i\\032\\341R\\222\\310:\\267\\202w\\220\\275\\023\\\\7EQ"\\214\\354\\205#\\024L\\262.\\027\\275\\273pN\\331\\177u\\360\\267i\\205p\\245\\030\\260\\317og\\267\\007\\365\\356~\\324_\\017\\177\\321\\366p\\300H\\373\\234NF\\316x \\273\\230\\274\\320\\000\\251\\3244\\212d\\037U5#_\\362\\304\\224\\373\\222\\351\\33250\\262\\261e\\000\\346\\2645\\311\\207w~:\\224\\260\\374\\367\\377\\000#\\224E\\216d\\337\\320*\\276\\035\\275\\216\\207\\303\\340\\314T\\017v\\272Cr\\274\\025\\345\\214\\322GN\\274\\010K+\\212x\\200\\313q\\247\\226\\275\\230\\023\\311M`\\313\\016~x\\245k\\253y^\\327\\221\\222:4\\250\\003\\244\\362\\251\\360\\254\\221P\\371\\301"\\277k<Q\\333\\362~\\240\\345\\2549+\\351f\\371u\\265\\213;{}\\031\\332=\\3319\\334R\\231n\\025\\310\\327L+\\364u\\275\\214\\242\\356\\331\\327\\243\\3148ca+TSj\\235\\353\\031\\354}~\\214\\3731Qo\\305\\311{\\304Ou\\310<\\266\\267\\\\\\2133\\272G$\\220\\307\\024@t\\321JUWoMjw-s\\317\\021\\272\\325''\\361Q\\325\\266\\327\\340\\225\\3469oQ\\335Y%\\265\\334k\\307\\331\\030\\204\\212\\250\\247l\\200(*\\214\\311\\274\\206m\\300\\3554\\324\\023\\256\\026X\\361w\\323\\310-\\355mz\\255r\\034$\\356\\017\\225\\005]\\2246\\332R\\270?\\3459\\233\\333\\337\\345\\346q''Cq\\203\\244\\300\\304L\\240\\267Sp\\324\\275r\\257\\273\\017\\346\\347\\317\\021\\303t\\010\\022r\\273\\014U\\215h"]?Oo\\277\\031\\354\\224-nd\\233\\226\\354H\\307\\322\\261\\232WX\\226F\\210\\212W0\\012\\016\\337\\247\\003\\335^\\334\\335\\\\\\264\\323\\022^@\\034\\223\\251\\332(\\276\\340;\\006\\010\\226\\335\\255\\370\\241y-ZY\\246\\010\\271\\345\\246\\347''\\032\\357\\320\\031-\\302\\321\\013B*\\337\\3529\\341\\326&y\\264\\3703\\230\\216/\\003~\\002n@!\\261\\263s\\011\\344O\\313\\261"\\241WW \\021P\\312\\245\\251\\357\\307\\217X\\316\\213|\\320\\305x\\362\\215\\241^&\\314.\\323\\330h)\\356\\246=pv\\375NR\\327\\252\\322\\2437ZIbS\\223\\266\\335\\264\\215\\207d\\232\\034o\\365\\215\\272\\207\\210\\2440[\\306\\225G\\216%\\012T0\\313s(\\314\\342\\237\\262\\010\\243%#G\\335\\274G\\324\\334|5\\320\\2344\\353\\267\\345\\017-4\\032\\375\\2350\\26614\\222$k\\236^\\026\\035\\200`\\375\\263\\376l\\237\\007\\253\\344?\\007\\355\\377\\000\\216\\022N\\277ycoz\\201''\\\\\\320\\356\\216E\\311\\321\\276\\322\\267g\\366\\035\\015F%\\271\\273[\\273Fsq\\031\\232''\\213j\\336F\\271\\006W\\336\\026U\\025\\333\\272\\271\\266\\232\\014Xc1\\232L\\311\\301\\312%\\206/\\346\\366\\366\\335c\\023Ou\\003,\\211\\347\\2143(R\\255\\336\\275\\343\\277\\017\\375G\\303:\\335\\262\\245\\325\\333\\332^\\227\\226D\\2069''X\\234\\347O\\006@I\\212Nr\\333\\217K9\\371\\031\\254\\243\\272\\236\\3266x\\313(\\337U\\361\\012=7.cQ\\210W\\365o8m\\343\\212U\\371Xa\\333\\034\\215\\010\\025f>g5\\007=Z\\203,F\\372\\2725dV\\255\\\\\\327''\\031''\\011$\\034\\227\\037?R%p\\223l\\255h\\016\\352:\\277\\211H\\246\\204W\\031rV\\343\\227\\344\\032\\205\\220\\310\\002\\201\\333\\220?\\254\\340NC\\223\\027\\0225\\314\\245&\\273\\206E\\351\\\\  \\312\\250@\\033\\305H\\323,\\011\\025\\375\\324\\323\\312-\\203(\\232\\244\\200*\\3259v{1\\013]\\235_9-\\264\\250\\202y\\313\\210\\336\\312\\033\\024\\363\\333\\263K1\\036P\\317\\220Q\\201.]KZH\\325\\012\\024#w\\032g\\215\\2676\\242+B\\202\\240\\226!\\330\\212\\370\\207\\233\\365\\343\\333\\2542\\331t$!Y|H\\372\\212\\366\\327\\024\\241%\\031d\\271m\\370)8\\213(\\3568c\\312B\\037\\3478\\271\\272\\252\\261\\217\\023D\\253\\275\\243\\002\\264\\334\\312\\307\\365`oQ\\240\\3458\\350\\356m\\010XO\\215\\253R[\\375G\\373N\\004\\364\\3377s`\\342T \\205\\000\\\\D\\325\\001\\243MjGh\\025*qb\\376\\221\\342nQ\\344\\264\\236Xm\\356?\\025#\\211\\220\\302\\273\\363\\335\\032\\2725+^\\374\\273)\\213jZx&bVH?NqSr\\267id\\253\\323#\\342\\312>\\244`\\325\\330\\366T\\371G\\267\\035[\\344,\\277%>\\027\\313\\351\\376\\317\\345\\376\\3165q|E\\207\\023o\\320\\262\\210 4\\336\\3477r;]\\273pf*\\002L\\306ao5\\317\\361\\334\\034\\002[\\307\\253\\267\\303\\201(e\\177\\331Z\\214\\207i9b\\007\\226\\365\\3679x\\373lJ\\330AB6\\256\\331$ \\375\\247e\\313\\331\\264\\014c$\\331[\\352\\277SXq\\266\\362X\\006iog]\\275(\\250J+\\014\\313\\356\\311j4\\304$\\327Hm\\001\\215\\366\\013\\307w\\332\\3003j6\\256\\354\\275\\330\\325kd\\323\\330]\\337%dz\\036\\243\\223\\272E|\\334\\226\\007?\\0255\\300\\366\\355\\031\\267F\\245#J\\241\\007Fq\\343%N\\271\\203\\214\\010\\326\\241z\\213n\\203\\361\\005#*H\\241f\\035\\247\\356\\267n\\032q|\\004\\246\\372d\\222\\351P\\303\\323\\017\\260\\203#4\\247H\\327V\\366\\323\\011\\025\\356\\013\\274\\364\\315\\374D\\340\\242\\367o*M#\\314\\035\\227tR\\002Wr\\214\\262>\\314\\024\\212\\215l]\\337\\372;\\203\\263\\264\\352;K#9\\242\\275\\304\\3750\\244\\2535h6\\012\\345\\246\\020\\303\\300q\\326\\362A}5\\3147V\\015t-f\\022\\327j\\257M\\247\\221\\203\\243-\\010QA\\256xL\\366/\\013\\203u\\020\\002]&v\\334\\244\\373Nt\\305?\\005\\351\\373\\236[\\210\\232\\021q\\025\\265\\244WFH\\343\\021\\231[\\257\\032\\252\\227\\363&L\\271S>\\374d\\323\\341\\031\\247rfe\\222)e\\273\\343\\335\\222\\010\\366\\356_6\\325\\220}mF\\237\\257\\027^\\216\\365*\\334\\223\\304^\\024\\202h\\0221h\\236S${t\\025''E\\241\\245kLI\\362\\234zEek\\311E2\\334X\\316\\3061\\261:e7\\022N[\\276\\320#<$\\275\\224K \\230xU\\221\\021@\\375\\322"\\177v\\020;\\2463\\034\\303\\207\\365\\347-\\307B\\251v\\007!l\\240\\001\\275\\266\\312\\240~\\360\\003\\273\\375B\\276\\334t\\037\\347\\\\w\\346\\217\\341\\276wQ\\360>\\336\\270\\3069u\\310\\271}\\274\\257"\\306\\343\\346\\000\\351I$\\241\\213(j\\025\\330\\016\\340=\\332c\\347#m\\305\\231#{\\010\\353\\276=\\3167\\020:\\2255\\332rZS\\374i\\201\\243\\376\\227m\\257\\305\\227\\315\\356O''\\335\\357\\366\\343y\\376\\211}\\345\\370\\320y\\264\\372\\337\\007\\367\\237k\\356\\341\\012\\237E\\234\\2661\\033\\233\\226\\351Gr\\245dD''|g\\315\\026\\342>\\330\\007\\013\\345\\270i\\355\\322\\021@\\001\\007\\303\\225\\000\\250>\\021\\357\\327\\007\\337\\177O?\\023\\370h<\\337\\365>\\267\\374\\276\\314+O\\2145\\362v\\177~1\\203,-\\032K\\370\\254&s\\037T\\205W&\\203k\\217\\015}\\370awi\\311q\\216l\\012\\307-\\261a \\205\\316\\256\\243\\315\\036jAq\\250S\\237\\350\\300|\\207\\361\\266\\272\\377\\000\\017\\016\\236o\\363\\356\\3721G\\352\\277,\\032\\177\\267\\361>/\\321\\355\\307''\\333\\275=b\\247E\\327\\255}\\254n\\343\\2578\\216~\\311x{\\262\\004\\316\\237\\366\\2632\\370\\320\\201\\222\\263v\\260\\245C\\014\\230k\\342\\306\\236:\\366\\357\\321i{gt\\217p\\217G\\266\\332<"b\\012\\325\\274C\\302@\\037\\243\\013\\255\\274\\274O\\237\\342\\317\\377\\000\\261\\361\\007\\223\\367]\\337\\177v\\036z\\363\\370[Ow\\326\\363\\351\\365\\261:Oo\\247\\255\\377\\000\\205m\\307\\333\\222V\\367\\2247\\226_*\\266\\3028\\224\\274\\214\\345\\316e\\230\\312\\324]59~\\214\\015\\314D \\272k6\\246\\370\\000\\036\\034\\206\\346\\012N<\\177\\264|\\237[\\315\\246\\243Oo\\367co\\250\\277\\253_i\\374T\\277\\265\\346\\377\\000\\212c\\265NT\\003\\201\\224\\376\\031\\327\\263\\036\\351s\\371\\255\\345\\350\\352~\\027\\330\\367`d\\370\\243\\337\\364\\341\\206\\020?\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (4, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000d\\000d\\000\\000\\377\\354\\000\\021Ducky\\000\\001\\000\\004\\000\\000\\000\\036\\000\\000\\377\\356\\000\\016Adobe\\000d\\300\\000\\000\\000\\001\\377\\333\\000\\204\\000\\020\\013\\013\\013\\014\\013\\020\\014\\014\\020\\027\\017\\015\\017\\027\\033\\024\\020\\020\\024\\033\\037\\027\\027\\027\\027\\027\\037\\036\\027\\032\\032\\032\\032\\027\\036\\036#%''%#\\036//33//@@@@@@@@@@@@@@@\\001\\021\\017\\017\\021\\023\\021\\025\\022\\022\\025\\024\\021\\024\\021\\024\\032\\024\\026\\026\\024\\032&\\032\\032\\034\\032\\032&0#\\036\\036\\036\\036#0+.''''''.+550055@@?@@@@@@@@@@@@\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\223\\000\\000\\002\\002\\003\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\004\\005\\003\\006\\000\\001\\002\\007\\001\\000\\003\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\002\\003\\004\\001\\000\\020\\000\\002\\001\\002\\004\\004\\004\\003\\005\\005\\007\\005\\000\\000\\000\\000\\001\\002\\003\\021\\004\\000!1\\022A"2\\005Qa\\0233q\\201R\\221\\241Br\\006\\360\\261#\\024\\025\\301\\321\\341bCS4\\361\\202\\3025U\\021\\000\\002\\002\\001\\002\\003\\010\\003\\001\\000\\000\\000\\000\\000\\000\\000\\001\\021\\000\\002!1\\022AQaq\\201\\221\\241\\301"B\\003\\3412b\\023\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\210~\\267\\330v^v\\347\\214\\214\\211\\216@\\337s(\\375\\370./\\325}\\252T-m\\275\\256A]\\266\\362\\256\\306pX\\006\\332\\302\\253Zy\\342\\214\\262:\\234\\217\\313\\004[\\276\\371W\\323\\000LO  f\\332\\201\\236Y\\340\\367\\224s\\303\\214X\\265\\230\\006\\255\\237\\214vn;\\225\\325\\270DvP\\214\\316\\320\\\\-CT\\356d\\335\\237/\\221\\370`\\033\\251\\247$\\304\\341A\\221@\\332\\256\\345\\033oJ\\235\\307"8\\034\\032d\\232EW4iJ\\321\\220)F\\257\\012\\202u\\304\\027VW\\236\\2378\\021\\025`\\012~:\\232\\347]\\000\\2451 \\275\\211\\311\\351\\254\\270\\320\\001\\201\\326\\004\\262\\240\\221E\\007\\250\\324\\323 \\006\\24452\\371`\\231mcd\\220,\\322\\200(\\013Ts8?\\015\\001\\256 x\\305\\271\\202\\3428\\214\\226\\3209\\022J\\243\\250\\361\\3328\\201\\216\\340\\276\\2636{\\032@&\\324\\356\\361\\037\\337\\202/\\006\\256\\010Y\\026P\\011=S''\\245+\\357\\215X+IJt\\212\\323\\357\\304r\\312\\316i\\232\\242\\364\\307SA\\366\\343\\222\\317\\264!5\\334w\\374\\333\\033\\364\\2459\\354o\\023\\226\\030\\242\\211\\215?L\\335YZw\\023=\\355\\301\\201\\0266\\021\\202\\245\\225\\213kR\\265\\245\\007\\226-\\335\\253\\274v\\376\\351\\352\\255\\2437\\251\\027R8\\332\\305t\\016\\242\\246\\243\\3668\\363\\323\\014\\240T\\251\\373\\261?n\\275\\223\\266\\337\\305v\\253\\270\\306Ht\\323r0\\243\\0142\\227X\\340\\363\\004\\211\\350\\361\\237\\341\\214ep\\233\\266~\\243\\267\\277\\221m\\3263\\013f[\\324u\\012\\252\\006Y\\232nbx\\0147\\334<G\\3320\\347T\\336 \\346R\\333\\262\\333J\\245\\342f\\210\\236\\003\\231~\\374.\\272\\261\\232\\325\\205H\\225E\\032\\251P@\\257\\021\\303\\016#X\\311>\\254\\222\\250=\\002=\\264\\257\\000w)\\256!\\274\\261\\273\\266\\2268n\\012\\241\\237?V\\274\\265\\322\\204\\345\\266\\206\\203\\303\\020\\322\\327\\316X\\022\\217\\262\\264\\306\\0211\\257j\\331\\374\\251\\274\\225\\375egA\\034\\264\\347\\310\\026\\033\\277\\315\\300\\340\\033\\371\\036v\\241m\\252\\344\\223\\361\\335\\266\\277*\\223\\362\\303k\\366\\226\\010O\\250)\\352\\270\\334)L\\324\\0127\\034$\\235Y\\266\\220z\\011\\257\\301\\2052\\373p\\252\\346\\316:\\330\\252\\205\\264\\312\\322B\\212\\273#\\267@\\301\\006\\212\\275(\\277\\0265''\\013o\\3326\\271\\235\\312\\203\\267\\323\\246C\\3613.\\013\\266\\025\\211\\336\\2436.\\347\\301W\\221\\006\\001H%\\272\\222F\\012Hw\\015\\220\\374\\021\\212\\002~g\\007T\\011\\351\\002\\314\\2009\\346C\\025\\271\\236\\360\\275(\\252\\025\\317\\206\\342\\001\\246\\016e\\250\\245*\\276>x6\\033d\\210\\227j(9\\210\\370\\261\\0368\\005\\245s,\\314\\357\\310\\254y\\210\\332\\243\\307\\035\\273q\\307\\0018Wh\\317\\023\\007\\231\\27574\\325\\274t\\037*\\022p\\265\\310,H5\\0364\\246\\013\\270\\271\\202CE\\253(\\255k\\312\\034\\374\\263\\333\\201]\\331\\315M\\007\\000\\240P\\001\\340\\006\\033Q\\023r''\\007n\\206\\230\\326\\324\\362\\301\\226\\367\\255\\000\\332b\\212D\\372]\\001\\317\\306\\270\\223\\372\\214\\037\\374\\373\\177\\035\\016\\270b\\353\\023\\274\\362\\363\\2077\\361\\030\\2504\\012+\\226\\265\\341Le\\325\\315\\335\\355\\300{\\206\\023\\030\\023q\\005@N\\\\\\321(\\0059\\3333\\343\\202\\254\\246\\211l/"\\226\\035\\315pk\\014\\243o.\\336Z\\232\\3466\\221\\273-q\\264\\264\\225\\254\\036&\\242K"\\264\\253)]\\200o\\364\\3750_F\\326\\231\\351\\236\\025A\\310\\276c\\254\\243\\3548\\310G\\201\\3510\\337\\033\\026N\\325{!x\\035\\214,\\216\\011xv\\321}D\\223\\212\\357\\256Z\\255<\\016 \\221\\0369Z\\006 \\264lV\\243BA\\313\\355\\307S\\315euo;]F\\302Idk\\233w\\032$\\214@\\2366\\256\\224\\241\\250\\320\\374F\\030\\303\\333\\324\\305kqs\\3139\\215\\033\\322S^\\034\\255!<i\\300aV gN}\\261\\265g\\036\\035\\220\\033kQ#\\210\\024\\221\\275\\270\\235O\\205<\\261b\\262\\261Kkv\\205h\\245\\363\\226^%x|0\\203\\270\\243C,RA\\312\\261\\324\\212}G\\\\\\021\\335\\277PG\\374\\233G\\035d.\\000b\\274\\265\\320\\037\\264\\340\\021\\262Yp\\330\\253xR\\036\\351\\177b$1\\333\\344\\203-\\354u\\247\\3221_\\277\\271\\0238Tm\\3103\\313J\\343\\265\\265\\236H\\246\\270\\270\\267~q\\374)\\211\\330\\250\\300\\347\\312A-\\226@\\012b\\013\\210\\004\\011\\022\\220}F\\005\\236\\274<\\260\\372V\\265\\320\\263''\\275\\355`XBC\\214\\306b[{i\\356d\\364\\341P\\317\\346\\312\\203\\303W#\\014\\212\\221W\\033\\256\\016~\\317v\\221\\273\\261P\\261\\320J\\315UE''\\360\\206`7\\267\\222\\003\\210\\377\\000\\247K\\365p\\337\\320\\376\\337\\327\\246\\230\\334\\314B:{b\\266=\\274\\253\\357[\\345b\\301\\000\\254k\\031\\335)\\314\\360Z\\327\\014\\240\\000@\\214\\273\\202:\\264\\210\\201~\\256\\224u\\314\\3743\\360\\256\\021v\\373Y/\\245\\267\\264\\214\\005E\\214\\233\\247\\247H\\007O\\314\\305(<p\\362\\361\\236(&\\221\\330$`f\\353\\312\\300Ut;u\\013\\235>x\\032\\200\\001 (w$\\220\\011q\\035\\333\\011\\014\\260F(\\322L\\301\\000\\341\\230V\\177\\205jqj\\271R\\362\\254\\221\\177\\0227Q\\351\\262\\347\\273\\317\\025{\\210\\244O\\343\\333\\256p\\000Y\\024\\235\\316\\273\\252\\365\\256\\247:\\347\\341\\207\\026\\242\\374\\300l\\355\\344\\021\\301),\\262\\361H\\330\\222v\\371\\034O\\366"\\001\\352|\\345?[\\251#\\220\\036QOq\\276U\\271h%$\\272\\234\\325Es\\360\\313\\216;\\262\\221\\255\\367\\272 \\015-7\\006\\316\\224\\341\\214\\275\\355v\\353~\\206\\312R\\354P\\2575\\024\\027E;\\3123d\\331aK<\\217\\003\\\\<\\255Bv\\306\\243\\22458\\340\\305A\\250\\003\\243\\200lE\\211<\\032\\216.''\\365\\230<\\212\\265\\032\\0002\\302\\216\\346\\352\\322\\250\\006\\254\\001,<+\\210\\005\\304\\313\\027\\244\\255E\\251$\\215s\\341\\\\E\\203\\2556\\230\\027\\3737\\004\\244\\366q,\\322\\355f\\012\\272U\\216\\321S\\347\\303\\014\\237kE\\351\\310\\027`\\033\\302\\362\\200)\\312\\332-s\\003/\\267<8\\354\\2266O\\331\\355\\240\\272E\\234L\\032S\\033)B\\254\\344m\\244\\264\\024%FY\\375\\230\\006\\347\\267\\264\\026\\321:\\334\\255\\314\\023I\\355\\032T\\210\\367\\0323\\243\\032\\345\\221\\310k\\215\\260:\\360\\203R4\\343\\006t\\273Xai\\326U\\267j\\233_T6\\332\\037\\245\\230g\\227\\370c[\\345\\377\\000q\\272=-O\\267\\364~\\\\5\\375C\\372\\200w8\\220,\\015\\012\\306\\301\\2661\\\\\\310\\005U\\027m~\\254&\\364\\256>\\261\\365\\177\\335\\364~\\\\f\\037\\3557+\\365\\356\\215\\277MY\\243v\\340s2\\335\\310w0\\314\\250\\347E\\345\\327M\\306\\243\\006\\316Y\\355\\344*\\253 x\\330\\020\\031\\252\\013V\\233\\226\\246\\233F_g\\236\\030v\\373\\026\\266\\355\\366q\\320\\372\\013\\014b\\361\\024\\327\\231\\3208''p4\\253eQ\\303,Ew\\004\\212\\223\\265\\312) 7\\253Nb#Q\\2617\\025a\\347\\375\\372U\\221q\\034\\222\\025\\237td\\202\\233@o5\\000W\\356\\304\\321]L\\362C\\000\\222\\227\\022\\271\\001\\251\\235\\024)_\\355\\300\\363\\330\\335\\331\\316`\\004]&\\250\\024\\201.\\317\\021\\272\\233\\250r5\\241\\304v\\323@nQ\\367:\\230\\244I\\0242\\020\\340\\253\\016Z\\037\\032\\347\\235)\\211v\\026\\265R\\257\\364\\013v\\216u/nH\\256^\\333\\320\\365.Y\\272\\352\\316\\315\\236\\374\\252q\\035\\317mi\\240!\\0326\\330\\015DN\\222\\025\\374\\312\\214NX2\\362\\372[\\207\\220G\\031\\213\\325 \\315#\\235\\245\\220\\346\\252\\262&\\341\\262\\224\\345J\\226\\342Fc\\012o\\247\\265\\206\\021\\004(\\236\\272\\220L\\333B\\315P)RT\\012\\017\\362\\214\\260@[\\014\\373\\274|`\\223TP\\033y\\350\\373"\\266\\033X\\255kBE~\\030\\321\\323\\031\\255O\\314\\343[\\207\\216\\037\\021/\\035\\232\\356;\\216\\325n\\301\\324\\010\\221a1J\\334\\241\\342\\0244\\335Q\\314>\\316\\030\\226\\347\\264__+\\335\\300#)\\034~\\243\\273U7\\273\\346Uh\\274\\002\\217\\205i\\236*\\377\\000\\247\\257^\\016\\341\\024;\\351\\014\\314ASFZ\\260\\342\\254\\0105\\3320\\373\\372\\215\\375\\254\\3676\\321JDRG\\036\\340\\373\\335\\217R\\211\\001s\\251\\003\\362\\343,\\227\\273I\\265{\\206\\335xD\\356\\004\\3270\\242\\214\\266\\372\\204|yW\\354\\317\\016\\277\\243\\\\\\177\\266\\276\\335u\\373\\2776\\026v\\365Qw#\\217\\364\\335\\021|\\225@?\\333\\213O\\363+\\364\\277U\\177\\307\\010y_\\317\\346P\\261\\273\\372\\374B\\373M\\334w\\2605\\224\\241=x\\324 \\327\\231\\024\\202\\273\\206GO\\017<M?n\\222\\3425\\332\\342`UV9CP\\215\\244\\326\\273\\267UN_\\277\\025\\270\\177\\366\\221\\373\\232''\\261\\356\\177\\253\\367\\370yW\\026\\216\\337\\320\\232\\365\\311\\354\\373:~\\377\\000\\374\\253\\212N\\245s\\223D\\375\\323\\264\\243\\310$\\221\\002J\\306F\\014\\216\\003 e\\012\\332\\014\\302\\225\\031\\374+\\226\\020\\334\\332\\337\\300\\206 \\015\\3071\\012\\354\\241\\035H\\323u\\015)M|\\015qa\\237\\3763\\351\\247\\342\\350\\353n\\237?\\253\\313\\034\\267B\\373|:\\375\\275W\\334\\300_g\\311z\\302\\246\\377\\000\\203^R\\221w}x\\215$n\\2069#v\\215\\213T\\351\\2259\\264#\\013\\253SS\\2318c\\335}\\344\\327O\\305\\325\\326\\335^\\177O\\371q\\253/u\\377\\000\\343\\364/\\271\\256\\261\\364~\\3368\\352\\355\\370\\316\\276\\347\\356\\231\\333;Y\\272\\225Zr\\005\\261\\310\\225uVbFAI<\\0157b\\304\\260C\\032,b4X@W\\216\\026\\332\\3610^\\255\\343k\\0269\\352O\\2151\\325\\207K{Z?O\\267\\363\\375\\265\\304\\213\\327m\\323\\327\\370}\\317\\372\\370\\371\\323\\007\\002*\\356\\035\\221\\244\\225g\\261z_.\\323\\260(H\\313/O\\200G\\2452:\\340\\270\\246\\355w\\326\\306\\342\\340\\265\\264\\262#\\252L\\3569g^fV\\000\\327Z\\220\\030q\\313\\004\\315\\354\\276\\235C\\362h5\\376\\337*\\341$==\\333\\247\\334\\213\\247\\247\\254{^~>X\\302\\226W|\\340\\330M\\364\\326o\\267\\261/;\\034\\211`H\\370\\252\\341\\307\\363\\017\\376\\341\\366\\366\\374\\360\\232\\313\\337\\233\\340\\277\\273\\007b\\177\\237w\\244\\253;;\\375g\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (5, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000d\\000d\\000\\000\\377\\354\\000\\021Ducky\\000\\001\\000\\004\\000\\000\\000\\036\\000\\000\\377\\356\\000\\016Adobe\\000d\\300\\000\\000\\000\\001\\377\\333\\000\\204\\000\\020\\013\\013\\013\\014\\013\\020\\014\\014\\020\\027\\017\\015\\017\\027\\033\\024\\020\\020\\024\\033\\037\\027\\027\\027\\027\\027\\037\\036\\027\\032\\032\\032\\032\\027\\036\\036#%''%#\\036//33//@@@@@@@@@@@@@@@\\001\\021\\017\\017\\021\\023\\021\\025\\022\\022\\025\\024\\021\\024\\021\\024\\032\\024\\026\\026\\024\\032&\\032\\032\\034\\032\\032&0#\\036\\036\\036\\036#0+.''''''.+550055@@?@@@@@@@@@@@@\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\233\\000\\000\\003\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\004\\005\\006\\003\\002\\001\\007\\000\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\004\\003\\002\\005\\001\\006\\020\\000\\002\\001\\002\\004\\002\\006\\007\\004\\011\\005\\001\\000\\000\\000\\000\\001\\002\\003\\021\\004\\000!1\\022\\023\\005AQaq"2\\201\\221BR3\\024\\006\\241\\301r#\\261b\\202C4T\\0255\\026\\360\\341\\361SD\\224\\021\\000\\002\\001\\003\\002\\004\\005\\003\\003\\005\\000\\000\\000\\000\\000\\000\\001\\002\\021\\000!\\0031\\022A\\201"\\004Qaq\\241\\023\\3212\\005\\360\\261\\024\\221\\341Bb#\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000Q\\276\\316+\\241\\363\\251\\306R\\214\\024\\034\\353B2\\300\\\\\\312\\326\\331b\\027\\274\\264\\260\\267\\255&\\205\\215Lm^\\216\\314os\\034\\227\\012\\313\\020\\335"\\3126\\016\\302hp2\\302\\266w\\201\\032eu\\234\\210\\245\\215jFy\\014\\36448Ve\\352"-\\300\\320\\2601\\012.dj5\\237\\245\\2508Xq\\365;\\230x}\\035\\030(3\\025\\317\\327\\\\\\36071\\2656W\\217\\000m\\334&\\033Xt\\243\\000\\313\\366c\\230\\256\\367\\011\\025\\262,\\301\\222\\272y\\206X\\212\\235\\262\\247\\204\\325\\335w\\000\\313}?\\245?\\345wW\\021\\203\\302\\033\\231s\\245s\\313\\244a\\314\\222Z\\363{c\\033\\000\\034\\216\\343\\\\N\\330\\315\\266B\\321\\2320\\314w\\215F\\017\\222\\34632\\313\\035cv\\03604\\334=\\2540"\\262\\206\\3454\\002\\354\\254\\303\\234~\\270\\324\\3373\\262\\237\\227\\334\\230X\\235\\246\\245\\017X\\301\\277)4\\320+\\306\\337\\230\\204:w\\201\\246*\\326\\013k\\370\\3427\\010\\255\\274\\020I\\241\\241\\031\\037^;\\272\\345*\\354\\206&\\011\\032.\\325P)JbK\\205\\003\\035\\326R4\\360\\253\\266w(\\245.\\313\\307\\304T\\033LZD\\016vJ\\256U\\363\\3509b\\303\\351^~\\350\\314\\241\\270\\327\\251\\032C5\\253\\032<\\361@[\\206\\366\\356M\\014\\212\\254F\\303\\346\\240\\241\\302\\256u\\310\\334\\253\\\\CC4Y\\260\\247\\230\\014O\\306\\301$F\\251\\005hC\\003B\\0115\\255~\\334C&=\\255\\023#\\201\\244\\342\\310\\035f \\361\\036\\006\\276\\235\\317>\\244\\2707V<\\267\\224\\310\\021\\257\\326)\\026\\352\\201\\216\\311Y\\225B,\\231W\\300k\\\\3\\371\\016u\\374\\361\\362m\\325<\\336\\367\\301\\377\\000n\\314|\\332\\356\\342?\\220\\345N\\256E\\352\\274\\261\\310\\343&\\332$\\016\\214)\\322\\254I\\030\\267\\377\\000$\\275\\352_\\355\\2771\\247\\376\\236\\277\\303\\331\\211\\325&\\242A2%\\312\\247\\230P\\353MN\\006\\226\\335\\325\\241v\\240T\\225K\\032\\366\\343K9T\\213\\271+\\221\\246\\343\\330:q\\224\\310\\263\\276\\346''\\207\\226\\305\\006\\202\\235m\\326p\\226\\207\\001\\242X\\203\\036\\233\\215\\011w#\\262\\314("m\\251\\332+)\\313_\\336M\\362\\364#nD\\350B\\214/4\\240#>\\203\\206ARFE\\205\\325\\0260K\\310\\203=\\244P\\351\\206\\026\\\\\\212\\336x\\367\\262\\025\\205\\207\\2045w\\376.\\316\\354\\027.UBK\\233\\314\\223\\353O\\355\\360>E\\037\\030\\260\\020\\001\\266\\224\\257\\226\\334\\224\\272\\001\\300!\\265\\007\\244\\214:n\\014s\\326,\\342z2\\327:vz05\\367\\323\\374/\\317\\263,\\354\\236-\\215\\256]X\\375\\313\\356Q\\324\\244\\252\\012\\260*A\\325\\033\\254wa=\\226tq\\012f\\362>\\224N\\377\\000\\266\\311\\214\\356e\\213_\\352)\\241\\272Xf\\211\\242\\177\\003\\346\\321\\015\\024\\366a\\214\\274\\3425\\214\\015\\300PP\\323\\010\\030\\241\\214\\012~b5*:F4\\267Xe\\223\\213r\\373 \\217\\315\\326\\304\\364\\0145\\225b\\343J\\012\\263h\\016\\266\\243\\3070G\\032U\\033#\\327\\210\\373\\305H\\356\\245\\206:l\\022\\032\\021\\325\\331\\212\\353\\213[Y\\024\\036\\031\\206\\026\\025L\\312\\236\\374\\360\\202\\363\\223\\271\\271\\021G -%Hv\\351\\030?r\\204\\200TXk\\316\\223\\332\\270\\014\\301\\232\\347NT\\002\\273\\313t%\\002\\2447\\202\\202\\273\\232\\276\\021\\366\\343\\351_\\320\\323\\337\\177\\355\\277+\\354\\374o\\177\\315\\257\\352\\342b\\322>Cg\\301\\201\\346\\232[\\200j\\257\\023m\\010\\376\\360\\003\\247\\277\\030\\177\\\\\\277\\376g\\333\\340yz?\\356\\374\\177f\\015\\3614\\352''\\326\\223\\362\\254L4zV6\\361(\\206Z\\214\\346c\\352QO\\323\\214\\355\\241VU\\017%\\012\\321@\\245I#\\015\\205\\251\\216\\315\\313\\257\\217p\\035\\224\\241\\246\\025\\020`\\272dPU_\\306_\\260\\364a[\\002\\204\\221o\\264\\363\\241o,\\\\\\003\\006\\3149Z\\275\\236\\003\\013\\227l\\326@\\261\\367U\\250t\\357\\305u\\254h#U\\031\\001\\227\\253\\023-\\303\\272\\267\\341\\261\\360\\221\\223`\\276]\\317$\\264Qm~\\205\\224d\\263\\240\\334\\010\\375`3\\307;\\362\\275\\246FU|JX^@\\275u\\177\\025\\336"\\253\\342\\312\\301\\036DM\\264\\246\\367q\\004 \\256D\\342^\\362!k\\315\\211\\003\\362\\346\\005\\210\\350\\007\\017\\247\\347\\034\\251\\302\\262\\334\\026$\\321QU\\211''\\3221;\\314o\\243\\236h\\331C+(!\\225\\207\\350\\246\\011\\370\\377\\000\\2212)`\\313\\032\\310\\212o|S''nVC\\036\\021D\\306\\312\\321\\361\\0271_\\277\\033\\306T\\310\\273WpG,:\\211\\323\\354\\302\\264\\271;\\004c\\300$rt\\366{\\260d\\020\\335s\\000R\\320\\210\\240L\\214\\255\\322z\\2051\\334n\\353\\032\\343\\334\\346\\312\\004\\372\\327\\317\\257i\\225\\362l\\306$\\2611\\351\\347L\\246\\012\\002\\317\\177p\\256\\317\\244jj@\\364aw1\\341\\245&\\266\\220\\221B4\\361%E+\\203c\\372Z\\032\\011.$y\\215**\\333G\\243n\\023_\\3315\\265\\324\\226\\221\\316\\342\\334*\\261\\014j|U\\313\\005_\\310\\343\\315\\273\\032\\203\\245\\271S\\037\\361\\2310\\005\\312\\314\\277t0\\327Z\\312&\\020,\\222 \\037\\224\\003\\200t,\\336\\012}\\370\\007\\210\\376\\361\\323w\\355u\\342\\226\\313\\350\\316s\\314\\271tW\\026\\202$\\267z\\224\\342\\263+\\275<"O\\012\\234\\262\\313\\327\\202\\377\\000\\301\\276\\251\\367\\355~\\037\\007\\314|\\275\\177\\017\\\\fk[N\\265\\305\\305\\364\\366\\351"J\\234H\\034\\3240\\363&\\025\\363\\025\\343B\\223Ft`\\0152\\252\\261\\2465\\270\\270\\220\\3266;T\\353\\201,[\\211\\034\\260\\223Tr\\312\\270fF\\004\\224\\271\\016\\017&\\025\\317\\304\\254\\000\\312cv2$\\370\\251\\255D\\225\\001EJ\\256U\\031\\014{\\274\\322\\24300$\\257\\340X\\364P\\254X\\365\\221\\203\\271}\\235\\205\\363\\361\\324\\360-\\255a\\214JET\\264\\315\\255z\\306X\\237\\315}\\276C\\214kV\\376?N\\343`I\\366\\361\\365\\240\\347\\225\\335\\012Sw~\\203\\034\\332\\2640#4\\210\\034\\023F$h4\\301\\022\\302\\273\\346\\021\\222\\361\\304\\333x\\203\\312k\\230\\356n\\314d\\253M\\313\\221\\351\\025\\355\\306D\\356\\014/\\257\\235R\\027a[\\2158\\321\\346\\036_,[D\\352\\255\\263(\\334\\020\\300\\364P\\320\\214\\023\\310\\371\\225\\254{\\254&"9# -h\\025\\206\\332x}9\\372p\\255$w\\024\\360\\324\\364\\201C\\214L 7\\211w\\015A9\\327\\030\\356\\261|\\370\\300#l\\377\\000\\220\\366\\257{L\\247\\267\\310Zf\\007\\333>:\\336\\255/9\\215\\2740\\022\\307!\\220\\313\\354\\030\\237\\344\\226\\017\\317\\271\\370I\\306\\350w\\011.\\024h\\260\\306\\246\\212{\\330\\201\\205\\313\\032\\356\\337Z\\216\\274^\\375\\005\\313\\336\\337\\227\\334\\\\\\274aE\\324\\225\\215\\251\\342dQ\\322z\\252M0\\\\=\\227\\361\\301i\\3356\\232nn\\361s\\200\\212\\245c\\250\\217\\357U*\\252\\212\\025@UQE\\003 \\000\\307\\270\\013\\233\\363k~Sj\\2673\\253:\\263\\254J\\251J\\356~\\326*\\000\\313\\257\\012\\277\\314\\254}\\337g\\177\\230i\\327\\335\\333\\212\\324\\274\\252>\\314\\331]\\006\\216\\350\\0223\\241R\\007\\247<\\0135\\210\\345\\305\\202\\237\\313\\256\\350\\317V2O\\311\\270(\\265 T\\245}\\336\\254\\025|\\374{3\\031\\256\\365\\036\\023\\331\\207\\230+\\270\\216\\244\\375\\305r\\256\\256\\021O\\374\\362\\021\\357@$K,\\003w\\212\\272\\365\\202zF;\\2672@x`\\205\\201\\223l\\214\\001\\250\\330K+\\320j\\335\\030\\032\\336\\352%E\\334i\\225\\017\\374\\340\\200\\242O\\024rT\\035(pp\\025\\200\\361\\210\\266\\264\\222\\316\\214\\300\\375\\244\\315\\305\\250\\310~F\\337\\222\\272;\\011w?\\026j\\344\\325b\\002\\372@\\246\\005n\\020\\3421;\\020\\000\\351\\3054g\\214\\350E\\005~\\314e 9\\253\\201)\\367H\\373\\361\\324\\222<\\353\\032L\\015"5\\216\\264$zu\\246<\\012A\\3515\\271\\014$\\213\\023\\257\\322\\264\\022\\300\\266\\234t#x;V\\034\\313\\226\\352\\307s<*\\261\\007u\\006aX\\331t\\2563Y\\300\\276\\027\\304#L)\\250"\\264\\033k\\221\\327\\031\\033x\\270r\\327`y\\244\\016\\030W\\300\\240\\356\\330\\270\\242\\344\\313\\344\\303H\\264V\\033\\036+Ie:\\361\\232.\\302\\310\\\\\\317Y%\\021\\332\\244\\310\\223\\034\\364mhz3\\313\\027<\\257\\352\\376E$\\355\\313\\222N\\010\\204\\210\\343\\221\\306\\310\\235\\205j\\250{)\\323LB\\233\\366\\371!\\313\\341D\\214\\022\\013\\224%\\344sZ\\203\\331\\246\\005\\271\\267\\222;\\251V\\352 \\223\\273\\011\\035\\012\\323\\314+\\247n$\\362H\\034\\017\\265W\\034\\000O\\037\\337\\316\\256\\276\\266\\346\\366\\237,\\234\\276?\\317\\227\\210\\222\\310\\022\\204*\\241\\334\\001m77WV&\\177\\255\\336\\377\\000-\\016\\233\\274\\315\\247\\273\\204\\326\\345Vs\\031\\334BWn\\332\\261%\\300\\242\\323L\\263\\314\\340\\215\\267\\275o\\257\\274\\276^\\256\\374f\\004q\\326<\\253rwp\\322|\\350\\2660\\312\\364\\027"s\\270\\031\\030.\\320\\2659\\323\\005K\\022D\\377\\000/6A\\276\\024\\303F\\007L\\001c\\3747\\356\\272|\\232z{pm\\307\\366\\353o\\305\\355k\\257\\263\\205\\256\\202y\\353\\3575\\317h\\223\\267\\307\\247Oh\\265a\\313l\\024]]r\\351\\316R\\243Ko\\356\\261Q\\342\\036\\254\\361?GUm\\304\\202\\207eGX\\305L\\237\\036\\307O\\212\\276_\\211\\373?\\177f\\020\\267\\361\\023y>3wjpG\\211\\351\\235\\274&\\237\\213tuF\\373n\\217\\032\\346\\011fZ+\\215\\343PkC\\202\\326H\\031I$\\306F\\273\\201\\247\\254e\\214\\323\\316\\336O\\277\\034\\311\\346]<\\303\\360k\\373\\316\\314mw\\305\\275\\352Y~)\\274\\317\\372~\\242\\265\\242\\310\\313\\032\\270b\\371 \\025fj\\365\\0000O\\312H\\324V\\215\\313\\214\\200\\010T\\237^,m?\\205\\213\\340y?w\\347\\323\\243\\263\\001\\315\\250\\323\\357\\305/y\\326\\242b\\027\\357\\211\\362\\255~\\235\\345\\326\\260A\\305x\\324^\\326\\244\\260\\025@t\\331\\200>\\256\\263\\226\\340\\300\\360A\\304\\011\\275\\246\\220d\\025\\024d\\030\\236\\212\\341\\255\\217\\361\\037\\260t\\323\\034s\\177\\355\\227_\\207\\331\\357\\306\\016\\206\\220\\261h\\250F/\\005\\270\\230\\252D\\322\\012-|\\304V\\225QM1\\217\\315\\267Y\\362S\\315\\355{\\332i\\203\\357\\376\\012|\\017\\204\\276m}\\030\\033\\377\\000\\213\\340\\377\\000\\257\\332\\306:\\271V\\272y\\315\\177\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (7, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000d\\000d\\000\\000\\377\\354\\000\\021Ducky\\000\\001\\000\\004\\000\\000\\000\\036\\000\\000\\377\\356\\000\\016Adobe\\000d\\300\\000\\000\\000\\001\\377\\333\\000\\204\\000\\020\\013\\013\\013\\014\\013\\020\\014\\014\\020\\027\\017\\015\\017\\027\\033\\024\\020\\020\\024\\033\\037\\027\\027\\027\\027\\027\\037\\036\\027\\032\\032\\032\\032\\027\\036\\036#%''%#\\036//33//@@@@@@@@@@@@@@@\\001\\021\\017\\017\\021\\023\\021\\025\\022\\022\\025\\024\\021\\024\\021\\024\\032\\024\\026\\026\\024\\032&\\032\\032\\034\\032\\032&0#\\036\\036\\036\\036#0+.''''''.+550055@@?@@@@@@@@@@@@\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\215\\000\\001\\000\\003\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\004\\005\\006\\003\\002\\007\\001\\001\\000\\003\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\001\\003\\002\\004\\020\\000\\002\\001\\003\\002\\003\\006\\004\\003\\006\\004\\007\\000\\000\\000\\000\\001\\002\\003\\000\\021\\004\\022\\005!1\\023AQ\\201"2\\006aq#\\024\\221B3\\261\\301RrCS\\321b\\2224sDT\\0255\\026\\007\\021\\000\\002\\002\\001\\003\\004\\002\\003\\000\\000\\000\\000\\000\\000\\000\\000\\000\\001\\021\\002\\003!1\\022Aa\\221"Q\\201qR\\023\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\337\\322\\224\\240\\005)T\\236\\341\\336d\\304\\003\\017\\021\\264\\344\\310\\272\\236N}$<\\001\\376f\\354\\374i\\244\\333\\204:\\325\\331\\302;n\\233\\300\\306\\236<,VC\\227!\\032\\272\\200\\262"\\332\\366!Yn\\307\\260_\\342{/\\021\\367\\374\\334Li%\\236\\030\\362\\302\\023\\365 =0\\247\\207\\225\\325\\213\\367\\363\\007\\302\\263\\221\\365 \\310IVV\\221\\2303\\270"\\347\\207\\035C\\266\\344\\367\\325\\236>\\345\\003`I\\210c\\216\\020\\304\\262B=D\\221\\3466\\260\\253,J!\\356t\\177$\\222Q?$\\235\\257s\\336\\346\\211ru\\214\\235L\\307\\355\\312\\252\\353Po\\364\\331B\\225=\\332\\257Z8''\\217"\\025\\232#tqq\\336;\\010#\\260\\203\\300\\212\\307\\340N\\370q<Q\\266\\223\\346h\\236\\336\\206=\\375\\365+n\\336~\\327,\\011\\025\\227\\023 \\216\\273H\\300\\350\\224\\360\\352^\\376\\223\\371\\211\\371\\367\\322\\311\\217\\252]5\\026L{\\272\\255\\276\\015U)J\\211\\316)JP\\005.g\\270\\036,\\2711\\240\\2167\\351\\270\\212\\357!R\\357\\2441\\000\\005<\\026\\375\\246\\242\\217u\\314\\013\\223\\216\\222\\242z\\2367r\\213sa\\251\\304l\\005\\317}C>\\331\\316\\352d\\300!\\014\\266\\276.c8\\270*X\\371\\207\\250\\231\\025\\254\\307\\274_\\215J\\233h\\310\\304\\332\\267\\011UB}\\32413\\341\\3045\\010\\344C\\3654\\021\\314\\021\\360\\255\\372BQ=\\313\\306=\\026\\217n\\245\\376\\026Ze\\341E\\230\\006\\205\\225\\003\\220M\\364\\360\\342\\011\\370V\\003s\\316\\2133r\\316\\316G\\323\\217\\255B\\220N\\242\\250\\212\\241\\207/^\\237\\302\\271\\345n\\031\\222\\313\\215\\265$\\355\\366\\214\\362\\261H\\211S\\245\\274\\305d\\034\\231C\\016\\321\\332E@\\306\\220d\\266K\\0057YH~\\002\\346\\350\\321\\226\\262\\376\\352\\325\\023\\255\\343\\362<u\\343\\221\\256\\314\\267\\306\\306\\236T\\014\\245\\241g\\027\\013\\345g\\260\\032\\256\\354\\367\\002\\303\\262\\220\\000%q \\006u\\265\\344\\265\\265\\253\\017+[\\302\\306\\275cnJ\\272YW\\352"\\332\\376W\\261"\\304\\201\\253\\237\\2050\\345L\\234\\307\\221A\\010J!%t\\213\\202\\305\\2549v\\326p\\333+\\311\\3550\\374\\027=\\343\\343\\034\\371\\364q\\320\\013*-\\310\\271_S\\033\\021{r\\002\\2744o\\215"\\305(")<\\245$\\271(\\314.\\007\\036\\306\\370\\327\\340\\313\\203\\035\\336\\011\\003,\\253#\\266\\235$\\036.H \\3279\\246l\\331UI{\\244\\250\\316\\034\\035F\\336mL\\334\\254-\\312\\263W\\225\\346\\235c\\224v\\201\\033\\017md\\244\\373LJ%\\352\\311\\011x\\244\\004\\335\\223K\\260Un\\337M\\252>\\363\\271nQ\\356pm\\333yUyQH$\\003vb\\374\\311\\275\\202\\254d\\362\\254,Sc\\035Y\\031m(\\201\\262\\214La:\\\\ :\\332\\305t\\236=\\227j\\325agC\\231\\356\\014\\\\\\250\\374\\2211\\351\\302\\254\\300\\220\\253\\013\\201\\332y\\223\\337ZKV\\343E'':\\257\\265\\255\\022\\227"#\\357\\371\\323\\273(\\223 \\311\\0370\\205T};\\365H\\320\\2400]$\\233\\327o\\375\\243t\\356\\037\\247\\323\\3449\\377\\000\\325\\362\\364\\3749V\\2216]\\265$\\211\\322\\020\\004\\021\\311\\012''\\345\\3231\\324\\367\\007\\231?\\274\\327_\\373n\\007\\366\\023\\364~\\327\\227\\364?\\267\\374\\264s_\\252\\360\\037\\322\\237\\251&\\224\\245`\\211\\231\\3676\\300$g\\336\\261\\345\\220O\\012\\036\\264}C\\244\\304\\276f\\351\\352\\276\\222\\000\\275\\271\\037\\237\\032\\304O4\\373v\\340\\361\\343C\\324\\215\\302\\273\\021\\332\\247\\370\\254\\000\\015_P\\3360r7\\014\\0110\\361\\362>\\325\\246\\262\\274\\272:\\207G\\346P\\272\\227\\325\\313\\345X\\215\\343h|\\007|I\\346\\006iP\\311\\026R\\335\\232\\301\\264\\333\\246\\327\\322Or\\370S\\253i\\351\\2437[5\\252z\\243\\224\\016''\\321\\225\\214\\355\\014\\326\\262\\310\\274\\035o\\314\\032\\233\\021\\2126X\\326\\336K\\035=\\266\\357\\252}\\212)\\307\\334!\\022\\026F\\014ZPC\\036\\034O\\036\\312\\260\\234\\351\\311\\201\\271\\022\\332<\\0105\\327W5V\\215^\\347]_$\\237\\312&n\\020C\\2238\\315\\217Ta\\254\\244\\002u#\\250\\346\\033\\343Y\\315\\303wq#\\246:\\312b\\2712\\344-\\311v\\355\\273\\037\\361\\253\\367$.\\223p\\033\\323{\\205$\\203\\310\\362\\252m\\257\\013K$\\031Oy2\\245Ha\\327\\351Ml\\020\\330\\202\\015\\324\\236W\\037\\276\\247\\225\\302Iu\\335\\223\\310\\335j\\240\\353\\266\\354\\363e\\3020\\361T\\3652\\307Q\\343p\\033J\\360\\372\\214\\372\\254\\274\\376u\\364-\\267n\\\\lh>\\342,s\\231\\032\\331\\345\\202!\\032\\337\\225\\324vp\\252X \\331\\375\\263\\236\\235l\\314\\231r%\\213IG\\001\\343T-\\352\\262\\240oR\\360\\342Mi`\\236\\034\\230Rx\\034I\\024\\202\\352\\352n\\010\\256s\\235\\314N\\260\\317t\\245(2(HPY\\215\\200\\342I\\354\\024\\256Yq4\\370\\263B\\204+K\\033"\\223\\310\\026\\004\\012\\000\\240\\310\\367\\\\\\215\\307\\026\\025\\216#\\351\\227 \\361a\\377\\000\\015H\\267\\372\\274*\\243.\\333\\234\\203#9\\272\\262\\200\\025%\\211Q\\031T\\022l\\255\\244\\260\\346{jg\\267\\333\\0057\\027\\305\\334b\\003-\\225#\\307\\216U\\276\\202\\001.\\226#\\201<\\357\\3329U\\346v\\317\\263\\230$\\232Ld\\217\\246\\254\\345\\342\\372M\\345\\027\\275\\343\\265ViW\\034g\\274\\235\\015\\322\\2168\\375\\231\\336\\25640\\030\\261\\343\\351\\206\\343,\\316\\332\\235\\376d\\324%1M(\\225\\0241[\\250\\177\\221\\261\\265Rm\\277w\\272\\304\\315\\23134Jt\\204\\0345\\037\\215\\252\\337\\002\\311\\003@\\226\\352A\\345\\267\\205\\324\\370\\325\\252\\345''\\021^\\205\\253\\020\\232\\331\\236\\267\\015\\322<\\\\q\\213$\\237\\252\\310\\312\\227\\345\\241\\203j\\343\\370WlO\\260iFD\\330\\321\\346C-\\272\\210\\300\\026[\\036\\017\\031>\\226\\036\\027\\254\\210\\303\\334\\263\\367"2#b\\372\\307T\\260!Uo\\337\\335j\\324\\373''m\\302\\316m\\307\\022I$W\\305\\225LRF\\366%\\033R\\333KjSm=\\335\\265''t\\347\\225}_\\222N\\352\\037*\\265Y\\216\\346\\2433\\334\\033d\\3302\\306\\025\\344\\222Te\\030\\355\\033\\002I\\026\\3635\\264\\201\\361\\275t\\366\\262\\260\\332\\020\\236!\\244\\220\\253\\177\\020\\324F\\257\\023H=\\263\\203\\033j\\235\\344\\311\\003\\222H@O\\025@\\267\\361\\341V\\312\\252\\252\\025@\\012\\005\\200\\034\\000\\002\\246\\335v\\254\\375\\222\\263\\254q\\254\\357:\\237\\264\\245+$\\305)\\\\\\362\\032t\\201\\333\\035\\004\\263(\\272F\\315\\2401\\037\\227U\\232\\327\\371P\\007\\243\\034e\\304\\205Au\\270V \\\\_\\235\\215e\\275\\345\\356\\275\\277\\017o\\312\\333q\\345\\022\\356\\023!\\204\\242q\\021\\207\\362\\271v\\344\\016\\233\\360\\347z\\203\\273{\\313\\334f&\\213\\007f\\237\\026@J\\274\\362#K\\244\\216z@@\\276&\\342\\261\\317\\260\\356\\363\\345\\305\\012\\304\\323\\347e/^HT\\035q\\011\\030\\351\\353^\\312\\205\\275\\\\O"/@\\317\\335\\207rLI\\2143\\033E!\\276\\243\\311Z\\255\\367(\\362\\214\\2033jeim\\365\\002\\020K\\001\\360\\355\\254\\366\\345\\266I\\267L\\220I43\\310\\352\\031\\227\\036A/M\\211\\266\\207+\\303W\\312\\254\\366\\217of\\021&fl9X\\370\\320\\262\\243\\030\\227D\\232\\233\\215\\376\\240\\344\\243\\366\\216W\\255\\254\\217\\217\\037\\021\\271Zdq\\302>\\326\\344\\034\\315\\353t\\230\\030fs\\030\\344\\312\\243A\\361\\355\\246\\305\\275\\345l{\\202f\\343\\200\\3744\\313\\021\\340$C\\315I\\354\\357\\007\\276\\254\\375\\305\\264\\346\\256\\024;\\201\\215\\3164lb9\\022\\200\\222>\\243t\\272\\363<;G\\013\\324?jm\\253\\271\\357Pc\\261\\210\\252\\236\\243E5\\364\\312\\250F\\264\\032A\\343\\246\\344|\\2536nurb\\363\\312\\033\\230>\\251\\355\\375\\373\\033~\\3019\\230\\361\\274A\\034\\304\\350\\366\\270`\\025\\270i&\\343\\315VuG\\355\\275\\211\\366''\\317\\306C|)eYqI7k\\025\\263+|\\210\\267\\306\\257)\\030\\024\\245(\\001JR\\200\\004\\200\\011&\\300q$\\326\\017\\337^\\340\\332$\\301\\223o\\333\\362\\213e\\311"\\274\\337mn\\233\\2004\\225\\232E\\340\\334;\\005\\371\\013\\326\\327;\\375\\224\\377\\000\\245\\372m\\376\\343\\364y\\177S\\374\\275\\365G\\262~\\270\\377\\000\\303\\363\\377\\000\\220\\365\\370|h\\0323\\037\\374\\347k\\202]\\312\\\\\\214\\314i\\014\\260"\\311\\210\\356\\244D\\015\\354\\315r,X\\\\i\\361=\\225\\364\\252R\\201\\031_r{sx\\367\\036jG$\\361\\342mx\\347\\351\\255\\313\\311#\\021\\346\\220\\250\\260\\370\\013\\237\\333Sv_gl\\2734\\213\\221\\0124\\331I}9\\022\\233\\260\\270\\261\\322\\242\\312?\\013\\374j\\366\\224\\014R\\224\\240B\\224\\245\\000\\177\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (8, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\001\\001\\000`\\000`\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.'' ",#\\034\\034(7),01444\\037''9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&''()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&''()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\363:(\\242\\276\\230\\371\\340\\242\\231$\\321E\\2172DL\\364\\334\\300f\\243\\373e\\257\\374\\374\\303\\377\\000}\\212\\227(\\255\\033)FOdOE29\\242\\227>\\\\\\210\\370\\353\\265\\201\\305>\\2514\\365Bi\\255\\302\\212d\\223E\\026<\\311\\0213\\323s\\001\\232\\217\\355\\226\\277\\363\\363\\017\\375\\366*\\\\\\242\\264lj2{"z)\\221\\315\\024\\271\\362\\344G\\307]\\254\\016)\\365I\\247\\252\\023Mn\\024QE\\002\\012\\226\\322\\322k\\373\\353[\\033c\\030\\270\\273\\236;x\\214\\204\\205\\016\\354\\025Kc\\234d\\214\\343\\265EWtk\\2704\\377\\000\\020i7\\327O\\345\\333Z\\337\\333\\334L\\370''li*\\263\\034\\016N\\000''\\003\\232\\212\\255\\2506\\267\\261t\\322sI\\367>\\231\\260\\322\\2743\\360\\347\\303s\\334(\\206\\312\\322\\010\\203]\\336\\312\\007\\23363\\202\\354\\006]\\262\\304\\005\\035\\333j\\201\\300\\257<\\324\\377\\000h\\377\\000\\017\\333j1C\\247i7\\327\\266\\242R\\223\\3343,X@@\\017\\032\\234\\227\\310\\311\\303l\\355\\234d\\343S\\366\\200\\322\\257\\265?\\207(\\3666\\322N,\\257R\\352\\343g%"\\021\\310\\245\\361\\324\\200Xg\\035\\006I\\340\\022>T\\257\\234n\\372\\263\\336J\\307\\327V\\032\\347\\201~2\\351\\222\\331\\252\\226\\274\\206&a\\034\\361\\210\\356\\355\\001lo\\215\\271\\035UI\\332Xr\\241\\2078\\257\\004\\376\\312\\274\\376\\335\\376\\303\\337\\007\\333\\376\\335\\375\\237\\277''\\312\\363\\274\\337+9\\306vn\\3478\\316;g\\212\\273\\360#J\\276\\275\\370\\237a\\177om$\\226\\226\\011+\\335L>\\354A\\342t\\\\\\237R\\307\\201\\327\\202z\\002B\\377\\000kY\\177\\302\\300\\376\\334\\363\\377\\000\\342[\\375\\275\\366\\377\\000;c\\177\\250\\373W\\231\\277n3\\3679\\3063\\333\\031\\342\\273ps\\222\\347K\\263\\373\\316LTb\\371o\\337\\360=\\266\\377\\000\\\\\\360/\\301\\2552+6R\\267\\223D\\254c\\2021%\\335\\330\\015\\215\\3627\\003\\2531\\033\\212\\216\\030(\\343\\025\\316i\\237\\264\\177\\207\\356u\\031a\\324t\\233\\353+S(H.\\025\\226\\\\\\241$\\027\\221F\\012``\\341w\\367\\306p3\\346\\237\\035\\364\\253\\353/\\211\\367\\367\\367\\026\\322Gi~\\221=\\254\\307\\356\\312\\022$F\\301\\365\\0149\\035y\\007\\241\\004\\371\\235q7}Y\\326\\225\\217\\266\\257\\364\\257\\014\\374F\\360\\334\\027\\014!\\275\\264\\236"\\326\\227\\261\\001\\346\\303\\234d\\243\\021\\224l\\250\\005Ou\\332\\300\\362+\\345\\353\\273I\\254/\\256\\254nLf\\342\\322y-\\3451\\222T\\2721V+\\236q\\220q\\236\\325\\356_\\263\\376\\225}\\246|9w\\276\\266\\222\\001{z\\367V\\373\\370/\\021\\2165\\017\\216\\240\\022\\247\\031\\3520G\\004\\023\\342\\272\\315\\334\\032\\207\\2105k\\353W\\363-\\256\\257\\356.!|\\021\\2727\\225\\231N\\017# \\203\\203\\315z\\031|\\237;\\217K\\0348\\344\\271S\\352R\\242\\212+\\326<\\300\\242\\212(\\003\\257\\360\\377\\000\\304\\377\\000\\024\\370oOK\\013K\\213[\\253H\\324$1_Bd\\362Tg\\345B\\254\\255\\216q\\206''\\000\\0000\\006*)\\374w5\\325\\304\\267\\027\\036\\021\\360T\\323\\312\\345\\344\\222M\\030\\263;\\023\\222I2d\\222y\\315r\\264W;\\302Qn\\356&\\353\\023U+&t\\372\\227\\304\\015wQ\\320\\306\\211\\022\\351\\332^\\225\\265\\221\\3554\\253_!$V9*\\331f g9\\012W;\\2306\\340k\\230\\242\\212\\322\\235(SV\\202\\261\\234\\352J\\243\\274\\231\\323\\351\\277\\0205\\335;C:$\\253\\247j\\232V\\325D\\264\\325m|\\364\\215T\\344*\\341\\224\\221\\234`1lmP\\273@\\247\\301\\343\\271\\255n"\\270\\267\\360\\217\\202\\241\\236''\\017\\034\\221\\350\\305Y\\030\\034\\202\\010\\223 \\203\\316k\\225\\242\\263xJ-\\335\\304\\321bj\\245d\\316\\277\\304\\037\\023\\374S\\342M=\\354.\\356-mm$R\\223Ec\\011\\217\\316S\\217\\225\\313368\\306\\024\\214\\202A\\3108\\256B\\212+Jt\\241MZ\\012\\306s\\251*\\216\\362aE\\024V\\204\\005V\\271\\276\\206\\322DI\\213\\015\\340\\235\\333r\\005Y\\246K\\014s\\241IP:\\372\\021S>k{\\273\\225\\036[\\373\\333\\012\\216\\262(d`\\312z\\0259\\024\\352\\312\\223L\\232\\331\\214\\266\\023\\024=Llx=\\177\\317?\\235,z\\261\\215\\374\\253\\350L/\\375\\3408=\\177\\317z\\305W\\345v\\250\\255\\371}\\346\\256\\2176\\264\\335\\377\\0003R\\212lr\\307*\\356\\215\\325\\306q\\2259\\247WBw\\330\\301\\253\\005\\024\\327u\\215K;\\005Q\\324\\261\\300\\254\\373\\215]\\003yV\\250g\\227\\266\\321\\307\\177\\317\\374\\363Q:\\260\\207\\304\\313\\2059O\\341F\\2130U,\\304\\000\\006I=\\252\\264Z\\205\\274\\367&\\010\\230\\273\\000N\\340>_\\317\\374\\212\\2464\\373\\273\\326\\337}1T\\316DI\\333\\257\\341\\374\\353F\\013hm\\227l1\\204\\035\\361\\324\\375Mg\\031T\\233\\272V^{\\377\\000\\300.Q\\247\\025k\\335\\371lKE\\024V\\346!E\\024P\\001L\\226\\030\\347B\\222\\240u\\364"\\237E\\015&\\254\\306\\233Z\\2432M\\032 \\373\\355\\345\\222\\007\\317\\0309\\307\\035\\273\\376\\264\\337\\260\\352#1\\255\\366b$e\\211;\\277\\317\\343Z\\264V\\017\\015O\\246\\236\\206\\253\\021>\\272\\372\\231k\\243+\\276\\373\\233\\231&#\\030\\317\\034zw\\253\\360[Cl\\273a\\214 \\357\\216\\247\\352jZ*\\341F\\020w\\212&ug=\\033\\012(\\242\\2643\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200?\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (9, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\001\\000\\000\\001\\000\\001\\000\\000\\377\\355\\000\\034Photoshop 3.0\\0008BIM\\004\\004\\000\\000\\000\\000\\000\\000\\377\\333\\000C\\000\\002\\002\\002\\002\\002\\001\\002\\002\\002\\002\\002\\002\\002\\003\\003\\006\\004\\003\\003\\003\\003\\007\\005\\005\\004\\006\\010\\007\\010\\010\\010\\007\\010\\010\\011\\012\\015\\013\\011\\011\\014\\012\\010\\010\\013\\017\\013\\014\\015\\016\\016\\016\\016\\011\\013\\020\\021\\017\\016\\021\\015\\016\\016\\016\\377\\333\\000C\\001\\002\\002\\002\\003\\003\\003\\006\\004\\004\\006\\016\\011\\010\\011\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&''()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&''()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\374\\201O\\005\\370)\\206^\\326X\\363"\\343m\\304\\207!\\343\\005\\177\\361\\340i\\213\\340\\317\\006\\230\\320\\265\\254\\241\\2312\\243\\3552|\\337(9\\374\\353\\265\\006\\376\\010C\\006\\266\\036Z\\246\\344\\0229\\311H\\231\\220\\365\\354y\\251\\355mu)\\216G\\331\\315\\274n\\251!y\\330m\\312\\216\\007>\\225\\371\\254\\363\\034JW\\366\\217\\357g\\346\\222\\3141)\\177\\021\\375\\354\\344\\354\\276\\034\\370^\\355\\026u\\261\\224\\331+\\015\\362\\231\\344\\311\\371\\001#\\257\\256kg\\376\\025\\317\\202\\344\\330SC\\333\\030Q\\222o&\\334\\336\\377\\000~\\273\\247\\220\\006KTU\\2125\\376\\022\\304\\222@\\3074m\\222\\346\\351mcS-\\3030\\304Q/ t\\340\\377\\000\\021\\377\\000ds^5l\\343\\032\\333\\265I/\\2338\\252f\\270\\245\\377\\000/e\\367\\263\\207O\\206\\236\\014i\\366\\235\\026L\\347\\204\\373T\\334\\217\\373\\356\\272k\\177\\204\\377\\000\\016\\335\\321\\233Bb\\273r\\301\\357\\347\\033\\275q\\363\\327\\323\\276\\005\\370\\003\\257k\\332tw\\376!\\276\\267\\360\\216\\235!\\030\\232\\364\\374\\3563\\301T\\316\\376\\235r85\\355\\251\\360?\\341\\316\\237yg\\012k\\227:\\213\\315\\272\\027\\272\\202F\\231Sj\\356''g\\223\\216}\\237\\216\\3709\\025\\344bs\\274u\\264\\257/\\374\\011\\377\\000\\231\\313_6\\305\\362\\351V_\\370\\023\\377\\0003\\341K\\177\\203\\177\\015\\215\\276\\347\\360\\271\\220\\226\\030\\335\\250\\334\\202\\001\\372IT\\265_\\203\\337\\016\\355\\343\\335\\017\\206\\374\\262\\037\\033~\\337pr?\\031+\\352\\337\\210\\037\\011$\\360\\207\\202\\033\\305\\236\\035\\326\\333T\\320\\214\\201&\\206\\352/.\\346\\330\\220B\\276\\334\\220\\351\\270\\205\\343\\240\\034\\363\\232w\\303\\317\\203>+\\370\\215\\245\\246\\262\\202\\033]\\023\\315kqr0\\354&\\034\\270\\332\\017\\001G#=k\\315\\216o\\231\\251\\335\\342go\\361K\\374\\317>\\236c\\2309\\337\\333\\317\\377\\000\\002\\177\\346|Ms\\360\\263\\300\\223\\332\\342\\333O\\373-\\332\\261\\371\\015\\354\\273\\010\\377\\000h\\2268?J\\343d\\3607\\202\\342F\\022\\333O\\034\\221\\223\\346\\017\\264I\\334\\340c\\332\\277G|M\\373+\\370\\306\\303J\\275\\271\\360\\336\\255c\\256\\274$<v\\022\\267\\223<\\340\\363\\2720\\016\\327\\004`\\234\\221\\202q\\332\\276C\\326\\354u\\030\\347\\271\\207\\354\\357\\035\\365\\234\\276]\\304\\022+\\226P\\256\\331\\\\\\002:\\021\\324\\376x\\257\\247\\300\\347\\230\\247e*\\262\\1776{\\030\\\\\\337\\024\\235\\245VO\\346\\317\\031_\\005x8\\334y\\177d`H\\000f\\346N\\2778=\\375v\\324\\311\\340\\237\\0044\\010\\306\\330\\344\\250?\\361\\365/\\370\\327a\\001\\325&\\270\\212@\\2213\\020\\037v[\\222\\316\\315\\375\\357Z\\206(\\365Ak\\030\\373:}\\321\\374O\\351\\376\\365{\\023\\307\\342[\\376,\\276\\366zk\\035\\211\\222\\277\\264\\177{=\\373\\341\\027\\300\\213\\357\\210V\\303S\\325n\\317\\207\\264gM\\366\\346\\346U\\216K\\224\\021c\\367{\\216\\017;\\227''\\275f|X\\3606\\221\\360\\363\\306\\326\\372\\016\\227yuz\\036\\312+\\253\\217\\264\\2621I\\034\\000\\243rpH\\306k\\351o\\015\\350\\215\\343O\\210-ckj\\226\\272\\\\0\\245\\265\\274\\363\\303\\346C\\247\\333F\\007Lt8\\035O''\\251\\257\\230>"\\330[Z|V\\324\\027M\\226K\\2356+\\262\\266\\3629\\0040Ry\\003\\260,X\\201\\357_\\027G4\\251[\\020\\323\\321\\037+\\014k\\255V\\313c\\317m\\2268\\204\\262\\026\\0159R\\312$<\\214\\364?\\210\\346\\276\\247\\370\\005g\\246\\350zM\\367\\213\\274C\\247\\331\\335)\\223\\375\\016k\\300|\\270\\0261\\231\\031\\027\\234\\310\\331\\0008RF\\336\\007R>^\\270\\216\\031uKkh\\303\\250\\2160\\0069\\300\\0359\\357\\305{\\257\\213e\\233J\\375\\232\\3554\\373t\\214\\303;C\\276V@$\\012]\\313\\242\\021\\316\\323\\362\\345zdV\\330\\351>x\\301=\\3151\\037\\022H\\372\\036/\\332\\247\\300\\376\\037\\324o\\261\\341\\351|G\\250M\\003D\\327\\306\\322+\\206\\3348\\014\\262o\\204\\0161\\312H\\340\\372\\236\\265\\347v\\237\\264\\277\\202\\306\\255\\004\\332\\236\\221\\343;2g\\3374\\332\\\\vk\\224\\317\\334P\\345\\231I\\356\\333\\261\\232\\370\\272\\344JT3\\276\\371\\011\\313\\223\\316[\\271\\372\\3475C\\317\\225/\\025J+\\251\\030$\\016H\\364\\372{t\\256\\370\\340i\\311(\\311\\036\\2140\\260I)\\037\\255\\317\\361?\\366v\\361\\307\\303\\231\\354\\254t\\237\\211\\267\\372y\\221ZemJ\\330\\335[\\273\\354\\302\\030\\314l\\341\\030\\371\\214\\035\\001\\213r|\\314\\244\\225\\034\\365\\277\\213~\\003\\370\\037\\300q\\330i\\367\\277\\024\\3741oo\\373\\313\\211n\\215\\263\\315\\271\\224`b5&yH\\007\\205L/\\361a\\001\\221~-\\370!\\253%\\207\\217/o\\345k\\304\\212\\033#\\001U\\234\\242\\006y#h\\367\\340}\\325\\330\\344zn\\342\\256\\374Y\\274\\324u\\317\\011hZ\\356\\247\\026\\253n\\213\\250\\310\\316\\327 \\224u\\362\\322H\\210=\\0166\\310\\003c$\\021\\222p1\\345\\325T\\376\\271\\032MY\\036mZ\\213\\353\\021\\202Z\\036\\374\\377\\000\\035>\\034\\330\\370\\266\\362\\346\\017\\370Z\\232\\246\\2056\\022(\\257\\364\\313X\\367\\307\\200y\\011u\\263p9\\031\\000t\\355\\322\\242\\370\\203\\250\\3747\\370\\267\\341\\233]G\\300\\323\\317\\341\\357\\023[\\266\\320\\332\\316\\232-\\332\\356\\005A\\2766\\235\\032H\\331\\201?+HS\\001\\202\\363\\200k\\341\\024\\226H\\265\\017*4\\221\\221[j\\221\\307N21]E\\216\\255\\250C\\325\\346\\014\\203\\3579\\335\\221\\351\\317\\030\\347\\247C]\\025\\260\\336\\306O\\224\\252\\220\\336\\335\\316{\\304\\032,\\336\\037\\361\\245\\316\\235}\\245\\311ky\\032#\\033}\\240l\\335\\273\\000\\020Ha\\226\\\\0$\\036\\275\\353:\\033\\2355m"W\\263\\237x@\\033\\216\\370\\257T\\327\\256\\355\\265\\337\\204:e\\325\\314\\3271\\370\\232\\332WD\\274\\220\\003\\037\\331#!\\2247vp\\305\\266\\373D\\007L\\212\\362/\\354/<y\\303P|I\\363\\014\\334\\240<\\363\\323\\265{xJ\\260\\225;\\315\\352z8:\\276\\346\\247\\350o\\206<S/\\205\\254-t{I\\226;}I\\014R\\336\\026\\302\\231\\037\\200\\030\\016:\\0020k\\346?\\034@\\377\\000\\360\\230\\335\\202\\024i\\351<\\211\\023\\210\\300\\015\\206 W\\333\\376\\001\\370o\\377\\000\\0117\\207\\265o\\001B\\326\\362\\\\\\352\\263~\\356i\\355LS\\3517\\260\\272\\220\\314\\017!BnV\\376\\022rq\\322\\276]\\370\\203\\341k\\335\\013\\302\\263&\\245uo3\\256\\255q\\005\\254\\233\\262\\263\\252(m\\312z\\221\\222F}\\253\\3400|\\360\\224[>^\\2174%\\027m\\031\\363\\240\\233\\311\\327d\\230:*\\030\\360>\\265\\365\\247\\207m4\\337\\034\\374\\013\\237\\302\\255\\031}BXCE9a\\271\\010\\376 O\\012\\271\\340\\347\\276k\\344hb\\3635m\\322\\020-\\367\\022\\331\\034\\257|W\\321>\\001\\361[i\\332r[\\333\\305l\\003\\271@\\256\\253\\260q\\376\\266N76:,`\\340\\221\\3105\\354f\\222\\224yg\\035Z=\\034|\\2357\\031\\243\\301|W\\341\\275c\\302\\332\\364\\372n\\267j\\366\\323\\306\\331\\363\\010>\\\\\\203\\250e=\\362\\016\\177\\032\\304\\322\\264\\353\\335kX\\212\\313L\\265\\226\\362\\355\\310\\330\\220\\214\\365\\353\\223\\372\\327\\352\\347\\300\\217\\207\\332\\177\\304_\\020kz\\357\\213|:\\332\\316\\225mh\\261\\303\\036\\251\\367\\231\\362Am\\213\\200\\275W\\345\\034/LW\\320\\032\\317\\300\\237\\002\\265\\264k\\240\\351\\366\\336\\033\\215''\\312\\265\\235\\2426\\003\\202\\244\\037\\227x\\340\\001\\313v\\310\\257F\\2066\\274\\360\\374\\321\\205\\331\\335\\012\\325\\252S\\346\\212\\271\\371\\215\\341\\277\\207Z\\276\\223\\360\\367Z\\215\\032\\341''\\2020\\367\\313\\00122\\203\\014\\362D\\307\\034\\015\\315\\030\\\\1#\\277z\\273\\251i\\277\\333\\377\\000\\006e\\273\\326\\365[\\310\\264\\343\\024q\\316n79\\212f@\\221*F2\\006\\0342\\373\\216k\\352\\233\\257\\200\\177\\030\\2741\\257My\\360\\367\\305Z%\\375\\275\\310\\204H\\227N\\321\\254\\361\\246\\360!x\\245\\216H\\330\\341\\233\\346`O=}9_\\024\\374?\\370\\263}\\342\\333O\\013i\\032.\\201\\341]\\036\\316\\336\\337P\\214\\315|\\032\\335\\247\\332\\003\\345\\243\\200\\023\\207.B\\266@\\317J\\360%C\\021\\317\\3175\\255\\3176Xj\\321\\235\\336\\347\\347u\\326\\227w\\240\\353si\\372\\225\\254\\326\\367Q\\014\\035\\314\\023 q\\271w\\014\\032\\334\\261\\261mF\\017.\\314\\213\\206\\331\\275\\231\\001o-{\\273\\036\\230\\365\\367\\257\\324\\037\\015|\\006\\360\\364\\226^G\\304G\\323<Wt\\322+\\307\\017\\317"\\356S\\227&l+\\000G\\000\\014\\017\\307\\232\\371K\\343w\\203\\277\\341W\\370\\217S\\322l-m\\355\\374;\\254+\\317\\246\\264\\007\\313h\\276b|\\262\\335J\\200v\\343<\\343''$\\223]\\365\\261\\023\\224S\\261u\\2475\\024\\237s\\346\\2039\\027\\222\\242\\204{x\\243T\\210\\214|\\241K6@>\\254\\356s\\327\\015\\216\\230\\025M|*\\0365e\\263\\210\\251\\031\\004\\242\\023\\217\\312\\243l\\177hF\\346Evh\\216\\331\\002`\\001\\351\\217n\\225\\256\\211\\037\\224\\2774\\235\\007\\374\\264?\\343]0\\2514\\364:\\242\\332>\\235\\370O\\373C\\332\\307\\246-\\317\\304I\\265M?]\\026\\351j\\236)\\263b\\376laB\\342\\346,\\222\\315\\200\\006\\365\\0318\\311$\\222O\\031\\361\\216\\377\\000\\301R\\336\\351R\\370S\\\\:\\276\\234!%C\\312\\316"\\030\\000\\200\\030\\236\\270\\317<\\363_0\\3523%\\205\\212\\331\\332l\\267\\216&*\\205X\\207\\3019#$\\2229>\\264\\350''\\377\\000F#*\\016\\315\\301\\002\\216\\017\\257\\343\\326\\252\\256^\\25255\\242L\\272\\270>f\\237ab\\2227\\326\\244v\\\\HX\\260\\3018\\301\\347\\247J\\367/\\203\\372D^%\\370\\231c\\243\\254s\\213\\213\\251\\224\\307"\\240b\\243v\\030\\240<o\\302\\266\\017n\\243\\006\\274\\036\\331\\230]n''a\\215\\001\\316\\321\\311-_V\\376\\313:\\225\\244_\\264\\226\\204oYS3\\272(\\034`\\225\\233j\\003\\330\\223\\316G<\\365\\247\\214\\242\\232Z\\221\\211\\204g%}\\217\\327\\237\\002\\370sE\\360''\\303-''D\\260\\362<\\330\\240\\022^]Iq\\373\\307v;\\311|\\362[8\\316OPj\\246\\247\\361+\\301\\232E\\357\\330\\377\\000\\266\\342yq\\231"\\022\\371\\214q\\333\\003\\356\\377\\000\\300\\211\\317\\351_0|a\\272\\361\\025\\274p(\\3275\\027\\211\\3343\\331G\\010\\313\\014\\354!\\017\\335\\340\\266\\343\\270d\\224>\\247?^\\370\\017\\340\\257\\303+/\\203\\276\\033\\361E\\274\\177\\360\\230\\331^,W\\023\\\\N\\302\\004X\\034\\256f\\221\\023\\000\\204\\004\\356\\004rO\\030\\305z\\030*\\265k?e\\207I\\036\\236\\001U\\251?gIZ\\312\\347\\004\\277\\023<\\023\\346\\312\\221\\352\\3660\\355\\214:\\000\\304\\262\\343\\375\\235\\244\\2022y\\3175\\005\\337\\210|5{k\\366\\230\\265U\\272>FT\\0172>1\\334\\225\\371\\277*\\372\\223C\\370{\\340\\310|Us.\\213\\246h\\320\\333Mj\\262[\\030\\026?-\\321\\325\\217\\312B\\343\\2023\\221[\\366\\276\\033\\322''\\261\\324\\357\\322\\332\\335\\345\\267\\222[x\\330H\\024\\237+r\\360O|\\205\\347\\245z\\353$\\305J6\\233W=o\\354LE_\\212J\\347\\347\\255\\327\\304\\217\\013[:Am\\253G|\\312\\016\\310a\\202M\\356\\303\\267 \\003\\216\\235+\\307~4\\015\\033\\342g\\354\\305\\257_\\304\\326\\363^\\350\\022\\211PJ\\233Z"\\335@\\035\\272\\364\\257\\254?j/\\002i\\227?\\010t\\335^\\333\\354\\366\\346\\373W\\265\\216=R\\336\\3532G\\276\\031\\216\\346t;\\210\\0065N\\270\\371zu\\257\\201\\376)]G\\340\\257\\331\\253P\\266\\322\\343\\270\\272\\276\\326\\357B\\\\L\\352\\314\\262\\301\\024\\177\\274nNF\\346e\\036\\277/\\035M|\\2166\\235l=\\177eR\\326>K\\035J\\255\\032\\376\\312z\\237\\013\\\\\\003$n\\303\\014\\211\\362\\201\\236\\242\\256''\\332|\\245\\304\\252\\006\\006\\007\\025\\233\\022=\\315\\320P\\304\\220rv&\\001\\037CS5\\264\\373\\316#\\237\\031\\343\\232\\323\\332(\\350\\331Svg\\013s0\\226y\\021\\225]\\211\\343`\\342\\267\\264K9a\\266\\270\\232P$>VT\\223\\317\\336#\\025\\243\\241\\370v{\\313\\231^(L\\221D\\276l\\317\\307\\310\\265\\326jz3\\351zu\\270xX\\315;\\011\\221\\017P\\203\\247O^\\277\\215ub1\\364\\327\\356\\223:\\253c\\351\\305(-\\316\\022\\366I\\322I\\021?v\\031>`Pg\\203\\305v?\\011|Ks\\240|d\\321\\357\\355\\342\\222\\356ho#\\220D\\244\\000X\\026''$\\364\\340\\232\\346^\\332g\\212\\352\\346\\350\\035\\331`\\007v$\\344\\177:\\207F\\202[\\017\\022\\307$\\236e\\274LW''i\\317<\\037\\322\\234\\334\\035\\011G\\250\\352\\252>\\301\\335\\352~\\345^\\351\\353\\343?\\005h\\272\\235\\250\\322\\344\\032\\214BQ\\366\\221\\276$b\\240\\262\\263\\016\\207p5\\322|/\\361W\\213>\\021h\\327\\276\\037\\177\\015\\336\\315\\240\\313$\\223\\233H\\330\\336C\\031\\223\\226\\362\\233w\\310\\207\\357\\004l\\252\\226m\\240d\\327\\003\\373:x\\237L\\326\\376\\010\\303\\341[\\351l\\226\\352\\025\\211-\\255\\343p\\254\\210\\031\\227\\346\\\\s\\224\\012;\\234\\374\\304\\340\\327\\264\\334i\\355\\004\\363\\207\\265\\015h\\020\\0149\\017\\032\\014p0\\006\\030\\343\\276:\\364\\247\\200\\247QF5(\\312\\314\\327\\015\\355c\\313R\\213\\326\\307{i\\361\\372\\310\\333\\330\\013/\\012j\\3266\\366\\211\\265 \\216$X\\323\\202\\240\\015\\270\\000`\\364\\350*\\275\\317\\307(\\336\\342\\3267\\360\\375\\334\\327\\204\\3147\\010\\220\\002\\2562C\\003\\362\\234\\237Q\\\\$\\272u\\244\\221\\020-\\320\\206\\214;1\\007k\\0001\\201\\236Ep\\327\\272%\\254\\222\\272\\230R;@71\\003;OQ\\203\\326\\275\\012\\231\\256>\\334\\255\\374\\316\\271f\\030\\270-O=\\370\\203\\342\\031\\274I\\361\\275\\3659\\255.\\364\\277\\014\\331\\331Z\\307\\006\\234\\017\\310\\346\\030\\317\\314cO\\220bI&\\340\\015\\307w\\2461\\362\\327\\307\\302\\367\\037\\010m\\256\\344\\263xm\\336C\\344\\022\\000\\177\\275\\220H?0\\037(\\340\\366\\0035\\366p\\321!mJ\\342(\\304I\\033\\262\\315#"\\037\\2265\\000\\263\\252\\367>\\340\\202+\\363\\317\\366\\225\\361}\\326\\253\\343\\213\\315!#\\220\\351\\220G\\376\\211''\\224Fc''\\357\\227\\007\\346=\\271\\007\\361\\353_-\\211Uj\\327R\\251.\\247\\201\\210R\\235Oi-\\317\\226\\264\\205t\\324\\226t\\2229\\242V+.\\011\\034f\\272&\\361\\006\\210\\2622\\377\\000i2\\340\\343\\036X\\342\\271kE\\223O\\224\\263\\2201\\367\\031y\\014*\\311\\206\\321\\330\\273XB\\305\\271''\\035k\\276\\265\\032U%vi5\\316\\356\\217\\242\\264]\\012\\322\\311-\\364\\235\\221$\\016O\\332$Q\\3631\\347\\000\\237L\\251\\342\\251x\\217Lk\\215M.\\012\\340\\255\\272$Q\\221\\367\\025\\224\\260?\\220\\257\\312*+\\351\\027\\206\\222\\346\\346\\372\\326\\277\\340\\377\\000\\355\\217\\245\\\\\\002\\357\\177\\254k\\376\\037\\376\\330\\375&>\\034\\214jP\\333\\336\\310!\\362gc+z\\341\\200\\037\\245k\\370\\263\\303\\3271\\352\\322Oola\\210:\\252\\005\\031\\030\\015\\201\\372b\\2770\\250\\255\\327\\207\\225o\\376\\365\\377\\000\\222\\177\\366\\306\\237\\352$\\265\\276#\\177\\356\\377\\000\\366\\307\\353\\367\\303?\\032\\352\\376\\015\\326\\257u8\\2655\\202\\376\\330\\376\\345\\245\\334\\005\\260\\331&\\371X)\\033\\260\\002\\252\\257\\374\\264gU\\030\\012\\333\\277K~\\035|a\\320<q\\247\\350\\240,6r\\275\\320\\262X&\\272\\004\\314\\300\\274q\\2518\\347{E''#\\003\\0120\\0008\\257\\345R\\212\\355\\243\\300\\363\\247kb4\\377\\000\\017\\377\\000lu\\341x>tR^\\336\\353\\374?\\375\\261\\375{_\\334\\\\Mb\\367\\266\\311kqm\\260\\313\\001\\212`\\301\\321Q\\330\\037\\305P~u\\315O\\246j\\277a\\274\\277\\237\\021\\332\\205.\\000*w\\251\\013\\2161\\2162k\\371.\\242\\273jp\\202\\227\\374\\275\\374?\\340\\235\\263\\341\\236guR\\337/\\370''\\364\\261\\3617\\307\\326\\226\\232\\005\\356\\225\\241\\264\\337\\331\\356\\031.nC\\000^A\\230\\314dzg\\037\\225~ux\\225nu\\237\\021\\337\\265\\332$\\322E\\204\\215\\231Av@p\\252[\\256\\025p\\277\\205~^Q^E\\177\\017\\034\\347\\314\\261\\026\\377\\000\\267\\177\\373c\\314\\253\\301S\\223\\277\\326?\\362_\\376\\330\\375\\015\\276\\320D\\261\\310\\266e\\245u$\\272\\267U\\256T\\371(\\305\\0109S\\203\\363\\032\\370r\\212\\350\\243\\300\\222\\202\\263\\304_\\376\\335\\377\\000\\355\\216\\212\\\\ \\341\\033:\\327\\377\\000\\267\\177\\340\\237\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (10, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\001\\001\\000`\\000`\\000\\000\\377\\355\\000\\034Photoshop 3.0\\0008BIM\\004\\004\\000\\000\\000\\000\\000\\000\\377\\333\\000C\\000\\002\\002\\002\\002\\002\\001\\002\\002\\002\\002\\002\\002\\002\\003\\003\\006\\004\\003\\003\\003\\003\\007\\005\\005\\004\\006\\010\\007\\010\\010\\010\\007\\010\\010\\011\\012\\015\\013\\011\\011\\014\\012\\010\\010\\013\\017\\013\\014\\015\\016\\016\\016\\016\\011\\013\\020\\021\\017\\016\\021\\015\\016\\016\\016\\377\\333\\000C\\001\\002\\002\\002\\003\\003\\003\\006\\004\\004\\006\\016\\011\\010\\011\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&''()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&''()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371;\\343\\367\\302=k\\340\\227\\306K\\333\\031\\254\\356\\355\\364\\023p\\302\\315\\247\\316!\\377\\000\\246.\\336\\213\\310\\007\\370\\200\\007\\275c\\370S\\304\\221\\317\\377\\000-\\031J\\2142\\036\\010`px\\372\\212\\375\\263\\375\\274b\\360\\024\\277\\012\\327O\\327<''\\177\\257\\353wV\\243jYE\\373\\311P\\277\\227\\032\\251\\376\\376\\363\\220\\337\\300\\006O\\030\\025\\374\\363\\370N=\\\\|U\\324m\\226\\031-!\\265\\221\\276\\331\\013H\\037n\\354\\225\\\\\\2168=\\353\\346sl\\272\\234\\251\\311\\310\\372\\254\\2436\\224''\\030\\332\\347\\337W\\177\\036\\265k\\177\\201W\\236\\003v[\\233mGid\\333\\271\\220\\247!\\201\\354s\\232\\363\\257\\021\\370\\213\\304\\237\\021<=\\244Y\\352\\326\\266p\\301j\\233`\\231!\\315\\304\\230\\377\\000k\\2568\\034V''\\206<2\\316b\\271\\275R\\356\\352\\030\\347\\232\\366M/@2?\\372\\275\\240\\364\\342\\2779\\251\\231\\2744yi\\263\\357\\226_\\034L\\271\\345\\023\\3174\\035\\003U\\262\\322\\356\\264\\3557Q\\273\\267\\202\\370\\342\\342\\030\\316\\004\\205pF\\177A\\370W\\275x\\007\\303\\177\\030<;\\246\\334\\335\\370{E\\272\\325\\264\\331\\000i\\243\\330\\256\\007\\035}\\275\\275j\\030<=\\012\\205lfA\\323\\034W]\\242\\353\\036&\\360\\321g\\3215\\353\\373\\015\\330\\302\\031\\011L\\203\\236\\237\\215x\\323\\315\\235Y~\\373Tz4\\262\\367I{\\221\\261\\3025\\313\\353^.\\227V\\272xt\\315^\\312R\\337\\331\\363\\256Iu\\343\\030=\\301b}\\271\\257\\263~\\032k1K&\\233g\\342\\277\\024k\\272=\\200\\213\\023\\275\\270h\\376r\\3356\\203\\362\\251''\\257z\\370\\363[\\217Q\\277\\361d\\336 \\273q=\\364\\367\\006y\\244\\362\\300\\014\\300`\\203\\216\\306\\276\\352\\375\\234N\\253\\251\\264\\332\\206\\217c\\242\\352\\366\\260\\305\\010\\277\\323\\256\\302\\227\\33027\\306\\304\\177\\013/J\\373~\\004\\253M\\325\\232\\214\\276G\\307\\361\\314g(\\306Ml}\\355\\341\\357\\354\\341\\341;O\\354\\253\\307\\276\\260+\\373\\251\\236B\\345\\207\\324\\363[\\243\\356\\372\\373\\325+H!\\202\\310$PGn\\237\\334@\\006\\017q\\305]Q\\205\\300\\351\\332\\277N?6\\026\\212(\\240\\017\\031\\370\\301\\360\\237I\\370\\255\\341{]3Q\\236K\\031\\355\\335\\266\\\\\\306\\200\\261\\211\\324\\253\\307\\236\\300\\2347\\325V\\277\\012\\376+\\3744\\360\\247\\201~8\\017\\015\\370_D\\264\\322\\354l\\356.#\\225\\342\\204\\241\\234\\305+(,s\\327\\344s\\216\\331\\257\\350\\326@\\274\\202\\240\\344|\\331\\035\\253\\360\\377\\000\\343\\356\\203qy\\373gj\\332M\\254~e\\365\\337\\211oc\\201q\\234\\264\\227\\007\\313R;\\347\\315\\002\\276\\177\\210\\224\\336\\025\\250\\365=\\336\\035qX\\270\\312[-O\\032\\360\\366\\225\\346*1\\037&\\334\\250\\356\\027\\267Oj\\365=7N\\311\\214"\\214\\036\\274\\327\\352O\\303?\\331\\347\\300^\\003\\370\\177kf\\372M\\256\\263\\254\\274\\001//\\256\\343\\016\\362\\036\\255\\301\\350:\\327\\212|k\\370\\035g\\341\\271_\\305\\276\\025\\266htrK_Z\\304\\274[\\034\\2148\\037\\335\\354G\\245~g\\232\\360\\2362\\030UY\\037\\244e|a\\203\\236*Td\\267\\331\\237''[\\351E\\202\\225\\217\\203\\316k\\240\\321\\374\\031\\253\\370\\223\\304\\260i\\032=\\234\\227w\\222\\251!S\\001@\\030\\311c\\330r+\\241\\263\\260\\005\\376\\341F\\030\\312\\373\\347\\007\\360\\317#\\333\\025\\366\\027\\354\\363\\341H\\3554\\015O\\304\\227\\021\\017:\\352_\\263\\300\\304s\\345\\247\\336\\307\\325\\217\\376;_7\\221\\3452\\3141P\\207E\\271\\364\\034C\\234G/\\302\\271\\255\\336\\307\\307>-\\370\\013\\361\\003\\302\\332\\023\\352W\\232/\\237m\\022\\026\\226KG\\023y \\036\\353\\336\\2753\\366@\\277[_\\215:\\206\\222\\237,\\023h\\362\\256\\302\\331\\033\\304\\250\\347\\371\\237\\316\\277D&\\267Y\\354^\\031\\325%\\215\\323k\\206\\347 \\365\\037\\316\\276\\007\\360\\276\\221o\\360\\317\\376\\012w\\036\\214\\212`\\323o\\347\\224Z\\355\\341v\\317\\031*\\243\\3309\\013\\370W\\352\\230N\\033\\206Y\\215\\215ZoF~W_\\210*fX)S\\250\\265G\\350"\\360\\243\\261\\3075 \\246\\256\\017j}}\\322\\334\\370\\324\\356\\024QEP\\310\\\\\\3479\\\\\\340\\023\\374\\253\\362w\\3434\\020i_\\360U\\035&[\\200\\251o\\037\\215t\\253\\207$\\343\\344\\177"V?\\230\\025\\372\\316z\\214\\327\\345\\277\\355E\\341\\251\\265/\\333\\216\\341\\355\\031\\222\\347\\373&\\332\\376''Q\\316\\344]\\240\\376\\0065\\307\\326\\274\\314\\332\\223\\236\\035\\245\\344we\\363\\345\\254\\217\\323\\207s\\032\\006\\310@\\261\\344\\373\\016\\244\\237\\312\\276K\\360\\267\\355\\215\\373;\\374V\\370\\367\\250|\\034\\320<Z\\272\\237\\210\\344\\022\\301\\034rZ\\225\\267\\276dF.\\221I\\322B\\025X\\361\\351\\306x\\257\\246\\364}B\\037\\022\\374=\\323\\265%$E\\177b\\245\\320\\034\\020YFF{\\020r+\\362\\323\\3417\\374\\023oA\\370\\037\\373jX\\374Z\\271\\370\\201>\\255\\341\\255\\002\\365\\3574-\\035-\\204s\\2316\\222\\211,\\237\\304\\252O\\030\\353\\265\\001\\357]\\236\\311N\\227+\\354\\2168K\\222i\\371\\235\\357\\2104\\317\\370G>%\\353z>\\345aiy$qa\\263\\373\\274\\345\\011\\367\\333\\264\\237|\\327\\222\\376\\325\\377\\000\\265\\307\\304\\237\\331\\333\\366z\\370?\\247|,\\263\\323 \\233^7\\322^j\\267\\266b\\341#\\362&\\342\\020\\2757\\235\\373\\216{W\\321z\\307\\303o\\022\\370\\247\\306\\372\\277\\2105MF\\035)o\\356^_\\263\\344\\022\\252\\314v\\251\\036\\313\\201\\370We\\341\\377\\000\\206\\276\\014\\323|)7\\207\\274_o\\244x\\333\\303\\317p/\\026\\313U\\201dKy\\370\\017$D\\344\\203\\200\\003\\001\\301\\030\\257\\206\\341\\336\\035\\253\\202\\307U\\250\\376\\031l}\\277\\020\\347\\264\\361\\230*T\\276\\324OG\\375\\224\\276,\\370\\203\\343\\217\\354\\013\\360\\377\\000\\342o\\212tx\\364={X\\265\\224\\335\\303\\032\\025\\215\\3329\\344\\207\\314Py\\012\\3420\\340t\\303q\\305|\\373\\373W.\\251\\240\\376\\326\\237\\017|a\\246\\027Ci\\245\\027!\\007\\374\\264\\212\\344H\\244\\376c\\365\\257\\2644_\\023\\370CM\\360\\355\\256\\231\\247\\311c\\245YZ\\306"\\266\\265\\205\\025#\\215\\007\\012\\252\\243\\2001\\216\\005yw\\304\\235\\023\\303\\336;\\327\\355n\\244\\177<\\332\\301\\344+d`\\206$\\263~\\000\\327\\331\\342\\2519$\\217\\215\\2437\\0113\\333|)\\342\\033/\\024|<\\321<Aa"Ik\\250Z$\\350T\\364\\334\\240\\225\\372\\203\\221\\370WD\\247#>\\265\\361\\267\\301?\\020\\315\\360\\373\\343.\\251\\360\\207^\\234\\215:\\361\\332\\377\\000\\302\\327\\022\\037\\225\\203\\363$\\000\\372\\367Q\\354}E}\\213\\011\\375\\300\\315uB\\366\\324\\303fME\\031\\315\\025`F\\377\\000|\\023\\323\\217\\347_\\015\\353\\342\\327\\304\\337\\360S\\217\\030A \\373U\\246\\221\\3418-e`r\\022Wtp>\\270''\\361S_bx\\257\\304\\026>\\024\\370{\\255\\370\\233S\\220Ga\\246YIu1''\\252\\242\\223\\217\\251\\340\\017s_\\234~\\033\\3615\\357\\201\\177d\\237\\210?\\032ux\\232\\343\\305>#\\236MB%\\177\\275\\3639Kq\\376\\350gf>\\325\\025\\025\\325\\212\\213i\\350}m\\037\\216,<\\007\\341\\261l\\357\\011\\201\\033\\367V\\331\\375\\343d\\234\\355\\035\\206k\\3115\\357\\214z\\216\\257|L)\\0341\\227;<\\301\\234\\016\\337\\215|u\\360\\303\\342\\344\\377\\000\\025\\264K\\373\\215fE\\377\\000\\204\\252\\316Bo\\224\\034$\\252\\334#D?\\272\\011''\\361\\257T''\\347$\\020A\\364\\253\\214l\\256\\015h{\\236\\213\\251X\\353\\004\\315\\255\\370\\236K\\024\\353\\201\\3115\\333\\307g\\360\\337\\202\\372\\314\\322\\220\\275M\\3063_)n\\3320\\007\\024\\273\\363\\222w\\344\\372PI\\365]\\335\\267\\302\\341f\\345\\3657F*N\\341s\\315|c\\373A|I\\177\\000\\3746\\324\\357\\274\\027\\253\\337F\\323\\312\\226v\\023n\\334|\\307\\3130#\\320"\\261\\372\\232\\351\\001\\034rp;\\032\\370\\367\\366\\242\\325M\\306\\265\\341\\037\\015A$\\246dF\\274\\236\\021\\374Ym\\221\\260\\367\\033e\\374\\3511\\255\\317\\254\\276\\025\\370\\203Y\\370\\365\\373#\\303{,\\363G\\361''\\303\\023#X_\\241\\331$\\314U\\233\\203\\331\\231T\\200{\\025\\317j\\373\\377\\000\\340\\227\\304\\350\\376&\\374\\033\\265\\324.DV\\336$\\262?f\\326\\354\\327\\217*u\\352@<\\205=G\\345_\\001\\376\\310Z,\\276\\031\\370%s\\252K\\225k\\373\\225\\362\\303tx\\343]\\241\\277\\026v\\374\\253\\331\\356\\265\\017\\370T\\037\\266?\\206\\274cm''\\331|\\027\\342\\331?\\2635\\324^#\\212\\340\\200\\022b:p\\304\\034\\373\\265$6}\\365\\031\\314@\\373S\\351\\211\\367\\006F\\015>\\250\\223\\344\\237\\333\\023]\\272\\266\\375\\233\\364\\177\\010\\351\\317\\267Q\\361_\\210mt\\330\\327\\273 c#\\177\\343\\311\\032\\377\\000\\300\\253\\317~%xF\\015c\\366p\\324<\\003`\\020\\013}.;{\\025\\350C\\333\\240\\021\\257\\342\\310\\246\\247\\375\\245\\265\\375#Y\\375\\251\\377\\000f\\235:\\317T\\323\\365\\033/\\355\\373\\251\\256\\276\\313p\\222\\354tkC\\020m\\244\\343''v>\\225\\265\\342\\033\\323\\016\\255:32J\\222\\220\\315\\214\\345\\207\\007\\364\\250M\\311^\\345\\351\\027\\251\\370\\305\\240\\352\\232\\217\\302_\\332*\\337S\\2229a\\263\\206r/"`y\\201\\233l\\212G\\260 \\217\\367E~\\211[]\\333]i\\260\\\\\\333\\312d\\267\\222%x\\234te#\\217\\360\\374+\\234\\370\\273\\360{F\\370\\201\\003\\336\\330\\010t\\355ck,\\304\\214\\244\\374g\\346\\364''\\247\\025\\344\\237\\015ou\\335\\017F\\277\\360\\037\\211\\343\\270\\267\\325\\364y\\002\\300\\32272\\333H~V\\007\\270V$~T\\342\\304\\371z\\037A\\264\\361\\246T\\234\\221\\353Q\\033\\250\\363\\367\\277*\\343\\033Rf\\001\\373\\267\\3143\\330\\036@\\374\\210\\250\\232\\374\\355\\317\\031\\252$\\355\\215\\324x8`NA\\301\\357_4]x/P\\370\\247\\373lj\\322<\\204h\\032$V\\326\\362]\\205\\312\\203\\261%h\\201\\376\\36125z\\347\\333\\230m$\\344\\227\\015\\222z\\005\\347\\374+\\244\\370{g\\016\\217\\242\\370\\205\\355\\362E\\366\\254\\367/''\\361\\034\\302\\200\\023\\371\\021I\\336\\332\\001\\356\\032;Zh\\372\\035\\236\\233`\\211\\015\\235\\244K\\004(\\243\\000*\\000\\277\\373-T\\370\\324\\260\\352\\277\\261\\217\\211$c\\272m6Kk\\270\\034\\377\\000\\003\\011\\343^=\\366\\263\\376u\\317\\177h\\342S\\214w\\343<\\365\\030\\374\\371\\256;\\343?\\212R\\307\\366S\\272\\323\\014\\301n5\\275F+dU''w\\224\\216%-\\217\\342\\371\\243U\\377\\000\\201\\032\\204\\343\\027\\3571\\3019+\\243\\364\\377\\000\\301:\\234\\372\\317\\301\\217\\010\\353\\0279\\027W\\3725\\255\\324\\331\\376\\364\\220\\243\\237\\324\\232\\351\\277\\032\\370\\303\\303\\277\\266\\017\\301\\015\\027\\301\\032F\\213\\035\\317\\212D\\032}\\224V\\321\\267\\366C`\\254h\\020\\036\\276\\200WA\\377\\000\\015\\233\\360@\\214\\377\\000ix\\207\\377\\000\\005\\017\\3765\\232\\305R\\177i}\\350\\327\\352\\325W\\331\\177s?3"\\321l<''\\361\\007F\\361\\025\\224S\\233\\2356\\366;\\230\\332IKc\\313a \\343\\276v\\025\\374k\\355_\\022\\353v\\327\\360\\331\\353V\\022\\254\\332V\\247\\027\\235m0n\\033w g\\3279\\030\\366\\257\\222\\374I\\376\\262O\\252\\377\\000:\\364\\357\\204\\362\\3137\\354\\337\\256$\\322I*\\333\\352\\230\\267WbD_7\\360\\347\\356\\365=+\\362>\\003\\342\\034]z\\256\\235Wt~\\245\\307\\034=\\204\\241IT\\244\\254\\316\\304\\336\\273\\236\\034\\216\\370\\306\\0105\\346\\3367\\266\\202MCO\\326\\331\\266\\\\G\\024\\226s0L\\023\\023\\262I\\327\\331\\243\\343\\352k\\262^o\\242S\\312\\226|\\203\\320\\327\\017\\343\\266c\\341-/$\\234\\335\\214\\363\\327\\344\\232\\277b\\345\\264T\\217\\312\\342\\323\\225\\216(\\352Yi7\\272\\243\\201\\227@xR{\\012\\2436\\250\\333\\307\\224C0\\341\\263\\\\\\374_\\361\\365''\\272.}\\370\\250&\\377\\000\\220\\214^\\3543\\357R\\347\\331\\026\\327*\\271\\320\\213\\326\\373W\\230fb\\242"\\2051\\221\\270\\364&\\273\\317\\003\\352\\336}\\375\\356\\234\\356K\\311\\036\\345\\301\\356\\243\\221^H\\274j7\\330\\343\\225\\376u\\335x\\014\\006\\370\\273\\341\\365`\\0305\\301V\\007\\370\\201+\\220}\\252\\233\\214\\272\\031Sr\\226\\332\\036\\335)\\266\\263\\360\\325\\316\\265\\254\\335\\305\\247h\\260\\035\\323\\336M\\362\\2508\\317\\313\\375\\3469\\001Tw\\316x\\257\\224<S\\343\\031\\374o\\361\\012\\013\\245\\213\\310\\322l\\2431i\\266\\344\\361\\032\\236\\256\\343\\373\\345\\267\\022\\007L\\327\\177\\373S\\335]\\017\\213:\\016\\226.n\\006\\230\\226\\222\\310\\226\\202C\\344\\253\\001\\303\\004\\350\\017\\276+\\303\\2741\\314\\026\\204\\362Ly9\\357\\305~W\\306Y\\315y^\\232\\321\\037\\250p\\276IJ\\034\\265\\244\\356\\375\\017k\\360\\316\\237\\001Vi \\212@P\\000Jrp\\000\\316?\\012\\355\\305\\205\\226\\301\\376\\207o\\323\\376}\\305s\\376\\031\\377\\000\\220lg\\276\\332\\352\\303\\035\\243\\223\\323\\326\\277''\\247\\217\\257w\\357\\037\\251<\\035\\033/u\\037\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (11, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000d\\000d\\000\\000\\377\\354\\000\\021Ducky\\000\\001\\000\\004\\000\\000\\000\\036\\000\\000\\377\\356\\000\\016Adobe\\000d\\300\\000\\000\\000\\001\\377\\333\\000\\204\\000\\020\\013\\013\\013\\014\\013\\020\\014\\014\\020\\027\\017\\015\\017\\027\\033\\024\\020\\020\\024\\033\\037\\027\\027\\027\\027\\027\\037\\036\\027\\032\\032\\032\\032\\027\\036\\036#%''%#\\036//33//@@@@@@@@@@@@@@@\\001\\021\\017\\017\\021\\023\\021\\025\\022\\022\\025\\024\\021\\024\\021\\024\\032\\024\\026\\026\\024\\032&\\032\\032\\034\\032\\032&0#\\036\\036\\036\\036#0+.''''''.+550055@@?@@@@@@@@@@@@\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\237\\000\\000\\002\\003\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\006\\003\\004\\005\\001\\002\\007\\001\\000\\002\\003\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\000\\001\\004\\000\\002\\003\\005\\006\\020\\000\\002\\001\\003\\002\\002\\007\\004\\005\\012\\007\\001\\000\\000\\000\\000\\001\\002\\003\\000\\021\\004!\\0221\\005AQa\\201"\\023\\006q2R\\024\\241B3S$\\221\\261\\301\\321br#s4\\025\\341\\202\\222\\242\\262\\203\\2635\\021\\000\\001\\003\\002\\003\\005\\005\\010\\003\\000\\000\\000\\000\\000\\000\\000\\001\\000\\021\\002!\\0031Q\\022Aaq\\023\\004\\360\\201\\2412\\005\\221\\301"Bb#\\024\\025\\261r$\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\177\\242\\212*(\\212Y\\3657\\252%\\345\\362\\374\\216\\020\\003$\\000d\\225\\200!\\003X\\256\\321}O\\264S5"z\\317\\225O\\0270nb\\252^\\014\\200\\013\\260\\032F\\310\\241,\\307\\264-\\352\\227\\014\\204I\\216)\\216\\216\\335\\233\\227\\343\\033\\307L\\016\\366s\\260\\023\\263\\260X9y\\231Y\\262\\231\\262\\345i\\244&\\367n\\035\\3124\\035\\325\\015\\024RD\\222\\\\\\227\\342\\275D!\\010DF\\021\\214"6D0E\\012YX:\\222\\254806#\\274QE\\007l\\0211\\022\\004H\\002\\016 \\340\\2319W\\254\\363\\261\\344T\\346''\\346q\\370\\026\\012\\004\\253\\336,\\017~\\275\\264\\361\\014\\321dD\\263B\\342H\\234]\\035M\\301\\025\\362:e\\364\\217=|\\\\\\204\\345\\231\\015|i\\333l7\\327\\313\\221\\216\\200v1\\372{\\351\\233W\\211:e\\334W\\033\\324=:1\\211\\275`io<\\006\\015\\234rl\\223\\325\\024QL.:(\\242\\212\\212"\\202\\003\\002\\254.\\016\\204\\036\\004R\\347?\\365`\\345\\231\\015\\205\\213\\017\\233\\222\\200oi4\\215w.\\345\\321M\\333\\217e)e\\372\\203\\234e\\20222\\334%\\357\\265\\017\\226\\275\\037\\005\\257\\303\\246\\263\\225\\330D\\263\\271\\310&\\354\\364\\035E\\350\\352\\021\\214 E%p\\260?\\311\\360O\\271^\\232\\344\\231L\\317.*\\207k\\335\\320\\262\\033\\237\\335"\\225}U\\311\\271G*\\206\\005\\305i\\027&V$)`\\303`\\342[p\\277\\036\\035\\365\\200\\347.{1Yg\\035\\014C\\270=\\3665\\037\\313\\276\\3175\\261\\334&\\244\\271\\215\\255\\247\\023{U''3 ~\\331/\\363\\020\\3457\\323\\364\\242\\314\\341\\376\\270\\304@\\326\\324.\\030\\307\\206^\\013\\316\\323\\361\\237\\243\\365W\\240\\010\\351''\\333j\\215L\\004\\370m\\355\\003\\364\\324\\200[\\374u\\245\\217j.\\304\\030\\324\\020xH\\311v\\270E\\305\\215v\\212\\013D\\377\\000\\351\\036t\\334\\303\\025\\261r_~V>\\245\\230\\222\\316\\204\\350\\306\\375\\\\)\\202\\276U\\3139\\204\\274\\267:,\\270\\217\\270F\\365\\370\\220\\373\\353\\323\\304W\\323\\276w\\023\\357W\\354\\274\\377\\000\\372\\276?e5\\316\\373z\\276o/~k\\203\\372\\321\\371\\242\\333\\216I{\\300}\\000\\326\\036\\322\\337\\325K$\\221\\304\\206I\\\\"/\\026b\\000\\036\\322k\\0377\\2369f\\217\\003iQ\\247\\3147\\210\\023\\255\\366(\\265\\375\\267\\267a\\253q\\301\\026t\\257\\2238\\022\\306\\216\\321\\301\\021\\271\\214yd\\251\\220\\251\\320\\276\\353\\370\\272\\270u\\225\\310C$b6]\\217\\037\\201\\322\\326\\332\\313\\241\\026\\246\\355D\\0311\\\\{\\222\\220\\210"\\217\\272\\253/\\232\\362\\374\\314\\234\\2313\\244\\230L\\322\\025\\363l\\233J\\252\\250M\\300)\\361X\\016\\025\\247\\311\\271\\026.N\\323\\002\\253"\\200\\315\\224\\3009\\271\\261\\033/\\322F\\272p\\251j\\270I\\361\\362`\\310\\211\\3440\\343;J\\270\\261\\270\\211Y\\332\\304\\356;M\\301\\267\\016\\332\\264\\254\\306\\017+q\\370\\217\\207\\005q\\325\\335\\271\\010\\332\\271rZ \\032#\\001\\270\\034\\373\\323\\003\\362\\274B\\247\\037\\036&w:<\\314\\356\\002\\022/~"\\347\\366W\\350\\256C\\351\\354q\\027\\227\\225+\\344\\022<D\\177\\010w\\010\\354m\\337Qc\\363\\036i?9,\\215\\217\\375\\223\\313\\276\\355\\300J\\255\\266\\3442\\223\\2700m5\\034;j\\336O;\\301\\204\\021\\013\\214\\231G\\004\\210\\356\\027\\352g\\367W\\274\\326@\\313\\0075D\\304\\006\\362\\346\\351{\\237\\372{\\0167\\205#$y\\367\\332\\334]<\\2737\\037\\254\\0106\\361p\\245\\234\\2749q$\\331&\\240\\373\\25680\\246\\334\\214\\231\\262\\346\\363\\347\\012\\030\\015\\250\\213\\301\\024\\353k\\236=\\247\\247\\252\\251\\346b|\\352|\\254hd\\310mbD\\261`GN\\274\\027\\254\\232\\227zq+ni1\\331\\212c\\241\\365\\031X\\274#YZ\\225$2\\372\\242\\225\\350\\257RF\\361J\\361H6\\311\\033\\024u\\352e6"\\274\\3275z\\220A\\000\\202\\356\\212\\233\\373\\226_\\337I\\366?)\\357\\037\\262\\3708\\360\\250j_\\355\\371\\237t\\337e\\363\\234\\017\\330}\\347\\262\\215X\\344\\341T\\351\\346E\\374\\332$\\334\\034?\\271}S\\0221\\024\\012\\241vn%\\312\\374-!.\\303\\270\\265e\\317\\312\\243\\346\\023I\\230\\222\\266>Av\\215E\\201FXI\\217\\306\\232n\\324^\\367\\006\\326\\027\\265^\\303a\\014\\322\\340\\263\\233\\241\\337\\216\\256w1\\205\\200\\351\\342B\\276\\345\\327[Z\\245\\226\\002dIb\\262\\310\\254\\013_\\203\\013mo\\243\\363\\012\\350TTl^C\\034v\\245\\3519g8\\215\\210\\030\\3130\\350h\\344Q~\\3516\\332\\275\\016W\\315[m\\261\\325w^\\373\\344_\\017\\267f\\357\\242\\230g\\204\\312\\253\\265\\212:6\\364a\\361\\000F\\243\\244\\033\\353Qo\\346`[\\312\\201\\317Cy\\216\\243\\375>[~z\\2776y\\252\\362\\341\\227\\212\\307oLM(\\337+\\343\\231:\\214E\\377\\000\\336X~j\\253\\221\\213\\225\\202\\311\\024\\320\\350\\347lM\\015\\335Y\\276\\033\\005\\005O\\264w\\326\\336<\\231\\011\\236W"Ui'']q\\342\\014\\311\\020Ap\\345\\233\\205\\357n\\002\\372i\\241\\2533\\311\\023\\342I(a\\261Ap\\375\\000\\306w\\006\\356+@]\\220r\\350\\233q,\\033\\330\\2611y6~M\\316G\\340\\343\\340\\007\\205\\345a\\331bU{\\357\\354\\025\\241\\006$\\274\\2767L\\0344g \\377\\000\\022I|r\\021\\303{\\024&\\264\\224\\335A\\265\\256/j\\355\\011H\\313\\022\\244@\\216\\001#\\317\\312\\322l\\311r\\263c+\\221+n\\222;l@\\334\\016\\320\\011\\352\\343sz\\216^O\\203"\\333aC\\320T\\220i\\273\\232\\362\\370\\3631\\244*6d\\252\\237*U\\260`F\\273o\\324z\\251J8ay\\235\\025w\\306cV\\022\\022K\\206k\\360s\\342\\275\\200<kKb\\022\\015\\242;\\320\\235\\313\\300\\211sgO-pm\\212\\256\\007\\246\\344\\312\\346\\253\\206\\316~[i\\225\\346\\026\\007`6*\\007\\305r\\007\\323O\\377\\000''\\215\\367c\\354\\374\\216\\237\\263\\370iw\\224\\211S\\230b.\\355\\344\\027\\0225\\270\\247\\226\\374{\\366\\323ES\\361\\355\\352\\322\\307O\\235\\275\\313o\\331uT\\236\\270\\353\\0216D\\266\\3515''\\215\\025>e\\200s#M\\2224r\\306n\\214\\247o\\036:\\330\\221\\247\\002*\\276;\\347\\254"(\\204\\322\\314\\332\\231s\\002"\\240\\035~P\\324\\376_mjQU\\342\\261b\\017\\302@\\324^TrN\\012\\274y\\210\\316!\\225L\\023\\221\\244o\\365\\272\\3660\\360\\267\\036\\216\\373T\\276b\\025$\\033\\201\\272\\340q\\360\\350\\332Wd\\2169P\\307*,\\210\\334U\\200`}\\240\\322\\326FB\\343\\316"\\344\\354R(\\336\\363;\\223"\\226QkF\\\\\\261 \\373\\255\\321\\240\\333\\255\\020\\011,\\003\\242d\\006%0\\256>3@cH\\324C''\\210\\252\\215\\241\\267k\\256\\333q\\242\\\\e\\220F\\227\\331\\014d\\023\\032\\200\\003m\\261P\\177d[\\207\\350\\245\\356]\\317\\371\\202o\\031\\020G&21X\\274\\242VK)\\261\\260s\\266\\300\\3505\\032u\\326\\242z\\203\\227\\260\\273\\011cn\\225h\\234\\221\\336\\201\\207\\34454\\221\\210(\\352\\033\\010+N\\212\\306\\223\\324p\\371\\237\\301\\212F\\213A\\275\\221\\222\\315~\\257x\\213~\\317\\351\\267b\\347\\370\\371R~\\011\\304\\256\\252L\\270\\2441\\221lmpbW\\351\\320\\364v\\365\\203LC"\\003\\341VZ\\031\\371+\\211\\207,\\355\\365W\\302:\\331\\274*;\\311\\245P!\\307\\213\\241\\021F\\244\\330p\\026\\271\\374\\225\\253\\014\\031|\\365$nc\\034\\3301\\305''\\341\\221\\010B\\302\\336\\373+\\241k\\215x\\351\\256\\202\\342\\365w\\027\\221\\362\\354i\\022m\\206i\\323\\335\\226f.F\\267\\270\\007\\302\\017h\\002\\257nzA\\245J\\316q\\324\\303''Uy\\026\\014\\302W\\316\\310F\\214\\225\\362\\340\\215\\305\\230)\\325\\235\\224\\352\\011:\\016\\317mmQE\\015gV\\255\\252r\\343\\245\\267\\272(\\242\\212\\252\\272\\306\\347|\\300\\334\\362\\370\\030\\\\\\257\\342XX\\225G\\004\\010\\373\\013q\\354\\036\\332\\302\\311c\\034i\\014^\\027\\224\\210\\343\\333\\365E\\265o\\362\\250\\253\\334\\303\\377\\000\\253\\233\\373\\351\\377\\000\\214uE\\377\\000\\256\\207\\371R\\377\\000\\312*b\\333h-\\216\\324\\274\\237]pz>\\012X\\343X\\243X\\320YP\\005Q\\330+\\335p\\360\\256\\326\\2535\\303{\\033hz\\015y\\307\\310\\224\\010\\262\\261\\310Y\\323Q}E\\370:7gA\\375u\\356\\240\\305\\367d\\376l\\2348{\\306\\204\\267\\340\\325Dx\\270d\\343\\217<y0$\\361\\033\\244\\202\\343\\205\\307X6\\276\\240\\350jJ\\312\\364\\357\\364\\263\\377\\000=\\270p\\367\\022\\265i3\\27167\\242\\212(\\250\\242\\377\\331');
INSERT INTO avatars (avatarid, image) VALUES (12, '\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\001\\000\\000\\001\\000\\001\\000\\000\\377\\355\\000\\034Photoshop 3.0\\0008BIM\\004\\004\\000\\000\\000\\000\\000\\000\\377\\333\\000C\\000\\002\\002\\002\\002\\002\\001\\002\\002\\002\\002\\002\\002\\002\\003\\003\\006\\004\\003\\003\\003\\003\\007\\005\\005\\004\\006\\010\\007\\010\\010\\010\\007\\010\\010\\011\\012\\015\\013\\011\\011\\014\\012\\010\\010\\013\\017\\013\\014\\015\\016\\016\\016\\016\\011\\013\\020\\021\\017\\016\\021\\015\\016\\016\\016\\377\\333\\000C\\001\\002\\002\\002\\003\\003\\003\\006\\004\\004\\006\\016\\011\\010\\011\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\016\\377\\300\\000\\021\\010\\000Z\\000Z\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&''()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&''()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\376\\177\\353c\\303\\3277\\226~>\\320\\356\\364\\370Z\\342\\376\\015B\\031-\\242X\\274\\323$\\213"\\225P\\230;\\211 \\014`\\347\\245c\\327\\244|\\033r\\237\\265\\347\\302\\267RC/\\2144\\302\\010\\355\\376\\227\\025\\000z\\017\\216>"\\303\\3612\\0134\\323\\364k\\013\\257\\034k\\0270\\331\\371Vzb\\302`\\330B\\243DT\\000e\\231\\233\\223\\350\\000\\365\\257\\323_\\201\\276\\014\\3607\\301/\\002Eq\\342mX\\353_\\025m\\264\\262\\321\\333[\\304.c\\321ZC\\226\\2160\\334y\\255\\274\\345\\273r\\007\\033\\211\\332\\360\\227\\300\\017\\206\\272\\177\\355\\327\\240\\374F\\322\\274?m\\246]\\013k\\231&\\264\\206\\\\[\\233\\307\\332\\251:\\304F\\020\\252\\231\\017\\312@,F\\024\\036k\\333\\274Q\\360wE\\203\\307\\015\\251\\351\\020\\245\\344\\267\\357\\376\\220\\215l\\351$S\\034s\\024\\201\\324\\344\\364 \\344w\\372\\370\\331\\236/WI?_\\362=\\334\\247\\001\\031/k-\\272\\177\\231\\362M\\367\\212~#x\\337\\366e\\361\\207\\305];\\340\\377\\000\\203o~\\027xf\\356;]V\\033\\313\\2225\\331\\213\\270\\304\\214\\341\\303\\254\\330*\\333B0P\\313\\234\\364\\037~\\374\\001\\361\\306\\275?\\300m\\013\\302\\267v\\032\\254~\\015\\271\\215\\344\\263\\262\\327\\206\\311-\\020\\200QJ\\270\\334:\\237\\227\\215\\247885\\342\\332\\236\\265\\340/\\206:\\332\\303\\252\\217\\370H|X\\370W\\216\\311\\026A\\021\\030%\\036g\\300w^2\\006pq\\305v\\362|~\\263\\360\\374:.\\245\\342\\257\\207\\232\\236\\225ay\\345\\265\\275\\356\\242\\206G\\010\\335\\030F\\234?C\\362\\203\\236\\010\\3005\\340N\\3527\\177\\327\\221\\354W\\244\\346\\224i\\307\\347\\337\\314\\372\\201\\3747\\253\\334x3P\\324l\\365K\\301\\244F\\007\\332"\\0239\\211Q[\\2207dm\\030\\347\\007\\214\\014\\361^\\221\\360\\233\\307^\\023\\260\\271}*\\363R\\207L\\272\\226LE%\\311\\330\\222\\023\\214\\000\\307\\216F9\\350x\\257Q\\264\\260\\320\\274_\\3738\\333M\\341\\271\\254\\3564-WKV\\262}2S\\260\\253\\2562\\216y\\340\\223\\327\\221\\214\\034\\021_\\315\\377\\000\\304?\\023k\\372\\027\\355I\\343O\\015Zx\\233[\\321um/\\304\\327v\\357+]I\\014\\022\\224\\220\\251\\033\\000\\330\\006\\340OLs^F\\025\\325\\257VT\\371l\\2678\\271#:m\\267\\252\\320\\376\\247\\243\\303F\\254\\010`FA\\035\\353\\371\\205\\377\\000\\202\\322\\301\\004_\\360U/\\004\\311\\0241\\307$\\377\\000\\013\\254d\\231\\225pdo\\355-Qw\\037S\\265Tg\\320\\017J\\375;\\375\\217?h\\177\\211v\\036=\\322~\\016\\374t\\202\\371\\244\\325\\354\\241\\271\\360v\\255r\\212\\035\\343t,\\221\\226\\037\\353au\\037\\273\\220g\\014\\012\\236\\010\\333\\371\\223\\377\\000\\005\\251\\377\\000\\224\\245\\370\\007\\376\\311U\\217\\376\\2355Z\\373\\214\\256\\244g\\035\\036\\275O\\237\\304Q\\2259Y\\237\\220TQEz\\3478W\\243|\\036\\377\\000\\223\\270\\370Y\\377\\000c~\\233\\377\\000\\245qW\\234\\327S\\340i\\365\\273_\\215\\236\\017\\272\\360\\314\\021]x\\222\\035n\\322M*\\031SrIr&C\\022\\260\\356\\013\\205\\004P\\007\\364M\\340\\313\\003\\377\\000\\011\\254\\372\\375\\3229\\263Y\\022\\322\\321@\\345\\330r\\370\\374X\\014\\373U\\237\\332\\033\\342\\216\\251\\360\\357\\341\\035\\235\\337\\207,>\\337\\361\\007\\305\\267CA\\370\\177\\244\\200C\\264\\362\\215\\262\\3376\\001\\341\\006p\\307\\201\\301\\310\\310\\256c\\300\\0365\\270\\327\\364\\335\\027F\\232\\332;{\\333K\\313\\265)\\030\\3322\\263yRI\\311\\316\\013\\207\\301=\\261\\351_\\236\\277\\264G\\3557\\342\\253\\377\\000\\333\\033_\\327\\274\\005\\015\\235\\240\\360\\226\\245\\036\\211\\341\\355~`&6\\177f\\371\\247\\026\\3612\\341K\\312\\253\\272C\\222R8\\361\\214\\223_0\\251\\373Z\\322\\233\\356}''\\264\\225*0\\207\\221\\364w\\200?d\\205\\203\\301\\237\\021\\274g\\361C\\306\\372\\353\\353^\\034\\324%\\260\\325\\246\\216\\337j\\213\\324\\201$1+\\026\\3716\\231\\021U\\000\\301\\030by\\343\\310\\364\\277\\206~+\\273\\370\\245\\255\\246\\203\\3426\\231\\264\\3152mRI\\265\\231XCu\\015\\264BIQ3\\362\\310\\301\\006\\002\\256O\\004v5\\360\\344\\337\\023\\276)\\336x_]\\320\\357~"\\370\\307Q\\321u\\255o\\373kZ\\322\\3565y\\232\\337Q\\275#\\015<\\313\\273\\347\\221\\200\\301l\\344\\200=\\006?a\\376\\002~\\320\\226_\\264E\\326\\273\\246\\351\\276\\000\\360\\267\\202\\365o\\012hSk\\177k\\271\\276h\\367Hah^\\322.\\030\\030\\237\\177\\224\\205\\211r\\322\\006*\\333B\\32769\\325\\247\\013\\307U\\367%\\376g^\\023\\026\\333\\264\\377\\000\\257\\326\\347\\325\\037\\360N\\337\\037K\\250\\370O\\342?\\207\\327Q\\274\\326<''gye\\250hR\\310\\206\\030\\242\\373JN\\322"F\\334\\306wDIO\\\\\\237Z\\247\\361\\247\\340L\\3767\\272\\370\\237/\\202\\274?\\340\\373\\275{\\305\\232\\310&\\353Q\\002+\\235:\\372\\326\\340\\305"\\244\\270\\375\\332\\314\\260\\243\\006\\373\\273\\345|\\360s\\\\\\207\\354\\207\\245\\370\\177\\303\\277\\037u/\\017x\\016\\312\\362\\313\\302Z\\327\\216\\365y4\\333\\031$i^\\336\\313O\\260\\3739\\3137$\\013\\213\\251\\023\\223\\214\\2168\\257\\2744\\317\\024\\370S\\304z}\\207\\212\\3743\\272\\353K\\275\\3615\\315\\265\\354\\300\\002\\253\\313G!q\\3362\\361\\251\\366$\\032\\371\\\\mnZ\\234\\321\\321~{o\\363G]''*u\\345+]\\265\\257\\256\\277\\327\\352~kxc\\306z\\334\\036*\\370\\037\\241\\370\\367\\302w>\\031\\370\\221\\360\\353\\306\\342\\303R\\321\\011\\011<:cKo-\\263+\\203\\266H\\203\\213\\201\\362\\226\\030`\\006A\\025\\362?\\374\\026\\225\\304\\237\\360T\\237\\001\\2609\\377\\000\\213Yd?\\362\\251\\252\\327\\357\\206\\253\\360+\\303\\036''\\375\\241t\\177\\210\\332\\344\\217s\\250i\\366?g\\021\\242\\251Y\\361&\\345\\022\\026\\311!G@\\010\\301\\344c\\002\\277\\000\\277\\340\\263W\\266\\267_\\360U?\\011[\\333H\\036K/\\206v\\020\\334\\201\\374\\016o\\365)q\\377\\000|\\310\\247\\361\\257\\244\\341\\352\\325g\\211m\\253&\\256\\327\\337\\370\\037=\\232N\\224\\243\\025\\007v\\217\\311:(\\242\\276\\330\\361\\202\\275''\\340\\323\\024\\375\\257\\276\\024\\270\\352\\2761\\323\\010\\377\\000\\300\\270\\253\\315\\253\\322\\376\\013\\205o\\333\\027\\341:\\261\\302\\237\\031i`\\237o\\266EQS\\340eC\\342G\\354?\\214|T>\\021|&\\370\\301\\361\\002EQ{\\246\\331\\033-)\\261\\307\\333g\\312G\\217\\\\<\\254\\307\\375\\334\\366\\257\\305{\\031\\245x\\246yey%\\226C$\\217#\\344\\263\\036\\254s\\324\\237Z\\375\\007\\375\\272<g\\250\\352\\232f\\217\\360\\357C\\262\\270\\226\\316\\303\\376*?\\022\\315\\022\\034G\\347\\261\\212\\321_\\035\\266\\356o\\373h\\236\\225\\371\\260\\036x\\2669\\014\\212y\\\\\\214\\002+\\315\\301\\320n\\227\\233=lf"*\\242Mh\\277\\257\\362;\\333A\\211A+\\204\\3342O~k\\351?\\331\\252\\353L\\360\\337\\355\\251\\341\\037\\020\\353\\376\\033\\377\\000\\204\\273@\\321\\365#wy\\2433\\355K\\215\\221\\263\\243\\034\\220\\017\\226\\341d\\031<\\025\\007\\250\\025\\362\\316\\221t\\323\\335yL\\371y\\023\\000\\236\\200\\366\\257\\320\\037\\331g\\303V\\227\\277\\264\\277\\207\\265\\031,\\333RA\\266{\\2503\\205d\\214\\011dS\\354\\301\\025\\017\\375t\\256\\014l\\275\\2159\\271tL\\354\\303SUe\\033w?_\\277e\\037\\010E\\360\\343\\342g\\2105\\335v\\344\\310\\276\\036\\360\\334\\032L\\262\\\\eqyu+\\337_:\\223\\367\\261\\345\\271f\\316q\\037\\326\\231\\373*E\\256Y~\\307:\\207\\216\\357\\336x\\374;\\252O5\\306\\221\\035\\303b#\\031\\275\\221\\374\\300\\271\\356\\024\\034\\236\\330\\353\\326\\274[\\366\\217\\361\\236\\275\\360\\317\\3417\\303\\177\\206Z;]\\\\|B\\370\\216\\327\\317;K\\0202,\\372\\207\\225df`\\275XA4\\252\\007fs\\212\\375\\013M#\\302\\036\\034\\375\\220\\3741\\360\\337\\303R\\266\\241o\\247\\245\\276\\221\\004@e\\346{uT<\\037\\357\\025\\317\\374\\012\\2777\\307\\326\\224\\360\\374\\322{\\335\\257;h\\355\\352\\331\\357a\\327%Ue~f\\223\\362H\\351|\\011\\343\\337\\267\\374\\026\\325\\274S\\252,\\260\\351v)=\\320\\221\\376S$q\\344\\222\\007\\277A\\353\\332\\277\\227_\\370(w\\210/\\274Y\\373wi\\376''\\324\\247\\216\\342\\367S\\360\\2743\\310\\321\\260d_\\364\\313\\305\\010\\244vP\\241}\\266\\327\\350\\207\\374\\024\\223\\366\\220\\361\\337\\303?\\204\\332O\\301/\\207\\020\\\\h\\372\\026\\251\\270x\\267\\305\\366R}\\353\\205''\\376%p\\225\\377\\000VQ@gs\\313\\036\\023\\005\\034\\327\\340e\\324\\322J\\320\\253<\\206(\\343\\333\\0123\\222\\021w\\026\\300\\317A\\226c\\217ROs_\\177\\302Yt\\350aUZ\\253\\336\\222\\373\\227E\\363\\334\\371<\\342\\265:\\230\\231\\272\\177\\015\\312\\264QE}i\\344\\205z/\\301\\371\\032\\037\\332\\327\\341t\\25072x\\273M`=H\\273\\210\\327\\235W\\242\\374\\037,?kO\\205\\3051\\277\\376\\022\\3557nzg\\355qVu]\\240\\375\\013\\247\\254\\321\\372>\\226\\363x\\303Z\\375\\257\\274;`\\336\\036\\272\\361g\\214WN\\3204(\\365\\255\\255\\031\\206\\335m\\246Ic8%\\037\\313\\214\\354e\\343t\\2319\\333^M\\361?\\340\\277\\304_\\006|L\\370\\227\\341\\037\\017x\\247N\\370\\235\\360\\373\\300\\332\\025\\265\\376\\263s\\251\\330}\\222\\031]\\342_6\\336\\335N\\345\\222X\\344%\\177v\\300\\364\\350s\\215McD\\236\\333\\343\\206\\253c\\247\\352\\327~\\023\\370\\217\\244\\301\\262\\322\\356@\\0255\\025\\376\\022s\\334''\\361\\014\\340\\234`\\327\\236\\267\\305?\\036\\370O\\304\\366\\003\\304s\\353\\272\\325\\275\\215\\342Mp\\267\\314\\367\\021\\314\\201\\376eQ\\320\\202\\245\\325{\\015\\3358\\343\\347\\243:\\222\\370\\032\\351\\243\\371~\\213OS\\352\\345\\207\\214o.f\\257\\367?\\352\\347\\222\\370k\\341?\\213u\\253-\\017\\304\\266>\\011\\361,\\036\\035\\327L\\246\\302V\\260\\220\\333\\335\\010\\237l\\206\\0310AU<\\036x\\257\\332_\\370''\\347\\354\\373o\\252\\374+\\274\\361\\325\\314\\351q<\\272\\233\\332\\033PN\\350\\240\\211\\221\\345U\\035\\203m\\2120{\\345\\375+\\300\\274!\\342O\\022\\374[\\361\\316\\207\\360#\\341sA\\251A\\251\\336\\2237\\210mRX\\023\\354\\263\\263\\031L\\221\\020\\273\\02686\\256\\322\\000\\312\\225\\3479\\037\\253?\\020|Y\\246\\374:\\370_\\255\\3744\\370uye\\245\\333i\\232DZe\\356\\247\\013\\210\\327JP\\230y\\011\\034\\011\\2379\\000r\\013\\023\\327\\232\\371\\\\\\3738\\251R\\212\\247(\\362\\266\\373\\353n\\227\\376\\256u\\341\\260\\036\\312^\\343\\274\\255\\362[_\\372\\365>F\\370u\\240j\\037\\035\\377\\000\\340\\261\\0367\\370\\363\\342Ti\\276\\032|3\\264\\232/\\014\\303#|\\222]#\\265\\274\\017\\201\\300S(\\271\\225O\\3752\\214\\364\\306w\\276-\\376\\320\\376\\037\\375\\236\\277\\340\\235>#\\370\\307\\341\\371-\\365_\\032x\\207_\\270\\320\\374\\012\\227O!\\363''\\336E\\325\\330\\317!!E\\227\\004\\014y\\233\\024\\343p\\257]\\375\\237\\357\\274\\031o\\360w\\305~\\013\\360|0\\303\\244i\\326\\315\\252j\\327\\267/\\345\\265\\363\\210\\366)rxHA\\013\\303\\036@''\\000\\032\\374''\\375\\263~/\\350\\177\\024\\277h\\177\\017x_\\300\\232\\203\\337\\374+\\370y\\243\\377\\000b\\370~P\\245#\\277\\271v\\363u\\013\\365V\\344y\\323\\222\\003\\177\\022E\\031\\3435\\305\\221\\341\\241\\230c\\243\\031G\\334\\246\\226\\2354\\332\\376m\\376\\244f\\265\\247\\206\\2414\\236\\263\\177\\205\\277\\257\\221\\314~\\321\\277\\2646\\203\\361\\263\\340\\357\\303\\373\\035*\\317_\\320uK\\033\\313\\211\\365\\2752\\342a-\\273;$b9\\026PA\\220\\217\\336\\340\\262\\206\\033\\217\\257?%\\352\\260%\\275\\334\\010\\230\\301\\200\\037\\324\\325\\243\\022\\276\\246\\321\\220\\244g\\003\\212\\317\\276VK\\244V$\\200\\230\\\\\\236\\203''\\212\\375aN\\362\\261\\361\\216:\\024\\250\\242\\212\\320\\200\\256\\317\\341\\316\\251.\\211\\373Bx\\023Z\\202\\017\\265M\\247\\370\\212\\312\\3528\\177\\347\\243Gp\\216\\027\\361#\\025\\306WC\\341)Z\\017\\212\\336\\031\\2357\\227\\217V\\266u\\332\\2719\\022\\251\\340w5\\025\\025\\340\\313\\247\\361#\\366\\023V\\361\\037\\301\\317\\217>\\014\\266\\262\\325mM\\207\\210a%\\367s\\015\\366\\234\\343\\251V\\353\\202q\\310\\312\\237z\\371\\214\\3703\\306k\\361\\373K\\360\\227\\203\\365\\313\\217\\024__],6+y\\0320!\\217\\374\\264?t\\257\\251\\305p\\372\\266\\273\\341k\\233\\351.\\032=\\272\\253e\\213\\252\\200\\001\\036\\235\\325\\253\\224\\261\\361G\\210\\264\\255j{\\337\\016k\\232\\276\\221;\\306c\\0270\\\\\\225\\227i\\352\\003u_\\300\\327\\306\\2545X\\337\\222_&}R\\305\\301Z\\353\\356g\\352~\\225\\361\\017\\301\\277\\260\\307\\303\\333\\3757U\\361\\227\\206\\274C\\361\\333Y\\214\\306\\272g\\207m>\\322\\372\\\\,\\274M4\\215\\2347\\242\\261U$\\202A\\003m|\\273q\\361\\267\\304\\177\\030<I''\\204\\254\\316\\257$\\322\\031/-m!\\200I\\011\\227\\005\\244\\272\\275\\223p\\363\\033''\\253\\025D\\316yl\\032\\370\\321\\0228\\356..f\\337<\\262I\\346O,\\322\\027y\\230\\365fc\\313\\036\\274\\223V\\207\\212\\265\\273=6m?E\\272\\223KK\\301\\266\\341mX\\253J\\200\\344\\007#\\252\\344\\003\\216\\231\\346\\271\\250\\344t\\325\\346\\3374\\336\\255\\275\\257\\344\\277\\257Q\\3175\\223\\226\\212\\313\\361\\267\\251\\364o\\210\\376/x\\207\\341\\367\\300_\\026\\374%\\360\\307\\214''\\325/<Ks\\273\\305\\372\\272F\\021\\244\\2112\\022\\322"\\031\\210C\\222X\\347\\236\\203\\326\\276*\\272\\267\\010~E\\340\\036Ev\\320\\351\\362$\\033\\234\\273\\273\\035\\316\\354z\\236\\346\\262\\357m1v\\340\\343\\036\\302\\275\\274\\004)\\341\\333\\345\\353\\273\\356\\3173\\025)V\\326_#\\315\\357A\\200\\231\\224\\014\\367\\346\\261.\\246\\363\\346F\\356\\023\\007\\3635\\350\\362hW\\232\\213\\030\\254l\\256\\357d\\301%-\\341g`\\007=\\024\\032\\363\\233\\261\\030\\271\\0026\\016\\273z\\217\\251\\257r\\233\\214\\235\\317&qi\\025h\\242\\212\\334\\310+WByc\\361\\276\\215$\\012\\317:\\337DcU\\352X8\\300\\374\\353*\\266\\2746\\252\\337\\021t\\005`\\031N\\245\\000 \\214\\202<\\305\\251\\227\\302\\312\\207\\304\\217\\253-<\\015\\256_i2j\\372\\357\\207\\244\\267\\267e/\\347\\024\\302\\250\\377\\000h\\203\\307\\352+\\224\\277\\232\\306\\326G\\216\\332(\\302!\\376\\003_\\\\\\376\\321\\214\\326?\\263f\\221\\015\\223\\0338\\236\\342\\024d\\200\\354VP\\234)\\003\\267\\265|S\\030\\037\\330\\223\\034\\014\\343\\374k\\346(\\311\\324Wg\\277Z*\\015$S\\021\\352Z\\335\\321\\216\\306#\\005\\2127\\357&a\\200\\307\\320g\\255v\\032v\\213ob7H\\376k\\237\\274\\3142I\\252:9#\\301\\020\\340\\221\\307o\\255t\\021\\377\\000\\254\\217\\353\\375+Y\\311\\265e\\2423\\204\\025\\323z\\213:\\257\\224b1\\250''\\241U\\306+\\012\\035\\036\\347T\\361\\014V\\226\\320\\231%\\221\\200\\013\\3658\\256\\221~iWw\\315\\362\\367\\346\\272\\037\\002\\200~0i\\352@*f@F8#x\\342\\271\\334\\371"\\344\\272\\035\\036\\317\\235\\244\\317\\325\\337\\330s\\366V\\324|;\\361oH\\361\\247\\210l\\355\\242h\\341Y\\355\\322q\\222Y[9\\012:\\214\\343\\004\\3675\\360_\\374\\025\\307\\302\\276\\014\\360\\267\\374\\024\\353\\303\\377\\000\\360\\206xoF\\360\\302\\352\\276\\001\\265\\3245\\2104\\333U\\201g\\275k\\375B7\\231\\325@\\036c$Q\\002q\\310Pk\\372E\\370p\\210\\276\\002\\321\\034"\\206\\020B\\240\\201\\310\\033W\\217\\245\\177<\\037\\360Y\\240\\007\\374\\025W\\302d\\000\\011\\370ibO\\271\\376\\320\\324\\253\\310\\341lej\\370\\247RO}\\032\\371]}\\337\\251\\317\\232\\323\\214}\\324\\266\\377\\0003\\362N\\212(\\257\\320\\317\\010\\377\\331');


--
-- TOC entry 1851 (class 0 OID 223332)
-- Dependencies: 1316
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1852 (class 0 OID 223334)
-- Dependencies: 1317
-- Data for Name: feedbackreputation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1853 (class 0 OID 223338)
-- Dependencies: 1318
-- Data for Name: friends; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1854 (class 0 OID 223340)
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
-- TOC entry 1855 (class 0 OID 223345)
-- Dependencies: 1320
-- Data for Name: gamecategory; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1856 (class 0 OID 223347)
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
-- TOC entry 1857 (class 0 OID 223352)
-- Dependencies: 1322
-- Data for Name: imnetwork; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (1, 'Aim');
INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (2, 'Yahoo');
INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (3, 'MSN');
INSERT INTO imnetwork (imnetworkid, imnetworkname) VALUES (4, 'Skype');


--
-- TOC entry 1858 (class 0 OID 223354)
-- Dependencies: 1323
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1859 (class 0 OID 223364)
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
-- TOC entry 1860 (class 0 OID 223369)
-- Dependencies: 1325
-- Data for Name: network; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO network (networkid, networkname) VALUES (1000003, 'ps2');
INSERT INTO network (networkid, networkname) VALUES (1000004, 'ps3');
INSERT INTO network (networkid, networkname) VALUES (1000002, 'xbox360');


--
-- TOC entry 1861 (class 0 OID 223371)
-- Dependencies: 1326
-- Data for Name: notificationqueue; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1862 (class 0 OID 223376)
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
-- TOC entry 1863 (class 0 OID 223378)
-- Dependencies: 1328
-- Data for Name: pictures; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1864 (class 0 OID 223383)
-- Dependencies: 1329
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1865 (class 0 OID 223399)
-- Dependencies: 1330
-- Data for Name: playeradmincomments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1866 (class 0 OID 223404)
-- Dependencies: 1331
-- Data for Name: playercomments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1867 (class 0 OID 223406)
-- Dependencies: 1332
-- Data for Name: playergame; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1868 (class 0 OID 223408)
-- Dependencies: 1333
-- Data for Name: playerim; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1869 (class 0 OID 223411)
-- Dependencies: 1334
-- Data for Name: playernetwork; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1870 (class 0 OID 223413)
-- Dependencies: 1335
-- Data for Name: sitecontent; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1871 (class 0 OID 223418)
-- Dependencies: 1336
-- Data for Name: sitecontentsection; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1872 (class 0 OID 223420)
-- Dependencies: 1337
-- Data for Name: skin; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1873 (class 0 OID 223422)
-- Dependencies: 1338
-- Data for Name: subnationalcode; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (319, 'true', '13', 'AU-NS', 'NS', 'New South Wales');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (320, 'true', '13', 'AU-QL', 'QL', 'Queensland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (321, 'true', '13', 'AU-SA', 'SA', 'South Australia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (322, 'true', '13', 'AU-TS', 'TS', 'Tasmania');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (323, 'true', '13', 'AU-VI', 'VI', 'Victoria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (324, 'true', '13', 'AU-WA', 'WA', 'Western Australia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (325, 'true', '13', 'AU-CT', 'CT', 'Australian Capital Territory');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (326, 'true', '13', 'AU-NT', 'NT', 'Northern Territory');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3934, 'true', '13', 'AU-', '(null)', 'Australia ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2839, 'true', '202', 'US-AL', 'AL', 'Alabama');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2840, 'true', '202', 'US-AK', 'AK', 'Alaska');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2841, 'false', '202', 'US-AZ', 'AZ', 'Arizona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2842, 'false', '202', 'US-AR', 'AR', 'Arkansas');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2843, 'true', '202', 'US-CA', 'CA', 'California');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2844, 'true', '202', 'US-CO', 'CO', 'Colorado');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2845, 'false', '202', 'US-CT', 'CT', 'Connecticut');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2846, 'false', '202', 'US-DE', 'DE', 'Delaware');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2847, 'false', '202', 'US-FL', 'FL', 'Florida');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2848, 'true', '202', 'US-GA', 'GA', 'Georgia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2849, 'true', '202', 'US-HI', 'HI', 'Hawaii');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2850, 'true', '202', 'US-ID', 'ID', 'Idaho');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2851, 'false', '202', 'US-IL', 'IL', 'Illinois');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2852, 'true', '202', 'US-IN', 'IN', 'Indiana');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2853, 'false', '202', 'US-IA', 'IA', 'Iowa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2854, 'true', '202', 'US-KS', 'KS', 'Kansas');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2855, 'true', '202', 'US-KY', 'KY', 'Kentucky');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2856, 'false', '202', 'US-LA', 'LA', 'Louisiana');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2857, 'true', '202', 'US-ME', 'ME', 'Maine');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2858, 'true', '202', 'US-MD', 'MD', 'Maryland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2859, 'true', '202', 'US-MA', 'MA', 'Massachusetts');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2860, 'true', '202', 'US-MI', 'MI', 'Michigan');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2861, 'true', '202', 'US-MN', 'MN', 'Minnesota');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2862, 'true', '202', 'US-MS', 'MS', 'Mississippi');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2863, 'true', '202', 'US-MO', 'MO', 'Missouri');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2864, 'true', '202', 'US-MT', 'MT', 'Montana');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2865, 'true', '202', 'US-NE', 'NE', 'Nebraska');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2866, 'true', '202', 'US-NV', 'NV', 'Nevada');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2867, 'true', '202', 'US-NH', 'NH', 'New Hampshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2868, 'true', '202', 'US-NJ', 'NJ', 'New Jersey');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2869, 'true', '202', 'US-NM', 'NM', 'New Mexico');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2870, 'true', '202', 'US-NY', 'NY', 'New York');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2871, 'true', '202', 'US-NC', 'NC', 'North Carolina');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2872, 'true', '202', 'US-ND', 'ND', 'North Dakota');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2873, 'true', '202', 'US-OH', 'OH', 'Ohio');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2874, 'true', '202', 'US-OK', 'OK', 'Oklahoma');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2875, 'true', '202', 'US-OR', 'OR', 'Oregon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2876, 'true', '202', 'US-PA', 'PA', 'Pennsylvania');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2877, 'true', '202', 'US-RI', 'RI', 'Rhode Island');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2878, 'false', '202', 'US-SC', 'SC', 'South Carolina');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2879, 'false', '202', 'US-SD', 'SD', 'South Dakota');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2880, 'false', '202', 'US-TN', 'TN', 'Tennessee');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2881, 'true', '202', 'US-TX', 'TX', 'Texas');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2882, 'true', '202', 'US-UT', 'UT', 'Utah');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2883, 'false', '202', 'US-VT', 'VT', 'Vermont');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2884, 'true', '202', 'US-VA', 'VA', 'Virginia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2885, 'true', '202', 'US-WA', 'WA', 'Washington');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2886, 'true', '202', 'US-WV', 'WV', 'West Virginia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2887, 'true', '202', 'US-WI', 'WI', 'Wisconsin');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2888, 'true', '202', 'US-WY', 'WY', 'Wyoming');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2889, 'true', '202', 'US-DC', 'DC', 'District of Columbia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (310, 'true', '14', 'AT-1', '1', 'Burgenland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (311, 'true', '14', 'AT-2', '2', 'KÃ¤rnten');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (312, 'true', '14', 'AT-3', '3', 'NiederÃ¶sterreich');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (313, 'true', '14', 'AT-4', '4', 'OberÃ¶sterreich');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (314, 'true', '14', 'AT-5', '5', 'Salzburg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (315, 'true', '14', 'AT-6', '6', 'Steiermark');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (316, 'true', '14', 'AT-7', '7', 'Tirol');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (317, 'true', '14', 'AT-8', '8', 'Vorarlberg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (318, 'true', '14', 'AT-9', '9', 'Wien');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3935, 'true', '14', 'AT-', '(null)', 'Austria ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1601, 'true', '21', 'BE-VAN', 'VAN', 'Antwerpen (nl)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1602, 'true', '21', 'BE-WBR', 'WBR', 'Brabant Wallon (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1603, 'true', '21', 'BE-WHT', 'WHT', 'Hainaut (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1604, 'true', '21', 'BE-WLG', 'WLG', 'LiÃ¨ge (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1605, 'true', '21', 'BE-VLI', 'VLI', 'Limburg (nl)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1606, 'true', '21', 'BE-WLX', 'WLX', 'Luxembourg (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1607, 'true', '21', 'BE-WNA', 'WNA', 'Namur (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1608, 'true', '21', 'BE-VOV', 'VOV', 'Oost-Vlaanderen (nl)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1609, 'true', '21', 'BE-VBR', 'VBR', 'Vlaams Brabant (nl)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1610, 'true', '21', 'BE-VWV', 'VWV', 'West-Vlaanderen (nl)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3942, 'true', '21', 'BE-', '(null)', 'Belgium ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1899, 'true', '38', 'CA-MB', 'MB', 'Manitoba');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1900, 'true', '38', 'CA-NB', 'NB', 'New Brunswick');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1902, 'true', '38', 'CA-NS', 'NS', 'Nova Scotia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1903, 'true', '38', 'CA-ON', 'ON', 'Ontario');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1904, 'true', '38', 'CA-PE', 'PE', 'Prince Edward Island');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1905, 'true', '38', 'CA-QC', 'QC', 'Quebec');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1906, 'true', '38', 'CA-SK', 'SK', 'Saskatchewan');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1907, 'true', '38', 'CA-NT', 'NT', 'Northwest Territories');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1908, 'true', '38', 'CA-NU', 'NU', 'Nunavut');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1909, 'true', '38', 'CA-YT', 'YT', 'Yukon Territory');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1901, 'true', '38', 'CA-NL', 'NL', 'Newfoundland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3957, 'true', '38', 'CA-', '(null)', 'Canada ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2161, 'true', '57', 'DK-147', '147', 'Frederiksberg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2162, 'true', '57', 'DK-101', '101', 'KÃ¸benhavn');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2163, 'true', '57', 'DK-040', '040', 'Bornholm');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2164, 'true', '57', 'DK-020', '020', 'Frederiksborg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2165, 'true', '57', 'DK-042', '042', 'Fyn');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2166, 'true', '57', 'DK-015', '015', 'KÃ¸benhavn');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2167, 'true', '57', 'DK-080', '080', 'Nordjylland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2168, 'true', '57', 'DK-055', '055', 'Ribe');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2169, 'true', '57', 'DK-065', '065', 'RingkÃ¸bing');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2170, 'true', '57', 'DK-025', '025', 'Roskilde');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2171, 'true', '57', 'DK-035', '035', 'StorstrÃ¸m');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2172, 'true', '57', 'DK-050', '050', 'SÃ¸nderjylland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2173, 'true', '57', 'DK-060', '060', 'Vejle');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2174, 'true', '57', 'DK-030', '030', 'VestsjÃ¦lland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2175, 'true', '57', 'DK-076', '076', 'Viborg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2176, 'true', '57', 'DK-070', '070', 'Ã…rhus');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3976, 'true', '57', 'DK-', '(null)', 'Denmark ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2385, 'true', '72', 'FI-AL', 'AL', 'Ahvenanmaan lÃ¤Ã¤ni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2386, 'true', '72', 'FI-ES', 'ES', 'EtelÃ¤-Suomen lÃ¤Ã¤ni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2387, 'true', '72', 'FI-IS', 'IS', 'ItÃ¤-Suomen lÃ¤Ã¤ni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2388, 'true', '72', 'FI-LL', 'LL', 'Lapin lÃ¤Ã¤ni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2389, 'true', '72', 'FI-LS', 'LS', 'LÃ¤nsi-Suomen lÃ¤Ã¤ni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2390, 'true', '72', 'FI-OL', 'OL', 'Oulun lÃ¤Ã¤ni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3991, 'true', '72', 'FI-', '(null)', 'Finland ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (927, 'true', '73', 'FR-70', '70', 'Haute-SaÃ´ne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (928, 'true', '73', 'FR-74', '74', 'Haute-Savoie');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (929, 'true', '73', 'FR-65', '65', 'Hautes-PyrÃ©nÃ©es');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (930, 'true', '73', 'FR-87', '87', 'Haute-Vienne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (931, 'true', '73', 'FR-68', '68', 'Haut-Rhin');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (932, 'true', '73', 'FR-92', '92', 'Hauts-de-Seine');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (933, 'true', '73', 'FR-34', '34', 'HÃ©rault');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (934, 'true', '73', 'FR-35', '35', 'Ille-et-Vilaine');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (935, 'true', '73', 'FR-36', '36', 'Indre');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (936, 'true', '73', 'FR-37', '37', 'Indre-et-Loire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (937, 'true', '73', 'FR-38', '38', 'IsÃ¨re');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (938, 'true', '73', 'FR-39', '39', 'Jura');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (939, 'true', '73', 'FR-40', '40', 'Landes');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (940, 'true', '73', 'FR-41', '41', 'Loir-et-Cher');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (941, 'true', '73', 'FR-42', '42', 'Loire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (942, 'true', '73', 'FR-44', '44', 'Loire-Atlantique');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (943, 'true', '73', 'FR-45', '45', 'Loiret');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (944, 'true', '73', 'FR-46', '46', 'Lot');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (945, 'true', '73', 'FR-47', '47', 'Lot-et-Garonne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (946, 'true', '73', 'FR-48', '48', 'LozÃ¨re');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (947, 'true', '73', 'FR-49', '49', 'Maine-et-Loire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (948, 'true', '73', 'FR-50', '50', 'Manche');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (949, 'true', '73', 'FR-51', '51', 'Marne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (950, 'true', '73', 'FR-53', '53', 'Mayenne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (951, 'true', '73', 'FR-54', '54', 'Meurthe-et-Moselle');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (952, 'true', '73', 'FR-55', '55', 'Meuse');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (953, 'true', '73', 'FR-56', '56', 'Morbihan');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (954, 'true', '73', 'FR-57', '57', 'Moselle');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (955, 'true', '73', 'FR-58', '58', 'NiÃ¨vre');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (956, 'true', '73', 'FR-59', '59', 'Nord');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (957, 'true', '73', 'FR-60', '60', 'Oise');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (958, 'true', '73', 'FR-61', '61', 'Orne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (959, 'true', '73', 'FR-75', '75', 'Paris');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (960, 'true', '73', 'FR-62', '62', 'Pas-de-Calais');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (961, 'true', '73', 'FR-63', '63', 'Puy-de-DÃ´me');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (962, 'true', '73', 'FR-64', '64', 'PyrÃ©nÃ©es-Atlantiques');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (963, 'true', '73', 'FR-66', '66', 'PyrÃ©nÃ©es-Orientales');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (964, 'true', '73', 'FR-69', '69', 'RhÃ´ne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (965, 'true', '73', 'FR-71', '71', 'SaÃ´ne-et-Loire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (966, 'true', '73', 'FR-72', '72', 'Sarthe');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (967, 'true', '73', 'FR-73', '73', 'Savoie');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (968, 'true', '73', 'FR-77', '77', 'Seine-et-Marne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (969, 'true', '73', 'FR-76', '76', 'Seine-Maritime');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (970, 'true', '73', 'FR-93', '93', 'Seine-Saint-Denis');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (971, 'true', '73', 'FR-80', '80', 'Somme');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (972, 'true', '73', 'FR-81', '81', 'Tarn');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (973, 'true', '73', 'FR-82', '82', 'Tarn-et-Garonne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (974, 'true', '73', 'FR-90', '90', 'Territoire de Belfort');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (975, 'true', '73', 'FR-94', '94', 'Val-de-Marne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (976, 'true', '73', 'FR-95', '95', 'Val-dOise');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (977, 'true', '73', 'FR-83', '83', 'Var');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (978, 'true', '73', 'FR-84', '84', 'Vaucluse');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (979, 'true', '73', 'FR-85', '85', 'VendÃ©e');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (980, 'true', '73', 'FR-86', '86', 'Vienne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (981, 'true', '73', 'FR-88', '88', 'Vosges');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (982, 'true', '73', 'FR-89', '89', 'Yonne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (983, 'true', '73', 'FR-78', '78', 'Yvelines');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2400, 'true', '73', 'FR-01', '01', 'Ain');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2401, 'true', '73', 'FR-02', '02', 'Aisne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2402, 'true', '73', 'FR-03', '03', 'Allier');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2403, 'true', '73', 'FR-04', '04', 'Alpes-de-Haute-Provence');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2404, 'true', '73', 'FR-06', '06', 'Alpes-Maritimes');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2405, 'true', '73', 'FR-07', '07', 'ArdÃ¨che');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2406, 'true', '73', 'FR-08', '08', 'Ardennes');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2407, 'true', '73', 'FR-09', '09', 'AriÃ¨ge');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2408, 'true', '73', 'FR-10', '10', 'Aube');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2409, 'true', '73', 'FR-11', '11', 'Aude');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2410, 'true', '73', 'FR-12', '12', 'Aveyron');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2411, 'true', '73', 'FR-67', '67', 'Bas-Rhin');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2412, 'true', '73', 'FR-13', '13', 'Bouches-du-RhÃ´ne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2413, 'true', '73', 'FR-14', '14', 'Calvados');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2414, 'true', '73', 'FR-15', '15', 'Cantal');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2415, 'true', '73', 'FR-16', '16', 'Charente');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2416, 'true', '73', 'FR-17', '17', 'Charente-Maritime');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2417, 'true', '73', 'FR-18', '18', 'Cher');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2418, 'true', '73', 'FR-19', '19', 'CorrÃ¨ze');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2419, 'true', '73', 'FR-2A', '2A', 'Corse-du-Sud');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2420, 'true', '73', 'FR-21', '21', 'CÃ´te-dOr');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2421, 'true', '73', 'FR-22', '22', 'CÃ´tes-dArmor');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2422, 'true', '73', 'FR-23', '23', 'Creuse');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2423, 'true', '73', 'FR-79', '79', 'Deux-SÃ¨vres');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2424, 'true', '73', 'FR-24', '24', 'Dordogne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2425, 'true', '73', 'FR-25', '25', 'Doubs');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2426, 'true', '73', 'FR-26', '26', 'DrÃ´me');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2427, 'true', '73', 'FR-91', '91', 'Essonne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2428, 'true', '73', 'FR-27', '27', 'Eure');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2429, 'true', '73', 'FR-28', '28', 'Eure-et-Loir');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2430, 'true', '73', 'FR-29', '29', 'FinistÃ¨re');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2431, 'true', '73', 'FR-30', '30', 'Gard');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2432, 'true', '73', 'FR-32', '32', 'Gers');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2433, 'true', '73', 'FR-33', '33', 'Gironde');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2434, 'true', '73', 'FR-2B', '2B', 'Haute-Corse');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2435, 'true', '73', 'FR-31', '31', 'Haute-Garonne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2436, 'true', '73', 'FR-43', '43', 'Haute-Loire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2437, 'true', '73', 'FR-52', '52', 'Haute-Marne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2438, 'true', '73', 'FR-05', '05', 'Hautes-Alpes');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3992, 'true', '73', 'FR-', '(null)', 'France ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2140, 'true', '80', 'DE-BW', 'BW', 'Baden-WÃ¼rttemberg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2141, 'true', '80', 'DE-BY', 'BY', 'Bayern');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2142, 'true', '80', 'DE-BE', 'BE', 'Berlin');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2143, 'true', '80', 'DE-BB', 'BB', 'Brandenburg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2144, 'true', '80', 'DE-HB', 'HB', 'Bremen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2145, 'true', '80', 'DE-HH', 'HH', 'Hamburg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2146, 'true', '80', 'DE-HE', 'HE', 'Hessen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2147, 'true', '80', 'DE-MV', 'MV', 'Mecklenburg-Vorpommern');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2148, 'true', '80', 'DE-NI', 'NI', 'Niedersachsen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2149, 'true', '80', 'DE-NW', 'NW', 'Nordrhein-Westfalen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2150, 'true', '80', 'DE-RP', 'RP', 'Rheinland-Pfalz');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2151, 'true', '80', 'DE-SL', 'SL', 'Saarland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2152, 'true', '80', 'DE-SN', 'SN', 'Sachsen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2153, 'true', '80', 'DE-ST', 'ST', 'Sachsen-Anhalt');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2154, 'true', '80', 'DE-SH', 'SH', 'Schleswig-Holstein');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2155, 'true', '80', 'DE-TH', 'TH', 'ThÃ¼ringen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3999, 'true', '80', 'DE-', '(null)', 'Germany ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4010, 'true', '91', 'HK-', '(null)', 'Hong Kong ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (511, 'true', '97', 'IE-CW', 'CW', 'Carlow');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (512, 'true', '97', 'IE-CN', 'CN', 'Cavan');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (513, 'true', '97', 'IE-CE', 'CE', 'Clare');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (514, 'true', '97', 'IE-C', 'C', 'Cork');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (515, 'true', '97', 'IE-DL', 'DL', 'Donegal');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (516, 'true', '97', 'IE-D', 'D', 'Dublin');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (517, 'true', '97', 'IE-G', 'G', 'Galway');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (518, 'true', '97', 'IE-KY', 'KY', 'Kerry');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (519, 'true', '97', 'IE-KE', 'KE', 'Kildare');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (520, 'true', '97', 'IE-KK', 'KK', 'Kilkenny');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (521, 'true', '97', 'IE-LS', 'LS', 'Laois');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (522, 'true', '97', 'IE-LM', 'LM', 'Leitrim');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (523, 'true', '97', 'IE-LK', 'LK', 'Limerick');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (524, 'true', '97', 'IE-LD', 'LD', 'Longford');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (525, 'true', '97', 'IE-LH', 'LH', 'Louth');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (526, 'true', '97', 'IE-MO', 'MO', 'Mayo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (527, 'true', '97', 'IE-MH', 'MH', 'Meath');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (528, 'true', '97', 'IE-MN', 'MN', 'Monaghan');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (529, 'true', '97', 'IE-OY', 'OY', 'Offaly');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (530, 'true', '97', 'IE-RN', 'RN', 'Roscommon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (531, 'true', '97', 'IE-SO', 'SO', 'Sligo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (532, 'true', '97', 'IE-TA', 'TA', 'Tipperary');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (533, 'true', '97', 'IE-WD', 'WD', 'Waterford');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (534, 'true', '97', 'IE-WH', 'WH', 'Westmeath');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (535, 'true', '97', 'IE-WX', 'WX', 'Wexford');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (536, 'true', '97', 'IE-WW', 'WW', 'Wicklow');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4016, 'true', '97', 'IE-', '(null)', 'Ireland ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (694, 'true', '99', 'IT-AG', 'AG', 'Agrigento');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (695, 'true', '99', 'IT-AL', 'AL', 'Alessandria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (696, 'true', '99', 'IT-AN', 'AN', 'Ancona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (697, 'true', '99', 'IT-AO', 'AO', 'Aosta');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (698, 'true', '99', 'IT-AR', 'AR', 'Arezzo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (699, 'true', '99', 'IT-AP', 'AP', 'Ascoli Piceno');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (700, 'true', '99', 'IT-AT', 'AT', 'Asti');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (701, 'true', '99', 'IT-AV', 'AV', 'Avellino');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (702, 'true', '99', 'IT-BA', 'BA', 'Bari');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (703, 'true', '99', 'IT-BL', 'BL', 'Belluno');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (704, 'true', '99', 'IT-BN', 'BN', 'Benevento');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (705, 'true', '99', 'IT-BG', 'BG', 'Bergamo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (706, 'true', '99', 'IT-BI', 'BI', 'Biella');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (707, 'true', '99', 'IT-BO', 'BO', 'Bologna');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (708, 'true', '99', 'IT-BZ', 'BZ', 'Bolzano');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (709, 'true', '99', 'IT-BS', 'BS', 'Brescia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (710, 'true', '99', 'IT-BR', 'BR', 'Brindisi');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (711, 'true', '99', 'IT-CA', 'CA', 'Cagliari');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (712, 'true', '99', 'IT-CL', 'CL', 'Caltanissetta');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (713, 'true', '99', 'IT-CB', 'CB', 'Campobasso');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (714, 'true', '99', 'IT-CE', 'CE', 'Caserta');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (715, 'true', '99', 'IT-CT', 'CT', 'Catania');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (716, 'true', '99', 'IT-CZ', 'CZ', 'Catanzaro');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (717, 'true', '99', 'IT-CH', 'CH', 'Chieti');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (718, 'true', '99', 'IT-CO', 'CO', 'Como');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (719, 'true', '99', 'IT-CS', 'CS', 'Cosenza');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (720, 'true', '99', 'IT-CR', 'CR', 'Cremona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (721, 'true', '99', 'IT-KR', 'KR', 'Crotone');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (722, 'true', '99', 'IT-CN', 'CN', 'Cuneo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (723, 'true', '99', 'IT-EN', 'EN', 'Enna');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (724, 'true', '99', 'IT-FE', 'FE', 'Ferrara');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (725, 'true', '99', 'IT-FI', 'FI', 'Firenze');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (726, 'true', '99', 'IT-FG', 'FG', 'Foggia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (727, 'true', '99', 'IT-FO', 'FO', 'ForlÃ¬');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (728, 'true', '99', 'IT-FR', 'FR', 'Frosinone ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (729, 'true', '99', 'IT-GE', 'GE', 'Genova');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (730, 'true', '99', 'IT-GO', 'GO', 'Gorizia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (731, 'true', '99', 'IT-GR', 'GR', 'Grosseto');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (732, 'true', '99', 'IT-IM', 'IM', 'Imperia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (733, 'true', '99', 'IT-IS', 'IS', 'Isernia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (734, 'true', '99', 'IT-AQ', 'AQ', 'LAquila');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (735, 'true', '99', 'IT-SP', 'SP', 'La Spezia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (736, 'true', '99', 'IT-LT', 'LT', 'Latina');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (737, 'true', '99', 'IT-LE', 'LE', 'Lecce');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (738, 'true', '99', 'IT-LC', 'LC', 'Lecco');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (739, 'true', '99', 'IT-LI', 'LI', 'Livorno');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (740, 'true', '99', 'IT-LO', 'LO', 'Lodi');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (741, 'true', '99', 'IT-LU', 'LU', 'Lucca');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (742, 'true', '99', 'IT-MC', 'MC', 'Macerata');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (743, 'true', '99', 'IT-MN', 'MN', 'Mantova');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (744, 'true', '99', 'IT-MS', 'MS', 'Massa-Carrara');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (745, 'true', '99', 'IT-MT', 'MT', 'Matera');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (746, 'true', '99', 'IT-ME', 'ME', 'Messina');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (747, 'true', '99', 'IT-MI', 'MI', 'Milano');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (748, 'true', '99', 'IT-MO', 'MO', 'Modena');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (749, 'true', '99', 'IT-NA', 'NA', 'Napoli');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (750, 'true', '99', 'IT-NO', 'NO', 'Novara');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (751, 'true', '99', 'IT-NU', 'NU', 'Nuoro');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (752, 'true', '99', 'IT-OR', 'OR', 'Oristano');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (753, 'true', '99', 'IT-PD', 'PD', 'Padova');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (754, 'true', '99', 'IT-PA', 'PA', 'Palermo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (755, 'true', '99', 'IT-PR', 'PR', 'Parma');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (756, 'true', '99', 'IT-PV', 'PV', 'Pavia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (757, 'true', '99', 'IT-PG', 'PG', 'Perugia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (758, 'true', '99', 'IT-PS', 'PS', 'Pesaro e Urbino');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (759, 'true', '99', 'IT-PE', 'PE', 'Pescara');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (760, 'true', '99', 'IT-PC', 'PC', 'Piacenza');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (761, 'true', '99', 'IT-PI', 'PI', 'Pisa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (762, 'true', '99', 'IT-PT', 'PT', 'Pistoia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (763, 'true', '99', 'IT-PN', 'PN', 'Pordenone');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (764, 'true', '99', 'IT-PZ', 'PZ', 'Potenza');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (765, 'true', '99', 'IT-PO', 'PO', 'Prato');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (766, 'true', '99', 'IT-RG', 'RG', 'Ragusa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (767, 'true', '99', 'IT-RA', 'RA', 'Ravenna');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (768, 'true', '99', 'IT-RC', 'RC', 'Reggio Calabria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (769, 'true', '99', 'IT-RE', 'RE', 'Reggio Emilia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (770, 'true', '99', 'IT-RI', 'RI', 'Rieti');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (771, 'true', '99', 'IT-RN', 'RN', 'Rimini');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (772, 'true', '99', 'IT-RM', 'RM', 'Roma');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (773, 'true', '99', 'IT-RO', 'RO', 'Rovigo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (774, 'true', '99', 'IT-SA', 'SA', 'Salerno');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (775, 'true', '99', 'IT-SS', 'SS', 'Sassari');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (776, 'true', '99', 'IT-SV', 'SV', 'Savona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (777, 'true', '99', 'IT-SI', 'SI', 'Siena');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (778, 'true', '99', 'IT-SR', 'SR', 'Siracusa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (779, 'true', '99', 'IT-SO', 'SO', 'Sondrio');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (780, 'true', '99', 'IT-TA', 'TA', 'Taranto');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (781, 'true', '99', 'IT-TE', 'TE', 'Teramo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (782, 'true', '99', 'IT-TR', 'TR', 'Terni');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (783, 'true', '99', 'IT-TO', 'TO', 'Torino');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (784, 'true', '99', 'IT-TP', 'TP', 'Trapani');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (785, 'true', '99', 'IT-TN', 'TN', 'Trento');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (786, 'true', '99', 'IT-TV', 'TV', 'Treviso');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (787, 'true', '99', 'IT-TS', 'TS', 'Trieste');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (788, 'true', '99', 'IT-UD', 'UD', 'Udine');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (789, 'true', '99', 'IT-VA', 'VA', 'Varese');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (790, 'true', '99', 'IT-VE', 'VE', 'Venezia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (791, 'true', '99', 'IT-VB', 'VB', 'Verbano-Cusio-Ossola');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (792, 'true', '99', 'IT-VC', 'VC', 'Vercelli');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (793, 'true', '99', 'IT-VR', 'VR', 'Verona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (794, 'true', '99', 'IT-VV', 'VV', 'Vibo Valentia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (795, 'true', '99', 'IT-VI', 'VI', 'Vicenza');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (796, 'true', '99', 'IT-VT', 'VT', 'Viterbo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4018, 'true', '99', 'IT-', '(null)', 'Italy ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (823, 'true', '100', 'JP-23', '23', 'Aiti [Aichi]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (824, 'true', '100', 'JP-05', '05', 'Akita');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (825, 'true', '100', 'JP-02', '02', 'Aomori');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (826, 'true', '100', 'JP-38', '38', 'Ehime');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (827, 'true', '100', 'JP-21', '21', 'Gihu [Gifu]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (828, 'true', '100', 'JP-10', '10', 'Gunma');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (829, 'true', '100', 'JP-34', '34', 'Hirosima [Hiroshima]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (830, 'true', '100', 'JP-01', '01', 'HokkaidÃ´ [Hokkaido]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (831, 'true', '100', 'JP-18', '18', 'Hukui [Fukui]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (832, 'true', '100', 'JP-40', '40', 'Hukuoka [Fukuoka]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (833, 'true', '100', 'JP-07', '07', 'Hukusima [Fukushima]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (834, 'true', '100', 'JP-28', '28', 'HyÃ´go [Hyogo]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (835, 'true', '100', 'JP-08', '08', 'Ibaraki');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (836, 'true', '100', 'JP-17', '17', 'Isikawa [Ishikawa]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (837, 'true', '100', 'JP-03', '03', 'Iwate');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (838, 'true', '100', 'JP-37', '37', 'Kagawa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (839, 'true', '100', 'JP-46', '46', 'Kagosima [Kagoshima]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (840, 'true', '100', 'JP-14', '14', 'Kanagawa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (841, 'true', '100', 'JP-39', '39', 'KÃ´ti [Kochi]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (842, 'true', '100', 'JP-43', '43', 'Kumamoto');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (843, 'true', '100', 'JP-26', '26', 'KyÃ´to [Kyoto]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (844, 'true', '100', 'JP-24', '24', 'Mie');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (845, 'true', '100', 'JP-04', '04', 'Miyagi');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (846, 'true', '100', 'JP-45', '45', 'Miyazaki');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (847, 'true', '100', 'JP-20', '20', 'Nagano');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (848, 'true', '100', 'JP-42', '42', 'Nagasaki');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (849, 'true', '100', 'JP-29', '29', 'Nara');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (850, 'true', '100', 'JP-15', '15', 'Niigata');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (851, 'true', '100', 'JP-44', '44', 'Ã”ita [Oita]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (852, 'true', '100', 'JP-33', '33', 'Okayama');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (853, 'true', '100', 'JP-47', '47', 'Okinawa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (854, 'true', '100', 'JP-27', '27', 'Ã”saka [Osaka]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (855, 'true', '100', 'JP-41', '41', 'Saga');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (856, 'true', '100', 'JP-11', '11', 'Saitama');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (857, 'true', '100', 'JP-25', '25', 'Siga [Shiga]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (858, 'true', '100', 'JP-32', '32', 'Simane [Shimane]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (859, 'true', '100', 'JP-22', '22', 'Sizuoka [Shizuoka]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (860, 'true', '100', 'JP-12', '12', 'Tiba [Chiba]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (861, 'true', '100', 'JP-09', '09', 'Totigi [Tochigi]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (862, 'true', '100', 'JP-36', '36', 'Tokusima [Tokushima]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (863, 'true', '100', 'JP-13', '13', 'TÃ´kyÃ´ [Tokyo]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (864, 'true', '100', 'JP-31', '31', 'Tottori');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (865, 'true', '100', 'JP-16', '16', 'Toyama');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (866, 'true', '100', 'JP-30', '30', 'Wakayama');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (867, 'true', '100', 'JP-06', '06', 'Yamagata');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (868, 'true', '100', 'JP-35', '35', 'Yamaguti [Yamaguchi]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (869, 'true', '100', 'JP-19', '19', 'Yamanasi [Yamanashi]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4019, 'true', '100', 'JP-', '(null)', 'Japan ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (174, 'true', '114', 'LU-D', 'D', 'Diekirch');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (175, 'true', '114', 'LU-G', 'G', 'Grevenmacher');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (176, 'true', '114', 'LU-L', 'L', 'Luxembourg (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4033, 'true', '114', 'LU-', '(null)', 'Luxembourg ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3552, 'true', '135', 'NL-DR', 'DR', 'Drenthe');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3553, 'true', '135', 'NL-FL', 'FL', 'Flevoland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3554, 'true', '135', 'NL-FR', 'FR', 'Friesland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3555, 'true', '135', 'NL-GE', 'GE', 'Gelderland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3556, 'true', '135', 'NL-GR', 'GR', 'Groningen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3557, 'true', '135', 'NL-LI', 'LI', 'Limburg');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3558, 'true', '135', 'NL-NB', 'NB', 'Noord-Brabant');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3559, 'true', '135', 'NL-NH', 'NH', 'Noord-Holland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3560, 'true', '135', 'NL-OV', 'OV', 'Overijssel');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3561, 'true', '135', 'NL-UT', 'UT', 'Utrecht');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3562, 'true', '135', 'NL-ZE', 'ZE', 'Zeeland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3563, 'true', '135', 'NL-ZH', 'ZH', 'Zuid-Holland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4054, 'true', '135', 'NL-', '(null)', 'Netherlands ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3599, 'true', '137', 'NZ-AUK', 'AUK', 'Auckland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3600, 'true', '137', 'NZ-BOP', 'BOP', 'Bay of Plenty');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3601, 'true', '137', 'NZ-CAN', 'CAN', 'Canterbury');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3602, 'true', '137', 'NZ-GIS', 'GIS', 'Gisborne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3603, 'true', '137', 'NZ-HKB', 'HKB', 'Hawkess Bay');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3604, 'true', '137', 'NZ-MWT', 'MWT', 'Manawatu-Wanganui');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3605, 'true', '137', 'NZ-MBH', 'MBH', 'Marlborough');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3606, 'true', '137', 'NZ-NSN', 'NSN', 'Nelson');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3607, 'true', '137', 'NZ-NTL', 'NTL', 'Northland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3608, 'true', '137', 'NZ-OTA', 'OTA', 'Otago');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3609, 'true', '137', 'NZ-STL', 'STL', 'Southland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3610, 'true', '137', 'NZ-TKI', 'TKI', 'Taranaki');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3611, 'true', '137', 'NZ-TAS', 'TAS', 'Tasman');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3612, 'true', '137', 'NZ-WKO', 'WKO', 'Waikato');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3613, 'true', '137', 'NZ-WGN', 'WGN', 'Wellington');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3614, 'true', '137', 'NZ-WTC', 'WTC', 'West Coast');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4056, 'true', '137', 'NZ-', '(null)', 'New Zealand ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3564, 'true', '144', 'NO-02', '02', 'Akershus');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3565, 'true', '144', 'NO-09', '09', 'Aust-Agder');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3566, 'true', '144', 'NO-06', '06', 'Buskerud');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3567, 'true', '144', 'NO-20', '20', 'Finnmark');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3568, 'true', '144', 'NO-04', '04', 'Hedmark');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3569, 'true', '144', 'NO-12', '12', 'Hordaland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3570, 'true', '144', 'NO-15', '15', 'MÃ¸re og Romsdal');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3571, 'true', '144', 'NO-18', '18', 'Nordland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3572, 'true', '144', 'NO-17', '17', 'Nord-TrÃ¸ndelag');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3573, 'true', '144', 'NO-05', '05', 'Oppland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3574, 'true', '144', 'NO-03', '03', 'Oslo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3575, 'true', '144', 'NO-11', '11', 'Rogaland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3576, 'true', '144', 'NO-14', '14', 'Sogn og Fjordane');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3577, 'true', '144', 'NO-16', '16', 'SÃ¸r-TrÃ¸ndelag');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3578, 'true', '144', 'NO-08', '08', 'Telemark');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3579, 'true', '144', 'NO-19', '19', 'Troms');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3580, 'true', '144', 'NO-10', '10', 'Vest-Agder');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3581, 'true', '144', 'NO-07', '07', 'Vestfold');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3582, 'true', '144', 'NO-01', '01', 'Ã˜stfold');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3583, 'true', '144', 'NO-22', '22', 'Jan Mayen (Arctic Region) (See also country code SJ)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3584, 'true', '144', 'NO-21', '21', 'Svalbard (Arctic Region) (See also country code SJ)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4061, 'true', '144', 'NO-', '(null)', 'Norway ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2488, 'true', '153', 'PT-01', '01', 'Aveiro');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2489, 'true', '153', 'PT-02', '02', 'Beja');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2490, 'true', '153', 'PT-03', '03', 'Braga');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2491, 'true', '153', 'PT-04', '04', 'BraganÃ§a');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2492, 'true', '153', 'PT-05', '05', 'Castelo Branco');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2493, 'true', '153', 'PT-06', '06', 'Coimbra');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2494, 'true', '153', 'PT-07', '07', 'Ã‰vora');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2495, 'true', '153', 'PT-08', '08', 'Faro');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2496, 'true', '153', 'PT-09', '09', 'Guarda');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2497, 'true', '153', 'PT-10', '10', 'Leiria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2498, 'true', '153', 'PT-11', '11', 'Lisboa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2499, 'true', '153', 'PT-12', '12', 'Portalegre');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2500, 'true', '153', 'PT-13', '13', 'Porto');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2501, 'true', '153', 'PT-14', '14', 'SantarÃ©m');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2502, 'true', '153', 'PT-15', '15', 'SetÃºbal');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2503, 'true', '153', 'PT-16', '16', 'Viana do Castelo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2504, 'true', '153', 'PT-17', '17', 'Vila Real');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2505, 'true', '153', 'PT-18', '18', 'Viseu');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2506, 'true', '153', 'PT-20', '20', 'RegiÃ£o AutÃ³noma dos AÃ§ores');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2507, 'true', '153', 'PT-30', '30', 'RegiÃ£o AutÃ³noma da Madeira');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4070, 'true', '153', 'PT-', '(null)', 'Portugal ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2323, 'true', '176', 'ES-C', 'C', 'A CoruÃ±a');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2324, 'true', '176', 'ES-VI', 'VI', 'Ãlava');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2325, 'true', '176', 'ES-AB', 'AB', 'Albacete');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2326, 'true', '176', 'ES-A', 'A', 'Alicante');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2327, 'true', '176', 'ES-AL', 'AL', 'AlmerÃ­a');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2328, 'true', '176', 'ES-O', 'O', 'Asturias');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2329, 'true', '176', 'ES-AV', 'AV', 'Ãvila');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2330, 'true', '176', 'ES-BA', 'BA', 'Badajoz');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2331, 'true', '176', 'ES-PM', 'PM', 'Baleares');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2332, 'true', '176', 'ES-B', 'B', 'Barcelona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2333, 'true', '176', 'ES-BU', 'BU', 'Burgos');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2334, 'true', '176', 'ES-CC', 'CC', 'CÃ¡ceres');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2335, 'true', '176', 'ES-CA', 'CA', 'CÃ¡diz');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2336, 'true', '176', 'ES-S', 'S', 'Cantabria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2337, 'true', '176', 'ES-CS', 'CS', 'CastellÃ³n');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2338, 'true', '176', 'ES-CR', 'CR', 'Ciudad Real');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2339, 'true', '176', 'ES-CO', 'CO', 'CÃ³rdoba');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2340, 'true', '176', 'ES-CU', 'CU', 'Cuenca');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2341, 'true', '176', 'ES-GI', 'GI', 'Girona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2342, 'true', '176', 'ES-GR', 'GR', 'Granada');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2343, 'true', '176', 'ES-GU', 'GU', 'Guadalajara');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2344, 'true', '176', 'ES-SS', 'SS', 'GuipÃºzcoa');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2345, 'true', '176', 'ES-H', 'H', 'Huelva');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2346, 'true', '176', 'ES-HU', 'HU', 'Huesca');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2347, 'true', '176', 'ES-J', 'J', 'JaÃ©n');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2348, 'true', '176', 'ES-LO', 'LO', 'La Rioja');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2349, 'true', '176', 'ES-GC', 'GC', 'Las Palmas');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2350, 'true', '176', 'ES-LE', 'LE', 'LeÃ³n');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2351, 'true', '176', 'ES-L', 'L', 'Lleida');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2352, 'true', '176', 'ES-LU', 'LU', 'Lugo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2353, 'true', '176', 'ES-M', 'M', 'Madrid');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2354, 'true', '176', 'ES-MA', 'MA', 'MÃ¡laga');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2355, 'true', '176', 'ES-MU', 'MU', 'Murcia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2356, 'true', '176', 'ES-NA', 'NA', 'Navarra');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2357, 'true', '176', 'ES-OR', 'OR', 'Ourense');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2358, 'true', '176', 'ES-P', 'P', 'Palencia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2359, 'true', '176', 'ES-PO', 'PO', 'Pontevedra');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2360, 'true', '176', 'ES-SA', 'SA', 'Salamanca');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2361, 'true', '176', 'ES-TF', 'TF', 'Santa Cruz de Tenerife');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2362, 'true', '176', 'ES-SG', 'SG', 'Segovia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2363, 'true', '176', 'ES-SE', 'SE', 'Sevilla');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2364, 'true', '176', 'ES-SO', 'SO', 'Soria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2365, 'true', '176', 'ES-T', 'T', 'Tarragona');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2366, 'true', '176', 'ES-TE', 'TE', 'Teruel');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2367, 'true', '176', 'ES-TO', 'TO', 'Toledo');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2368, 'true', '176', 'ES-V', 'V', 'Valencia');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2369, 'true', '176', 'ES-VA', 'VA', 'Valladolid');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2370, 'true', '176', 'ES-BI', 'BI', 'Vizcaya');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2371, 'true', '176', 'ES-ZA', 'ZA', 'Zamora');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2372, 'true', '176', 'ES-Z', 'Z', 'Zaragoza');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2373, 'true', '176', 'ES-CE', 'CE', 'Ceuta');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (2374, 'true', '176', 'ES-ML', 'ML', 'Melilla');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4092, 'true', '176', 'ES-', '(null)', 'Spain ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3322, 'true', '182', 'SE-K', 'K', 'Blekinge lÃ¤n [SE-10]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3323, 'true', '182', 'SE-W', 'W', 'Dalarnas lÃ¤n [SE-20]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3324, 'true', '182', 'SE-I', 'I', 'Gotlands lÃ¤n [SE-09]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3325, 'true', '182', 'SE-X', 'X', 'GÃ¤vleborgs lÃ¤n [SE-21]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3326, 'true', '182', 'SE-N', 'N', 'Hallands lÃ¤n [SE-13]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3327, 'true', '182', 'SE-Z', 'Z', 'JÃ¤mtlands lÃ¤n [SE-23]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3328, 'true', '182', 'SE-F', 'F', 'JÃ¶nkÃ¶pings lÃ¤n [SE-06]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3329, 'true', '182', 'SE-H', 'H', 'Kalmar lÃ¤n [SE-08]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3330, 'true', '182', 'SE-G', 'G', 'Kronobergs lÃ¤n [SE-07]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3331, 'true', '182', 'SE-BD', 'BD', 'Norrbottens lÃ¤n [SE-25]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3332, 'true', '182', 'SE-M', 'M', 'SkÃ¥ne lÃ¤n [SE-12]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3333, 'true', '182', 'SE-AB', 'AB', 'Stockholms lÃ¤n [SE-01]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3334, 'true', '182', 'SE-D', 'D', 'SÃ¶dermanlands lÃ¤n [SE-04]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3335, 'true', '182', 'SE-C', 'C', 'Uppsala lÃ¤n [SE-03]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3336, 'true', '182', 'SE-S', 'S', 'VÃ¤rmlands lÃ¤n [SE-17]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3337, 'true', '182', 'SE-AC', 'AC', 'VÃ¤sterbottens lÃ¤n [SE-24]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3338, 'true', '182', 'SE-Y', 'Y', 'VÃ¤sternorrlands lÃ¤n [SE-22]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3339, 'true', '182', 'SE-U', 'U', 'VÃ¤stmanlands lÃ¤n [SE-19]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3340, 'true', '182', 'SE-O', 'O', 'VÃ¤stra GÃ¶talands lÃ¤n [SE-14]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3341, 'true', '182', 'SE-T', 'T', 'Ã–rebro lÃ¤n [SE-18]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (3342, 'true', '182', 'SE-E', 'E', 'Ã–stergÃ¶tlands lÃ¤n [SE-05]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4098, 'true', '182', 'SE-', '(null)', 'Sweden ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1949, 'true', '183', 'CH-AG', 'AG', 'Aargau (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1950, 'true', '183', 'CH-AR', 'AR', 'Appenzell Ausserrhoden (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1951, 'true', '183', 'CH-AI', 'AI', 'Appenzell Innerrhoden (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1952, 'true', '183', 'CH-BL', 'BL', 'Basel-Landschaft (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1953, 'true', '183', 'CH-BS', 'BS', 'Basel-Stadt (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1954, 'true', '183', 'CH-BE', 'BE', 'Bern (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1955, 'true', '183', 'CH-FR', 'FR', 'Fribourg (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1956, 'true', '183', 'CH-GE', 'GE', 'GenÃ¨ve (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1957, 'true', '183', 'CH-GL', 'GL', 'Glarus (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1958, 'true', '183', 'CH-GR', 'GR', 'GraubÃ¼nden (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1959, 'true', '183', 'CH-JU', 'JU', 'Jura (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1960, 'true', '183', 'CH-LU', 'LU', 'Luzern (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1961, 'true', '183', 'CH-NE', 'NE', 'NeuchÃ¢tel (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1962, 'true', '183', 'CH-NW', 'NW', 'Nidwalden (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1963, 'true', '183', 'CH-OW', 'OW', 'Obwalden (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1964, 'true', '183', 'CH-SG', 'SG', 'Sankt Gallen (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1965, 'true', '183', 'CH-SH', 'SH', 'Schaffhausen (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1966, 'true', '183', 'CH-SZ', 'SZ', 'Schwyz (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1967, 'true', '183', 'CH-SO', 'SO', 'Solothurn (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1968, 'true', '183', 'CH-TG', 'TG', 'Thurgau (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1969, 'true', '183', 'CH-TI', 'TI', 'Ticino (it)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1970, 'true', '183', 'CH-UR', 'UR', 'Uri (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1971, 'true', '183', 'CH-VS', 'VS', 'Valais (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1972, 'true', '183', 'CH-VD', 'VD', 'Vaud (fr)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1973, 'true', '183', 'CH-ZG', 'ZG', 'Zug (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1974, 'true', '183', 'CH-ZH', 'ZH', 'ZÃ¼rich (de)');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4099, 'true', '183', 'CH-', '(null)', 'Switzerland ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (993, 'true', '201', 'GB-ABE', 'ABE', 'Aberdeen City');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (994, 'true', '201', 'GB-ABD', 'ABD', 'Aberdeenshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (995, 'true', '201', 'GB-ANS', 'ANS', 'Angus');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (996, 'true', '201', 'GB-ANT', 'ANT', 'Antrim');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (997, 'true', '201', 'GB-ARD', 'ARD', 'Ards');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (998, 'true', '201', 'GB-AGB', 'AGB', 'Argyll and Bute');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (999, 'true', '201', 'GB-ARM', 'ARM', 'Armagh');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1000, 'true', '201', 'GB-BLA', 'BLA', 'Ballymena');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1001, 'true', '201', 'GB-BLY', 'BLY', 'Ballymoney');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1002, 'true', '201', 'GB-BNB', 'BNB', 'Banbridge');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1003, 'true', '201', 'GB-BDG', 'BDG', 'Barking and Dagenham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1004, 'true', '201', 'GB-BNE', 'BNE', 'Barnet');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1005, 'true', '201', 'GB-BNS', 'BNS', 'Barnsley');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1006, 'true', '201', 'GB-BAS', 'BAS', 'Bath and North East Somerset');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1007, 'true', '201', 'GB-BDF', 'BDF', 'Bedfordshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1008, 'true', '201', 'GB-BFS', 'BFS', 'Belfast');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1009, 'true', '201', 'GB-BEX', 'BEX', 'Bexley');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1010, 'true', '201', 'GB-BIR', 'BIR', 'Birmingham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1011, 'true', '201', 'GB-BBD', 'BBD', 'Blackburn with Darwen');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1012, 'true', '201', 'GB-BPL', 'BPL', 'Blackpool');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1013, 'true', '201', 'GB-BGW', 'BGW', 'Blaenau Gwent');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1014, 'true', '201', 'GB-BOL', 'BOL', 'Bolton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1015, 'true', '201', 'GB-BMH', 'BMH', 'Bournemouth');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1016, 'true', '201', 'GB-BRC', 'BRC', 'Bracknell Forest');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1017, 'true', '201', 'GB-BRD', 'BRD', 'Bradford');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1018, 'true', '201', 'GB-BEN', 'BEN', 'Brent');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1019, 'true', '201', 'GB-BGE', 'BGE', 'Bridgend [Pen-y-bont ar Ogwr GB-POG]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1020, 'true', '201', 'GB-BNH', 'BNH', 'Brighton and Hove');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1021, 'true', '201', 'GB-BST', 'BST', 'Bristol, City of');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1022, 'true', '201', 'GB-BRY', 'BRY', 'Bromley');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1023, 'true', '201', 'GB-BKM', 'BKM', 'Buckinghamshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1024, 'true', '201', 'GB-BUR', 'BUR', 'Bury');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1025, 'true', '201', 'GB-CAY', 'CAY', 'Caerphilly [Caerffili GB-CAF]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1026, 'true', '201', 'GB-CLD', 'CLD', 'Calderdale');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1027, 'true', '201', 'GB-CAM', 'CAM', 'Cambridgeshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1028, 'true', '201', 'GB-CMD', 'CMD', 'Camden');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1029, 'true', '201', 'GB-CRF', 'CRF', 'Cardiff [Caerdydd GB-CRD]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1030, 'true', '201', 'GB-CMN', 'CMN', 'Carmarthenshire [Sir Gaerfyrddin GB-GFY]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1031, 'true', '201', 'GB-CKF', 'CKF', 'Carrickfergus');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1032, 'true', '201', 'GB-CSR', 'CSR', 'Castlereagh');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1033, 'true', '201', 'GB-CGN', 'CGN', 'Ceredigion [Sir Ceredigion]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1034, 'true', '201', 'GB-CHS', 'CHS', 'Cheshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1035, 'true', '201', 'GB-CLK', 'CLK', 'Clackmannanshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1036, 'true', '201', 'GB-CLR', 'CLR', 'Coleraine');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1037, 'true', '201', 'GB-CWY', 'CWY', 'Conwy');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1038, 'true', '201', 'GB-CKT', 'CKT', 'Cookstown');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1039, 'true', '201', 'GB-CON', 'CON', 'Cornwall');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1040, 'true', '201', 'GB-COV', 'COV', 'Coventry');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1041, 'true', '201', 'GB-CGV', 'CGV', 'Craigavon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1042, 'true', '201', 'GB-CRY', 'CRY', 'Croydon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1043, 'true', '201', 'GB-CMA', 'CMA', 'Cumbria');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1044, 'true', '201', 'GB-DAL', 'DAL', 'Darlington');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1045, 'true', '201', 'GB-DEN', 'DEN', 'Denbighshire [Sir Ddinbych GB-DDB]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1046, 'true', '201', 'GB-DER', 'DER', 'Derby');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1047, 'true', '201', 'GB-DBY', 'DBY', 'Derbyshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1048, 'true', '201', 'GB-DRY', 'DRY', 'Derry');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1049, 'true', '201', 'GB-DEV', 'DEV', 'Devon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1050, 'true', '201', 'GB-DNC', 'DNC', 'Doncaster');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1051, 'true', '201', 'GB-DOR', 'DOR', 'Dorset');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1052, 'true', '201', 'GB-DOW', 'DOW', 'Down');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1053, 'true', '201', 'GB-DUD', 'DUD', 'Dudley');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1054, 'true', '201', 'GB-DGY', 'DGY', 'Dumfries and Galloway');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1055, 'true', '201', 'GB-DND', 'DND', 'Dundee City');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1056, 'true', '201', 'GB-DGN', 'DGN', 'Dungannon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1057, 'true', '201', 'GB-DUR', 'DUR', 'Durham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1058, 'true', '201', 'GB-EAL', 'EAL', 'Ealing');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1059, 'true', '201', 'GB-EAY', 'EAY', 'East Ayrshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1060, 'true', '201', 'GB-EDU', 'EDU', 'East Dunbartonshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1061, 'true', '201', 'GB-ELN', 'ELN', 'East Lothian');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1062, 'true', '201', 'GB-ERW', 'ERW', 'East Renfrewshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1063, 'true', '201', 'GB-ERY', 'ERY', 'East Riding of Yorkshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1064, 'true', '201', 'GB-ESX', 'ESX', 'East Sussex');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1065, 'true', '201', 'GB-EDH', 'EDH', 'Edinburgh, City of');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1066, 'true', '201', 'GB-ELS', 'ELS', 'Eilean Siar');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1067, 'true', '201', 'GB-ENF', 'ENF', 'Enfield');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1068, 'true', '201', 'GB-ESS', 'ESS', 'Essex');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1069, 'true', '201', 'GB-FAL', 'FAL', 'Falkirk');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1070, 'true', '201', 'GB-FER', 'FER', 'Fermanagh');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1071, 'true', '201', 'GB-FIF', 'FIF', 'Fife');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1072, 'true', '201', 'GB-FLN', 'FLN', 'Flintshire [Sir y Fflint GB-FFL]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1073, 'true', '201', 'GB-GAT', 'GAT', 'Gateshead');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1074, 'true', '201', 'GB-GLG', 'GLG', 'Glasgow City');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1075, 'true', '201', 'GB-GLS', 'GLS', 'Gloucestershire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1076, 'true', '201', 'GB-GRE', 'GRE', 'Greenwich');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1077, 'true', '201', 'GB-GSY', 'GSY', 'Guernsey [Guernesey]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1078, 'true', '201', 'GB-GWN', 'GWN', 'Gwynedd');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1079, 'true', '201', 'GB-HCK', 'HCK', 'Hackney');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1080, 'true', '201', 'GB-HAL', 'HAL', 'Halton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1081, 'true', '201', 'GB-HMF', 'HMF', 'Hammersmith and Fulham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1082, 'true', '201', 'GB-HAM', 'HAM', 'Hampshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1083, 'true', '201', 'GB-HRY', 'HRY', 'Haringey');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1084, 'true', '201', 'GB-HRW', 'HRW', 'Harrow');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1085, 'true', '201', 'GB-HPL', 'HPL', 'Hartlepool');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1086, 'true', '201', 'GB-HAV', 'HAV', 'Havering');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1087, 'true', '201', 'GB-HEF', 'HEF', 'Herefordshire, County of');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1088, 'true', '201', 'GB-HRT', 'HRT', 'Hertfordshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1089, 'true', '201', 'GB-HLD', 'HLD', 'Highland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1090, 'true', '201', 'GB-HIL', 'HIL', 'Hillingdon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1091, 'true', '201', 'GB-HNS', 'HNS', 'Hounslow');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1092, 'true', '201', 'GB-IVC', 'IVC', 'Inverclyde');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1093, 'true', '201', 'GB-AGY', 'AGY', 'Isle of Anglesey [Sir Ynys MÃ´n GB-YNM]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1094, 'true', '201', 'GB-IOW', 'IOW', 'Isle of Wight');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1095, 'true', '201', 'GB-IOS', 'IOS', 'Isles of Scilly');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1096, 'true', '201', 'GB-ISL', 'ISL', 'Islington');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1097, 'true', '201', 'GB-JSY', 'JSY', 'Jersey');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1098, 'true', '201', 'GB-KEC', 'KEC', 'Kensington and Chelsea');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1099, 'true', '201', 'GB-KEN', 'KEN', 'Kent');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1100, 'true', '201', 'GB-KHL', 'KHL', 'Kingston upon Hull, City of');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1101, 'true', '201', 'GB-KTT', 'KTT', 'Kingston upon Thames');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1102, 'true', '201', 'GB-KIR', 'KIR', 'Kirklees');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1103, 'true', '201', 'GB-KWL', 'KWL', 'Knowsley');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1104, 'true', '201', 'GB-LBH', 'LBH', 'Lambeth');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1105, 'true', '201', 'GB-LAN', 'LAN', 'Lancashire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1106, 'true', '201', 'GB-LRN', 'LRN', 'Larne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1107, 'true', '201', 'GB-LDS', 'LDS', 'Leeds');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1108, 'true', '201', 'GB-LCE', 'LCE', 'Leicester');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1109, 'true', '201', 'GB-LEC', 'LEC', 'Leicestershire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1110, 'true', '201', 'GB-LEW', 'LEW', 'Lewisham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1111, 'true', '201', 'GB-LMV', 'LMV', 'Limavady');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1112, 'true', '201', 'GB-LIN', 'LIN', 'Lincolnshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1113, 'true', '201', 'GB-LSB', 'LSB', 'Lisburn');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1114, 'true', '201', 'GB-LIV', 'LIV', 'Liverpool');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1115, 'true', '201', 'GB-LND', 'LND', 'London, City of');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1116, 'true', '201', 'GB-LUT', 'LUT', 'Luton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1117, 'true', '201', 'GB-MFT', 'MFT', 'Magherafelt');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1118, 'true', '201', 'GB-MAN', 'MAN', 'Manchester');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1119, 'true', '201', 'GB-MDW', 'MDW', 'Medway');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1120, 'true', '201', 'GB-MTY', 'MTY', 'Merthyr Tydfil [Merthyr Tudful GB-MTU]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1121, 'true', '201', 'GB-MRT', 'MRT', 'Merton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1122, 'true', '201', 'GB-MDB', 'MDB', 'Middlesbrough');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1123, 'true', '201', 'GB-MLN', 'MLN', 'Midlothian');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1124, 'true', '201', 'GB-MIK', 'MIK', 'Milton Keynes');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1125, 'true', '201', 'GB-MON', 'MON', 'Monmouthshire [Sir Fynwy GB-FYN]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1126, 'true', '201', 'GB-MRY', 'MRY', 'Moray');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1127, 'true', '201', 'GB-MYL', 'MYL', 'Moyle');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1128, 'true', '201', 'GB-NTL', 'NTL', 'Neath Port Talbot [Castell-nedd Port Talbot GB-CTL]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1129, 'true', '201', 'GB-NET', 'NET', 'Newcastle upon Tyne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1130, 'true', '201', 'GB-NWM', 'NWM', 'Newham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1131, 'true', '201', 'GB-NWP', 'NWP', 'Newport [Casnewydd GB-CNW]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1132, 'true', '201', 'GB-NYM', 'NYM', 'Newry and Mourne');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1133, 'true', '201', 'GB-NTA', 'NTA', 'Newtownabbey');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1134, 'true', '201', 'GB-NFK', 'NFK', 'Norfolk');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1135, 'true', '201', 'GB-NAY', 'NAY', 'North Ayrshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1136, 'true', '201', 'GB-NDN', 'NDN', 'North Down');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1137, 'true', '201', 'GB-NEL', 'NEL', 'North East Lincolnshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1138, 'true', '201', 'GB-NLK', 'NLK', 'North Lanarkshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1139, 'true', '201', 'GB-NLN', 'NLN', 'North Lincolnshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1140, 'true', '201', 'GB-NSM', 'NSM', 'North Somerset');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1141, 'true', '201', 'GB-NTY', 'NTY', 'North Tyneside');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1142, 'true', '201', 'GB-NYK', 'NYK', 'North Yorkshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1143, 'true', '201', 'GB-NTH', 'NTH', 'Northamptonshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1144, 'true', '201', 'GB-NBL', 'NBL', 'Northumberland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1145, 'true', '201', 'GB-NGM', 'NGM', 'Nottingham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1146, 'true', '201', 'GB-NTT', 'NTT', 'Nottinghamshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1147, 'true', '201', 'GB-OLD', 'OLD', 'Oldham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1148, 'true', '201', 'GB-OMH', 'OMH', 'Omagh');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1149, 'true', '201', 'GB-ORK', 'ORK', 'Orkney Islands');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1150, 'true', '201', 'GB-OXF', 'OXF', 'Oxfordshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1151, 'true', '201', 'GB-PEM', 'PEM', 'Pembrokeshire [Sir Benfro GB-BNF]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1152, 'true', '201', 'GB-PKN', 'PKN', 'Perth and Kinross');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1153, 'true', '201', 'GB-PTE', 'PTE', 'Peterborough');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1154, 'true', '201', 'GB-PLY', 'PLY', 'Plymouth');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1155, 'true', '201', 'GB-POL', 'POL', 'Poole');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1156, 'true', '201', 'GB-POR', 'POR', 'Portsmouth');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1157, 'true', '201', 'GB-POW', 'POW', 'Powys');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1158, 'true', '201', 'GB-RDG', 'RDG', 'Reading');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1159, 'true', '201', 'GB-RDB', 'RDB', 'Redbridge');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1160, 'true', '201', 'GB-RCC', 'RCC', 'Redcar and Cleveland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1161, 'true', '201', 'GB-RFW', 'RFW', 'Renfrewshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1162, 'true', '201', 'GB-RCT', 'RCT', 'Rhondda, Cynon, Taff [Rhondda, Cynon,Taf]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1163, 'true', '201', 'GB-RIC', 'RIC', 'Richmond upon Thames');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1164, 'true', '201', 'GB-RCH', 'RCH', 'Rochdale');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1165, 'true', '201', 'GB-ROT', 'ROT', 'Rotherham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1166, 'true', '201', 'GB-RUT', 'RUT', 'Rutland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1167, 'true', '201', 'GB-SHN', 'SHN', 'St. Helens');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1168, 'true', '201', 'GB-SLF', 'SLF', 'Salford');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1169, 'true', '201', 'GB-SAW', 'SAW', 'Sandwell');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1170, 'true', '201', 'GB-SCB', 'SCB', 'Scottish Borders, The');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1171, 'true', '201', 'GB-SFT', 'SFT', 'Sefton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1172, 'true', '201', 'GB-SHF', 'SHF', 'Sheffield');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1173, 'true', '201', 'GB-ZET', 'ZET', 'Shetland Islands');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1174, 'true', '201', 'GB-SHR', 'SHR', 'Shropshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1175, 'true', '201', 'GB-SLG', 'SLG', 'Slough');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1176, 'true', '201', 'GB-SOL', 'SOL', 'Solihull');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1177, 'true', '201', 'GB-SOM', 'SOM', 'Somerset');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1178, 'true', '201', 'GB-SAY', 'SAY', 'South Ayrshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1179, 'true', '201', 'GB-SGC', 'SGC', 'South Gloucestershire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1180, 'true', '201', 'GB-SLK', 'SLK', 'South Lanarkshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1181, 'true', '201', 'GB-STY', 'STY', 'South Tyneside');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1182, 'true', '201', 'GB-STH', 'STH', 'Southampton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1183, 'true', '201', 'GB-SOS', 'SOS', 'Southend-on-Sea');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1184, 'true', '201', 'GB-SWK', 'SWK', 'Southwark');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1185, 'true', '201', 'GB-STS', 'STS', 'Staffordshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1186, 'true', '201', 'GB-STG', 'STG', 'Stirling');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1187, 'true', '201', 'GB-SKP', 'SKP', 'Stockport');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1188, 'true', '201', 'GB-STT', 'STT', 'Stockton-on-Tees');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1189, 'true', '201', 'GB-STE', 'STE', 'Stoke-on-Trent');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1190, 'true', '201', 'GB-STB', 'STB', 'Strabane');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1191, 'true', '201', 'GB-SFK', 'SFK', 'Suffolk');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1192, 'true', '201', 'GB-SND', 'SND', 'Sunderland');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1193, 'true', '201', 'GB-SRY', 'SRY', 'Surrey');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1194, 'true', '201', 'GB-STN', 'STN', 'Sutton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1195, 'true', '201', 'GB-SWA', 'SWA', 'Swansea [Abertawe GB-ATA]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1196, 'true', '201', 'GB-SWD', 'SWD', 'Swindon');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1197, 'true', '201', 'GB-TAM', 'TAM', 'Tameside');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1198, 'true', '201', 'GB-TFW', 'TFW', 'Telford and Wrekin');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1199, 'true', '201', 'GB-THR', 'THR', 'Thurrock');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1200, 'true', '201', 'GB-TOB', 'TOB', 'Torbay');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1201, 'true', '201', 'GB-TOF', 'TOF', 'Torfaen [Tor-faen]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1202, 'true', '201', 'GB-TWH', 'TWH', 'Tower Hamlets');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1203, 'true', '201', 'GB-TRF', 'TRF', 'Trafford');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1204, 'true', '201', 'GB-VGL', 'VGL', 'Vale of Glamorgan, The [Bro Morgannwg GB-BMG]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1205, 'true', '201', 'GB-WKF', 'WKF', 'Wakefield');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1206, 'true', '201', 'GB-WLL', 'WLL', 'Walsall');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1207, 'true', '201', 'GB-WFT', 'WFT', 'Waltham Forest');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1208, 'true', '201', 'GB-WND', 'WND', 'Wandsworth');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1209, 'true', '201', 'GB-WRT', 'WRT', 'Warrington');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1210, 'true', '201', 'GB-WAR', 'WAR', 'Warwickshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1211, 'true', '201', 'GB-WBK', 'WBK', 'West Berkshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1212, 'true', '201', 'GB-WDU', 'WDU', 'West Dunbartonshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1213, 'true', '201', 'GB-WLN', 'WLN', 'West Lothian');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1214, 'true', '201', 'GB-WSX', 'WSX', 'West Sussex');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1215, 'true', '201', 'GB-WSM', 'WSM', 'Westminster');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1216, 'true', '201', 'GB-WGN', 'WGN', 'Wigan');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1217, 'true', '201', 'GB-WIL', 'WIL', 'Wiltshire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1218, 'true', '201', 'GB-WNM', 'WNM', 'Windsor and Maidenhead');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1219, 'true', '201', 'GB-WRL', 'WRL', 'Wirral');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1220, 'true', '201', 'GB-WOK', 'WOK', 'Wokingham');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1221, 'true', '201', 'GB-WLV', 'WLV', 'Wolverhampton');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1222, 'true', '201', 'GB-WOR', 'WOR', 'Worcestershire');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1223, 'true', '201', 'GB-WRX', 'WRX', 'Wrexham [Wrecsam GB-WRC]');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1224, 'true', '201', 'GB-YOR', 'YOR', 'York');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4117, 'true', '201', 'GB-', '(null)', 'United Kingdom ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (4118, 'true', '202', 'US-', '(null)', 'United States ');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1897, 'true', '38', 'CA-AB', 'AB', 'Alberta');
INSERT INTO subnationalcode (subnatcodeid, islegal, natcodeid, nationcode, localabbrev, nationname) VALUES (1898, 'true', '38', 'CA-BC', 'BC', 'British Columbia');


--
-- TOC entry 1874 (class 0 OID 223427)
-- Dependencies: 1339
-- Data for Name: timezones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1875 (class 0 OID 223430)
-- Dependencies: 1340
-- Data for Name: twoplayermatch; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1876 (class 0 OID 223435)
-- Dependencies: 1341
-- Data for Name: twoplayermatchcomments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1877 (class 0 OID 223440)
-- Dependencies: 1342
-- Data for Name: twoplayertournament; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1878 (class 0 OID 223446)
-- Dependencies: 1343
-- Data for Name: twoplayertournamentadmincomments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1879 (class 0 OID 223448)
-- Dependencies: 1344
-- Data for Name: twoplayertournamentgameoptions; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1702 (class 2606 OID 223451)
-- Dependencies: 1314 1314
-- Name: PK_AdminNotification_AdminNotificationID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adminnotification
    ADD CONSTRAINT "PK_AdminNotification_AdminNotificationID" PRIMARY KEY (adminnotificationid);


--
-- TOC entry 1700 (class 2606 OID 223453)
-- Dependencies: 1313 1313
-- Name: PK_Admin_AdminId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "admin"
    ADD CONSTRAINT "PK_Admin_AdminId" PRIMARY KEY (adminid);


--
-- TOC entry 1710 (class 2606 OID 223455)
-- Dependencies: 1317 1317
-- Name: PK_FeedbackReputation_FeedbackReputationId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY feedbackreputation
    ADD CONSTRAINT "PK_FeedbackReputation_FeedbackReputationId" PRIMARY KEY (feedbackreputationid);


--
-- TOC entry 1712 (class 2606 OID 223457)
-- Dependencies: 1318 1318
-- Name: PK_Friends_FriendsId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "PK_Friends_FriendsId" PRIMARY KEY (friendsid);


--
-- TOC entry 1719 (class 2606 OID 223459)
-- Dependencies: 1320 1320
-- Name: PK_GameCategoryMaster_CategoryId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gamecategory
    ADD CONSTRAINT "PK_GameCategoryMaster_CategoryId" PRIMARY KEY (categoryid);


--
-- TOC entry 1717 (class 2606 OID 223461)
-- Dependencies: 1319 1319
-- Name: PK_GameMaster_Gameid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "PK_GameMaster_Gameid" PRIMARY KEY (gameid);


--
-- TOC entry 1730 (class 2606 OID 223463)
-- Dependencies: 1323 1323
-- Name: PK_Message_MessageId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "PK_Message_MessageId" PRIMARY KEY (messageid);


--
-- TOC entry 1741 (class 2606 OID 223465)
-- Dependencies: 1325 1325
-- Name: PK_NetworkMaster_Networkid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY network
    ADD CONSTRAINT "PK_NetworkMaster_Networkid" PRIMARY KEY (networkid);


--
-- TOC entry 1745 (class 2606 OID 223467)
-- Dependencies: 1326 1326
-- Name: PK_NotificationQueue_NotificationId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "PK_NotificationQueue_NotificationId" PRIMARY KEY (notificationid);


--
-- TOC entry 1747 (class 2606 OID 223469)
-- Dependencies: 1327 1327
-- Name: PK_NotificationType_NotificationTypeID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationtype
    ADD CONSTRAINT "PK_NotificationType_NotificationTypeID" PRIMARY KEY (notificationtypeid);


--
-- TOC entry 1763 (class 2606 OID 223471)
-- Dependencies: 1331 1331
-- Name: PK_PlayerComments_CommentSeq; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playercomments
    ADD CONSTRAINT "PK_PlayerComments_CommentSeq" PRIMARY KEY (commentseq);


--
-- TOC entry 1778 (class 2606 OID 223473)
-- Dependencies: 1336 1336
-- Name: PK_SiteContentSection_ContenetId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sitecontentsection
    ADD CONSTRAINT "PK_SiteContentSection_ContenetId" PRIMARY KEY (contentid);


--
-- TOC entry 1784 (class 2606 OID 223475)
-- Dependencies: 1338 1338
-- Name: PK_SubNationalCode_SubNatCodeId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subnationalcode
    ADD CONSTRAINT "PK_SubNationalCode_SubNatCodeId" PRIMARY KEY (subnatcodeid);


--
-- TOC entry 1797 (class 2606 OID 223477)
-- Dependencies: 1342 1342
-- Name: PK_TwoPlayerTournament_TournamentId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "PK_TwoPlayerTournament_TournamentId" PRIMARY KEY (tournamentid);


--
-- TOC entry 1776 (class 2606 OID 223479)
-- Dependencies: 1335 1335
-- Name: Pk_SiteContent_SiteId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sitecontent
    ADD CONSTRAINT "Pk_SiteContent_SiteId" PRIMARY KEY (siteid);


--
-- TOC entry 1801 (class 2606 OID 223481)
-- Dependencies: 1343 1343
-- Name: Pkey_CommentSeq; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournamentadmincomments
    ADD CONSTRAINT "Pkey_CommentSeq" PRIMARY KEY (commentseq);


--
-- TOC entry 1706 (class 2606 OID 223483)
-- Dependencies: 1316 1316
-- Name: Pkey_employeeid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT "Pkey_employeeid" PRIMARY KEY (employeeid);


--
-- TOC entry 1708 (class 2606 OID 223485)
-- Dependencies: 1316 1316
-- Name: UK_Employee_loginName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT "UK_Employee_loginName" UNIQUE (loginname);


--
-- TOC entry 1721 (class 2606 OID 223487)
-- Dependencies: 1320 1320
-- Name: UK_GameCategory_Name; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gamecategory
    ADD CONSTRAINT "UK_GameCategory_Name" UNIQUE (name);


--
-- TOC entry 1732 (class 2606 OID 223489)
-- Dependencies: 1323 1323
-- Name: UK_Message_RandomId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "UK_Message_RandomId" UNIQUE (randomid);


--
-- TOC entry 1743 (class 2606 OID 223491)
-- Dependencies: 1325 1325
-- Name: UK_Network_NetworkName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY network
    ADD CONSTRAINT "UK_Network_NetworkName" UNIQUE (networkname);


--
-- TOC entry 1749 (class 2606 OID 223493)
-- Dependencies: 1327 1327
-- Name: UK_NotificationType_Name; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationtype
    ADD CONSTRAINT "UK_NotificationType_Name" UNIQUE (name);


--
-- TOC entry 1765 (class 2606 OID 223495)
-- Dependencies: 1332 1332 1332
-- Name: UK_PlayerGame_PlayerId_GameId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT "UK_PlayerGame_PlayerId_GameId" UNIQUE (playerid, gameid);


--
-- TOC entry 1771 (class 2606 OID 223497)
-- Dependencies: 1334 1334 1334
-- Name: UK_PlayerNetwork_Network_PlayerID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT "UK_PlayerNetwork_Network_PlayerID" UNIQUE (networkid, playerid);


--
-- TOC entry 1753 (class 2606 OID 223499)
-- Dependencies: 1329 1329
-- Name: UK_Player_EmailAddress; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "UK_Player_EmailAddress" UNIQUE (emailaddress);


--
-- TOC entry 1755 (class 2606 OID 223501)
-- Dependencies: 1329 1329
-- Name: UK_Player_ScreenName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "UK_Player_ScreenName" UNIQUE (screenname);


--
-- TOC entry 1786 (class 2606 OID 223503)
-- Dependencies: 1338 1338 1338
-- Name: UK_SubNationalCode_NatCode_NationCode; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subnationalcode
    ADD CONSTRAINT "UK_SubNationalCode_NatCode_NationCode" UNIQUE (natcodeid, nationcode);


--
-- TOC entry 1791 (class 2606 OID 223505)
-- Dependencies: 1340 1340 1340
-- Name: UK_TwoPlayerMatch_MatchID_TournamentId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "UK_TwoPlayerMatch_MatchID_TournamentId" UNIQUE (matchid, tournamentid);


--
-- TOC entry 1799 (class 2606 OID 223507)
-- Dependencies: 1342 1342 1342
-- Name: UK_TwoPlayerTournament_TournamentId_GameId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "UK_TwoPlayerTournament_TournamentId_GameId" UNIQUE (tournamentid, gameid);


--
-- TOC entry 1714 (class 2606 OID 223509)
-- Dependencies: 1318 1318 1318
-- Name: UQ_Friends_PlayerId1_PlayerId2; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "UQ_Friends_PlayerId1_PlayerId2" UNIQUE (playerid1, playerid2);


--
-- TOC entry 1780 (class 2606 OID 223511)
-- Dependencies: 1337 1337
-- Name: UniqueSkinId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY skin
    ADD CONSTRAINT "UniqueSkinId" UNIQUE (skinid);


--
-- TOC entry 1704 (class 2606 OID 223513)
-- Dependencies: 1315 1315
-- Name: avatars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avatars
    ADD CONSTRAINT avatars_pkey PRIMARY KEY (avatarid);


--
-- TOC entry 1723 (class 2606 OID 223515)
-- Dependencies: 1321 1321 1321 1321
-- Name: gameoptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gameoptions
    ADD CONSTRAINT gameoptions_pkey PRIMARY KEY (gameid, optionid, valueid);


--
-- TOC entry 1725 (class 2606 OID 223517)
-- Dependencies: 1322 1322
-- Name: imnetwork_imnetworkname_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imnetwork
    ADD CONSTRAINT imnetwork_imnetworkname_key UNIQUE (imnetworkname);


--
-- TOC entry 1727 (class 2606 OID 223519)
-- Dependencies: 1322 1322
-- Name: imnetwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imnetwork
    ADD CONSTRAINT imnetwork_pkey PRIMARY KEY (imnetworkid);


--
-- TOC entry 1735 (class 2606 OID 223521)
-- Dependencies: 1324 1324
-- Name: nationalcode_nationcode_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_nationcode_key UNIQUE (nationcode);


--
-- TOC entry 1737 (class 2606 OID 223523)
-- Dependencies: 1324 1324 1324
-- Name: nationalcode_nationname_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_nationname_key UNIQUE (nationname, nationcode);


--
-- TOC entry 1739 (class 2606 OID 223525)
-- Dependencies: 1324 1324
-- Name: nationalcode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_pkey PRIMARY KEY (natcodeid);


--
-- TOC entry 1751 (class 2606 OID 223527)
-- Dependencies: 1328 1328
-- Name: pictures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pictures
    ADD CONSTRAINT pictures_pkey PRIMARY KEY (id);


--
-- TOC entry 1757 (class 2606 OID 223529)
-- Dependencies: 1329 1329
-- Name: player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (playerid);


--
-- TOC entry 1761 (class 2606 OID 223531)
-- Dependencies: 1330 1330
-- Name: playeradmincomments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playeradmincomments
    ADD CONSTRAINT playeradmincomments_pkey PRIMARY KEY (id);


--
-- TOC entry 1767 (class 2606 OID 223533)
-- Dependencies: 1332 1332
-- Name: playergame_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT playergame_pkey PRIMARY KEY (playergameid);


--
-- TOC entry 1769 (class 2606 OID 223535)
-- Dependencies: 1333 1333
-- Name: playerim_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playerim
    ADD CONSTRAINT playerim_pkey PRIMARY KEY (imid);


--
-- TOC entry 1773 (class 2606 OID 223537)
-- Dependencies: 1334 1334
-- Name: playernetwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT playernetwork_pkey PRIMARY KEY (playernetworkid);


--
-- TOC entry 1782 (class 2606 OID 223539)
-- Dependencies: 1337 1337
-- Name: skin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY skin
    ADD CONSTRAINT skin_pkey PRIMARY KEY (id);


--
-- TOC entry 1789 (class 2606 OID 223541)
-- Dependencies: 1339 1339
-- Name: timezones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY timezones
    ADD CONSTRAINT timezones_pkey PRIMARY KEY (id);


--
-- TOC entry 1793 (class 2606 OID 223543)
-- Dependencies: 1340 1340
-- Name: twoplayermatch_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT twoplayermatch_pkey PRIMARY KEY (matchid);


--
-- TOC entry 1795 (class 2606 OID 223545)
-- Dependencies: 1341 1341
-- Name: twoplayermatchcomments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayermatchcomments
    ADD CONSTRAINT twoplayermatchcomments_pkey PRIMARY KEY (tournamentcommentsid);


--
-- TOC entry 1803 (class 2606 OID 223547)
-- Dependencies: 1344 1344
-- Name: twoplayertournamentgameoptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournamentgameoptions
    ADD CONSTRAINT twoplayertournamentgameoptions_pkey PRIMARY KEY (twoplayertournamentgameoptionsid);


--
-- TOC entry 1728 (class 1259 OID 223548)
-- Dependencies: 1322
-- Name: index_imnetwork_imnetworkname_key; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX index_imnetwork_imnetworkname_key ON imnetwork USING btree (imnetworkname);


--
-- TOC entry 1733 (class 1259 OID 223549)
-- Dependencies: 1324
-- Name: index_nationalcode_nationcode_key; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX index_nationalcode_nationcode_key ON nationalcode USING btree (nationcode);


--
-- TOC entry 1758 (class 1259 OID 223550)
-- Dependencies: 1329
-- Name: uk_player_emailaddress; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_player_emailaddress ON player USING btree (emailaddress);


--
-- TOC entry 1759 (class 1259 OID 223551)
-- Dependencies: 1329
-- Name: uk_player_screenname; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_player_screenname ON player USING btree (screenname);


--
-- TOC entry 1774 (class 1259 OID 223552)
-- Dependencies: 1334 1334
-- Name: uk_playernetwork_network_playerid; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_playernetwork_network_playerid ON playernetwork USING btree (networkid, playerid);


--
-- TOC entry 1787 (class 1259 OID 223553)
-- Dependencies: 1338 1338
-- Name: uk_subnationalcode_natcode_nationcode; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_subnationalcode_natcode_nationcode ON subnationalcode USING btree (natcodeid, nationcode);


--
-- TOC entry 1715 (class 1259 OID 223554)
-- Dependencies: 1318 1318
-- Name: uq_friends_playerid1_playerid2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uq_friends_playerid1_playerid2 ON friends USING btree (playerid1, playerid2);


--
-- TOC entry 1842 (class 2606 OID 223555)
-- Dependencies: 1342 1316 1705
-- Name: FK_Employee_TwoPlayerTournament_EmployeeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "FK_Employee_TwoPlayerTournament_EmployeeId" FOREIGN KEY (createdby) REFERENCES employee(employeeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1809 (class 2606 OID 223560)
-- Dependencies: 1718 1319 1320
-- Name: FK_GameCategoryMaster_GameMaster_CategoryId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "FK_GameCategoryMaster_GameMaster_CategoryId" FOREIGN KEY (categoryid) REFERENCES gamecategory(categoryid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1811 (class 2606 OID 223565)
-- Dependencies: 1319 1716 1321
-- Name: FK_GameMaster_GameOptionMaster_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gameoptions
    ADD CONSTRAINT "FK_GameMaster_GameOptionMaster_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1829 (class 2606 OID 223570)
-- Dependencies: 1332 1319 1716
-- Name: FK_Game_PlayerGame_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT "FK_Game_PlayerGame_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid);


--
-- TOC entry 1843 (class 2606 OID 223575)
-- Dependencies: 1342 1319 1716
-- Name: FK_Game_TwoPlayerTournament_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "FK_Game_TwoPlayerTournament_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1812 (class 2606 OID 223580)
-- Dependencies: 1323 1327 1746
-- Name: FK_Message_NotificationType_NotificationTypeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Message_NotificationType_NotificationTypeId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1813 (class 2606 OID 223585)
-- Dependencies: 1323 1329 1756
-- Name: FK_Message_Player_FromPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Message_Player_FromPlayerId" FOREIGN KEY (fromplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1814 (class 2606 OID 223590)
-- Dependencies: 1323 1340 1792
-- Name: FK_Message_TwoPlayerMatch_MatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Message_TwoPlayerMatch_MatchId" FOREIGN KEY (matchid) REFERENCES twoplayermatch(matchid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1836 (class 2606 OID 223595)
-- Dependencies: 1338 1324 1738
-- Name: FK_NationalCode_SubNationalCode_NatCodeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subnationalcode
    ADD CONSTRAINT "FK_NationalCode_SubNationalCode_NatCodeId" FOREIGN KEY (natcodeid) REFERENCES nationalcode(natcodeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1810 (class 2606 OID 223600)
-- Dependencies: 1319 1325 1740
-- Name: FK_NetworkMaster_GameMaster_NetworkId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "FK_NetworkMaster_GameMaster_NetworkId" FOREIGN KEY (networkid) REFERENCES network(networkid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1833 (class 2606 OID 223605)
-- Dependencies: 1334 1325 1740
-- Name: FK_NetworkMaster_PlayerNetwork_NetworkId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT "FK_NetworkMaster_PlayerNetwork_NetworkId" FOREIGN KEY (networkid) REFERENCES network(networkid);


--
-- TOC entry 1804 (class 2606 OID 223610)
-- Dependencies: 1314 1327 1746
-- Name: FK_NotificationType_AdminNotification_NotificationId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adminnotification
    ADD CONSTRAINT "FK_NotificationType_AdminNotification_NotificationId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1818 (class 2606 OID 223615)
-- Dependencies: 1326 1327 1746
-- Name: FK_NotificationType_NotificationQueue_NotificationTypeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "FK_NotificationType_NotificationQueue_NotificationTypeId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1821 (class 2606 OID 223620)
-- Dependencies: 1328 1329 1756
-- Name: FK_Pictures_UserId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pictures
    ADD CONSTRAINT "FK_Pictures_UserId" FOREIGN KEY (userid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1822 (class 2606 OID 223625)
-- Dependencies: 1329 1315 1703
-- Name: FK_Player_Avatars_AvatarId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "FK_Player_Avatars_AvatarId" FOREIGN KEY (avatarid) REFERENCES avatars(avatarid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1805 (class 2606 OID 223630)
-- Dependencies: 1317 1329 1756
-- Name: FK_Player_FeedbackReputation_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY feedbackreputation
    ADD CONSTRAINT "FK_Player_FeedbackReputation_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1807 (class 2606 OID 223635)
-- Dependencies: 1318 1329 1756
-- Name: FK_Player_Friends_PlayerId1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "FK_Player_Friends_PlayerId1" FOREIGN KEY (playerid1) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1808 (class 2606 OID 223640)
-- Dependencies: 1318 1329 1756
-- Name: FK_Player_Friends_PlayerId2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "FK_Player_Friends_PlayerId2" FOREIGN KEY (playerid2) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1815 (class 2606 OID 223645)
-- Dependencies: 1323 1329 1756
-- Name: FK_Player_Message_ToPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Player_Message_ToPlayerId" FOREIGN KEY (toplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1819 (class 2606 OID 223650)
-- Dependencies: 1326 1329 1756
-- Name: FK_Player_NotificationQueue_RecipientPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "FK_Player_NotificationQueue_RecipientPlayerId" FOREIGN KEY (recipientplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1820 (class 2606 OID 223655)
-- Dependencies: 1326 1329 1756
-- Name: FK_Player_NotifictionQueue_SourcePlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "FK_Player_NotifictionQueue_SourcePlayerId" FOREIGN KEY (sourceplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1827 (class 2606 OID 223660)
-- Dependencies: 1331 1329 1756
-- Name: FK_Player_PlayerComments_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playercomments
    ADD CONSTRAINT "FK_Player_PlayerComments_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1828 (class 2606 OID 223665)
-- Dependencies: 1331 1329 1756
-- Name: FK_Player_PlayerComments_TargetPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playercomments
    ADD CONSTRAINT "FK_Player_PlayerComments_TargetPlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1830 (class 2606 OID 223670)
-- Dependencies: 1332 1329 1756
-- Name: FK_Player_PlayerGame_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT "FK_Player_PlayerGame_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1834 (class 2606 OID 223675)
-- Dependencies: 1756 1334 1329
-- Name: FK_Player_PlayerNetwork_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT "FK_Player_PlayerNetwork_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1823 (class 2606 OID 223680)
-- Dependencies: 1779 1329 1337
-- Name: FK_Player_Skin_skinID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "FK_Player_Skin_skinID" FOREIGN KEY (skinid) REFERENCES skin(skinid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1824 (class 2606 OID 223685)
-- Dependencies: 1329 1339 1788
-- Name: FK_Player_Timezone_Timezoneid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "FK_Player_Timezone_Timezoneid" FOREIGN KEY (preftimezone) REFERENCES timezones(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1837 (class 2606 OID 223690)
-- Dependencies: 1329 1340 1756
-- Name: FK_Player_TwoPlayerMatch_PlayerOneId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_Player_TwoPlayerMatch_PlayerOneId" FOREIGN KEY (playeroneid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1838 (class 2606 OID 223695)
-- Dependencies: 1340 1329 1756
-- Name: FK_Player_TwoPlayerMatch_PlayerTwoId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_Player_TwoPlayerMatch_PlayerTwoId" FOREIGN KEY (playertwoid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1835 (class 2606 OID 223700)
-- Dependencies: 1335 1775 1336
-- Name: FK_SiteContentSection_SiteContent_ContentId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sitecontentsection
    ADD CONSTRAINT "FK_SiteContentSection_SiteContent_ContentId" FOREIGN KEY (contentid) REFERENCES sitecontent(siteid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1816 (class 2606 OID 223705)
-- Dependencies: 1779 1323 1337
-- Name: FK_Skin_Message_SiteID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Skin_Message_SiteID" FOREIGN KEY (siteid) REFERENCES skin(skinid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1844 (class 2606 OID 223710)
-- Dependencies: 1337 1779 1342
-- Name: FK_Skin_TwoPlayerTournament_SkinId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "FK_Skin_TwoPlayerTournament_SkinId" FOREIGN KEY (skinid) REFERENCES skin(skinid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1806 (class 2606 OID 223715)
-- Dependencies: 1340 1317 1792
-- Name: FK_TwoPlayerMatch_FeedbackReputation_MatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY feedbackreputation
    ADD CONSTRAINT "FK_TwoPlayerMatch_FeedbackReputation_MatchId" FOREIGN KEY (matchid) REFERENCES twoplayermatch(matchid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1839 (class 2606 OID 223720)
-- Dependencies: 1792 1340 1340
-- Name: FK_TwoPlayerMatch_NextMatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_TwoPlayerMatch_NextMatchId" FOREIGN KEY (nextmatchid) REFERENCES twoplayermatch(matchid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1845 (class 2606 OID 223725)
-- Dependencies: 1705 1343 1316
-- Name: FK_TwoPlayerTournamentAdminComments_Employee_EmployeeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournamentadmincomments
    ADD CONSTRAINT "FK_TwoPlayerTournamentAdminComments_Employee_EmployeeId" FOREIGN KEY (employeeid) REFERENCES employee(employeeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1846 (class 2606 OID 223730)
-- Dependencies: 1796 1343 1342
-- Name: FK_TwoPlayerTournamentAdminComments_TwoPlayerTournament_Tournam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournamentadmincomments
    ADD CONSTRAINT "FK_TwoPlayerTournamentAdminComments_TwoPlayerTournament_Tournam" FOREIGN KEY (tournamentid) REFERENCES twoplayertournament(tournamentid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1847 (class 2606 OID 223735)
-- Dependencies: 1796 1344 1342
-- Name: FK_TwoPlayerTournamentGameOptions_TwoPlayerTournament_Tournamen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournamentgameoptions
    ADD CONSTRAINT "FK_TwoPlayerTournamentGameOptions_TwoPlayerTournament_Tournamen" FOREIGN KEY (tournamentid) REFERENCES twoplayertournament(tournamentid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1840 (class 2606 OID 223740)
-- Dependencies: 1329 1756 1340
-- Name: FK_TwoPlayerTournament_Player_WinnerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_TwoPlayerTournament_Player_WinnerId" FOREIGN KEY (winnerid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1841 (class 2606 OID 223745)
-- Dependencies: 1340 1342 1796
-- Name: FK_TwoPlayerTournament_TwoPlayerMatch_TournamentId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_TwoPlayerTournament_TwoPlayerMatch_TournamentId" FOREIGN KEY (tournamentid) REFERENCES twoplayertournament(tournamentid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1817 (class 2606 OID 223750)
-- Dependencies: 1323 1340 1792
-- Name: K_Message_TwoPlayerMatch_MatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "K_Message_TwoPlayerMatch_MatchId" FOREIGN KEY (matchid) REFERENCES twoplayermatch(matchid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1831 (class 2606 OID 223755)
-- Dependencies: 1333 1329 1756
-- Name: fk700373c5c919dcca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playerim
    ADD CONSTRAINT fk700373c5c919dcca FOREIGN KEY (playerid) REFERENCES player(playerid);


--
-- TOC entry 1825 (class 2606 OID 223760)
-- Dependencies: 1330 1705 1316
-- Name: playeradmincomments_employeeid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playeradmincomments
    ADD CONSTRAINT playeradmincomments_employeeid FOREIGN KEY (employeeid) REFERENCES employee(employeeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1826 (class 2606 OID 223765)
-- Dependencies: 1756 1329 1330
-- Name: playeradmincomments_player_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playeradmincomments
    ADD CONSTRAINT playeradmincomments_player_fkey FOREIGN KEY (player) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1832 (class 2606 OID 223770)
-- Dependencies: 1322 1333 1726
-- Name: playerim_imnetworkid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playerim
    ADD CONSTRAINT playerim_imnetworkid FOREIGN KEY (imnetworkid) REFERENCES imnetwork(imnetworkid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1884 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2008-05-20 20:29:32

--
-- PostgreSQL database dump complete
--

