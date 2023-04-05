package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);

        /*
         "findOne" adlı bir method tanımlanmıştır ve bu method, bir "int" türünde "id" parametresi alır.

          Method içerisinde, bir "Predicate" nesnesi tanımlanır. Bu nesne, Java 8 lambda ifadesi kullanılarak tanımlanır ve kullanıcının ID'sine göre arama yapmak için kullanılır.

          Daha sonra, "users" listesi üzerinde "filter" methodu kullanılarak, "predicate" nesnesine göre filtreleme işlemi gerçekleştirilir ve "findFirst" methodu ile ilk bulunan elemanın değeri "get" methodu ile döndürülür. Eğer listede aranan eleman yoksa "java.util.NoSuchElementException" hatası fırlatılır.
         */
    }

    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

}

/*

@Component anotasyonu, Spring Framework'ün bir bileşen sınıfı olduğunu belirtmek için kullanılır. Bu anotasyon, bileşenin otomatik olarak tarama ve oluşturma işlemlerini gerçekleştirmek için kullanılır. Spring Framework, bir bileşen sınıfını otomatik olarak taramak ve yönetmek için ComponentScan adı verilen bir mekanizma kullanır.

@Component anotasyonu, aynı zamanda bir Spring bileşeninin herhangi bir alt sınıfının, bu bileşen için kullanılabileceği anlamına gelir. Böylece, bileşenin yalnızca bu sınıfın kullanımına sınırlı olmayacağı ve diğer bileşenler tarafından kolayca kullanılabileceği anlamına gelir.

Bu nedenle, UserDaoService sınıfı, Spring Framework tarafından yönetilen bir bileşen olarak işaretlenmiştir, böylece uygulamanın başlatılması sırasında otomatik olarak tarama ve oluşturma işlemleri gerçekleştirilir ve bu bileşen, diğer bileşenler tarafından kolayca kullanılabilir.

*/


/*

Bu kodda "UserDaoService" adında bir bileşen sınıfı tanımlanmıştır. Bu bileşen, "User" adında bir model sınıfını kullanarak, örnek bir kullanıcı listesi yönetir.

Bileşen, Spring Framework tarafından yönetilen bir bileşen olarak işaretlenmiştir ve "Component" anotasyonu ile işaretlenmiştir. Bu, bileşenin otomatik olarak taranıp, uygulama başlatıldığında Spring Framework tarafından otomatik olarak oluşturulacak ve yönetileceği anlamına gelir.

"Bileşenin statik bir kullanıcı listesi tanımlar" kısmı, bileşenin oluşturulması sırasında yapılan ilk işlemdir. "users" adlı bir "ArrayList" oluşturulur ve örnek bir "User" nesnesi bu listeye eklenir.

Bu bileşen, Spring Framework'ün veri erişimi katmanı bileşenlerine örnek olarak kullanılabilir. Örneğin, gerçek bir veritabanına erişmek yerine, bu bileşen, örnek bir veritabanı nesnesini temsil ederek, uygulamada kullanıcıların verilerini tutar ve yönetir.



*/
