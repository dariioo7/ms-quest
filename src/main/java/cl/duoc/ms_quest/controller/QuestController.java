package cl.duoc.ms_quest.controller;

import cl.duoc.ms_quest.dto.QuestRequestDto;
import cl.duoc.ms_quest.dto.QuestResponseDto;
import cl.duoc.ms_quest.service.QuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quests")
public class QuestController {

    @Autowired
    private QuestService service;

    @PostMapping
    public ResponseEntity<QuestResponseDto> create(@Valid @RequestBody QuestRequestDto dto) {
        return ResponseEntity.ok(service.createQuest(dto));
    }

    @GetMapping
    public ResponseEntity<List<QuestResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllQuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQuestById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteQuest(id);
        return ResponseEntity.noContent().build();
    }
}