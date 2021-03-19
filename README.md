# LP3Projeto
<h1>Repositório do Projeto Integrador da matéria de LP3 do 2º semestre de 2020<h1>

Os arquivos do projeto encontram-se na branch master

Descrição do funcionamento do programa:

O projeto foi feito usando interfaces gráficas do Java. Ao rodar o projeto abre uma tela inicial com as comboBoxes mês e ano e quatro botões opcionais: "Pesquisar", "Criar Novo", "Atualizar" e "Excluir". Ao clicar no botão "Pesquisar" após escolher o mês e ano, o programa exibe uma tabela caso haja despesas cadastradas, bem como a soma das despesas e recebimentos e uma mensagem caso não haja despesas cadastradas no mês. Ao clicar no botão "Criar Novo", o programa abre uma pequena tela com quatro campos: data, descrição, categoria e valor. Caso algum dos campos fique em branco e o usuário clique no botão "Cadastrar" o sistema retorna uma mensagem de erro. Do contrário, retorna uma mensagem de movimentação cadastrada com sucesso. Os botões "Atualizar" e "Excluir" só funcionam no caso em que haja uma tabela aberta, pois ambos dependem de que uma linha da tabela seja selecionada para que seja excluída ou atualizada.

Descrição detalhada das classes do programa:

O projeto foi separado em quatro pacotes de acordo com o padrão MVC: model, view, controller e dao. O pacote view possui quatro classes: OrcamentoPrincipalGUI, que exibe a tela inicial do programa, OrcamentoNovoGUI, que exibe a tela para criar um novo orçamento, OrcamentoAtualizacaoGUI, que exibe uma tela para atualizar um orçamento existente
