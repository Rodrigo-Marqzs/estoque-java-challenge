/**
 * Lançada quando se tenta vender uma quantidade maior do que a disponível em estoque.
 */
public class ProdutoIndisponivelException extends EstoqueException {

    public ProdutoIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
