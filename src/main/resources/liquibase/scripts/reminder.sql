-- liquibase formatted sql

    --changeset mmaxim:create_table_reminders
    CREATE TABLE reminders(
        id SERIAL PRIMARY KEY,
        message TEXT,
        dateAndTime TEXT

    )