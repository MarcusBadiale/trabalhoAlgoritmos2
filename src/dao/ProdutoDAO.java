package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Cidade;
import model.Cliente;
import model.ClientePF;
import model.Produto;

/**
 *
 * @author assparremberger
 */
public class ProdutoDAO {
    
    public static void inserir(Produto produto){
    	String query = "INSERT INTO produtos "
                + " (nome, quantidade, preco, codCategoria)"
                + " VALUES ( '" + produto.getNome()           		+ "' , "
                         + " '" + produto.getQuantidade()     		+ "' , "
                         + " '" + produto.getPreco()          		+ "' , "
                         + "  " + produto.getCategoria().getId()    + " ) ";
            Conexao.executar( query );
    }
    
    public static void editar(Produto produto){
    	String query = "UPDATE produtos SET "
                + " nome = '" 			+ produto.getNome()           		+ "' , "
                + " quantidade = '" 	+ produto.getQuantidade()     		+ "' , "
                + " preco = " 			+ produto.getPreco()        		+ "  , "
                + " codCategoria = " 	+ produto.getCategoria().getId() 	+ "    "
                + " WHERE id = " 		+ produto.getId();
            Conexao.executar( query );
    }
    
    public static void excluir(int idProduto){
    	String query = "DELETE FROM produtos "
                + " WHERE id = " + idProduto;
    Conexao.executar( query );
    }
    
    public static List<Produto> getProdutos(){
    	List<Produto> lista = new ArrayList<>();
        String query = 
            "SELECT c.id, c.nome, c.quantidade, c.preco, "
                + " FROM produtos c "
                + " INNER JOIN categoria d "
                + " ON c.codCategoria = d.id ";
        
        ResultSet rs = Conexao.consultar( query );
        if( rs != null){
            try {
                while ( rs.next()  ) {                    
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 6 ) );
                    cat.setNome( rs.getString( 7 ) );
                    
                    Produto produto = new Produto();
                    produto.setId( rs.getInt( 1 ) );
                    produto.setNome( rs.getString( 2 ) );
                    produto.setPreco( rs.getDouble( 3 ) );
                    produto.setQuantidade( rs.getInt( 4 ) );
                    produto.setCategoria( cat );
                    lista.add( produto );
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
}
