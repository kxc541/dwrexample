
CREATE TABLE "admin" (
    adminemail text,
    maxpicsize integer,
    maxpics integer,
    adminid integer NOT NULL
);


ALTER TABLE public."admin" OWNER TO postgres;

--
-- TOC entry 1342 (class 1259 OID 148844)
-- Dependencies: 1696 1697 1698 4
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
-- TOC entry 1323 (class 1259 OID 25778)
-- Dependencies: 4
-- Name: avatars; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avatars (
    avatarid integer NOT NULL,
    image bytea NOT NULL
);


ALTER TABLE public.avatars OWNER TO postgres;

--
-- TOC entry 1318 (class 1259 OID 25753)
-- Dependencies: 4
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
-- TOC entry 1337 (class 1259 OID 74982)
-- Dependencies: 1682 1683 4
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
-- TOC entry 1330 (class 1259 OID 58606)
-- Dependencies: 4
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
-- TOC entry 1331 (class 1259 OID 58610)
-- Dependencies: 4
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
-- TOC entry 1332 (class 1259 OID 58614)
-- Dependencies: 4
-- Name: gamecategory; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gamecategory (
    categoryid integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.gamecategory OWNER TO postgres;

--
-- TOC entry 1340 (class 1259 OID 91410)
-- Dependencies: 4
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
-- TOC entry 1322 (class 1259 OID 25772)
-- Dependencies: 4
-- Name: imnetwork; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE imnetwork (
    imnetworkid integer NOT NULL,
    imnetworkname character varying(10)
);


ALTER TABLE public.imnetwork OWNER TO postgres;

--
-- TOC entry 1327 (class 1259 OID 25797)
-- Dependencies: 1677 1678 1679 1680 1681 4
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
-- TOC entry 1329 (class 1259 OID 58597)
-- Dependencies: 4
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
-- TOC entry 1321 (class 1259 OID 25770)
-- Dependencies: 4
-- Name: network; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE network (
    networkid integer NOT NULL,
    networkname character varying(10)
);


ALTER TABLE public.network OWNER TO postgres;

--
-- TOC entry 1326 (class 1259 OID 25792)
-- Dependencies: 4
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
-- TOC entry 1328 (class 1259 OID 25802)
-- Dependencies: 4
-- Name: notificationtype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE notificationtype (
    notificationtypeid integer NOT NULL,
    name character varying(16)
);


ALTER TABLE public.notificationtype OWNER TO postgres;

--
-- TOC entry 1336 (class 1259 OID 66822)
-- Dependencies: 4
-- Name: pictures; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pictures (
    id integer NOT NULL,
    userid integer NOT NULL,
    image bytea NOT NULL
);


ALTER TABLE public.pictures OWNER TO postgres;

--
-- TOC entry 1338 (class 1259 OID 83236)
-- Dependencies: 1684 1685 1686 1687 1688 1689 1690 1691 1692 1693 1694 4
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
    preftimezone integer,
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
-- TOC entry 1344 (class 1259 OID 173443)
-- Dependencies: 4
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
-- TOC entry 1320 (class 1259 OID 25768)
-- Dependencies: 4
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
-- TOC entry 1333 (class 1259 OID 58632)
-- Dependencies: 4
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
-- TOC entry 1343 (class 1259 OID 157056)
-- Dependencies: 1699 4
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
-- TOC entry 1319 (class 1259 OID 25762)
-- Dependencies: 4
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
-- TOC entry 1324 (class 1259 OID 25785)
-- Dependencies: 4
-- Name: sitecontent; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sitecontent (
    siteid integer NOT NULL,
    contentid integer,
    content text
);


ALTER TABLE public.sitecontent OWNER TO postgres;

--
-- TOC entry 1325 (class 1259 OID 25790)
-- Dependencies: 4
-- Name: sitecontentsection; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sitecontentsection (
    contentid integer NOT NULL,
    name character varying(16)
);


ALTER TABLE public.sitecontentsection OWNER TO postgres;

--
-- TOC entry 1345 (class 1259 OID 214466)
-- Dependencies: 4
-- Name: skin; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE skin (
    skinid character varying(255) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.skin OWNER TO postgres;

--
-- TOC entry 1335 (class 1259 OID 58640)
-- Dependencies: 4
-- Name: subnationalcode; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE subnationalcode (
    subnatcodeid integer NOT NULL,
    islegal character varying(255),
    natcodeid integer,
    nationcode character varying(255),
    localabbrev character varying(255),
    nationname character varying(255)
);


ALTER TABLE public.subnationalcode OWNER TO postgres;

--
-- TOC entry 1341 (class 1259 OID 116032)
-- Dependencies: 1695 4
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
-- TOC entry 1334 (class 1259 OID 58636)
-- Dependencies: 4
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
-- TOC entry 1315 (class 1259 OID 25743)
-- Dependencies: 4
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
-- TOC entry 1314 (class 1259 OID 25741)
-- Dependencies: 1676 4
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
-- TOC entry 1316 (class 1259 OID 25745)
-- Dependencies: 4
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
-- TOC entry 1317 (class 1259 OID 25747)
-- Dependencies: 4
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
-- TOC entry 1796 (class 2606 OID 223063)
-- Dependencies: 1342 1342
-- Name: PK_AdminNotification_AdminNotificationID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY adminnotification
    ADD CONSTRAINT "PK_AdminNotification_AdminNotificationID" PRIMARY KEY (adminnotificationid);


--
-- TOC entry 1790 (class 2606 OID 223144)
-- Dependencies: 1339 1339
-- Name: PK_Admin_AdminId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "admin"
    ADD CONSTRAINT "PK_Admin_AdminId" PRIMARY KEY (adminid);


--
-- TOC entry 1780 (class 2606 OID 223037)
-- Dependencies: 1337 1337
-- Name: PK_FeedbackReputation_FeedbackReputationId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY feedbackreputation
    ADD CONSTRAINT "PK_FeedbackReputation_FeedbackReputationId" PRIMARY KEY (feedbackreputationid);


--
-- TOC entry 1754 (class 2606 OID 223049)
-- Dependencies: 1330 1330
-- Name: PK_Friends_FriendsId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "PK_Friends_FriendsId" PRIMARY KEY (friendsid);


--
-- TOC entry 1761 (class 2606 OID 214478)
-- Dependencies: 1332 1332
-- Name: PK_GameCategoryMaster_CategoryId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gamecategory
    ADD CONSTRAINT "PK_GameCategoryMaster_CategoryId" PRIMARY KEY (categoryid);


--
-- TOC entry 1759 (class 2606 OID 214453)
-- Dependencies: 1331 1331
-- Name: PK_GameMaster_Gameid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "PK_GameMaster_Gameid" PRIMARY KEY (gameid);


--
-- TOC entry 1739 (class 2606 OID 223095)
-- Dependencies: 1327 1327
-- Name: PK_Message_MessageId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "PK_Message_MessageId" PRIMARY KEY (messageid);


--
-- TOC entry 1722 (class 2606 OID 214460)
-- Dependencies: 1321 1321
-- Name: PK_NetworkMaster_Networkid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY network
    ADD CONSTRAINT "PK_NetworkMaster_Networkid" PRIMARY KEY (networkid);


--
-- TOC entry 1737 (class 2606 OID 223072)
-- Dependencies: 1326 1326
-- Name: PK_NotificationQueue_NotificationId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "PK_NotificationQueue_NotificationId" PRIMARY KEY (notificationid);


--
-- TOC entry 1743 (class 2606 OID 222663)
-- Dependencies: 1328 1328
-- Name: PK_NotificationType_NotificationTypeID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationtype
    ADD CONSTRAINT "PK_NotificationType_NotificationTypeID" PRIMARY KEY (notificationtypeid);


--
-- TOC entry 1720 (class 2606 OID 223006)
-- Dependencies: 1320 1320
-- Name: PK_PlayerComments_CommentSeq; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playercomments
    ADD CONSTRAINT "PK_PlayerComments_CommentSeq" PRIMARY KEY (commentseq);


--
-- TOC entry 1735 (class 2606 OID 222953)
-- Dependencies: 1325 1325
-- Name: PK_SiteContentSection_ContenetId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sitecontentsection
    ADD CONSTRAINT "PK_SiteContentSection_ContenetId" PRIMARY KEY (contentid);


--
-- TOC entry 1773 (class 2606 OID 223028)
-- Dependencies: 1335 1335
-- Name: PK_SubNationalCode_SubNatCodeId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subnationalcode
    ADD CONSTRAINT "PK_SubNationalCode_SubNatCodeId" PRIMARY KEY (subnatcodeid);


--
-- TOC entry 1701 (class 2606 OID 214499)
-- Dependencies: 1314 1314
-- Name: PK_TwoPlayerTournament_TournamentId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "PK_TwoPlayerTournament_TournamentId" PRIMARY KEY (tournamentid);


--
-- TOC entry 1733 (class 2606 OID 222951)
-- Dependencies: 1324 1324
-- Name: Pk_SiteContent_SiteId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sitecontent
    ADD CONSTRAINT "Pk_SiteContent_SiteId" PRIMARY KEY (siteid);


--
-- TOC entry 1707 (class 2606 OID 173471)
-- Dependencies: 1316 1316
-- Name: Pkey_CommentSeq; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournamentadmincomments
    ADD CONSTRAINT "Pkey_CommentSeq" PRIMARY KEY (commentseq);


--
-- TOC entry 1711 (class 2606 OID 173456)
-- Dependencies: 1318 1318
-- Name: Pkey_employeeid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT "Pkey_employeeid" PRIMARY KEY (employeeid);


--
-- TOC entry 1713 (class 2606 OID 223176)
-- Dependencies: 1318 1318
-- Name: UK_Employee_loginName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY employee
    ADD CONSTRAINT "UK_Employee_loginName" UNIQUE (loginname);


--
-- TOC entry 1763 (class 2606 OID 223181)
-- Dependencies: 1332 1332
-- Name: UK_GameCategory_Name; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gamecategory
    ADD CONSTRAINT "UK_GameCategory_Name" UNIQUE (name);


--
-- TOC entry 1741 (class 2606 OID 223186)
-- Dependencies: 1327 1327
-- Name: UK_Message_RandomId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "UK_Message_RandomId" UNIQUE (randomid);


--
-- TOC entry 1724 (class 2606 OID 223188)
-- Dependencies: 1321 1321
-- Name: UK_Network_NetworkName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY network
    ADD CONSTRAINT "UK_Network_NetworkName" UNIQUE (networkname);


--
-- TOC entry 1745 (class 2606 OID 223190)
-- Dependencies: 1328 1328
-- Name: UK_NotificationType_Name; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY notificationtype
    ADD CONSTRAINT "UK_NotificationType_Name" UNIQUE (name);


--
-- TOC entry 1765 (class 2606 OID 223223)
-- Dependencies: 1333 1333 1333
-- Name: UK_PlayerGame_PlayerId_GameId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT "UK_PlayerGame_PlayerId_GameId" UNIQUE (playerid, gameid);


--
-- TOC entry 1715 (class 2606 OID 223004)
-- Dependencies: 1319 1319 1319
-- Name: UK_PlayerNetwork_Network_PlayerID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT "UK_PlayerNetwork_Network_PlayerID" UNIQUE (networkid, playerid);


--
-- TOC entry 1782 (class 2606 OID 222659)
-- Dependencies: 1338 1338
-- Name: UK_Player_EmailAddress; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "UK_Player_EmailAddress" UNIQUE (emailaddress);


--
-- TOC entry 1784 (class 2606 OID 222661)
-- Dependencies: 1338 1338
-- Name: UK_Player_ScreenName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "UK_Player_ScreenName" UNIQUE (screenname);


--
-- TOC entry 1775 (class 2606 OID 223035)
-- Dependencies: 1335 1335 1335
-- Name: UK_SubNationalCode_NatCode_NationCode; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subnationalcode
    ADD CONSTRAINT "UK_SubNationalCode_NatCode_NationCode" UNIQUE (natcodeid, nationcode);


--
-- TOC entry 1769 (class 2606 OID 223245)
-- Dependencies: 1334 1334 1334
-- Name: UK_TwoPlayerMatch_MatchID_TournamentId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "UK_TwoPlayerMatch_MatchID_TournamentId" UNIQUE (matchid, tournamentid);


--
-- TOC entry 1703 (class 2606 OID 223252)
-- Dependencies: 1314 1314 1314
-- Name: UK_TwoPlayerTournament_TournamentId_GameId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "UK_TwoPlayerTournament_TournamentId_GameId" UNIQUE (tournamentid, gameid);


--
-- TOC entry 1756 (class 2606 OID 223061)
-- Dependencies: 1330 1330 1330
-- Name: UQ_Friends_PlayerId1_PlayerId2; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "UQ_Friends_PlayerId1_PlayerId2" UNIQUE (playerid1, playerid2);


--
-- TOC entry 1802 (class 2606 OID 214474)
-- Dependencies: 1345 1345
-- Name: UniqueSkinId; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY skin
    ADD CONSTRAINT "UniqueSkinId" UNIQUE (skinid);


--
-- TOC entry 1731 (class 2606 OID 214447)
-- Dependencies: 1323 1323
-- Name: avatars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY avatars
    ADD CONSTRAINT avatars_pkey PRIMARY KEY (avatarid);


--
-- TOC entry 1792 (class 2606 OID 107801)
-- Dependencies: 1340 1340 1340 1340
-- Name: gameoptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gameoptions
    ADD CONSTRAINT gameoptions_pkey PRIMARY KEY (gameid, optionid, valueid);


--
-- TOC entry 1726 (class 2606 OID 222637)
-- Dependencies: 1322 1322
-- Name: imnetwork_imnetworkname_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imnetwork
    ADD CONSTRAINT imnetwork_imnetworkname_key UNIQUE (imnetworkname);


--
-- TOC entry 1728 (class 2606 OID 173425)
-- Dependencies: 1322 1322
-- Name: imnetwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY imnetwork
    ADD CONSTRAINT imnetwork_pkey PRIMARY KEY (imnetworkid);


--
-- TOC entry 1748 (class 2606 OID 58605)
-- Dependencies: 1329 1329
-- Name: nationalcode_nationcode_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_nationcode_key UNIQUE (nationcode);


--
-- TOC entry 1750 (class 2606 OID 214497)
-- Dependencies: 1329 1329 1329
-- Name: nationalcode_nationname_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_nationname_key UNIQUE (nationname, nationcode);


--
-- TOC entry 1752 (class 2606 OID 58603)
-- Dependencies: 1329 1329
-- Name: nationalcode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nationalcode
    ADD CONSTRAINT nationalcode_pkey PRIMARY KEY (natcodeid);


--
-- TOC entry 1778 (class 2606 OID 223146)
-- Dependencies: 1336 1336
-- Name: pictures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pictures
    ADD CONSTRAINT pictures_pkey PRIMARY KEY (id);


--
-- TOC entry 1786 (class 2606 OID 83242)
-- Dependencies: 1338 1338
-- Name: player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (playerid);


--
-- TOC entry 1800 (class 2606 OID 173469)
-- Dependencies: 1344 1344
-- Name: playeradmincomments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playeradmincomments
    ADD CONSTRAINT playeradmincomments_pkey PRIMARY KEY (id);


--
-- TOC entry 1767 (class 2606 OID 58635)
-- Dependencies: 1333 1333
-- Name: playergame_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT playergame_pkey PRIMARY KEY (playergameid);


--
-- TOC entry 1798 (class 2606 OID 157059)
-- Dependencies: 1343 1343
-- Name: playerim_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playerim
    ADD CONSTRAINT playerim_pkey PRIMARY KEY (imid);


--
-- TOC entry 1717 (class 2606 OID 83203)
-- Dependencies: 1319 1319
-- Name: playernetwork_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT playernetwork_pkey PRIMARY KEY (playernetworkid);


--
-- TOC entry 1804 (class 2606 OID 214476)
-- Dependencies: 1345 1345
-- Name: skin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY skin
    ADD CONSTRAINT skin_pkey PRIMARY KEY (id);


--
-- TOC entry 1794 (class 2606 OID 116036)
-- Dependencies: 1341 1341
-- Name: timezones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY timezones
    ADD CONSTRAINT timezones_pkey PRIMARY KEY (id);


--
-- TOC entry 1771 (class 2606 OID 58639)
-- Dependencies: 1334 1334
-- Name: twoplayermatch_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT twoplayermatch_pkey PRIMARY KEY (matchid);


--
-- TOC entry 1705 (class 2606 OID 124230)
-- Dependencies: 1315 1315
-- Name: twoplayermatchcomments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayermatchcomments
    ADD CONSTRAINT twoplayermatchcomments_pkey PRIMARY KEY (tournamentcommentsid);


--
-- TOC entry 1709 (class 2606 OID 99609)
-- Dependencies: 1317 1317
-- Name: twoplayertournamentgameoptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY twoplayertournamentgameoptions
    ADD CONSTRAINT twoplayertournamentgameoptions_pkey PRIMARY KEY (twoplayertournamentgameoptionsid);


--
-- TOC entry 1729 (class 1259 OID 223167)
-- Dependencies: 1322
-- Name: index_imnetwork_imnetworkname_key; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX index_imnetwork_imnetworkname_key ON imnetwork USING btree (imnetworkname);


--
-- TOC entry 1746 (class 1259 OID 223168)
-- Dependencies: 1329
-- Name: index_nationalcode_nationcode_key; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX index_nationalcode_nationcode_key ON nationalcode USING btree (nationcode);


--
-- TOC entry 1787 (class 1259 OID 223166)
-- Dependencies: 1338
-- Name: uk_player_emailaddress; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_player_emailaddress ON player USING btree (emailaddress);


--
-- TOC entry 1788 (class 1259 OID 223169)
-- Dependencies: 1338
-- Name: uk_player_screenname; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_player_screenname ON player USING btree (screenname);


--
-- TOC entry 1718 (class 1259 OID 223170)
-- Dependencies: 1319 1319
-- Name: uk_playernetwork_network_playerid; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_playernetwork_network_playerid ON playernetwork USING btree (networkid, playerid);


--
-- TOC entry 1776 (class 1259 OID 223171)
-- Dependencies: 1335 1335
-- Name: uk_subnationalcode_natcode_nationcode; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uk_subnationalcode_natcode_nationcode ON subnationalcode USING btree (natcodeid, nationcode);


--
-- TOC entry 1757 (class 1259 OID 223165)
-- Dependencies: 1330 1330
-- Name: uq_friends_playerid1_playerid2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX uq_friends_playerid1_playerid2 ON friends USING btree (playerid1, playerid2);


--
-- TOC entry 1807 (class 2606 OID 223278)
-- Dependencies: 1318 1314 1710
-- Name: FK_Employee_TwoPlayerTournament_EmployeeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "FK_Employee_TwoPlayerTournament_EmployeeId" FOREIGN KEY (createdby) REFERENCES employee(employeeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1828 (class 2606 OID 214484)
-- Dependencies: 1331 1760 1332
-- Name: FK_GameCategoryMaster_GameMaster_CategoryId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "FK_GameCategoryMaster_GameMaster_CategoryId" FOREIGN KEY (categoryid) REFERENCES gamecategory(categoryid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1843 (class 2606 OID 214479)
-- Dependencies: 1340 1331 1758
-- Name: FK_GameMaster_GameOptionMaster_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gameoptions
    ADD CONSTRAINT "FK_GameMaster_GameOptionMaster_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1830 (class 2606 OID 223022)
-- Dependencies: 1758 1333 1331
-- Name: FK_Game_PlayerGame_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT "FK_Game_PlayerGame_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid);


--
-- TOC entry 1805 (class 2606 OID 214500)
-- Dependencies: 1758 1314 1331
-- Name: FK_Game_TwoPlayerTournament_GameId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "FK_Game_TwoPlayerTournament_GameId" FOREIGN KEY (gameid) REFERENCES game(gameid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1822 (class 2606 OID 223147)
-- Dependencies: 1328 1742 1327
-- Name: FK_Message_NotificationType_NotificationTypeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Message_NotificationType_NotificationTypeId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1820 (class 2606 OID 223131)
-- Dependencies: 1785 1338 1327
-- Name: FK_Message_Player_FromPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Message_Player_FromPlayerId" FOREIGN KEY (fromplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1824 (class 2606 OID 223299)
-- Dependencies: 1770 1327 1334
-- Name: FK_Message_TwoPlayerMatch_MatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Message_TwoPlayerMatch_MatchId" FOREIGN KEY (matchid) REFERENCES twoplayermatch(matchid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1836 (class 2606 OID 223029)
-- Dependencies: 1329 1335 1751
-- Name: FK_NationalCode_SubNationalCode_NatCodeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subnationalcode
    ADD CONSTRAINT "FK_NationalCode_SubNationalCode_NatCodeId" FOREIGN KEY (natcodeid) REFERENCES nationalcode(natcodeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1827 (class 2606 OID 214461)
-- Dependencies: 1321 1721 1331
-- Name: FK_NetworkMaster_GameMaster_NetworkId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY game
    ADD CONSTRAINT "FK_NetworkMaster_GameMaster_NetworkId" FOREIGN KEY (networkid) REFERENCES network(networkid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1811 (class 2606 OID 222993)
-- Dependencies: 1321 1319 1721
-- Name: FK_NetworkMaster_PlayerNetwork_NetworkId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT "FK_NetworkMaster_PlayerNetwork_NetworkId" FOREIGN KEY (networkid) REFERENCES network(networkid);


--
-- TOC entry 1844 (class 2606 OID 223064)
-- Dependencies: 1742 1342 1328
-- Name: FK_NotificationType_AdminNotification_NotificationId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY adminnotification
    ADD CONSTRAINT "FK_NotificationType_AdminNotification_NotificationId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1816 (class 2606 OID 223073)
-- Dependencies: 1326 1742 1328
-- Name: FK_NotificationType_NotificationQueue_NotificationTypeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "FK_NotificationType_NotificationQueue_NotificationTypeId" FOREIGN KEY (notificationtypeid) REFERENCES notificationtype(notificationtypeid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1837 (class 2606 OID 223191)
-- Dependencies: 1338 1336 1785
-- Name: FK_Pictures_UserId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pictures
    ADD CONSTRAINT "FK_Pictures_UserId" FOREIGN KEY (userid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1840 (class 2606 OID 222643)
-- Dependencies: 1730 1323 1338
-- Name: FK_Player_Avatars_AvatarId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "FK_Player_Avatars_AvatarId" FOREIGN KEY (avatarid) REFERENCES avatars(avatarid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1838 (class 2606 OID 223038)
-- Dependencies: 1338 1337 1785
-- Name: FK_Player_FeedbackReputation_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY feedbackreputation
    ADD CONSTRAINT "FK_Player_FeedbackReputation_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1825 (class 2606 OID 223050)
-- Dependencies: 1330 1338 1785
-- Name: FK_Player_Friends_PlayerId1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "FK_Player_Friends_PlayerId1" FOREIGN KEY (playerid1) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1826 (class 2606 OID 223055)
-- Dependencies: 1785 1330 1338
-- Name: FK_Player_Friends_PlayerId2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY friends
    ADD CONSTRAINT "FK_Player_Friends_PlayerId2" FOREIGN KEY (playerid2) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1819 (class 2606 OID 223126)
-- Dependencies: 1785 1338 1327
-- Name: FK_Player_Message_ToPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Player_Message_ToPlayerId" FOREIGN KEY (toplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1817 (class 2606 OID 223078)
-- Dependencies: 1326 1785 1338
-- Name: FK_Player_NotificationQueue_RecipientPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "FK_Player_NotificationQueue_RecipientPlayerId" FOREIGN KEY (recipientplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1818 (class 2606 OID 223083)
-- Dependencies: 1326 1785 1338
-- Name: FK_Player_NotifictionQueue_SourcePlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY notificationqueue
    ADD CONSTRAINT "FK_Player_NotifictionQueue_SourcePlayerId" FOREIGN KEY (sourceplayerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1813 (class 2606 OID 223007)
-- Dependencies: 1785 1320 1338
-- Name: FK_Player_PlayerComments_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playercomments
    ADD CONSTRAINT "FK_Player_PlayerComments_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1814 (class 2606 OID 223012)
-- Dependencies: 1338 1320 1785
-- Name: FK_Player_PlayerComments_TargetPlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playercomments
    ADD CONSTRAINT "FK_Player_PlayerComments_TargetPlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1829 (class 2606 OID 223017)
-- Dependencies: 1338 1333 1785
-- Name: FK_Player_PlayerGame_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playergame
    ADD CONSTRAINT "FK_Player_PlayerGame_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1812 (class 2606 OID 222998)
-- Dependencies: 1338 1785 1319
-- Name: FK_Player_PlayerNetwork_PlayerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playernetwork
    ADD CONSTRAINT "FK_Player_PlayerNetwork_PlayerId" FOREIGN KEY (playerid) REFERENCES player(playerid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1841 (class 2606 OID 222648)
-- Dependencies: 1801 1338 1345
-- Name: FK_Player_Skin_skinID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "FK_Player_Skin_skinID" FOREIGN KEY (skinid) REFERENCES skin(skinid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1842 (class 2606 OID 222653)
-- Dependencies: 1793 1338 1341
-- Name: FK_Player_Timezone_Timezoneid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT "FK_Player_Timezone_Timezoneid" FOREIGN KEY (preftimezone) REFERENCES timezones(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1831 (class 2606 OID 223224)
-- Dependencies: 1785 1334 1338
-- Name: FK_Player_TwoPlayerMatch_PlayerOneId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_Player_TwoPlayerMatch_PlayerOneId" FOREIGN KEY (playeroneid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1832 (class 2606 OID 223229)
-- Dependencies: 1338 1785 1334
-- Name: FK_Player_TwoPlayerMatch_PlayerTwoId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_Player_TwoPlayerMatch_PlayerTwoId" FOREIGN KEY (playertwoid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1815 (class 2606 OID 222954)
-- Dependencies: 1324 1325 1732
-- Name: FK_SiteContentSection_SiteContent_ContentId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sitecontentsection
    ADD CONSTRAINT "FK_SiteContentSection_SiteContent_ContentId" FOREIGN KEY (contentid) REFERENCES sitecontent(siteid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1821 (class 2606 OID 223136)
-- Dependencies: 1801 1327 1345
-- Name: FK_Skin_Message_SiteID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "FK_Skin_Message_SiteID" FOREIGN KEY (siteid) REFERENCES skin(skinid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1806 (class 2606 OID 214514)
-- Dependencies: 1314 1801 1345
-- Name: FK_Skin_TwoPlayerTournament_SkinId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournament
    ADD CONSTRAINT "FK_Skin_TwoPlayerTournament_SkinId" FOREIGN KEY (skinid) REFERENCES skin(skinid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1839 (class 2606 OID 223043)
-- Dependencies: 1334 1337 1770
-- Name: FK_TwoPlayerMatch_FeedbackReputation_MatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY feedbackreputation
    ADD CONSTRAINT "FK_TwoPlayerMatch_FeedbackReputation_MatchId" FOREIGN KEY (matchid) REFERENCES twoplayermatch(matchid) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1835 (class 2606 OID 223246)
-- Dependencies: 1770 1334 1334
-- Name: FK_TwoPlayerMatch_NextMatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_TwoPlayerMatch_NextMatchId" FOREIGN KEY (nextmatchid) REFERENCES twoplayermatch(matchid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1808 (class 2606 OID 223258)
-- Dependencies: 1316 1318 1710
-- Name: FK_TwoPlayerTournamentAdminComments_Employee_EmployeeId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournamentadmincomments
    ADD CONSTRAINT "FK_TwoPlayerTournamentAdminComments_Employee_EmployeeId" FOREIGN KEY (employeeid) REFERENCES employee(employeeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1809 (class 2606 OID 223263)
-- Dependencies: 1700 1316 1314
-- Name: FK_TwoPlayerTournamentAdminComments_TwoPlayerTournament_Tournam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournamentadmincomments
    ADD CONSTRAINT "FK_TwoPlayerTournamentAdminComments_TwoPlayerTournament_Tournam" FOREIGN KEY (tournamentid) REFERENCES twoplayertournament(tournamentid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1810 (class 2606 OID 223253)
-- Dependencies: 1317 1700 1314
-- Name: FK_TwoPlayerTournamentGameOptions_TwoPlayerTournament_Tournamen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayertournamentgameoptions
    ADD CONSTRAINT "FK_TwoPlayerTournamentGameOptions_TwoPlayerTournament_Tournamen" FOREIGN KEY (tournamentid) REFERENCES twoplayertournament(tournamentid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1834 (class 2606 OID 223239)
-- Dependencies: 1334 1338 1785
-- Name: FK_TwoPlayerTournament_Player_WinnerId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_TwoPlayerTournament_Player_WinnerId" FOREIGN KEY (winnerid) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1833 (class 2606 OID 223234)
-- Dependencies: 1334 1314 1700
-- Name: FK_TwoPlayerTournament_TwoPlayerMatch_TournamentId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY twoplayermatch
    ADD CONSTRAINT "FK_TwoPlayerTournament_TwoPlayerMatch_TournamentId" FOREIGN KEY (tournamentid) REFERENCES twoplayertournament(tournamentid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1823 (class 2606 OID 223294)
-- Dependencies: 1334 1327 1770
-- Name: K_Message_TwoPlayerMatch_MatchId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT "K_Message_TwoPlayerMatch_MatchId" FOREIGN KEY (matchid) REFERENCES twoplayermatch(matchid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1845 (class 2606 OID 157060)
-- Dependencies: 1785 1343 1338
-- Name: fk700373c5c919dcca; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playerim
    ADD CONSTRAINT fk700373c5c919dcca FOREIGN KEY (playerid) REFERENCES player(playerid);


--
-- TOC entry 1848 (class 2606 OID 173457)
-- Dependencies: 1710 1318 1344
-- Name: playeradmincomments_employeeid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playeradmincomments
    ADD CONSTRAINT playeradmincomments_employeeid FOREIGN KEY (employeeid) REFERENCES employee(employeeid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1847 (class 2606 OID 173450)
-- Dependencies: 1338 1344 1785
-- Name: playeradmincomments_player_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playeradmincomments
    ADD CONSTRAINT playeradmincomments_player_fkey FOREIGN KEY (player) REFERENCES player(playerid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1846 (class 2606 OID 173426)
-- Dependencies: 1727 1322 1343
-- Name: playerim_imnetworkid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY playerim
    ADD CONSTRAINT playerim_imnetworkid FOREIGN KEY (imnetworkid) REFERENCES imnetwork(imnetworkid) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1853 (class 0 OID 0)
-- Dependencies: 4
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2008-05-14 12:21:21

--
-- PostgreSQL database dump complete
--

