package com.example.pecafacil.config;

import com.example.pecafacil.model.Produto;
import com.example.pecafacil.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Profile("dev") // ⬅️ só roda se o perfil "dev" estiver ativo
public class DevDataSeederConfig {

    private final ProdutoService produtoService;

    @Bean
    public CommandLineRunner seedProdutosDev() {
        return args -> {

            // Evita duplicar dados toda vez que subir a aplicação
            if (!produtoService.listarTodos().isEmpty()) {
                return;
            }

            // Simula um usuário ADMIN logado durante o seed
            var auth = new UsernamePasswordAuthenticationToken(
                    "admin", // username
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            try {
                // =====================================================
                // PRODUTO 1
                // =====================================================
                Produto p1 = Produto.builder()
                        .nome("Óleo Motor 5W30 Sintético")
                        .descricao("Óleo lubrificante sintético para motores a gasolina")
                        .preco(42.90)
                        .quantidade(0)
                        .estoqueMinimo(20)
                        .marca("Petrobras")
                        .local("Prateleira A-01")
                        .ativo(true)
                        .build();
                p1 = produtoService.salvar(p1);
                produtoService.registrarEntrada(p1.getId(), 45);

                // =====================================================
                // PRODUTO 2
                // =====================================================
                Produto p2 = Produto.builder()
                        .nome("Filtro de Óleo W719/45")
                        .descricao("Filtro de óleo para veículos Volkswagen")
                        .preco(28.50)
                        .quantidade(0)
                        .estoqueMinimo(15)
                        .marca("Mann Filter")
                        .local("Prateleira B-05")
                        .ativo(true)
                        .build();
                p2 = produtoService.salvar(p2);
                produtoService.registrarEntrada(p2.getId(), 12);

                // =====================================================
                // PRODUTO 3
                // =====================================================
                Produto p3 = Produto.builder()
                        .nome("Pastilha de Freio Dianteira")
                        .descricao("Pastilha de freio cerâmica dianteira, linha leve")
                        .preco(189.90)
                        .quantidade(0)
                        .estoqueMinimo(10)
                        .marca("Fremax")
                        .local("Prateleira C-01")
                        .ativo(true)
                        .build();
                p3 = produtoService.salvar(p3);
                produtoService.registrarEntrada(p3.getId(), 8);

                // =====================================================
                // PRODUTO 4
                // =====================================================
                Produto p4 = Produto.builder()
                        .nome("Disco de Freio Ventilado")
                        .descricao("Disco de freio ventilado dianteiro, linha sedan")
                        .preco(320.00)
                        .quantidade(0)
                        .estoqueMinimo(6)
                        .marca("Bosch")
                        .local("Prateleira C-02")
                        .ativo(true)
                        .build();
                p4 = produtoService.salvar(p4);
                produtoService.registrarEntrada(p4.getId(), 4);

                // =====================================================
                // PRODUTO 5
                // =====================================================
                Produto p5 = Produto.builder()
                        .nome("Filtro de Ar do Motor")
                        .descricao("Filtro de ar para motores 1.0 a 1.6")
                        .preco(35.00)
                        .quantidade(0)
                        .estoqueMinimo(20)
                        .marca("Fram")
                        .local("Prateleira B-10")
                        .ativo(true)
                        .build();
                p5 = produtoService.salvar(p5);
                produtoService.registrarEntrada(p5.getId(), 30);

                // =====================================================
                // PRODUTO 6
                // =====================================================
                Produto p6 = Produto.builder()
                        .nome("Filtro de Combustível Flex")
                        .descricao("Filtro de combustível para veículos flex")
                        .preco(49.90)
                        .quantidade(0)
                        .estoqueMinimo(12)
                        .marca("Mahle")
                        .local("Prateleira B-08")
                        .ativo(true)
                        .build();
                p6 = produtoService.salvar(p6);
                produtoService.registrarEntrada(p6.getId(), 15);

                // =====================================================
                // PRODUTO 7
                // =====================================================
                Produto p7 = Produto.builder()
                        .nome("Lâmpada Farol H7 55W")
                        .descricao("Lâmpada automotiva H7 12V 55W")
                        .preco(24.90)
                        .quantidade(0)
                        .estoqueMinimo(30)
                        .marca("Philips")
                        .local("Prateleira D-01")
                        .ativo(true)
                        .build();
                p7 = produtoService.salvar(p7);
                produtoService.registrarEntrada(p7.getId(), 40);

                // =====================================================
                // PRODUTO 8
                // =====================================================
                Produto p8 = Produto.builder()
                        .nome("Lâmpada Farol H4 60/55W")
                        .descricao("Lâmpada automotiva H4 12V 60/55W")
                        .preco(27.90)
                        .quantidade(0)
                        .estoqueMinimo(25)
                        .marca("Osram")
                        .local("Prateleira D-02")
                        .ativo(true)
                        .build();
                p8 = produtoService.salvar(p8);
                produtoService.registrarEntrada(p8.getId(), 25);

                // =====================================================
                // PRODUTO 9
                // =====================================================
                Produto p9 = Produto.builder()
                        .nome("Aditivo para Radiador Concentrado")
                        .descricao("Aditivo concentrado para sistema de arrefecimento")
                        .preco(39.90)
                        .quantidade(0)
                        .estoqueMinimo(18)
                        .marca("Krafft")
                        .local("Prateleira E-03")
                        .ativo(true)
                        .build();
                p9 = produtoService.salvar(p9);
                produtoService.registrarEntrada(p9.getId(), 20);

                // =====================================================
                // PRODUTO 10
                // =====================================================
                Produto p10 = Produto.builder()
                        .nome("Líquido de Freio DOT 4")
                        .descricao("Fluido de freio sintético DOT 4")
                        .preco(22.50)
                        .quantidade(0)
                        .estoqueMinimo(15)
                        .marca("Castrol")
                        .local("Prateleira E-01")
                        .ativo(true)
                        .build();
                p10 = produtoService.salvar(p10);
                produtoService.registrarEntrada(p10.getId(), 18);

                // =====================================================
                // PRODUTO 11
                // =====================================================
                Produto p11 = Produto.builder()
                        .nome("Correia Dentada 120 dentes")
                        .descricao("Correia dentada para motores 1.6 16V")
                        .preco(129.90)
                        .quantidade(0)
                        .estoqueMinimo(8)
                        .marca("Dayco")
                        .local("Prateleira F-01")
                        .ativo(true)
                        .build();
                p11 = produtoService.salvar(p11);
                produtoService.registrarEntrada(p11.getId(), 10);

                // =====================================================
                // PRODUTO 12
                // =====================================================
                Produto p12 = Produto.builder()
                        .nome("Correia Poly-V 6PK")
                        .descricao("Correia Poly-V para acessórios automotivos")
                        .preco(89.90)
                        .quantidade(0)
                        .estoqueMinimo(10)
                        .marca("Gates")
                        .local("Prateleira F-02")
                        .ativo(true)
                        .build();
                p12 = produtoService.salvar(p12);
                produtoService.registrarEntrada(p12.getId(), 12);

                // =====================================================
                // PRODUTO 13
                // =====================================================
                Produto p13 = Produto.builder()
                        .nome("Jogo de Velas de Ignição")
                        .descricao("Jogo com 4 velas de ignição linha flex")
                        .preco(79.90)
                        .quantidade(0)
                        .estoqueMinimo(16)
                        .marca("NGK")
                        .local("Prateleira G-01")
                        .ativo(true)
                        .build();
                p13 = produtoService.salvar(p13);
                produtoService.registrarEntrada(p13.getId(), 20);

                // =====================================================
                // PRODUTO 14
                // =====================================================
                Produto p14 = Produto.builder()
                        .nome("Cabo de Vela Automotivo")
                        .descricao("Jogo de cabos de vela alta performance")
                        .preco(119.90)
                        .quantidade(0)
                        .estoqueMinimo(10)
                        .marca("NGK")
                        .local("Prateleira G-02")
                        .ativo(true)
                        .build();
                p14 = produtoService.salvar(p14);
                produtoService.registrarEntrada(p14.getId(), 10);

                // =====================================================
                // PRODUTO 15
                // =====================================================
                Produto p15 = Produto.builder()
                        .nome("Bateria 60Ah Selada")
                        .descricao("Bateria automotiva 12V 60Ah selada")
                        .preco(459.90)
                        .quantidade(0)
                        .estoqueMinimo(6)
                        .marca("Moura")
                        .local("Pallet H-01")
                        .ativo(true)
                        .build();
                p15 = produtoService.salvar(p15);
                produtoService.registrarEntrada(p15.getId(), 6);

                // =====================================================
                // PRODUTO 16
                // =====================================================
                Produto p16 = Produto.builder()
                        .nome("Bateria 70Ah Selada")
                        .descricao("Bateria automotiva 12V 70Ah selada")
                        .preco(529.90)
                        .quantidade(0)
                        .estoqueMinimo(4)
                        .marca("Heliar")
                        .local("Pallet H-02")
                        .ativo(true)
                        .build();
                p16 = produtoService.salvar(p16);
                produtoService.registrarEntrada(p16.getId(), 4);

                // =====================================================
                // PRODUTO 17
                // =====================================================
                Produto p17 = Produto.builder()
                        .nome("Palheta Limpador de Para-brisa 18\"")
                        .descricao("Palheta universal 18 polegadas")
                        .preco(29.90)
                        .quantidade(0)
                        .estoqueMinimo(30)
                        .marca("Bosch")
                        .local("Prateleira I-01")
                        .ativo(true)
                        .build();
                p17 = produtoService.salvar(p17);
                produtoService.registrarEntrada(p17.getId(), 40);

                // =====================================================
                // PRODUTO 18
                // =====================================================
                Produto p18 = Produto.builder()
                        .nome("Palheta Limpador de Para-brisa 20\"")
                        .descricao("Palheta universal 20 polegadas")
                        .preco(31.90)
                        .quantidade(0)
                        .estoqueMinimo(30)
                        .marca("Bosch")
                        .local("Prateleira I-02")
                        .ativo(true)
                        .build();
                p18 = produtoService.salvar(p18);
                produtoService.registrarEntrada(p18.getId(), 32);

                // =====================================================
                // PRODUTO 19
                // =====================================================
                Produto p19 = Produto.builder()
                        .nome("Desengraxante Automotivo 5L")
                        .descricao("Desengraxante para limpeza pesada de motor e peças")
                        .preco(64.90)
                        .quantidade(0)
                        .estoqueMinimo(10)
                        .marca("Vonder")
                        .local("Prateleira J-01")
                        .ativo(true)
                        .build();
                p19 = produtoService.salvar(p19);
                produtoService.registrarEntrada(p19.getId(), 12);

                // =====================================================
                // PRODUTO 20
                // =====================================================
                Produto p20 = Produto.builder()
                        .nome("Shampoo Automotivo Neutro 1L")
                        .descricao("Shampoo automotivo neutro para lavagem externa")
                        .preco(24.90)
                        .quantidade(0)
                        .estoqueMinimo(18)
                        .marca("3M")
                        .local("Prateleira J-02")
                        .ativo(true)
                        .build();
                p20 = produtoService.salvar(p20);
                produtoService.registrarEntrada(p20.getId(), 20);

                // =====================================================
                // PRODUTO 21
                // =====================================================
                Produto p21 = Produto.builder()
                        .nome("Pneu 175/65 R14")
                        .descricao("Pneu para veículos compactos 175/65 R14")
                        .preco(329.90)
                        .quantidade(0)
                        .estoqueMinimo(8)
                        .marca("Pirelli")
                        .local("Pallet K-01")
                        .ativo(true)
                        .build();
                p21 = produtoService.salvar(p21);
                produtoService.registrarEntrada(p21.getId(), 10);

                // =====================================================
                // PRODUTO 22
                // =====================================================
                Produto p22 = Produto.builder()
                        .nome("Pneu 195/55 R15")
                        .descricao("Pneu para veículos hatch 195/55 R15")
                        .preco(379.90)
                        .quantidade(0)
                        .estoqueMinimo(8)
                        .marca("Michelin")
                        .local("Pallet K-02")
                        .ativo(true)
                        .build();
                p22 = produtoService.salvar(p22);
                produtoService.registrarEntrada(p22.getId(), 8);

                // =====================================================
                // PRODUTO 23
                // =====================================================
                Produto p23 = Produto.builder()
                        .nome("Macaco Hidráulico 2 Toneladas")
                        .descricao("Macaco hidráulico tipo garrafa 2T")
                        .preco(199.90)
                        .quantidade(0)
                        .estoqueMinimo(4)
                        .marca("Vonder")
                        .local("Prateleira L-01")
                        .ativo(true)
                        .build();
                p23 = produtoService.salvar(p23);
                produtoService.registrarEntrada(p23.getId(), 5);

                // =====================================================
                // PRODUTO 24
                // =====================================================
                Produto p24 = Produto.builder()
                        .nome("Chave de Roda Cruzeta")
                        .descricao("Chave de roda cruzeta 17x19x21x23mm")
                        .preco(79.90)
                        .quantidade(0)
                        .estoqueMinimo(6)
                        .marca("Gedore")
                        .local("Prateleira L-02")
                        .ativo(true)
                        .build();
                p24 = produtoService.salvar(p24);
                produtoService.registrarEntrada(p24.getId(), 8);

                // =====================================================
                // PRODUTO 25
                // =====================================================
                Produto p25 = Produto.builder()
                        .nome("Jogo de Chave Combinada 8-19mm")
                        .descricao("Jogo com 12 chaves combinadas 8 a 19mm")
                        .preco(259.90)
                        .quantidade(0)
                        .estoqueMinimo(5)
                        .marca("Gedore")
                        .local("Prateleira L-03")
                        .ativo(true)
                        .build();
                p25 = produtoService.salvar(p25);
                produtoService.registrarEntrada(p25.getId(), 6);

                // =====================================================
                // PRODUTO 26
                // =====================================================
                Produto p26 = Produto.builder()
                        .nome("Fluido de Direção Hidráulica")
                        .descricao("Fluido para direção hidráulica sintético")
                        .preco(34.90)
                        .quantidade(0)
                        .estoqueMinimo(10)
                        .marca("Lubrax")
                        .local("Prateleira E-05")
                        .ativo(true)
                        .build();
                p26 = produtoService.salvar(p26);
                produtoService.registrarEntrada(p26.getId(), 14);

                // =====================================================
                // PRODUTO 27
                // =====================================================
                Produto p27 = Produto.builder()
                        .nome("Reparador de Pneus Spray 300ml")
                        .descricao("Reparador instantâneo de pneus em spray")
                        .preco(29.90)
                        .quantidade(0)
                        .estoqueMinimo(15)
                        .marca("Vipal")
                        .local("Prateleira J-03")
                        .ativo(true)
                        .build();
                p27 = produtoService.salvar(p27);
                produtoService.registrarEntrada(p27.getId(), 20);

                // =====================================================
                // PRODUTO 28
                // =====================================================
                Produto p28 = Produto.builder()
                        .nome("Lanterna Traseira Direita")
                        .descricao("Lanterna traseira direita para hatch compacto")
                        .preco(219.90)
                        .quantidade(0)
                        .estoqueMinimo(4)
                        .marca("Magneti Marelli")
                        .local("Prateleira M-01")
                        .ativo(true)
                        .build();
                p28 = produtoService.salvar(p28);
                produtoService.registrarEntrada(p28.getId(), 4);

                // =====================================================
                // PRODUTO 29
                // =====================================================
                Produto p29 = Produto.builder()
                        .nome("Lanterna Traseira Esquerda")
                        .descricao("Lanterna traseira esquerda para hatch compacto")
                        .preco(219.90)
                        .quantidade(0)
                        .estoqueMinimo(4)
                        .marca("Magneti Marelli")
                        .local("Prateleira M-02")
                        .ativo(true)
                        .build();
                p29 = produtoService.salvar(p29);
                produtoService.registrarEntrada(p29.getId(), 4);

                // =====================================================
                // PRODUTO 30
                // =====================================================
                Produto p30 = Produto.builder()
                        .nome("Kit Embreagem Completo")
                        .descricao("Kit de embreagem completo platô + disco + rolamento")
                        .preco(689.90)
                        .quantidade(0)
                        .estoqueMinimo(3)
                        .marca("Sachs")
                        .local("Prateleira N-01")
                        .ativo(true)
                        .build();
                p30 = produtoService.salvar(p30);
                produtoService.registrarEntrada(p30.getId(), 3);

                // =====================================================
                // EXEMPLOS DE SAÍDA (VENDAS / USO EM SERVIÇO)
                // =====================================================
                produtoService.registrarSaida(p1.getId(), 5);   // vendeu 5 óleos
                produtoService.registrarSaida(p2.getId(), 2);   // 2 filtros de óleo
                produtoService.registrarSaida(p7.getId(), 4);   // 4 lâmpadas H7
                produtoService.registrarSaida(p13.getId(), 3);  // 3 jogos de vela
                produtoService.registrarSaida(p21.getId(), 2);  // 2 pneus
                produtoService.registrarSaida(p17.getId(), 5);  // 5 palhetas

            } finally {
                // limpa o contexto de segurança para não deixar o "admin" pendurado
                SecurityContextHolder.clearContext();
            }
        };
    }
}
