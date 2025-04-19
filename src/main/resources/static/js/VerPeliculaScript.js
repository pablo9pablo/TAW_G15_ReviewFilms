const stars = document.querySelectorAll('.star-rating .star');
const ratingInput = document.getElementById('rating');
let selectedValue = 0; // Guarda la puntuación seleccionada

stars.forEach(star => {
    // Hover: resalta temporalmente
    star.addEventListener('mouseenter', () => {
        const value = parseInt(star.getAttribute('data-value'));
        stars.forEach(s => {
            s.classList.toggle('hovered', parseInt(s.getAttribute('data-value')) <= value);
        });
    });

    // Salir del hover: vuelve a mostrar solo las seleccionadas
    star.addEventListener('mouseleave', () => {
        stars.forEach(s => s.classList.remove('hovered'));
        stars.forEach(s => {
            s.classList.toggle('selected', parseInt(s.getAttribute('data-value')) <= selectedValue);
        });
    });

    // Click: guarda el valor y marca las estrellas seleccionadas
    star.addEventListener('click', () => {
        selectedValue = parseInt(star.getAttribute('data-value'));
        ratingInput.value = selectedValue;

        stars.forEach(s => {
            s.classList.remove('selected');
            if (parseInt(s.getAttribute('data-value')) <= selectedValue) {
                s.classList.add('selected');
            }
        });
    });
});