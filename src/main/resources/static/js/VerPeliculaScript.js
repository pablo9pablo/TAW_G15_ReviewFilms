//MANUEL GALÁN ALFARO: 100%

const CAROUSEL_CONFIG = {
    moviesPerPage: 4,
    scrollBehavior: 'smooth'
};

function initCarousels() {
    document.querySelectorAll('.carousel').forEach(carousel => {
        const movieCards = carousel.querySelectorAll('.movie-card');
        const cardWidth = movieCards[0]?.offsetWidth + 20;

        if (cardWidth) {
            const pageWidth = cardWidth * CAROUSEL_CONFIG.moviesPerPage;

            carousel.style.maxWidth = `${pageWidth}px`;

            carousel.style.overflow = 'hidden';

            carousel.parentElement.style.overflow = 'hidden';
        }
    });
}

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

document.addEventListener('DOMContentLoaded', initCarousels);
