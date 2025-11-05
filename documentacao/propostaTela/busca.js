const btnPesquisar = document.getElementById('btnPesquisar');
const searchMain = document.getElementById('search');
let isVisible = false;

btnPesquisar.addEventListener('click', () => {
    if (!isVisible) {
        // Mostra com fade-in
        searchMain.classList.remove('fade-out');
        searchMain.style.display = 'block';
        void searchMain.offsetWidth;
        searchMain.classList.add('fade-in');
        isVisible = true;
    } else {
        // Esconde com fade-out
        searchMain.classList.remove('fade-in');
        void searchMain.offsetWidth;
        searchMain.classList.add('fade-out');
        // Após o fim da animação, realmente esconde o elemento
        searchMain.addEventListener('animationend', () => {
            if (!isVisible) searchMain.style.display = 'none';
        }, { once: true });
        isVisible = false;
    }
    });