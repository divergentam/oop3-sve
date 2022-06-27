package rs.ac.ni.oop3.tamara333.predavanja_18_3;

/*Exception mora da se obradi */

import rs.ac.ni.oop3.tamara333.predavanja_18_3.BadValueException;

public class ForbiddenValueException extends BadValueException { /*Moze da nasledi i RunTimeException*/
    public ForbiddenValueException(String message) {
        super(message);
    }
}
