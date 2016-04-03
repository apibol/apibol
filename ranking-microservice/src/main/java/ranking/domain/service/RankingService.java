package ranking.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ranking.domain.RankingElement;
import ranking.domain.exception.RankingNotFound;
import ranking.domain.repository.RankingRepository;

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
     * @param predictorId
     * @return
     */
    public List<RankingElement> findRanking(String predictorId){
        List<RankingElement> rankingData = this.rankingRepository.findRankingData(predictorId);
        if(rankingData.isEmpty()){
            log.error(String.format("Ranking for predictor %s not found",predictorId));
            throw new RankingNotFound(predictorId);
        }
        rankingData.sort((r1,r2) -> r1.compareTo(r2));
        return rankingData;
    }

}
