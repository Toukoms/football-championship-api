CREATE DATABASE football_championship;
\c football_championship;

-- COACH
CREATE TABLE coach (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    nationality VARCHAR(56),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- CLUB
CREATE TABLE club (
    id UUID PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE,
    acronym VARCHAR(3) NOT NULL,
    year_creation INTEGER,
    stadium VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    current_club_id UUID REFERENCES club(id) ON DELETE SET NULL,
    CONSTRAINT  unique_player UNIQUE(name, number)
);

-- SEASON
CREATE TABLE season (
    id UUID PRIMARY KEY,
    year INT NOT NULL UNIQUE,
    alias VARCHAR(24) NOT NULL,
    status VARCHAR(16) NOT NULL CHECK (status IN ('NOT_STARTED', 'STARTED', 'FINISHED')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- MATCH
CREATE TABLE "match" (
    id UUID PRIMARY KEY,
    stadium VARCHAR(100),
    match_datetime TIMESTAMP,
    status VARCHAR(16) NOT NULL CHECK (status IN ('NOT_STARTED', 'STARTED', 'FINISHED')),
    score_home BIGINT DEFAULT 0,
    score_away BIGINT DEFAULT 0,
    season_id UUID NOT NULL REFERENCES season(id) ON DELETE CASCADE,
    club_home_id UUID NOT NULL REFERENCES club(id) ON DELETE CASCADE,
    club_away_id UUID NOT NULL REFERENCES club(id) ON DELETE CASCADE,
    CONSTRAINT different_clubs CHECK (club_home_id != club_away_id)
);

-- MATCH_PLAYER
CREATE TABLE match_player (
    match_id UUID NOT NULL REFERENCES "match"(id) ON DELETE CASCADE,
    player_id UUID NOT NULL REFERENCES player(id) ON DELETE CASCADE,
    club_id UUID NOT NULL REFERENCES club(id) ON DELETE CASCADE,
    playing_time BIGINT DEFAULT 0,
    PRIMARY KEY (match_id, player_id)
);

-- GOAL
CREATE TABLE goal (
    id SERIAL PRIMARY KEY,
    minute BIGINT NOT NULL CHECK (minute BETWEEN 1 AND 90),
    own_goal BOOLEAN NOT NULL DEFAULT FALSE,
    match_id UUID NOT NULL REFERENCES "match"(id) ON DELETE CASCADE,
    club_id UUID NOT NULL REFERENCES club(id) ON DELETE CASCADE,
    player_id UUID NOT NULL REFERENCES player(id) ON DELETE CASCADE
);

-- PLAYER_STATISTICS
CREATE TABLE player_statistics (
    scored_goals BIGINT DEFAULT 0,
    playing_time_value BIGINT DEFAULT 0,
    playing_time_unit VARCHAR(12) CHECK (playing_time_unit IN ('SECOND', 'MINUTE', 'HOUR')),
    player_id UUID NOT NULL REFERENCES player(id),
    season_id UUID NOT NULL REFERENCES season(id),
    PRIMARY KEY (player_id, season_id)
);

-- CLUB_STATISTICS
CREATE TABLE club_statistics (
    ranking_points BIGINT DEFAULT 0,
    scored_goals BIGINT DEFAULT 0,
    conceded_goals BIGINT DEFAULT 0,
    difference_goals BIGINT DEFAULT 0,
    clean_sheet_number BIGINT DEFAULT 0,
    club_id UUID NOT NULL REFERENCES club(id),
    season_id UUID NOT NULL REFERENCES season(id),
    PRIMARY KEY (club_id, season_id)
);