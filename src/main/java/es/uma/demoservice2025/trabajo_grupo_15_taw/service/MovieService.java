package es.uma.demoservice2025.trabajo_grupo_15_taw.service;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO;
import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.CrewMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.GenreMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieCastMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.mapper.MovieMapper;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Busqueda;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ProductionCompanyRepository productionCompanyRepository;
    private final CrewRepository crewRepository;
    private final ReviewRepository reviewRepository;
    private final MovieCastRepository movieCastRepository;

    public MovieService(MovieRepository movieRepository,
                        GenreRepository genreRepository,
                        ProductionCompanyRepository productionCompanyRepository,
                        CrewRepository crewRepository,
                        ReviewRepository reviewRepository,
                        ActorRepository actorRepository,
                        MovieCastRepository movieCastRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
        this.productionCompanyRepository = productionCompanyRepository;
        this.crewRepository = crewRepository;
        this.reviewRepository = reviewRepository;
        this.movieCastRepository = movieCastRepository;
    }

    // ============================================================================
    // GESTIÓN BÁSICA DE PELÍCULAS
    // ============================================================================

    public MovieDetailsResult getMovieDetails(Integer id, User user) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }

        Optional<Integer> hasReview = Optional.empty();
        if (user != null) {
            hasReview = reviewRepository.findReviewIdByUserAndMovie(user.getId(), id);
        }

        List<Genre> genres = movieRepository.findGenresByMovieId(id);
        Set<Movie> relatedMoviesGenre = getRelatedMoviesByGenre(genres, id);

        List<ProductionCompany> productionCompanies = movieRepository.findProductionCompanyByMovieId(id);
        Set<Movie> relatedMoviesProductionCompany = getRelatedMoviesByProductionCompany(productionCompanies, id);

        return new MovieDetailsResult(movie, hasReview, relatedMoviesGenre, relatedMoviesProductionCompany);
    }

    public Movie getMovieOrNew(Integer id) {
        return movieRepository.findById(id).orElse(new Movie());
    }

    public MovieDTO getMovieDTO(Movie movie, boolean isNew) {
        if (!isNew) {
            return MovieMapper.toDTO(movie);
        }

        MovieDTO dto = new MovieDTO();
        dto.setGenreIds(new ArrayList<>());
        dto.setProductionCompanyIds(new ArrayList<>());
        dto.setCrewIds(new ArrayList<>());
        dto.setActorIds(new ArrayList<>());
        return dto;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<ProductionCompany> getAllProductionCompanies() {
        return productionCompanyRepository.findAll();
    }

    public List<Crew> getAllCrew() {
        return crewRepository.findAll();
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Integer saveMovie(MovieDTO movieDTO) {
        Movie movie = movieDTO.getId() != null
                ? movieRepository.findById(movieDTO.getId()).orElse(new Movie())
                : new Movie();

        List<Genre> genres = getValidatedGenres(movieDTO.getGenreIds());
        List<ProductionCompany> companies = getValidatedCompanies(movieDTO.getProductionCompanyIds());

        MovieMapper.updateEntity(movie, movieDTO, genres, companies);

        movie = movieRepository.save(movie);

        updateCrewRelations(movie, movieDTO.getCrewIds());

        return movie.getId();
    }

    public void deleteMovieById(Integer movieId) {
        movieRepository.deleteById(movieId);
    }

    // ============================================================================
    // GESTIÓN DE REPARTO Y EQUIPO TÉCNICO
    // ============================================================================

    public void cargarReparto(Integer movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        MovieDTO movieDTO = MovieMapper.toDTO(movie);
        List<MovieCastDTO> repartoDTO = movie.getMovieCasts().stream()
                .map(new MovieCastMapper()::toDto)
                .collect(Collectors.toList());

        model.addAttribute("movie", movieDTO);
        model.addAttribute("cast", repartoDTO);
    }

    public void cargarEquipoTecnico(Integer movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        MovieDTO movieDTO = MovieMapper.toDTO(movie);
        List<CrewDTO> equipo = crewRepository.findByMoviesId(movieId).stream()
                .map(CrewMapper::toDto)
                .collect(Collectors.toList());

        model.addAttribute("movie", movieDTO);
        model.addAttribute("crew", equipo);
    }

    public void cargarGeneros(Integer movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        MovieDTO movieDTO = MovieMapper.toDTO(movie);
        List<GenreDTO> dtoList = genreRepository.findByMovies_Id(movieId)
                .stream()
                .map(GenreMapper::toDto)
                .toList();

        model.addAttribute("movie", movieDTO);
        model.addAttribute("genres", dtoList);
    }

    // ============================================================================
    // GESTIÓN DE CAST (REPARTO) - SIMPLIFICADO
    // ============================================================================

    public void prepararCast(Integer movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            throw new RuntimeException("Película no encontrada con ID: " + movieId);
        }

        List<MovieCastDTO> currentCast = movieCastRepository.findByMovie(movie).stream()
                .map(mc -> {
                    MovieCastDTO dto = new MovieCastMapper().toDto(mc);
                    dto.setActorName(getActorNameById(mc.getActor()));
                    return dto;
                }).toList();

        model.addAttribute("movie", movie);
        model.addAttribute("currentCast", currentCast);
        model.addAttribute("actores", actorRepository.findAll());

        MovieCastDTO dto = new MovieCastDTO();
        dto.setMovieId(movieId);
        model.addAttribute("movieCastDTO", dto);
    }

    public String guardarCast(MovieCastDTO dto) {
        Movie movie = movieRepository.findById(dto.getMovieId()).orElse(null);
        Actor actor = actorRepository.findById(dto.getActorId()).orElse(null);

        if (movie == null || actor == null) {
            throw new RuntimeException("Película o actor no encontrado");
        }

        MovieCastId castId = new MovieCastId(dto.getMovieId(), dto.getActorId());
        MovieCast movieCast = buildMovieCast(dto, movie, actor, castId);

        movieCastRepository.save(movieCast);

        return "redirect:/cast?id=" + dto.getMovieId();
    }

    public String eliminarCast(Integer movieId, Integer actorId) {
        MovieCastId castId = new MovieCastId(movieId, actorId);
        if (movieCastRepository.existsById(castId)) {
            movieCastRepository.deleteById(castId);
        }

        return "redirect:/cast?id=" + movieId;
    }

    public void prepararEdicionCast(Integer movieId, Integer actorId, Model model) {
        MovieCastId castId = new MovieCastId(movieId, actorId);
        MovieCastDTO dto = movieCastRepository.findById(castId)
                .map(mc -> new MovieCastMapper().toDto(mc))
                .orElse(null);

        if (dto == null) {
            throw new RuntimeException("Cast no encontrado");
        }

        model.addAttribute("movieCastDTO", dto);
        model.addAttribute("actores", actorRepository.findAll());
    }

    // ============================================================================
    // RECOMENDACIONES
    // ============================================================================

    public DashboardData getDashboardData() {
        List<Movie> movieList = movieRepository.findAll();
        List<Movie> bestRatingMovieList = movieRepository.findTopMovies(7.0);
        List<Movie> moreCommentedMovieList = movieRepository.findMostReviewsMovies(8000);
        List<Movie> blockbusters = movieRepository.findBlockbusters(1081000000.00);
        List<Movie> releasesTenYearsMovieList = movieRepository.findTenYearsReleases(LocalDate.of(2015, 1, 1));
        List<Movie> superheroeMovieList = movieRepository.findAllMoviesByGenre(21);
        List<Movie> dramaMovieList = movieRepository.findAllMoviesByGenre(1);
        List<Movie> comedyMovieList = movieRepository.findAllMoviesByGenre(8);
        List<Movie> warMovieList = movieRepository.findAllMoviesByGenre(17);
        List<Movie> basedTrueStoryMovieList = movieRepository.findMoviesByKeywordIds(List.of(63));
        List<Genre> genreList = genreRepository.findAll();

        return new DashboardData(
                movieList, bestRatingMovieList, moreCommentedMovieList, blockbusters,
                releasesTenYearsMovieList, superheroeMovieList, dramaMovieList,
                comedyMovieList, warMovieList, basedTrueStoryMovieList, genreList
        );
    }

    // ============================================================================
    // BÚSQUEDA DE PELÍCULAS
    // ============================================================================

    public String listarPeliculasConBusqueda(Busqueda busqueda, Model model) {
        List<Movie> movieListBusqueda = this.movieRepository.buscarPorTitulo(busqueda.getTexto());
        model.addAttribute("movieList", movieListBusqueda);

        setupDashboardModel(model);
        return "index";
    }

    // ============================================================================
    // FILTRADO DE PELÍCULAS
    // ============================================================================

    public String listarPeliculasConFiltrado(Filtro filtro, Model model) {
        if (filtro == null) {
            filtro = new Filtro();
        }
        if (filtro.getGeneroIds() == null) {
            filtro.setGeneroIds(new ArrayList<>());
        }

        List<Movie> movies;
        LocalDate[] dateRange = getYearDateRange(filtro.getYear());

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        if (filtro.getGeneroIds().isEmpty()) {
            movies = this.movieRepository.buscarPorFiltrosSinGenero(
                    filtro.getYear(), filtro.getVote(), dateRange[0], dateRange[1]
            );
        } else {
            movies = this.movieRepository.buscarPorFiltrosConGenero(
                    filtro.getYear(), filtro.getVote(), filtro.getGeneroIds(), dateRange[0], dateRange[1]
            );
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("movieList", movies);

        DashboardData dashboardData = getDashboardData();
        addDashboardToModel(model, dashboardData);
        addSearchAndFilterAttributes(model);

        return "index";
    }

    // ============================================================================
    // METODOS AUXILIARES
    // ============================================================================

    private Set<Movie> getRelatedMoviesByGenre(List<Genre> genres, Integer id) {
        Set<Movie> relatedMoviesGenre = new HashSet<>();
        for (Genre genre : genres) {
            relatedMoviesGenre.addAll(movieRepository.findMoviesByGenre(genre.getId(), id));
        }
        return relatedMoviesGenre;
    }

    private Set<Movie> getRelatedMoviesByProductionCompany(List<ProductionCompany> productionCompanies, Integer id) {
        Set<Movie> relatedMoviesProductionCompany = new HashSet<>();
        for (ProductionCompany productionCompany : productionCompanies) {
            relatedMoviesProductionCompany.addAll(movieRepository.findMoviesByProductionCompany(productionCompany.getId(), id));
        }
        return relatedMoviesProductionCompany;
    }

    private List<Genre> getValidatedGenres(List<Integer> genreIds) {
        return (genreIds != null && !genreIds.isEmpty())
                ? genreRepository.findAllById(genreIds)
                : new ArrayList<>();
    }

    private List<ProductionCompany> getValidatedCompanies(List<Integer> companyIds) {
        return (companyIds != null && !companyIds.isEmpty())
                ? productionCompanyRepository.findAllById(companyIds)
                : new ArrayList<>();
    }

    private void updateCrewRelations(Movie movie, List<Integer> crewIds) {
        if (movie.getId() != null) {
            List<Crew> existingCrew = crewRepository.findByMoviesId(movie.getId());
            for (Crew crew : existingCrew) {
                crew.getMovies().remove(movie);
            }
            crewRepository.saveAll(existingCrew);
        }

        if (crewIds != null && !crewIds.isEmpty()) {
            List<Crew> selectedCrew = crewRepository.findAllById(crewIds);
            for (Crew crew : selectedCrew) {
                crew.getMovies().add(movie);
            }
            crewRepository.saveAll(selectedCrew);
        }
    }

    private String getActorNameById(Actor actor) {
        return actor != null ? actor.getName() : "Desconocido";
    }

    private String getActorNameById(Integer actorId) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        return getActorNameById(actor);
    }

    private MovieCast buildMovieCast(MovieCastDTO dto, Movie movie, Actor actor, MovieCastId castId) {
        MovieCast movieCast = movieCastRepository.findById(castId).orElse(new MovieCast());
        movieCast.setId(castId);
        movieCast.setMovie(movie);
        movieCast.setActor(actor);
        movieCast.setCharacter(dto.getCharacter());
        movieCast.setCreditOrder(dto.getCreditOrder());
        movieCast.setCreditId(dto.getCreditId());
        return movieCast;
    }

    private LocalDate[] getYearDateRange(Integer year) {
        if (year != null) {
            return new LocalDate[]{
                    LocalDate.of(year, Month.JANUARY, 1),
                    LocalDate.of(year, Month.DECEMBER, 31)
            };
        }
        return new LocalDate[]{null, null};
    }

    private void setupDashboardModel(Model model) {
        DashboardData dashboardData = getDashboardData();
        addDashboardToModel(model, dashboardData);
        addSearchAndFilterAttributes(model);
    }

    private void addSearchAndFilterAttributes(Model model) {
        model.addAttribute("filtro", new Filtro());
        model.addAttribute("busqueda", new Busqueda());
    }

    private void addDashboardToModel(Model model, DashboardData data) {
        model.addAttribute("bestRatingMovieList", data.bestRatingMovieList);
        model.addAttribute("moreCommentedMovieList", data.moreCommentedMovieList);
        model.addAttribute("blockbusters", data.blockbusters);
        model.addAttribute("releasesTenYearsMovieList", data.releasesTenYearsMovieList);
        model.addAttribute("superheroeMovieList", data.superheroeMovieList);
        model.addAttribute("dramaMovieList", data.dramaMovieList);
        model.addAttribute("comedyMovieList", data.comedyMovieList);
        model.addAttribute("warMovieList", data.warMovieList);
        model.addAttribute("basedTrueStoryMovieList", data.basedTrueStoryMovieList);
        model.addAttribute("genreList", data.genreList);
    }
}