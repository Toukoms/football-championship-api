-- COACHES
INSERT INTO coach (id, name, nationality, created_at) VALUES (gen_random_uuid(), 'Entraineur 4', 'Malgache', 2024);
INSERT INTO coach (id, name, nationality, created_at) VALUES (gen_random_uuid(), 'Entraineur 5', 'Ivorien', 2024);
INSERT INTO coach (id, name, nationality, created_at) VALUES (gen_random_uuid(), 'Entraineur 6', 'Espagnol', 2024);

-- CLUBS
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES (gen_random_uuid(), 'Club 4', 'C4', 'Stade 4', 2024, (SELECT id FROM coach WHERE name = 'Entraineur 4'));
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES (gen_random_uuid(), 'Club 5', 'C5', 'Stade 5', 2024, (SELECT id FROM coach WHERE name = 'Entraineur 5'));
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES (gen_random_uuid(), 'Club 6', 'C6', 'Stade 6', 2024, (SELECT id FROM coach WHERE name = 'Entraineur 6'));


-- PLAYERS
INSERT INTO player (id, name, number, position, nationality, age, created_at, current_club_id)
VALUES
    (gen_random_uuid(), 'Gardien 4', 1, 'GOAL_KEEPER', 'Brésilien', 30, 2024, (SELECT id FROM club WHERE name = 'Club 4')),
    (gen_random_uuid(), 'Défense 4', 2, 'DEFENSE', 'Brésilien', 25, 2024, (SELECT id FROM club WHERE name = 'Club 4')),
    (gen_random_uuid(), 'Milieu 4', 5, 'MIDFIELDER', 'Français', 24, 2024, (SELECT id FROM club WHERE name = 'Club 4')),
    (gen_random_uuid(), 'Attaquant 4', 7, 'STRIKER', 'Allemand', 17, 2024, (SELECT id FROM club WHERE name = 'Club 4')),

    (gen_random_uuid(), 'Gardien 5', 1, 'GOAL_KEEPER', 'Français', 21, 2024, (SELECT id FROM club WHERE name = 'Club 5')),
    (gen_random_uuid(), 'Défense 5', 2, 'DEFENSE', 'Belge', 34, 2024, (SELECT id FROM club WHERE name = 'Club 5')),
    (gen_random_uuid(), 'Milieu 5', 5, 'MIDFIELDER', 'Français', 29, 2024, (SELECT id FROM club WHERE name = 'Club 5')),
    (gen_random_uuid(), 'Attaquant 5', 7, 'STRIKER', 'Allemand', 18, 2024, (SELECT id FROM club WHERE name = 'Club 5')),

    (gen_random_uuid(), 'Gardien 6', 1, 'GOAL_KEEPER', 'Espagnol', 28, 2024, (SELECT id FROM club WHERE name = 'Club 6')),
    (gen_random_uuid(), 'Défense 6', 2, 'DEFENSE', 'Brésilien', 21, 2024, (SELECT id FROM club WHERE name = 'Club 6')),
    (gen_random_uuid(), 'Milieu 6', 5, 'MIDFIELDER', 'Italien', 29, 2024, (SELECT id FROM club WHERE name = 'Club 6')),
    (gen_random_uuid(), 'Attaquant 6', 7, 'STRIKER', 'Allemand', 23, 2024, (SELECT id FROM club WHERE name = 'Club 6'));

-- SEASONS
INSERT INTO season (id, year, alias, status, created_at)
VALUES (gen_random_uuid(),2024,'Saison 2024','NOT_STARTED', 2024);


-- MATCHES
INSERT INTO "match" (id, stadium, match_datetime, status, season_id, club_home_id, club_away_id)
VALUES
    (gen_random_uuid(), 'Stade 4', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM club WHERE name = 'Club 5')),
    (gen_random_uuid(), 'Stade 5', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 5'), (SELECT id FROM club WHERE name = 'Club 6')),
    (gen_random_uuid(), 'Stade 4', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM club WHERE name = 'Club 6')),
    (gen_random_uuid(), 'Stade 6', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 6'), (SELECT id FROM club WHERE name = 'Club 5')),
    (gen_random_uuid(), 'Stade 5', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 5'), (SELECT id FROM club WHERE name = 'Club 4')),
    (gen_random_uuid(), 'Stade 6', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 6'), (SELECT id FROM club WHERE name = 'Club 4'));

-- MATCH_PLAYER
INSERT INTO match_player (match_id, player_id, club_id, playing_time)
SELECT
    m.id,
    p.id,
    p.current_club_id,
    CASE
        WHEN name = 'Milieu 4' THEN 31400
        ELSE 32400
    END AS playing_time
FROM
    "match" m
JOIN player p ON p.current_club_id IN (m.club_home_id, m.club_away_id);


-- GOALS
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (2, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 4' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 4') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 5')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Attaquant 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (8, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 4' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 4') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 5')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Attaquant 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (50, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 4' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 4') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 5')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Milieu 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (60, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 4' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 4') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 5')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Défense 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (1, TRUE, (SELECT id FROM "match" WHERE stadium = 'Stade 4' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 4') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 5')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Gardien 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (88, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 5' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 5') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 6')), (SELECT id FROM club WHERE name = 'Club 5'), (SELECT id FROM player WHERE name = 'Attaquant 5'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (88, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 5' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 5') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 4')), (SELECT id FROM club WHERE name = 'Club 5'), (SELECT id FROM player WHERE name = 'Attaquant 5'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (21, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 5' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 5') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 6')), (SELECT id FROM club WHERE name = 'Club 6'), (SELECT id FROM player WHERE name = 'Attaquant 6'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (69, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 4' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 4') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 6')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Attaquant 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (88, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 6' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 6') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 4')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Gardien 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (89, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 6' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 6') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 4')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Gardien 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (90, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 6' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 6') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 4')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Gardien 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (56, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 6' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 6') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 4')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Attaquant 4'));
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES (90, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 6' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 6') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 4')), (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM player WHERE name = 'Milieu 4'));

-- PLAYER_STATISTICS
INSERT INTO player_statistics (scored_goals, playing_time_value, playing_time_unit, player_id, season_id)
VALUES
  (4, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Attaquant 4'), (SELECT id FROM season WHERE year = 2024)),
  (2, 31400, 'SECOND', (SELECT id FROM player WHERE name = 'Milieu 4'), (SELECT id FROM season WHERE year = 2024)),
  (1, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Défense 4'), (SELECT id FROM season WHERE year = 2024)),
  (4, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Gardien 4'), (SELECT id FROM season WHERE year = 2024)),
  (2, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Attaquant 5'), (SELECT id FROM season WHERE year = 2024)),
  (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Milieu 5'), (SELECT id FROM season WHERE year = 2024)),
  (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Défense 5'), (SELECT id FROM season WHERE year = 2024)),
  (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Gardien 5'), (SELECT id FROM season WHERE year = 2024)),
  (1, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Attaquant 6'), (SELECT id FROM season WHERE year = 2024)),
  (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Milieu 6'), (SELECT id FROM season WHERE year = 2024)),
  (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Défense 6'), (SELECT id FROM season WHERE year = 2024)),
  (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Gardien 6'), (SELECT id FROM season WHERE year = 2024));

-- CLUB_STATISTICS
INSERT INTO club_statistics (ranking_points, scored_goals, conceded_goals, difference_goals, clean_sheet_number, club_id, season_id)
VALUES
  (6, 11, 2, 9, 0, (SELECT id FROM club WHERE name = 'Club 4'), (SELECT id FROM season WHERE year = 2024)),
  (3, 2, 5, -3, 0, (SELECT id FROM club WHERE name = 'Club 5'), (SELECT id FROM season WHERE year = 2024)),
  (0, 1, 7, -6, 0, (SELECT id FROM club WHERE name = 'Club 6'), (SELECT id FROM season WHERE year = 2024));
