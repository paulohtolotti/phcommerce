package com.phsoft.phcommerce.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StandardController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String standardEndpoint() {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>PH Commerce - API Backend</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        
                        body {
                            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            min-height: 100vh;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 20px;
                        }
                        
                        .container {
                            background: white;
                            border-radius: 10px;
                            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
                            padding: 60px 40px;
                            max-width: 700px;
                            text-align: center;
                        }
                        
                        h1 {
                            color: #333;
                            font-size: 2.5em;
                            margin-bottom: 10px;
                        }
                        
                        .subtitle {
                            color: #667eea;
                            font-size: 1.1em;
                            margin-bottom: 30px;
                        }
                        
                        p {
                            color: #666;
                            line-height: 1.8;
                            margin-bottom: 20px;
                            font-size: 1em;
                        }
                        
                        .endpoints {
                            text-align: left;
                            background: #f8f9fa;
                            border-radius: 8px;
                            padding: 25px;
                            margin: 30px 0;
                        }
                        
                        .endpoints h2 {
                            color: #333;
                            font-size: 1.3em;
                            margin-bottom: 15px;
                            text-align: center;
                        }
                        
                        .endpoint {
                            margin: 12px 0;
                            padding: 10px;
                            background: white;
                            border-left: 4px solid #667eea;
                            border-radius: 4px;
                        }
                        
                        .endpoint strong {
                            color: #667eea;
                        }
                        
                        .endpoint p {
                            margin: 0;
                            color: #555;
                            font-size: 0.95em;
                        }
                        
                        .footer {
                            margin-top: 30px;
                            padding-top: 20px;
                            border-top: 1px solid #eee;
                            color: #999;
                            font-size: 0.9em;
                        }
                        
                        .status {
                            display: inline-block;
                            background: #4caf50;
                            color: white;
                            padding: 8px 16px;
                            border-radius: 20px;
                            font-size: 0.9em;
                            margin-top: 15px;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>PH Commerce</h1>
                        <p class="subtitle">Backend de E-commerce com Java Spring</p>
                        <div class="status">✓ API em Produção</div>
                        
                        <p>Aplicação para estudar desenvolvimento backend.</p>
                        
                        <div class="endpoints">
                            <h2>Principais Endpoints</h2>
                            <div class="endpoint">
                                <strong>Usuários</strong>
                                <p>GET /users - Lista todos os usuários</p>
                                <p style="color: #ff9800; font-size: 0.85em; margin-top: 5px;">GET /users/me - Dados do usuário logado (Privado)</p>
                            </div>
                            <div class="endpoint">
                                <strong>Categorias</strong>
                                <p>GET /categories - Lista todas as categorias</p>
                            </div>
                            <div class="endpoint">
                                <strong>Produtos</strong>
                                <p>GET /products - Lista todos os produtos</p>
                            </div>
                            <div class="endpoint">
                                <strong>Pedidos</strong>
                                <p>GET /orders - Requer autenticação (Usuário ou Admin)</p>
                                <p style="color: #ff9800; font-size: 0.85em; margin-top: 5px;">Usuários veem apenas seus pedidos | Admins veem todos</p>
                            </div>
                        </div>
                        
                        <div class="footer">
                            <p>Desenvolvido com Spring Boot | Documentação disponível em /swagger-ui.html</p>
                        </div>
                    </div>
                </body>
                </html>
                """;
    }
}
