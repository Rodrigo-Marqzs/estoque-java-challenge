/**
 * Lançada quando um produto é cadastrado com preço ou quantidade negativos.
 */
public class QuantidadeInvalidaException extends EstoqueException {

    public QuantidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}
