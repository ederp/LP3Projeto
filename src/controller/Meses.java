package controller;

public enum Meses {
	JANEIRO(1),
	FEVEREIRO(2),
	MAR�O(3),
	ABRIL(4),
	MAIO(5),
	JUNHO(6),
	JULHO(7),
	AGOSTO(8),
	SETEMBRO(9),
	OUTUBRO(10),
	NOVEMBRO(11),
	DEZEMBRO(12);

	private final int numeroMes;

	Meses(int numeroMes) {
		this.numeroMes = numeroMes;
	}
	
	public int getNumeroMes() {
		return numeroMes;
	}
}
