package entities;

public class Item {
    private String idItem;
    private String titulo;
    private String categoria;
    private int anoLancamento;
    private String classificacao;
    private int quantCopias;

    public Item() {
    }

    public Item(String idItem, String titulo, String categoria, int anoLancamento, String classificacao, int quantCopias) {
        this.idItem = idItem;
        this.titulo = titulo;
        this.categoria = categoria;
        this.anoLancamento = anoLancamento;
        this.classificacao = classificacao;
        this.quantCopias = quantCopias;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public int getQuantCopias() {
        return quantCopias;
    }

    public void setQuantCopias(int quantCopias) {
        this.quantCopias = quantCopias;
    }
}
