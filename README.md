---

# 💳 IFBANKV1 - Sistema Bancário Simples

Este projeto foi desenvolvido como parte da conclusão da unidade 1 de **Programação Orientada a Objetos (POO)**, utilizando **Java** e princípios fundamentais da orientação a objetos. O objetivo é implementar um sistema bancário simples, com funcionalidades básicas, utilizando **persistência em arquivos** para armazenar os dados, sem a utilização de banco de dados. A aplicação é executada exclusivamente no console, sem interface gráfica.

## Funcionalidades

Este sistema bancário permite a execução das seguintes operações:

- **Adicionar Cliente**: Cadastro de novos clientes no sistema.
- **Adicionar Conta**: Criação de contas bancárias associadas aos clientes.
- **Realizar Saque**: Permite a retirada de saldo de uma conta bancária.
- **Realizar Depósito**: Permite o depósito de valores em uma conta bancária.
- **Transferências**: Realização de transferências entre contas de diferentes clientes.
- **Emissão de Extrato**: Geração de extrato bancário para uma conta específica em um determinado período.

## Tecnologias Utilizadas

- **Java**: Linguagem principal para a implementação do sistema.
- **Persistência em Arquivo**: Dados são armazenados em arquivos locais, ao invés de um banco de dados.
- **JDBC**: Não é utilizado, já que não há banco de dados em uso.

## Estrutura do Projeto

O projeto segue os conceitos fundamentais de **Programação Orientada a Objetos**, sendo organizado com as seguintes abordagens:

- **Classes e Objetos**: O sistema é composto por classes que representam entidades como `Cliente`, `Conta`, `Transacao`, etc.
- **Encapsulamento**: Cada classe contém atributos e métodos relevantes, respeitando o princípio de encapsulamento.
- **Herança e Polimorfismo**: A estrutura permite flexibilidade para adicionar novos tipos de contas ou transações, aplicando herança e polimorfismo conforme necessário.
- **Persistência em Arquivo**: As informações de clientes, contas e transações são armazenadas em arquivos de texto, sem a necessidade de um banco de dados.

## Instalação e Execução

1. **Configuração do Projeto**:
   - Certifique-se de que o projeto tenha todos os arquivos necessários para persistir os dados (clientes.txt, contas.txt, transações.txt, etc.).
   - Não é necessário configurar banco de dados, pois os dados são armazenados em arquivos locais.

2. **Execução do Sistema**:
   - Compile e execute o projeto no console:
   
   ```bash
   javac -cp . src/main/Main.java
   java -cp . src/main.Main
   ```

3. **Utilização do Sistema**:
   - O sistema será executado no console e permitirá que o usuário interaja com as funcionalidades de adição de clientes, contas e transações.

## Contato

Caso tenha dúvidas sobre o projeto ou queira saber mais, entre em contato pelo email: [hemanoel718@gmail.com].

---
