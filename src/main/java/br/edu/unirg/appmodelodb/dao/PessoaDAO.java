/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirg.appmodelodb.dao;

import br.edu.unirg.appmodelodb.models.Pessoa;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
