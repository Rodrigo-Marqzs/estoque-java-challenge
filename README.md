# Sistema de Estoque de Produtos — Java

Exercício acadêmico de implementação em Java aplicando **Programação Orientada a Objetos**: classes abstratas, herança, interfaces, polimorfismo (dinâmico e estático), composição e tratamento de exceções.

## 📋 Contexto

Simulação de um sistema de estoque para uma loja, capaz de cadastrar produtos de tipos diferentes (comuns e perecíveis), controlar a venda de itens e tratar corretamente situações inválidas — tudo através de uma hierarquia de classes bem desenhada.

## 🧠 Conceitos aplicados

| Conceito | Onde aparece |
|---|---|
| **Classe abstrata** | `Product` define o contrato comum e deixa `calcularValorTotal()` para as subclasses |
| **Herança** | `ProdutoComum` e `ProdutoPerecivel` estendem `Product` |
| **Interface** | `Vendavel` define o comportamento `vender()`, implementado por `Product` |
| **Polimorfismo dinâmico** | `Estoque.calcularValorTotalEstoque()` soma o valor de cada produto sem saber seu subtipo |
| **Polimorfismo estático (sobrecarga)** | `aplicarDesconto(double)` e `aplicarDesconto(double, double)` em `Product` |
| **Composição** | `Estoque` **tem uma** lista de `Product` (não herda de `Product`) |
| **Hierarquia de exceções checked** | `EstoqueException` → `QuantidadeInvalidaException` e `ProdutoIndisponivelException` |

## 🗂️ Estrutura do projeto

```
├── EstoqueException.java              # Exceção checked base
├── QuantidadeInvalidaException.java   # Preço/quantidade negativos no cadastro
├── ProdutoIndisponivelException.java  # Venda maior que o estoque disponível
├── Vendavel.java                      # Interface: void vender(int) throws ProdutoIndisponivelException
├── Product.java                       # Classe abstrata (implementa Vendavel)
├── ProdutoComum.java                  # Product: valor total = preço x quantidade
├── ProdutoPerecivel.java              # Product: 20% de desconto se diasParaVencer <= 3
├── Estoque.java                       # Composição: TEM UMA lista de Product
└── EstoqueApp.java                    # Classe principal (main)
```

## ▶️ Como executar

**Via terminal (javac + java):**
```bash
javac *.java
java EstoqueApp
```

**Via IntelliJ IDEA:**
1. Crie um novo projeto Java.
2. Copie todos os arquivos `.java` para dentro da pasta `src` do projeto.
3. Abra `EstoqueApp.java` e clique em ▶️ Run ao lado do método `main`.

## 🧾 O que o `EstoqueApp` demonstra

1. Cadastro de 2 `ProdutoComum` e 2 `ProdutoPerecivel` (um deles vencendo em 2 dias).
2. Tentativa de cadastro com quantidade negativa → captura `QuantidadeInvalidaException`.
3. Venda válida de um produto.
4. Tentativa de venda acima do estoque disponível → captura `ProdutoIndisponivelException` (com os catches ordenados corretamente: exceções específicas antes da genérica `EstoqueException`).
5. Cálculo do valor total do estoque, somando produtos comuns e perecíveis com suas regras de cálculo diferentes (polimorfismo).
6. Demonstração das duas versões sobrecarregadas de `aplicarDesconto()`.

### Exemplo de saída

```
=== 1. Cadastro de produtos ===
Produto: Arroz 5kg            | Preço: R$    25,90 | Quantidade:   50
Produto: Detergente           | Preço: R$     3,50 | Quantidade:  100
Produto: Iogurte Natural      | Preço: R$     4,80 | Quantidade:   30 | Validade: 2 dia(s) [PRÓXIMO DO VENCIMENTO - 20% de desconto aplicado]
Produto: Queijo Minas         | Preço: R$    22,00 | Quantidade:   15 | Validade: 10 dia(s)

=== 2. Tentativa de cadastro inválido ===
Falha ao cadastrar produto: Quantidade inválida para o produto "Produto Errado": -5 (não pode ser negativa).

=== 3. Venda válida ===
Venda realizada com sucesso: 10 unidade(s) de "Arroz 5kg".
Novo estado: Produto: Arroz 5kg            | Preço: R$    25,90 | Quantidade:   40

=== 4. Tentativa de venda acima do estoque disponível ===
Venda não realizada: Estoque insuficiente para "Detergente". Disponível: 100, solicitado: 1000.

=== 5. Valor total do estoque ===
Valor total do estoque (somando produtos comuns e perecíveis, cada um com sua própria regra de cálculo): R$ 1831,20

=== 6. Demonstração de sobrecarga de aplicarDesconto() ===
Preço antes do desconto: R$ 22,00
Preço após aplicarDesconto(10): R$ 19,80
Preço após aplicarDesconto(50, 2.0) [desconto máx. R$2,00]: R$ 17,80
```

## 🛠️ Tecnologias

- Java 21 (compatível com versões anteriores, sem uso de recursos exclusivos do 21)

## 👤 Autor

Rodrigo Marqzs
