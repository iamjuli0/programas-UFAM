import java.util.Hashtable;
import java.util.LinkedList;

public class ListaInvertida {
    private Hashtable<String, LinkedList<String>> tabela;

    public ListaInvertida() {
        tabela = new Hashtable<>();
    }

    public boolean insere(String palavra, String documento) {
        if (tabela.get(palavra) == null) {
        	
            LinkedList<String> novo = new LinkedList<String>();
            
            novo.add(documento);
            tabela.put(palavra, novo);
            
            return true;
        }
        
        if(tabela.get(palavra).contains(documento)) {
        	return false;
        } else {
        	tabela.get(palavra).add(documento);
            return true;
        }
        
    }

    public LinkedList<String> busca(String palavra){
        return tabela.get(palavra);
    }

    public String toString() {
        return tabela.toString();
    }
}
