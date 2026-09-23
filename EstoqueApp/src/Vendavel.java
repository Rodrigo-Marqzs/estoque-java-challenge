/**
 * Contrato para qualquer item que possa ser vendido.
 */
public interface Vendavel {

    /**
     * Tenta vender a quantidade desejada.
     *
     * @param quantidadeDesejada quantidade que se deseja vender
     * @throws ProdutoIndisponivelException se a quantidade desejada for maior
     *                                       que a quantidade disponível em estoque
     */
    void vender(int quantidadeDesejada) throws ProdutoIndisponivelException;
}
