-- liquibase formatted sql

    --changeset mmaxim:create_table_reminders
    CREATE TABLE reminders(
        id SERIAL PRIMARY KEY,
        chatId INTEGER,
        message TEXT,
        dateAndTime TEXT

    )