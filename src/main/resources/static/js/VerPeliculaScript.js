// Configuración del carrusel
const CAROUSEL_CONFIG = {
    moviesPerPage: 4,       // Número de películas a mostrar por página
    scrollBehavior: 'smooth' // Comportamiento del scroll
};

// Función para inicializar los carruseles
function initCarousels() {
    document.querySelectorAll('.carousel').forEach(carousel => {
        const movieCards = carousel.querySelectorAll('.movie-card');
        const cardWidth = movieCards[0]?.offsetWidth + 20; // Ancho de tarjeta + gap

        if (cardWidth) {
            // Calcula el ancho total visible por página
            const pageWidth = cardWidth * CAROUSEL_CONFIG.moviesPerPage;

            // Aplica el ancho máximo por página
            carousel.style.maxWidth = `${pageWidth}px`;

            // Oculta el scrollbar
            carousel.style.overflow = 'hidden';

            // Ajusta el contenedor interno para evitar desbordamiento
            carousel.parentElement.style.overflow = 'hidden';
        }
    });
}
// Función modificada para el scroll del carrusel
function scrollCarousel(direction, carouselId) {
    const container = document.getElementById(carouselId || 'carousel');

    if (!container) {
        return;
    }

    const movieCards = container.querySelectorAll('.movie-card');
    if (movieCards.length === 0){
        return;
    }

    const cardWidth = movieCards[0].offsetWidth + 20; // Ancho + gap
    const scrollAmount = cardWidth * CAROUSEL_CONFIG.moviesPerPage;

    container.scrollBy({
        left: direction * scrollAmount,
        behavior: CAROUSEL_CONFIG.scrollBehavior
    });
}

// Inicializar los carruseles cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', initCarousels);
