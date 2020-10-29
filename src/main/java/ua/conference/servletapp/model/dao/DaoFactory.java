package ua.conference.servletapp.model.dao;

import ua.conference.servletapp.model.dao.impl.JdbcDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract ConferenceDao createConferenceDao();
    public abstract ReportDao createReportDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JdbcDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
