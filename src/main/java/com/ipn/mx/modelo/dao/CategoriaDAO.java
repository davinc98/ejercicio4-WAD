/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.entidades.Categoria;
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
public class CategoriaDAO {
    public void create(CategoriaDTO dto){
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
    
    public void update(CategoriaDTO dto){
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
    
//    public void delete(CategoriaDTO dto){
//        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
//        Transaction tx = s.getTransaction();
//                
//        try{
//            tx.begin();
//            
//            //Recuperar la entidad completa
//            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCategoria()));
//            
//            s.delete(dto.getEntidad());
//            tx.commit();
//        }catch(HibernateException e){
//            if(tx != null && tx.isActive()){
//                tx.rollback();
//            }
//        }
//    }
    
    public CategoriaDTO delete(CategoriaDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
                
        try{
            tx.begin();
            
            //Recuperar la entidad completa
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCategoria()));
            
            s.delete(dto.getEntidad());
            tx.commit();
        }catch(HibernateException e){
            if(tx != null && tx.isActive()){
                tx.rollback();
            }
            return null;
        }
        return dto;
    }
    
    public CategoriaDTO read(CategoriaDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCategoria()));
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
            Query q = s.createQuery("from Categoria as c order by c.idCategoria");
//            lista = q.list()            
            for(Categoria c: (List<Categoria>)q.list()){
                CategoriaDTO dto = new CategoriaDTO();
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
        
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
       
        dto.getEntidad().setIdCategoria(3);
//        dto.getEntidad().setNombreCategoria("Linea blanca");
//        dto.getEntidad().setDescripcionCategoria("Descripcion prueba");
//        dao.update(dto);
        
        dao.delete(dto);
        
        System.out.println(dao.readAll());
    }
}
