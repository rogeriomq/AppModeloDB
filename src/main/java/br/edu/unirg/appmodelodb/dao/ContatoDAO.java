/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.dao;

import br.edu.unirg.appmodelodb.models.Contato;
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
public class ContatoDAO {
    ConexaoDB conexao;
    public ObservableList<Contato> getAllContactsByPessoaId(UUID pk) {
        conexao = new ConexaoDB();
        ObservableList<Contato> lista = FXCollections.observableArrayList();
        String sql = "select * from Contato x where x.pessoaid = ? order by x.tipo";
        try {
            PreparedStatement ps = conexao.connect().prepareStatement(sql);
            ps.setObject(1, pk);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contato c = new Contato();
                //setar os atributos de contato
                c.setId(rs.getInt("id"));
                c.setTipo(rs.getInt("tipo"));
                c.setDescr(rs.getString("descr"));
                c.setPessoaId((UUID)rs.getObject("pessoaid"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //Caso o retorno seja -1, é porque houve exceção.
    public Integer saveOrUpdate(Contato x, UUID pessoaId) {
        conexao = new ConexaoDB();
        String camposNaoChave = "tipo = ?, descr = ?, pessoaid = ?";
        String sqlInsertOrUpdate = "INSERT INTO contato SET id = ?,"+ camposNaoChave + " ON DUPLICATE KEY UPDATE " + camposNaoChave;
        //tanto no insert quanto no update, vai ser retornado o ID, caso o ID seja nulo, é pq o SQL nao foi realmente executado.
        Integer pkReturn = null; 
        try {
            System.out.println("IDContato = " + x.getId());
            PreparedStatement ps = conexao.connect().prepareStatement(sqlInsertOrUpdate, Statement.RETURN_GENERATED_KEYS);
            //Id somente no insert, quando ele eh ZERO, é porque o IntegerProperty tem um valor defaul e ele eh o ZERO e  nao null.
            ps.setObject(1, x.getId() == 0? null : x.getId());
            ps.setInt(2, x.getTipo());
            ps.setString(3, x.getDescr());
            ps.setObject(4, pessoaId);
            //update
            ps.setInt(5, x.getTipo());
            ps.setString(6, x.getDescr());
            ps.setObject(7, pessoaId);
            
            int result = ps.executeUpdate();
            System.out.println("saveOrUpdate Contato = " + result);
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                pkReturn = rs.getInt(1);
                System.out.println("new ID= " + pkReturn);
            } else {
                pkReturn = x.getId();
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            pkReturn = -1;
        } 
        return pkReturn;
    }
}
