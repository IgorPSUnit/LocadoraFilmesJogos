import com.google.gson.reflect.TypeToken;
import entities.Cliente;
import entities.Item;
import entities.Locacao;
import utilities.JsonUtils;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // Arquivos JSON usados como "banco de dados"
    private static final String CLIENTES_FILE = "clientes.json";
    private static final String ITENS_FILE = "itens.json";
    private static final String LOCACOES_FILE = "locacoes.json";
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        // Menus principais e submenus
        String[] opcao_menu = {
                "Clientes",
                "Filmes/Jogos",
                "Locação",
        };
        String[] opcoes_submenu = {"Criar", "Listar", "Atualizar", "Excluir"};
        int opcao, opcao_submenu;
        // Loop principal do programa (menu principal)
        do {
            opcao = menu(opcao_menu, "Menu principal", "Sair do programa");

            // se for -1, o usuário pediu para sair
            if (opcao == -1) {
                System.out.println("Saindo do programa...");
                break;
            }

            switch (opcao) {
                case 0 -> { // Caso Clientes
                    do {
                        opcao_submenu = menu(opcoes_submenu, "Menu de Clientes", "Voltar");
                        if (opcao_submenu == -1) break; // voltou
                        String clientesJson = "clientes.json";
                        switch (opcao_submenu) {
                            case 0 -> {
                                System.out.println("Cadastrar Cliente");
                                Cliente novo = new Cliente();
                                System.out.println("Id do  Cliente:");
                                String idCliente = ler.nextLine().trim();
                                novo.setIdCliente(idCliente);
                                System.out.println("Nome do Cliente:");
                                String nomeCliente = ler.nextLine().trim();
                                novo.setNome(nomeCliente);
                                System.out.println("CPF do cliente:");
                                String cpfCliente = ler.nextLine().trim();
                                novo.setCpf(cpfCliente);
                                System.out.println("Email do cliente:");
                                String emailCliente = ler.nextLine().trim();
                                novo.setEmail(emailCliente);
                                System.out.println("Telefone do cliente:");
                                String telefoneCliente = ler.nextLine().trim();
                                novo.setTelefone(telefoneCliente);

                                // === Ler clientes existentes ===
                                Type clienteListType = new TypeToken<List<Cliente>>() {
                                }.getType();
                                List<Cliente> clientes = JsonUtils.ler(clientesJson, clienteListType);
                                if (clientes == null) clientes = new ArrayList<>();
                                clientes.add(novo);
                                JsonUtils.salvar(clientesJson, clientes);
                                System.out.println("Cliente salvo com sucesso!");
                            }
                            case 1 -> {
                                System.out.println("Listar Clientes");
                                // === Ler clientes existentes ===
                                Type clienteListType = new TypeToken<List<Cliente>>() {}.getType();
                                List<Cliente> clientes = JsonUtils.ler(clientesJson, clienteListType);
                                if (clientes == null || clientes.isEmpty()) {
                                    System.out.println("Nenhum cliente encontrado!");
                                } else {
                                    for (Cliente cliente : clientes) {
                                        System.out.println("Id do cliente:" + cliente.getIdCliente());
                                        System.out.println("Nome do cliente:" + cliente.getNome());
                                        System.out.println("CPF do cliente:" + cliente.getCpf());
                                        System.out.println("Email do cliente:" + cliente.getEmail());
                                        System.out.println("Telefone do cliente:" + cliente.getTelefone());
                                        System.out.println("========================================");
                                    }
                                }

                            }
                            case 2 -> {

                                System.out.println("Atualizar Cliente");

                                Type clienteListType = new TypeToken<List<Cliente>>() {}.getType();
                                List<Cliente> clientes = lerLista(clientesJson, clienteListType);

                                if (clientes.isEmpty()){
                                    System.out.println("Nenhum cliente encontrado!");
                                    break;
                                }
                                System.out.println("Digite o ID do cliente a ser atualizado: ");
                                String idClienteAtualizar = ler.nextLine().trim();
                                Cliente clienteEncontrado = null;
                                for (Cliente cliente : clientes) {
                                    if (cliente.getIdCliente().equals(idClienteAtualizar)) {
                                        clienteEncontrado = cliente;
                                        break;
                                    }
                                }
                                if (clienteEncontrado != null){
                                    System.out.println("Id do cliente:" + clienteEncontrado.getIdCliente());
                                    System.out.println("Novo nome (atual: " + clienteEncontrado.getNome() + "): ");
                                    clienteEncontrado.setNome(ler.nextLine().trim());
                                    System.out.println("Novo CPF (atual: " + clienteEncontrado.getCpf() + "): ");
                                    clienteEncontrado.setCpf(ler.nextLine().trim());
                                    System.out.println("Novo email (atual: " + clienteEncontrado.getEmail() + "): ");
                                    clienteEncontrado.setEmail(ler.nextLine().trim());
                                    System.out.println("Novo telefone (atual: " + clienteEncontrado.getTelefone() + "): ");
                                    clienteEncontrado.setTelefone(ler.nextLine().trim());

                                    salvarLista(clientesJson, clientes);
                                    System.out.println("Cliente atualizado com sucesso!");
                                } else {
                                    System.out.println("Cliente não encontrado.");
                                }
                            }
                            case 3 -> {
                                System.out.println("Excluir Cliente");
                                Type clienteListType = new TypeToken<List<Cliente>>() {}.getType();
                                List<Cliente> clientes = lerLista(clientesJson, clienteListType);

                                if (clientes.isEmpty()) {
                                    System.out.println("Nenhum cliente cadastrado.");
                                    break;
                                }

                                System.out.print("Digite o ID do cliente a excluir: ");
                                String idExcluir = ler.nextLine().trim();
                                boolean removido = clientes.removeIf(c -> c.getIdCliente().equals(idExcluir));

                                if (removido) {
                                    salvarLista(clientesJson, clientes);
                                    System.out.println("Cliente removido com sucesso!");
                                } else {
                                    System.out.println("Cliente não encontrado.");
                                }
                            }
                        }
                    } while (true);
                }
                case 1 -> { // Caso Filmes/Jogos
                    do {
                        String[] opcoes_submenu_itens = {"Criar", "Listar", "Atualizar (todas as cópias)", "Excluir", "Atualizar quantidade de cópias"};
                        opcao_submenu = menu(opcoes_submenu_itens, "Menu de Filmes/Jogos", "Voltar");
                        if (opcao_submenu == -1) break;
                        String itensJson = "itens.json";
                        switch (opcao_submenu) {
                            case 0 -> {
                                System.out.println("Cadastrar Filme/Jogo");
                                Item novoItem = new Item();
                                System.out.println("Id do  Filme/Jogo:");
                                String idItem = ler.nextLine().trim();
                                novoItem.setIdItem(idItem);
                                System.out.println("Título do Filme/Jogo:");
                                String nomeItem = ler.nextLine().trim();
                                novoItem.setTitulo(nomeItem);
                                System.out.println("Categoria do Filme/Jogo:");
                                String categoria = ler.nextLine().trim();
                                novoItem.setCategoria(categoria);
                                System.out.println("Ano de lançamento do Filme/Jogo:");
                                String anoLancamento = ler.nextLine().trim();
                                novoItem.setAnoLancamento(Integer.parseInt(anoLancamento));
                                System.out.println("Classificação do Filme/Jogo:");
                                String classificacao = ler.nextLine().trim();
                                novoItem.setClassificacao(classificacao);
                                System.out.println("Quantidade de copias do Filme/Jogo:");
                                String quantCopias = ler.nextLine().trim();
                                novoItem.setQuantCopias(Integer.parseInt(quantCopias));

                                // === Ler itens existentes ===
                                Type itemListType = new TypeToken<List<Item>>() {}.getType();
                                List<Item> itens = JsonUtils.ler(itensJson, itemListType);
                                if (itens == null) itens = new ArrayList<>();
                                itens.add(novoItem);
                                JsonUtils.salvar(itensJson, itens);
                                System.out.println("Filme/Jogo salvo com sucesso!");
                            }

                            case 1 -> {
                                System.out.println("Listar Filmes/Jogos");
                                // === Ler itens existentes ===
                                Type itemListType = new TypeToken<List<Item>>() {}.getType();
                                List<Item> itens = JsonUtils.ler(itensJson, itemListType);
                                if (itens == null || itens.isEmpty()) {
                                    System.out.println("Nenhum Filme/Jogo encontrado!");
                                } else {
                                    itens.stream()
                                            .collect(Collectors.groupingBy(Item::getTitulo))
                                            .forEach((titulo, lista) -> {
                                                System.out.println("Título: " + titulo);
                                                System.out.println("Categoria: " + lista.get(0).getCategoria());
                                                System.out.println("Ano: " + lista.get(0).getAnoLancamento());
                                                System.out.println("Classificação: " + lista.get(0).getClassificacao());
                                                System.out.println("Total de cópias: " + lista.size());
                                                System.out.println("IDs: " + lista.stream()
                                                        .map(Item::getIdItem)
                                                        .collect(Collectors.joining(", ")));
                                                System.out.println("========================================");
                                            });
                                }
                            }
                            case 2 -> {
                                System.out.println("Atualizar Filme/Jogo");
                                // === Ler itens existentes ===
                                Type itemListType = new TypeToken<List<Item>>() {}.getType();
                                List<Item> itens = JsonUtils.ler(itensJson, itemListType);

                                if (itens.isEmpty()){
                                    System.out.println("Nenhum Filme/Jogo encontrado!");
                                    break;
                                }
                                System.out.println("Digite o Título do Filme/Jogo a ser atualizado: ");
                                String tituloAtualizar = ler.nextLine().trim();
                                // Filtra todas as cópias existentes
                                List<Item> copias = itens.stream()
                                        .filter(i -> i.getTitulo().equalsIgnoreCase(tituloAtualizar))
                                        .toList();
                                if (copias.isEmpty()) {
                                    System.out.println("Título não encontrado.");
                                    break;
                                }
                                Item itemAtual = copias.get(0); // usa a primeira cópia como referência
                                System.out.println("Título atual: " + itemAtual.getTitulo());
                                System.out.println("Digite o novo título (pressione ENTER para manter):");
                                String novoTitulo = ler.nextLine().trim();
                                if (!novoTitulo.isEmpty()) {
                                    copias.forEach(cop -> cop.setTitulo(novoTitulo));
                                }
                                System.out.println("Categoria atual: " + itemAtual.getCategoria());
                                System.out.println("Digite a nova categoria (ENTER para manter):");
                                String novaCategoria = ler.nextLine().trim();
                                if (!novaCategoria.isEmpty()) {
                                    copias.forEach(cop -> cop.setCategoria(novaCategoria));
                                }
                                System.out.println("Ano atual: " + itemAtual.getAnoLancamento());
                                System.out.println("Digite o novo ano (ENTER para manter):");
                                String novoAnoStr = ler.nextLine().trim();
                                if (!novoAnoStr.isEmpty()) {
                                    try {
                                        int novoAno = Integer.parseInt(novoAnoStr);
                                        copias.forEach(cop -> cop.setAnoLancamento(novoAno));
                                    } catch (NumberFormatException e) {
                                        System.out.println("Ano inválido, mantendo valor atual.");
                                    }
                                }
                                System.out.println("Classificação atual: " + itemAtual.getClassificacao());
                                System.out.println("Digite a nova classificação (ENTER para manter):");
                                String novaClassificacao = ler.nextLine().trim();
                                if (!novaClassificacao.isEmpty()) {
                                    copias.forEach(cop -> cop.setClassificacao(novaClassificacao));
                                }
                                salvarLista(itensJson, itens);
                                System.out.println("Todas as cópias do título foram atualizadas com sucesso!");
                            }
                            case 3 -> {
                                System.out.println("Excluir Filme/Jogo");
                                Type itemListType = new TypeToken<List<Item>>() {}.getType();
                                List<Item> itens = JsonUtils.ler(itensJson, itemListType);
                                if (itens.isEmpty()) {
                                    System.out.println("Nenhum Filme/Jogo encontrado!");
                                    break;
                                }
                                System.out.println("Digite o Titulo do Filme/Jogo a ser deletado: ");
                                String tituloExcluir = ler.nextLine().trim();
                                long qtdAntes = itens.size();
                                boolean removido = itens.removeIf(item -> item.getTitulo().equalsIgnoreCase(tituloExcluir));
                                long qtdDepois = itens.size();

                                if (removido) {
                                    salvarLista(itensJson, itens);
                                    System.out.println("Foram removidas " + (qtdAntes - qtdDepois) + " cópias de '" + tituloExcluir + "'.");
                                } else {
                                    System.out.println("Título não encontrado.");
                                }
                            }
                            case 4 -> {
                                System.out.println("Atualizar quantidade de cópias");
                                Type itemListType = new TypeToken<List<Item>>() {}.getType();
                                List<Item> itens = JsonUtils.ler(itensJson, itemListType);

                                if (itens.isEmpty()) {
                                    System.out.println("Nenhum item encontrado!");
                                    break;
                                }

                                System.out.println("Digite o Título do item para alterar quantidade de cópias: ");
                                String titulo = ler.nextLine().trim();

                                // Filtra todas as cópias existentes
                                List<Item> copias = itens.stream()
                                        .filter(i -> i.getTitulo().equalsIgnoreCase(titulo))
                                        .toList();

                                if (copias.isEmpty()) {
                                    System.out.println("Título não encontrado.");
                                    break;
                                }

                                System.out.println("Quantidade atual de cópias: " + copias.size());
                                System.out.println("Digite a nova quantidade: ");
                                int novaQtd = Integer.parseInt(ler.nextLine().trim());

                                if (novaQtd > copias.size()) {
                                    // Adicionar novas cópias
                                    int qtdAdicionar = novaQtd - copias.size();
                                    for (int i = 0; i < qtdAdicionar; i++) {
                                        Item novaCopia = new Item();
                                        novaCopia.setIdItem(java.util.UUID.randomUUID().toString());
                                        novaCopia.setTitulo(copias.get(0).getTitulo());
                                        novaCopia.setCategoria(copias.get(0).getCategoria());
                                        novaCopia.setAnoLancamento(copias.get(0).getAnoLancamento());
                                        novaCopia.setClassificacao(copias.get(0).getClassificacao());
                                        itens.add(novaCopia);
                                    }
                                    System.out.println("Adicionadas " + qtdAdicionar + " novas cópias.");
                                } else if (novaQtd < copias.size()) {
                                    // Remover cópias extras
                                    int qtdRemover = copias.size() - novaQtd;
                                    int removidas = 0;
                                    for (Item copia : new ArrayList<>(itens)) {
                                        if (copia.getTitulo().equalsIgnoreCase(titulo) && removidas < qtdRemover) {
                                            itens.remove(copia);
                                            removidas++;
                                        }
                                    }
                                    System.out.println("Removidas " + qtdRemover + " cópias.");
                                } else {
                                    System.out.println("Quantidade já está correta, nenhuma alteração feita.");
                                }

                                salvarLista(itensJson, itens);
                            }
                        }
                    } while (true);
                }
                case 2 -> { // Caso Locações
                    do {

                        opcao_submenu = menu(new String[]{"Registrar", "Listar", "Devolver"}, "Menu de Locações", "Voltar");
                        if (opcao_submenu == -1) break;

                        switch (opcao_submenu) {
                            case 0 -> registrarLocacao(ler);
                            case 1 -> listarLocacoes();
                            case 2 -> devolverItem(ler);
                        }
                    } while (true);
                }
            }
        } while (true);
    }

    public static int menu(String[] opcao_menu, String title, String go_back) {
        System.out.println("\n=== " + title + " ===");
        String opcoes = "";
        for (int i = 0; i < opcao_menu.length; i++) {
            System.out.println((i + 1) + " - " + opcao_menu[i]);
            opcoes += (i + 1) + ";";
        }
        System.out.println("0 - " + go_back);
        opcoes += "0";
        System.out.print("Escolha uma opcao (" + opcoes + "): ");
        Scanner s = new Scanner(System.in);
        int opcao;
        try {
            opcao = s.nextInt();
        } catch (Exception e) {
            opcao = -1; // inválido
        }

        if (opcao == 0) {
            System.out.println("Opcao escolhida: 0 - " + go_back);
            return -1; // indica sair/voltar
        } else if (opcao > 0 && opcao <= opcao_menu.length) {
            System.out.println("Opcao escolhida: " + opcao_menu[opcao - 1]);
            return opcao - 1; // retorna índice válido
        }
        return -1;
    }

    public static <T> List<T> lerLista(String caminho, Type tipo) {
        List<T> lista = JsonUtils.ler(caminho, tipo);
        return (lista == null) ? new ArrayList<>() : lista;
    }

    public static <T> void salvarLista(String caminho, List<T> lista) {
        JsonUtils.salvar(caminho, lista);
    }

    public static void registrarLocacao(Scanner ler) {
        List<Cliente> clientes = Main.lerLista(CLIENTES_FILE, new TypeToken<List<Cliente>>(){}.getType());
        List<Item> itens = Main.lerLista(ITENS_FILE, new TypeToken<List<Item>>(){}.getType());
        List<Locacao> locacoes = Main.lerLista(LOCACOES_FILE, new TypeToken<List<Locacao>>(){}.getType());

        if (clientes == null) clientes = new ArrayList<>();
        if (itens == null) itens = new ArrayList<>();
        if (locacoes == null) locacoes = new ArrayList<>();

        System.out.print("Digite o CPF do cliente: ");
        String cpf = ler.nextLine().trim();

        Cliente cliente = clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        // Regra de negócio: cada cliente pode ter no máximo 3 locações ativas
        long locacoesAtivas = locacoes.stream()
                .filter(l -> l.getCpfCliente().equals(cpf) && l.getStatus().equals("Alugado"))
                .count();

        if (locacoesAtivas >= 3) {
            System.out.println("O cliente " + cliente.getNome() + " já possui 3 locações ativas.");
            return;
        }

        System.out.print("Digite o título do item: ");
        String titulo = ler.nextLine().trim();

        Item item = itens.stream()
                .filter(i -> i.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (item == null) {
            System.out.println("Item não encontrado.");
            return;
        }

        if (item.getQuantCopias() <= 0) {
            System.out.println("Não há cópias disponíveis deste item.");
            return;
        }

        // Cria locação
        String idLocacao = UUID.randomUUID().toString();
        LocalDateTime dataLocacao = LocalDateTime.now();
        LocalDateTime dataDevolucao = dataLocacao.plusDays(7);

        Locacao nova = new Locacao(idLocacao, cpf, item.getTitulo(), dataLocacao, dataDevolucao, "Alugado");
        locacoes.add(nova);

        // Atualiza estoque
        item.setQuantCopias(item.getQuantCopias() - 1);

        Main.salvarLista(LOCACOES_FILE, locacoes);
        Main.salvarLista(ITENS_FILE, itens);

        System.out.println("Locação registrada com sucesso para " + cliente.getNome());
        System.out.println("Cópias restantes do item: " + item.getQuantCopias());
    }



    public static void listarLocacoes() {
        List<Locacao> locacoes = Main.lerLista(LOCACOES_FILE, new TypeToken<List<Locacao>>(){}.getType());

        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locação registrada.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (Locacao l : locacoes) {
            String devolucao = (l.getDataDevolucao() != null) ? l.getDataDevolucao().format(formatter) : "---";
            System.out.println("ID: " + l.getIdLocacao() +
                    " | Cliente (CPF): " + l.getCpfCliente() +
                    " | Item: " + l.getTituloItem() +
                    " | Locação: " + l.getDataLocacao().format(formatter) +
                    " | Devolução: " + devolucao +
                    " | Status: " + l.getStatus());
        }
    }


    public static void devolverItem(Scanner ler) {
        List<Locacao> locacoes = Main.lerLista(LOCACOES_FILE, new TypeToken<List<Locacao>>(){}.getType());
        List<Item> itens = Main.lerLista(ITENS_FILE, new TypeToken<List<Item>>(){}.getType());

        if (locacoes == null || locacoes.isEmpty()) {
            System.out.println("Nenhuma locação encontrada.");
            return;
        }

        System.out.print("Digite o ID da locação para devolver: ");
        String id = ler.nextLine().trim();
        // Localiza a locação pelo ID e valida que está "Alugada"
        Locacao locacao = locacoes.stream()
                .filter(l -> l.getIdLocacao().equals(id) && l.getStatus().equals("Alugado"))
                .findFirst()
                .orElse(null);

        if (locacao == null) {
            System.out.println("Locação não encontrada ou já devolvida.");
            return;
        }

        Item item = itens.stream()
                .filter(i -> i.getTitulo().equalsIgnoreCase(locacao.getTituloItem()))
                .findFirst()
                .orElse(null);
        // Atualiza estoque: devolve uma cópia ao acervo
        if (item != null) {
            item.setQuantCopias(item.getQuantCopias() + 1);
            Main.salvarLista(ITENS_FILE, itens);
        }

        locacao.setStatus("Devolvido");
        locacao.setDataDevolucao(LocalDateTime.now());
        Main.salvarLista(LOCACOES_FILE, locacoes);

        System.out.println("Item devolvido com sucesso!");
        if (item != null) {
            System.out.println("Cópias disponíveis agora: " + item.getQuantCopias());
        }
    }


}


