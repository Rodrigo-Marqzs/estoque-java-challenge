/**
 * Classe abstrata que representa um produto genérico do estoque.
 * Implementa {@link Vendavel} pois todo produto pode ser vendido.
 */
public abstract class Product implements Vendavel {

    private String nome;
    private double preco;
    private int quantidade;

    protected Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException(
                    "Preço inválido para o produto \"" + nome + "\": " + preco + " (não pode ser negativo).");
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException(
                    "Quantidade inválida para o produto \"" + nome + "\": " + quantidade + " (não pode ser negativa).");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // ---------- Getters / Setters (encapsulamento) ----------

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    protected void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    protected void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // ---------- Métodos abstratos (polimorfismo dinâmico) ----------

    /**
     * Cada subclasse decide como calcular o valor total do seu estoque
     * (ex.: com ou sem desconto).
     */
    public abstract double calcularValorTotal();

    // ---------- Métodos concretos ----------

    public String getDescricao() {
        return String.format("Produto: %-20s | Preço: R$ %8.2f | Quantidade: %4d", nome, preco, quantidade);
    }

    // ---------- Implementação de Vendavel ----------

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada > this.quantidade) {
            throw new ProdutoIndisponivelException(
                    "Estoque insuficiente para \"" + nome + "\". Disponível: " + this.quantidade
                            + ", solicitado: " + quantidadeDesejada + ".");
        }
        this.quantidade -= quantidadeDesejada;
    }

    // ---------- Sobrecarga (polimorfismo estático) ----------

    /**
     * Aplica um desconto percentual simples sobre o preço do produto.
     *
     * @param percentual percentual de desconto (ex.: 10 para 10%)
     */
    public void aplicarDesconto(double percentual) {
        double novoPreco = this.preco - (this.preco * percentual / 100.0);
        this.preco = Math.max(novoPreco, 0);
    }

    /**
     * Aplica um desconto percentual, mas limita o valor absoluto do desconto
     * a {@code descontoMaximo}.
     *
     * @param percentual     percentual de desconto (ex.: 10 para 10%)
     * @param descontoMaximo valor máximo (em reais) que pode ser descontado
     */
    public void aplicarDesconto(double percentual, double descontoMaximo) {
        double descontoCalculado = this.preco * percentual / 100.0;
        double descontoAplicado = Math.min(descontoCalculado, descontoMaximo);
        this.preco = Math.max(this.preco - descontoAplicado, 0);
    }
}
