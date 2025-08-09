# Sistema de Gerenciamento de Restaurante

## Descrição do Projeto

O Sistema de Gerenciamento de Restaurante é uma aplicação projetada para otimizar o fluxo de trabalho de garçons e gerentes. Ele oferece uma solução completa para a gestão de mesas, pedidos e cardápio, com diferentes níveis de acesso para garantir a segurança e a organização das operações diárias de um restaurante.

## Funcionalidades

- **Gerenciamento de Usuários**: Cadastro e gestão de garçons e administradores (gerentes) com permissões específicas para cada papel.
- **Gerenciamento de Mesas**: Garçons podem registrar pedidos para mesas específicas e fechá-las após o pagamento. Administradores podem visualizar o status de todas as mesas em tempo real.
- **Gerenciamento de Cardápio**: Administradores podem adicionar, editar e remover pratos, além de marcar itens como disponíveis ou indisponíveis.
- **Gerenciamento de Pedidos**: Garçons podem adicionar, remover e atualizar a quantidade de itens em um pedido. O sistema calcula o valor total automaticamente.
- **Gestão de Pagamentos**: Funcionalidade para fechar pedidos, gerar o total a pagar e liberar mesas.

---

## Tecnologias Utilizadas

### Backend
- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para o desenvolvimento rápido e fácil de aplicações Java, com foco em microsserviços.
- **Spring Data JPA**: Facilita a implementação de repositórios baseados em JPA (Java Persistence API), simplificando a comunicação com o banco de dados.

### Banco de Dados
- **PostgreSQL**: Banco de dados relacional robusto e de código aberto.

### Controle de Versão e Containers
- **Git**: Sistema de controle de versão distribuído para rastrear alterações no código-fonte.
- **Docker**: Plataforma para empacotar a aplicação e suas dependências em containers, facilitando a implantação.

### Frontend
- **A ser definido**

---