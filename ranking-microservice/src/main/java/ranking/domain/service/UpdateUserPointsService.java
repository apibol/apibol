package ranking.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ranking.domain.RequestUpdateUserPoints;
import ranking.domain.repository.RankingRepository;

/**
 * @author Claudio E. de Oliveira on 23/03/16.
 */
@Service
@Log4j2
public class UpdateUserPointsService {

    private final RankingRepository rankingRepository;

    @Autowired
    public UpdateUserPointsService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    /**
     * Update User Points
     * @param request
     */
    public void update(RequestUpdateUserPoints request){
        log.info("[RECEIVE-UPDATE-USER-POINTS] Update user points. data: "+ request.toString());
        this.rankingRepository.updatePoints(request);
    }

}
