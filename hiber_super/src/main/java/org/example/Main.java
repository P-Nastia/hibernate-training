package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("HEllo!");
        //SimpleInsert();
        //SimpleInsertFactory();
        var session = HibernateHelper.getSession();
        try{
            var result = session.createSelectionQuery("from CategoryEntity", CategoryEntity.class)
                    .getResultList();
            result.forEach(System.out::println);
        }catch (Exception e){
            System.out.println("Huston we`ve a problem " + e);
        }
    }

    private static void SimpleInsert(){
        var session = HibernateHelper.getSession();
        try{
            session.beginTransaction();
//            var category = new CategoryEntity();
//            category.setName("Тест");
//            session.persist(category); // зберігання в БД = save
//            session.getTransaction();
            CategoryEntity [] list = new CategoryEntity[2];
            list[0] = new CategoryEntity();
            list[0].setName("Borsch");
            session.persist(list[0]);

            list[1] = new CategoryEntity();
            list[1].setName("Pampushky");
            session.persist(list[1]);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }
    private static void SimpleInsertFactory(){
        var sessionFactory = HibernateHelper.getSessionFactory();
        sessionFactory.inTransaction(session -> {
            session.persist(new CategoryEntity("Kurochka"));
            session.persist(new CategoryEntity("Zyplia"));
        });
        sessionFactory.close();
    }
}