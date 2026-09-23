/**
 * Produto perecível: recebe 20% de desconto automático no cálculo do valor
 * total quando faltam poucos dias para vencer (diasParaVencer <= 3).
 */
public class ProdutoPerecivel extends Product {

    private static final int LIMITE_DIAS_PARA_DESCONTO = 3;
    private static final double PERCENTUAL_DESCONTO_VENCIMENTO = 0.20;

    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double valorTotal = getPreco() * getQuantidade();
        if (diasParaVencer <= LIMITE_DIAS_PARA_DESCONTO) {
            valorTotal -= valorTotal * PERCENTUAL_DESCONTO_VENCIMENTO;
        }
        return valorTotal;
    }

    @Override
    public String getDescricao() {
        String situacao = diasParaVencer <= LIMITE_DIAS_PARA_DESCONTO
                ? " [PRÓXIMO DO VENCIMENTO - 20% de desconto aplicado]"
                : "";
        return super.getDescricao() + " | Validade: " + diasParaVencer + " dia(s)" + situacao;
    }
}
