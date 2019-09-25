package by.training.paragliding.dao;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.dao.mysql.sportsmen.SportsmenRepository;
import by.training.paragliding.dao.mysql.user.UserRepository;

import java.sql.Connection;
/*
todo: Connection.
Hold be singleton?
 */
public class DaoFactory {
    /**
     * User DAO.
     */
    private Repository<User> userRepository;
    /**
     * Sportsman DAO.
     */
    private Repository<Sportsman> sportsmanRepository;

    public DaoFactory(final Connection newConnection) {
        userRepository = new UserRepository(newConnection);
        sportsmanRepository = new SportsmenRepository(newConnection);
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public Repository<Sportsman> getSportsmanRepository() {
        return sportsmanRepository;
    }
}
