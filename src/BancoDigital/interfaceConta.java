package BancoDigital;

public interface interfaceConta {

    void  sacar(double valor);

    void  depositar(double valor);

    void  transferir(double valor, Conta cintaDestino);

    void imprimirExtrato();

 }
