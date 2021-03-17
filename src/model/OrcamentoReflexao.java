package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class OrcamentoReflexao {

	public OrcamentoReflexao(Object objeto, Class classe) {
		super();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("OrcamentoInfos.txt"))) {
			bw.newLine();
			bw.write("===========================");
			bw.write("\nNome da classe: "+classe.getName());
			bw.write("\nNome da classe (simples): "+classe.getSimpleName());
			bw.write("\nNome canônico da classe :"+classe.getResource(classe.getName()));
			
			bw.write("\nTipo: "+classe.getTypeName());
			
			bw.write("\n===========================");
			bw.write("\n=========ATRIBUTOS=========");
			
			Field[] atributos = classe.getDeclaredFields();
			for(Field f: atributos) {
				f.setAccessible(true);
				bw.write("\n"+f.getName()+ ": "+f.getType().getTypeName()
						+" (primitivo: "+f.getType().isPrimitive()+")"
						+" -> Valor: "+f.get(objeto));
			}
			bw.write("\n===========================");
			bw.write("\n==========METODOS==========");
			
			Method[] metodos = classe.getDeclaredMethods();
			
			for(Method m: metodos) {
				bw.write("\n"+m.getName()+" - parâmetros: "+ Arrays.toString(m.getParameterTypes())
					+ " - retorno: "+m.getReturnType().getSimpleName());
			}
		} catch (IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
	}
}
