function scrollCarousel(direction) {
    const container = document.getElementById('carousel');
    const scrollAmount = 300;
    container.scrollBy({ left: direction * scrollAmount, behavior: 'smooth' });
}