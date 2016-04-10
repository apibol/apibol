package ranking.domain.repository.model;

import lombok.Getter;

/**
 * TO for Ranking element
 *
 * @author Claudio E. de Oliveira on on 10/04/16.
 */
public class RankingTO {

    @Getter
    private final String nickname;

    @Getter
    private final Integer points;

    @Getter
    private final Integer ranking;

    private RankingTO(String nickname, Integer points, Integer ranking) {
        this.nickname = nickname;
        this.points = points;
        this.ranking = ranking;
    }

    public static RankingTO newElement(String nickname, Integer points, Integer ranking) {
        return new RankingTO(nickname, points, ranking);
    }

}
