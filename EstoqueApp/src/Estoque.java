import java.util.ArrayList;
import java.util.List;

/**
 * Representa o estoque de uma loja. Usa COMPOSIÇÃO: um Estoque TEM UMA lista
 * de produtos, mas não é um Product nem herda dele.
 */
public class Estoque {

    private List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
    }

    /**
     * Vende uma quantidade de um produto identificado pelo índice na lista.
     *
     * @throws ProdutoIndisponivelException propagada de {@link Product#vender(int)}
     *                                       quando o estoque do produto é insuficiente
     */
    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        Product produto = produtos.get(indice);
        produto.vender(quantidade);
    }

    /**
     * Soma o valor total do estoque. Cada produto calcula seu próprio valor
     * (calcularValorTotal), sem que o Estoque precise saber de qual subtipo se trata:
     * este é o ponto em que o polimorfismo dinâmico entra em ação.
     */
    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public List<Product> getProdutos() {
        return produtos;
    }

    public void listarProdutos() {
        for (Product p : produtos) {
            System.out.println(p.getDescricao());
        }
    }
}
