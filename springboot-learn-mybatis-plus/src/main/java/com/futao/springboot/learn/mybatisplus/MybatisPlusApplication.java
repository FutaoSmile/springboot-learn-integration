package com.futao.springboot.learn.mybatisplus;

import com.futao.springboot.learn.mybatisplus.dao.UserMapper;
import com.futao.springboot.learn.mybatisplus.service.UserService;
import com.futao.springboot.learn.mybatisplus.transactiont.service.AService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author futao
 * @date 2020/3/9.
 */
@MapperScan("com.futao.springboot.learn.mybatisplus.dao")
@SpringBootApplication
public class MybatisPlusApplication implements ApplicationRunner {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AService aService;

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        UserService userService = webApplicationContext.getBean(UserService.class);
//        UserMapper userMapper = webApplicationContext.getBean(UserMapper.class);
//        aService.m1();

//        userService.insert(User.builder().name("张三").age(33).birthday(LocalDate.of(1990, 12, 12)).build());
//        userService.insert(User.builder().name("李四").age(44).birthday(LocalDate.of(1944, 7, 7)).build());
//        userService.insert(User.builder().name("王二").age(22).birthday(LocalDate.of(1922, 6, 6)).build());
//        userService.insert(User.builder().name("麻子").age(18).birthday(LocalDate.of(2018, 1, 1)).build());
//        userService.insert(User.builder().name("付韬").age(25).birthday(LocalDate.of(1995, 1, 23)).build());


//        LambdaQueryChainWrapper<User> qw = new LambdaQueryChainWrapper<>(userMapper);

//        //只查询需要的几列
//        List<User> list = qw
//                .select(User::getFullName, User::getAge)
//                .isNotNull(User::getBirthday)
//                .and(x ->
//                        x.likeRight(User::getFullName, "张")
//                                .or()
//                                .ge(User::getAge, 20)
//                )
//                .list();

        //分页查询，并且设置是否需要查询count信息
//        IPage<User> page = qw.orderByDesc(User::getCreateDateTime).page(new Page<>(2, 5, true));
//        System.out.println(JSON.toJSONString(page, true));

//        Page<User> userPage = userMapper.selectByXmlPage(new Page<>(1, 3), qw);
//        System.out.println(JSON.toJSONString(userPage, true));


        //使用mybatis的条件构造器作为xml的where条件
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
//        userLambdaQueryWrapper.ge(User::getAge, 20);
//        List<User> users = userMapper.selectByXml(userLambdaQueryWrapper);
//        System.out.println(JSON.toJSONString(users, true));


//        QueryWrapper<User> queryWrapper = Wrappers.<User>query().select("min(age) minAge,max(age) maxAge avg(age) avgAge").groupBy("full_name").having("minAge>{0}", 0);
//        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
//        System.out.println(JSON.toJSONString(maps, true));


        //AR模式
//        User.builder().fullName("AR").birthday(LocalDate.now()).age(299).build().insert();
    }
}
