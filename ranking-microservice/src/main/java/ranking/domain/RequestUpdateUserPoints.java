package ranking.domain;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * User earned points
 *
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class RequestUpdateUserPoints {

    private String predictorId;

    private String userId;

    private Integer pointsEarned;

    private String nickname;

    private String gameId;

    /**
     * Create the redis value key
     * @return
     */
    public Map<String, String> redisValue() {
        return RankingElement.createRankingElement(this.nickname,this.pointsEarned).redisValue();
    }

}
