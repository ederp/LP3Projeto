package model;

import java.util.Comparator;
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

		@Override
		public int hashCode() {
			final int prime = 31;
	        int result = 1;
	        result = prime * result
	                + ((id == null) ? 0 : id.hashCode());
	        return result;
		}

		@Override
		public boolean equals(final Object obj) {
			 if (this == obj)
		            return true;
		        if (obj == null)
		            return false;
		        if (getClass() != obj.getClass())
		            return false;
		        final Orcamento other = (Orcamento) obj;
		        if (id == null) {
		            if (other.id != null)
		                return false;
		        } else if (!id.equals(other.id))
		            return false;
		        return true;
		}
		
		public static Comparator<Orcamento> getComparatorData(){
			return new Comparator<Orcamento>() {

				@Override
				public int compare(Orcamento o1, Orcamento o2) {
					return o1.getData().compareTo(o2.getData());
				}
			};
		}
}
