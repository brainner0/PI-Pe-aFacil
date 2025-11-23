package com.example.pecafacil.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditRepo;

    public void registrar(String usuario, String acao, String entidade, Long entidadeId, String detalhes) {
        AuditLog log = AuditLog.builder()
                .usuario(usuario)
                .acao(acao)
                .entidade(entidade)
                .entidadeId(entidadeId)
                .detalhes(detalhes)
                .dataHora(LocalDateTime.now())
                .build();
        auditRepo.save(log);
    }
}
