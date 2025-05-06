-- COACHES
INSERT INTO coach (id, name, nationality) VALUES
('aa1bcae3-2b5f-4bd0-9c5c-1e8be00c1c1f', 'Lucien', 'France'),
('bb2d7aa0-2c9e-4f3f-b8ae-b4b6f20d4a5e', 'Franco', 'Italy'),
('cc3f4ef4-71db-4a37-9999-fffb8a0cde2b', 'Carlos', 'Spain'),
('dd47ccf3-8657-44ce-90f5-ec5b54ed4d45', 'Samir', 'Algeria'),
('ee5891b1-d7a1-4979-9d84-9c7256f6c72c', 'João', 'Portugal'),
('ff6ac973-70d2-40cd-b31c-0ed370fa2b8c', 'Hans', 'Germany'),
('112e83e5-194b-4097-8120-59fc830c177f', 'Akira', 'Japan'),
('223fb3c3-9b2b-4048-b2aa-9f4ea04e83bd', 'Ahmed', 'Egypt'),
('334fb5e1-dc38-4c5d-8cf8-44e337391e54', 'Mamadou', 'Senegal'),
('445eafa1-2de7-4bfa-b406-f0ee8ab9f52f', 'Paul', 'England');

-- CLUBS
INSERT INTO club (id, name, acronym, stadium, coach_id) VALUES
('c1f3d8a3-7d53-4f88-8e9b-aaaa11111111', 'AS Adema', 'ADA', 'Mahamasina', 'aa1bcae3-2b5f-4bd0-9c5c-1e8be00c1c1f'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa22222222', 'CNaPS Sport', 'CNS', 'Vontovorona', 'bb2d7aa0-2c9e-4f3f-b8ae-b4b6f20d4a5e'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa33333333', 'Fosa Juniors', 'FSJ', 'Rabemananjara', 'cc3f4ef4-71db-4a37-9999-fffb8a0cde2b'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa44444444', 'Zanak’Ala FC', 'ZFC', 'Toamasina Arena', 'dd47ccf3-8657-44ce-90f5-ec5b54ed4d45'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa55555555', 'Tana United', 'TUN', 'Mahamasina', 'ee5891b1-d7a1-4979-9d84-9c7256f6c72c'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa66666666', 'Dago FC', 'DFC', 'Ampasambazaha', 'ff6ac973-70d2-40cd-b31c-0ed370fa2b8c'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa77777777', 'Iarivo SC', 'ISC', 'Stade Municipal', '112e83e5-194b-4097-8120-59fc830c177f'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa88888888', 'Andoany FC', 'AFC', 'Ambodifotatra', '223fb3c3-9b2b-4048-b2aa-9f4ea04e83bd'),
('c1f3d8a3-7d53-4f88-8e9b-aaaa99999999', 'Manakara Sport', 'MSP', 'Stade SudEst', '334fb5e1-dc38-4c5d-8cf8-44e337391e54'),
('c1f3d8a3-7d53-4f88-8e9b-aaaaffffffff', 'Majunga United', 'MAU', 'Complexe Boeny', '445eafa1-2de7-4bfa-b406-f0ee8ab9f52f');

-- PLAYERS
INSERT INTO player (id, name, number, position, nationality, age, current_club_id) VALUES
('dfdab296-f6c1-45d1-9f34-a66cf0559e9d', 'Tiana Rakoto', 9, 'STRIKER', 'Madagascar', 24, 'c1f3d8a3-7d53-4f88-8e9b-aaaa11111111'),
('92db60c4-9c7e-46f1-9686-aa50351eefe2', 'Marco Rami', 10, 'MIDFIELDER', 'Madagascar', 27, 'c1f3d8a3-7d53-4f88-8e9b-aaaa22222222'),
('d09f7410-eded-484c-a7ce-a04580d0f41d', 'Alain Boto', 5, 'DEFENSE', 'Madagascar', 22, 'c1f3d8a3-7d53-4f88-8e9b-aaaa33333333'),
('3440bc2d-a9b5-4182-b39c-3c161f5a624a', 'Jean Freddy', 1, 'GOAL_KEEPER', 'Madagascar', 26, 'c1f3d8a3-7d53-4f88-8e9b-aaaa44444444'),
('960b1777-1108-4fa5-85f7-cfc06e1c97bd', 'Hery Solo', 7, 'STRIKER', 'Madagascar', 23, 'c1f3d8a3-7d53-4f88-8e9b-aaaa55555555'),
('e43b879c-6040-4f92-b744-65fa7c8a7fde', 'Toky Raja', 11, 'MIDFIELDER', 'Madagascar', 25, 'c1f3d8a3-7d53-4f88-8e9b-aaaa66666666'),
('617b6d09-80ba-4fd7-89ca-203758785b6b', 'Andry Mika', 4, 'DEFENSE', 'Madagascar', 21, 'c1f3d8a3-7d53-4f88-8e9b-aaaa77777777'),
('aef6f68f-6d3d-4872-99b9-c5f87748098d', 'Franck Dany', 13, 'GOAL_KEEPER', 'Madagascar', 28, 'c1f3d8a3-7d53-4f88-8e9b-aaaa88888888'),
('a36fc8f8-133c-4e7f-9572-67f02bdc9582', 'Dina Feno', 6, 'MIDFIELDER', 'Madagascar', 20, 'c1f3d8a3-7d53-4f88-8e9b-aaaa99999999'),
('d1434fe3-c6af-4181-99b7-06a9ec069efa', 'Rado Zo', 8, 'STRIKER', 'Madagascar', 24, 'c1f3d8a3-7d53-4f88-8e9b-aaaaffffffff');

-- SEASONS
INSERT INTO season (id, year, alias, status) VALUES
('45a2755c-71a7-4c75-8d78-82d7a0152c25', 2020, 'S2020-2021', 'FINISHED'),
('89b82cb5-0dfc-4619-95b4-e2185ff033a5', 2021, 'S2021-2022', 'FINISHED'),
('7e92bc44-e356-44dc-81e9-cef1a28d8289', 2022, 'S2022-2023', 'FINISHED'),
('7d6e69cf-a44e-4dd0-814e-2802fed0d159', 2023, 'S2023-2024', 'STARTED'),
('280b7ffc-5aef-4fcd-96ae-e2b89ad0e821', 2024, 'S2024-2025', 'NOT_STARTED');

-- MATCHES
INSERT INTO "match" (
    id, stadium, match_datetime, status, score_home, score_away, season_id, club_home_id, club_away_id
) VALUES
('646d6787-581b-455c-8309-8019a6da17bb', 'Mahamasina', '2023-08-01 15:00:00', 'FINISHED', 2, 1,
 '7d6e69cf-a44e-4dd0-814e-2802fed0d159', 'c1f3d8a3-7d53-4f88-8e9b-aaaa11111111', 'c1f3d8a3-7d53-4f88-8e9b-aaaa22222222'),
('09bfcb3a-0562-4dbd-806e-a52f0a7a2470', 'Rabemananjara', '2023-08-02 16:00:00', 'FINISHED', 0, 0,
 '7d6e69cf-a44e-4dd0-814e-2802fed0d159', 'c1f3d8a3-7d53-4f88-8e9b-aaaa33333333', 'c1f3d8a3-7d53-4f88-8e9b-aaaa44444444'),
('7fdb9488-3962-4ebd-abca-8ce72d0f1aa4', 'Toamasina Arena', '2023-08-03 14:30:00', 'FINISHED', 3, 2,
 '7d6e69cf-a44e-4dd0-814e-2802fed0d159', 'c1f3d8a3-7d53-4f88-8e9b-aaaa55555555', 'c1f3d8a3-7d53-4f88-8e9b-aaaa66666666'),
('489adc85-93c0-4425-9a05-3f570b134f37', 'Stade Municipal', '2023-08-04 15:30:00', 'STARTED', 1, 0,
 '7d6e69cf-a44e-4dd0-814e-2802fed0d159', 'c1f3d8a3-7d53-4f88-8e9b-aaaa77777777', 'c1f3d8a3-7d53-4f88-8e9b-aaaa88888888'),
('71915ac1-3fa7-40bf-9583-6e52c485ddf3', 'Stade SudEst', '2023-08-05 15:00:00', 'NOT_STARTED', 0, 0,
 '7d6e69cf-a44e-4dd0-814e-2802fed0d159', 'c1f3d8a3-7d53-4f88-8e9b-aaaa99999999', 'c1f3d8a3-7d53-4f88-8e9b-aaaaffffffff');

-- MATCH_PLAYER
INSERT INTO match_player (match_id, player_id, club_id) VALUES
('646d6787-581b-455c-8309-8019a6da17bb', 'dfdab296-f6c1-45d1-9f34-a66cf0559e9d', 'c1f3d8a3-7d53-4f88-8e9b-aaaa11111111'),
('646d6787-581b-455c-8309-8019a6da17bb', '92db60c4-9c7e-46f1-9686-aa50351eefe2', 'c1f3d8a3-7d53-4f88-8e9b-aaaa22222222'),
('09bfcb3a-0562-4dbd-806e-a52f0a7a2470', 'd09f7410-eded-484c-a7ce-a04580d0f41d', 'c1f3d8a3-7d53-4f88-8e9b-aaaa33333333'),
('09bfcb3a-0562-4dbd-806e-a52f0a7a2470', '3440bc2d-a9b5-4182-b39c-3c161f5a624a', 'c1f3d8a3-7d53-4f88-8e9b-aaaa44444444'),
('7fdb9488-3962-4ebd-abca-8ce72d0f1aa4', '960b1777-1108-4fa5-85f7-cfc06e1c97bd', 'c1f3d8a3-7d53-4f88-8e9b-aaaa55555555'),
('7fdb9488-3962-4ebd-abca-8ce72d0f1aa4', 'e43b879c-6040-4f92-b744-65fa7c8a7fde', 'c1f3d8a3-7d53-4f88-8e9b-aaaa66666666'),
('489adc85-93c0-4425-9a05-3f570b134f37', '617b6d09-80ba-4fd7-89ca-203758785b6b', 'c1f3d8a3-7d53-4f88-8e9b-aaaa77777777'),
('489adc85-93c0-4425-9a05-3f570b134f37', 'aef6f68f-6d3d-4872-99b9-c5f87748098d', 'c1f3d8a3-7d53-4f88-8e9b-aaaa88888888'),
('71915ac1-3fa7-40bf-9583-6e52c485ddf3', 'a36fc8f8-133c-4e7f-9572-67f02bdc9582', 'c1f3d8a3-7d53-4f88-8e9b-aaaa99999999'),
('71915ac1-3fa7-40bf-9583-6e52c485ddf3', 'd1434fe3-c6af-4181-99b7-06a9ec069efa', 'c1f3d8a3-7d53-4f88-8e9b-aaaaffffffff');

-- GOALS
INSERT INTO goal (minute, own_goal, match_id, club_id, player_id) VALUES
(10, false, '646d6787-581b-455c-8309-8019a6da17bb', 'c1f3d8a3-7d53-4f88-8e9b-aaaa11111111', 'dfdab296-f6c1-45d1-9f34-a66cf0559e9d'),
(35, false, '646d6787-581b-455c-8309-8019a6da17bb', 'c1f3d8a3-7d53-4f88-8e9b-aaaa11111111', 'dfdab296-f6c1-45d1-9f34-a66cf0559e9d'),
(60, false, '646d6787-581b-455c-8309-8019a6da17bb', 'c1f3d8a3-7d53-4f88-8e9b-aaaa22222222', '92db60c4-9c7e-46f1-9686-aa50351eefe2'),
(25, false, '7fdb9488-3962-4ebd-abca-8ce72d0f1aa4', 'c1f3d8a3-7d53-4f88-8e9b-aaaa55555555', '960b1777-1108-4fa5-85f7-cfc06e1c97bd'),
(40, false, '7fdb9488-3962-4ebd-abca-8ce72d0f1aa4', 'c1f3d8a3-7d53-4f88-8e9b-aaaa55555555', '960b1777-1108-4fa5-85f7-cfc06e1c97bd'),
(75, false, '7fdb9488-3962-4ebd-abca-8ce72d0f1aa4', 'c1f3d8a3-7d53-4f88-8e9b-aaaa66666666', 'e43b879c-6040-4f92-b744-65fa7c8a7fde'),
(50, false, '489adc85-93c0-4425-9a05-3f570b134f37', 'c1f3d8a3-7d53-4f88-8e9b-aaaa77777777', '617b6d09-80ba-4fd7-89ca-203758785b6b');

-- PLAYER_STATISTICS
INSERT INTO player_statistics (scored_goals, playing_time_value, playing_time_unit, player_id, season_id) VALUES
(2, 90, 'MINUTE', 'dfdab296-f6c1-45d1-9f34-a66cf0559e9d', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(1, 90, 'MINUTE', '92db60c4-9c7e-46f1-9686-aa50351eefe2', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(0, 90, 'MINUTE', 'd09f7410-eded-484c-a7ce-a04580d0f41d', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(0, 90, 'MINUTE', '3440bc2d-a9b5-4182-b39c-3c161f5a624a', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(2, 90, 'MINUTE', '960b1777-1108-4fa5-85f7-cfc06e1c97bd', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(1, 90, 'MINUTE', 'e43b879c-6040-4f92-b744-65fa7c8a7fde', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(1, 90, 'MINUTE', '617b6d09-80ba-4fd7-89ca-203758785b6b', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(0, 90, 'MINUTE', 'aef6f68f-6d3d-4872-99b9-c5f87748098d', '7d6e69cf-a44e-4dd0-814e-2802fed0d159');

-- CLUB_STATISTICS
INSERT INTO club_statistics (ranking_points, scored_goals, conceded_goals, difference_goals, clean_sheet_number, club_id, season_id) VALUES
(3, 2, 1, 1, 0, 'c1f3d8a3-7d53-4f88-8e9b-aaaa11111111', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(0, 1, 2, -1, 0, 'c1f3d8a3-7d53-4f88-8e9b-aaaa22222222', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(1, 0, 0, 0, 1, 'c1f3d8a3-7d53-4f88-8e9b-aaaa33333333', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(1, 0, 0, 0, 1, 'c1f3d8a3-7d53-4f88-8e9b-aaaa44444444', '7d6e69cf-a44e-4dd0-814e-2802fed0d159'),
(3, 3, 2, 1, 0, 'c1f3d8a3-7d53-4f88-8e9b-aaaa55555555', '7d6e69cf-a44e-4dd0-814e-2802fed0d159');
