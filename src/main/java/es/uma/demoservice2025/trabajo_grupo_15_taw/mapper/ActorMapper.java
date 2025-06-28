package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

// OUAIL BOUAZZA MANSOURI : 100%
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor;

public class ActorMapper {

    public static ActorDTO toDto(Actor entity) {
        if (entity == null) return null;
        ActorDTO dto = new ActorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setGender(entity.getGender());
        return dto;
    }

    public static Actor toEntity(ActorDTO dto) {
        if (dto == null) return null;
        Actor entity = new Actor();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());
        return entity;
    }
}
