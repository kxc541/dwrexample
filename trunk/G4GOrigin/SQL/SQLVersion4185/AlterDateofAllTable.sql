ALTER TABLE friends ALTER player1accepted TYPE timestamp with time zone;
ALTER TABLE friends ALTER player2accepted TYPE timestamp with time zone;
ALTER TABLE friends ALTER COLUMN player1accepted SET STATISTICS -1;
ALTER TABLE friends ALTER COLUMN player2accepted SET STATISTICS -1;

ALTER TABLE message ALTER createddate TYPE timestamp with time zone;
ALTER TABLE message ALTER COLUMN createddate SET STATISTICS -1;

ALTER TABLE player ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE player ALTER lastupdatedate TYPE timestamp with time zone;
ALTER TABLE player ALTER lastlogintime TYPE timestamp with time zone;
ALTER TABLE player ALTER COLUMN creationdate SET STATISTICS -1;
ALTER TABLE player ALTER COLUMN lastupdatedate SET STATISTICS -1;
ALTER TABLE player ALTER COLUMN lastlogintime SET STATISTICS -1;

ALTER TABLE playeradmincomments ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE playeradmincomments ALTER COLUMN creationdate SET STATISTICS -1;


ALTER TABLE playercomments ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE playercomments ALTER COLUMN creationdate SET STATISTICS -1;


ALTER TABLE playergame ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE playergame ALTER lastupdatedate TYPE timestamp with time zone;
ALTER TABLE playergame ALTER COLUMN creationdate SET STATISTICS -1;
ALTER TABLE playergame ALTER COLUMN lastupdatedate SET STATISTICS -1;

ALTER TABLE playerim ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE playerim ALTER lastupdatedate TYPE timestamp with time zone;
ALTER TABLE playerim ALTER COLUMN creationdate SET STATISTICS -1;
ALTER TABLE playerim ALTER COLUMN lastupdatedate SET STATISTICS -1;

ALTER TABLE playernetwork ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE playernetwork ALTER lastupdatedate TYPE timestamp with time zone;
ALTER TABLE playernetwork ALTER COLUMN creationdate SET STATISTICS -1;
ALTER TABLE playernetwork ALTER COLUMN lastupdatedate SET STATISTICS -1;


ALTER TABLE twoplayermatch ALTER playeroneaccepteddate TYPE timestamp with time zone;
ALTER TABLE twoplayermatch ALTER playertwoaccepteddate TYPE timestamp with time zone;
ALTER TABLE twoplayermatch ALTER scheduledstartdate TYPE timestamp with time zone;
ALTER TABLE twoplayermatch ALTER completeddate TYPE timestamp with time zone;
ALTER TABLE twoplayermatch ALTER enddate TYPE timestamp with time zone;
ALTER TABLE twoplayermatch ALTER COLUMN playeroneaccepteddate SET STATISTICS -1;
ALTER TABLE twoplayermatch ALTER COLUMN playertwoaccepteddate SET STATISTICS -1;
ALTER TABLE twoplayermatch ALTER COLUMN scheduledstartdate SET STATISTICS -1;
ALTER TABLE twoplayermatch ALTER COLUMN completeddate SET STATISTICS -1;
ALTER TABLE twoplayermatch ALTER COLUMN enddate SET STATISTICS -1;


ALTER TABLE twoplayermatchcomments ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE twoplayermatchcomments ALTER COLUMN creationdate SET STATISTICS -1;


ALTER TABLE twoplayertournamentadmincomments ALTER creationdate TYPE timestamp with time zone;
ALTER TABLE twoplayertournamentadmincomments ALTER COLUMN creationdate SET STATISTICS -1;