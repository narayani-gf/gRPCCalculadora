package grpccalculadora.cliente;

import com.proto.calculadora.CalculadoraServiceGrpc;

import javax.swing.JOptionPane;

import com.proto.calculadora.Calculadora.CalculadoraEntrada;
import com.proto.calculadora.Calculadora.CalculadoraSalida;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 8080;

        ManagedChannel ch = ManagedChannelBuilder.forAddress(host, puerto).usePlaintext().build();

        CalculadoraServiceGrpc.CalculadoraServiceBlockingStub stub = CalculadoraServiceGrpc.newBlockingStub(ch);

        while (true) {
            // Se presenta el menú
            String opt = JOptionPane.showInputDialog(
                "Calcular\n" +
                    "Suma...............(1)\n" +
                    "Resta..............(2)\n" +
                    "Multip.............(3)\n" +
                    "Division...........(4)\n\n" +
                    "Cancelar para salir" 
            );
            // Si no selecciona nada, termina.
            if (opt == null)
                break;

            // Obtenemos los dos parámetros de la operación
            int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
            int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

            CalculadoraEntrada entrada = CalculadoraEntrada.newBuilder().setA(a).setB(b).build();

            // Menú de operaciones
            switch (opt) {
                case "1": {
                    CalculadoraSalida resultado = stub.sum(entrada);
                    JOptionPane.showMessageDialog(null, a + "+" + b + " = " + resultado.getResultado());
                    break;
                }
                
                case "2": {
                    CalculadoraSalida resultado = stub.res(entrada);
                    JOptionPane.showMessageDialog(null, a + "-" + b + " = " + resultado.getResultado());
                    break;
                }

                case "3": {
                    CalculadoraSalida resultado = stub.mul(entrada);
                    JOptionPane.showMessageDialog(null, a + "*" + b + " = " + resultado.getResultado());
                    break;
                }

                case "4": {
                    CalculadoraSalida resultado = stub.div(entrada);
                    JOptionPane.showMessageDialog(null, a + "*" + b + " = " + resultado.getResultado());
                    break;
                }
            }
        }

        System.out.println("Apagando...");
        ch.shutdown();
    }
}
