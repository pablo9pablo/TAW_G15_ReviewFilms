package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.FavouritesRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.GenreRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.UsuarioRepository;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.FavoriteMovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.User;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.FavoriteMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FavoriteService {

    @Autowired
    protected FavouritesRepository favouritesRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected FavoriteMapper favoriteMapper;

    public List<FavoriteMovieDTO> getFavoritesByUser(String email) {
        User user = usuarioRepository.findByEmail(email);
        List<Favorite> favourites = favouritesRepository.findByUserId(user.getId());
        return favourites.stream().map(favoriteMapper::toDTO).collect(Collectors.toList());
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public void removeFavoriteMovie(String email, Integer movieId) {
        User user = usuarioRepository.findByEmail(email);
        Favorite favorite = favouritesRepository.findByUserIdAndMovieId(user.getId(), movieId);
        if (favorite != null) {
            favouritesRepository.delete(favorite);
        }
    }

    public List<FavoriteMovieDTO> filterFavorites(String email, Filtro filtro, String orden) {
        User user = usuarioRepository.findByEmail(email);
        Integer userId = user.getId();

        LocalDate startDate = null;
        LocalDate endDate = null;
        if (filtro != null && filtro.getYear() != null) {
            startDate = LocalDate.of(filtro.getYear(), Month.JANUARY, 1);
            endDate = LocalDate.of(filtro.getYear(), Month.DECEMBER, 31);
        }

        List<Integer> generos = filtro != null ? filtro.getGeneroIds() : null;
        if (generos != null && generos.isEmpty()) generos = null;

        List<Favorite> result;
        String ordenSeguro = orden != null ? orden : "";

        if (generos == null) {
            switch (ordenSeguro) {
                case "asc":
                    result = favouritesRepository.buscarPorFiltrosSinGeneroOrdenAscParaUsuario(filtro.getYear(), filtro.getVote(), startDate, endDate, userId);
                    break;
                case "desc":
                    result = favouritesRepository.buscarPorFiltrosSinGeneroOrdenDescParaUsuario(filtro.getYear(), filtro.getVote(), startDate, endDate, userId);
                    break;
                default:
                    result = favouritesRepository.buscarPorFiltrosSinGeneroParaUsuario(filtro.getYear(), filtro.getVote(), startDate, endDate, userId);
            }
        } else {
            switch (ordenSeguro) {
                case "asc":
                    result = favouritesRepository.buscarPorFiltrosConGeneroOrdenAscParaUsuario(filtro.getYear(), filtro.getVote(), generos, startDate, endDate, userId);
                    break;
                case "desc":
                    result = favouritesRepository.buscarPorFiltrosConGeneroOrdenDescParaUsuario(filtro.getYear(), filtro.getVote(), generos, startDate, endDate, userId);
                    break;
                default:
                    result = favouritesRepository.buscarPorFiltrosConGeneroParaUsuario(filtro.getYear(), filtro.getVote(), generos, startDate, endDate, userId);
            }
        }

        return result.stream().map(favoriteMapper::toDTO).collect(Collectors.toList());
    }
}
