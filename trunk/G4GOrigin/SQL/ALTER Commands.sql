ALTER TABLE player ALTER COLUMN creationdate SET DEFAULT CURRENT_TIMESTAMP AT TIME ZONE 'UTC';
ALTER TABLE player ALTER COLUMN creationdate SET STATISTICS -1;

UPDATE player SET creationdate=CURRENT_TIMESTAMP AT TIME ZONE 'UTC' WHERE creationdate is null;

// 24-5-2008

DELETE FROM message;

ALTER TABLE message
   ALTER COLUMN createddate SET DEFAULT CURRENT_TIMESTAMP AT TIME ZONE 'UTC';
ALTER TABLE message ALTER COLUMN createddate SET STATISTICS -1;

CREATE SEQUENCE message_messageid_seq;
ALTER SEQUENCE message_messageid_seq OWNED BY message.messageid;

ALTER TABLE message ALTER COLUMN messageid SET DEFAULT nextval('message_messageid_seq');

// 25-5-2008
ALTER TABLE feedbackreputation DROP COLUMN feedbackdate;
ALTER TABLE feedbackreputation ADD COLUMN feedbackdate timestamp with time zone DEFAULT CURRENT_TIMESTAMP;


//29-5-2008
ALTER TABLE playercomments ALTER "comment" TYPE text;
ALTER TABLE playercomments ALTER COLUMN "comment" SET STATISTICS -1;
