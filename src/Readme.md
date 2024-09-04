# Visualização de Ordenação em Pirâmide (SAV)

Este projeto é uma aplicação Java que visualiza algoritmos de ordenação em um formato de pirâmide. Ele permite que o usuário insira um tipo de dado (números ou caracteres), valores, algoritmo de ordenação e ordem (crescente ou decrescente). A visualização é feita em uma interface gráfica usando a biblioteca Swing.

## Estrutura do Projeto

O projeto é composto pelas seguintes classes:

- **SAV**: Classe principal que inicializa a aplicação.
- **PyramidPanel**: Classe que gerencia a visualização da pirâmide.
- **InputUtils**: Classe utilitária para validação e processamento de entradas.
- **Sorter**: Classe que implementa os algoritmos de ordenação.

## Requisitos

- JDK 8 ou superior
- Biblioteca Swing (inclusa no JDK)

## Como Usar

Para executar a aplicação, utilize o seguinte comando:

```bash
java SAV t=<tipo> v=<valores> a=<algoritmo> o=<ordem>
```
# Descrição do Diagrama de Classes UML

## Classes e Métodos

### SAV
- **Métodos**:
    - `main(String[] args)`: Método principal que inicia a aplicação.
    - `sortAndDisplay()`: Método responsável por chamar a lógica de ordenação e exibir os resultados.
    - Métodos privados para processar tipos e valores.

### PyramidPanel
- **Atributos**:
    - `Object[] array`: Armazena os valores a serem exibidos.
- **Métodos**:
    - `PyramidPanel(Object[] array)`: Construtor que aceita um array de `Object`.
    - `updatePyramid(Object[] array)`: Atualiza o array e chama a atualização da interface gráfica.
    - `paintComponent(Graphics g)`: Desenha as colunas na interface.

### InputUtils
- **Métodos**:
    - `isTipoValido(String tipo): boolean`: Verifica se o tipo é válido.
    - `processarNumeros(String[] valoresArray): Integer[]`: Processa um array de strings em números.
    - `processarCaracteres(String[] valoresArray): Character[]`: Processa um array de strings em caracteres.

### Sorter
- **Enumeração**:
    - `Algorithm`: Define os algoritmos de ordenação disponíveis (Bubble Sort, Merge Sort, Quick Sort).
- **Métodos**:
    - `sort(T[] array, Algorithm algorithm, PyramidPanel panel, boolean crescente)`: Método genérico para ordenar.
    - Métodos privados para implementações específicas de ordenação.

## Relacionamentos
- A classe `SAV` utiliza `InputUtils` para validar e processar os dados de entrada.
- A classe `SAV` cria uma instância de `PyramidPanel` para exibir os dados.
- A classe `SAV` também utiliza `Sorter` para realizar a ordenação.
- `Sorter` interage com `PyramidPanel` para atualizar a visualização durante a ordenação.

## Fluxograma do Processo de Ordenação
![fluxograma AT4](../src/image/fluxo2.png)

## Exemplos de Execução
### Para ordenar números em ordem crescente usando Bubble Sort:
```bash
java SAV t=n v=5,3,8,1 a=BUBBLE_SORT o=c
```
Resultado:
![fluxograma AT4](../src/image/Teste1.png)
### Para ordenar caracteres em ordem decrescente usando Merge Sort:
```bash
java SAV t=c v=a,b,c,d e=MERGE_SORT o=d
```
Resultado:
![fluxograma AT4](../src/image/Teste2.png)