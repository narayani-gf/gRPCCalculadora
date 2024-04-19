package grpccalculadora.servidor;

import com.proto.calculadora.Calculadora.CalculadoraEntrada;
import com.proto.calculadora.Calculadora.CalculadoraSalida;
import com.proto.calculadora.CalculadoraServiceGrpc;

import io.grpc.stub.StreamObserver;

public class ServidorImpl extends CalculadoraServiceGrpc.CalculadoraServiceImplBase {
    @Override
    public void sum(CalculadoraEntrada entrada, StreamObserver<CalculadoraSalida> salidaObserver) {
        // Se construye la respuesta a enviarle al cliente
        CalculadoraSalida salida = CalculadoraSalida.newBuilder().setResultado(entrada.getA() + entrada.getB()).build();

        salidaObserver.onNext(salida);
        salidaObserver.onCompleted();
    }

    @Override
    public void res(CalculadoraEntrada entrada, StreamObserver<CalculadoraSalida> salidaObserver) {
        CalculadoraSalida salida = CalculadoraSalida.newBuilder().setResultado(entrada.getA() - entrada.getB()).build();

        salidaObserver.onNext(salida);
        salidaObserver.onCompleted();
    }

    @Override
    public void mul(CalculadoraEntrada entrada, StreamObserver<CalculadoraSalida> salidaObserver) {
        CalculadoraSalida salida = CalculadoraSalida.newBuilder().setResultado(entrada.getA() * entrada.getB()).build();

        salidaObserver.onNext(salida);
        salidaObserver.onCompleted();
    }

    @Override
    public void div(CalculadoraEntrada entrada, StreamObserver<CalculadoraSalida> salidaObserver) {
        CalculadoraSalida salida = CalculadoraSalida.newBuilder().setResultado(entrada.getA() / entrada.getB()).build();

        salidaObserver.onNext(salida);
        salidaObserver.onCompleted();
    }
}
