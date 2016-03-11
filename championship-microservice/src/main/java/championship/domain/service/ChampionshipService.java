package championship.domain.service;

import championship.domain.Championship;
import championship.domain.User;
import championship.domain.repository.ChampionshipRepository;
import championship.domain.resource.model.ChampionshipDTO;
import championship.domain.resource.model.NewGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    private final UserInfoService userInfoService;

    @Autowired
    public ChampionshipService(ChampionshipRepository championshipRepository, UserInfoService userInfoService) {
        this.championshipRepository = championshipRepository;
        this.userInfoService = userInfoService;
    }

    public Championship create(ChampionshipDTO championship) {
        User userInfo = this.userInfoService.getUserInfo(championship.getOwnerId());
        Championship savedChampionship = this.championshipRepository.save(championship.toDomain(userInfo));
        return savedChampionship;
    }

    public Championship addNewGame(String championshipId, NewGame newGame) {
        Championship championship = this.championshipRepository.findOne(championshipId);
        championship.addGame(newGame.toDomain());
        this.championshipRepository.save(championship);
        return championship;
    }

    public List<Championship> all() {
        return this.championshipRepository.findAll();
    }

}
