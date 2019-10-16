package by.training.paragliding.service;

import by.training.paragliding.bean.entity.User;
import by.training.paragliding.service.exception.ServiceException;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.concurrent.locks.ReentrantLock;

public interface UserService extends Service<User, UserService.FindByProps> {
    User readById(int id) throws ServiceException;
    User readByLoginAndPassword(String login, String password)
            throws ServiceException;
    boolean addUser(final User newUser) throws ServiceException;
    enum FindByProps {
        ALL,
        LOGIN
    }

    class Argon2Hasher{
        private static final int SALT_LENGTH = 16;
        private static final int HASH_LENGTH = 16;
        private static final int ITERATIONS = 5;
        private static final int MEMORY = 64 * 1024;
        private static final int PARALLELISM = 1;

        private static Argon2 argon2;
        /**
         * Lock for synchronization.
         */
        private static ReentrantLock lock = new ReentrantLock();
        /**
         * INSTANCE
         */
        private static Argon2Hasher instance;
        /**
         * Returns instance of controller.
         *
         * @return instance
         */
        public static Argon2Hasher getInstance() {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Argon2Hasher();
                }
            } finally {
                lock.unlock();
            }
            return instance;
        }
        
        private Argon2Hasher() {
            argon2 = Argon2Factory.createAdvanced(
                    Argon2Factory.Argon2Types.ARGON2i,
                    SALT_LENGTH,
                    HASH_LENGTH);
        }

        public String hashPassword(final String password) {
            return argon2.hash(ITERATIONS, MEMORY, PARALLELISM,
                    password.toCharArray());
        }

        public boolean verifyPassword(final String encodedPassword,
                                      final String rawPassword) {
            return argon2.verify(encodedPassword, rawPassword.toCharArray());
        }
    }
}
