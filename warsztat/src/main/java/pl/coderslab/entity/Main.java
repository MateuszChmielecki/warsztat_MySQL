package pl.coderslab.entity;

public class Main {
    public static void main(String[] args) {
//        UserDao user = new UserDao ("Jan Kowalski", "kowalski@gmail11.com","123");
//        System.out.println(user.create(user));
//        System.out.println(UserDao.read(40));
//        UserDao.delete(5);
//        UserDao user = new UserDao(7, "MATEUSZ CHMIELECKI", "mateusz@gmail.com","password123");
//        UserDao.update(user);

        User [] tab = UserDao.findAll();
        for (User user : tab) {
            System.out.println(user);
        }
    }
}
