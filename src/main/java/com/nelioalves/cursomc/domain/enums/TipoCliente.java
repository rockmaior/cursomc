package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	private int cod;
	private String descricao;

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id invalido " + cod);

	}

}
