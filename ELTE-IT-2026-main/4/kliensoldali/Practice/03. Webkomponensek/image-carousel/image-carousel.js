class ImageCarousel extends HTMLDivElement {
    images;
    currentImage;

    constructor() {
        super();

        this.attachShadow({mode: 'open'});
        // мы создаем теневой DOM
        // open означает, что мы можем получить доступ к теневому DOM снаружи с помощью JS.

        this.shadowRoot.append(this.firstElementChild);
        // переместите <ul> в теневой DOM

        const style = document.createElement('link');
        style.rel = 'stylesheet';
        style.href = './image-carousel.css';
        this.shadowRoot.append(style);
        // стиль влияет только на теневой DOM

        this.currentImage = 0;
        this.images = this.shadowRoot.querySelectorAll('li img');
        this.images.forEach(image => this.hide(image));

        window.addEventListener('keydown', this.carouselMove);
    }

    hide(image) {
        image.style.display = 'none';
    }

    show(image) {
        image.style.display = '';
    }


    carouselMove = (e) => {
        this.hide(this.images[this.currentImage]);

        switch (e.key) {
            case 'Left': // Специальное значение IE/Edge
            case 'ArrowLeft':
                this.currentImage--;
                break;
            case 'Right': // Специальное значение IE/Edge
            case 'ArrowRight':
                this.currentImage++;
                break;
            default:
                return;
        }

        // злой код
        this.currentImage = ((this.currentImage % this.images.length) + this.images.length) % this.images.length;
        this.show(this.images[this.currentImage]);

        e.preventDefault();
    };
}

customElements.define('image-carousel', ImageCarousel, {extends: 'div'});