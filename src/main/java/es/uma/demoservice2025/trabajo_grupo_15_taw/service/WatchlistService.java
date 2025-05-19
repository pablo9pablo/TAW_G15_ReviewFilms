package es.uma.demoservice2025.trabajo_grupo_15_taw.service;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.WatchlistRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.WatchlistDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.WatchlistMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private WatchlistMapper watchlistMapper;

    public List<WatchlistDTO> filterWatchlistByUserEmailAndFiltro(String email, Filtro filtro, String orden) {
        User user = usuarioRepository.findByEmail(email);
        if (user == null) {
            return List.of();
        }

        LocalDate startDate = filtro.getYear() != null ? LocalDate.of(filtro.getYear(), Month.JANUARY, 1) : null;
        LocalDate endDate = filtro.getYear() != null ? LocalDate.of(filtro.getYear(), Month.DECEMBER, 31) : null;

        List<Integer> generos = filtro.getGeneroIds();
        if (generos != null && generos.isEmpty()) generos = null;

        List<Watchlist> result;

        if (generos == null) {
            switch (orden != null ? orden : "") {
                case "asc":
                    result = watchlistRepository.buscarPorFiltrosSinGeneroOrdenAsc(filtro.getYear(), filtro.getVote(), startDate, endDate);
                    break;
                case "desc":
                    result = watchlistRepository.buscarPorFiltrosSinGeneroOrdenDesc(filtro.getYear(), filtro.getVote(), startDate, endDate);
                    break;
                default:
                    result = watchlistRepository.buscarPorFiltrosSinGenero(filtro.getYear(), filtro.getVote(), startDate, endDate);
            }
        } else {
            switch (orden != null ? orden : "") {
                case "asc":
                    result = watchlistRepository.buscarPorFiltrosConGeneroOrdenAsc(filtro.getYear(), filtro.getVote(), generos, startDate, endDate);
                    break;
                case "desc":
                    result = watchlistRepository.buscarPorFiltrosConGeneroOrdenDesc(filtro.getYear(), filtro.getVote(), generos, startDate, endDate);
                    break;
                default:
                    result = watchlistRepository.buscarPorFiltrosConGenero(filtro.getYear(), filtro.getVote(), generos, startDate, endDate);
            }
        }

        return result.stream()
                .filter(w -> w.getUser().getId().equals(user.getId()))
                .map(watchlistMapper::toDTO)
                .collect(Collectors.toList());
    }
}
