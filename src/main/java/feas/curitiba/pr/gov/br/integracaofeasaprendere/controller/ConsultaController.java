package feas.curitiba.pr.gov.br.integracaofeasaprendere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dados")
public class ConsultaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<?> buscarDados(@RequestParam String cpf) {
        // Validação simples do CPF (11 dígitos).
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return ResponseEntity.badRequest().body("CPF inválido! Certifique-se de digitar apenas 11 números.");
        }

        String sql =
                "SELECT " +
                        "  f.numcpf AS CPF, " +
                        "  CASE " +
                        "    WHEN TRIM(c.numcid) IS NULL OR TRIM(c.numcid) = '' THEN 'NAO INFORMADO' " +
                        "    ELSE c.numcid " +
                        "  END AS RG, " +
                        "  f.nomfun AS NOME, " +
                        "  CASE " +
                        "    WHEN TRIM(c.nomsoc) IS NULL OR TRIM(c.nomsoc) = '' THEN 'NAO INFORMADO' " +
                        "    ELSE c.nomsoc " +
                        "  END AS \"NOME SOCIAL\", " +
                        "  f.numcad AS MATRICULA, " +
                        "  TO_CHAR(f.datnas, 'YYYY-MM-DD') AS \"DATA DE NASCIMENTO\", " +
                        "  CASE " +
                        "    WHEN REGEXP_LIKE(c.emapar, '[A-Za-z0-9]') THEN c.emapar " +
                        "    WHEN REGEXP_LIKE(c.emacom, '[A-Za-z0-9]') THEN c.emacom " +
                        "    ELSE 'NAO INFORMADO' " +
                        "  END AS EMAIL, " +
                        "  f.tipsex AS SEXO, " +
                        "  CASE " +
                        "    WHEN f.sitafa = 1 THEN 1 " +
                        "    ELSE 0 " +
                        "  END AS ATIVO, " +
                        "  f.postra AS LOTACAO " +
                        "FROM r034fun f " +
                        "LEFT JOIN r034cpl c ON f.numemp = c.numemp " +
                        "AND f.tipcol = c.tipcol " +
                        "AND f.numcad = c.numcad " +
                        "WHERE f.numcpf = ?";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql, cpf);

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum dado encontrado para o CPF informado.");
        }

        Map<String, Object> row = resultados.get(0);

        // Reorganiza os dados na ordem exata desejada.
        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("CODIGO EMPRESA", 1);
        resposta.put("CPF", row.get("CPF"));
        resposta.put("RG", row.get("RG"));
        resposta.put("NOME", row.get("NOME"));
        resposta.put("NOME SOCIAL", row.get("NOME SOCIAL"));
        resposta.put("MATRICULA", row.get("MATRICULA"));
        resposta.put("DATA DE NASCIMENTO", row.get("DATA DE NASCIMENTO"));
        resposta.put("EMAIL", row.get("EMAIL"));
        resposta.put("SEXO", row.get("SEXO"));
        resposta.put("ATIVO", row.get("ATIVO"));
        resposta.put("ORGAO ORIGEM", "FEAS");
        resposta.put("LOTACAO", row.get("LOTACAO"));

        return ResponseEntity.ok(resposta);
    }
}
