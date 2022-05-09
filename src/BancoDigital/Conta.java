package BancoDigital;

import java.util.Scanner;

public abstract class Conta implements interfaceConta {
    private static final int AGENCIA_PADRAO = 001;
    private static int SEQUENCIAL = 100;
    protected int agencia;
    protected int numeroConta;
    protected double saldo;
    protected Cliente cliente;
    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numeroConta = SEQUENCIAL++;
        this.cliente = cliente;
    }
    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo Insuficiente, saldo atual: R$ " + saldo);
        } else {
            saldo -= valor;
        }
    }
    @Override
    public void depositar(double valor) {
        saldo += valor;
    }
    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (valor > saldo) {
            System.out.println("Operação não realizada.");
            saldo = saldo;
        } else {
            sacar(valor);
            contaDestino.depositar(valor);
        }
    }
    public int getAgencia() {
        return agencia;
    }
    public int getNumeroConta() {
        return numeroConta;
    }
    public double getSaldo() {
        return saldo;
    }
    protected void ImprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Número da Conta: %d", this.numeroConta));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
    protected static int getOp(Scanner scan) {
        System.out.println("------- Sejá Bem Vindo -----------");
        System.out.println("Informe operação deseja:\n1- Saque\n2- Deposito\n3- Transferir");
        int operacao = scan.nextInt();
        if (operacao == 1 || operacao == 2 || operacao == 3) {
            return operacao;
        } else {
            System.out.println("Opção inválida, opção selecionada foi " + operacao);
            return getOp(scan);
        }
    }
    protected static int getTipoConta(Scanner scan) {
        System.out.println("----------------------------------");
        System.out.println("Informe o tipo da conta:\n1- C. Corrente\n2- C. Poupança");
        int tipoConta = scan.nextInt();
        if (tipoConta == 1 || tipoConta == 2 || tipoConta == 3) {
            return tipoConta;
        } else {
            System.out.println("Opção inválida, opção selecionada foi " + tipoConta);
            return getTipoConta(scan);
        }
    }
    protected static void OpEscolhida(Scanner scan, Conta cc, Conta cP, int operacao, int tipoConta) {
        switch (operacao) {
            case 1:
                if (tipoConta == 1) {
                    System.out.print("Valor do saque na C. Corrente: ");
                    cc.sacar(scan.nextDouble());
                    break;
                } else {
                    System.out.print("Valor do saque na C. Poupança: ");
                    cP.sacar(scan.nextDouble());
                    break;
                }
            case 2:
                if (tipoConta == 1) {
                    System.out.print("Valor a ser depositado na C. Corrente: ");
                    cc.depositar(scan.nextDouble());
                    break;
                } else {
                    System.out.print("Valor a ser depositado na C. Poupança: ");
                    cP.depositar(scan.nextDouble());
                    break;
                }
            case 3:
                if (tipoConta == 1) {
                    System.out.print("Valor a ser transferido da C. Corrente para C. Poupança: ");
                    cc.transferir(scan.nextDouble(), cP);
                    break;
                }
                else{
                    System.out.print("Valor a ser transferido da C. Poupança para C. Corrente: ");
                    cP.transferir(scan.nextDouble(), cc);
                    break;
                }
                default:
                    System.out.println("Valor inválido");
            }
        }
}
