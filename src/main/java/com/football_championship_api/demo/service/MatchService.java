package com.football_championship_api.demo.service;

import com.football_championship_api.demo.data.entity.MatchEntity;
import com.football_championship_api.demo.data.entity.PlayingStatus;
import com.football_championship_api.demo.data.repository.FilterMatch;
import com.football_championship_api.demo.data.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public List<MatchEntity> getMatchesBySeasonYear(Integer seasonYear, PlayingStatus matchStatus, String clubPlayingName, LocalDate matchAfter, LocalDate matchBeforeOrEquals) {
        if (seasonYear == null) {
            throw new IllegalArgumentException("Season year must be provided");
        }
        if (matchStatus == null && clubPlayingName == null && matchAfter == null && matchBeforeOrEquals == null) {
            return matchRepository.findAll(seasonYear);
        }
        FilterMatch filter = new FilterMatch();
        filter.setMatchStatus(matchStatus);
        filter.setClubPlayingName(clubPlayingName);
        filter.setMatchAfter(matchAfter);
        filter.setMatchBeforeOrEquals(matchBeforeOrEquals);

        return matchRepository.findAll(seasonYear, filter);
    }

}
