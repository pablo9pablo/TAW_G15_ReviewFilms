package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Crew;
import org.springframework.stereotype.Component;

@Component
public class CrewMapper {

    public CrewDTO toDto(Crew crew) {
        if (crew == null) return null;

        CrewDTO dto = new CrewDTO();
        dto.setId(crew.getId());
        dto.setName(crew.getName());
        dto.setGender(crew.getGender());
        return dto;
    }
}
