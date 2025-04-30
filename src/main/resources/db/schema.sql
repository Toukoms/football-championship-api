CREATE DATABASE football_championship;

CREATE TABLE coach (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    nationality VARCHAR(56),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE club (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE,
    acronym VARCHAR(3) NOT NULL,
    stadium VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    coach_id UUID REFERENCES coach(id)
);

CREATE TABLE player (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    number INTEGER,
    position VARCHAR(16) CHECK (position IN ('STRIKER', 'MIDFIELDER', 'DEFENSE', 'GOAL_KEEPER')),
    nationality VARCHAR(50),
    age INTEGER CHECK (age BETWEEN 0 and 80),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    current_club_id UUID REFERENCES club(id)
);

CREATE TABLE season (
    id UUID PRIMARY KEY,
    year INTEGER NOT NULL,
    alias VARCHAR(24) NOT NULL,
    status VARCHAR(16) NOT NULL CHECK (status IN ('NOT_STARTED', 'STARTED', 'FINISHED'))
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "match" (
    id VARCHAR(50) PRIMARY KEY,
    stadium VARCHAR(100),
    match_datetime TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (sta tus IN ('NOT_STARTED', 'STARTED', 'FINISHED')),
    score_home INTEGER DEFAULT 0,
    score_away INTEGER DEFAULT 0,

    season_id UUID NOT NULL REFERENCES season(id),
    club_home_id UUID NOT NULL REFERENCES club(id),
    club_away_id UUID NOT NULL REFERENCES club(id),
    CONSTRAINT different_clubs CHECK (club_home_id != club_away_id)
);

CREATE TABLE match_player (
    match_id UUID NOT NULL REFERENCES "match"(id),
    player_id UUID NOT NULL REFERENCES player(id),
    club_id UUID NOT NULL REFERENCES club(id),
    PRIMARY KEY (match_id, player_id)
);

CREATE TABLE goal (
    id SERIAL PRIMARY KEY,
    minute INTEGER NOT NULL CHECK (minute BETWEEN 1 AND 90),
    own_goal BOOLEAN NOT NULL DEFAULT FALSE,

    match_id UUID NOT NULL REFERENCES "match"(id),
    club_id UUID NOT NULL REFERENCES club(id),
    player_id UUID NOT NULL REFERENCES player(id),
    FOREIGN KEY (match_id, player_id) REFERENCES match_player(match_id, player_id)
);

CREATE TABLE player_statistics (
    scored_goals INTEGER DEFAULT 0,
    playing_time_value NUMERIC,
    playing_time_unit VARCHAR(12) CHECK (playing_time_unit IN ('SECOND', 'MINUTE', 'HOUR')),

    player_id UUID NOT NULL REFERENCES player(id),
    season_id UUID NOT NULL REFERENCES season(id),
    PRIMARY KEY (player_id, season_id)
);

CREATE TABLE club_statistics (
    ranking_points INTEGER DEFAULT 0,
    scored_goals INTEGER DEFAULT 0,
    conceded_goals INTEGER DEFAULT 0,
    difference_goals INTEGER DEFAULT 0,
    clean_sheet_number INTEGER DEFAULT 0,

    club_id UUID NOT NULL REFERENCES club(id),
    season_id UUID NOT NULL REFERENCES season(id),
    PRIMARY KEY (club_id, season_id)
);
