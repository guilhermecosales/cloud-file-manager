# Cloud File Manager

---

### Desafio: Sistema de Gerenciamento de Documentos

**Contexto**:  
Você foi contratado para desenvolver um sistema simples de gerenciamento de documentos para uma empresa. A aplicação
deve permitir que os usuários façam upload de documentos, que serão armazenados na **Amazon S3**, e que os metadados
desses documentos sejam salvos em um banco de dados relacional (MySQL, PostgreSQL, etc.). Além disso, a aplicação deve
possibilitar a busca e o download dos documentos armazenados.

---

### Requisitos do Desafio:

1. **Upload de Documentos:**
    - Implementar uma API que permita o upload de documentos (PDFs, imagens, etc.).
    - O documento deve ser salvo no Amazon S3.
    - Após o upload, armazenar os seguintes metadados no banco de dados:
        - Nome do arquivo original.
        - URL do arquivo no S3.
        - Data de upload.
        - Tamanho do arquivo.
        - Tipo de arquivo (MIME type).

2. **Listagem de Documentos:**
    - Implementar uma API para listar todos os documentos cadastrados. A lista deve exibir:
        - Nome do arquivo.
        - Data de upload.
        - URL para download.

3. **Busca por Nome:**
    - Implementar uma funcionalidade que permita buscar um documento pelo nome ou parte do nome, retornando seus
      metadados.

4. **Download de Documentos:**
    - Implementar uma API que permita o download de um documento específico, através de sua URL armazenada no banco de
      dados.

5. **Remoção de Documentos:**
    - Implementar uma funcionalidade que permita remover um documento tanto do S3 quanto do banco de dados.

---

### Detalhes Técnicos:

- **Spring Boot**:
    - Utilize Spring Boot para implementar as APIs RESTful.
    - Use o Spring Data JPA para a interação com o banco de dados.
    - Autenticação básica pode ser opcional, mas recomendada (por exemplo, com Spring Security).

- **Banco de Dados**:
    - Pode ser utilizado MySQL, PostgreSQL ou outro banco relacional de sua escolha.
    - Definir um modelo de entidade para armazenar os metadados dos documentos.

- **Amazon S3**:
    - Use o SDK do AWS para realizar o upload e o download de arquivos no S3.
    - Crie um bucket no S3 para armazenar os documentos.

---

### Critérios de Avaliação:

- **Funcionamento Completo**: Todos os requisitos devem estar implementados e funcionais.
- **Boas Práticas de Código**: O código deve ser limpo, organizado e seguir as melhores práticas de desenvolvimento (
  incluindo tratamento de exceções e injeção de dependências).
- **Desempenho**: A aplicação deve lidar eficientemente com grandes arquivos.
- **Segurança**: Se possível, adicione camadas de segurança para proteger os endpoints (autenticação e autorização).
- **Documentação**: Uma breve documentação explicando como configurar e executar o projeto, incluindo as credenciais
  necessárias para o S3 e o banco de dados.

---
