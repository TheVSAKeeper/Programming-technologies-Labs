DROP TABLE IF EXISTS public.progress CASCADE;
DROP TABLE IF EXISTS public.student CASCADE;
DROP TABLE IF EXISTS public.subject CASCADE;

CREATE TABLE student
(
    student_id      SERIAL NOT NULL PRIMARY KEY,
    name            TEXT,
    passport_series VARCHAR(4),
    passport_number VARCHAR(6)
);

CREATE TABLE subject
(
    subject_id SERIAL NOT NULL PRIMARY KEY,
    name       TEXT
);

CREATE TABLE progress
(
    id         SERIAL NOT NULL PRIMARY KEY,
    mark       INT,
    student_id INT,
    subject_id INT,

    CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student (student_id)
        ON DELETE CASCADE,
    CONSTRAINT subject_id_fk FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
        ON DELETE CASCADE
);

ALTER TABLE student
    ADD CONSTRAINT series_number_unique UNIQUE (passport_series, passport_number);

ALTER TABLE progress
    ADD CONSTRAINT mark_check CHECK (mark BETWEEN 2 AND 5);

INSERT INTO student (name, passport_series, passport_number)
VALUES ('Василиса', '4631', '516097'),
       ('Антон', '4147', '126650'),
       ('Ефим', '4130', '157955'),
       ('Максим', '4489', '913047');

INSERT INTO subject (name)
VALUES ('Математика'),
       ('Технологии программирования'),
       ('Алгоритмы и структуры данных'),
       ('Управление данными');

INSERT INTO progress (mark, student_id, subject_id)
VALUES (5, 1, 1),
       (2, 1, 4),
       (3, 1, 3),
       (5, 2, 1),
       (2, 2, 3),
       (2, 3, 4),
       (4, 4, 1),
       (5, 1, 4);

SELECT s.name, sb.name, p.mark
FROM progress p
         JOIN student s ON s.student_id = p.student_id
         JOIN subject sb ON sb.subject_id = p.subject_id
WHERE sb.name = 'Математика' AND p.mark > 3;

SELECT avg(mark)
FROM progress p
         JOIN subject sb ON sb.subject_id = p.subject_id
WHERE sb.name = 'Управление данными';

SELECT avg(mark)
FROM progress p
         JOIN subject sb ON sb.subject_id = p.subject_id
         JOIN student s ON s.student_id = p.student_id
WHERE s.name = 'Василиса';

SELECT sb.name, count(*)
FROM progress p
         JOIN subject sb ON sb.subject_id = p.subject_id
WHERE mark >= 3
GROUP BY sb.name
ORDER BY count(*) DESC
LIMIT 3;

DELETE
FROM subject
WHERE name = 'Математика';

INSERT INTO progress (mark, student_id, subject_id)
VALUES (5, 4, 2),
       (5, 4, 3),
       (5, 4, 4);

SELECT DISTINCT s.name
FROM progress p
         JOIN student s ON s.student_id = p.student_id
WHERE NOT exists(SELECT *
                 FROM progress p2
                 WHERE p2.student_id = p.student_id AND p2.mark <= 3);