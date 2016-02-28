package championship.domain.service;

import championship.domain.Championship;
import championship.domain.repository.ChampionshipRepository;
import championship.domain.resource.model.NewGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    @Autowired
    public ChampionshipService(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    public Championship create(Championship championship) {
        championshipRepository.add(championship);
        return championship;
    }

    public Championship addNewGame(String championshipId, NewGame newGame) {
        Championship championship = championshipRepository.get(championshipId);
        championship.addGame(newGame.toDomain());
        championshipRepository.update(championship);
        return championship;
    }

}
