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
            "SELECT p.id, p.nome, p.quantidade, p.preco, c.id, c.nome "
                + " FROM produtos p "
                + " INNER JOIN categoria c "
                + " ON p.codCategoria = c.id ";
        
        ResultSet rs = Conexao.consultar( query );
        if( rs != null){
            try {
                while ( rs.next()  ) {                    
                    Categoria cat = new Categoria();
                    cat.setId( rs.getInt( 5 ) );
                    cat.setNome( rs.getString( 6 ) );
                    
                    Produto produto = new Produto();
                    produto.setId( rs.getInt( 1 ) );
                    produto.setNome( rs.getString( 2 ) );
                    produto.setPreco( rs.getDouble( 4 ) );
                    produto.setQuantidade( rs.getInt( 3 ) );
                    produto.setCategoria( cat );
                    lista.add( produto );
                }
            } catch (Exception e) {
                
            }
        }
        return lista;
    }
}
