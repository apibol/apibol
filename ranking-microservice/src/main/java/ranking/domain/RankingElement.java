package ranking.domain;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * Represents the ranking entry value
 * @author Claudio E. de Oliveira on on 03/04/16.
 */
@Data
public class RankingElement implements Comparable<RankingElement>  {

    private String nickname;

    private Integer points;

    RankingElement(){}

    private RankingElement(String nickname, Integer points){
        this.nickname = nickname;
        this.points = points;
    }

    private RankingElement(Map<String,String> values){
        this.nickname = values.get("nickname");
        this.points = Integer.valueOf(values.get("points"));
    }

    public static RankingElement createRankingElement(String nickname, Integer points) {
        return new RankingElement(nickname, points);
    }

    public static RankingElement createRankingElementByData(Map<String,String> values) {
        return new RankingElement(values);
    }

    /**
     * Create the redis value key
     * @return
     */
    public Map<String, String> redisValue() {
        final Map<String, String> value = Maps.newHashMap();
        value.put("points",String.valueOf(this.points));
        value.put("nickname",this.nickname);
        return value;
    }

    @Override
    public int compareTo(RankingElement o) {
        return this.points.compareTo(o.points);
    }

}
