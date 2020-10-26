package HardNightGame.DesanCardGamestatistic.controller;

import HardNightGame.DesanCardGamestatistic.dto.GameRecordData;
import HardNightGame.DesanCardGamestatistic.dto.GameRecordId;
import HardNightGame.DesanCardGamestatistic.service.GameRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/game-records")
public class GameRecordController {

    private final GameRecordService gameRecordService;

    public GameRecordController(GameRecordService gameRecordService) {
        this.gameRecordService = gameRecordService;
    }

    @PostMapping
    public GameRecordId AddGameRecord(@RequestBody GameRecordData gameRecordData) {
        log.debug("GameRecordController.AddGameRecord GameRecordData:" + gameRecordData);
        return gameRecordService.AddGameRecord(gameRecordData);
    }
}
