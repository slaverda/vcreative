SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;

CREATE TABLE public.posts (
    uid text PRIMARY KEY NOT NULL,
    text text NOT NULL
);

CREATE TABLE public.votes (
    uid text NOT NULL,
    vote boolean NOT NULL,
    postUID text NOT NULL,
    FOREIGN KEY (postUID) REFERENCES public.posts (uid)
);

INSERT INTO public.posts (uid, text) VALUES
('b0b00f54-8451-4815-a393-b88a621ae4bc', 'The text');
INSERT INTO public.votes (uid, vote, postUID) VALUES
('862d3ed8-5cd8-484f-90d4-eeebeafef13c', true, 'b0b00f54-8451-4815-a393-b88a621ae4bc');