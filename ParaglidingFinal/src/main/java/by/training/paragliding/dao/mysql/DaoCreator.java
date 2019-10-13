package by.training.paragliding.dao.mysql;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.mysql.application.ApplicationRepository;
import by.training.paragliding.dao.mysql.competition.CompetitionRepository;
import by.training.paragliding.dao.mysql.sportsmen.SportsmenRepository;
import by.training.paragliding.dao.mysql.user.UserRepository;

import java.sql.Connection;

final class DaoCreator {

    static Repository<User> createUserRepository(
            final Connection newConnection) {
        return new UserRepository(newConnection);
    }


    static Repository<Sportsman> createSportsmanRepository(
            final Connection newConnection) {
        return new SportsmenRepository(newConnection);
    }

    static Repository<Competition> createCompetitionRepository(
            final Connection newConnection) {
        return new CompetitionRepository(newConnection);
    }
    static Repository<Application> createApplicationRepository(
            final Connection newConnection) {
        return new ApplicationRepository(newConnection);
    }


    private DaoCreator(){}

}
