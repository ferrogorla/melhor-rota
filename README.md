# Descrição
O programa **Melhor Rota** foi desenvolvido para fornecer duas interfaces para o usuário:
- uma através de uma API REST;
- outra através linha de comando pelo terminal;

As duas interfaces possibilitam a pesquisa da melhor rota de viagem, porém, a inserção de novas rotas está disponível apenas através da API REST.

Para que a leitura das rotas e valores possa ser realizada corretamente pelas interfaces, é necessário informar o caminho do arquivo CSV que contém esses dados. Abaixo segue exemplo do conteúdo do arquivo.

    GRU,BRC,10
    BRC,SCL,5
    GRU,CDG,75
    GRU,SCL,20
    GRU,ORL,56
    ORL,CDG,5
    SCL,ORL,20

**Motivos para a utilização do padrão Singleton:**

- Evitar consumo excessivo e desnecessário de memória;
- Evitar o processamento constante do arquivo;
- Permitir que a instancia seja acessada em todas as camadas evitando problemas de concorrência.

**Algoritmo de Dijkstra**

Para encontrar a rota com menor valor foi utilizado o [algoritmo de Dijkstra](https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/dijkstra.html), que calcula o caminho com menor custo.

Também foi adicionado ao programa uma variedade de validações para garantir o correto tratamentos das informações de entrada, garantindo que as mensagens de validação sejam retornadas ao usuário de forma clara.

# Como executar
- Acessar a pasta do programa e executar o comando para compilar o projeto.


    cd melhor-rota
  
    mvn clean install

- Execute o programa informando através de parâmetro o caminho válido do arquivo CSV contendo as rotas.


    mvn spring-boot:run -Dspring-boot.run.arguments=--file=[DIRETORIO_DO_ARQUIVO]/routes.csv

# Documentação da API

**GET** - Encontrar a melhor rota através da API

    curl -X GET "http://localhost:8080/route?from=GRU&to=CDG"


**PUT** - Inserir uma nova rota através da API

    curl -X PUT "http://localhost:8080/route" -H  "Content-Type: application/json" -d "{\"from\":\"GRU\",\"to\":\"CRT\",\"price\":35}"

# Testes

Os testes foram elaborados utilizando JUnit e Mockito.

**Executando os testes**

    mvn test

**Executar apenas uma classe de testes**

    mvn -Dtest=RouteValidateTest test

**Executar um único teste**

    mvn -Dtest=RouteValidateTest#whenRouteIsNull test