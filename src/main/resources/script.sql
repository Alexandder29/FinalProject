CREATE TABLE public.travelhistory
(
    id bigserial,
    destination text,
    description text,
    season text,
    seat_class text,
    cost bigint,
    visited boolean,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.travelhistory
    OWNER to traveler;