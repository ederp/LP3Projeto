# LP3Projeto
<h2>Repositório do Projeto Integrador da matéria de LP3 do 2º semestre de 2020</h2>

Os arquivos do projeto encontram-se na branch [master](https://github.com/ederp/LP3Projeto/tree/master).

<h3>Descrição do funcionamento do programa:</h3>

O projeto foi feito usando interfaces gráficas do Java. Ao rodar o projeto abre uma tela inicial com as comboBoxes mês e ano e quatro botões opcionais: "Pesquisar", "Criar Novo", "Atualizar" e "Excluir". Ao clicar no botão "Pesquisar" após escolher o mês e ano, o programa exibe uma tabela caso haja despesas cadastradas, bem como a soma das despesas e recebimentos e uma mensagem caso não haja despesas cadastradas no mês. Ao clicar no botão "Criar Novo", o programa abre uma pequena tela com quatro campos: data, descrição, categoria e valor. Caso algum dos campos fique em branco e o usuário clique no botão "Cadastrar" o sistema retorna uma mensagem de erro. Do contrário, retorna uma mensagem de movimentação cadastrada com sucesso. Os botões "Atualizar" e "Excluir" só funcionam no caso em que haja uma tabela aberta, pois ambos dependem de que uma linha da tabela seja selecionada para que seja excluída ou atualizada.

<h3>Descrição detalhada das classes do programa:</h3>

O projeto foi separado em quatro pacotes de acordo com o padrão MVC: model, view, controller e dao. <br><br> O pacote [view](https://github.com/ederp/LP3Projeto/tree/master/src/view) possui quatro classes: *OrcamentoPrincipalGUI*, que exibe a tela inicial do programa, *OrcamentoNovoGUI*, que exibe a tela para criar um novo orçamento, *OrcamentoAtualizacaoGUI*, que exibe uma tela para atualizar um orçamento existente e *DateLabelFormatter* que formata as datas nas GUIs de criar novo orçamento e atualização. <br><br> O pacote [controller](https://github.com/ederp/LP3Projeto/tree/master/src/controller) possui duas classes: *OrcamentoController* que serve para pegar os dados nas GUIs e manipulá-los num arquivo de backup chamado OrcamentoBackup.txt e *Meses*, do tipo Enum para apresentar os meses numa comboBox da tela inicial e também converter as descrição dos meses em números para serem manipulados pela classe *OrcamentoController*. <br><br> O pacote [model](https://github.com/ederp/LP3Projeto/tree/master/src/model) tem duas classes: *Orcamento*, com as definições dos campos do orçamento e *OrcamentoReflexao*, usados para gerar um arquivo contendo as informações de um orçamento novo que foi criado (este arquivo é o OrcamentoInfos.txt). <br><br>E por fim, temos o pacote [dao](https://github.com/ederp/LP3Projeto/tree/master/src/dao) que contém duas classes: *OrcamentoDao*, que realiza o CRUD no arquivo *OrcamentoBackup* e *Read* que implementa a classe Runnable para fazer a leitura do arquivo por meio de uma Thread. 

<h3>Tecnologias aprendidas no semestre e o uso delas no trabalho:</h3>

Segue uma descrição das tecnologias, as classes onde foram aplicadas e a função delas:

* **Threads**

Para realizar a leitura do arquivo de modo a exibir a lista de orçamentos do mês foi criada a classe *Read* que implementa *Runnable* . Essa classe é chamada em *OrcamentoDao* e em *OrcamentoController* através de uma Thread. Em *OrcamentoDao*, o método que faz a leitura está dentro de um bloco do tipo *synchronized*.

* **Streams**

Os streams são usados na classe *OrcamentoPrincipalGUI* para adicionar itens na GUI e para exibir a tabela.

* **Stream com Reduce**

O reduce combinado com stream também é usado na classe *OrcamentoPrincipalGUI* para somar os gastos e recebimentos quando existem despesas num determinado mês. Esses gastos e recebimentos são exibidos junto com a tabela.

* **Optional**

O Optional é usado na classe *OrcamentoPrincipalGUI* para evitar que os botões de "Atualizar" e "Excluir" lancem uma exceção do tipo *NullPointerException* quando não há uma tabela na GUI.

* **Exceções**

As seguintes classes lançam diversos tipos de exceções: *OrcamentoPrincipalGUI*, *OrcamentoNovoGUI*, *OrcamentoAtualizacaoGUI*, *OrcamentoReflexao* e *OrcamentoDao*. Esta última classe se utiliza da técnica try-with-resources para fechar o arquivo quando ele é acessado para escrita ou leitura.

* **Meta-Programação**

A técnica de Meta-Programação é usada na classe *OrcamentoReflexao* para gerar um arquivo com as informações de um novo orçamento quando é adicionado.
