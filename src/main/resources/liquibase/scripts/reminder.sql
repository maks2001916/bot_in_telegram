-- liquibase formatted sql

    --changeset mmaxim:create_table_reminders
    CREATE TABLE notifications(
        id          SERIAL PRIMARY KEY,
        chatId      SERIAL PRIMARY KEY,
        message     TEXT,
        dateAndTime TEXT

    )