/**
 * Minden HTML elemnek megfelel egy DOM osztály, amit saját komponens definiálásánál ki tudunk terjeszteni.
 * Autonóm elemeknél mivel nem kívánunk semmilyen megörökölni ezért csak a HTMLElement osztályt terjesztjük ki.
 */
class TextareaWithLength extends HTMLElement {
    textArea;
    lengthDisplay;

    constructor() {
        // В JS нет неявного супервызова
        super();
        this.textArea = this.querySelector('textarea');
        this.lengthDisplay = document.createElement('span');
        this.textArea.addEventListener('input', this.onInput);

        // или если onInput вместо простого метода (функционального литерала), тогда
        // this.textArea.addEventListener('input', this.onInput.bind(this));

        // искусственная генерация событий
        this.textArea.dispatchEvent(new Event('input'));
        this.append(this.lengthDisplay);
    }

    /*
    anonim függvény nem változtatja meg a this mire köt (ha csak egy sima metódus lenne ez akkor a 14. sorban lévő
    eseménykezelő megváltoztatná a this-t az esemény célpontjára
     */
    onInput = () => {
        this.lengthDisplay.innerHTML = this.textArea.value.length.toString();
    }
}

//
customElements.define('textarea-with-length', TextareaWithLength)