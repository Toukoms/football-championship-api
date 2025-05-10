CREATE DATABASE foot_central;


CREATE TYPE championship_type AS ENUM ('PREMIER_LEAGUE', 'LA_LIGA', 'BUNDESLIGA', 'SERIA', 'LIGUE_1');


CREATE TABLE coach (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    nationality VARCHAR(56),
    created_at INTEGER NOT NULL
);

CREATE TABLE club (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE,
    acronym VARCHAR(3) NOT NULL,
    year_creation INTEGER,
    stadium VARCHAR(128),
    created_at INTEGER NOT NULL,
    coach_id UUID REFERENCES coach(id) ON DELETE SET NULL
);

-- PLAYER
CREATE TABLE player (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    number BIGINT,
    position VARCHAR(16) CHECK (position IN ('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER')),
    nationality VARCHAR(50),
    age INT CHECK (age BETWEEN 0 AND 80),
    current_club_id UUID REFERENCES club(id) ON DELETE SET NULL
);

CREATE TABLE season (
    id UUID PRIMARY KEY,
    year INTEGER NOT NULL,
    alias VARCHAR(24) NOT NULL,
    status VARCHAR(16) NOT NULL CHECK (status IN ('NOT_STARTED', 'STARTED', 'FINISHED')),
    created_at INTEGER NOT NULL

);

CREATE TABLE player_ranking (
    player_id UUID NOT NULL REFERENCES player(id),
    championship championship_type,
    scored_goals INTEGER DEFAULT 0,
    playing_time_value NUMERIC,
    playing_time_unit VARCHAR(12) CHECK (playing_time_unit IN ('SECOND', 'MINUTE', 'HOUR')),
    PRIMARY KEY (player_id)
);

CREATE TABLE club_ranking (
    club_id UUID NOT NULL REFERENCES club(id),
    ranking_points INTEGER DEFAULT 0,
    scored_goals INTEGER DEFAULT 0,
    conceded_goals INTEGER DEFAULT 0,
    difference_goals INTEGER DEFAULT 0,
    clean_sheet_number INTEGER DEFAULT 0,
    PRIMARY KEY (club_id)
);
