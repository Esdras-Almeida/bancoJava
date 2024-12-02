package univc.banco.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private Double saldo;
    
    @Column(nullable = true)
    private double condicaoEspecial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConta tipoConta;

    public enum TipoConta {
        CC,
        ES, 
        CP  
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
        }

     	public Double getCondicaoEspecial() {
		return condicaoEspecial;
	}

        public void setCondicaoEspecial(double condicaoEspecial) {
            this.condicaoEspecial = condicaoEspecial;
        }
        
}
