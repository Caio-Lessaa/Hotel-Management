# 🏨 Sistema de Gerenciamento de Hotel

Este projeto é uma aplicação backend desenvolvida como parte do curso **Desenvolvimento Java Avançado - SENAI**. O sistema simula a gestão de um hotel, permitindo o controle de **hóspedes**, **quartos** e **reservas**, com operações CRUD completas e tratamento de exceções personalizado.

## 🚀 Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Postman (para testes de endpoints)

## 📦 Funcionalidades

O sistema possui três entidades principais:

- **Hóspede**: cadastro, listagem, atualização e remoção.
- **Quarto**: cadastro, listagem, atualização e remoção.
- **Reserva**: associação entre hóspedes e quartos com data de entrada e saída, além de operações de gerenciamento.

Cada entidade possui um serviço responsável pelas operações de CRUD. Além disso, o sistema conta com **tratamentos de exceções personalizados**, como por exemplo:
- `HospedeNaoEncontradoException`
- `QuartoNaoEncontradoException`
- `ReservaInvalidaException`
