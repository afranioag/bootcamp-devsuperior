package tests.entities;

import entitys.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.factory.AccountFactory;

public class AccountTests {

    @Test
    public void depositoComDesconto(){
        double amount = 200.0;
        double expected = 196.0;
        Account acc = AccountFactory.createEmptyAccount();

        acc.deposit(amount);

        Assertions.assertEquals(expected, acc.getBalance());
    }

    @Test
    public void depositoNaoPodeAcontecerComValorNegativo(){
        double valorEsperado = 100.0;
        Account acc = AccountFactory.createAccount(valorEsperado);
        double deposito = -200.0;

        acc.deposit(deposito);

        Assertions.assertEquals(valorEsperado, acc.getBalance());
    }

    @Test
    public void deveriaLimparOSaldo(){
        double valorEsperado = 0.0;
        double initialBalance = 800.0;
        Account acc = AccountFactory.createAccount(initialBalance);
        double result = acc.fullWithdraw();

        Assertions.assertEquals(valorEsperado, acc.getBalance());
        Assertions.assertTrue(initialBalance == result);
    }

    @Test
    public void saqueTest(){
        Account acc = AccountFactory.createAccount(800.00);
        acc.whithdraw(300.00);
        Assertions.assertEquals(500.00, acc.getBalance());

    }

    @Test
    public void saqueInvalidoTest(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Account acc = AccountFactory.createAccount(800.00);
            acc.whithdraw(900.00);
        });
    }
}

