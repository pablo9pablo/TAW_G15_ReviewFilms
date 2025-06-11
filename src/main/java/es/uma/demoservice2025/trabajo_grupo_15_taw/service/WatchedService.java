// MIGUEL LABELLA RAMÍREZ: 100%

package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.SeenRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.SeenMovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.SeenMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchedService {

    @Autowired
    protected SeenRepository seenRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected SeenMapper seenMapper;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<SeenMovieDTO> getSeenMoviesByUser(String email) {
        User user = usuarioRepository.findByEmail(email);
        List<Seen> seenList = seenRepository.findByUserId(user.getId());
        return seenList.stream().map(seenMapper::toDTO).collect(Collectors.toList());
    }

    public void removeSeenMovie(String email, Integer movieId) {
        User user = usuarioRepository.findByEmail(email);
        Seen seen = seenRepository.findByUserIdAndMovieId(user.getId(), movieId);
        if (seen != null) {
            seenRepository.delete(seen);
        }
    }

    public List<SeenMovieDTO> filterSeenMovies(String email, Filtro filtro, String orden) {
        User user = usuarioRepository.findByEmail(email);
        Integer userId = user.getId();

        LocalDate startDate = filtro.getYear() != null ? LocalDate.of(filtro.getYear(), Month.JANUARY, 1) : null;
        LocalDate endDate = filtro.getYear() != null ? LocalDate.of(filtro.getYear(), Month.DECEMBER, 31) : null;

        List<Integer> generos = filtro.getGeneroIds();
        if (generos != null && generos.isEmpty()) generos = null;

        if (orden == null) {
            orden = "";
        }

        List<Seen> result;

        if (generos == null) {
            switch (orden) {
                case "asc":
                    result = seenRepository.buscarPorFiltrosSinGeneroOrdenAsc(userId, filtro.getYear(), filtro.getVote(), startDate, endDate);
                    break;
                case "desc":
                    result = seenRepository.buscarPorFiltrosSinGeneroOrdenDesc(userId, filtro.getYear(), filtro.getVote(), startDate, endDate);
                    break;
                default:
                    result = seenRepository.buscarPorFiltrosSinGenero(userId, filtro.getYear(), filtro.getVote(), startDate, endDate);
                    break;
            }
        } else {
            switch (orden) {
                case "asc":
                    result = seenRepository.buscarPorFiltrosConGeneroOrdenAsc(userId, filtro.getYear(), filtro.getVote(), generos, startDate, endDate);
                    break;
                case "desc":
                    result = seenRepository.buscarPorFiltrosConGeneroOrdenDesc(userId, filtro.getYear(), filtro.getVote(), generos, startDate, endDate);
                    break;
                default:
                    result = seenRepository.buscarPorFiltrosConGenero(userId, filtro.getYear(), filtro.getVote(), generos, startDate, endDate);
                    break;
            }
        }

        return result.stream().map(seenMapper::toDTO).collect(Collectors.toList());
    }

}




