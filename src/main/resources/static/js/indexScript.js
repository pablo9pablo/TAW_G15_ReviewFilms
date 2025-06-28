//MANUEL GALÁN ALFARO: 100%
function scrollCarousel(direction, carouselId) {
    const carousel = document.getElementById(carouselId);
    if (!carousel) return;

    const card = carousel.querySelector('.movie-card');
    if (!card) return;

    const cardWidth = card.offsetWidth + 20;
    carousel.scrollBy({
        left: direction * cardWidth * 3,
        behavior: 'smooth'
    });
}

// Inicialización opcional para mejor experiencia
document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.carousel').forEach(carousel => {
        carousel.style.scrollSnapType = 'x mandatory';
    });
});