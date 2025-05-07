-- COACHES
INSERT INTO coach (id, name, nationality, created_at) VALUES
('eecf58b6-1a3a-4d24-bf4b-111111111111', 'Lucien', 'FRANCE', 2024),
('eecf58b6-1a3a-4d24-bf4b-222222222222', 'Franco', 'ITALY', 2024);

-- CLUBS
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES
('a3e11b02-9be6-41a0-bc22-111111111111', 'AS Adema', 'ADA', 'Mahamasina', 2000, 'eecf58b6-1a3a-4d24-bf4b-111111111111'),
('a3e11b02-9be6-41a0-bc22-222222222222', 'CNaPS Sport', 'CNS', 'Vontovorona', 2005, 'eecf58b6-1a3a-4d24-bf4b-222222222222');

-- PLAYERS
INSERT INTO player (id, name, number, position, nationality, age, created_at, current_club_id) VALUES
('1fd09d63-4eae-4d5a-aaaa-111111111111', 'Tiana Rakoto', 9, 'STRIKER', 'MADAGASCAR', 24, 2024, 'a3e11b02-9be6-41a0-bc22-111111111111'),
('1fd09d63-4eae-4d5a-bbbb-222222222222', 'Marco Rami', 10, 'MIDFIELDER', 'MADAGASCAR', 27, 2024, 'a3e11b02-9be6-41a0-bc22-222222222222');

-- SEASONS
INSERT INTO season (id, year, alias, status, created_at) VALUES
('55555555-aaaa-bbbb-cccc-111111111111', 2024, '2024 Season', 'STARTED', 2024),
('55555555-aaaa-bbbb-cccc-333333333333', 2025, '2025 Season', 'NOT_STARTED', 2024);

-- MATCHES
INSERT INTO "match" (id, stadium, match_datetime, status, score_home, score_away, season_id, club_home_id, club_away_id) VALUES
('54612378-aaaa-bbbb-cccc-424242445142', 'Mahamasina', '2024-04-01 15:00:00', 'FINISHED', 2, 1,
 '55555555-aaaa-bbbb-cccc-111111111111', 'a3e11b02-9be6-41a0-bc22-111111111111', 'a3e11b02-9be6-41a0-bc22-222222222222');

-- MATCH_PLAYER
INSERT INTO match_player (match_id, player_id, club_id) VALUES
('54612378-aaaa-bbbb-cccc-424242445142', '1fd09d63-4eae-4d5a-aaaa-111111111111', 'a3e11b02-9be6-41a0-bc22-111111111111'),
('54612378-aaaa-bbbb-cccc-424242445142', '1fd09d63-4eae-4d5a-bbbb-222222222222', 'a3e11b02-9be6-41a0-bc22-222222222222');

-- GOALS
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES
(15, false, '54612378-aaaa-bbbb-cccc-424242445142', 'a3e11b02-9be6-41a0-bc22-111111111111', '1fd09d63-4eae-4d5a-aaaa-111111111111'),
(55, false, '54612378-aaaa-bbbb-cccc-424242445142', 'a3e11b02-9be6-41a0-bc22-111111111111', '1fd09d63-4eae-4d5a-aaaa-111111111111'),
(70, false, '54612378-aaaa-bbbb-cccc-424242445142', 'a3e11b02-9be6-41a0-bc22-222222222222', '1fd09d63-4eae-4d5a-bbbb-222222222222');

-- PLAYER_STATISTICS
INSERT INTO player_statistics (scored_goals, playing_time_value, playing_time_unit, player_id, season_id) VALUES
(2, 90, 'MINUTE', '1fd09d63-4eae-4d5a-aaaa-111111111111', '55555555-aaaa-bbbb-cccc-111111111111'),
(1, 90, 'MINUTE', '1fd09d63-4eae-4d5a-bbbb-222222222222', '55555555-aaaa-bbbb-cccc-111111111111');

-- CLUB_STATISTICS
INSERT INTO club_statistics (ranking_points, scored_goals, conceded_goals, difference_goals, clean_sheet_number, club_id, season_id) VALUES
(3, 2, 1, 1, 0, 'a3e11b02-9be6-41a0-bc22-111111111111', '55555555-aaaa-bbbb-cccc-111111111111'),
(0, 1, 2, -1, 0, 'a3e11b02-9be6-41a0-bc22-222222222222', '55555555-aaaa-bbbb-cccc-111111111111');
