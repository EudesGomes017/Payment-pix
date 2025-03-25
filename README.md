# Sistema de Gerenciamento de Chaves PIX com Geração de QR Code

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk)
![API REST](https://img.shields.io/badge/API-REST-blue?style=for-the-badge)

Uma aplicação Spring Boot para gerenciamento de chaves PIX e geração dinâmica de QR Codes para pagamentos.

## 📌 Funcionalidades Principais

- **Cadastro de Chaves PIX**
  - Criação de chaves aleatórias associadas a contas de usuários
  - Suporte aos principais tipos de chave PIX
- **Gerenciamento de Chaves**
  - Listagem de chaves por usuário
  - Remoção de chaves cadastradas
- **Geração de QR Codes**
  - Criação dinâmica de QR Codes para pagamentos PIX
  - Customização com valor, descrição e informações do beneficiário

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação principal
- **Spring Boot 3** - Framework para desenvolvimento da API REST
- **Spring Data JPA** - Persistência de dados
- **Biblioteca QR Code** - Geração de códigos QR (ex: ZXing)
- **Maven/Gradle** - Gerenciamento de dependências
- **Lombok** - Redução de boilerplate code

## 📚 Documentação da API

A documentação completa da API está disponível em:  
[https://dev.efipay.com.br/](https://dev.efipay.com.br/)

### Endpoints Principais

| Método | Endpoint                | Descrição                          |
|--------|-------------------------|------------------------------------|
| POST   | /api/pix/keys           | Cria uma nova chave PIX            |
| GET    | /api/pix/keys/{userId}  | Lista chaves de um usuário         |
| DELETE | /api/pix/keys/{keyId}   | Remove uma chave PIX               |
| POST   | /api/pix/qrcode         | Gera QR Code para pagamento        |
