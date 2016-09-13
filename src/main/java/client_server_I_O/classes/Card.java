package client_server_I_O.classes;

import java.io.Serializable;

public class Card implements Serializable {
    static final long serialVersionUID = 4566843898265039363L;

    CardElement[][] elements = new CardElement[7][7];

    public Card() {

    }

    public CardElement[][] getElements() {
        return elements;
    }
    public void setElements(CardElement[][] elements) {
        this.elements = elements;
    }

}
