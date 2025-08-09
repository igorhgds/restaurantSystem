package igor.henrique.security.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gerenciamento de Restaurante")
                        .version("1.0.0")
                        .description("Documentação da API do sistema de restaurante"))
                .addTagsItem(new Tag().name("1. Auth").description("Autenticação e geração de tokens"))
                .addTagsItem(new Tag().name("2. User").description("Operações relacionadas aos usuários do restaurante"))
                .addTagsItem(new Tag().name("3. Table").description("Operações relacionadas às mesas do restaurante"))
                .addTagsItem(new Tag().name("4. Order").description("Gerenciamento dos pedidos no restaurante"))
                .addTagsItem(new Tag().name("5. OrderItem").description("Gerenciamento dos itens de um pedido"))
                .addTagsItem(new Tag().name("6. Dish").description("Gerenciamento dos pratos do restaurante"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                        ))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"));
    }
}