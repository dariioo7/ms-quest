package cl.duoc.ms_quest.service.impl;

import cl.duoc.ms_quest.client.UserFeignClient;
import cl.duoc.ms_quest.dto.QuestRequestDto;
import cl.duoc.ms_quest.dto.QuestResponseDto;
import cl.duoc.ms_quest.dto.UserDto;
import cl.duoc.ms_quest.model.Quest;
import cl.duoc.ms_quest.repository.QuestRepository;
import cl.duoc.ms_quest.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {


    private final QuestRepository repository;
    private final UserFeignClient userFeignClient;

    @Override
    public QuestResponseDto createQuest(QuestRequestDto dto) {
        Quest quest = toEntity(dto);
        quest = repository.save(quest);
        return toDto(quest);
    }

    @Override
    public List<QuestResponseDto> getAllQuests() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuestResponseDto getQuestById(Long id) {
        Quest quest = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("La quest con el id " + id + " no existe"));
        return toDto(quest);
    }

    @Override
    public void deleteQuest(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede borrar: La quest con el id " + id + " no existe");
        }
        repository.deleteById(id);
    }


    private Quest toEntity(QuestRequestDto dto) {
        Quest quest = new Quest();
        quest.setTitle(dto.getTitle());
        quest.setDescription(dto.getDescription());
        quest.setQuestType(dto.getQuestType());
        quest.setObjective(dto.getObjective());
        quest.setExpReward(dto.getExpReward());
        quest.setCoinReward(dto.getCoinReward());
        quest.setStatus("ACTIVE");
        return quest;
    }

    private QuestResponseDto toDto(Quest entity) {
        QuestResponseDto dto = new QuestResponseDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setQuestType(entity.getQuestType());
        dto.setObjective(entity.getObjective());
        dto.setExpReward(entity.getExpReward());
        dto.setCoinReward(entity.getCoinReward());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    @Override
    public void assignQuestToUser(Long questId, Long userId) {
        try {
            userFeignClient.getUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error: El usuario con la id: " + userId + " no existe");
        }

        Quest quest = repository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Misión no encontrada"));


        System.out.println("La misión " + quest.getTitle() + " ha sido asignada al usuario: " + userId);
    }

    public void validateUser(Long userId) {
        UserDto user =  userFeignClient.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("El usuario con la id: " + userId + " no existe");
        }
        if ("SUSPENDED".equals(user.getAccountStatus())) {
            throw new RuntimeException("El usuario esta suspendido no puede aceptar misiones");
        }
    }
}