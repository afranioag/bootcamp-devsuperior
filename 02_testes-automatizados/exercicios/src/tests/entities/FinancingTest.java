package tests.entities;

import entities.Financing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTest {

    @Test
    public void deveCriarComSucessoUmFinanciamento() {
            Financing financing = new Financing(10000.0, 1500.0, 15);
            Assertions.assertNotNull(financing);
    }

    @Test
    public void deveFalharAoPassarValoresIncompativeis() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(10000.0, 1500.0, 2);
        });
    }

    @Test
    public void deveAlterarAmount() {
        Financing financing = new Financing(10000.0, 1500.0, 15);
        financing.setTotalAmount(12000.00);
        Assertions.assertEquals(financing.getTotalAmount(), 12000.00);
    }

    @Test
    public void deveFalharAoPassarAmountIncompativel() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(10000.0, 1500.0, 20);
            financing.setTotalAmount(50000.00);
        });
    }

    @Test
    public void deveAlterarIncome() {
        Financing financing = new Financing(10000.0, 1500.0, 20);
        financing.setIncome(2000.00);
        Assertions.assertEquals(financing.getIncome(), 2000.00);
    }

    @Test
    public void deveFalharAoPassarIncomeIncompativel() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(10000.0, 1500.0, 15);
            financing.setIncome(800.00);
        });
    }

    @Test
    public void deveAlterarMonths() {
        Financing financing = new Financing(10000.0, 1500.0, 15);
        financing.setMonths(20);
        Assertions.assertEquals(financing.getMonths(), 20);
    }

    @Test
    public void deveFalharAoPassarMonthsIncompativel() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Financing financing = new Financing(10000.0, 1500.0, 15);
            financing.setMonths(10);
        });
    }

    @Test
    public void deveCalcularCorretamenteOvalorDaEntrada(){
        Financing financing = new Financing(10000.0, 1500.0, 15);
        Assertions.assertEquals(financing.entry(), 2000.0);
    }

    @Test
    public void deveCalcularCorretamenteOvalorDaPrestacao(){
        Financing financing = new Financing(10000.0, 1500.0, 16);

        Assertions.assertEquals(financing.quota(), 500.00);
    }
}
