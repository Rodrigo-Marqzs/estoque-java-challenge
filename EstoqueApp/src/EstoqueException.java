/**
 * Exceção base (checked) para todos os problemas relacionados ao estoque.
 * As exceções mais específicas do sistema devem herdar desta classe.
 */
public class EstoqueException extends Exception {

    public EstoqueException(String mensagem) {
        super(mensagem);
    }
}
