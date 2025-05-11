package es.uma.demoservice2025.trabajo_grupo_15_taw.controller;

import es.uma.demoservice2025.trabajo_grupo_15_taw.dao.*;

import es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*;

import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Busqueda;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.Filtro;
import es.uma.demoservice2025.trabajo_grupo_15_taw.ui.ReviewUI;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Controller
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
    protected ActorRepository actorRepository;

    @Autowired
    protected MovieCastRepository movieCast;

    @Autowired
    protected ReviewRepository reviewRepository;



    @GetMapping("/")
    public String index(Model model) {

        List<Movie> movieList = this.movieRepository.findAll();
        model.addAttribute("movieList", movieList);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtro", new Filtro());
        model.addAttribute("busqueda", new Busqueda());

        return "index";
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

        ReviewUI reviewUI = new ReviewUI();
        reviewUI.setMovieId(movie.getId());

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMoviesGenre", relatedMoviesGenre);
        model.addAttribute("relatedMoviesProductionCompany", relatedMoviesProductionCompany);
        model.addAttribute("reviewUI", reviewUI);
        model.addAttribute("hasReview", hasReview);

        return "VerPelicula";

    }

    @GetMapping("/editmovie")
    public String crearMovie(@RequestParam(value = "id", defaultValue = "-1") Integer id,
                             Model model) {

        Movie movie = movieRepository.findById(id).orElse(new Movie());
        List<Genre> genre = genreRepository.findAll();
        List<ProductionCompany> pcompany = productionCompanyRepository.findAll();
        List <Actor> actores = actorRepository.findAll();

        model.addAttribute("movie", movie);
        model.addAttribute("genre", genre);
        model.addAttribute("pcompany", pcompany);
        model.addAttribute("actores", actores);
        return "editarPelicula";

    }

    @PostMapping("/savemovie")
    @Transactional
    public String guardarPelicula(@RequestParam("id") Integer id,
                                  @RequestParam("title") String title,
                                  @RequestParam("originalTitle") String originalTitle,
                                  @RequestParam("releaseDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
                                  @RequestParam("runtime") Integer runtime,
                                  @RequestParam("budget") BigDecimal budget,
                                  @RequestParam("revenue") BigDecimal revenue,
                                  @RequestParam("originalLanguage") String originalLanguage,
                                  @RequestParam("overview") String overview,
                                  @RequestParam("imageUrl") String imageUrl,
                                  @RequestParam("generos") String[] generos,
                                  @RequestParam("pcompany") String[] pCompany,
                                  @RequestParam("actores") String[] actores
    ) {

        Movie movie = (id != null) ? movieRepository.findById(id).orElse(new Movie()) : new Movie();

        movie.setTitle(title);
        movie.setOriginalTitle(originalTitle);
        movie.setReleaseDate(releaseDate);
        movie.setRuntime(runtime);
        movie.setBudget(budget);
        movie.setRevenue(revenue);
        movie.setOriginalLanguage(originalLanguage);
        movie.setOverview(overview);
        movie.setImageUrl(imageUrl);

        // Asociar géneros
        Set<Genre> selectedGenres = new HashSet<>();
        for (String genreId : generos) {
            selectedGenres.add(genreRepository.findById(Integer.parseInt(genreId)).get());
        }
        movie.setGenres(selectedGenres);

        // Asociar compañías de producción
        Set<ProductionCompany> selectedCompanies = new HashSet<>();
        for (String pcompanyID : pCompany) {
            selectedCompanies.add(productionCompanyRepository.findById(Integer.parseInt(pcompanyID)).get());
        }
        movie.setProductionCompanies(selectedCompanies);


        movie = movieRepository.save(movie);  // Guardar nueva película


        // Eliminar actores antiguos solo si la película ya existe
        if (id != null) {
            movieCast.deleteByMovieId(id);  // Eliminar actores asociados a la película si ya existe
        }

        // Crear y guardar las relaciones de actores
        for (String actorId : actores) {
            Actor actor = actorRepository.findById(Integer.parseInt(actorId)).orElse(null);
            if (actor != null) {
                MovieCastId movieCastId = new MovieCastId();
                movieCastId.setMovieId(movie.getId());
                movieCastId.setActorId(Integer.parseInt(actorId));

                MovieCast mc = new MovieCast();
                mc.setId(movieCastId);  // Establecer la clave compuesta
                mc.setMovie(movie);  // Establecer la relación con la película
                mc.setActor(actor);  // Establecer la relación con el actor

                movieCast.save(mc);  // Guardar la relación MovieCast
            }
        }

        return "redirect:/";
    }


    @GetMapping("/deletemovie")
    public String eliminarPelicula(@RequestParam("id") Integer movieId) {
        //FALTA: Comprobacion de q este autenticado y de q tenga rol de editor
        movieRepository.deleteById(movieId);
        return "redirect:/";
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
    public String addToWatched(@RequestParam("id") Integer movieId, HttpSession session) {


        User user = (User) session.getAttribute("user");

        Seen seen = new Seen();
        SeenId seenId = new SeenId();

        Integer userId = user.getId();
        seenId.setMovieId(movieId);
        seenId.setUserId(userId);

        seen.setId(seenId);


        this.seenRepository.save(seen);

        return "VerPelicula";
    }


    /*
     *---------------------------- BUSQUEDA----------------------------------------------------------*
     */

    protected String listarPeliculasConBusqueda(Busqueda busqueda,Model model) {

        List<Movie> movieListBusqueda = this.movieRepository.buscarPorTitulo(busqueda.getTexto());
        model.addAttribute("movieList", movieListBusqueda);

        List<Genre> genreList = this.genreRepository.findAll();
        model.addAttribute("genreList", genreList);

        model.addAttribute("filtro", new Filtro());
        model.addAttribute("busqueda", new Busqueda());

        return "index";


    }

    @PostMapping("/buscar")
    public String doBuscar(@ModelAttribute("busqueda") Busqueda busqueda, Model model) {

        return this.listarPeliculasConBusqueda(busqueda, model);
    }

    /*
     *----------------------------------FILTRADO--------------------------------------------------------*
     */

    protected String listarPeliculasConFiltrado(Filtro filtro, Model model) {

        if (filtro == null) {
            filtro = new Filtro();
        }
        if (filtro.getGeneroIds() == null) {
            filtro.setGeneroIds(new ArrayList<>());
        }

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
        model.addAttribute("filtro", filtro);
        model.addAttribute("busqueda", new Busqueda());

        return "index";
    }


    @PostMapping("/filtrar")
    public String doFiltrar(@ModelAttribute("filtro") Filtro filtro, Model model) {

        return this.listarPeliculasConFiltrado(filtro,model);
    }


    public LocalDate getStartDateOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }


    public LocalDate getEndDateOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }


}




