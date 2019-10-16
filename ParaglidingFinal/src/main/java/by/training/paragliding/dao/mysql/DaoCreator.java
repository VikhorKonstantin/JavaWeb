package by.training.paragliding.dao.mysql;

import by.training.paragliding.bean.entity.*;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.mysql.application.ApplicationRepository;
import by.training.paragliding.dao.mysql.competition.CompetitionRepository;
import by.training.paragliding.dao.mysql.result.ResultRepository;
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
    static Repository<Result> createResultRepository(
            final Connection newConnection) {
        return new ResultRepository(newConnection);
    }


    private DaoCreator(){}

}
