/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;


import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.entidades.Producto;
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
public class ProductoDAO {
    public void create(ProductoDTO dto){
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
    
    public void update(ProductoDTO dto){
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
    
    public ProductoDTO delete(ProductoDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            
            //recuperar entidad completa
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdProducto()));
            
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
    
    public ProductoDTO read(ProductoDTO dto){
        Session s = HibernateUtil.getSessiobFactory().getCurrentSession();
        Transaction tx = s.getTransaction();
        try{
            tx.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdProducto()));
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
            Query q = s.createQuery("from Producto as c order by c.idProducto");
//            lista = q.list();            
            for(Producto c: (List<Producto>)q.list()){
                ProductoDTO dto = new ProductoDTO();
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
        
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
       
        dto.getEntidad().setIdProducto(3);

        dto = dao.read(dto);
        dao.delete(dto);
        
        System.out.println(dao.readAll());
    }
}
