package cn.lxsir.uniapp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * <p>
 * 代码生成器 示例
 * </p>
 *
 * @author K神
 * @since 2017/12/29
 */
public class CodeGeneratorTest {

    /**
     * 是否强制带上注解
     */
    boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * 默认是以I开头
     * user表 -> IUserService, UserServiceImpl
     */
    boolean serviceClassNameStartWithI = true;

    @Test
    public void generateCode() {
        String packageName = "cn.lxsir";
//        tableIdType = null;
//        generateByTables(packageName + ".noannoidtype", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = null;
//        generateByTables(packageName + ".noidtype", "user");
//        enableTableFieldAnnotation = false;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".noanno", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".both", "user");
////
//        fieldPrefix = new String[]{"impl"};
//        enableTableFieldAnnotation = false;
//        tableIdType = null;
//        generateByTables(packageName + ".noannoidtypewithprefix", "user");
//        enableTableFieldAnnotation = true;
//        tableIdType = null;
//        generateByTables(packageName + ".noidtypewithprefix", "user");
//        enableTableFieldAnnotation = false;
//        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".noannowithprefix", "user");
        enableTableFieldAnnotation = true;
        tableIdType = IdType.INPUT;
//        generateByTables(packageName + ".withannoidtypeprefix", "user");
        String[] tables = new String[]{
                "challenge_detail",
                "challenge_result",
                "header_click",
                "image_classify",
                "keyword_result",
                "keyword_search_num",
                "question_bank",
                "slide_show",
                "speech_classify",
                "user",
                "uv_page"
        };
        serviceClassNameStartWithI = false;
        generateByTables(packageName, tables);
    }

    private void generateByTables(String packageName, String... tableNames) {
        com.baomidou.mybatisplus.generator.config.GlobalConfig config = new com.baomidou.mybatisplus.generator.config.GlobalConfig();
        String dbUrl = "jdbc:mysql://111.230.72.174:3306/garbagesort?serverTimezone=GMT%2B8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("(xdpo#eW_9aE")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .entityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setFieldPrefix(fieldPrefix)//test_id -> id, test_type -> targetType
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setIdType(tableIdType)
                .setAuthor("luoxiang")
                .setOutputDir("/Users/luoxiang/testpath/image/codeGen")
                .setFileOverride(true);
        config.setServiceName("%sService");
        config.setServiceImplName("%sServiceImpl");
        config.setEntityName("%s");
        config.setMapperName("%sMapper");
        config.setXmlName("%sMapper");
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setEntity("entity")
                ).execute();
    }
}
