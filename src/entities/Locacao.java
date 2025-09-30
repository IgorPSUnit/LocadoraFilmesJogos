package entities;

import java.time.LocalDateTime;

public class Locacao {
    private String idLocacao;
    private String cpfCliente;
    private String tituloItem;
    private LocalDateTime dataLocacao;
    private LocalDateTime dataDevolucao;
    private String status;

    public Locacao() {}

    public Locacao(String idLocacao, String cpfCliente, String tituloItem,
                   LocalDateTime dataLocacao, LocalDateTime dataDevolucao, String status) {
        this.idLocacao = idLocacao;
        this.cpfCliente = cpfCliente;
        this.tituloItem = tituloItem;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    // Getters e setters
    public String getIdLocacao() { return idLocacao; }
    public void setIdLocacao(String idLocacao) { this.idLocacao = idLocacao; }

    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

    public String getTituloItem() { return tituloItem; }
    public void setTituloItem(String tituloItem) { this.tituloItem = tituloItem; }

    public LocalDateTime getDataLocacao() { return dataLocacao; }
    public void setDataLocacao(LocalDateTime dataLocacao) { this.dataLocacao = dataLocacao; }

    public LocalDateTime getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDateTime dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
