package ranking.domain.repository;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ranking.domain.RankingElement;
import ranking.domain.RequestUpdateUserPoints;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ranking Repository
 *
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Service
@Log4j2
public class RankingRepository {

    private static final String RANKING_PATTERN = "ranking:predictor:%s:user:%s";

    private static final String GAME_PATTERN = "games:predictor:%s:user:%s";

    private final JedisPool jedisPool;

    @Autowired
    public RankingRepository(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * Update user points in requested predictor
     *
     * @param request
     */
    public void updatePoints(RequestUpdateUserPoints request) {
        final Jedis redis = jedisPool.getResource();
        try {
            String key = String.format(RANKING_PATTERN, request.getPredictorId(), request.getUserId());
            String gameKey = String.format(GAME_PATTERN, request.getPredictorId(), request.getUserId());
            if (!checkIfGameIsRepeated(redis, gameKey, request.getGameId())) {
                String data = redis.hget(key, "nickname");
                if (Strings.isNullOrEmpty(data)) {
                    redis.hmset(key, request.redisValue());
                } else {
                    redis.hincrBy(key, "points", request.getPointsEarned());
                }
                redis.sadd(key, request.getGameId());
            }
        } finally {
            jedisPool.returnResourceObject(redis);
        }
    }

    /**
     * Find rankings by predictor id
     *
     * @param predictorId
     * @return
     */
    public List<RankingElement> findRankingData(String predictorId) {
        final Jedis redis = jedisPool.getResource();
        final List<RankingElement> rankingElements = new ArrayList<>();
        try {
            String argument = String.format(RANKING_PATTERN, predictorId, "*");
            Set<String> keys = redis.keys(argument);
            keys.stream().forEach(key -> {
                Map<String, String> data = redis.hgetAll(key);
                rankingElements.add(RankingElement.createRankingElementByData(data));
            });
        } finally {
            jedisPool.returnResourceObject(redis);
        }
        return rankingElements;
    }

    /**
     * Check if the game was computed
     * @param redis
     * @param key
     * @param gameId
     * @return
     */
    private boolean checkIfGameIsRepeated(Jedis redis, String key, String gameId) {
        return redis.sismember(key, gameId);
    }

}
