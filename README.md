# Projeto de Consulta de Endereços

Este repositório contém o código fonte de um projeto desenvolvido como parte da conclusão da Atividade A3 da Unidade Curricular Sistemas Distribuídos e Mobile na Universidade Anhembi Morumbi (SP). O projeto consiste em uma aplicação Java que permite aos usuários consultar informações de endereços a partir do CEP informado, realizando conexão web com o Google Maps para informar rotas e localização precisa de acordo com o solicitado pelo usuário.

## Introdução

A atividade proposta visava criar uma aplicação capaz de realizar consultas de endereços através de um serviço de busca de CEP e, adicionalmente, permitir a visualização do histórico de pesquisas realizadas durante a sessão. A aplicação foi desenvolvida em Java e utiliza a biblioteca Swing para construção das interfaces gráficas.

O objetivo principal do projeto é oferecer uma ferramenta simples e intuitiva para consulta de informações de endereços, visando facilitar a obtenção de detalhes sobre locais específicos a partir do CEP fornecido pelo usuário. Além disso, a funcionalidade de histórico de pesquisas permite ao usuário revisitar consultas anteriores e realizar filtros para uma melhor organização e visualização dos dados.

## O Que o Usuário Pode Obter

Ao utilizar esta aplicação, o usuário pode:

- Realizar consultas de endereços a partir do CEP informado.
- Visualizar detalhes do endereço, incluindo logradouro, bairro, cidade, estado, IBGE, DDD e temperatura.
- Navegar pelo histórico de pesquisas realizadas durante a sessão.
- Filtrar o histórico de pesquisas por localidade, bairro ou UF.
- Ver a rota entre dois endereços utilizando a funcionalidade de mapa.
- Ver a localização de um cep informado de forma individual.

## Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java:** Linguagem de programação utilizada para desenvolver a lógica do sistema.
- **Swing:** Biblioteca gráfica do Java utilizada para criar a interface gráfica do usuário.
- **JSON:** Formato de dados utilizado para troca de informações entre o sistema e a API ViaCEP.
- **API ViaCEP:** [API pública](https://viacep.com.br/) utilizada para consultar informações de endereços a partir do CEP informado.
- **API OpenWeatherMap:** [API pública](https://openweathermap.org/) utilizada para obter informações de temperatura associadas a uma localidade.
- **Git:** Sistema de controle de versão utilizado para o versionamento do código fonte.
- **GitHub:** Plataforma de hospedagem de código fonte utilizada para armazenar e compartilhar o projeto.
- **Visual Studio Code:** IDE (Ambiente de Desenvolvimento Integrado) utilizada para escrever, depurar e gerenciar o código-fonte do projeto.

## Responsáveis pelo Código

Este projeto foi desenvolvido por:

- **Matheus Guerreiro** - [125111352906]
- **Sergio Carrero Durante** - [12522231828]
- **Gabriel Sabatini de Oliveira** - [125111355020]
- **Thiago Ribeiro Guerreiro** - [125221101587]
- **Giuliano D'Agosto Neto** - [125111363272]
- **Kauã Amadeu Souza** - [125111348553]
- **[Matheus André da Silva]** - [125111367931]
