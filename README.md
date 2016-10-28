# neural-network
# README #

Esta implementação da rede neural Perceptron, que apresenta fronteira de separação linear, logo, pode ser usada apenas para classificação de padrões.

O conteúdo foi desenvolvido conforme o Livro REDES NEURAIS ARTIFICIAIS PARA ENGENHARIA E CIÊNCIAS APLICADAS: CURSO PRÁTICO, do Prof Ivan Nunes, com o qual tive aula na disciplina de mesmo nome do livro.

A temática de inteligência artificial é algo que me despertou grande interesse, Redes Neurais, por certo, é um constituindo importantíssimo e aplicável em qualquer aplicação comercial também!

Espero que gostem!

Arquivos de dados, para o treinamento da rede, que é supervisionado, e para a operação estão no diretório resources.

### Por que desta implementação? ###

* Utilizar Java para o processo de Treinamento e Operação da rede;
* Treinar os conceitos teóricos em aspectos práticos;
* [Resumo do Livro](http://www.scielo.br/pdf/ca/v23n5/11.pdf)

### Como executar? ###

* Basta ter Java 8 instalado;
* Build do projeto usa Maven, então rode em qualquer IDE e seja feliz =)

* Para executar diretamente basta executar:

```
#!shell

<PROJECT_HOME> mvn exec:java -Dexe.mainClass="Main"
```

### Como posso usar para resolver outros problemas? ###

Simples! Basta alterar os arquivos .csv com os dados de seu sistema/problema e informar na chamada dos métodos o número de sinais de cada amostra sua!

Sim, é só isso mesmo!

No comando acima, a rede será executada e resolverá os problemas conforme cada arquivo .doc descreve.
