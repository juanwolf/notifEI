package model;

import java.math.BigDecimal;
import javax.ejb.Remote;

@Remote 
/*Indique que les beans qui implanteront cette interface 
/*pourront être accessibles depuis des clients s’exécutant sur d’autres JVM, 
/*et donc également sur d’autres machines.*/

public interface Converter {
 public BigDecimal dollarToYen(BigDecimal dollars);
 
 public BigDecimal yenToEuro(BigDecimal yen);
}

