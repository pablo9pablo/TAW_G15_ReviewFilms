package es.uma.demoservice2025.trabajo_grupo_15_taw.mapper;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;

// OUAIL BOUAZZA MANSOURI : 100%
public class GenreMapper {

    public static GenreDTO toDto(Genre entity) {
        if (entity == null) return null;
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Genre toEntity(GenreDTO dto) {
        if (dto == null) return null;
        Genre entity = new Genre();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
