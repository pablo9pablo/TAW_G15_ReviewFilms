package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.*;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ReviewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;

import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieCastMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.BusquedaDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.FiltroDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Controller
@SessionAttributes("tempCastList")
public class ControllerMovie {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected ProductionCompanyRepository productionCompanyRepository;

    @Autowired
    protected SeenRepository seenRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    protected ActorRepository actorRepository;

    @Autowired
    protected MovieCastRepository movieCastRepository;

    @Autowired
    protected ReviewRepository reviewRepository;

    @Autowired
    protected WatchlistRepository watchlistRepository;

    @Autowired
    protected FavouritesRepository favouritesRepository;



    @GetMapping("/")
    public String index(Model model) {

        List<Movie> movieList = this.movieRepository.findAll();
        List<Movie> superheroeMovieList = this.movieRepository.findAllSuperheroMovies();

        model.addAttribute("movieList", movieList);
        model.addAttribute("superheroeMovieList", superheroeMovieList);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtro", new FiltroDTO());
        model.addAttribute("busqueda", new BusquedaDTO());

        return "index";
    }

    @ModelAttribute("tempCastList")
    public List<MovieCastDTO> tempCastList() {
        return new ArrayList<>();
    }


    @GetMapping("/viewmovie")
    public String verPelicula(@RequestParam("id") Integer id,
                              @AuthenticationPrincipal UserPrincipal userPrincipal,
                              Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) return "redirect:/";

        User user = userPrincipal.getUser();
        Optional<Integer> hasReview = Optional.empty();

        if (user != null){
            hasReview = reviewRepository.findReviewIdByUserAndMovie(user.getId(), id);
        }

        // Listas y conjuntos para las películas recomendadas
        List<Genre> genres = this.movieRepository.findGenresByMovieId(id);
        Set<Movie> relatedMoviesGenre = relatedMoviesGenre(genres, id);

        List<ProductionCompany> productionCompanies = this.movieRepository.findProductionCompanyByMovieId(id);
        Set<Movie> relatedMoviesProductionCompany = relatedMoviesProductionCompany(productionCompanies, id);

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setMovieDTO(MovieMapper.toDTO(movie));

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMoviesGenre", relatedMoviesGenre);
        model.addAttribute("relatedMoviesProductionCompany", relatedMoviesProductionCompany);
        model.addAttribute("review", reviewDTO);
        model.addAttribute("hasReview", hasReview);

        return "VerPelicula";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editmovie")
    public String crearMovie(@RequestParam(value = "id", defaultValue = "-1") Integer id,
                             Model model) {

        Movie movie = movieRepository.findById(id).orElse(new Movie());
        List<Genre> genre = genreRepository.findAll();
        List<ProductionCompany> pcompany = productionCompanyRepository.findAll();
        MovieDTO movieDTO = new MovieDTO();

        if(id!=-1){
             movieDTO = MovieMapper.toDTO(movie);

        }

        List<Actor> actores = actorRepository.findAll();
        model.addAttribute("actores", actores);

        MovieCastDTO movieCastDTO = new MovieCastDTO();


        model.addAttribute("movieCastDTO", movieCastDTO);

        model.addAttribute("movie", movie);
        model.addAttribute("MovieDTO", movieDTO);
        model.addAttribute("genre", genre);
        model.addAttribute("pcompany", pcompany);
        model.addAttribute("actores", actores);
        return "editarPelicula";

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/savemovie")
    public String guardarPelicula(
            @ModelAttribute MovieDTO movieDTO,
            @ModelAttribute("tempCastList") List<MovieCastDTO> tempCastList) {

        // Obtener la película a editar o crear una nueva
        Movie movie = movieDTO.getId() != null
                ? movieRepository.findById(movieDTO.getId()).orElse(new Movie())
                : new Movie();

        // Actualizar la entidad con los datos del DTO
        MovieMapper.updateEntity(movie, movieDTO,
                genreRepository.findAllById(movieDTO.getGenreIds()),
                productionCompanyRepository.findAllById(movieDTO.getProductionCompanyIds()));

        // Guardar película actualizada
        movie = movieRepository.save(movie);

        // Si hay reparto temporal, lo asociamos ahora que la película existe
        for (MovieCastDTO castDTO : tempCastList) {
            MovieCastId castId = new MovieCastId();
            castId.setMovieId(movie.getId());
            castId.setActorId(castDTO.getActorId());

            MovieCast movieCast = new MovieCast();
            movieCast.setId(castId);
            movieCast.setMovie(movie);
            movieCast.setActor(actorRepository.findById(castDTO.getActorId()).orElse(null));
            movieCast.setCharacter(castDTO.getCharacter());
            movieCast.setCreditId(castDTO.getCreditId());
            movieCast.setCreditOrder(castDTO.getCreditOrder());

            movieCastRepository.save(movieCast);
        }

        // Limpiar cast temporal
        tempCastList.clear();


        return "redirect:/editmovie?id=" + movie.getId();
    }




    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletemovie")
    public String eliminarPelicula(@RequestParam("id") Integer movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cast")
    public String castPelicula(
            @RequestParam(value = "id", required = false) Integer movieId,
            @ModelAttribute("tempCastList") List<MovieCastDTO> tempCastList,
            Model model) {

        Movie movie;
        List<MovieCastDTO> currentCast;

        if (movieId != null && movieId != -1) {
            movie = movieRepository.findById(movieId).orElse(null);
            currentCast = movie != null
                    ? movieCastRepository.findByMovie(movie).stream()
                    .map(mc -> {
                        MovieCastDTO dto = new MovieCastMapper().toDto(mc);
                        // Asegurar que el nombre del actor está cargado
                        dto.setActorName(mc.getActor() != null ? mc.getActor().getName() : "Desconocido");
                        return dto;
                    }).toList()
                    : new ArrayList<>();
        } else {
            movie = new Movie(); // película no guardada aún
            // Para cada MovieCastDTO temporal, obtener el nombre de actor de la BD
            currentCast = tempCastList.stream().map(c -> {
                Actor actor = actorRepository.findById(c.getActorId()).orElse(null);
                c.setActorName(actor != null ? actor.getName() : "Desconocido");
                return c;
            }).toList();
        }

        model.addAttribute("movie", movie);
        model.addAttribute("currentCast", currentCast);
        model.addAttribute("actores", actorRepository.findAll());

        MovieCastDTO dto = new MovieCastDTO();
        dto.setMovieId(movieId != null ? movieId : -1);
        model.addAttribute("movieCastDTO", dto);

        return "editarCast";
    }

    @PostMapping("/saveCast")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveCast(
            @ModelAttribute MovieCastDTO movieCastDTO,
            @ModelAttribute("tempCastList") List<MovieCastDTO> tempCastList,
            Model model) {

        if (movieCastDTO.getMovieId() == null || movieCastDTO.getMovieId() == -1) {
            // Si actor ya está en la lista temporal, actualizarlo
            tempCastList.removeIf(c -> c.getActorId().equals(movieCastDTO.getActorId()));
            tempCastList.add(movieCastDTO);
            return "redirect:/cast?id=-1";
        }

        // Persistir en BD
        Movie movie = movieRepository.findById(movieCastDTO.getMovieId()).orElse(null);
        Actor actor = actorRepository.findById(movieCastDTO.getActorId()).orElse(null);

        MovieCastId castId = new MovieCastId();
        castId.setMovieId(movieCastDTO.getMovieId());
        castId.setActorId(movieCastDTO.getActorId());

        MovieCast movieCast = new MovieCast();
        if(movieCastRepository.findById(castId)!=null) {
            movieCast = movieCastRepository.findById(castId);

        }


        movieCast.setId(castId);
        movieCast.setMovie(movie);
        movieCast.setActor(actor);
        movieCast.setCharacter(movieCastDTO.getCharacter());
        movieCast.setCreditOrder(movieCastDTO.getCreditOrder());
        movieCast.setCreditId(movieCastDTO.getCreditId());

        movieCastRepository.save(movieCast);

        return "redirect:/cast?id=" + movieCastDTO.getMovieId();
    }



    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteCast")
    public String deleteCast(
            @RequestParam("movieId") Integer movieId,
            @RequestParam("actorId") Integer actorId,
            @ModelAttribute("tempCastList") List<MovieCastDTO> tempCastList) {

        if (movieId == -1) {
            // Borrar de la lista temporal
            tempCastList.removeIf(c -> c.getActorId().equals(actorId));
            return "redirect:/cast?id=-1";
        }

        // Borrar de base de datos
        MovieCastId castId = new MovieCastId();
        castId.setMovieId(movieId);
        castId.setActorId(actorId);
        if (movieCastRepository.existsById(castId)) {
            movieCastRepository.deleteById(castId);
        }

        return "redirect:/cast?id=" + movieId;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cast/edit")
    public String editCast(
            @RequestParam("movieId") Integer movieId,
            @RequestParam("actorId") Integer actorId,
            @ModelAttribute("tempCastList") List<MovieCastDTO> tempCastList,
            Model model) {

        MovieCastDTO movieCastDTO;

        if (movieId == -1) {
            movieCastDTO = tempCastList.stream()
                    .filter(c -> c.getActorId().equals(actorId))
                    .findFirst()
                    .orElse(null);
        } else {
            MovieCastId castId = new MovieCastId();
            castId.setMovieId(movieId);
            castId.setActorId(actorId);
            MovieCast movieCast = new MovieCast();
            if(movieCastRepository.findById(castId)!=null) {
                movieCast = movieCastRepository.findById(castId);

            }

            movieCastDTO = movieCast != null ? new MovieCastMapper().toDto(movieCast) : null;
        }

        model.addAttribute("movieCastDTO", movieCastDTO);
        model.addAttribute("actores", actorRepository.findAll());

        return "editCast";
    }





    // Métodos auxiliares para la recomendación de películas
    public Set<Movie> relatedMoviesGenre(List<Genre> genres, Integer id) {
        Set<Movie> relatedMoviesGenre = new HashSet<>();

        for (Genre genre : genres) {
            relatedMoviesGenre.addAll(this.movieRepository.findMoviesByGenre(genre.getId(), id));
        }
        return relatedMoviesGenre;
    }

    public Set<Movie> relatedMoviesProductionCompany(List<ProductionCompany> productionCompanies, Integer id) {
        Set<Movie> relatedMoviesProductionCompany = new HashSet<>();

        for (ProductionCompany productionCompany : productionCompanies) {
            relatedMoviesProductionCompany.addAll(this.movieRepository.findMoviesByProductionCompany(productionCompany.getId(), id));
        }
        return relatedMoviesProductionCompany;
    }


    @PostMapping("/marcarComoVista")
    public String addToWatched(@RequestParam("idMovie") Integer idMovie, HttpSession session, Principal principal, Model model) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        SeenId seenId = new SeenId();
        seenId.setMovieId(idMovie);
        seenId.setUserId(user.getId());

        boolean alreadySeen = seenRepository.existsById(seenId);

        if (alreadySeen) {
            session.setAttribute("error", "La película fue marcada como vista anteriormente");
            return "redirect:/viewmovie?id=" + idMovie;
        }

        Movie movie = movieRepository.findById(idMovie).orElse(null);

            Seen seen = new Seen();
            seen.setMovie(movie);
            seen.setId(seenId);
            seen.setUser(user);
            seenRepository.save(seen);

            Watchlist watchlist = watchlistRepository.findByUserIdAndMovieId(user.getId(), idMovie);
            if (watchlist != null) {
                session.setAttribute("error", "La película fue marcada como pendiente anteriormente, a continuación se marcará como vista");
                watchlistRepository.delete(watchlist);
            }


        return "redirect:/viewmovie?id=" + idMovie;
    }


    /*
     *----------------------------MARCAR COMO PENDIENTE----------------------------------------------------------*
     */

    @PostMapping("/pendiente")
    public String addToPending(@RequestParam("idMovie") Integer idMovie, HttpSession session, Principal principal, Model model) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        WatchlistId watchlistId = new WatchlistId();
        watchlistId.setMovieId(idMovie);
        watchlistId.setUserId(user.getId());

        boolean alreadyPending = watchlistRepository.existsById(watchlistId);

        if (alreadyPending) {
            session.setAttribute("error", "La película fue marcada como pendiente anteriormente");
            return "redirect:/viewmovie?id=" + idMovie;
        }

        Movie movie = movieRepository.findById(idMovie).orElse(null);

            Watchlist watchlist = new Watchlist();
            watchlist.setMovie(movie);
            watchlist.setId(watchlistId);
            watchlist.setUser(user);
            watchlistRepository.save(watchlist);

            SeenId seenId = new SeenId();
            seenId.setMovieId(idMovie);
            seenId.setUserId(user.getId());

            boolean alreadySeen = seenRepository.existsById(seenId);
            if (alreadySeen) {
                session.setAttribute("error", "La película fue marcada como vista anteriormente, a continuación se marcará como pendiente");
                seenRepository.deleteById(seenId);
            }

            if (seenRepository.existsById(seenId)) {
                seenRepository.deleteById(seenId);
            }

        return "redirect:/viewmovie?id=" + idMovie;
    }

    /*
     *----------------------------MARCAR COMO FAVORITA----------------------------------------------------------*
     */

    @PostMapping("/favorita")
    public String addToFavorites(@RequestParam("idMovie") Integer idMovie, HttpSession session, Principal principal, Model model) {
        String email = principal.getName();
        User user = usuarioRepository.findByEmail(email);

        FavoriteId favoriteId = new FavoriteId();
        favoriteId.setMovieId(idMovie);
        favoriteId.setUserId(user.getId());

        boolean alreadyFavorite = favouritesRepository.existsById(favoriteId);

        if (alreadyFavorite) {
            session.setAttribute("error", "La película ya está marcada como favorita");
            return "redirect:/viewmovie?id=" + idMovie;
        }

        Movie movie = movieRepository.findById(idMovie).orElse(null);

        Favorite favorite = new Favorite();
        favorite.setMovie(movie);
        favorite.setId(favoriteId);
        favorite.setUser(user);
        favouritesRepository.save(favorite);

        return "redirect:/viewmovie?id=" + idMovie;
    }




    /*
     *---------------------------- BUSQUEDA----------------------------------------------------------*
     */

    protected String listarPeliculasConBusqueda(BusquedaDTO busqueda, Model model) {

        List<Movie> movieListBusqueda = this.movieRepository.buscarPorTitulo(busqueda.getTexto());
        model.addAttribute("movieList", movieListBusqueda);
        List<Movie> superheroeMovieList = this.movieRepository.findAllSuperheroMovies();


        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtro", new FiltroDTO());
        model.addAttribute("busqueda", new BusquedaDTO());
        model.addAttribute("superheroeMovieList", superheroeMovieList);


        return "index";


    }

    @PostMapping("/buscar")
    public String doBuscar(@ModelAttribute("busqueda") BusquedaDTO busqueda, Model model) {

        return this.listarPeliculasConBusqueda(busqueda, model);
    }

    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasConFiltrado(FiltroDTO filtro, Model model) {

        if (filtro == null) {
            filtro = new FiltroDTO();
        }
        if (filtro.getGeneroIds() == null) {
            filtro.setGeneroIds(new ArrayList<>());
        }

        List<Movie> superheroeMovieList = this.movieRepository.findAllSuperheroMovies();


        List<Movie> movies;
        LocalDate startDate = null;
        LocalDate endDate = null;

        if (filtro.getYear() != null) {
            startDate = getStartDateOfYear(filtro.getYear());
            endDate = getEndDateOfYear(filtro.getYear());
        }

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        if (filtro.getGeneroIds().isEmpty()) {
            movies = this.movieRepository.buscarPorFiltrosSinGenero(
                    filtro.getYear(), filtro.getVote(), startDate, endDate
            );
        } else {
            movies = this.movieRepository.buscarPorFiltrosConGenero(
                    filtro.getYear(), filtro.getVote(), filtro.getGeneroIds(), startDate, endDate
            );
        }

        model.addAttribute("movieList", movies);
        model.addAttribute("superheroeMovieList", superheroeMovieList);
        model.addAttribute("filtro", filtro);
        model.addAttribute("busqueda", new BusquedaDTO());


        return "index";
    }


    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") FiltroDTO filtro, Model model) {

        return this.listarPeliculasConFiltrado(filtro,model);
    }


    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }


}




