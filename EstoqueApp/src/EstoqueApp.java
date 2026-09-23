/**
 * Classe principal que demonstra o funcionamento do sistema de estoque:
 * cadastro de produtos, tratamento de exceções e cálculo do valor total.
 */
public class EstoqueApp {

    public static void main(String[] args) {

        Estoque estoque = new Estoque();

        System.out.println("=== 1. Cadastro de produtos ===");
        try {
            // Dois produtos comuns
            estoque.adicionarProduto(new ProdutoComum("Arroz 5kg", 25.90, 50));
            estoque.adicionarProduto(new ProdutoComum("Detergente", 3.50, 100));

            // Dois produtos perecíveis (um deles próximo do vencimento)
            estoque.adicionarProduto(new ProdutoPerecivel("Iogurte Natural", 4.80, 30, 2));   // <= 3 dias -> desconto
            estoque.adicionarProduto(new ProdutoPerecivel("Queijo Minas", 22.00, 15, 10));    // sem desconto

        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro inesperado ao cadastrar produto: " + e.getMessage());
        }

        estoque.listarProdutos();

        System.out.println("\n=== 2. Tentativa de cadastro inválido ===");
        try {
            Product produtoInvalido = new ProdutoComum("Produto Errado", 10.0, -5);
            estoque.adicionarProduto(produtoInvalido);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Falha ao cadastrar produto: " + e.getMessage());
        }

        System.out.println("\n=== 3. Venda válida ===");
        try {
            // Vende 10 unidades de Arroz 5kg (índice 0)
            estoque.venderProduto(0, 10);
            System.out.println("Venda realizada com sucesso: 10 unidade(s) de \""
                    + estoque.getProdutos().get(0).getNome() + "\".");
            System.out.println("Novo estado: " + estoque.getProdutos().get(0).getDescricao());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Erro inesperado na venda: " + e.getMessage());
        }

        System.out.println("\n=== 4. Tentativa de venda acima do estoque disponível ===");
        try {
            // Tenta vender 1000 unidades de Detergente (índice 1), que não tem esse estoque.
            // Usamos um método auxiliar que declara lançar QuantidadeInvalidaException E
            // ProdutoIndisponivelException, só para poder demonstrar corretamente a ordem
            // dos catches pedida no enunciado (específicos primeiro, EstoqueException por último).
            venderComValidacao(estoque, 1, 1000);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro de quantidade: " + e.getMessage());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Venda não realizada: " + e.getMessage());
        } catch (EstoqueException e) {
            // Catch genérico por último: pega qualquer outro EstoqueException não previsto acima.
            // Se ele viesse antes dos catches específicos, o código nem compilaria
            // (catches subsequentes ficariam inalcançáveis).
            System.out.println("Erro de estoque: " + e.getMessage());
        }

        System.out.println("\n=== 5. Valor total do estoque ===");
        double total = estoque.calcularValorTotalEstoque();
        System.out.printf("Valor total do estoque (somando produtos comuns e perecíveis, cada um com sua "
                + "própria regra de cálculo): R$ %.2f%n", total);

        System.out.println("\n=== 6. Demonstração de sobrecarga de aplicarDesconto() ===");
        Product queijo = estoque.getProdutos().get(3); // Queijo Minas
        System.out.println("Preço antes do desconto: R$ " + String.format("%.2f", queijo.getPreco()));
        queijo.aplicarDesconto(10); // desconto simples de 10%
        System.out.println("Preço após aplicarDesconto(10): R$ " + String.format("%.2f", queijo.getPreco()));
        queijo.aplicarDesconto(50, 2.0); // desconto de 50%, limitado a R$ 2,00
        System.out.println("Preço após aplicarDesconto(50, 2.0) [desconto máx. R$2,00]: R$ "
                + String.format("%.2f", queijo.getPreco()));
    }

    /**
     * Método auxiliar que valida a quantidade antes de vender.
     * Declara as duas exceções específicas para permitir demonstrar, no main(),
     * um bloco try com múltiplos catches (QuantidadeInvalidaException,
     * ProdutoIndisponivelException e o genérico EstoqueException por último).
     */
    private static void venderComValidacao(Estoque estoque, int indice, int quantidade)
            throws QuantidadeInvalidaException, ProdutoIndisponivelException {
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("Quantidade de venda não pode ser negativa: " + quantidade);
        }
        estoque.venderProduto(indice, quantidade);
    }
}
