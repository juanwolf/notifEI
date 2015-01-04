package model;

import java.math.BigDecimal;
import javax.ejb.Remote;

@Remote 
/*Indique que les beans qui implanteront cette interface 
/*pourront �tre accessibles depuis des clients s�ex�cutant sur d�autres JVM, 
/*et donc �galement sur d�autres machines.*/

public interface Converter {
 public BigDecimal dollarToYen(BigDecimal dollars);
 
 public BigDecimal yenToEuro(BigDecimal yen);
}

