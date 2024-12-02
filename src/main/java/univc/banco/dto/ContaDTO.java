package univc.banco.dto;

import lombok.Data;

@Data
public class ContaDTO {
    private Long id;
    private String nomeTitular;
    private Double saldo;
    private univc.banco.entity.Conta.TipoConta tipoConta;
}