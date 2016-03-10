package championship.domain.resource.model;

import championship.domain.Championship;
import championship.domain.Period;
import championship.domain.User;
import lombok.Data;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 09/03/16.
 */
@Data
public class ChampionshipDTO {

    private String name;

    private Period period;

    private Boolean open = Boolean.FALSE;
    
    private String ownerId;

    public Championship toDomain(User owner){
        return Championship.newChampionship(UUID.randomUUID().toString(),this.name,this.period,this.open,owner);
    }

}
