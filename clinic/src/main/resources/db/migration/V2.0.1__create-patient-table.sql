CREATE TABLE PATIENT (
    ID              BIGSERIAL          PRIMARY KEY,
    FIRST_NAME      VARCHAR(64)        NOT NULL,
    LAST_NAME       VARCHAR(64)        NOT NULL,
    GENDER          VARCHAR(6)         NOT NULL,
    PESEL           VARCHAR(11)        UNIQUE NOT NULL,
    PHONE_NUMBER    VARCHAR(9)         NOT NULL,
    BIRTH_DATE      DATE               NOT NULL
);