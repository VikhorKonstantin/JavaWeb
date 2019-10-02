package by.training.paragliding.service;

public interface ServiceFactory {

    UserService createUserService();

    SportsmanService createSportsmanService();
}
