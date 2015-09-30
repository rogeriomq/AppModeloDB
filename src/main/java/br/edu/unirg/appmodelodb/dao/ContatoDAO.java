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
                System.out.println(rs.getString("descr"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
