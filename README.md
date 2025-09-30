# Sistema de Locadora de Filmes e Jogos em Java

Este projeto é um sistema de gerenciamento de locadora de filmes e jogos, desenvolvido em **Java**, que utiliza **arquivos JSON** como banco de dados. O sistema permite gerenciar clientes, itens (filmes/jogos) e locações, oferecendo funcionalidades de criação, listagem, atualização e exclusão de registros.

---

## Funcionalidades

O sistema oferece três módulos principais:

### 1. Clientes
- **Criar**: Cadastro de novos clientes com ID, nome, CPF, email e telefone.
- **Listar**: Exibir todos os clientes cadastrados.
- **Atualizar**: Alterar informações de clientes existentes.
- **Excluir**: Remover clientes do sistema.

### 2. Filmes/Jogos
- **Criar**: Cadastro de filmes ou jogos com ID, título, categoria, ano de lançamento, classificação e quantidade de cópias.
- **Listar**: Exibir todos os itens cadastrados, agrupados por título.
- **Atualizar**: Atualizar informações de todas as cópias de um item.
- **Excluir**: Remover todas as cópias de um item.
- **Atualizar quantidade de cópias**: Ajustar a quantidade de cópias disponíveis de um item.

### 3. Locações
- **Registrar**: Criar uma nova locação associando cliente e item, com limite de 3 locações ativas por cliente.
- **Listar**: Visualizar todas as locações registradas, com status e datas de locação/devolução.
- **Devolver**: Registrar a devolução de um item, atualizando o estoque e o status da locação.

---

## Estrutura de Arquivos JSON

O sistema utiliza três arquivos JSON como "banco de dados":

- `clientes.json` – armazena informações dos clientes.
- `itens.json` – armazena informações dos filmes e jogos.
- `locacoes.json` – armazena informações das locações.

---

## Tecnologias Utilizadas

- **Java 17+**
- **Gson** – para leitura e escrita de arquivos JSON.
- **Java Collections (List, Stream, Map)** – manipulação de dados.
- **UUID** – geração de IDs únicos para itens e locações.

---

## Como Executar

1. Certifique-se de ter o **Java 17** ou superior instalado.
2. Baixe ou clone o repositório.
3. Adicione a biblioteca **Gson** ao projeto.  
   - Se estiver usando **IntelliJ**, vá em *File > Project Structure > Libraries > + > Java* e selecione o `.jar` do Gson.
4. Execute a classe `Main.java`.
5. Navegue pelos menus interativos para gerenciar clientes, itens e locações.

---

## Observações

- Cada cliente pode ter no máximo 3 locações ativas.
- Ao registrar uma locação, a quantidade de cópias disponíveis do item é automaticamente reduzida.
- Ao devolver um item, a quantidade de cópias é automaticamente incrementada.
- IDs únicos são gerados para itens e locações usando `UUID`.

---

## Possíveis Melhorias Futuras

- Implementar persistência com **banco de dados relacional** (MySQL, PostgreSQL) em vez de JSON.
- Adicionar interface gráfica usando **JavaFX** ou **Swing**.
- Validar CPF e emails de forma mais robusta.
- Implementar histórico de locações por cliente.
- Adicionar relatórios de itens mais locados ou clientes mais ativos.

---

## Autor

Igor Silveira  
Projeto desenvolvido como exercício de manipulação de arquivos JSON e lógica de negócios em Java.

