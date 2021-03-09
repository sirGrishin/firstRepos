import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());
        HelloWorld bean1 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.equals(bean1));
        Cat catBeanA =
                (Cat) applicationContext.getBean("cat");
        Cat catBeanB =
                (Cat) applicationContext.getBean("cat");
        System.out.println(catBeanA.equals(catBeanB));
    }
}