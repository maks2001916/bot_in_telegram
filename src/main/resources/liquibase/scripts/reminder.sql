- liquibase formatted sql

    -changeset mmaxim:1
    CREATE TABLE reminders(
        id SERIAL PRIMARY KEY,
        message TEXT,
        dateAndTime TEXT

    )