package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Cidade;

/**
 *
 * @author assparremberger
 */
public class CategoriaDAO {
    
    public static void inserir(Categoria categoria){
    	String query = "INSERT INTO categoria ( nome ) "
                + " VALUES ('" + categoria.getNome() + "'); ";
        Conexao.executar( query );
    }
    
    public static void editar(Categoria categoria){
    	String query = "UPDATE categoria SET "
                + " nome =  '" + categoria.getNome() + "' "
                + " WHERE id = " + categoria.getId();
   Conexao.executar( query );
    }
    
    public static void excluir(int idCategoria){
    	String query = "DELETE FROM categoria  "
                + " WHERE id = " + idCategoria;
   Conexao.executar( query );
    }
    
    public static List<Categoria> getCategorias(){
        List<Categoria> lista = new ArrayList<>();
        String query = "SELECT id, nome FROM categoria ORDER BY nome";
        ResultSet rs = Conexao.consultar( query );
        
        if( rs != null ){
           
            try {
                while ( rs.next() ) {                    
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 1 ) );
                    cat.setNome( rs.getString( 2 ) );
                    lista.add(cat);
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
    
    public static Categoria getCategoriaById( int idCategoria ){
    	String query = "SELECT id, nome FROM categoria "
                + " WHERE id = " + idCategoria;
   ResultSet rs = Conexao.consultar( query );
   if( rs != null ){
       try {
           rs.next();                   
           Categoria cat = new Categoria();
           cat.setId( rs.getInt( 1 ) );
           cat.setNome( rs.getString( 2 ) );
           return cat;
       } catch (Exception e) {
           return null;
       }
   }else{
       return null;
   }
    }
    
}
