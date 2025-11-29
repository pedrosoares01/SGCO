# SGCO - Sistema de Gerenciamento Consultorios Odontologico
Descrição do projeto.

## Equipe de Desenvolvimento
| Ordem | Nome          |
| - | -------------- |
| 1 | pedrosoares01  |
| 2 | frxd-sloureiro |
| 3 | DaviR-250 |
| 4 | GiovanaDomingos |
| 5 | Linaa13 |

## Atores do Sistema
| Ator      | Definição     |
|:----------|:--------------|
| Recepcionista |	Usuário com cargo de recepcionista utilizará o sistema para cadastro dos usuários, recebimento dos pagamentos e agendamento de pacientes |
| Profissionais |Usuários com esse cargo utilizarão o sistema para atualizar o prontuário dos pacientes com os procedimentos realizados e para fazer orçamentos |
| Gerente | Esse tipo de usuário utilizará o sistema para fazer o controle de estoque e controlar a receita do estabelecimento |
| Paciente | Esse usuário utilizará o sistema para avaliar os profissionais |


## Requisitos Funcionais
| Id     | Ator       | Descrição   |
|:-------|:-----------|:------------|
| REQ001 | Recepcionista | Preciso cadastrar os pacientes no banco de dados |
| REQ002 | Recepcionista | Preciso poder atualizar as informações dos pacientes caso necessário |
| REQ003 | Recepcionista, profissionais | Preciso poder buscar as informações dos clientes |
| REQ004 | Profissionais | Preciso atualizar o prontuário de cada paciente com os procedimentos realizados |
| REQ005 | Gerente | Preciso cadastrar os recepcionistas, funcionários e gerentes atribuindo senhas para que estes possam utilizar o sistema |
| REQ006 | Gerente | Preciso cadastrar os procedimentos realizados no consultório com seus respectivos preços |
| REQ007 | Profissionais | Preciso selecionar os procedimentos que vão ser realizados entre os cadastrados para fazer um orçamento |
| REQ008 | Recepcionista | Preciso agendar pacientes nas agendas de cada profissional |
| REQ009 | Gerente | Preciso cadastrar os fornecedores de materiais ao consultório |
| REQ010 | Gerente | Preciso cadastrar os fornecedores de serviços ao consultório |
| REQ011 | Gerente | Preciso atualizar as informações dos fornecedores de materiais caso necessário |
| REQ012 | Gerente | Preciso atualizar as informações dos fornecedores de serviços caso necessário |
| REQ013 | Gerente | Preciso cadastrar os materiais comprados no estoque |
| REQ014 | Gerente | Preciso atualizar as informações dos materiais comprados caso necessário |
| REQ015 | Recepcionista | Preciso que o sistema avise aos clientes que eles possuem uma consulta agendada |
| REQ016 | Recepcionista | Preciso registrar o pagamento e a forma de pagamento |
| REQ017 | Gerente | Preciso que o sistema faça um relatório com as despesas e receitas do consultório |
| REQ018 | Gerente | Preciso atualizar o cadastro dos recepcionistas e profissionais |
| REQ019 | Gerente | Preciso atualizar os procedimentos realizados no consultório |
| REQ020 | Recepcionista | Preciso que o sistema emita notas fiscais |
| REQ021 | Recepcionista | Preciso que o sistema emita relatórios dos pacientes agendados |
| REQ022 | Profissional | Preciso que o sistema gere o saldo devedor ao final de cada pagamento |
| REQ023 | Gerente | Preciso poder atribuir aos logins certos cargos para limitar as funções disponíveis |
| REQ024 | Paciente | Preciso poder avaliar o profissional que me atendeu |
| REQ025 | Recepcionista | Preciso poder mostrar ao cliente uma aba para que ele avalie o profissional que o atendeu |

## Regras de Negócio
| Id     | Nome       | Descrição   |
|:-------|:-----------|:------------|
| RN001 | Data do lembrete | Lembrar o paciente da consulta 1 dia antes com um email |
| RN002 | Número mínimo de materiais | Não se pode ter menos de 1 material em estoque |
| RN003 | Informações necessárias para cadastro de clientes | Não se pode cadastrar um paciente sem que ele apresente: nome, CPF, endereço e número de telefone |
| RN004 | Intervalo da agenda | O intervalo mínimo entre pacientes agendados deve ser de 30 minutos |
| RN005 | Mínimo de pacientes | Não se pode agendar mais de 10 pacientes por dia |
| RN006 | Desconto | Se a forma de pagamento for feita em dinheiro deve-se aplicar um desconto de 10% |
| RN007 | Informações necessárias para cadastro de funcionários | É necessário cadastrar o funcionário com um e-mail válido |
| RN008 | Emissão de nota fiscal | Será feito um documento para que ele seja impresso e entregue ao paciente após o pagamento |
| RN009 | Login | Precisa-se que os logins sejam atribuídos a funções e que direcionem para as páginas correspondentes |

## Casos de Uso
| Id    | Nome       | Requisitos     | Regras de Negócio  |
|:------|:-----------|:---------------|:-------------------|
| CSU01 | Gestão de Pacientes | REQ001, REQ002, REQ003 | RN003 |
| CSU02 | Gestão de Usuários | REQ005, REQ018, REQ023 | RN007 |
| CSU03 | Gestão de Procedimentos | REQ006, REQ019 |  |
| CSU04 | Orçamento | REQ007 |  |
| CSU05 | Agenda | REQ008 | RN004, RN005 |
| CSU06 | Controle de Estoque | REQ013, REQ014 | RN002 |
| CSU07 | Gestão de Fornecedores de Materiais | REQ009, REQ011 |  |
| CSU08 | Lembrete de Consulta | REQ015 | RN001 |
| CSU09 | Recebimento de Pagamento | REQ016 | RN006 |
| CSU10 | Gestão da Receita | REQ017 |  |
| CSU11 | Prontuário | REQ004 |  |
| CSU12 | Gestão de Fornecedores de Serviços | REQ010, REQ012 |  |
| CSU13 | Emissão de Notas Fiscais | REQ020 | RN008 |
| CSU14 | Controle de Pacientes Agendados | REQ021 |  |
| CSU15 | Controle de Saldo | REQ022 |  |
| CSU16 | Avaliação de Profissionais | REQ024, REQ025 |  |
| CSU17 | Pesquisa | REQ003 |  |
| CSU18 | Login |  | RN009 |
| CSU19 | Controle de Equipamentos e Manutenção | | RN01, RN02, RN03, RN04 |

## Planejamento
| Sprint | Caso de Uso | Desenvolvedor  |
|:-------|:------------|:---------------|
| 1 | CSU01 | 5 |
| 1 | CSU02 | 1 |
| 1 | CSU03 | 3 |
| 1 | CSU04 | 3 |
| 1 | CSU05 | 2 |
| 1 | CSU06 | 4 |
| 2 | CSU07 | 4 |
| 2 | CSU08 | 1 |
| 2 | CSU09 | 3 |
| 3 | CSU10 | 5 |
| 3 | CSU11 | 2 |
| 3 | CSU12 | 4 |
| 3 | CSU13 | 3 |
| 2 | CSU14 | 2 |
| 3 | CSU15 | 1 |
| 1 | CSU16 | 2 |
| 2 | CSU17 | 2 |
| 1 | CSU18 | 1 |
| 2 | CSU19 | 5 |
