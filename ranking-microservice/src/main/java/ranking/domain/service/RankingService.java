package ranking.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import ranking.domain.RankingElement;
import ranking.domain.exception.RankingNotFound;
import ranking.domain.repository.RankingRepository;
import ranking.domain.repository.model.RankingTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Ranking Service
 *
 * @author Claudio E. de Oliveira on on 03/04/16.
 */
@Service
@Log4j2
public class RankingService {

    private final RankingRepository rankingRepository;

    @Autowired
    public RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    /**
     * Retrieve ranking by predictor
     *
     * @param predictorId
     * @return
     */
    public List<RankingTO> findRanking(String predictorId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info(String.format("[REQUEST-RANKING] Request ranking for predictor %s", predictorId));
        List<RankingElement> rankings = this.rankingRepository.findRankingData(predictorId);
        if (rankings.isEmpty()) {
            log.error(String.format("[REQUEST-RANKING] Ranking for predictor %s not found", predictorId));
            throw new RankingNotFound(predictorId);
        }
        rankings.sort((r1, r2) -> r1.compareTo(r2));
        int ranking = 1;
        final List<RankingTO> rankingTOs = new ArrayList<>();
        for (RankingElement rankingElement : rankings) {
            rankingTOs.add(RankingTO.newElement(rankingElement.getNickname(), rankingElement.getPoints(), ranking));
            ranking++;
        }
        stopWatch.stop();
        log.info(String.format("[REQUEST-RANKING] Request ranking for predictor %s complete with success in %s seconds", predictorId, String.valueOf(stopWatch.getTotalTimeSeconds())));
        return rankingTOs;
    }

}
