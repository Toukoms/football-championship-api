-- COACHES
INSERT INTO coach (id, name, nationality, created_at) VALUES (gen_random_uuid(), 'Entraineur 1', 'Français', 2024);
INSERT INTO coach (id, name, nationality, created_at) VALUES (gen_random_uuid(), 'Entraineur 2', 'Italien', 2024);
INSERT INTO coach (id, name, nationality, created_at) VALUES (gen_random_uuid(), 'Entraineur 3', 'Allemand', 2024);


-- CLUBS
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES (gen_random_uuid(), 'Club 1', 'C1', 'Stade 1', 2024, (SELECT id FROM coach WHERE name = 'Entraineur 1'));
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES (gen_random_uuid(), 'Club 2', 'C2', 'Stade 2', 2024, (SELECT id FROM coach WHERE name = 'Entraineur 2'));
INSERT INTO club (id, name, acronym, stadium, created_at, coach_id) VALUES (gen_random_uuid(), 'Club 3', 'C3', 'Stade 3', 2024, (SELECT id FROM coach WHERE name = 'Entraineur 3'));




-- PLAYERS
INSERT INTO player (id, name, number, position, nationality, age, created_at, current_club_id)
VALUES
   (gen_random_uuid(), 'Gardien 1', 1, 'GOAL_KEEPER', 'Espagnol', 30, 2024, (SELECT id FROM club WHERE name = 'Club 1')),
   (gen_random_uuid(), 'Défense 1', 2, 'DEFENSE', 'Espagnol', 25, 2024, (SELECT id FROM club WHERE name = 'Club 1')),
   (gen_random_uuid(), 'Milieu 1', 5, 'MIDFIELDER', 'Espagnol', 24, 2024, (SELECT id FROM club WHERE name = 'Club 1')),
   (gen_random_uuid(), 'Attaquant 1', 7, 'STRIKER', 'Espagnol', 17, 2024, (SELECT id FROM club WHERE name = 'Club 1')),


   (gen_random_uuid(), 'Gardien 2', 1, 'GOAL_KEEPER', 'Espagnol', 21, 2024, (SELECT id FROM club WHERE name = 'Club 2')),
   (gen_random_uuid(), 'Défense 2', 2, 'DEFENSE', 'Belge', 34, 2024, (SELECT id FROM club WHERE name = 'Club 2')),
   (gen_random_uuid(), 'Milieu 2', 5, 'MIDFIELDER', 'Français', 29, 2024, (SELECT id FROM club WHERE name = 'Club 2')),
   (gen_random_uuid(), 'Attaquant 2', 7, 'STRIKER', 'Allemand', 18, 2024, (SELECT id FROM club WHERE name = 'Club 2')),


   (gen_random_uuid(), 'Gardien 3', 1, 'GOAL_KEEPER', 'Brésilien', 28, 2024, (SELECT id FROM club WHERE name = 'Club 3')),
   (gen_random_uuid(), 'Défense 3', 2, 'DEFENSE', 'Brésilien', 21, 2024, (SELECT id FROM club WHERE name = 'Club 3')),
   (gen_random_uuid(), 'Milieu 3', 5, 'MIDFIELDER', 'Français', 29, 2024, (SELECT id FROM club WHERE name = 'Club 3')),
   (gen_random_uuid(), 'Attaquant 3', 7, 'STRIKER', 'Allemand', 23, 2024, (SELECT id FROM club WHERE name = 'Club 3'));


-- SEASONS
INSERT INTO season (id, year, alias, status, created_at)
VALUES (gen_random_uuid(),2024,'Saison 2024','NOT_STARTED', 2024);




-- MATCHES
INSERT INTO "match" (id, stadium, match_datetime, status, season_id, club_home_id, club_away_id)
VALUES
   (gen_random_uuid(), 'Stade 1', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM club WHERE name = 'Club 2')),
   (gen_random_uuid(), 'Stade 2', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 2'), (SELECT id FROM club WHERE name = 'Club 3')),
   (gen_random_uuid(), 'Stade 1', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM club WHERE name = 'Club 3')),
   (gen_random_uuid(), 'Stade 3', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 3'), (SELECT id FROM club WHERE name = 'Club 2')),
   (gen_random_uuid(), 'Stade 2', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 2'), (SELECT id FROM club WHERE name = 'Club 1')),
   (gen_random_uuid(), 'Stade 3', NULL, 'NOT_STARTED', (SELECT id FROM season WHERE year = 2024), (SELECT id FROM club WHERE name = 'Club 3'), (SELECT id FROM club WHERE name = 'Club 1'));


-- MATCH_PLAYER
INSERT INTO match_player (match_id, player_id, club_id, playing_time)
SELECT
   m.id,
   p.id,
   p.current_club_id,
   CASE
       WHEN name = 'Milieu 1' THEN 31400
       ELSE 32400
   END AS playing_time
FROM
   "match" m
JOIN player p ON p.current_club_id IN (m.club_home_id, m.club_away_id);




-- GOALS
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),2, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 1' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 1') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 2')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Attaquant 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),8, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 1' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 1') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 2')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Attaquant 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),50, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 1' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 1') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 2')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Milieu 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),60, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 1' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 1') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 2')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Défense 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),1, TRUE, (SELECT id FROM "match" WHERE stadium = 'Stade 1' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 1') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 2')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Gardien 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),88, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 2' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 2') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 3')), (SELECT id FROM club WHERE name = 'Club 2'), (SELECT id FROM player WHERE name = 'Attaquant 2'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),88, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 2' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 2') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 1')), (SELECT id FROM club WHERE name = 'Club 2'), (SELECT id FROM player WHERE name = 'Attaquant 2'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),21, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 2' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 2') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 3')), (SELECT id FROM club WHERE name = 'Club 3'), (SELECT id FROM player WHERE name = 'Attaquant 3'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),69, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 2' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 1') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 3')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Attaquant 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),88, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 3' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 3') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 1')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Gardien 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),89, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 3' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 3') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 1')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Gardien 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),90, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 3' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 3') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 1')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Gardien 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),56, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 3' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 3') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 1')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Attaquant 1'));
INSERT INTO goal (id,minute, own_goal, match_id, club_id, player_id) VALUES (gen_random_uuid(),90, FALSE, (SELECT id FROM "match" WHERE stadium = 'Stade 3' AND club_home_id = (SELECT id FROM club WHERE name = 'Club 3') AND club_away_id = (SELECT id FROM club WHERE name = 'Club 1')), (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM player WHERE name = 'Milieu 1'));


-- PLAYER_STATISTICS
INSERT INTO player_statistics (scored_goals, playing_time_value, playing_time_unit, player_id, season_id)
VALUES
 (4, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Attaquant 1'), (SELECT id FROM season WHERE year = 2024)),
 (2, 31400, 'SECOND', (SELECT id FROM player WHERE name = 'Milieu 1'), (SELECT id FROM season WHERE year = 2024)),
 (1, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Défense 1'), (SELECT id FROM season WHERE year = 2024)),
 (4, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Gardien 1'), (SELECT id FROM season WHERE year = 2024)),
 (2, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Attaquant 2'), (SELECT id FROM season WHERE year = 2024)),
 (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Milieu 2'), (SELECT id FROM season WHERE year = 2024)),
 (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Défense 2'), (SELECT id FROM season WHERE year = 2024)),
 (0, 32400, 'SECOND', (SELECT id FROM player WHERE name = 'Gardien 2'), (SELECT id FROM season WHERE year = 2024));




-- CLUB_STATISTICS
INSERT INTO club_statistics (ranking_points, scored_goals, conceded_goals, difference_goals, clean_sheet_number, club_id, season_id)
VALUES
 (7, 1, 4, -3, 0, (SELECT id FROM club WHERE name = 'Club 3'), (SELECT id FROM season WHERE year = 2024)),
 (6, 7, 2, 5, 0, (SELECT id FROM club WHERE name = 'Club 1'), (SELECT id FROM season WHERE year = 2024)),
 (4, 1, 5, -4, 0, (SELECT id FROM club WHERE name = 'Club 2'), (SELECT id FROM season WHERE year = 2024));

