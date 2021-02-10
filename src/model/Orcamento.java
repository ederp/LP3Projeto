package model;

import java.util.Date;

public class Orcamento {
		private Integer id;
		private Date data;
		private String descricao;
		private String categoria;
		private double valor;
		
		public Orcamento(Integer id, Date data, String descricao, String categoria, double valor) {
			super();
			this.id = id;
			this.data = data;
			this.descricao = descricao;
			this.categoria = categoria;
			this.valor = valor;
		}
		
		public Orcamento() {
		}

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getCategoria() {
			return categoria;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}
}
