/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author leoj_
 */
public class UsuarioDAO {
    public void create(UsuarioDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            s.save(dto.getEntidad());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
    
    public void update(UsuarioDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            s.update(dto.getEntidad());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
    
    public void delete(UsuarioDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            
            //recuperar entidad completa
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdUsuario()));
            
            s.delete(dto.getEntidad());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
    
    public UsuarioDTO read(UsuarioDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdUsuario()));
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return dto;
    }
    
    public List readAll(){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        List lista = new ArrayList();
        try{
            tx.begin();
            Query q = s.createQuery("from Usuario as c order by c.idUsuario");

            for(Usuario c: (List<Usuario>)q.list()){
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(c);
                lista.add(dto);
            }
            
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
        }
        return lista;
    }
    
    public static void main(String[] args){
        
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
       
        dto.getEntidad().setIdUsuario(3);

        dto = dao.read(dto);
        dao.delete(dto);
        
        System.out.println(dao.readAll());
    }
}
