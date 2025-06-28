// MIGUEL LABELLA : 60%
// OUAIL BOUAZZA MANSOURI : 40%

package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserMovieInteractionService {

    private final SeenRepository seenRepository;
    private final WatchlistRepository watchlistRepository;
    private final FavouritesRepository favouritesRepository;
    private final UsuarioRepository usuarioRepository;
    private final MovieRepository movieRepository;

    public UserMovieInteractionService(SeenRepository seenRepository,
                                       WatchlistRepository watchlistRepository,
                                       FavouritesRepository favouritesRepository,
                                       UsuarioRepository usuarioRepository,
                                       MovieRepository movieRepository) {
        this.seenRepository = seenRepository;
        this.watchlistRepository = watchlistRepository;
        this.favouritesRepository = favouritesRepository;
        this.usuarioRepository = usuarioRepository;
        this.movieRepository = movieRepository;
    }

    public String markAsWatched(Integer idMovie, String userEmail) {
        User user = usuarioRepository.findByEmail(userEmail);

        SeenId seenId = new SeenId();
        seenId.setMovieId(idMovie);
        seenId.setUserId(user.getId());

        boolean alreadySeen = seenRepository.existsById(seenId);
        if (alreadySeen) {
            return "La película fue marcada como vista anteriormente";
        }

        Movie movie = movieRepository.findById(idMovie).orElse(null);

        Seen seen = new Seen();
        seen.setMovie(movie);
        seen.setId(seenId);
        seen.setUser(user);
        seenRepository.save(seen);

        // Si estaba en watchlist, eliminarla
        Watchlist watchlist = watchlistRepository.findByUserIdAndMovieId(user.getId(), idMovie);
        if (watchlist != null) {
            watchlistRepository.delete(watchlist);
            return "La película fue marcada como pendiente anteriormente, a continuación se marcará como vista";
        }

        return null;
    }

    public String addToWatchlist(Integer idMovie, String userEmail) {
        User user = usuarioRepository.findByEmail(userEmail);

        WatchlistId watchlistId = new WatchlistId();
        watchlistId.setMovieId(idMovie);
        watchlistId.setUserId(user.getId());

        boolean alreadyPending = watchlistRepository.existsById(watchlistId);
        if (alreadyPending) {
            return "La película fue marcada como pendiente anteriormente";
        }

        Movie movie = movieRepository.findById(idMovie).orElse(null);

        Watchlist watchlist = new Watchlist();
        watchlist.setMovie(movie);
        watchlist.setId(watchlistId);
        watchlist.setUser(user);
        watchlistRepository.save(watchlist);

        // Si estaba vista, eliminarla
        SeenId seenId = new SeenId();
        seenId.setMovieId(idMovie);
        seenId.setUserId(user.getId());

        boolean alreadySeen = seenRepository.existsById(seenId);
        if (alreadySeen) {
            seenRepository.deleteById(seenId);
            return "La película fue marcada como vista anteriormente, a continuación se marcará como pendiente";
        }

        return null;
    }

    public String addToFavorites(Integer idMovie, String userEmail) {
        User user = usuarioRepository.findByEmail(userEmail);

        FavoriteId favoriteId = new FavoriteId();
        favoriteId.setMovieId(idMovie);
        favoriteId.setUserId(user.getId());

        boolean alreadyFavorite = favouritesRepository.existsById(favoriteId);
        if (alreadyFavorite) {
            return "La película ya está marcada como favorita";
        }

        Movie movie = movieRepository.findById(idMovie).orElse(null);

        Favorite favorite = new Favorite();
        favorite.setMovie(movie);
        favorite.setId(favoriteId);
        favorite.setUser(user);
        favouritesRepository.save(favorite);

        return null;
    }
}
