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

## Como Executar o Projeto

1. Criar um arquivo .env na raiz do projeto com as seguintes variáveis de ambiente:

```
ACTIVE_PROFILES=development

AWS_ACCESS_KEY_ID
AWS_REGION
AWS_S3_BUCKET_NAME
AWS_SECRET_ACCESS_KEY

DATABASE_URL
DATABASE_PASSWORD
DATABASE_USERNAME

JWT_EXPIRATION_TIME
JWT_SECRET_KEY
```

2. Executar o comando `docker-compose up -d` para subir o banco de dados e a aplicação.

## Detalhes dos Endpoints

### API v1 Files

Host: `localhost:8080`
Base URL: `/api/v1/files`

---

### 1. Upload File

- **URL**: `/upload`
- **Method**: `POST`
- **Description**: Faz o upload de um arquivo.
- **Request Body**:
    - `file` (*MultipartFile*): Arquivo a ser enviado.
- **Response**:
    - **Status**: `201 Created`
    - **Body**: Vazio (indica que o upload foi bem-sucedido).

---

### 2. Get Documents Metadata

- **URL**: ``
- **Method**: `GET`
- **Description**: Retorna uma página com metadados de arquivos armazenados.
- **Request Parameters**:
    - `pageable` (*Pageable*): Parâmetros de paginação.
- **Response**:
    - **Status**: `200 OK`
    - **Body**: `Page<ReducedFileDto>`
        - Cada `ReducedFileDto` contém:
            - `fileName` (*String*): Nome do arquivo.
            - `uploadDate` (*LocalDateTime*): Data de upload do arquivo.
            - `fileUrl` (*String*): URL do arquivo.

---

### 3. Get File Metadata by Name

- **URL**: `/{fileName}`
- **Method**: `GET`
- **Description**: Busca metadados de arquivos por nome.
- **Path Variable**:
    - `fileName` (*String*): Nome do arquivo a ser buscado.
- **Response**:
    - **Status**: `200 OK`
    - **Body**: `List<ReducedFileDto>`
        - Cada `ReducedFileDto` contém:
            - `fileName` (*String*): Nome do arquivo.
            - `uploadDate` (*LocalDateTime*): Data de upload do arquivo.
            - `fileUrl` (*String*): URL do arquivo.

---

### 4. Download File

- **URL**: `/download/{fileName}`
- **Method**: `GET`
- **Description**: Faz o download do arquivo especificado.
- **Path Variable**:
    - `fileName` (*String*): Nome do arquivo a ser baixado.
- **Response**:
    - **Status**: `200 OK`
    - **Headers**:
        - `Content-Type`: `application/octet-stream`
        - `Content-Disposition`: `attachment; filename="{fileName}"`
    - **Body**: Arquivo em formato de bytes.

---

### 5. Get Download URL for File

- **URL**: `/download/{fileName}/url`
- **Method**: `GET`
- **Description**: Retorna a URL de download para o arquivo especificado.
- **Path Variable**:
    - `fileName` (*String*): Nome do arquivo para obter a URL de download.
- **Response**:
    - **Status**: `200 OK`
    - **Body**: `FileDownloadUrlDto`
        - `downloadFileUrl` (*String*): URL para download do arquivo.

---

### 6. Delete File

- **URL**: `/{fileName}`
- **Method**: `DELETE`
- **Description**: Remove o arquivo especificado do armazenamento.
- **Path Variable**:
    - `fileName` (*String*): Nome do arquivo a ser deletado.
- **Response**:
    - **Status**: `204 No Content`
    - **Body**: Vazio (indica que o arquivo foi deletado com sucesso).

---

### API v1 Users

Host: `localhost:8080`
Base URL: `/api/v1/users`

---

### 1. Create User

- **URL**: `/`
- **Method**: `POST`
- **Description**: Cria um novo usuário.
- **Request Body**:
    - `CreateUserDto`: Objeto contendo as informações do usuário.
        - `fullName` (*String*): Nome completo do usuário.
        - `username` (*String*): Nome de usuário.
        - `password` (*String*): Senha do usuário.
        - `role` (*String*): Papel do usuário (por exemplo, "ADMIN", "USER").
- **Response**:
    - **Status**: `201 Created`
    - **Body**: `UserDto`
        - `userId` (*Long*): ID do usuário.
        - `fullName` (*String*): Nome completo.
        - `username` (*String*): Nome de usuário.
        - `role` (*String*): Papel do usuário.
        - `createdAt` (*LocalDateTime*): Data de criação do usuário.
        - `updatedAt` (*LocalDateTime*): Data de última atualização do usuário.

---

### 2. Get Authenticated User

- **URL**: `/me`
- **Method**: `GET`
- **Description**: Retorna os dados do usuário autenticado no momento.
- **Response**:
    - **Status**: `200 OK`
    - **Body**: `UserDto`
        - `userId` (*Long*): ID do usuário autenticado.
        - `fullName` (*String*): Nome completo.
        - `username` (*String*): Nome de usuário.
        - `role` (*String*): Papel do usuário.
        - `createdAt` (*LocalDateTime*): Data de criação do usuário.
        - `updatedAt` (*LocalDateTime*): Data de última atualização do usuário.

---

### 3. Get All Users

- **URL**: `/`
- **Method**: `GET`
- **Description**: Retorna uma página com a lista de todos os usuários registrados.
- **Request Parameters**:
    - `pageable` (*Pageable*): Parâmetros de paginação.
- **Response**:
    - **Status**: `200 OK`
    - **Body**: `Page<UserDto>`
        - Cada `UserDto` contém:
            - `userId` (*Long*): ID do usuário.
            - `fullName` (*String*): Nome completo.
            - `username` (*String*): Nome de usuário.
            - `role` (*String*): Papel do usuário.
            - `createdAt` (*LocalDateTime*): Data de criação do usuário.
            - `updatedAt` (*LocalDateTime*): Data de última atualização do usuário.

---

### API v1 Authentication

Host: `localhost:8080`
Base URL: `/api/v1/auth`

---

### 1. User Login

- **URL**: `/login`
- **Method**: `POST`
- **Description**: Autentica o usuário e gera um token JWT para acesso.
- **Request Body**:
    - `LoginRequestDto`: Objeto contendo as informações de login.
        - `username` (*String*): Nome de usuário.
        - `password` (*String*): Senha do usuário.
- **Response**:
    - **Status**: `200 OK`
    - **Body**: `LoginResponseDto`
        - `token` (*String*): Token JWT gerado para o usuário.
        - `expirationTime` (*LocalDateTime*): Data e hora de expiração do token.

---
