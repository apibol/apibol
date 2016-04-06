package ranking.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ranking.domain.RequestUpdateUserPoints;
import ranking.domain.repository.RankingRepository;

/**
 * @author Claudio E. de Oliveira on 23/03/16.
 */
@Service
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
        this.rankingRepository.updatePoints(request);
    }

}
