CREATE TABLE IF NOT EXISTS STUDENT(
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(64) NOT NULL,
    AGE INTEGER NOT NULL,
    GRADES VARCHAR(64)
);