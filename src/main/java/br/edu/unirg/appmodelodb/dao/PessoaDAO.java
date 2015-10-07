/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.dao;

import br.edu.unirg.appmodelodb.models.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author man1gold
 */
public class PessoaDAO {
    ConexaoDB conexao;
    public ObservableList<Pessoa> getAllOrderByName() {
        conexao = new ConexaoDB();
        ObservableList<Pessoa> lista = FXCollections.observableArrayList();
        String sql = "select * from pessoa p order by p.nome";
        try {
            ResultSet rs = conexao.connect().createStatement().executeQuery(sql);
            while(rs.next()) {
                Pessoa p = new Pessoa();
                p.setId((UUID) rs.getObject("id"));
                p.setNome(rs.getString("nome"));
                p.setDataNasc(rs.getDate("datanasc"));
                p.setApelido(rs.getString("apelido"));
                p.setEndereco(rs.getString("endereco"));
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public UUID saveOrUpdate(Pessoa p) {
        conexao = new ConexaoDB();
        String camposNaoChave = "nome = ?, datanasc = ?, apelido = ?, endereco = ?, img = ?";
        String sqlInsertOrUpdate = "INSERT INTO PESSOA SET id = ?,"+ camposNaoChave + " ON DUPLICATE KEY UPDATE " + camposNaoChave;
        //tanto no insert quanto no update, vai ser retornado o ID, caso o ID seja nulo, é pq o SQL nao foi realmente executado.
        UUID pkReturn = null; 
        try {
            PreparedStatement ps = conexao.connect().prepareStatement(sqlInsertOrUpdate, Statement.RETURN_GENERATED_KEYS);
            System.out.println("ID PEssoa = " + p.getid());
            //isert Caso o ID seja null, então passo um randomUUID() para o novo contato. 
            //Operador ternário aqui é uma ótima alternativa.
            ps.setObject(1, p.getid() == null ? UUID.randomUUID() : p.getid());//Id somente no insert
            ps.setString(2, p.getNome());
            ps.setDate(3, p.getDataNasc() != null ? new java.sql.Date(p.getDataNasc().getTime()) : null );
            ps.setString(4, p.getApelido());
            ps.setString(5, p.getEndereco());
            ps.setString(6, p.getImg());
            //update
            ps.setString(7, p.getNome());
            ps.setDate(8, p.getDataNasc() != null ? new java.sql.Date(p.getDataNasc().getTime()) : null );
            ps.setString(9, p.getApelido());
            ps.setString(10, p.getEndereco());
            ps.setString(11, p.getImg());
            
            System.out.println("SQL= " + ps.toString());
            int result = ps.executeUpdate();
            System.out.println("RESULT EXEC SQL= " + result);
            pkReturn = p.getid();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return pkReturn;
    }
}
