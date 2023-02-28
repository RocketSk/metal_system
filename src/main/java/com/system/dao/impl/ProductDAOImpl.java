package com.system.dao.impl;

import com.system.dao.ProductDAO;
import com.system.model.Product;
import com.system.model.ProductGroup;
import com.system.util.HibernateUtil;
import javafx.scene.Group;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private final static String SQL_QUERY_GET_ALL_PRODUCT = "From " + Product.class.getSimpleName();
    private final static String SQL_QUERY_GET_ALL_BY_GROUP = "select product from Product product where product.productGroup =: GROUP";
    private static final String SQL_QUERY_GET_PRODUCT_BY_ID = "select product from Product product where product.id = :ID";
    private final static String SQL_QUERY_GET_PRODUCT_BY_ARTICLE = "select product from Product product where product.article = :ARTICLE";

    private static final String ID_PARAM = "ID";
    private static final String ARTICLE_PARAM = "ARTICLE";
    private static final String GROUP_PARAM = "GROUP";

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Product getById(Integer id) {
        return getProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery(SQL_QUERY_GET_ALL_PRODUCT, Product.class).list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> getAllByGroup(ProductGroup group){
        try (Session session = sessionFactory.openSession()){
            return session.createQuery(SQL_QUERY_GET_ALL_BY_GROUP, Product.class)
                    .setParameter(GROUP_PARAM, group)
                    .list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getByArticle(String article){
        return getProductByArticle(article);
    }

    @Override
    public void update(Product product) {
        Transaction transaction = null;
        Product newProduct = getById(product.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newProduct);
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setProductGroup(product.getProductGroup());
            newProduct.setCount(product.getCount());
            newProduct.setPrice(product.getPrice());
            newProduct.setArticle(product.getArticle());
            session.update(newProduct);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private Product getProductById(Integer id){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_PRODUCT_BY_ID, Product.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Product();
    }

    private Product getProductByArticle(String article) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_PRODUCT_BY_ARTICLE, Product.class)
                    .setParameter(ARTICLE_PARAM, article)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Product();
    }


}
