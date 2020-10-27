package HardNightGame.DesanCardGamestatistic.controllers;

import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordDataDto;
import HardNightGame.DesanCardGamestatistic.dtos.GameRecordIdDto;
import HardNightGame.DesanCardGamestatistic.services.GameRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/game-records")
public class GameRecordController {

    private final GameRecordService gameRecordService;

    public GameRecordController(GameRecordService gameRecordService) {
        this.gameRecordService = gameRecordService;
    }

    @PostMapping
    public GameRecordIdDto AddGameRecord(@RequestBody GameRecordDataDto gameRecordData) {
        log.debug("GameRecordController.AddGameRecord GameRecordData:" + gameRecordData);
        return gameRecordService.AddGameRecord(gameRecordData);
    }

    @GetMapping
    public Collection<GameRecordDto> GetGameRecord(@RequestParam Integer topCount){
        return gameRecordService.GetTopGameRecords(topCount);
    }
}
