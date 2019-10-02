package by.training.paragliding.dao;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.entity.User;

public interface DaoFactory {
    Repository<User> getUserRepository();

    Repository<Sportsman> getSportsmanRepository();

}
