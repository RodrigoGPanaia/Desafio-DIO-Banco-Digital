package BancoDigital;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Cliente rodrigo = new Cliente();
        rodrigo.setNome("Rodrigo G Panaia");

        Conta cc = new ContaCorrente(rodrigo);

        Conta cP = new ContaPoupanca(rodrigo);

        int outraOperacao;

        do {
            int operacao = Conta.getOp(scan);
            if (operacao == 1 || operacao == 2 || operacao == 3) {
                int tipoConta = Conta.getTipoConta(scan);
                Conta.OpEscolhida(scan, cc, cP, operacao, tipoConta);
            } else {
                System.out.println("Opção inválida, tente novamente");
                return;
            }

            System.out.println("----------------------------------\n");
            cc.imprimirExtrato();
            System.out.println("----------------------------------\n");
            cP.imprimirExtrato();
            System.out.println("----------------------------------");

            System.out.println("Deseja realizar outra operação?\n1 - Sim\n2 - Não");
            outraOperacao = scan.nextInt();
        }while(outraOperacao != 2); {


            System.out.println("----------- Volte Sempre ----------");
        }


    }
}
