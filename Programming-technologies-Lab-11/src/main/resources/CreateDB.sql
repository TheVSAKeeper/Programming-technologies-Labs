-- CREATE DATABASE surveys;
--
-- CREATE TABLESPACE ts LOCATION 'C:/Downloads/Test/db';
-- CREATE DATABASE surveys TABLESPACE ts;
--
-- \c surveys
--
-- CREATE SCHEMA catalog;

SET SEARCH_PATH TO catalog;

CREATE TABLE role
(
    role_id SMALLSERIAL NOT NULL PRIMARY KEY,
    name    VARCHAR(20) NOT NULL UNIQUE DEFAULT 'Unknown'
);

CREATE TABLE "user"
(
    user_id         SERIAL        NOT NULL PRIMARY KEY,
    login           VARCHAR(20)   NOT NULL UNIQUE,
    hashed_password VARCHAR(255)  NOT NULL,
    salt            VARCHAR(1024) NOT NULL,
    last_name       VARCHAR(20)   NOT NULL,
    first_name      VARCHAR(20)   NOT NULL,
    patronymic      VARCHAR(20)   NULL,
    role_id         SMALLINT      NOT NULL,

    CONSTRAINT user_role_id_fk FOREIGN KEY (role_id) REFERENCES role (role_id)
        ON DELETE SET DEFAULT
        ON UPDATE CASCADE
);

CREATE TYPE GENDER AS ENUM ('Ì', 'Æ');

CREATE TABLE patient
(
    patient_id SERIAL      NOT NULL PRIMARY KEY,
    last_name  VARCHAR(20) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    patronymic VARCHAR(20) NULL,
    gender     GENDER      NOT NULL,
    birth_date DATE        NOT NULL,

    CONSTRAINT birth_date_check CHECK (birth_date < current_date)
);

CREATE TABLE anamnesis_type
(
    anamnesis_type_id SMALLSERIAL NOT NULL PRIMARY KEY,
    name              VARCHAR(40) NOT NULL UNIQUE,
    description       TEXT        NULL
);

CREATE TABLE anamnesis_template
(
    anamnesis_template_id SMALLSERIAL NOT NULL PRIMARY KEY,
    anamnesis_type_id     SMALLINT    NOT NULL,

    CONSTRAINT anamnesis_type_id_fk FOREIGN KEY (anamnesis_type_id) REFERENCES anamnesis_type (anamnesis_type_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE diagnosis
(
    diagnosis_id SERIAL      NOT NULL PRIMARY KEY,
    diagnosis    VARCHAR(40) NOT NULL UNIQUE,
    description  TEXT        NULL
);

CREATE TABLE survey
(
    survey_id      SERIAL                   NOT NULL PRIMARY KEY,
    complaint      TEXT                     NOT NULL,
    date_of_survey TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
    patient_id     INT                      NOT NULL,
    user_id        INT                      NOT NULL,

    CONSTRAINT date_of_survey_check CHECK (date_of_survey <= current_timestamp),

    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user" (user_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    CONSTRAINT patient_id_fk FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE survey_diagnosis
(
    survey_diagnosis_id SERIAL                   NOT NULL PRIMARY KEY,
    survey_id           INT                      NOT NULL,
    diagnosis_id        INT                      NOT NULL,
    patient_id          INT                      NOT NULL,
    user_id             INT                      NOT NULL,
    reason              TEXT                     NOT NULL,
    date_of_diagnosis   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,

    UNIQUE (survey_id, diagnosis_id),
    CONSTRAINT date_of_diagnosis_check CHECK (date_of_diagnosis <= current_timestamp),

    CONSTRAINT diagnosis_id_fk FOREIGN KEY (diagnosis_id) REFERENCES diagnosis (diagnosis_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT survey_id_fk FOREIGN KEY (survey_id) REFERENCES survey (survey_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT patient_id_fk FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user" (user_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);


CREATE TABLE anamnesis
(
    anamnesis_id          SERIAL   NOT NULL PRIMARY KEY,
    anamnesis_template_id SMALLINT NOT NULL,
    survey_id             INT      NOT NULL,

    CONSTRAINT anamnesis_template_id_fk FOREIGN KEY (anamnesis_template_id) REFERENCES anamnesis_template (anamnesis_template_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT survey_id_fk FOREIGN KEY (survey_id) REFERENCES survey (survey_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE question
(
    question_id           SERIAL   NOT NULL PRIMARY KEY,
    question              TEXT     NOT NULL,
    anamnesis_template_id SMALLINT NOT NULL,

    CONSTRAINT anamnesis_template_id_fk FOREIGN KEY (anamnesis_template_id) REFERENCES anamnesis_template (anamnesis_template_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE answer
(
    answer_id    SERIAL NOT NULL PRIMARY KEY,
    answer       TEXT   NOT NULL,
    anamnesis_id INT    NOT NULL,
    question_id  INT    NOT NULL,

    CONSTRAINT anamnesis_id_fk FOREIGN KEY (anamnesis_id) REFERENCES anamnesis (anamnesis_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT question_id_fk FOREIGN KEY (question_id) REFERENCES question (question_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);